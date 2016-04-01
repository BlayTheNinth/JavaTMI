package net.blay09.javatmi;

import net.blay09.javairc.*;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TMIClient {

    private static final Pattern SUBSCRIBE_PATTERN = Pattern.compile("([^ ]+)(?: just)? subscribed!?(?: for )?([0-9]+)?(?: months in a row)?");
    private static final Pattern HOST_PATTERN = Pattern.compile("([^ ]+) is now hosting you(?: for)?([0-9]+)");

    private final TMIListener listener;
    private final IRCConnection client;
    private final TwitchCommands twitchCommands;

    public TMIClient(TMIListener listener) {
        this(getAnonymousUsername(), null, Collections.<String>emptyList(), listener);
    }

    public TMIClient(String username, String oauth, Collection<String> channels, TMIListener listener) {
        this(defaultBuilder()
                .nick(username)
                .password(oauth)
                .autoJoinChannels(channels)
                .build(), listener);
    }

    public TMIClient(final IRCConfiguration configuration, final TMIListener listener) {
        this.listener = listener;
        client = new IRCConnection(configuration, new IRCAdapter() {
            @Override
            public void onConnected(IRCConnection connection) {
                listener.onConnected(TMIClient.this);
            }

            @Override
            public void onDisconnected(IRCConnection connection) {
                listener.onDisconnected(TMIClient.this);
            }

            @Override
            public void onUnhandledException(IRCConnection connection, Exception e) {
                listener.onUnhandledException(TMIClient.this, e);
            }

            @Override
            public boolean onRawMessage(IRCConnection connection, IRCMessage message) {
                switch(message.getCommand()) {
                    case "HOSTTARGET": // channel, target, viewers
                        if(message.arg(1).charAt(0) == '-') {
                            listener.onUnhost(TMIClient.this, message.arg(0), Integer.parseInt(message.arg(2)));
                        } else {
                            listener.onHost(TMIClient.this, message.arg(0), message.arg(1), Integer.parseInt(message.arg(2)));
                        }
                        break;
                    case "ROOMSTATE": // channel
                        String slow = message.getTagByKey("slow");
                        if(slow != null && message.getTagByKey("subs-only") == null) { // Only trigger event if ROOMSTATE occured from change (will only contain changed tag)
                            int slowTime = Integer.parseInt(slow);
                            if(slowTime == 0) {
                                listener.onSlowMode(TMIClient.this, message.arg(0), false, 0);
                            } else {
                                listener.onSlowMode(TMIClient.this, message.arg(0), true, slowTime);
                            }
                        }
                        break;
                    case "CLEARCHAT": // channel, [username]
                        if(message.argCount() > 1) {
                            listener.onTimeout(TMIClient.this, message.arg(0), message.arg(1));
                        } else {
                            listener.onClearChat(TMIClient.this, message.arg(0));
                        }
                        break;
                    case "WHISPER": // username, message
                        listener.onWhisperMessage(TMIClient.this, TwitchUser.fromMessage(message), message.arg(1));
                        break;
                }
                return true;
            }

            @Override
            public void onChannelChat(IRCConnection connection, IRCMessage message, IRCUser user, String channel, String text) {
                if(user.getNick().equals("twitchnotify")) {
                    Matcher matcher = SUBSCRIBE_PATTERN.matcher(text);
                    if(matcher.find()) {
                        if(matcher.group(2) != null) {
                            listener.onResubscribe(TMIClient.this, channel, matcher.group(1), Integer.parseInt(matcher.group(2)));
                        } else {
                            listener.onSubscribe(TMIClient.this, channel, matcher.group(1));
                        }
                    }
                } else if(user.getNick().equals("jtv")) {
                    Matcher matcher = HOST_PATTERN.matcher(text);
                    if(matcher.find()) {
                        listener.onHosted(TMIClient.this, channel, matcher.group(1), matcher.group(2) != null ? Integer.parseInt(matcher.group(2)) : 0);
                    }
                } else if(text.startsWith("\u0001ACTION ") && text.endsWith("\u0001")) {
                    listener.onActionMessage(TMIClient.this, channel, TwitchUser.fromMessage(message), text.substring(8, text.length() - 1));
                } else {
                    listener.onChatMessage(TMIClient.this, channel, TwitchUser.fromMessage(message), text);
                }
            }

            @Override
            public void onChannelNotice(IRCConnection connection, IRCMessage message, IRCUser user, String channel, String text) {
                String messageId = message.getTagByKey("msg-id");
                listener.onServerMessage(TMIClient.this, channel, messageId, text);
                switch(messageId) {
                    case "subs_on":
                        listener.onSubMode(TMIClient.this, channel, true);
                        break;
                    case "subs_off":
                        listener.onSubMode(TMIClient.this, channel, false);
                        break;
                    case "emote_only_on":
                        listener.onEmoteOnly(TMIClient.this, channel, true);
                        break;
                    case "emote_only_off":
                        listener.onEmoteOnly(TMIClient.this, channel, false);
                        break;
                    case "r9k_on":
                        listener.onR9kBeta(TMIClient.this, channel, true);
                        break;
                    case "r9k_off":
                        listener.onR9kBeta(TMIClient.this, channel, false);
                        break;
                }
            }
        });
        twitchCommands = new TwitchCommands(client);
    }

    public void connect() {
        client.start();
    }

    public void disconnect() {
        client.stop();
    }

    public void join(String channel) {
        if(!channel.startsWith("#")) {
            channel = "#" + channel;
        }
        client.sendRaw("JOIN " + channel.toLowerCase());
    }

    public void part(String channel) {
        if(!channel.startsWith("#")) {
            channel = "#" + channel;
        }
        client.sendRaw("PART " + channel.toLowerCase());
    }

    public void send(String channel, String message) {
        if(message.toLowerCase().startsWith("/me ")) {
            twitchCommands.action(channel, message.substring(4));
        } else {
            client.message(channel, message);
        }
    }

    public IRCConnection getIRCConnection() {
        return client;
    }

    public TwitchCommands getTwitchCommands() {
        return twitchCommands;
    }

    private static String getAnonymousUsername() {
        return "justinfan" + Math.floor((Math.random() * 80000) + 1000);
    }

    public static IRCConfiguration.IRCConfigurationBuilder defaultBuilder() {
        return IRCConfiguration.builder()
                .server("irc.chat.twitch.tv")
                .port(6667)
                .capability("twitch.tv/commands")
                .capability("twitch.tv/tags");
    }
}

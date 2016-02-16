package net.blay09.javatmi;

import net.blay09.javairc.IRCConnection;

public class TwitchCommands {

    private final IRCConnection client;

    public TwitchCommands(IRCConnection client) {
        this.client = client;
    }

    public void action(String channel, String message) {
        client.privmsg(channel, "\u0001ACTION " +message + "\u0001");
    }

    public void ban(String channel, String username) {
        client.privmsg(channel, "/ban " + username);
    }

    public void clear(String channel) {
        client.privmsg(channel, "/clear");
    }

    public void color(String color) {
        client.privmsg("#jtv", "/color " + color);
    }

    public void commercial(String channel, int seconds) {
        client.privmsg(channel, "/commercial " + seconds);
    }

    public void emoteonly(String channel) {
        client.privmsg(channel, "/emoteonly");
    }

    public void emoteonlyoff(String channel) {
        client.privmsg(channel, "/emoteonlyoff");
    }

    public void host(String channel, String target) {
        client.privmsg(channel, "/host " + target);
    }

    public void mod(String channel, String username) {
        client.privmsg(channel, "/mod " + username);
    }

    public void mods(String channel) {
        client.privmsg(channel, "/mods");
    }

    public void purge(String channel, String username) {
        timeout(channel, username, 1);
    }

    public void r9kbeta(String channel) {
        client.privmsg(channel, "/r9kbeta");
    }

    public void r9kbetaoff(String channel) {
        client.privmsg(channel, "/r9kbetaoff");
    }

    public void slow(String channel, int seconds) {
        client.privmsg(channel, "/slow " + seconds);
    }

    public void slowoff(String channel) {
        client.privmsg(channel, "/slowoff");
    }

    public void subscribers(String channel) {
        client.privmsg(channel, "/subscribers");
    }

    public void subscribersoff(String channel) {
        client.privmsg(channel, "/subscribersoff");
    }

    public void timeout(String channel, String username, int seconds) {
        client.privmsg(channel, "/timeout " + username + " " + seconds);
    }

    public void unban(String channel, String username) {
        client.privmsg(channel, "/unban " + username);
    }

    public void unhost(String channel) {
        client.privmsg(channel, "/unhost");
    }

    public void unmod(String channel, String username) {
        client.privmsg(channel, "/unmod " + username);
    }

    public void whisper(String username, String message) {
        client.privmsg("#jtv", "/w " + username + " " + message);
    }
}

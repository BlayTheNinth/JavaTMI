package net.blay09.javatmi;

import net.blay09.javairc.IRCConnection;

public class TwitchCommands {

    private final IRCConnection client;

    public TwitchCommands(IRCConnection client) {
        this.client = client;
    }

    public void action(String channel, String message) {
        client.message(channel, "\u0001ACTION " +message + "\u0001");
    }

    public void ban(String channel, String username) {
        client.message(channel, "/ban " + username);
    }

    public void clear(String channel) {
        client.message(channel, "/clear");
    }

    public void color(String color) {
        client.message("#jtv", "/color " + color);
    }

    public void commercial(String channel, int seconds) {
        client.message(channel, "/commercial " + seconds);
    }

    public void emoteonly(String channel) {
        client.message(channel, "/emoteonly");
    }

    public void emoteonlyoff(String channel) {
        client.message(channel, "/emoteonlyoff");
    }

    public void host(String channel, String target) {
        client.message(channel, "/host " + target);
    }

    public void mod(String channel, String username) {
        client.message(channel, "/mod " + username);
    }

    public void mods(String channel) {
        client.message(channel, "/mods");
    }

    public void purge(String channel, String username) {
        timeout(channel, username, 1);
    }

    public void r9kbeta(String channel) {
        client.message(channel, "/r9kbeta");
    }

    public void r9kbetaoff(String channel) {
        client.message(channel, "/r9kbetaoff");
    }

    public void slow(String channel, int seconds) {
        client.message(channel, "/slow " + seconds);
    }

    public void slowoff(String channel) {
        client.message(channel, "/slowoff");
    }

    public void subscribers(String channel) {
        client.message(channel, "/subscribers");
    }

    public void subscribersoff(String channel) {
        client.message(channel, "/subscribersoff");
    }

    public void timeout(String channel, String username, int seconds) {
        client.message(channel, "/timeout " + username + " " + seconds);
    }

    public void unban(String channel, String username) {
        client.message(channel, "/unban " + username);
    }

    public void unhost(String channel) {
        client.message(channel, "/unhost");
    }

    public void unmod(String channel, String username) {
        client.message(channel, "/unmod " + username);
    }

    public void whisper(String username, String message) {
        client.message("#jtv", "/w " + username + " " + message);
    }
}

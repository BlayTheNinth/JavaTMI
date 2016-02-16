package net.blay09.javatmi;

public interface TMIListener {
    void onActionMessage(TMIClient client, String channel, TwitchUser user, String message);
    void onChatMessage(TMIClient client, String channel, TwitchUser user, String message);
    void onWhisperMessage(TMIClient client, TwitchUser user, String message);
    void onServerMessage(TMIClient client, String channel, String messageId, String message);

    void onConnected(TMIClient client);
    void onDisconnected(TMIClient client);

    void onSubscribe(TMIClient client, String channel, String username);
    void onResubscribe(TMIClient client, String channel, String username, int months);
    void onHost(TMIClient client, String channel, String username, int viewers);
    void onUnhost(TMIClient client, String channel, int viewers);

    void onTimeout(TMIClient client, String channel, String username);
    void onClearChat(TMIClient client, String channel);

    void onEmoteOnly(TMIClient client, String channel, boolean enabled);
    void onR9kBeta(TMIClient client, String channel, boolean enabled);
    void onSlowMode(TMIClient client, String channel, boolean enabled, int seconds);
    void onSubMode(TMIClient client, String channel, boolean enabled);

    void onHosted(TMIClient client, String channel, String username, int viewers);
}

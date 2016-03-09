package net.blay09.javatmi;

import lombok.Data;
import net.blay09.javairc.IRCMessage;
import net.blay09.javairc.IRCUser;

import java.util.Objects;

@Data
public class TwitchUser {
    private final IRCUser user;
    private String color;
    private String displayName;
    private int userId;
    private UserType userType;
    private boolean mod;
    private boolean subscriber;
    private boolean turbo;

    public TwitchUser(IRCUser user) {
        this.user = user;
    }

    public boolean hasColor() {
        return color != null && !color.isEmpty();
    }

    public String getDisplayName() {
        return displayName != null ? displayName : user.getNick();
    }

    public String getName() {
        return user.getNick();
    }

    public static TwitchUser fromMessage(IRCMessage message) {
        TwitchUser twitchUser = new TwitchUser(message.parseSender());
        twitchUser.color = message.getTagByKey("color");
        twitchUser.displayName = message.getTagByKey("display-name");
        twitchUser.mod = Objects.equals(message.getTagByKey("mod"), "1");
        twitchUser.subscriber = Objects.equals(message.getTagByKey("subscriber"), "1");
        twitchUser.turbo = Objects.equals(message.getTagByKey("turbo"), "1");
        try {
            twitchUser.userId = Integer.parseInt(message.getTagByKey("user-id"));
        } catch(NumberFormatException ignored) {
        }
        twitchUser.userType = UserType.fromTag(message.getTagByKey("user-type"));
        return twitchUser;
    }
}

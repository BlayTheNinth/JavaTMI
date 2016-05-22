package net.blay09.javatmi;

import lombok.Data;
import net.blay09.javairc.IRCMessage;
import net.blay09.javairc.IRCUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Data
public class TwitchUser {
    private final IRCUser user;
    private String[] badges;
    private List<TwitchEmote> emotes = Collections.emptyList();
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

    public boolean hasEmotes() {
        return emotes != null;
    }

    public String getDisplayName() {
        return displayName != null && !displayName.isEmpty() ? displayName : user.getNick();
    }

    public String getNick() {
        return user.getNick();
    }

    public static TwitchUser fromMessage(IRCMessage message) {
        TwitchUser twitchUser = new TwitchUser(message.parseSender());
        twitchUser.badges = message.getTagByKey("badges").split(",");
        String[] emotes = message.getTagByKey("emotes").split("/");
        for(String emoteData : emotes) {
            if(twitchUser.emotes == null) {
                twitchUser.emotes = new ArrayList<>();
            }
            int colonIdx = emoteData.indexOf(':');
            if(colonIdx != -1) {
                int emoteId = Integer.parseInt(emoteData.substring(0, colonIdx));
                String[] occurences = emoteData.substring(colonIdx + 1).split(",");
                for(String occurenceData : occurences) {
                    int dashIdx = occurenceData.indexOf('-');
                    if(dashIdx != -1) {
                        int start = Integer.parseInt(occurenceData.substring(0, dashIdx));
                        int end = Integer.parseInt(occurenceData.substring(dashIdx + 1));
                        twitchUser.emotes.add(new TwitchEmote(emoteId, start, end));
                    }
                }
            }
        }
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

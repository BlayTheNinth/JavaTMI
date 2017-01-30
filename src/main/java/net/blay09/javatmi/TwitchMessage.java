package net.blay09.javatmi;

import lombok.Value;

@Value
public class TwitchMessage {
	public String message;
	public int channelId;
	public boolean isAction;
	public int bits;
}

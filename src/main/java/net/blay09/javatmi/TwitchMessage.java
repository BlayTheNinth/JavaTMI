package net.blay09.javatmi;

public class TwitchMessage {
	public final String message;
	public final int channelId;
	public final boolean isAction;
	public final int bits;

	public TwitchMessage(String message, int channelId, boolean isAction, int bits) {
		this.message = message;
		this.channelId = channelId;
		this.isAction = isAction;
		this.bits = bits;
	}

	public String getMessage() {
		return message;
	}

	public int getChannelId() {
		return channelId;
	}

	public boolean isAction() {
		return isAction;
	}

	public int getBits() {
		return bits;
	}
}

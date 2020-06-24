package net.blay09.javatmi;

public class TwitchEmote {
	private final String id;
	private final int start;
	private final int end;

	public TwitchEmote(String id, int start, int end) {
		this.id = id;
		this.start = start;
		this.end = end;
	}

	public String getId() {
		return id;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}
}

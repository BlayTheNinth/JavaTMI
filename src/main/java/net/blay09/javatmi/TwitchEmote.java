package net.blay09.javatmi;

import lombok.Value;

@Value
public class TwitchEmote {
	private final int id;
	private final int start;
	private final int end;
}

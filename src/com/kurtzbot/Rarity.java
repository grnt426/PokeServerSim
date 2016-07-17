package com.kurtzbot;

public enum Rarity {
	EVERYWHERE (617),
	FREQUENT (787),
	VERY_COMMON (1037),
	COMMON (2017),
	UNCOMMON (3467),
	VERY_UNCOMMON (5657),
	ALMOST_RARE (10627),
	RARE (31063),
	VERY_RARE(67943),
	LEGENDARY(104729);

	private final long interval;

	Rarity(long value) {
		this.interval = value;
	}

	public long getInterval() {
		return interval;
	}
}

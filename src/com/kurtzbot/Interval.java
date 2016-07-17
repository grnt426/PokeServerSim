package com.kurtzbot;

/**
 * Maps a particular Mon to a given Rarity, which defines the triggering
 * interval.
 *
 * TODO: This class should only be instantiated by a Factory to minimize object count
 */
public class Interval {

	public final Mon mon;
	public Rarity rarity;

	public Interval(Mon mon, Rarity rarity) {
		this.mon = mon;
		this.rarity = rarity;
	}

	public Mon getMon() {
		return mon;
	}

	public long getInterval() {
		return rarity.getInterval();
	}

	public Rarity getRarity() {
		return rarity;
	}
}

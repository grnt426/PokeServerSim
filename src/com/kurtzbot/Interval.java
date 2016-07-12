package com.kurtzbot;

public class Interval {

	public final Mon mon;
	public final long interval;

	public Interval(Mon mon, long interval) {
		this.mon = mon;
		this.interval = interval;
	}

	public Mon getMon() {
		return mon;
	}

	public long getInterval() {
		return interval;
	}
}

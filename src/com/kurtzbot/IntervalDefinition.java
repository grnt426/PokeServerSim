package com.kurtzbot;

import java.util.ArrayList;

public class IntervalDefinition {

	public static final long INTERVAL_LENGTH = 5_000L;
	public final long MAX_VAL = 100_000_000L;
	public ArrayList<Interval> intervals;

	public IntervalDefinition() {
		intervals = new ArrayList<>();
		intervals.add(new Interval(Mon.FIREMON, 3467L));
		intervals.add(new Interval(Mon.WATERMON, 1037L));
		intervals.add(new Interval(Mon.LEAFMON, 2017L));
	}
}

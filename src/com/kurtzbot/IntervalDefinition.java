package com.kurtzbot;

import java.util.ArrayList;

/**
 * Defines the period for which Mons should spawn.
 *
 * Prime numbers are pulled from this list: http://www.primos.mat.br/primeiros_10000_primos.txt
 */
public class IntervalDefinition {

	public static final long INTERVAL_LENGTH = 5_000L;
	public final long MAX_VAL = 100_000_000L;
	public ArrayList<Interval> intervals;

	/**
	 * Default spawn rates, good for any boring region.
	 */
	public IntervalDefinition() {
		intervals = new ArrayList<>();
		intervals.add(new Interval(Mon.FIREMON, Rarity.UNCOMMON));
		intervals.add(new Interval(Mon.WATERMON, Rarity.VERY_COMMON));
		intervals.add(new Interval(Mon.LEAFMON, Rarity.COMMON));
		intervals.add(new Interval(Mon.ELECTRICMON, Rarity.ALMOST_RARE));
		intervals.add(new Interval(Mon.NORMALMON, Rarity.FREQUENT));
		intervals.add(new Interval(Mon.ROCKMON, Rarity.UNCOMMON));
	}

	/**
	 * Seeds itself with the Default spawn rates, then applies the definitions provided.
	 * @param definitions   The varargs list of differences that should override the default set.
	 */
	public IntervalDefinition(Interval... definitions) {
		this();

		// TODO: Optimization
		// Yes, I know the below is O(n^2), its fine for the small datasets we are working with for now.
		for(Interval interval : definitions) {
			replaceInterval(interval.getMon(), interval.getRarity());
		}
	}

	private void replaceInterval(Mon mon, Rarity rarity) {
		for(Interval interval : intervals) {
			if(interval.getMon().equals(mon)) {
				interval.rarity = rarity;
			}
		}
	}
}

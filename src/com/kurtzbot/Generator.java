package com.kurtzbot;

import java.util.List;
import java.util.stream.Collectors;

public class Generator {

	private final List<Region> regions;
	IntervalDefinition intervals;

	public Generator(List<Region> regions) {
		intervals = new IntervalDefinition();
		this.regions = regions;
	}

	public List<Mon> generate(long time, long x, long y) {
		long result = hash(hash(hash(0, time), x), y) % intervals.MAX_VAL;
		IntervalDefinition intervalsToCheck = getIntervalToUse(time, x, y);
		List<Mon> matching = intervalsToCheck.intervals.stream()
				.filter(interval -> result % interval.getInterval() == 0)
				.map(Interval::getMon)
				.collect(Collectors.toList());
		return matching;
	}

	private IntervalDefinition getIntervalToUse(long time, long x, long y) {

		// TODO: Optimization
		// For now, we are OK just doing a raw search through all regions
		// Later, we can employ BSP for much faster region look-ups
		for(Region region : regions) {
			if(x <= region.getStartX() + region.getWidth() && x >= region.getStartX()) {
				if (y <= region.getStartY() + region.getHeight() && y >= region.getStartY()) {
					return region.getIntervalDefinition();
				}
			}
		}

		// TODO: Add daily intervals which change with the time of day

		// Return the default interval definitions if nothing else matches
		return intervals;
	}

	private long hash(long init, long val) {
		return 31 * init + (int) (val ^ (val >>> 32));
	}


}

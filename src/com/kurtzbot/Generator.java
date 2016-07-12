package com.kurtzbot;

import java.util.List;
import java.util.stream.Collectors;

public class Generator {

	IntervalDefinition intervals;

	public Generator() {
		intervals = new IntervalDefinition();
	}

	public List<Mon> generate(long time, long x, long y) {
		long result = hash(hash(hash(0, time), x), y) % intervals.MAX_VAL;
		List<Mon> matching = intervals.intervals.stream()
				.filter(interval -> result % interval.getInterval() == 0)
				.map(Interval::getMon)
				.collect(Collectors.toList());
		return matching;
	}

	private long hash(long init, long val) {
		return 31 * init + (int) (val ^ (val >>> 32));
	}


}

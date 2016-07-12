package com.kurtzbot;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Cache {

	Queue<CachedMon> cache = new ConcurrentLinkedQueue<>();

	public void clearDead(long cur) {
		for (CachedMon mon : cache) {
			if (mon.lifetime <= cur) {
				cache.poll();
			} else {
				return;
			}
		}
	}

	public void add(long x, long y, long curTime) {
		cache.add(new CachedMon(x, y, curTime));
	}

	public Queue<CachedMon> getCurrent() {
		return cache;
	}
}

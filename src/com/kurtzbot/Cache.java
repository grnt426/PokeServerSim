package com.kurtzbot;

import java.util.LinkedList;
import java.util.Queue;

public class Cache {

	Queue<CachedMon> cache = new LinkedList<>();

	public synchronized void clearDead(long cur) {
		for(CachedMon mon : cache) {
			if(mon.lifetime <= cur) {
				cache.poll();
			}
			else{
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

package com.kurtzbot;

public class CachedMon {

	long lifetime;
	long x;
	long y;

	public CachedMon(long x, long y, long lifetime) {
		this.x = x;
		this.y = y;
		this.lifetime = lifetime;
	}
}

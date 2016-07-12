package com.kurtzbot;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class Player {

	// For seeding movement to something predictable
	static int id;
	Random random = new Random(id++);

	long x;
	long y;
	Color c;

	HashMap<Mon, Integer> captured;

	public Player(long x, long y, Color c) {
		this.x = x;
		this.y = y;
		this.c = c;
		captured = new HashMap<>();
	}

	public void move() {
		x += random.nextInt(11) - 5;
		y += random.nextInt(11) - 5;
		if(x > Main.PLAYFIELD_X)
			x = x % Main.PLAYFIELD_X;
		else if(x < 0)
			x = Main.PLAYFIELD_X - x;

		if(y > Main.PLAYFIELD_Y)
			y = y % Main.PLAYFIELD_Y;
		else if(y < 0)
			y = Main.PLAYFIELD_Y - y;
	}

	public void capture(Mon mon) {
		Integer before = captured.get(mon);
		captured.put(mon, before == null ? 1 : ++before);
	}

	public HashMap<Mon, Integer> getCaptured() {
		return captured;
	}
}

package com.kurtzbot;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Main {

	public static final long PLAYFIELD_X = 800;
	public static final long PLAYFIELD_Y = 800;

	public static void main(String[] args) {

		Random random = new Random(0);

		// Generate Players
		List<Player> players = new ArrayList<>();
		players.add(new Player(10, 10, new Color(213, 0, 0), "Red"));
		players.add(new Player(220, 370, new Color(0, 9, 219), "Blue"));
		players.add(new Player(780, 630, new Color(229, 227, 24), "Yellow"));
		players.add(new Player(500, 400, new Color(77, 225, 57), "Green"));

		// Mons that are spawned are cached for a time
		Cache cache = new Cache();

		// Build special regions
		List<Region> regions = buildSpecialRegions();

		// Mon generator
		Generator gen = new Generator(regions);

		// Tell renderer what's up
		WorldWindow window = new WorldWindow(cache, regions);
		int stepsToMove = 0;
		while (window.isStillOpen()) {
			long curTime = System.currentTimeMillis();
			cache.clearDead(curTime);
			HashMap<String, Mon> totalMon = new HashMap<>();
			for (Player p : players) {

				// Move it, Move it!
				if (stepsToMove++ > 5) {
					p.move();
					stepsToMove = 0;
				}

				// Run into the high grass and encounter...
				List<Mon> generated = gen.generate(curTime, p.x, p.y);

				// ...a Mon! Give it a location
				for (Mon mon : generated) {
					p.capture(mon);
					long x = p.x + random.nextInt(20) - 10;
					long y = p.y + random.nextInt(20) - 10;

					// For now, we aren't planning on retrieving anything other than location, so
					// we don't have to bother with what Mon it is.
					cache.add(x, y, curTime + IntervalDefinition.INTERVAL_LENGTH);
				}
			}

			// Make a masterpiece
			window.update(players);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (Player player : players) {
			System.out.println(player.getName() + " caught " + player.getCaptured().size()
					+ " unique types!");
			for (Mon mon : player.getCaptured().keySet()) {
				System.out.println(player.getCaptured().get(mon) + " " + mon + " captured.");
			}
		}
	}

	private static List<Region> buildSpecialRegions() {
		List<Region> regions = new ArrayList<Region>();
		Region beach = new Region(200, 50, 400, 100, new Color(31, 160, 163, 120));
		beach.setupIntervalDefinitions(new Interval(Mon.WATERMON, Rarity.FREQUENT),
				new Interval(Mon.FIREMON, Rarity.VERY_UNCOMMON));
		regions.add(beach);

		Region city = new Region(400, 300, 200, 200, new Color(209, 150, 57, 120));
		city.setupIntervalDefinitions(new Interval(Mon.ELECTRICMON, Rarity.UNCOMMON));
		regions.add(city);

		return regions;
	}
}

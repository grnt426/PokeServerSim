package com.kurtzbot;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {

	    // Generate Players
	    List<Player> players = new ArrayList<>();
	    players.add(new Player(10, 10, new Color(213, 0, 0)));
	    players.add(new Player(220, 370, new Color(0, 9, 219)));
	    players.add(new Player(780, 630, new Color(46, 232, 39)));

	    Cache cache = new Cache();

	    // Generate World
	    Generator gen = new Generator();

	    // Tell renderer what's up
	    WorldWindow window = new WorldWindow(cache);

	    while(true) {
		    long curTime = System.currentTimeMillis();
		    cache.clearDead(curTime);
		    HashMap<String, Mon> totalMon = new HashMap<>();
		    for(Player p : players) {

			    // Move it, Move it!
			    p.move();

			    // Run into the high grass and encounter...
			    List<Mon> generated = gen.generate(curTime, p.x, p.y);
			    if (!generated.isEmpty()) {

			    }

			    // ...a Mon! Give it a location
			    for(Mon mon : generated) {
				    System.out.println("Found one! " + p.x + " " + p.y);
				    cache.add(p.x, p.y, curTime + IntervalDefinition.INTERVAL_LENGTH);
			    }
		    }

		    // Make a masterpiece
		    window.update(players);
		    try {
			    Thread.sleep(11);
		    } catch (InterruptedException e) {
			    e.printStackTrace();
		    }
	    }
    }
}

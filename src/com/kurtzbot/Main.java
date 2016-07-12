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
	    players.add(new Player(10, 10, new Color(213, 0, 0)));
	    players.add(new Player(220, 370, new Color(0, 9, 219)));
	    players.add(new Player(780, 630, new Color(46, 232, 39)));

	    Cache cache = new Cache();

	    // Generate World
	    Generator gen = new Generator();

	    // Tell renderer what's up
	    WorldWindow window = new WorldWindow(cache);
	    int stepsToMove = 0;
	    while(true) {
		    long curTime = System.currentTimeMillis();
		    cache.clearDead(curTime);
		    HashMap<String, Mon> totalMon = new HashMap<>();
		    for(Player p : players) {

			    // Move it, Move it!
			    if(stepsToMove++ > 5) {
				    p.move();
				    stepsToMove = 0;
			    }

			    // Run into the high grass and encounter...
			    List<Mon> generated = gen.generate(curTime, p.x, p.y);
			    if (!generated.isEmpty()) {

			    }

			    // ...a Mon! Give it a location
			    for(Mon mon : generated) {
				    long x = p.x + random.nextInt(20) - 10;
				    long y = p.y + random.nextInt(20) - 10;
				    System.out.println("Found one! " + x + " " + y);
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
    }
}

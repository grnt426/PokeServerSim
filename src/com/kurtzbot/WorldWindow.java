package com.kurtzbot;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WorldWindow extends JFrame {

	WorldRenderer renderer;

	public WorldWindow(Cache cache) {
		renderer = new WorldRenderer((int)Main.PLAYFIELD_X, (int)Main.PLAYFIELD_Y, cache);
		setTitle("PokeSim");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension((int)Main.PLAYFIELD_X, (int)Main.PLAYFIELD_Y));
		add(renderer);
		setVisible(true);
	}

	public void update(List<Player> players) {
		renderer.repaint(players);
	}
}

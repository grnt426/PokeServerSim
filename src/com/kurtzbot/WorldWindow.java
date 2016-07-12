package com.kurtzbot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class WorldWindow extends JFrame {

	WorldRenderer renderer;

	private boolean stillOpen = true;

	public WorldWindow(Cache cache) {
		renderer = new WorldRenderer((int) Main.PLAYFIELD_X, (int) Main.PLAYFIELD_Y, cache);
		setTitle("PokeSim");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(new Dimension((int) Main.PLAYFIELD_X, (int) Main.PLAYFIELD_Y));
		add(renderer);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				stillOpen = false;
				dispose();
			}
		});
	}

	public void update(List<Player> players) {
		renderer.repaint(players);
	}

	public boolean isStillOpen() {
		return stillOpen;
	}
}

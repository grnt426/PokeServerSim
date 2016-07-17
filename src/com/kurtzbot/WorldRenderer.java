package com.kurtzbot;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WorldRenderer extends JPanel {

	private final int maxWidth;
	private final int maxHeight;
	private final List<Region> regions;
	private List<Player> players;
	private Cache cache;


	public WorldRenderer(int maxWidth, int maxHeight, Cache cache, List<Region> regions) {
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
		this.cache = cache;
		this.regions = regions;
	}

	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);

		setBackground(Color.WHITE);

		drawGrid(g);
		drawRegions(g);
		drawPlayers(g);
		drawMon(g);
	}

	private void drawRegions(Graphics g) {
		for(Region region : regions) {
			g.setColor(region.getC());
			g.fillRect(region.getStartX(), region.getStartY(), region.getWidth(), region.getHeight());
		}
	}

	private void drawMon(Graphics g) {
		for (CachedMon mon : cache.getCurrent()) {
			g.setColor(new Color(255, 135, 232));
			g.drawOval((int) mon.x - 12, (int) mon.y - 12, 24, 24);
		}
	}

	private void drawPlayers(Graphics g) {
		for (Player p : players) {
			g.setColor(p.c);
			g.fillOval((int) p.x - 4, (int) p.y - 4, 8, 8);
		}
	}

	private void drawGrid(Graphics g) {

		int dotSize = 3;
		int totalPoints = 100;
		int spacing = maxWidth / totalPoints;
		g.setColor(new Color(0, 0, 0));
		for (int x = 0; x < totalPoints; x++) {
			for (int y = 0; y < totalPoints; y++) {
				g.drawRect(spacing / 2 + x * spacing, spacing / 2 + y * spacing, dotSize, dotSize);
			}
		}
	}

	public void repaint(List<Player> players) {
		this.players = players;
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(maxWidth, maxHeight);
	}
}

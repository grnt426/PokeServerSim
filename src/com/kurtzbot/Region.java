package com.kurtzbot;

import java.awt.*;

/**
 * Regions define areas with special, certain, or rare types of Mons. For now, they are defined
 * simply as rectangles. Coordinates define the top-left starting position of the Region.
 */
public class Region {

	private final Color c;
	private int startX;
	private int startY;
	private int width;
	private int height;
	private IntervalDefinition intervalDefinition;

	public Region(int startX, int startY, int width, int height, Color c) {
		this.startX = startX;
		this.startY = startY;
		this.width = width;
		this.height = height;
		this.c = c;
	}

	public void setupIntervalDefinitions(Interval... definitions) {
		intervalDefinition = new IntervalDefinition(definitions);
	}

	public Color getC() {
		return c;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public IntervalDefinition getIntervalDefinition() {
		return intervalDefinition;
	}
}

package com.escapeindustries.numericdisplay;

import java.util.GregorianCalendar;

import android.graphics.Paint;

public class CountdownPaintUpdateProvider implements PaintUpdateProvider {

	private Paint[] lit;
	private Paint[] dim;
	private long[] timeOnColor;
	private int current;
	private long lastChange;

	public CountdownPaintUpdateProvider(Paint[] lit, Paint[] dim,
			long[] timeOnColor) {
		this.lit = lit;
		this.dim = dim;
		this.timeOnColor = timeOnColor;
		this.current = 0;
		this.lastChange = getNow();
	}

	private long getNow() {
		return GregorianCalendar.getInstance().getTimeInMillis();
	}

	@Override
	public long getNextPossibleUpdateTime() {
		return lastChange + (timeOnColor[current] * 1000);
	}

	@Override
	public Paint[] getCurrentPaints() {
		long now = getNow();
		// Move on to the next color if we have passed the time limit for the
		// current color
		if (now > lastChange + (timeOnColor[current] * 1000)) {
			current = current == (lit.length - 1) ? 0 : current + 1;
			lastChange = now;
		}
		return new Paint[] { lit[current], dim[current] };
	}
}
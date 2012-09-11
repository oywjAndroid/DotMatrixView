package com.escapeindustries.numericdisplay;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GridFullRenderer implements DrawStrategy {

	private ModelGrid grid;
	private int rows;
	private int columns;
	private int[][] coordsX;
	private int[][] coordsY;
	private float radius;

	public GridFullRenderer(ModelGrid grid, int rows, int columns,
			int[][] coordsX, int[][] coordsY, float radius) {
		this.grid = grid;
		this.rows = rows;
		this.columns = columns;
		this.coordsX = coordsX;
		this.coordsY = coordsY;
		this.radius = radius;
	}

	@Override
	public void draw(Canvas canvas, Paint[] paints) {
		canvas.drawColor(Color.BLACK);
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				canvas.drawCircle(coordsX[row][column], coordsY[row][column],
						radius, paints[grid.getDotState(column, row)]);
			}
		}
	}

}

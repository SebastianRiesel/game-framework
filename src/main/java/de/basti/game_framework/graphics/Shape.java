package de.basti.game_framework.graphics;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Should be extended by all {@code Drawable} implementations which represent a
 * shape. This has variables for stroking or filling Color, the width of outer
 * lines and the position.
 * 
 * @see Drawable
 * @see Rectagle
 */
public abstract class Shape implements Drawable {

	protected Paint strokeColor = Color.BLACK;
	protected Paint fillColor = Color.BLUE;
	protected double lineWidth = 2;
	
	public Paint getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(Paint strokeColor) {
		this.strokeColor = strokeColor;
	}

	public Paint getFillColor() {
		return fillColor;
	}

	public void setFillColor(Paint fillColor) {
		this.fillColor = fillColor;
	}

	public double getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
	}
}

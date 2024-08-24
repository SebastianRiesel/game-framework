package de.basti.game_framework.graphics;

import de.basti.game_framework.math.Rectangle;
import de.basti.game_framework.math.Vector2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * Implementation of {@code Shape} which represents a single-colored Rectangle.
 * 
 * @see Shape
 * @see Drawable
 */
public class DrawableRectangle extends Shape{

	private Rectangle rectangle;

	public DrawableRectangle(Vector2D position, double width, double height) {
		this(new Rectangle(position, width, height));

	}

	public DrawableRectangle(Rectangle rect) {
		this.rectangle = rect;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.beginPath();
		gc.setStroke(strokeColor);
		gc.setFill(fillColor);
		gc.setLineWidth(lineWidth);

		Vector2D position = this.rectangle.getPosition();
		double w = this.rectangle.getWidth();
		double h = this.rectangle.getHeight();

		gc.fillRect(position.getX() - w / 2, position.getY() - h / 2, w, h);
		gc.strokeRect(position.getX() - w / 2, position.getY() - h / 2, w, h);

		gc.closePath();
	}

	public double getWidth() {
		return this.rectangle.getWidth();
	}

	public void setWidth(double width) {
		this.rectangle.setWidth(width);
	}

	public double getHeight() {
		return this.rectangle.getHeight();
	}

	public void setHeight(double height) {
		this.rectangle.setHeight(height);
	}

	public Vector2D getPosition() {
		return this.rectangle.getPosition();
	}

	public void translate(Vector2D vector) {
		this.rectangle.translate(vector);
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	

	@Override
	public String toString() {
		return "DrawableRectangle [rectangle=" + rectangle + "]";
	}

}

package de.basti.game_framework.graphics;

import de.basti.game_framework.math.Circle;
import de.basti.game_framework.math.Vector2D;
import javafx.scene.canvas.GraphicsContext;

public class DrawableCircle extends Shape {
	private Circle circle;

	public DrawableCircle(Vector2D position, double radius) {
		this(new Circle(position, radius));
	}

	public DrawableCircle(Circle circle) {
		this.circle = circle;
	}

	public double getRadius() {
		return this.circle.getRadius();
	}

	public void setRadius(double radius) {
		this.circle.setRadius(radius);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.beginPath();
		gc.setStroke(strokeColor);
		gc.setFill(fillColor);
		gc.setLineWidth(lineWidth);

		Vector2D position = this.circle.getPosition();
		double radius = this.circle.getRadius();

		gc.fillOval(position.getX() - radius, position.getY() - radius, radius * 2, radius * 2);

		gc.strokeOval(position.getX() - radius, position.getY() - radius, radius * 2, radius * 2);

		gc.closePath();

	}

	@Override
	public void translate(Vector2D vector) {
		this.circle.translate(vector);
	}

}

package de.basti.game_framework.collision;


import de.basti.game_framework.math.Rectangle;
import de.basti.game_framework.math.Vector2D;

public class CircleCollider implements Collider {
	
	private Vector2D position;
	private double radius;

	int precision = 10;

	private Vector2D[] vectors = new Vector2D[precision];

	public CircleCollider(Vector2D position, double radius) {
		this.setPosition(position);
		this.setRadius(radius);
		this.recalculateVectors();

	}

	private void recalculateVectors() {
		for (int i = 0; i < precision; i++) {
			double a = (double) i;
			a = a / precision;
			a = a * 2 * Math.PI;
			Vector2D v = new Vector2D();
			v.set(Math.sin(a) * radius, Math.cos(a) * radius);
			v.translate(this.position.getX(), this.position.getY());
			this.vectors[i] = v;
		}
	}

	@Override
	public boolean collidesWith(Vector2D vector) {
		Vector2D center = this.position.clone();
		center.translate(radius, radius);
		center.translate(vector.scaled(-1));
		boolean collidesWith = center.length() < this.radius;
		return collidesWith;
		

	}

	@Override
	public Vector2D[] getVectors() {
		this.recalculateVectors();

		return this.vectors;
	}

	@Override
	public void translate(Vector2D vector) {
		this.position.translate(vector);
		this.recalculateVectors();

	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
		this.recalculateVectors();

	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
		this.recalculateVectors();
	}

	@Override
	public Rectangle getEnclosingBounds() {
		return new Rectangle(this.position.translated(-this.radius,-this.radius),this.radius*2, this.radius*2);
		
	}
	
	

}

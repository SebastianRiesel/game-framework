package de.basti.game_framework.collision;

import de.basti.game_framework.collision.system.NaiveCollisionSystem;
import de.basti.game_framework.math.Rectangle;
import de.basti.game_framework.math.Vector2D;

/**
 * A {@code Collider} representing a Rectangle collider.
 * 
 * @see Collider
 * @see NaiveCollisionSystem
 * 
 */
public class BoxCollider implements Collider {
	
	private Rectangle bounds;

	public static int vectorsPerSide = 5;

	private Vector2D[] vectors = { new Vector2D(), new Vector2D(), new Vector2D(), new Vector2D(), };

	public BoxCollider(Vector2D position, double width, double height) {
		this(new Rectangle(position, width, height));

	}

	public BoxCollider(Rectangle bounds) {
		super();
		this.bounds = bounds;
		this.recalculateVectors();

	}

	@Override
	public boolean collidesWith(Vector2D vector) {
		double x = this.bounds.getPosition().getX() - this.bounds.getWidth() / 2;
		double y = this.bounds.getPosition().getY() - this.bounds.getHeight() / 2;

		double x1 = vector.getX();
		double y1 = vector.getY();

		boolean xCollision = ((x1 >= x) && (x1 <= x + this.bounds.getWidth()));
		boolean yCollision = ((y1 >= y) && (y1 <= y + this.bounds.getHeight()));

		boolean collidesWith = xCollision && yCollision;
		return collidesWith;

	}

	@Override
	public Vector2D[] getVectors() {
		return vectors;

	}

	private void recalculateVectors() {
		double x = this.bounds.getPosition().getX();
		double y = this.bounds.getPosition().getY();
		double w = this.bounds.getWidth();
		double h = this.bounds.getHeight();

		vectors[0].set(x - w / 2, y - h / 2);
		vectors[1].set(x - w / 2, y + h / 2);
		vectors[2].set(x + w / 2, y - h / 2);
		vectors[3].set(x + w / 2, y + h / 2);

	}

	@Override
	public void translate(Vector2D vector) {
		this.bounds.translate(vector);
		this.recalculateVectors();
	}

	public Vector2D getPosition() {

		return bounds.getPosition();
	}

	public void setPosition(Vector2D position) {
		this.bounds.setPosition(position);
		this.recalculateVectors();
	}

	public double getWidth() {
		return bounds.getWidth();
	}

	public void setWidth(double width) {
		this.bounds.setWidth(width);
		this.recalculateVectors();
	}

	public double getHeight() {
		return bounds.getHeight();
	}

	public void setHeight(double height) {
		this.bounds.setHeight(height);
		this.recalculateVectors();
	}

	@Override
	public Rectangle getEnclosingBounds() {
		return this.bounds;
	}

}

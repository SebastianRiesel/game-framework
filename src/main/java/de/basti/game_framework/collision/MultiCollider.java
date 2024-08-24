package de.basti.game_framework.collision;

import de.basti.game_framework.collision.system.NaiveCollisionSystem;
import de.basti.game_framework.math.Rectangle;
import de.basti.game_framework.math.Vector2D;

/**
 * A {@code Collider} which can have multiple other {@code Collider}s added to
 * it. These are all considered in the collisions with this collider.
 * 
 * @see Collider
 * @see NaiveCollisionSystem
 */
public class MultiCollider implements Collider {

	private Collider[] colliders;

	private MultiCollider(Collider... colliders) {
		super();
		this.colliders = colliders;
	}

	@Override
	public boolean collidesWith(Vector2D vector) {
		for (Collider collider : this.colliders) {
			if (collider.collidesWith(vector)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Vector2D[] getVectors() {
		Vector2D[] allVectors = this.colliders[0].getVectors();
		for (int i = 1; i < colliders.length; i++) {
			Vector2D[] newVectors = this.colliders[i].getVectors();

			Vector2D[] allVectors2 = new Vector2D[allVectors.length + newVectors.length];
			System.arraycopy(allVectors, 0, allVectors2, 0, allVectors.length);
			System.arraycopy(newVectors, 0, allVectors2, allVectors.length, newVectors.length);
			allVectors = allVectors2;
		}
		return allVectors;
	}

	@Override
	public void translate(Vector2D vector) {
		for (Collider collider : this.colliders) {
			collider.translate(vector);

		}
	}

	@Override
	public Rectangle getEnclosingBounds() {

		Vector2D upperLeft = this.colliders[0].getEnclosingBounds().getPosition().clone();

		Vector2D lowerRight = upperLeft.clone();

		Vector2D pos = new Vector2D();

		for (Collider coll : this.colliders) {
			Rectangle bounds = coll.getEnclosingBounds();
			pos.set(bounds.getPosition().getX(),bounds.getPosition().getY());
			if (pos.getX() < upperLeft.getX()) {
				upperLeft.setX(pos.getX());
			}
			if (pos.getY() < upperLeft.getY()) {
				upperLeft.setX(pos.getY());
			}

			pos.set(pos.getX() + bounds.getWidth(), pos.getY() + bounds.getHeight());

			if (pos.getX() > lowerRight.getX()) {
				lowerRight.setX(pos.getX());
			}
			if (pos.getY() > lowerRight.getY()) {
				lowerRight.setX(pos.getY());
			}
		}
		
		return new Rectangle(upperLeft, lowerRight.getX()-upperLeft.getX(),lowerRight.getY()-upperLeft.getY());

	}

}

package de.basti.game_framework.collision;



import de.basti.game_framework.collision.system.NaiveCollisionSystem;
import de.basti.game_framework.math.Rectangle;
import de.basti.game_framework.math.Vector2D;

/**
 * Represents an Object which can collide with other {@code Collider}s in the {@code GameCollisionSystem} class.
 * Gives out all its Vectors(should be its corners) via {@code getVectors()} and has a method {@code collidesWith(Vector2D)} which determines if a position is inside this {@code Collider} 
 * 
 * @see NaiveCollisionSystem
 * @see CollisionPair
 * @see CollisionHandler
 * @see Vector2D
 * @see BoxColldier
 * @see MultiCollider
 */

public interface Collider {
	

	/***
	 * determines if one of the {@code Vector}s of c2 is inside the boundary of this
	 * {@code Collider}
	 * 
	 * @param Collider c2
	 * @return true, when one vector of c2 is inside this Collider false, when not
	 */
	public default boolean collidesWith(Collider c) {
		
		if (this == c) {
			return true;
		}
		
		Vector2D[] vectors = c.getVectors();
		for(int i = 0;i<vectors.length;i++) {
			Vector2D v = vectors[i];
			if(v==null) {
				throw new NullPointerException("Vectors from Colliders cannot be null!");
			}
			if (this.collidesWith(v)) {
				return true;
			}
		}
		return false;
	}

	public abstract Vector2D[] getVectors();
	public abstract boolean collidesWith(Vector2D vector);
	public abstract void translate(Vector2D vector);
	public abstract Rectangle getEnclosingBounds();
	
	
	
	

}

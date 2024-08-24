package de.basti.game_framework.collision;

import de.basti.game_framework.collision.system.NaiveCollisionSystem;

/**
 * Handler which gets called by {@code GameCollisionSystem}, when a collision
 * happens. The {@code CollisionPair} has the two colliding colliders.
 * 
 * @param <T> type of {@code Collider} to use
 * 
 * @see NaiveCollisionSystem
 * @see CollisionPair
 * @see Collider
 * 
 */
public interface CollisionHandler<T extends Collider> {

	public void onCollision(CollisionPair<T> pair);

}

package de.basti.game_framework.core;

import de.basti.game_framework.collision.CollisionHandler;
import de.basti.game_framework.collision.CollisionPair;

public class EngineCollisionHandler<E extends Entity<?,?>>
		implements CollisionHandler<E> {

	@Override
	public void onCollision(CollisionPair<E> pair) {
		pair.getCollider1().onCollision(pair.getType(), pair.getCollider2());
		pair.getCollider2().onCollision(pair.getType(), pair.getCollider1());
	}

}

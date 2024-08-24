package de.basti.game_framework.collision.system;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import de.basti.game_framework.collision.Collider;
import de.basti.game_framework.collision.CollisionPair;
import de.basti.game_framework.core.Updatable;

/**
 * Class which checks collisions between multiple {@code Collider}s, which can
 * be added to it. On collision begin, ongoing collision or collision end it
 * sends a {@code CollisionPair} to the respective {@code CollisionHandler}. The
 * collisions are only checked when the update(double deltaMillis) is called.
 * 
 * @param <T> type of {@code Collider} to use
 */
public class NaiveCollisionSystem<T extends Collider> extends CollisionSystem<T>
		implements Updatable {
	

	private Set<T> colliders = new HashSet<>();
	
	public NaiveCollisionSystem() {
		super();

	}

	protected void updateSubstep() {
		Set<CollisionPair<T>> currentCollisions = ConcurrentHashMap.newKeySet(colliders.size() * colliders.size());
		for (T c1 : this.colliders) {
			if (c1 == null) {
				throw new NullPointerException("Collider cant be null!");
			}

			this.colliders.parallelStream().filter(c2->c1!=c2).filter(c2 -> c1.collidesWith(c2) || c2.collidesWith(c1))
					.forEach(c2 -> currentCollisions.add(new CollisionPair<T>(c1, c2)));
		}

		this.handleCollisions(currentCollisions);

		
		
	}
	
	public boolean add(T collider) {
		return this.colliders.add(collider);
	}
	
	public boolean remove(T collider) {
		return this.colliders.remove(collider);
	}
	
	public void clear() {
		this.colliders.clear();
	}
	
	
	

}

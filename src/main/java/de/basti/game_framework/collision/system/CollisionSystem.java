package de.basti.game_framework.collision.system;

import java.util.HashSet;
import java.util.Set;

import de.basti.game_framework.collision.Collider;
import de.basti.game_framework.collision.CollisionHandler;
import de.basti.game_framework.collision.CollisionPair;
import de.basti.game_framework.collision.CollisionType;
import de.basti.game_framework.core.Updatable;

public abstract class CollisionSystem<T extends Collider> implements Updatable {

	private int updateIterations = 1;

	private Set<CollisionHandler<T>> handlers = new HashSet<>();

	private Set<CollisionPair<T>> lastCollisions = new HashSet<>();

	public CollisionSystem() {
		super();
	}

	@Override
	public void update(long deltaMillis) {
		for (int i = 0; i < updateIterations; i++) {
			this.updateSubstep();
		}
	}

	protected abstract void updateSubstep();

	private Set<CollisionPair<T>> getCollisionsToHandle(Set<CollisionPair<T>> allCollisions) {

		Set<CollisionPair<T>> toHandle = new HashSet<>();

		for (CollisionPair<T> pair : allCollisions) {
			if (lastCollisions.contains(pair)) {
				pair.setType(CollisionType.ONGOING);
				lastCollisions.remove(pair);
			} else {
				pair.setType(CollisionType.BEGIN);
			}
			toHandle.add(pair);

		}
		for (CollisionPair<T> pair : lastCollisions) {
			pair.setType(CollisionType.END);
			toHandle.add(pair);
		}

		lastCollisions = allCollisions;
		return toHandle;

	}

	protected void handleCollisions(Set<CollisionPair<T>> currentCollisions) {
		Set<CollisionPair<T>> toHandle = this.getCollisionsToHandle(currentCollisions);

		for (var handler : this.handlers) {
			for (var pair : toHandle) {
				handler.onCollision(pair);
			}
		}
	}

	public void addHandler(CollisionHandler<T> handler) {
		this.handlers.add(handler);
	}

	public boolean removeHandler(CollisionHandler<T> handler) {
		return this.handlers.remove(handler);
	}
	
	public void clearHandlers() {
		this.handlers.clear();
	}

	public int getUpdateIterations() {
		return updateIterations;
	}

	public void setUpdateIterations(int updateIterations) {
		this.updateIterations = updateIterations;
	}

	public abstract void clear();

	public abstract boolean add(T collider);

	public abstract boolean remove(T collider);

}
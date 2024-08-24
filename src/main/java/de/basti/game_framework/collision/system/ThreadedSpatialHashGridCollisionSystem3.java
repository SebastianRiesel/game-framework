package de.basti.game_framework.collision.system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import de.basti.game_framework.collision.Collider;
import de.basti.game_framework.collision.CollisionPair;
import de.basti.game_framework.math.Rectangle;
import de.basti.game_framework.math.Vector2D;

public class ThreadedSpatialHashGridCollisionSystem3<T extends Collider> extends CollisionSystem<T> {

	private Set<T> allColliders = new HashSet<>();
	private SpatialHashGrid<T> grid;
	private double cellWidth;

	public ThreadedSpatialHashGridCollisionSystem3() {
		this(50, 50d);

	}

	public ThreadedSpatialHashGridCollisionSystem3(int capicity) {
		this(capicity, 50d);

	}

	public ThreadedSpatialHashGridCollisionSystem3(double cellWidth) {
		this(50, cellWidth);

	}

	public ThreadedSpatialHashGridCollisionSystem3(int capacity, double cellWidth) {
		this.grid = new SpatialHashGrid<>(capacity);
		this.cellWidth = cellWidth;
	}

	// private ThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(8);

	private ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	@Override
	protected void updateSubstep() {
		this.grid.clear();
		for (T collider : this.allColliders) {
			this.insert(collider);
		}

		Set<CollisionPair<T>> currentCollisions = new HashSet<CollisionPair<T>>();

		List<Future<Set<CollisionPair<T>>>> newCollisionFutures = new ArrayList<>();

		for (Set<T> colliders : this.grid.getAll()) {

			Future<Set<CollisionPair<T>>> future = exec.submit(new Callable<>() {

				@Override
				public Set<CollisionPair<T>> call() throws Exception {
					Set<CollisionPair<T>> newCollisionFutures = new HashSet<CollisionPair<T>>();
					for (T c1 : colliders) {
						if (c1 == null) {
							throw new NullPointerException("Collider cant be null!");
						}

						for (T c2 : colliders) {
							if (c1 == c2) {
								continue;
							}

							if (c1.collidesWith(c2) || c2.collidesWith(c1)) {
								newCollisionFutures.add(new CollisionPair<T>(c1, c2));
							}
						}
					}
					return newCollisionFutures;
				}

			});

			newCollisionFutures.add(future);

			/*
			 * colliders.parallelStream().filter(c2 -> c1 != c2) .filter(c2 ->
			 * c1.collidesWith(c2) || c2.collidesWith(c1)) .forEach(c2 ->
			 * currentCollisions.add(new CollisionPair<T>(c1, c2)));
			 */

		}

		for (Future<Set<CollisionPair<T>>> future : newCollisionFutures) {

			try {
				currentCollisions.addAll(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();

			}

		}

		this.handleCollisions(currentCollisions);

	}

	@Override
	public void clear() {
		this.allColliders.clear();
		this.grid.clear();
	}

	@Override
	public boolean add(T collider) {
		if (this.allColliders.add(collider)) {

			return true;
		}
		return false;
	}

	private void insert(T collider) {
		Rectangle r = collider.getEnclosingBounds();
		Vector2D pos = r.getPosition();
		int lowestX = indexOfPos(pos.getX() - r.getWidth() / 2);
		int lowestY = indexOfPos(pos.getY() - r.getHeight() / 2);
		int highestX = indexOfPos(pos.getX() + r.getWidth() / 2);
		int highestY = indexOfPos(pos.getY() + r.getHeight() / 2);

		for (int x = lowestX; x < highestX + 1; x++) {
			for (int y = lowestY; y < highestY + 1; y++) {
				this.insert(collider, x, y);
			}
		}
	}

	private int indexOfPos(double pos) {
		return (int) Math.floor(pos / cellWidth);
	}

	private void insert(T collider, int x, int y) {
		this.grid.get(x, y).add(collider);

	}

	@Override
	public boolean remove(T collider) {
		return this.allColliders.remove(collider);
	}

}

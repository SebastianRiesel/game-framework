package de.basti.game_framework.core;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.basti.game_framework.collision.system.CollisionSystem;
import de.basti.game_framework.collision.system.ThreadedSpatialHashGridCollisionSystem3;
import de.basti.game_framework.graphics.Drawable;
import de.basti.game_framework.graphics.GameGraphics;
import de.basti.game_framework.input.Input;
import de.basti.game_framework.math.Vector2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

/*TODO
 * Eine Klasse GameScene implementieren, 
 * die eine Kollektion aus Entities des Typs E, Drawables und Updatables besteht
 * 
 * Die Engine soll nach außen die Möglichkeit bieten, den derzeitigen GameContext zu ändern
 * Der Spieleentwickler kann so mehrere Scenen entwickeln, die gut von einander abgekapselt sind
 */
public class Engine<E extends Entity<?, ?>> {

	private Updater userUpdater = new Updater();

	private double width;
	private double height;

	private Scene scene;
	private GraphicsContext gc;

	private Set<E> entities = new HashSet<>();
	private CollisionSystem<E> collisionSystem = new ThreadedSpatialHashGridCollisionSystem3<E>();
	private GameGraphics drawing;
	private Loop loop = new Loop();
	private Input input;
	private E camera = null;

	private Updatable cameraUpdate = new Updatable() {

		@Override
		public void update(long deltaMillis) {
			// transform the camera so the player stays in the middle
			Vector2D transform;

			if (camera == null) {
				transform = new Vector2D(0, 0);
			} else {
				transform = camera.getPosition().clone();
			}

			transform.scale(-1);
			transform.translate(width / 2, height / 2);
			getDrawing().setTransform(transform);
			getInput().getMouseData().setTransform(transform);

		}
	};

	public static interface SizeChangeListener {
		public void onSizeChange(double width, double height);
	}

	private List<SizeChangeListener> sizeChangeListeners = new ArrayList<>();
	
	
	public Engine(Scene scene, GraphicsContext gc) {
		this(scene, gc, Input.newFXInput(scene));
	}
	
	public Engine(Scene scene, GraphicsContext gc, Input input) {
		this.scene = scene;
		this.gc = gc;
		this.drawing = new GameGraphics(gc);
		this.input = input;

		Updatable topLevelUpdate = new Updatable() {

			@Override
			public void update(long deltaMillis) {
				// collision
				// user
				// input
				// camera
				// drawing
				collisionSystem.update(deltaMillis);
				userUpdater.update(deltaMillis);
				input.update(deltaMillis);
				cameraUpdate.update(deltaMillis);
				drawing.update(deltaMillis);
			}
		};

		this.loop.getUpdater().add(topLevelUpdate);

		// collision handler
		this.collisionSystem.addHandler(new EngineCollisionHandler<E>());

		this.width = gc.getCanvas().getWidth();
		this.height = gc.getCanvas().getHeight();

		this.scene.widthProperty().addListener((observable, oldValue, newValue) -> {
			this.width = (double) newValue;
			fireSizeChange();
		});

		this.scene.heightProperty().addListener((observable, oldValue, newValue) -> {
			this.height = (double) newValue;
			fireSizeChange();
		});
	}

	private void fireSizeChange() {
		this.sizeChangeListeners.forEach(scl -> scl.onSizeChange(width, height));
	}

	public void addSizeChangeListener(SizeChangeListener scl) {
		this.sizeChangeListeners.add(scl);
	}

	public boolean removeSizeChangeListener(SizeChangeListener scl) {
		return this.sizeChangeListeners.remove(scl);
	}

	public void addEntity(int layer, E e) {
		this.addDrawableRelative(layer, e);
		this.collisionSystem.add(e);
		this.addUpdatable(e);
		this.entities.add(e);

	}

	public boolean removeEntity(E e) {
		this.removeDrawable(e);
		this.collisionSystem.remove(e);
		this.removeUpdatable(e);
		return this.entities.remove(e);
	}

	public void removeAllEntities() {
		for (E e : this.entities) {
			this.removeDrawable(e);
			this.collisionSystem.remove(e);
			this.removeUpdatable(e);
		}
		this.entities.clear();
	}

	public void removeAllColliders() {
		this.collisionSystem.clear();
	}

	public void removeAllDrawables() {
		this.drawing.removeAll();

	}

	public void removeAllUpdatables() {

		userUpdater.removeAll();
	}

	public void removeAllCollisionHandlers() {
		collisionSystem.clearHandlers();
	}

	public void removeAll() {
		this.removeAllEntities();
		this.removeAllColliders();
		this.removeAllDrawables();
		this.removeAllUpdatables();
	}

	public boolean removeUpdatable(Updatable u) {
		return userUpdater.remove(u);
	}

	public void addUpdatable(Updatable u) {
		userUpdater.add(u);
	}

	public boolean removeDrawable(Drawable d) {
		return this.drawing.remove(d);
	}

	public void addDrawableRelative(int layer, Drawable d) {
		this.drawing.addRelative(layer, d);
	}

	public void addDrawableAbsolute(Drawable d) {
		this.drawing.addAbsolute(d);
	}

	public void stickCameraTo(E e) {
		camera = e;
	}

	public void start() {
		this.loop.start();
	}

	public void stop() {
		this.loop.stop();
	}

	public GameGraphics getDrawing() {
		return drawing;
	}

	public Loop getLoop() {
		return loop;
	}

	public Input getInput() {
		return input;
	}
	
	

	public void setInput(Input input) {
		this.input = input;
	}

	public double getWidth() {
		return gc.getCanvas().getWidth();
	}

	public double getHeight() {
		return gc.getCanvas().getHeight();
	}

	public Set<E> getEntities() {
		return this.entities;
	}

}

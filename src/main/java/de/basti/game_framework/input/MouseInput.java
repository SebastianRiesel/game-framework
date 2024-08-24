package de.basti.game_framework.input;

import java.util.HashSet;
import java.util.Set;

import de.basti.game_framework.core.Updatable;
import de.basti.game_framework.math.Vector2D;
import javafx.scene.input.MouseButton;

public class MouseInput implements Updatable{

	protected Set<MouseButton> buttonsDown = new HashSet<>();
	protected Set<MouseButton> buttonsPressed = new HashSet<>();
	protected Set<MouseButton> buttonsReleased = new HashSet<>();
	protected Vector2D mousePosition = new Vector2D(0, 0);
	private Vector2D cameraTransform = new Vector2D(0, 0);

	public MouseInput() {
		super();
	}

	public boolean isDown(MouseButton b) {
		return this.buttonsDown.contains(b);
	}

	public boolean isPressed(MouseButton b) {
		return this.buttonsPressed.contains(b);
	}

	public boolean isReleased(MouseButton b) {
		return this.buttonsReleased.contains(b);
	}

	/**
	 * @return relative position as {@code Vector2D} considering the cameraTransform
	 */
	public Vector2D getRelativeMousePosition() {
		return this.mousePosition.translated(this.cameraTransform.scaled(-1));
	}

	/**
	 * @return absolute position as {@code Vector2D} without considering the
	 *         cameraTransform
	 */
	public Vector2D getAbsoluteMousePosition() {
		return this.mousePosition.clone();
	}

	public Set<MouseButton> getButtonsDown() {
		return buttonsDown;
	}

	public Set<MouseButton> getButtonsPressed() {
		return buttonsPressed;
	}

	public Set<MouseButton> getButtonsReleased() {
		return buttonsReleased;
	}

	public void update(long deltaMillis) {
		this.buttonsPressed = new HashSet<>();
		this.buttonsReleased = new HashSet<>();
	}

	public Vector2D getTransform() {
		return cameraTransform;
	}

	public void translateTransform(Vector2D translation) {
		this.cameraTransform.translate(translation);
	
	}

	public void setTransform(Vector2D cameraTransformt) {
		this.cameraTransform = cameraTransform;
	
	}

}
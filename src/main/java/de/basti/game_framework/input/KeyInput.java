package de.basti.game_framework.input;

import java.util.HashSet;
import java.util.Set;

import de.basti.game_framework.core.Updatable;
import javafx.scene.input.KeyCode;

public class KeyInput implements Updatable{

	protected Set<KeyCode> keysDown = new HashSet<>();
	protected Set<KeyCode> keysPressed = new HashSet<>();
	protected Set<KeyCode> keysReleased = new HashSet<>();

	public KeyInput() {
		super();
	}

	public boolean isDown(KeyCode k) {
		return this.keysDown.contains(k);
	}

	public boolean isPressed(KeyCode k) {
		return this.keysPressed.contains(k);
	}

	public boolean isReleased(KeyCode k) {
		return this.keysReleased.contains(k);
	}

	public Set<KeyCode> getKeysDown() {
		return keysDown;
	}

	public Set<KeyCode> getKeysPressed() {
		return keysPressed;
	}

	public Set<KeyCode> getKeysReleased() {
		return keysReleased;
	}

	@Override
	public void update(long deltaMillis) {
		this.keysPressed = new HashSet<>();
		this.keysReleased = new HashSet<>();
	
	}

}
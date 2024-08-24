package de.basti.game_framework.input;

import de.basti.game_framework.core.Updatable;
import javafx.scene.Scene;

public class Input implements Updatable{
	private KeyInput keyData;
	private MouseInput mouseData;
	
	public Input(KeyInput keyData, MouseInput mouseData) {
		super();
		this.keyData = keyData;
		this.mouseData = mouseData;
	}
	
	public static Input newFXInput(Scene scene) {
		FXMouseInput mouse = new FXMouseInput(scene);
		FXKeyInput key = new FXKeyInput(scene);
		mouse.listenTo(scene);
		key.listenTo(scene);
		return new Input(key, mouse);
	}

	public KeyInput getKeyData() {
		return keyData;
	}

	public MouseInput getMouseData() {
		return mouseData;
	}

	
	public void update(long deltaMillis) {
		this.keyData.update(deltaMillis);
		this.mouseData.update(deltaMillis);
	}
	
	
}

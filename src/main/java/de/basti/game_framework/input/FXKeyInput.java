package de.basti.game_framework.input;

import de.basti.game_framework.core.Updatable;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class FXKeyInput extends KeyInput {
	private Scene currentScene = new Scene(new Pane());// Scene is placeholder, so no null-checks are required

	public FXKeyInput(Scene currentScene) {
		super();
		this.listenTo(currentScene);
		
	}

	private EventHandler<KeyEvent> onKeyPressed = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent event) {
			KeyCode k = event.getCode();
			if (keysDown.add(k)) {
				keysPressed.add(k);
			}
		}

	};

	private EventHandler<KeyEvent> onKeyReleased = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent event) {
			KeyCode k = event.getCode();
			keysDown.remove(k);
			keysReleased.add(k);

		}

	};

	public void listenTo(Scene newScene) {
		currentScene.removeEventHandler(KeyEvent.KEY_PRESSED, onKeyPressed);
		currentScene.removeEventHandler(KeyEvent.KEY_RELEASED, onKeyReleased);
		currentScene = newScene;
		currentScene.addEventHandler(KeyEvent.KEY_PRESSED, onKeyPressed);
		currentScene.addEventHandler(KeyEvent.KEY_RELEASED, onKeyReleased);
	}
}

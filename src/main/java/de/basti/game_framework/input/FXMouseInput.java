package de.basti.game_framework.input;

import de.basti.game_framework.core.Updatable;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class FXMouseInput extends MouseInput implements Updatable {
	private Scene currentScene = new Scene(new Pane());// Scene is placeholder, so no null-checks are required

	public FXMouseInput(Scene currentScene) {
		super();
		this.listenTo(currentScene);
		
	}

	private EventHandler<MouseEvent> onMousePressed = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			MouseButton b = event.getButton();
			buttonsDown.add(b);
			buttonsPressed.add(b);
		}

	};

	private EventHandler<MouseEvent> onMouseReleased = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {

			MouseButton b = event.getButton();
			buttonsDown.remove(b);
			buttonsReleased.add(b);

		}

	};

	private EventHandler<MouseEvent> onMouseMoved = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {

			mousePosition.setX(event.getX());
			mousePosition.setY(event.getY());

		}

	};

	public void listenTo(Scene newScene) {
		currentScene.removeEventHandler(MouseEvent.MOUSE_PRESSED, onMousePressed);
		currentScene.removeEventHandler(MouseEvent.MOUSE_RELEASED, onMouseReleased);
		currentScene.removeEventHandler(MouseEvent.MOUSE_RELEASED, onMouseMoved);
		currentScene.removeEventHandler(MouseEvent.MOUSE_DRAGGED, onMouseMoved);

		currentScene = newScene;
		currentScene.addEventHandler(MouseEvent.MOUSE_PRESSED, onMousePressed);
		currentScene.addEventHandler(MouseEvent.MOUSE_RELEASED, onMouseReleased);
		currentScene.addEventHandler(MouseEvent.MOUSE_MOVED, onMouseMoved);
		currentScene.addEventHandler(MouseEvent.MOUSE_DRAGGED, onMouseMoved);
	}

}

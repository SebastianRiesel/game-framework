package de.basti.game_framework.input;

import java.util.ArrayList;
import java.util.List;

import de.basti.game_framework.core.Updatable;
import javafx.scene.input.KeyCode;

public class CustomKeyInput extends KeyInput {
	
	private static class TimedKeyPress implements Updatable{
		public long remainingDuration;
		public KeyCode key;
		
		public TimedKeyPress(long duration, KeyCode key) {
			super();
			this.remainingDuration = duration;
			this.key = key;
		}
		
		@Override
		public void update(long deltaMillis) {
			this.remainingDuration-=deltaMillis;
		}
		
		
	}
	
	private List<TimedKeyPress> timedPresses = new ArrayList<>();
	
	
	public void press(KeyCode key) {
		keysPressed.add(key);
		keysDown.add(key);
		
	}
	
	public void release(KeyCode key) {
		keysReleased.add(key);
		keysDown.remove(key);

	}
	
	public void keyDownForDuration(long duration, KeyCode key) {
		this.press(key);
		this.timedPresses.add(new TimedKeyPress(duration, key));
	}
	
	
	
	@Override
	public void update(long deltaMillis) {
		super.update(deltaMillis);
		for(int i = 0; i<timedPresses.size();i++) {
			TimedKeyPress press = timedPresses.get(i);
			press.update(deltaMillis);
			if(press.remainingDuration<=0) {
				this.release(press.key);
				timedPresses.remove(i);
			}
		}
	}
}

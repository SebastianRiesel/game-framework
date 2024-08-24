package de.basti.game_framework.graphics.animation;

import java.util.Arrays;

import de.basti.game_framework.core.Updatable;
import de.basti.game_framework.graphics.Drawable;
import de.basti.game_framework.math.Vector2D;
import javafx.scene.canvas.GraphicsContext;

public class AnimatedSprite implements Updatable, Drawable {
	
	private AnimationFrame[] frames;
	private Vector2D position;

	private double width;
	private double height;
	private double rotation;

	private int timer = 0;
	private int frameIndex = 0;
	private boolean spriteChanged;

	public AnimatedSprite(Vector2D position, AnimationFrame... frames) {
		this.position = position;
		this.frames = frames;
		this.width = frames[0].getSprite().getWidth();
		this.height = frames[0].getSprite().getHeight();
		this.rotation = frames[0].getSprite().getRotation();

		Arrays.stream(frames).forEach(frame -> {
			frame.getSprite().setWidth(width);
			frame.getSprite().setHeight(height);
			frame.getSprite().setRotation(rotation);
			frame.getSprite().setPosition(position.clone());
		});
	}

	@Override
	public void update(long deltaMillis) {
		this.timer += deltaMillis;
		if (timer > this.frames[frameIndex].getFrameTime()) {
			nextFrame();
		}

	}

	private void nextFrame() {
		this.timer -= this.frames[frameIndex].getFrameTime();
		this.frameIndex++;
		
		if(frameIndex==this.frames.length)this.frameIndex=0;
		
		updateSpriteValues();

	}

	private void updateSpriteValues() {
		AnimationFrame frame = this.frames[frameIndex];
		frame.getSprite().setRotation(rotation);
		frame.getSprite().setWidth(width);
		frame.getSprite().setHeight(height);
		this.spriteChanged = false;
	}

	@Override
	public void draw(GraphicsContext gc) {

		if (this.spriteChanged) {
			updateSpriteValues();
		}
		this.frames[frameIndex].getSprite().draw(gc);
	}
	
	@Override
	public void translate(Vector2D vector) {
		this.position.translate(vector);
		Arrays.stream(frames).forEach(frame -> {
			frame.getSprite().translate(vector);
		});

	}

	public void setPosition(Vector2D position) {
		Vector2D translation = this.position.clone();
		translation.scale(-1);
		translation.translate(position);
		this.translate(translation);
	}

	public double getWidth() {
		return width;

	}

	public void setWidth(double width) {
		this.width = width;
		Arrays.stream(frames).forEach(frame -> {
			frame.getSprite().setWidth(width);
		});
		this.spriteChanged = true;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
		Arrays.stream(frames).forEach(frame -> {
			frame.getSprite().setHeight(height);
		});
		this.spriteChanged = true;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
		Arrays.stream(frames).forEach(frame -> {
			frame.getSprite().setRotation(rotation);
		});
		this.spriteChanged = true;
	}

}

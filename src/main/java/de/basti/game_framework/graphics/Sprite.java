package de.basti.game_framework.graphics;


import de.basti.game_framework.math.Vector2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Implementation of {@code Drawable} which represents a picture. The picture to
 * be drawn must be given as a {@code import javafx.scene.image.Image}.
 * 
 * @see Drawable
 * @see javafx.scene.image.Image
 */
public class Sprite implements Drawable {
	
	private Vector2D position;

	private ImageView imageView;
	private Image image;

	private SnapshotParameters params = new SnapshotParameters();

	private boolean spriteChanged = false;

	public Sprite(Vector2D position, Image image) {
		super();
		this.position = position;
		this.imageView = new ImageView(image);
		this.image = image;
		imageView.setSmooth(false);

		this.imageView.setFitWidth(image.getWidth());
		this.imageView.setFitHeight(image.getHeight());

		params.setFill(Color.TRANSPARENT);

	}

	@Override
	public void draw(GraphicsContext gc) {
		if (spriteChanged) {
			this.updateImage();
			spriteChanged = false;
		}
		gc.drawImage(image, position.getX() - image.getWidth() / 2, position.getY() - image.getHeight() / 2);

	}


	@Override
	public void translate(Vector2D vector) {
		this.position.translate(vector);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.imageView = new ImageView(image);
		this.spriteChanged = true;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public double getWidth() {
		return imageView.getFitWidth();

	}

	public void setWidth(double width) {
		imageView.setFitWidth(width);
		this.spriteChanged = true;
	}

	public double getHeight() {
		return imageView.getFitHeight();
	}

	public void setHeight(double height) {
		imageView.setFitHeight(height);
		this.spriteChanged = true;
	}

	public double getRotation() {
		return imageView.getRotate();
	}

	public void setRotation(double rotation) {
		imageView.setRotate(rotation);
		this.spriteChanged = true;
	}

	private void updateImage() {
		this.image = imageView.snapshot(params, null);
	}

}

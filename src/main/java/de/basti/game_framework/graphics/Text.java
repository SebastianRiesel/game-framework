package de.basti.game_framework.graphics;

import de.basti.game_framework.math.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Text extends Shape {

	private String text = "";

	private Font font = Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 12);

	private Vector2D position;

	public Text(Vector2D pos, String text) {
		this.position = pos;
		this.text = text;

	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setStroke(strokeColor);
		gc.setFill(fillColor);
		gc.setLineWidth(lineWidth);
		gc.setFont(font);

		gc.fillText(text, position.getX(), position.getY() + this.font.getSize() / 2);
		gc.strokeText(text, position.getX(), position.getY() + this.font.getSize() / 2);

	}

	@Override
	public void translate(Vector2D vector) {
		this.position.translate(vector);

	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

}

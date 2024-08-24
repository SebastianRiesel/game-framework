package de.basti.game_framework.math;

public class Rectangle {
	private Vector2D position;// center point
	private double width;
	private double height;

	public Rectangle(Vector2D position, double width, double height) {
		super();
		this.position = position;
		this.width = width;
		this.height = height;
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double area() {
		return width / height;
	}

	public Vector2D getTopLeft() {
		return this.position.translated(-this.width / 2, -this.height / 2);
	}

	public Vector2D getTopRight() {
		return this.position.translated(this.width / 2, -this.height / 2);
	}

	public Vector2D getBottomRight() {
		return this.position.translated(this.width / 2, this.height / 2);
	}

	public Vector2D getBottomLeft() {
		return this.position.translated(-this.width / 2, this.height / 2);
	}

	public void translate(Vector2D vector) {
		this.position.translate(vector);

	}
	
	public Rectangle clone() {
		return new Rectangle(this.getPosition().clone(), this.width, this.height);
	}

}

package de.basti.gameEngine.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.basti.game_framework.math.Vector2D;

public class Vector2DTranslateTest {
	private Vector2D vector1;
	private Vector2D vector2;
	private Vector2D vector3;
	

	
	@BeforeEach
	public void setup() {
		vector1 = new Vector2D(0,0);
		vector2 = new Vector2D(0,0);
		vector3 = new Vector2D(0,0);
	}
	
	
	@Test
	public void simpleTranslateTest() {
		vector1.setX(2);
		vector1.setY(3);
		vector2.setX(4);
		vector2.setY(-2);
		vector3.setX(6);
		vector3.setY(1);
		
		vector1.translate(vector2);
		assertEquals(vector1, vector3);
	}
	
	
	@Test
	public void bothWaysTranslateTest() {
		vector1.setX(2);
		vector1.setY(3);
		vector2.setX(4);
		vector2.setY(6);
		vector3.setX(4);
		vector3.setY(6);

		vector3.translate(vector1);
		vector1.translate(vector2);
		
		assertEquals(vector1, vector3);
	}
}

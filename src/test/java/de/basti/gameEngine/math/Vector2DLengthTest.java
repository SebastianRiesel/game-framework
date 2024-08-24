package de.basti.gameEngine.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.basti.game_framework.math.Vector2D;

public class Vector2DLengthTest {
	private Vector2D vector;
	
	@BeforeEach
	public void setup() {
		vector = new Vector2D(0,0);
	}
	
	
	@Test
	public void simpleLengthTest() {
		vector.setX(1);
		vector.setY(1);
		assertEquals(Math.sqrt(2), vector.length());
	}
	
	@Test 
	public void lengthIsXIfYZero() {
		vector.setX(123.213);
		vector.setY(0);
		assertEquals(123.213, vector.length());
	}
	
	@Test 
	public void lengthIsYIfXZero() {
		vector.setX(0);
		vector.setY(698423.0767);
		assertEquals(698423.0767, vector.length());
	}
	
	@Test
	public void lengthIsZeroIfXZeroAndYZero() {
		vector.setX(0);
		vector.setY(0);
		assertEquals(0, vector.length());
	}
	
	@Test
	public void lengthIsOneIfNormal() {
		vector.setX(9151234.1452);
		vector.setY(-12313.3123);
		vector.normalize();
		assertEquals(1, vector.length());
	}
	
	@Test
	public void lengthIsOneIfNormal2() {
		vector.setX(0.6);
		vector.setY(0.8);
		assertEquals(1, vector.length());
	}
	
	
	
	
	
}

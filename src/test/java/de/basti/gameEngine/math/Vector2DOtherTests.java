package de.basti.gameEngine.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.basti.game_framework.math.Vector2D;

public class Vector2DOtherTests {
	private Vector2D vector1;
	private Vector2D vector2;
	

	
	@BeforeEach
	public void setup() {
		vector1 = new Vector2D(0,0);
		vector2 = new Vector2D(0,0);
	}
	
	
	@Test
	public void cloneEqualsTest() {
		vector1.setX(6);
		vector1.setY(2);
		vector2 = vector1.clone();
		
		assertEquals(vector1, vector2);
		
	}
	
	@Test
	public void cloneNotSameObjectTest() {
		vector1.setX(6);
		vector1.setY(2);
		vector2 = vector1.clone();
		
		assertFalse(vector1 == vector2);
		
	}
	
	@Test
	public void simpleScaleTest() {
		vector1.setX(6);
		vector1.setY(2);
		vector2.setX(9);
		vector2.setY(3);
		vector1.scale(1.5);
		
		
		assertEquals(vector1,vector2);
		
	}
	
	@Test
	public void scaleByZeroTest() {
		vector1.setX(6);
		vector1.setY(2);
		vector2.setX(0);
		vector2.setY(0);
		vector1.scale(0);
		
		
		assertEquals(vector1,vector2);
		
	}
	
	@Test
	public void scaleByNegativeTest() {
		vector1.setX(6);
		vector1.setY(2);
		vector2.setX(-12);
		vector2.setY(-4);
		vector1.scale(-2);

		assertEquals(vector1,vector2);
		
	}
	
	
	
}

package de.basti.gameEngine.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.basti.game_framework.math.Vector2D;

public class Vector2DEqualsTest {
	private Vector2D vector1;
	private Vector2D vector2;

	
	@BeforeEach
	public void setup() {
		vector1 = new Vector2D(0,0);
		vector2 = new Vector2D(0,0);
		
	}
	
	@Test
	public void sameAdressEqualsTest() {
		vector1.setX(13213132);
		assertTrue(vector1.equals(vector1));
	}
	
	@Test
	public void sameValueEqualsTest() {
		vector1.setX(2);
		vector1.setY(124);
		vector2.setX(2);
		vector2.setY(124);
		assertTrue(vector1.equals(vector2));
	}
	
	@Test
	public void nullEqualsTest() {
		assertFalse(vector1.equals(null));
	}
	
	@Test
	public void differentClassEqualsTest() {
		assertFalse(vector1.equals(new Object()));
	}
	
	@Test
	public void differentXEqualsTest() {
		vector1.setX(2);
		vector1.setY(124);
		vector2.setX(3);
		vector2.setY(124);
		assertFalse(vector1.equals(vector2));
	}
	
	@Test
	public void differentYEqualsTest() {
		vector1.setX(2);
		vector1.setY(124);
		vector2.setX(2);
		vector2.setY(414);
		assertFalse(vector1.equals(vector2));
	}
	
	
	
	
}

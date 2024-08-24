package de.basti.gameEngine.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.basti.game_framework.math.Vector2D;

public class Vector2DNormalizeTest {
	
	private Vector2D vector1;
	private Vector2D vector2;
	
	
	@BeforeEach
	public void setup() {
		vector1 = new Vector2D(0,0);
		vector2 = new Vector2D(0,0);
		
	}
	
	@Test
	public void vectorIsNormalAfterNormalizeTest() {
		vector1.setX(3);
		vector1.setY(4);
		vector1.normalize();
		
		vector2.setX(0.6);
		vector2.setY(0.8);
		
		
		assertEquals(vector2, vector1);
	}
	
	@Test
	public void vectorIsNormalAfterNormalizeTest2() {
		vector1.setX(6);
		vector1.setY(8);
		vector1.normalize();
		
		vector2.setX(0.6);
		vector2.setY(0.8);
		
		
		assertEquals(vector2, vector1);
	}
	
	@Test
	public void lengthIsOneNormalizeTest() {
		vector1.setX(2);
		vector1.setY(5);
		vector1.normalize();
		
		
		
		
		assertEquals(1, vector1.length());
	}
	
	@Test
	public void lengthIsOneNormalizeTest2() {
		vector1.setX(123213.3);
		vector1.setY(-5123213.2);
		vector1.normalize();
		
		
		
		
		assertEquals(1, vector1.length());
	}
	
	
	@Test
	public void normalVectorStaysNormalNormalizeTest() {
		vector1.setX(0.6);
		vector1.setY(0.8);
		vector1.normalize();
		
		vector2.setX(0.6);
		vector2.setY(0.8);
		
		
		assertEquals(vector2, vector1);
	}
	
	@Test
	public void XzeroXZeroNormalizeTest() {
		vector1.setX(0);
		vector1.setY(4);
		vector1.normalize();	
		assertEquals(0,vector1.getX());
	}
	
	@Test
	public void YzeroYZeroNormalizeTest() {
		vector1.setX(4);
		vector1.setY(0);
		vector1.normalize();	
		assertEquals(0,vector1.getY());
	}
	
	@Test
	public void XzeroYOneNormalizeTest() {
		vector1.setX(0);
		vector1.setY(4);
		vector1.normalize();	
		assertEquals(1,vector1.getY());
	}
	
	@Test
	public void YzeroXOneNormalizeTest() {
		vector1.setX(4);
		vector1.setY(0);
		vector1.normalize();	
		assertEquals(1,vector1.getX());
	}
	
	@Test
	public void zeroVectorStaysZeroNormalizeTest() {
		vector1.setX(0);
		vector1.setY(0);
		vector1.normalize();
		vector2.setX(0);
		vector2.setY(0);
		assertEquals(vector2, vector1);
		
	}
	
	
}

package de.basti.gameEngine.physics;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import de.basti.game_framework.collision.BoxCollider;
import de.basti.game_framework.math.Vector2D;

public class BoxColliderTest {
	private BoxCollider collider1;
	
	
	@BeforeEach
	public void setup() {
		collider1 = new BoxCollider(new Vector2D(5,5), 10, 10);
	}
	
	@ParameterizedTest
	@MethodSource
	public void vectorCollisionTest(int x,int y,boolean expected) {
		assertEquals(expected,collider1.collidesWith(new Vector2D(x,y)),x+"|"+y);
	}
	
	public static Stream<Arguments> vectorCollisionTest(){
		return Stream.of(
				Arguments.of(5,5,true),
				Arguments.of(1,9,true),
				Arguments.of(2,3,true),
				Arguments.of(0,0,true),
				Arguments.of(0,10,true),
				Arguments.of(10,0,true),
				Arguments.of(10,10,true),
				Arguments.of(0,3,true),
				Arguments.of(2,10,true),
				Arguments.of(10,8,true),
				Arguments.of(7,0,true),
				Arguments.of(11,11,false),
				Arguments.of(11,-1,false),
				Arguments.of(-1,11,false),
				Arguments.of(-1,-1,false),
				Arguments.of(-1,6,false),
				Arguments.of(11,3,false),
				Arguments.of(2,-1,false),
				Arguments.of(5,11,false)
				);
	}
	
}

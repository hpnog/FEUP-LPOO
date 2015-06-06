package jumpyjay.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jumpyjay.handlers.SingletonVandC;
import jumpyjay.logic.entities.Robot;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class RobotTest {

	@Test
	public void test() {
		SingletonVandC singleton = SingletonVandC.getSingleton();
		SingletonVandC.testing = true;

		World testWorld = new World(new Vector2(0, -9.8f), false);
		
		float xIni = 100;			//IRRELEVANT
		float yIni = 100;			//IRRELEVANT
		float tileSize = 16;		//IRRELEVANT
		
		//create platform-------------------------------------------------------------------------------------
		BodyDef robotBody = new BodyDef();
		robotBody.position.set((xIni * tileSize) / singleton.PPM, (yIni * tileSize) / singleton.PPM);
		robotBody.type = BodyType.DynamicBody;
		Body robotB = testWorld.createBody(robotBody);

		//create robot
		Robot testRobot = new Robot(robotB, xIni, yIni, tileSize, testWorld);
		
		assertEquals(testRobot.getHp(), 3);
		assertTrue(testRobot.getPosition().x == xIni * tileSize / singleton.PPM);
		assertTrue(testRobot.getPosition().y == yIni * tileSize / singleton.PPM);
		assertTrue(testRobot.getX() == xIni * tileSize);
		assertTrue(testRobot.getY() == yIni * tileSize);
		assertTrue(!testRobot.checkIfOutOfBounds(singleton.SCREEN_WIDTH, singleton.SCREEN_HEIGHT));
		
		testWorld.dispose();
		testRobot.dispose();
	}

}

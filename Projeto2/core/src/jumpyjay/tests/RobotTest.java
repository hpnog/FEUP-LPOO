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

/**
 * The Class RobotTest.
 */
public class RobotTest {

	/**
	 * Robot initial test.
	 */
	@Test(timeout=5000)
	public void robotInitialTest() {
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
		assertTrue(testRobot.getBody().getPosition().y == yIni * tileSize / singleton.PPM);
		assertTrue(testRobot.getX() == xIni * tileSize);
		assertTrue(testRobot.getY() == yIni * tileSize);

		testWorld.dispose();
		testRobot.dispose();
	}

	/**
	 * Robot falling test.
	 */
	@Test(timeout=5000)
	public void robotFallingTest() {
		SingletonVandC singleton = SingletonVandC.getSingleton();
		SingletonVandC.testing = true;

		World testWorld = new World(new Vector2(0, -9.8f), false);

		float xIni = 100;			//IRRELEVANT
		float yIni = 100;			//IRRELEVANT
		float tileSize = 16;		//IRRELEVANT
		float testingWidth = 100;	//IRRELEVANT
		float testingHeight = Float.MAX_VALUE;
		
		
		//create platform-------------------------------------------------------------------------------------
		BodyDef robotBody = new BodyDef();
		robotBody.position.set((xIni * tileSize) / singleton.PPM, (yIni * tileSize) / singleton.PPM);
		robotBody.type = BodyType.DynamicBody;
		Body robotB = testWorld.createBody(robotBody);
		
		//create robot
		Robot testRobot = new Robot(robotB, xIni, yIni, tileSize, testWorld);

		float oldY = testRobot.getY();
		float dt = 1; //IRRELEVANT AS SOON AS IT IS > 0
		
		SingletonVandC.paused = 1;
		
		for (int i = 0; i < 50; i++)
		{
			testWorld.step(dt, 2, 6);
			testRobot.update(dt, testWorld,	(testingWidth * tileSize / singleton.PPM), testingHeight * tileSize / singleton.PPM, new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
			
			assertTrue(oldY > testRobot.getY());
			oldY = testRobot.getY();
		}

		testWorld.dispose();
		testRobot.dispose();
	}

	/**
	 * Robot dies from falling test.
	 */
	@Test(timeout=5000)
	public void robotDiesFromFallingTest() {
		SingletonVandC singleton = SingletonVandC.getSingleton();
		SingletonVandC.testing = true;

		World testWorld = new World(new Vector2(0, -9.8f), false);

		float xIni = 100;			//IRRELEVANT
		float yIni = 100;			//IRRELEVANT
		float tileSize = 16;		//IRRELEVANT
		float testingWidth = 100;	//IRRELEVANT
		float testingHeight = 100;
		
		
		//create platform-------------------------------------------------------------------------------------
		BodyDef robotBody = new BodyDef();
		robotBody.position.set((xIni * tileSize) / singleton.PPM, (yIni * tileSize) / singleton.PPM);
		robotBody.type = BodyType.DynamicBody;
		Body robotB = testWorld.createBody(robotBody);
		
		//create robot
		Robot testRobot = new Robot(robotB, xIni, yIni, tileSize, testWorld);

		float dt = 1; //IRRELEVANT AS SOON AS IT IS > 0
		
		SingletonVandC.paused = 1;
		
		boolean dead = false;
		for (int i = 0; i < 500; i++)
		{
			testWorld.step(dt, 2, 6);
			dead = testRobot.update(dt, testWorld,	(testingWidth * tileSize / singleton.PPM), testingHeight * tileSize / singleton.PPM, new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
			if (dead)
				break;
		}
		assertTrue(dead);

		testWorld.dispose();
		testRobot.dispose();
	}

	/**
	 * Robot dies from loosing health.
	 */
	@Test(timeout=5000)
	public void robotDiesFromLoosingHealth() {
		SingletonVandC singleton = SingletonVandC.getSingleton();
		SingletonVandC.testing = true;
		SingletonVandC.sound = false;
		SingletonVandC.music = false;
		
		World testWorld = new World(new Vector2(0, 0), false);		//IMPORTANT NOT TO HAVE GRAVITY SO THAT HE ONLY DIES OUT OF LOSS OF LIFE

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

		float dt = 0; //IRRELEVANT AS SOON AS IT IS > 0
		
		SingletonVandC.paused = 1;
		
		for (int i = 0; i < 500; i++)
		{
			testWorld.step(dt, 2, 6);
			testRobot.update(dt, testWorld,	Float.MAX_VALUE, Float.MAX_VALUE, new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
			singleton.loseLife++;
		}
				
		testWorld.dispose();
		testRobot.dispose();
	}
}

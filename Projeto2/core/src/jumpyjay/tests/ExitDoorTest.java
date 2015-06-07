package jumpyjay.tests;

import static org.junit.Assert.*;
import jumpyjay.handlers.SingletonVandC;
import jumpyjay.logic.entities.ExitDoor;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * The Class ExitDoorTest.
 */
public class ExitDoorTest {

	/**
	 * Exit door initial test.
	 */
	@Test(timeout=5000)
	public void ExitDoorInitialTest() {
		SingletonVandC singleton = SingletonVandC.getSingleton();
		SingletonVandC.testing = true;

		World testWorld = new World(new Vector2(0, -9.81f), true);

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		bdef.type = BodyType.StaticBody;

		float x = 0; //IRRELEVANT
		float y = 0; //IRRELEVANT

		bdef.position.set(x, y);

		PolygonShape kShape = new PolygonShape();
		kShape.setAsBox(32 / singleton.PPM / 2, 48 / singleton.PPM / 2);

		fdef.shape = kShape;
		fdef.isSensor = true;
		fdef.filter.categoryBits = singleton.BIT_DOOR;
		fdef.filter.maskBits = singleton.BIT_ROBOT;

		Body body = testWorld.createBody(bdef);
		Fixture fix = body.createFixture(fdef);
		fix.setUserData("door");

		int nKeys = 5; //Number of keys to catch
		
		ExitDoor exitDoor = new ExitDoor(body, x, y, 32, 48, nKeys);
		
		assertEquals(exitDoor.getKeysToCatch(), nKeys);

		testWorld.dispose();
		exitDoor.dispose();
	}

	/**
	 * Exit door catching keys test.
	 */
	@Test(timeout=5000)
	public void ExitDoorCatchingKeysTest() {
		SingletonVandC singleton = SingletonVandC.getSingleton();
		SingletonVandC.testing = true;

		World testWorld = new World(new Vector2(0, -9.81f), true);

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		bdef.type = BodyType.StaticBody;

		float x = 0; //IRRELEVANT
		float y = 0; //IRRELEVANT

		bdef.position.set(x, y);

		PolygonShape kShape = new PolygonShape();
		kShape.setAsBox(32 / singleton.PPM / 2, 48 / singleton.PPM / 2);

		fdef.shape = kShape;
		fdef.isSensor = true;
		fdef.filter.categoryBits = singleton.BIT_DOOR;
		fdef.filter.maskBits = singleton.BIT_ROBOT;

		Body body = testWorld.createBody(bdef);
		Fixture fix = body.createFixture(fdef);
		fix.setUserData("door");

		int nKeys = 50; //Number of keys to catch
		
		ExitDoor exitDoor = new ExitDoor(body, x, y, 32, 48, nKeys);

		for(int i = 0; i < nKeys; i++)
		{
			assertEquals(exitDoor.getKeysToCatch(), nKeys - i);
			exitDoor.keyCaught();
		}
		
		assertEquals(exitDoor.getKeysToCatch(), 0);
		
		testWorld.dispose();
		exitDoor.dispose();
	}

}

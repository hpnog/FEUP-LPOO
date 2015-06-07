package jumpyjay.tests;

import static org.junit.Assert.*;
import jumpyjay.handlers.SingletonVandC;
import jumpyjay.logic.entities.Key;

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
 * The Class KeyTest.
 */
public class KeyTest {

	/**
	 * Key initial test.
	 */
	@Test(timeout=5000)
	public void KeyInitialTest() {
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
		kShape.setAsBox(8 / singleton.PPM, 8 / singleton.PPM);

		fdef.shape = kShape;
		fdef.isSensor = true;
		fdef.filter.categoryBits = singleton.BIT_KEY;
		fdef.filter.maskBits = singleton.BIT_ROBOT;

		Body body = testWorld.createBody(bdef);
		Fixture fix = body.createFixture(fdef);
		fix.setUserData("key");

		Key k = new Key(body, fix, x, y, 13, 12);
		
		k.update(0);
		k.checkifCaught();

		assertTrue(!k.isCaught());
		
		testWorld.dispose();
		k.dispose();
	}
	
	/**
	 * Key caught test.
	 */
	@Test(timeout=5000)
	public void KeyCaughtTest() {
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
		kShape.setAsBox(8 / singleton.PPM, 8 / singleton.PPM);

		fdef.shape = kShape;
		fdef.isSensor = true;
		fdef.filter.categoryBits = singleton.BIT_KEY;
		fdef.filter.maskBits = singleton.BIT_ROBOT;

		Body body = testWorld.createBody(bdef);
		Fixture fix = body.createFixture(fdef);
		fix.setUserData("key");

		Key k = new Key(body, fix, x, y, 13, 12);
		
		fix.setUserData("caughtKey");
		
		k.update(0, true);
		k.checkifCaught();
		
		assertTrue(k.isCaught());
		
		testWorld.dispose();
		k.dispose();
	}

}

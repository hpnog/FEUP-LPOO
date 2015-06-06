package jumpyjay.tests;

import static org.junit.Assert.*;
import jumpyjay.handlers.SingletonVandC;
import jumpyjay.logic.entities.Diamond;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class DiamondTest {

	@Test
	public void DiamondInitialTest() {
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
		fdef.filter.categoryBits = singleton.BIT_DIAMOND;
		fdef.filter.maskBits = singleton.BIT_ROBOT;

		Body body = testWorld.createBody(bdef);
		Fixture fix = body.createFixture(fdef);
		fix.setUserData("diamond");

		Diamond k = new Diamond(body, fix, x, y, 14, 16);

		assertTrue(!k.isCaught());
		
		testWorld.dispose();
		k.dispose();
	}
	
	@Test
	public void DiamondCaughtTest() {
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
		fdef.filter.categoryBits = singleton.BIT_DIAMOND;
		fdef.filter.maskBits = singleton.BIT_ROBOT;

		Body body = testWorld.createBody(bdef);
		Fixture fix = body.createFixture(fdef);
		fix.setUserData("diamond");

		Diamond k = new Diamond(body, fix, x, y, 14, 16);

		fix.setUserData("caughtDiamond");
		
		k.update(0);
		
		assertTrue(k.isCaught());
		
		testWorld.dispose();
		k.dispose();
	}

}

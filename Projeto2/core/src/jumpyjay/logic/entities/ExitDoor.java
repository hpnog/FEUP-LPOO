package jumpyjay.logic.entities;

import jumpyjay.handlers.Assets;
import jumpyjay.handlers.SingletonVandC;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class ExitDoor extends Element {

	private SingletonVandC singleton;
	
	private int keysToCatch;
	private Texture openedDoorTex;
	private Texture closedDoorTex;

	public ExitDoor(Body body, float x, float y, float width, float height, int keys) {
		super(body);

		singleton = SingletonVandC.getSingleton();
		
		keysToCatch = keys;

		this.x = x * singleton.PPM;
		this.y = y * singleton.PPM;
		this.width = width;
		this.height = height;

		openedDoorTex = Assets.manager.get(Assets.openedDoor);
		closedDoorTex = Assets.manager.get(Assets.closedDoor);

		//create foot sensor
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		shape.setAsBox(0.5f / singleton.PPM, 0.5f / singleton.PPM, new Vector2(0, -10 / singleton.PPM), 0);
		fdef.shape = shape;
		fdef.filter.categoryBits = (short) singleton.BIT_DOOR_CENTER;
		fdef.filter.maskBits = singleton.BIT_ROBOT;
		fdef.isSensor = true;
		this.body.createFixture(fdef).setUserData("doorCenter");
		//---------------------------------------------------------------------------------------------------
	}

	public void update(float dt)
	{	

	}

	public void draw(Batch batch) {
		if (keysToCatch > 0)
			batch.draw(closedDoorTex, x - width / 2, y - height / 2);
		else
			batch.draw(openedDoorTex, x - width / 2, y - height / 2);
	}

	public int getKeysToCatch()
	{
		return keysToCatch;
	}

	public void setKeysToCatch(int is)
	{
		keysToCatch = is;
	}
	public void keyCaught()
	{
		keysToCatch--;
		if (keysToCatch == 0)
			singleton.exiting = 0;
	}


}

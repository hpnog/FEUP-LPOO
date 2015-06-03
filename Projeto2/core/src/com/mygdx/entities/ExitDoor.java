package com.mygdx.entities;

import static com.mygdx.game.Box2DVariables.PPM;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.gamestates.PlayState;

import static com.mygdx.game.Box2DVariables.*;
import static com.mygdx.entities.Robot.exiting;

public class ExitDoor extends Element {

	private int keysToCatch;
	private Texture openedDoorTex;
	private Texture closedDoorTex;

	public ExitDoor(Body body, float x, float y, float width, float height, int keys) {
		super(body);

		keysToCatch = keys;

		this.x = x * PPM;
		this.y = y * PPM;
		this.width = width;
		this.height = height;

		openedDoorTex = PlayState.res.getTexture("openedDoor");
		closedDoorTex = PlayState.res.getTexture("closedDoor");

		//create foot sensor
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		shape.setAsBox(0.5f / PPM, 0.5f / PPM, new Vector2(0, -10 / PPM), 0);
		fdef.shape = shape;
		fdef.filter.categoryBits = BIT_DOOR_CENTER;
		fdef.filter.maskBits = BIT_ROBOT;
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
			exiting = 0;
	}


}

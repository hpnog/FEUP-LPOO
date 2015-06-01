package com.mygdx.game;

import static com.mygdx.game.Box2DVariables.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class Robot extends Element {

	private static int JUMP_TIMEOUT = 10;
	private static int JUMP_FORCE = 25;
	
	private boolean canExit;
	private int hp;
	private String name;
	private int score;
	private int constantSpeedX;

	private PolygonShape robotShape;
	private Body robotB;
	private FixtureDef robotFixDef;
	private BodyDef robotBody;
	private int jumpTimeout;


	public Robot(Texture tex[], int x, int y, String name, float tileSize, World world) {
		super(tex, x * tileSize, y * tileSize, tileSize, tileSize);
		canExit = false;
		hp = 100;
		this.name = name;
		score = 0;
		constantSpeedX = 1;

		initPhysics(world);
	}

	private void initPhysics(World world)
	{
		//create platform-------------------------------------------------------------------------------------
		robotBody = new BodyDef();
		robotBody.position.set((getX() + 1) / PPM, (getY() + 1) / PPM);
		robotBody.type = BodyType.DynamicBody;
		robotB = world.createBody(robotBody);

		robotShape = new PolygonShape();
		robotShape.setAsBox((getElementWidth() - 3) / PPM / 2, (getElementHeight() - 2) / PPM / 2);

		robotFixDef = new FixtureDef();
		robotFixDef.shape = robotShape;
		robotFixDef.restitution = 0;
		robotFixDef.friction = 0;
		robotB.createFixture(robotFixDef);

		jumpTimeout = 0;
		//---------------------------------------------------------------------------------------------------
	}

	public void draw(Batch batch) {
		batch.draw(getSprites()[0].getTexture(), getX() - getElementWidth() / 2 - 1, getY() - getElementHeight() / 2 - 1, getElementWidth(), getElementHeight());
	}

	public void update(float deltaTime, World world) {
		setX(robotB.getPosition().x * PPM);
		setY(robotB.getPosition().y * PPM);		
						
		//Mantaining walking speed
		if (robotB.getLinearVelocity().x < constantSpeedX)
			robotB.applyForceToCenter(5, 0, true);
				
		//Jumping
		if (Gdx.input.isTouched() && robotB.getLinearVelocity().y == 0)
			jumpTimeout = JUMP_TIMEOUT;
		if (jumpTimeout > 0)
		{
			jumpTimeout--;
			robotB.applyForceToCenter(0, JUMP_FORCE, true);
		}
	}

}

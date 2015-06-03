package com.mygdx.entities;

import static com.mygdx.game.Box2DVariables.*;
import handlers.Click;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gamestates.PlayState;

public class Robot extends Element {

	private static int JUMP_FORCE_Y = 200;
	private static float SPEED_X = 1.3f;

	public static int jumpReady = 0;

	private int direction = 1;

	private PolygonShape robotShape;
	private Body robotB;
	private FixtureDef robotFixDef;

	//Para controlar o primeiro toque e para nao saltar
	private int paused;

	Click click;

	private String currentSprite;

	public Robot(Body body, float x, float y, float tileSize, World world)
	{
		super(body);
		robotB = body;

		currentSprite = "robotRight";
		Texture texture = PlayState.res.getTexture(currentSprite);
		TextureRegion[] sprites = TextureRegion.split(texture, 21, 21)[0];
		setAnimation(sprites,  1 / 12f);

		this.x = x * tileSize;
		this.y = y * tileSize;

		paused = 0;

		//Inicializa o click----------------------------------------------------------------------------------
		click = new Click();
		//----------------------------------------------------------------------------------------------------

		initPhysics(world);
	}

	private void initPhysics(World world)
	{
		robotShape = new PolygonShape();
		robotShape.setAsBox(width / PPM / 2,height / PPM / 2);

		robotFixDef = new FixtureDef();
		robotFixDef.shape = robotShape;
		robotFixDef.filter.categoryBits = BIT_ROBOT;
		robotFixDef.filter.maskBits = BIT_FLOOR_ROBOT;
		Fixture fix = robotB.createFixture(robotFixDef);
		fix.setUserData("robot");

		//create foot sensor
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		shape.setAsBox(width / PPM / 1.5f, height / PPM / 2 / 5, new Vector2(0, - height / PPM / 2), 0);
		fdef.shape = shape;
		fdef.filter.categoryBits = BIT_ROBOT;
		fdef.filter.maskBits = BIT_FLOOR_ROBOT;
		fdef.isSensor = true;
		robotB.createFixture(fdef).setUserData("robotFoot");
		//---------------------------------------------------------------------------------------------------
	}

	public void draw(Batch batch) {

		batch.draw(animation.getFrame(), x - width / 2, y - height / 2 - 1.2f);
	}

	public boolean update(float deltaTime, World world, double width, double height) {
		click.update();
		handleInput();

		animation.update(deltaTime);
		changeAnimation();

		x = getPosition().x * PPM;
		y = getPosition().y * PPM;		

		updatePause();

		if (paused > 0)
			updateWalkingSpeed();

		if (checkIfOutOfBounds(width, height))
			return true;

		return false;
	}

	private void handleInput() {
		if (click.gotClicked() && jumpReady > 0 && paused > 2)
			robotB.applyForceToCenter(0, JUMP_FORCE_Y, true);
		else if (click.gotClicked() && paused == 0)
			robotB.applyForceToCenter(SPEED_X, 0, true);
	}

	private void updatePause()
	{
		if (paused == 0 && click.gotClicked())
			paused++;
		else if (paused > 0)
			paused++;

	}

	private void updateWalkingSpeed()
	{
		//Mantém para a frente
		if (robotB.getLinearVelocity().x > 0 && robotB.getLinearVelocity().x < SPEED_X && paused > 0)
			robotB.applyForceToCenter(SPEED_X, 0, true);

		//Mantem para tras
		else if (robotB.getLinearVelocity().x < 0 && robotB.getLinearVelocity().x > -SPEED_X && paused > 0)
			robotB.applyForceToCenter(-SPEED_X, 0, true);

		//altera velocidades
		else if (robotB.getLinearVelocity().x == 0 && paused > 1)
			if (direction > 0)
			{
				direction = -1;
				robotB.applyForceToCenter(-SPEED_X * 50, 0, true);
			}
			else
			{
				direction = 1;
				robotB.applyForceToCenter(SPEED_X * 50, 0, true);
			}
	}

	private void changeAnimation()
	{
		if (jumpReady > 0)
		{
			if (direction == 1)
				if (currentSprite != "robotRight")
				{
					currentSprite = "robotRight";
					Texture texture = PlayState.res.getTexture(currentSprite);
					TextureRegion[] sprites = TextureRegion.split(texture, 21, 21)[0];
					setAnimation(sprites,  1 / 12f);
				}
				else {}
			else
			{
				if (currentSprite != "robotLeft"){
					currentSprite = "robotLeft";
					Texture texture = PlayState.res.getTexture(currentSprite);
					TextureRegion[] sprites = TextureRegion.split(texture, 21, 21)[0];
					setAnimation(sprites,  1 / 12f);	
				}
				else {}
			}
		}
		else
		{
			if (direction == 1)
			{
				if (currentSprite != "robotJumpRight")
				{
					currentSprite = "robotJumpRight";
					Texture texture = PlayState.res.getTexture(currentSprite);
					TextureRegion[] sprites = TextureRegion.split(texture, 21, 21)[0];
					setAnimation(sprites,  1 / 12f);
				}
				else {}
			}
			else
			{
				if (currentSprite != "robotJumpLeft"){
					currentSprite = "robotJumpLeft";
					Texture texture = PlayState.res.getTexture(currentSprite);
					TextureRegion[] sprites = TextureRegion.split(texture, 21, 21)[0];
					setAnimation(sprites,  1 / 12f);	
				}
				else {}
			}
		}
	}

	private boolean checkIfOutOfBounds(double width, double height) {
		if (x < 0)
			return true;
		else if (y < 0)
			return true;
		/*else if (x + width > width)
			return true;
		else if (y + height > height)
			return true;*/
		else	
			return false;
	}

	public void dispose() {
		robotShape.dispose();
	}

}

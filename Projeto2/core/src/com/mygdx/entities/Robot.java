package com.mygdx.entities;

import static com.mygdx.game.Box2DVariables.PPM;
import handlers.Click;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gamestates.PlayState;

public class Robot extends Element {

	private static int JUMP_TIMEOUT = 3;
	private static int JUMP_FORCE = 80;

	private float constantSpeedX;
	private int direction = -1;

	private PolygonShape robotShape;
	private Body robotB;
	private FixtureDef robotFixDef;
	private int jumpTimeout;
	private float decrease;

	//Para controlar o primeiro toque e para nao saltar
	private int paused;

	Click click;

	public Robot(Body body, float x, float y, float tileSize, World world)
	{
		super(body);
		robotB = body;
		
		changeAnimation("robotRight");

		this.x = x * tileSize;
		this.y = y * tileSize;

		constantSpeedX = (float) 0.5;
		paused = 0;

		//Inicializa o click----------------------------------------------------------------------------------
		click = new Click();
		//----------------------------------------------------------------------------------------------------

		initPhysics(world);
	}

	private void initPhysics(World world)
	{
		robotShape = new PolygonShape();
		robotShape.setAsBox(width / PPM / 2, height / PPM / 2);

		robotFixDef = new FixtureDef();
		robotFixDef.shape = robotShape;
		robotFixDef.restitution = 0;
		robotFixDef.friction = 0;
		robotB.createFixture(robotFixDef);
		
		jumpTimeout = 0;
		decrease = 1;
		//---------------------------------------------------------------------------------------------------
	}

	public void draw(Batch batch) {

			batch.draw(animation.getFrame(), x - width / 2, y - height / 2);
	}

	public boolean update(float deltaTime, World world, double width, double height) {
		animation.update(deltaTime);
		
		x = getPosition().x * PPM;
		y = getPosition().y * PPM;		

		//Jumping
		if (click.gotClicked() && robotB.getLinearVelocity().y == 0 && paused > 2)
		{
			decrease = 1;
			jumpTimeout = JUMP_TIMEOUT;
		}

		updatePause();

		updateWalkingSpeed();

		updateJump();

		if (checkIfOutOfBounds(width, height))
			return true;

		return false;
	}

	private void updateJump()
	{
		if (jumpTimeout > 0)
		{
			jumpTimeout--;
			robotB.applyForceToCenter(JUMP_FORCE / 6, JUMP_FORCE, true);
			decrease = decrease / 2;
		}
		else if (robotB.getLinearVelocity().x > constantSpeedX)
			robotB.applyForceToCenter(-JUMP_FORCE / 50, 0, true);

	}

	private void updatePause()
	{
		click.update();
		if (paused == 0 && click.gotClicked())
			paused++;
		else if (paused > 0)
			paused++;

	}

	private void updateWalkingSpeed()
	{
		//Mantém para a frente
		if (robotB.getLinearVelocity().x > 0 && robotB.getLinearVelocity().x < constantSpeedX && paused > 0)
			robotB.applyForceToCenter(5, 0, true);

		//Mantem para tras
		else if (robotB.getLinearVelocity().x < 0 && robotB.getLinearVelocity().x > -constantSpeedX && paused > 0)
			robotB.applyForceToCenter(-5, 0, true);

		//altera velocidades
		else if (robotB.getLinearVelocity().x == 0 && paused > 0)
			if (direction > 0)
			{
				direction = -1;
				robotB.applyForceToCenter(-5, 0, true);
				
				changeAnimation("robotLeft");
			}
			else
			{
				direction = 1;
				robotB.applyForceToCenter(5, 0, true);
				
				changeAnimation("robotRight");
			}
	}
	
	private void changeAnimation(String name)
	{
		Texture texture = PlayState.res.getTexture(name);
		TextureRegion[] sprites = TextureRegion.split(texture, 21, 21)[0];
		setAnimation(sprites,  1 / 12f);
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

}

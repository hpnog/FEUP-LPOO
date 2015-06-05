package jumpyjay.logic.entities;

import jumpyjay.handlers.Assets;
import jumpyjay.handlers.SingletonVandC;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Robot extends Element {

	SingletonVandC singleton;

	private int direction = 1;

	private PolygonShape robotShape;
	private Body robotB;
	private FixtureDef robotFixDef;

	private int hurtTimer = 0;
	private int hp;

	//Para controlar o primeiro toque e para nao saltar

	private String currentSprite;

	public Robot(Body body, float x, float y, float tileSize, World world)
	{
		super(body);

		singleton = SingletonVandC.getSingleton();
		singleton.jumpReady = 0;
		singleton.loseLife = 0;

		singleton.exiting = -1;
		robotB = body;

		hp = 3;
		singleton.levelScore = 0;

		currentSprite = "robotRight";

		Texture texture = Assets.manager.get(Assets.robotRight);
		TextureRegion[] sprites = TextureRegion.split(texture, 21, 21)[0];
		setAnimation(sprites,  1 / 12f);

		this.x = x * tileSize;
		this.y = y * tileSize;
		
		String mess = "Xini = " + this.x + " Yini = " + this.y;
		Gdx.app.log("MyG", mess);
		
		SingletonVandC.paused = -1;

		
		initPhysics(world);
	}

	private void initPhysics(World world)
	{
		robotShape = new PolygonShape();
		robotShape.setAsBox(width / singleton.PPM / 2,height / singleton.PPM / 2);

		robotFixDef = new FixtureDef();
		robotFixDef.shape = robotShape;
		robotFixDef.filter.categoryBits = singleton.BIT_ROBOT;
		robotFixDef.filter.maskBits = (short) (singleton.BIT_FLOOR_ROBOT | singleton.BIT_KEY | singleton.BIT_DIAMOND | singleton.BIT_DOOR_CENTER | singleton.BIT_DANGER);
		Fixture fix = robotB.createFixture(robotFixDef);
		fix.setUserData("robot");

		//create foot sensor
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		shape.setAsBox(width / singleton.PPM / 1.6f, height / singleton.PPM / 2 / 5, new Vector2(0, - height / singleton.PPM / 2), 0);
		fdef.shape = shape;
		fdef.filter.categoryBits = singleton.BIT_ROBOT_FOOT;
		fdef.filter.maskBits = singleton.BIT_FLOOR;
		fdef.isSensor = true;
		robotB.createFixture(fdef).setUserData("robotFoot");
		//---------------------------------------------------------------------------------------------------
	}

	public void draw(Batch batch) {

		batch.draw(animation.getFrame(), x - width / 2, y - height / 2 - 1.2f);
	}

	public boolean update(float deltaTime, World world, double width, double height, Vector2 doorPos) {

		
		handleInput();
		updatePause();
		
		if (SingletonVandC.paused < 0)
			return false;
		
		if (singleton.exiting > 0)
		{
			
			robotB.setLinearVelocity(new Vector2(0, 0));
			changeAnimation();
			animation.update(deltaTime);
			x = (doorPos.x * singleton.PPM);
			y = (doorPos.y * singleton.PPM - this.height / 2);
			return false;
		}

		animation.update(deltaTime);
		changeAnimation();

		x = getPosition().x * singleton.PPM;
		y = getPosition().y * singleton.PPM;		
				
		if (SingletonVandC.paused > 0)
			updateWalkingSpeed();

		updateLife();

		if (checkIfOutOfBounds(width, height))
			return true;
		
		return false;
	}

	public void updateLife()
	{
		if (singleton.loseLife > 3 && hurtTimer == 0)
		{
			hp--;
			hurtTimer = 50;
			robotB.applyLinearImpulse(new Vector2(0, singleton.JUMP_FORCE_Y), new Vector2((robotB.getPosition().x + width / singleton.PPM), (robotB.getPosition().y + height / singleton.PPM)), true);
		}
		else if (hurtTimer > 0)
		{
			hurtTimer--;
		}
	}

	private void handleInput() {
		if (singleton.click.gotClicked() && singleton.jumpReady > 0 && SingletonVandC.paused > 0)
		{
			robotB.applyLinearImpulse(new Vector2(0, singleton.JUMP_FORCE_Y), new Vector2((robotB.getPosition().x + width / singleton.PPM), (robotB.getPosition().y + height / singleton.PPM)), true);
			singleton.loseLife = 0;
		}
		//Restarts / starts the game
		else if (singleton.click.gotClicked() && SingletonVandC.paused <= 0)
			robotB.applyForceToCenter(singleton.SPEED_X, 0, true);
	}

	private void updatePause()
	{
		if ((SingletonVandC.paused <= 0) && singleton.click.gotClicked())
			SingletonVandC.paused = 1;
		else if (SingletonVandC.paused == 1)
			SingletonVandC.paused++;
	}

	private void updateWalkingSpeed()
	{
		//Mantém para a frente
		if (robotB.getLinearVelocity().x > 0 && robotB.getLinearVelocity().x < singleton.SPEED_X && SingletonVandC.paused > 0)
			robotB.applyForceToCenter(singleton.SPEED_X, 0, true);

		//Mantem para tras
		else if (robotB.getLinearVelocity().x < 0 && robotB.getLinearVelocity().x > -singleton.SPEED_X && SingletonVandC.paused > 0)
			robotB.applyForceToCenter(-singleton.SPEED_X, 0, true);

		//altera velocidades
		else if (robotB.getLinearVelocity().x == 0 && SingletonVandC.paused > 1)
			if (direction > 0)
			{
				direction = -1;
				robotB.applyForceToCenter(-singleton.SPEED_X * 50, 0, true);
			}
			else
			{
				direction = 1;
				robotB.applyForceToCenter(singleton.SPEED_X * 50, 0, true);
			}
	}

	private void changeAnimation()
	{
		if (singleton.exiting > 0 && currentSprite != "robotExit")
		{
			singleton.exiting = 100;
			currentSprite = "robotExit";
			Texture tex = Assets.manager.get(Assets.robotExit);
			TextureRegion[] sprites = TextureRegion.split(tex, 18, 21)[0];
			setAnimation(sprites, 1 / 3f);
			animation.setFrames(sprites, 1/5f, false);
		}
		else if (singleton.jumpReady > 0 && currentSprite != "robotExit")
		{
			if (direction == 1)
				if (currentSprite != "robotRight")
				{
					currentSprite = "robotRight";

					y = y + 20;

					Texture texture = Assets.manager.get(Assets.robotRight);
					TextureRegion[] sprites = TextureRegion.split(texture, 21, 21)[0];
					setAnimation(sprites,  1 / 12f);
				}
				else {}
			else
			{
				if (currentSprite != "robotLeft"){
					currentSprite = "robotLeft";
					Texture texture = Assets.manager.get(Assets.robotLeft);
					TextureRegion[] sprites = TextureRegion.split(texture, 21, 21)[0];
					setAnimation(sprites,  1 / 12f);	
				}
				else {}
			}
		}
		else if (currentSprite != "robotExit")
		{
			if (direction == 1)
			{
				if (currentSprite != "robotJumpRight")
				{
					currentSprite = "robotJumpRight";
					Texture texture = Assets.manager.get(Assets.robotJumpRight);
					TextureRegion[] sprites = TextureRegion.split(texture, 21, 21)[0];
					setAnimation(sprites,  1 / 12f);
				}
				else {}
			}
			else
			{
				if (currentSprite != "robotJumpLeft"){
					currentSprite = "robotJumpLeft";
					Texture texture = Assets.manager.get(Assets.robotJumpLeft);
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

	public int getHp()
	{
		return hp;
	}

}

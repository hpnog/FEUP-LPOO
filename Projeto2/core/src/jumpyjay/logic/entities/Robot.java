package jumpyjay.logic.entities;

import jumpyjay.handlers.Assets;
import jumpyjay.handlers.SingletonVandC;
import jumpyjay.handlers.SoundHandler;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

// TODO: Auto-generated Javadoc
/**
 * The Class Robot.
 */
public class Robot extends Element {

	/** The singleton. */
	SingletonVandC singleton;

	/** The direction. */
	private int direction = 1;

	/** The robot shape. */
	private PolygonShape robotShape;
	
	/** The robot b. */
	private Body robotB;
	
	/** The robot fix def. */
	private FixtureDef robotFixDef;

	/** The hurt timer. */
	private int hurtTimer = 0;
	
	/** The hp. */
	private int hp;

	//Para controlar o primeiro toque e para nao saltar

	/** The current sprite. */
	private String currentSprite;

	/**
	 * Instantiates a new robot.
	 *
	 * @param body the body
	 * @param x the x
	 * @param y the y
	 * @param tileSize the tile size
	 * @param world the world
	 */
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

		if (!SingletonVandC.testing)
		{
			Texture texture = Assets.manager.get(Assets.robotRight);
			TextureRegion[] sprites = TextureRegion.split(texture, 21, 21)[0];
			setAnimation(sprites,  1 / 12f);
		}

		this.x = x * tileSize;
		this.y = y * tileSize;

		SingletonVandC.paused = -1;


		initPhysics(world);
	}

	/**
	 * Inits the physics.
	 *
	 * @param world the world
	 */
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

	/**
	 * Draw.
	 *
	 * @param batch the batch
	 */
	public void draw(Batch batch) {

		batch.draw(animation.getFrame(), x - width / 2, y - height / 2 - 1.2f);
	}

	/**
	 * Update.
	 *
	 * @param deltaTime the delta time
	 * @param world the world
	 * @param width the width
	 * @param height the height
	 * @param doorPos the door pos
	 * @return true, if successful
	 */
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

		if (!SingletonVandC.testing)
		{
			animation.update(deltaTime);
			changeAnimation();
		}

		x = getPosition().x * singleton.PPM;
		y = getPosition().y * singleton.PPM;		

		if (SingletonVandC.paused > 0)
			updateWalkingSpeed();

		updateLife();

		if (checkIfOutOfBounds(width, height))
			return true;

		return false;
	}

	/**
	 * Update life.
	 */
	public void updateLife()
	{
		if (singleton.loseLife > 3 && hurtTimer == 0)
		{
			if (hp > 1)
				SoundHandler.playLostLife();
			hp--;
			hurtTimer = 50;
			robotB.applyLinearImpulse(new Vector2(0, singleton.JUMP_FORCE_Y), new Vector2((robotB.getPosition().x + width / singleton.PPM), (robotB.getPosition().y + height / singleton.PPM)), true);
		}
		else if (hurtTimer > 0)
		{
			hurtTimer--;
		}
	}

	/**
	 * Handle input.
	 */
	private void handleInput() {
		if (singleton.click.gotClicked() && singleton.jumpReady > 0 && SingletonVandC.paused > 0)
		{
			robotB.applyLinearImpulse(new Vector2(0, singleton.JUMP_FORCE_Y), new Vector2((robotB.getPosition().x + width / singleton.PPM), (robotB.getPosition().y + height / singleton.PPM)), true);
			singleton.loseLife = 0;
			SoundHandler.playJump();
		}
		//Restarts / starts the game
		else if (singleton.click.gotClicked() && SingletonVandC.paused <= 0)
			robotB.applyForceToCenter(singleton.SPEED_X, 0, true);
	}

	/**
	 * Update pause.
	 */
	public void updatePause()
	{
		if ((SingletonVandC.paused <= 0) && singleton.click.gotClicked())
			SingletonVandC.paused = 1;
		else if (SingletonVandC.paused == 1)
			SingletonVandC.paused++;
	}

	/**
	 * Update walking speed.
	 */
	public void updateWalkingSpeed()
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

	/**
	 * Change animation.
	 */
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

	/**
	 * Check if out of bounds.
	 *
	 * @param width the width
	 * @param height the height
	 * @return true, if successful
	 */
	public boolean checkIfOutOfBounds(double width, double height) {
		if (x < 0)
			return true;
		else if (y < 0)
			return true;
		else if (x + this.width > width)
			return true;
		else if (y + this.height > height)
			return true;
		else	
			return false;
	}

	/* (non-Javadoc)
	 * @see jumpyjay.logic.entities.Element#dispose()
	 */
	public void dispose() {
		robotShape.dispose();
	}

	/**
	 * Gets the hp.
	 *
	 * @return the hp
	 */
	public int getHp()
	{
		return hp;
	}

}

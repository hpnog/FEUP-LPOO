package com.mygdx.game;

import static com.mygdx.game.Box2DVariables.PPM;
import resCode.Animator;
import handlers.Click;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class Robot extends Element {

	private static int JUMP_TIMEOUT = 3;
	private static int JUMP_FORCE = 80;
	
	private boolean canExit;
	private int hp;
	private String name;
	private int score;
	private float constantSpeedX;
	private int direction = -1;

	private PolygonShape robotShape;
	private Body robotB;
	private FixtureDef robotFixDef;
	private BodyDef robotBody;
	private int jumpTimeout;
	private float decrease;

	private Animator robotRight;
	
	//Para controlar o primeiro toque e para nao saltar
	private int paused;

	Click click;

	public Robot(Texture tex[], int x, int y, String name, float tileSize, World world)
	{
		super(tex, x * tileSize, y * tileSize, tileSize, tileSize);
		canExit = false;
		hp = 100;
		this.name = name;
		score = 0;
		constantSpeedX = (float) 0.5;
		paused = 0;
		
		robotRight = new Animator("maps/robots/robotwalkright.png", 2, 1);

		//Inicializa o click----------------------------------------------------------------------------------
		click = new Click();
		//----------------------------------------------------------------------------------------------------

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
		decrease = 1;
		//---------------------------------------------------------------------------------------------------
	}

	public void draw(Batch batch) {
		
		batch.draw(getSprites()[0].getTexture(), getX() - getElementWidth() / 2 - 1, getY() - getElementHeight() / 2 - 1, getElementWidth(), getElementHeight());
		
		//batch.draw(robotRight.getTextureRegion(), getX() - getElementWidth() / 2, getY() - getElementHeight() / 2, getElementWidth(), getElementHeight());
		
	}

	public static int getJUMP_TIMEOUT() {
		return JUMP_TIMEOUT;
	}

	public static void setJUMP_TIMEOUT(int jUMP_TIMEOUT) {
		JUMP_TIMEOUT = jUMP_TIMEOUT;
	}

	public static int getJUMP_FORCE() {
		return JUMP_FORCE;
	}

	public static void setJUMP_FORCE(int jUMP_FORCE) {
		JUMP_FORCE = jUMP_FORCE;
	}

	public boolean isCanExit() {
		return canExit;
	}

	public void setCanExit(boolean canExit) {
		this.canExit = canExit;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public float getConstantSpeedX() {
		return constantSpeedX;
	}

	public void setConstantSpeedX(float constantSpeedX) {
		this.constantSpeedX = constantSpeedX;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public PolygonShape getRobotShape() {
		return robotShape;
	}

	public void setRobotShape(PolygonShape robotShape) {
		this.robotShape = robotShape;
	}

	public Body getRobotB() {
		return robotB;
	}

	public void setRobotB(Body robotB) {
		this.robotB = robotB;
	}

	public FixtureDef getRobotFixDef() {
		return robotFixDef;
	}

	public void setRobotFixDef(FixtureDef robotFixDef) {
		this.robotFixDef = robotFixDef;
	}

	public BodyDef getRobotBody() {
		return robotBody;
	}

	public void setRobotBody(BodyDef robotBody) {
		this.robotBody = robotBody;
	}

	public int getJumpTimeout() {
		return jumpTimeout;
	}

	public void setJumpTimeout(int jumpTimeout) {
		this.jumpTimeout = jumpTimeout;
	}

	public float getDecrease() {
		return decrease;
	}

	public void setDecrease(float decrease) {
		this.decrease = decrease;
	}

	public int getPaused() {
		return paused;
	}

	public void setPaused(int paused) {
		this.paused = paused;
	}

	public Click getClick() {
		return click;
	}

	public void setClick(Click click) {
		this.click = click;
	}

	public Animator getRobotRight() {
		return robotRight;
	}

	public void setRobotRight(Animator robotRight) {
		this.robotRight = robotRight;
	}

	public boolean update(float deltaTime, World world, double width, double height) {
		setX(robotB.getPosition().x * PPM);
		setY(robotB.getPosition().y * PPM);		

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
			}
			else
			{
				direction = 1;
				robotB.applyForceToCenter(5, 0, true);
			}
	}

	private boolean checkIfOutOfBounds(double width, double height) {
		if (getX() < 0)
			return true;
		else if (getY() < 0)
			return true;
		else if (getX() + getElementWidth() > width)
			return true;
		else if (getY() + getElementHeight() > height)
			return true;
		else	
			return false;
	}

}

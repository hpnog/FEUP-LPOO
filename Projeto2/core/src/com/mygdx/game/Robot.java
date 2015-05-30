package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Robot extends Sprite {
	
	/**
	 * gravity - macro for the Gravity speed
	 */
	private final int gravity = 30;
	/**
	 * jumpingSpeed - macro for the Y speed when jumping
	 */
	private final int jumpingSpeed = 50;
	
	private boolean canExit;
	private int hp;
	private String name;
	private int score;
	private TiledMapTileLayer collision;
	private float speedX;
	private float speedY;

	public Robot(Texture tex, int x, int y, String name, TiledMapTileLayer collisionLayer) {
		super(tex, x, y, tex.getWidth(), tex.getHeight());
		canExit = false;
		hp = 100;
		this.name = name;
		score = 0;
		collision = collisionLayer;
		speedY = 0;			//Initializes the speed as 0
		speedX = 0;
		setX(collision.getTileWidth() * x);
		setY(collision.getTileHeight() * y);
	}

	public void draw(Batch batch) {
		update (Gdx.graphics.getDeltaTime());
		batch.draw(getTexture(), getX(), getY(), getWidth(), getHeight());
	}

	public void update(float deltaTime) {
		
		//Store the old value of coordinates to prevent collisions
		float oldX = getX();
		float oldY = getY();
		float tileWidth = collision.getTileWidth();
		float tileHeight = collision.getTileHeight();
		
		//Coliding variables initiated
		boolean collidesX = false;
		boolean collidesY = false;
		
		//Apply gravity
		speedY -= deltaTime * gravity;
		
		//Update coordinates according to speed
		setY(getY() + speedY * deltaTime);
		setX(getX() + speedX * deltaTime);
		
		//Check collisions when moving backwards
		if (speedX < 0)
		{
			//Top Left
			if (collision.getCell((int) (getX() / tileWidth), (int) ((getY() + getHeight()) / tileHeight)) != null)
				collidesX = collision.getCell((int) (getX() / tileWidth), (int) ((getY() + getHeight()) / tileHeight)).getTile().getProperties().containsKey("blocked");
			//Middle Left
			if (!collidesX && collision.getCell((int) (getX() / tileWidth), (int) (((getY() + getHeight()) / 2) / tileHeight)) != null)
				collidesX = collision.getCell((int) (getX() / tileWidth), (int) (((getY() + getHeight()) / 2) / tileHeight)).getTile().getProperties().containsKey("blocked");
			//Bottom Left
			if (!collidesX && collision.getCell((int) (getX() / tileWidth), (int) (getY() / tileHeight)) != null)
				collidesX = collision.getCell((int) (getX() / tileWidth), (int) (getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
		}
		
		//Check collisions when moving forwards
		else if (speedX > 0)
		{
			//Top Right
			if (collision.getCell((int) ((getX() + getWidth()) / tileWidth), (int) ((getY() + getHeight()) / tileHeight)) != null)
				collidesX = collision.getCell((int) ((getX() + getWidth()) / tileWidth), (int) ((getY() + getHeight()) / tileHeight)).getTile().getProperties().containsKey("blocked");
			//Middle Right
			if (!collidesX && collision.getCell((int) ((getX() + getWidth()) / tileWidth), (int) (((getY() + getHeight()) / 2) / tileHeight)) != null)
				collidesX = collision.getCell((int) ((getX() + getWidth()) / tileWidth), (int) (((getY() + getHeight()) / 2) / tileHeight)).getTile().getProperties().containsKey("blocked");
			//Bottom Right
			if (!collidesX && collision.getCell((int) ((getX() + getWidth()) / tileWidth), (int) (getY() / tileHeight)) != null)
				collidesX = collision.getCell((int) ((getX() + getWidth()) / tileWidth), (int) (getY() / tileHeight)).getTile().getProperties().containsKey("blocked");

		}
		
		//Deal with collisions - revert movement and reset speed
		if (collidesX)
		{
			setX(oldX);
			speedX = 0;
		}
		
		//Check collisions when moving up
		if (speedY > 0) 
		{
			//Top Left
			if (collision.getCell((int) (getX() / tileWidth), (int) ((getY() + getHeight()) / tileHeight)) != null)
				collidesY = collision.getCell((int) (getX() / tileWidth), (int) ((getY() + getHeight()) / tileHeight)).getTile().getProperties().containsKey("blocked");
			//Top Middle
			if (!collidesY && collision.getCell((int) (((getX() + getWidth()) / 2) / tileWidth), (int) ((getY() + getHeight()) / tileHeight)) != null)
				collidesY = collision.getCell((int) (((getX() + getWidth()) / 2) / tileWidth), (int) ((getY() + getHeight()) / tileHeight)).getTile().getProperties().containsKey("blocked");
			//Top Right
			if (!collidesY && collision.getCell((int) ((getX() + getWidth()) / tileWidth), (int) ((getY() + getHeight()) / tileHeight)) != null)
				collidesY = collision.getCell((int) ((getX() + getWidth()) / tileWidth), (int) ((getY() + getHeight()) / tileHeight)).getTile().getProperties().containsKey("blocked");
		}
		
		//Check collisions when moving down
		else if (speedY < 0)
		{
			//Bottom Left
			if (collision.getCell((int) (getX() / tileWidth), (int) (getY() / tileHeight)) != null)
				collidesY = collision.getCell((int) (getX() / tileWidth), (int) (getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
			//Bottom Middle
			if (!collidesY && collision.getCell((int) (((getX() + getWidth()) / 2) / tileWidth), (int) (getY() / tileHeight)) != null)
				collidesY = collision.getCell((int) (((getX() + getWidth()) / 2) / tileWidth), (int) (getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
			//Bottom Right
			if (!collidesY && collision.getCell((int) ((getX() + getWidth()) / tileWidth), (int) (getY() / tileHeight)) != null)
				collidesY = collision.getCell((int) ((getX() + getWidth()) / tileWidth), (int) (getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
		}
		
		//Deal with collisions - revert movement and reset speed
		if (collidesY)
		{
			setY(oldY);
			speedY = 0;
		}
	}
	
	public void checkJump()
	{
		//When the screen is touched the robot jumps unless he is not stoped (according to Y axis)
		if (Gdx.input.isTouched() && speedY == 0)
			speedY = jumpingSpeed;
	}
}

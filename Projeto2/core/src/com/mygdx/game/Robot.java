package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Robot extends Sprite {
	
	private boolean canExit;
	private int hp;
	private String name;
	private int score;
	
	public Robot(Texture tex, int x, int y, String name) {
		super(tex, x, y, tex.getWidth(), tex.getHeight());
		canExit = false;
		hp = 100;
		this.name = name;
		score = 0;
	}
	
	public void draw(SpriteBatch batch) {
		update (Gdx.graphics.getDeltaTime());
		super.draw(batch);
	}

	public void update(float deltaTime) {
		setX(getX() + 50 * deltaTime);
	}
}

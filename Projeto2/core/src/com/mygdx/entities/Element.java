package com.mygdx.entities;

import handlers.Animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class Element {
	
	protected Body body;
	protected Animation animation;
	protected float width;
	protected float height;
	
	protected float x;
	protected float y;
	
	public Element(Body body)
	{
		this.body = body;
		animation = new Animation();
	}

	public void setAnimation(TextureRegion[] reg, float delay) {
		animation.setFrames(reg, delay);
		width = reg[0].getRegionWidth();
		height = reg[0].getRegionHeight();
	}
	
	public void update(float dt) {
		animation.update(dt);
	}
	
	public void render(SpriteBatch sb) {
		sb.begin();
		
		sb.draw(animation.getFrame(), x, y, width, height);
		
		System.out.printf("%f, %f, %f, %f\n", x, y, width, height);
		
		sb.end();
	}
	
	public Body getBody() {
		return body;
	}
	
	public Vector2 getPosition() {
		return body.getPosition();
	}

	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
}

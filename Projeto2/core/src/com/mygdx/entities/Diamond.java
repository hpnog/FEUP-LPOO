package com.mygdx.entities;

import handlers.Assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.mygdx.game.SingletonVandC;

public class Diamond extends Element {

	private SingletonVandC singleton;
	
	private boolean caught;
	private Fixture kFix;

	public Diamond(Body body, Fixture fix, float x, float y, float width, float height) {
		super(body);

		singleton = SingletonVandC.getSingleton();
		
		kFix = fix;
		caught = false;

		this.x = x * singleton.PPM;		
		this.y = y * singleton.PPM;
		this.width = width;
		this.height = height;

		
		
		Texture texture = Assets.manager.get(Assets.diamond);
		TextureRegion[] sprites = TextureRegion.split(texture, 14, 16)[0];
		setAnimation(sprites,  1/10f);
	}

	public void update(float dt)
	{
		if (kFix.getUserData() == "caughtDiamond" && !caught)
			caught = true;
		
		animation.update(dt);
	}

	public void draw(Batch batch) {
		if (!caught)
			batch.draw(animation.getFrame(), x - width / 2, y - height / 2);
	}

	public boolean isCaught()
	{
		return caught;
	}

	public void setCaught(boolean is)
	{
		caught = is;
	}

}

package com.mygdx.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.mygdx.gamestates.PlayState;

import static com.mygdx.game.Box2DVariables.*;

public class Key extends Element {

	private boolean caught;
	private Fixture kFix;

	public Key(Body body, Fixture fix, float x, float y, float width, float height) {
		super(body);

		kFix = fix;
		caught = false;

		this.x = x * PPM;
		this.y = y * PPM;
		this.width = width;
		this.height = height;

		Texture texture = PlayState.res.getTexture("key");
		TextureRegion[] sprites = TextureRegion.split(texture, 13, 12)[0];
		setAnimation(sprites,  1/3f);
	}

	public void update(float dt, boolean justCaught)
	{
		animation.update(dt);
	}
	
	public boolean checkifCaught()
	{
		if (kFix.getUserData() == "caughtKey" && !caught)
		{
			caught = true;
			return true;
		}
		return false;
	}

	public void draw(Batch batch) {
		if (!caught)
			batch.draw(animation.getFrame(), x - width / 2, y - height / 2 + 2);
	}

	public boolean isCaught()
	{
		return caught;
	}

	public boolean setCaught(boolean is)
	{
		if (!caught && is == true)
		{
			caught = is;
			return true;
		}
		caught = is;
		return false;
	}

}

package com.mygdx.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.mygdx.gamestates.PlayState;

import static com.mygdx.game.Box2DVariables.*;

public class Diamond extends Element {

	private boolean caught;
	private Fixture kFix;

	public Diamond(Body body, Fixture fix, float x, float y, float width, float height) {
		super(body);

		kFix = fix;
		caught = false;

		this.x = x * PPM;		
		this.y = y * PPM;
		this.width = width;
		this.height = height;

		
		
		Texture texture = PlayState.res.getTexture("diamond");
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

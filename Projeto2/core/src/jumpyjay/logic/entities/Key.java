package jumpyjay.logic.entities;

import jumpyjay.handlers.Assets;
import jumpyjay.handlers.SingletonVandC;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

// TODO: Auto-generated Javadoc
/**
 * The Class Key.
 */
public class Key extends Element {

	/** The caught. */
	private boolean caught;
	
	/** The k fix. */
	private Fixture kFix;

	/** The singleton. */
	private SingletonVandC singleton;

	/**
	 * Instantiates a new key.
	 *
	 * @param body the body
	 * @param fix the fix
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 */
	public Key(Body body, Fixture fix, float x, float y, float width, float height) {
		super(body);

		singleton = SingletonVandC.getSingleton();

		kFix = fix;
		caught = false;

		this.x = x * singleton.PPM;
		this.y = y * singleton.PPM;
		this.width = width;
		this.height = height;

		if (!SingletonVandC.testing)
		{
			Texture texture = Assets.manager.get(Assets.key);
			TextureRegion[] sprites = TextureRegion.split(texture, 13, 12)[0];
			setAnimation(sprites,  1/3f);
		}
	}

	/**
	 * Update.
	 *
	 * @param dt the dt
	 * @param justCaught the just caught
	 */
	public void update(float dt, boolean justCaught)
	{
		animation.update(dt);
	}

	/**
	 * Checkif caught.
	 *
	 * @return true, if successful
	 */
	public boolean checkifCaught()
	{
		if (kFix.getUserData() == "caughtKey" && !caught)
		{
			caught = true;
			return true;
		}
		return false;
	}

	/**
	 * Draw.
	 *
	 * @param batch the batch
	 */
	public void draw(Batch batch) {
		if (!caught)
			batch.draw(animation.getFrame(), x - width / 2, y - height / 2 + 2);
	}

	/**
	 * Checks if is caught.
	 *
	 * @return true, if is caught
	 */
	public boolean isCaught()
	{
		return caught;
	}

}

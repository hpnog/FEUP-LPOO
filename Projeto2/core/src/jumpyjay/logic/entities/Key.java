package jumpyjay.logic.entities;

import jumpyjay.handlers.Assets;
import jumpyjay.handlers.SingletonVandC;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;


/**
 * The Class Key.
 */
public class Key extends Element {

	/** Boolean to see if the key is caught or not */
	private boolean caught;
	
	/** The k fixture */
	private Fixture kFix;

	/** The singleton. */
	private SingletonVandC singleton;

	/**
	 * Instantiates a new key.
	 *
	 * @param body the body
	 * @param fix the fixture
	 * @param x the x coord of the key.
	 * @param y the y coord of the key.
	 * @param width the width of the key.
	 * @param height the height of the key.
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
	 * Updates the animation
	 *
	 * @param dt the time that has passed since the last update
	 * @param justCaught if true, the element is caught
	 */
	public void update(float dt, boolean justCaught)
	{
		animation.update(dt);
	}

	/**
	 * Check if caught.
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
	 * Draws the key 
	 *
	 * @param batch the batch
	 */
	public void draw(Batch batch) {
		if (!caught)
			batch.draw(animation.getFrame(), x - width / 2, y - height / 2 + 2);
	}

	/**
	 * Checks if the key is caught.
	 *
	 * @return true, if is caught
	 */
	public boolean isCaught()
	{
		return caught;
	}

}

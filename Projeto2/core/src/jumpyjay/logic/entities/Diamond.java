package jumpyjay.logic.entities;

import jumpyjay.handlers.Assets;
import jumpyjay.handlers.SingletonVandC;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;


/**
 * The Class Diamond.
 */
public class Diamond extends Element {

	/** The singleton. */
	private SingletonVandC singleton;

	/** Boolean to check if the diamond was caught or not */
	private boolean caught;
	
	/** The k fix. */
	private Fixture kFix;

	/**
	 * Instantiates a new diamond.
	 *
	 * @param body the body
	 * @param fix the fixture
	 * @param x the x coord of the diamond.
	 * @param y the y coord of the diamond.
	 * @param width the width of the diamond.
	 * @param height the height of the diamond.
	 */
	public Diamond(Body body, Fixture fix, float x, float y, float width, float height) {
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
			Texture texture = Assets.manager.get(Assets.diamond);
			TextureRegion[] sprites = TextureRegion.split(texture, 14, 16)[0];
			setAnimation(sprites,  1/10f);
		}
	}

	/** 
	 * @see jumpyjay.logic.entities.Element#update(float)
	 */
	public void update(float dt)
	{
		if (kFix.getUserData() == "caughtDiamond" && !caught)
		{
			caught = true;
			singleton.levelScore += 10;
		}

		animation.update(dt);
	}

	/**
	 * Draws the diamond
	 *
	 * @param batch the batch
	 */
	public void draw(Batch batch) {
		if (!caught)
			batch.draw(animation.getFrame(), x - width / 2, y - height / 2);
	}

	/**
	 * Checks if the diamond is caught
	 *
	 * @return true, if is caught
	 */
	public boolean isCaught()
	{
		return caught;
	}

}

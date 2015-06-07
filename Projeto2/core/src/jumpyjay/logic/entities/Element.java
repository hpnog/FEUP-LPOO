package jumpyjay.logic.entities;

import jumpyjay.handlers.Animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;


/**
 * The Class Element.
 */
public abstract class Element {
	
	/** The body of the element. */
	protected Body body;
	
	/** The animation of the element. */
	protected Animation animation;
	
	/** The width of the element. */
	protected float width;
	
	/** The height of the element. */
	protected float height;
	
	/** The x coord of the element.*/
	protected float x;
	
	/** The y coord of the element. */
	protected float y;
	
	/**
	 * Instantiates a new element.
	 *
	 * @param body the body of the element.
	 */
	public Element(Body body)
	{
		this.body = body;
		animation = new Animation();
	}

	/**
	 * Sets the animation.
	 *
	 * @param reg the region of the texture
	 * @param delay the delay of the animation
	 */
	public void setAnimation(TextureRegion[] reg, float delay) {
		animation.setFrames(reg, delay);
		width = reg[0].getRegionWidth();
		height = reg[0].getRegionHeight();
	}
	
	/**
	 * Updates the element
	 *
	 * @param dt the time that has passed since the last update
	 */
	public void update(float dt) {
		animation.update(dt);
	}
	
	/**
	 * Render the element
	 *
	 * @param sb the sprite batch
	 */
	public void render(SpriteBatch sb) {
		sb.begin();
		
		sb.draw(animation.getFrame(), x, y, width, height);
		
		System.out.printf("%f, %f, %f, %f\n", x, y, width, height);
		
		sb.end();
	}
	
	/**
	 * Gets the body.
	 *
	 * @return the body
	 */
	public Body getBody() {
		return body;
	}
	
	/**
	 * Gets the position of the body
	 *
	 * @return the position
	 */
	public Vector2 getPosition() {
		return body.getPosition();
	}

	/**
	 * Gets the x coord of the element.
	 *
	 * @return the x
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Gets the y coord of the element.
	 *
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * Disposes the element
	 */
	public void dispose() {}
}

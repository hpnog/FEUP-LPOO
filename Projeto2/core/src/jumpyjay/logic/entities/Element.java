package jumpyjay.logic.entities;

import jumpyjay.handlers.Animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

// TODO: Auto-generated Javadoc
/**
 * The Class Element.
 */
public abstract class Element {
	
	/** The body. */
	protected Body body;
	
	/** The animation. */
	protected Animation animation;
	
	/** The width. */
	protected float width;
	
	/** The height. */
	protected float height;
	
	/** The x. */
	protected float x;
	
	/** The y. */
	protected float y;
	
	/**
	 * Instantiates a new element.
	 *
	 * @param body the body
	 */
	public Element(Body body)
	{
		this.body = body;
		animation = new Animation();
	}

	/**
	 * Sets the animation.
	 *
	 * @param reg the reg
	 * @param delay the delay
	 */
	public void setAnimation(TextureRegion[] reg, float delay) {
		animation.setFrames(reg, delay);
		width = reg[0].getRegionWidth();
		height = reg[0].getRegionHeight();
	}
	
	/**
	 * Update.
	 *
	 * @param dt the dt
	 */
	public void update(float dt) {
		animation.update(dt);
	}
	
	/**
	 * Render.
	 *
	 * @param sb the sb
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
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Vector2 getPosition() {
		return body.getPosition();
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * Dispose.
	 */
	public void dispose() {}
}

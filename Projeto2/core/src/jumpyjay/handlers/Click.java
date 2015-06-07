package jumpyjay.handlers;

import com.badlogic.gdx.Gdx;

// TODO: Auto-generated Javadoc
/**
 * The Class Click.
 */
public class Click {
	
	/** The has been clicked before. */
	boolean hasBeenClickedBefore = true;
	
	/** The has just been clicked. */
	boolean hasJustBeenClicked = true;
	
	/**
	 * Update.
	 */
	public void update()
	{
		hasBeenClickedBefore = hasJustBeenClicked;
		hasJustBeenClicked = Gdx.input.isTouched();
	}
	
	/**
	 * Got clicked.
	 *
	 * @return true, if successful
	 */
	public boolean gotClicked()
	{
		if (!hasBeenClickedBefore && hasJustBeenClicked)
			return true;
		return false;
	}
}

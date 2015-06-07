package jumpyjay.handlers;

import com.badlogic.gdx.Gdx;


/**
 * The Class Click.
 */
public class Click {
	
	/** Cheks if it has been clicked before. */
	boolean hasBeenClickedBefore = true;
	
	/** Cheks if it has just been clicked. */
	boolean hasJustBeenClicked = true;
	
	/**
	 * Updates state
	 */
	public void update()
	{
		hasBeenClickedBefore = hasJustBeenClicked;
		hasJustBeenClicked = Gdx.input.isTouched();
	}
	
	/**
	 * Checks if the screen got clicked
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

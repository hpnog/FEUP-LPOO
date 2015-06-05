package jumpyjay.handlers;

import com.badlogic.gdx.Gdx;

public class Click {
	
	boolean hasBeenClickedBefore = true;
	boolean hasJustBeenClicked = true;
	
	public void update()
	{
		hasBeenClickedBefore = hasJustBeenClicked;
		hasJustBeenClicked = Gdx.input.isTouched();
	}
	
	public boolean gotClicked()
	{
		if (!hasBeenClickedBefore && hasJustBeenClicked)
			return true;
		return false;
	}
}

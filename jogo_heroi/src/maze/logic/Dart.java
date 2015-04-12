package maze.logic;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Dart.
 */
public class Dart extends Object implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4254410647641732977L;
	
	/**   The boolean caught *. */
	private boolean caught;
	
	/**
	 * Constructor for the dart.
	 *
	 * @param x position on X-axis
	 * @param y position on Y-axis
	 */
	public Dart(int x, int y) {
		super (x, y);
		caught = false;
	}

	/**
	 * Set if the dart is caught or not.
	 *
	 * @param b the new _caught
	 */
	public void set_caught(boolean b) {
		caught = true;
	}
	
	/**
	 * Checks if is caught.
	 *
	 * @return true if the dart has been caught, false if the dart has not been caught
	 */
	public boolean isCaught() {
		return caught;
	}
}

package maze.logic;

import java.io.Serializable;

public class Dardo extends Object implements Serializable{
	private static final long serialVersionUID = -4254410647641732977L;
	private boolean caught = false;
	
	/**
	 * Constructor for the dart
	 * @param x position on X-axis
	 * @param y position on Y-axis
	 */
	public Dardo(int x, int y) {
		super (x, y);
	}

	/**
	 * Set if the dart is caught or not
	 * @param b 
	 */
	public void set_caught(boolean b) {
		caught = true;
	}
	
	/**
	 * 
	 * @return true if the dart has been caught, false if the dart has not been caught
	 */
	public boolean isCaught() {
		return caught;
	}
}

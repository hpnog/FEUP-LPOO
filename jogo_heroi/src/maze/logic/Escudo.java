package maze.logic;

import java.io.Serializable;

public class Escudo extends Object implements Serializable {
	private static final long serialVersionUID = -3755944495068306522L;
	private boolean caught;
	
	/**
	 * Constructor for the shield
	 */
	public Escudo() {
		super (1, 1);
		caught = false;
	}	
	
	/**
	 * Set if the shield has been caught or not
	 * @param a
	 */
	public void set_caught (boolean a) {
		caught = a;
	}
	
	/**
	 * 
	 * @return true if the shield has been caught, false if the shield has not been caught
	 */
	public boolean isCaught() {
		return caught;
	}
	
	
}

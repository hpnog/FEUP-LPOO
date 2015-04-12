package maze.logic;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Escudo.
 */
public class Escudo extends Object implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3755944495068306522L;
	
	/** The caught. */
	private boolean caught;
	
	/**
	 * Constructor for the shield.
	 */
	public Escudo() {
		super (1, 1);
		caught = false;
	}	
	
	/**
	 * Set if the shield has been caught or not.
	 *
	 * @param a the new _caught
	 */
	public void set_caught (boolean a) {
		caught = a;
	}
	
	/**
	 * Checks if is caught.
	 *
	 * @return true if the shield has been caught, false if the shield has not been caught
	 */
	public boolean isCaught() {
		return caught;
	}
	
	
}

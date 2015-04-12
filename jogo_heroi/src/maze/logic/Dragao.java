package maze.logic;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Dragao.
 */
public class Dragao extends Object implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3095911441768951534L;
	
	/** The alive. */
	private boolean alive;
	
	/** The status. */
	private int status;
	
	/** The spit. */
	private String spit = "";
	
	/**
	 * Constructor for the dragon.
	 *
	 * @param sleeper - status of the dragon
	 */
	public Dragao(int sleeper) {
		super(5, 5);
		alive = true;
		status = sleeper;		//0 - awake | 1 - asleep | 2 - non sleeper moving | 3 - non sleeper static
	}

	/**
	 * Checks if is alive.
	 *
	 * @return true if the dragon is alive, false if the dragon is dead
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status of the dragon
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * Set if dragon is alive or not.
	 *
	 * @param a the new alive
	 */
	public void setAlive(boolean a) {
		alive = a;
	}
	
	/**
	 * Set a different status for the dragon.
	 *
	 * @param s the new status
	 */
	public void setStatus(int s) {
		status = s;
	}

	/**
	 * Gets the spit.
	 *
	 * @return the spit
	 */
	public String getSpit() {
		return spit;
	}

	/**
	 * Sets the spit.
	 *
	 * @param spit the new spit
	 */
	public void setSpit(String spit) {
		this.spit = spit;
	}
}
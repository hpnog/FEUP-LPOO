package maze.logic;

import java.io.Serializable;

public class Dragao extends Object implements Serializable {
	private static final long serialVersionUID = -3095911441768951534L;
	private boolean alive;
	private int status;
	
	/**
	 * Constructor for the dragon
	 * @param sleeper - status of the dragon 
	 */
	public Dragao(int sleeper) {
		super(5, 5);
		alive = true;
		status = sleeper;		//0 - awake | 1 - asleep | 2 - non sleeper moving | 3 - non sleeper static
	}

	/**
	 * 
	 * @return true if the dragon is alive, false if the dragon is dead
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * 
	 * @return the status of the dragon
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * Set if dragon is alive or not
	 * @param a
	 */
	public void setAlive(boolean a) {
		alive = a;
	}
	
	/**
	 * Set a different status for the dragon
	 * @param s
	 */
	public void setStatus(int s) {
		status = s;
	}
}
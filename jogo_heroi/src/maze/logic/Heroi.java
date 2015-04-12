package maze.logic;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Heroi.
 */
public class Heroi extends Object implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 484562412430950679L;
	
	/** The armed. */
	private boolean armado;
	
	/** The darts. */
	private int dardos = 0;
	
	/** The shielded. */
	private boolean shielded = false;
	
	/**
	 * Constructor for the hero.
	 */
	public Heroi() {
		super(1, 1);
		armado = false;
	}

	/**
	 * Increments the number of darts that the hero has.
	 */
	public void inc_dardos() {
		dardos++;
	}
	
	/**
	 * Decrements the number of darts that the hero has.
	 */
	public void dec_dardos() {
		dardos--;
		
	}
	
	/**
	 * Checks if is armed.
	 *
	 * @return true if the hero is armed, false if the hero is not armed
	 */
	public boolean isArmado() {
		return armado;
	}

	/**
	 * Sets if the hero is armed or not.
	 *
	 * @param armado the new armed
	 */
	public void setArmado(boolean armado) {
		this.armado = armado;
	}
	

	/**
	 * Gets the darts.
	 *
	 * @return the number of darts that the hero has
	 */
	public int getDardos() {
		return dardos;
	}

	/**
	 * Sets how many darts the hero has.
	 *
	 * @param dardos the new darts
	 */
	public void setDardos(int dardos) {
		this.dardos = dardos;
	}
	
	/**
	 * Checks if is shielded.
	 *
	 * @return true if the hero is shielded, false if the hero is not shielded
	 */
	public boolean isShielded() {
		return shielded;
	}

	/**
	 * Sets if the hero is shielded or not.
	 *
	 * @param shielded the new shielded
	 */
	public void setShielded(boolean shielded) {
		this.shielded = shielded;
	}
	
	
}

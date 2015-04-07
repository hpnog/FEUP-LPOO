package maze.logic;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Object.
 */
public class Object implements Serializable {
	
	
	private static final long serialVersionUID = -4071368693971647608L;
	private int x_coord;
	private int y_coord;

	/**
	 * Instantiates a new object.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Object(int x, int y) {
		x_coord = x;
		y_coord = y;
	}

	/**
	 * Increments the x.
	 */
	public void x_coord_inc() {
		x_coord++;
	}
	
	/**
	 * Increments the y.
	 */
	public void y_coord_inc() {
		y_coord++;
	}
	
	/**
	 * Decrements the x.
	 */
	public void x_coord_dec() {
		x_coord--;
	}
	
	/**
	 * Decrements the y.
	 */
	public void y_coord_dec() {
		y_coord--;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x_coord;
		result = prime * result + y_coord;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(java.lang.Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Object))
			return false;
		Object other = (Object) obj;
		if (x_coord != other.x_coord)
			return false;
		if (y_coord != other.y_coord)
			return false;
		return true;
	}

	
	
	/**
	 * Gets the x_coord.
	 *
	 * @return the x_coord
	 */
	public int getX_coord() {
		return x_coord;
	}

	/**
	 * Sets the x_coord.
	 *
	 * @param x_coord the new x_coord
	 */
	public void setX_coord(int x_coord) {
		this.x_coord = x_coord;
	}

	/**
	 * Gets the y_coord.
	 *
	 * @return the y_coord
	 */
	public int getY_coord() {
		return y_coord;
	}

	/**
	 * Sets the y_coord.
	 *
	 * @param y_coord the new y_coord
	 */
	public void setY_coord(int y_coord) {
		this.y_coord = y_coord;
	}

}

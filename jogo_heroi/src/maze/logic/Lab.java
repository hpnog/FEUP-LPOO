package maze.logic;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Lab.
 */
public class Lab implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1139625071182668982L;
	
	/** The lab. */
	protected char [][] lab ;
	
	/** The size. */
	protected int size;
	
	/**
	 * Instantiates a new lab.
	 *
	 * @param s the s
	 */
	public Lab(int s) {
		size = s;
		lab = new char [size][size];
	}

	/**
	 * Gets the dragon position.
	 *
	 * @return the dragon position
	 */
	public Object getDragonPosition() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (lab[i][j] == 'D')
					return new Object(i,j);
			}
		}
		return null;
	}
	//-----------------------------------
	
	/**
	 * Gets the char.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the char
	 */
	public char getChar(int x, int y) {
		return lab[x][y];
	}
	
	/**
	 * Gets the matrix.
	 *
	 * @return the matrix
	 */
	public char[][] getMatrix() {
		return lab;
	}
	
	/**
	 * Gets the exit position.
	 *
	 * @return the exit position
	 */
	public Object getExitPosition() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (lab[i][j] == 'S')
					return new Object(i,j);
			}
		}
		return null;
	}
	
	/**
	 * Gets the hero position.
	 *
	 * @return the hero position
	 */
	public Object getHeroPosition() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (lab[i][j] == 'H' || lab[i][j] == 'A')
					return new Object(i,j);
			}
		}
		return null;
	}
	
	/**
	 * Gets the sword position.
	 *
	 * @return the sword position
	 */
	public Object getSwordPosition() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (lab[i][j] == 'E')
					return new Object(i,j);
			}
		}
		return null;
	}

	/**
	 * Gets the lab.
	 *
	 * @return the lab
	 */
	public char[][] getLab() {
		return lab;
	}
	
	/**
	 * Sets the lab.
	 *
	 * @param lab the new lab
	 */
	public void setLab(char[][] lab) {
		this.lab = lab;
	}
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * Sets the lab cell.
	 *
	 * @param x_coord the x_coord
	 * @param y_coord the y_coord
	 * @param c the c
	 */
	public void setLabCell(int x_coord, int y_coord, char c) {
		lab[x_coord][y_coord] = c;		
	}
}

package maze.logic;

import java.io.Serializable;

public class Dardo extends Object implements Serializable{
	private static final long serialVersionUID = -4254410647641732977L;
	private boolean caught = false;

	public Dardo(int x, int y) {
		super (x, y);
	}

	public void set_caught(boolean b) {
		caught = true;
	}
	public boolean isCaught() {
		return caught;
	}
}

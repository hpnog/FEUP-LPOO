package maze.logic;

import java.io.Serializable;

public class Escudo extends Object implements Serializable {
	private static final long serialVersionUID = -3755944495068306522L;
	private boolean caught;
	
	public Escudo() {
		super (1, 1);
		caught = false;
	}	
		
	public void set_caught (boolean a) {
		caught = a;
	}
	public boolean isCaught() {
		return caught;
	}
	public void setCaught(boolean caught) {
		this.caught = caught;
	}
		
}

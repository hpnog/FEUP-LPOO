package maze.logic;

import java.io.Serializable;

public class Dragao extends Object implements Serializable {
	private static final long serialVersionUID = -3095911441768951534L;
	private boolean alive;
	private int status;
	
	public Dragao(int sleeper) {
		super(5, 5);
		alive = true;
		status = sleeper;		//0 - awake | 1 - asleep | 2 - non sleeper moving | 3 - non sleeper static
	}

	public boolean isAlive() {
		return alive;
	}
	public int getStatus() {
		return status;
	}
	public void setAlive(boolean a) {
		alive = a;
	}
	public void setStatus(int s) {
		status = s;
	}
}
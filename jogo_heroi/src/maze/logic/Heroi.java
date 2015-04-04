package maze.logic;

import java.io.Serializable;

public class Heroi extends Object implements Serializable {
	private static final long serialVersionUID = 484562412430950679L;
	private boolean armado;
	private int dardos = 0;
	private boolean shielded = false;
	
	public Heroi() {
		super(1, 1);
		armado = false;
	}
	

	public boolean get_shielded() {
		return shielded;
	}
	public int get_dardos() {
		return dardos;
	}
	public boolean get_armado() {
		return armado;
	}
	public void inc_dardos() {
		dardos++;
	}
	public void dec_dardos() {
		dardos--;
		
	}

	
	public boolean isArmado() {
		return armado;
	}

	public void setArmado(boolean armado) {
		this.armado = armado;
	}

	public int getDardos() {
		return dardos;
	}

	public void setDardos(int dardos) {
		this.dardos = dardos;
	}

	public boolean isShielded() {
		return shielded;
	}

	public void setShielded(boolean shielded) {
		this.shielded = shielded;
	}
	
	
}

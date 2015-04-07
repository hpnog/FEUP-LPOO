package maze.logic;

import java.io.Serializable;

public class Lab implements Serializable {
	private static final long serialVersionUID = 1139625071182668982L;
	protected char [][] lab ;
	protected int size;
	
	public Lab(int s) {
		size = s;
		lab = new char [size][size];
	}

	//Funcao apenas para efeito de testes
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
	
	public char getChar(int x, int y) {
		return lab[x][y];
	}
	public char[][] getMatrix() {
		return lab;
	}
	public Object getExitPosition() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (lab[i][j] == 'S')
					return new Object(i,j);
			}
		}
		return null;
	}
	public Object getHeroPosition() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (lab[i][j] == 'H' || lab[i][j] == 'A')
					return new Object(i,j);
			}
		}
		return null;
	}
	public Object getSwordPosition() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (lab[i][j] == 'E')
					return new Object(i,j);
			}
		}
		return null;
	}

	public char[][] getLab() {
		return lab;
	}
	public void setLab(char[][] lab) {
		this.lab = lab;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void setLabCell(int x_coord, int y_coord, char c) {
		lab[x_coord][y_coord] = c;		
	}
}

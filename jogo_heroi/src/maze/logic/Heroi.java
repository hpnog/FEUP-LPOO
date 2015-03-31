package maze.logic;

import maze.logic.Lab;

public class Heroi extends Object {
	private boolean armado;
	private int dardos = 0;
	private boolean shielded = false;
	
	public Heroi() {
		super(1, 1);
		armado = false;
	}
	
	public void change_hero_pos() {
		if (Jogo.labirinto.lab[x_coord][y_coord] == 'O')
			shielded = true;
		if (armado)
			Jogo.labirinto.lab[x_coord][y_coord] = 'A';
		else
			Jogo.labirinto.lab[x_coord][y_coord] = 'H';	
	}
	
	public void random_start() {
		int randomX;
		int randomY;
		while (true) {
			randomX = (int)(1 + Math.random()*Lab.size);
			randomY = (int)(1 + Math.random()*Lab.size);
			randomX--;
			randomY--;
			if (Jogo.labirinto.lab[randomX][randomY] == ' ')
				break;
		}
		x_coord = randomX;
		y_coord = randomY;
		change_hero_pos();
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
	
	public void set_armado(boolean a) {
		armado = a;
	}

	public void set_shielded(boolean b) {
		shielded = b;	
	}

	public int get_x_coord() {
		return x_coord;
	}
	
	public int get_y_coord() {
		return y_coord;
	}

	public void dec_dardos() {
		dardos--;
		
	}

	public void setDards(int i) {
		dardos = i;		
	}
	
}

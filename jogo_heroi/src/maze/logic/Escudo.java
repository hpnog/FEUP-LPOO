package maze.logic;

import java.io.Serializable;

public class Escudo extends Object implements Serializable {
	boolean caught;
	
	public Escudo() {
		super (1, 1);
		caught = false;
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
		change_escudo_pos();
	}
	
	public void set_caught (boolean a) {
		caught = a;
	}
	
	public void change_escudo_pos() {
		if (!caught)
			Jogo.labirinto.lab[x_coord][y_coord] = 'O';
	}
}

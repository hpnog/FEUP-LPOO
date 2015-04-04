package maze.logic;

import java.io.Serializable;

public class Espada extends Object implements Serializable {
	
	public Espada() {
		super(1, 1);
	}
	
	public void random_sword() {
		int randomX = 1;
		int randomY = 1;
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
		change_sword_pos();
	}
	
	public void change_sword_pos() {
		Jogo.labirinto.lab[x_coord][y_coord] = 'E';
	}
}

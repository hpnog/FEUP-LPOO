package maze.logic;

import maze.logic.Lab;

public class Heroi extends Object {
	private static boolean armado;
	private static int dardos = 0;
	private static boolean shielded = false;
	
	Heroi() {
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
	

	public static boolean get_armado() {
		return armado;
	}

	public void set_armado(boolean a) {
		armado = a;
	}

	
	
}

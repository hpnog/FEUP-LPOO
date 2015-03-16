package maze.logic;

public class Escudo extends Object {
	
	Escudo() {
		super (1, 1);
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
	
	public void change_escudo_pos() {
			Jogo.labirinto.lab[x_coord][y_coord] = 'O';
	}
}

package maze.logic;

import maze.logic.Jogo;

public class Dragao extends Object {
	
	private static boolean alive;
	
	Dragao() {
		super(5, 5);
		alive = true;
	}
	
	public int check_if_dead() {
				
		if ((Jogo.abs(x_coord - Jogo.heroi.get_x_coord()) > 1) || (Jogo.abs(y_coord - Jogo.heroi.get_y_coord()) > 1) ||
				((y_coord != Jogo.heroi.get_y_coord()) && (x_coord != Jogo.heroi.get_x_coord())))
			return -1;
		if (Jogo.heroi.get_armado()) {
			if (alive) {
				System.out.println();
				System.out.println("You just killed the dragon. You can now find your way out!");
				System.out.println();
				if (x_coord != Jogo.heroi.get_x_coord())
					if (y_coord != Jogo.heroi.get_y_coord())
						Lab.lab[x_coord][y_coord] = ' ';
			}
			alive = false;
			return -1;
		}
		return 5;
	}
	public void change_dragon_pos() {
		if (Lab.lab[x_coord][y_coord] == 'E') {
			if (alive)
				Lab.lab[x_coord][y_coord] = 'F';
		}
		else if (alive)
			Lab.lab[x_coord][y_coord] = 'D';
	}
	public void movimentar_dragao() {
		int check = 0;
		while (check == 0) {
			int random = 0 + (int)(Math.random()*4);
			if (random == 0) {
				if (Lab.lab[x_coord+1][y_coord] != 'X' && Lab.lab[x_coord+1][y_coord] != 'S') {
					if (Lab.lab[x_coord][y_coord] == 'F')
						Lab.lab[x_coord][y_coord] = 'E';
					else
						Lab.lab[x_coord][y_coord] = ' ';
					x_coord++;
					change_dragon_pos();
					check = 1;
				}
			}
			else if (random == 1) {
				if (Lab.lab[x_coord-1][y_coord] != 'X' && Lab.lab[x_coord-1][y_coord] != 'S') {
					if (Lab.lab[x_coord][y_coord] == 'F')
						Lab.lab[x_coord][y_coord] = 'E';
					else
						Lab.lab[x_coord][y_coord] = ' ';
					x_coord--;
					change_dragon_pos();
					check = 1;
				}
			}
			else if (random == 2) {
				if (Lab.lab[x_coord][y_coord-1] != 'X' && Lab.lab[x_coord][y_coord-1] != 'S') {
					if (Lab.lab[x_coord][y_coord] == 'F')
						Lab.lab[x_coord][y_coord] = 'E';
					else
						Lab.lab[x_coord][y_coord] = ' ';
					y_coord--;
					change_dragon_pos();
					check = 1;
				}
			}
			else {
				if (Lab.lab[x_coord][y_coord+1] != 'X' && Lab.lab[x_coord][y_coord+1] != 'S' ) {
					if (Lab.lab[x_coord][y_coord] == 'F')
						Lab.lab[x_coord][y_coord] = 'E';
					else
						Lab.lab[x_coord][y_coord] = ' ';
					y_coord++;
					change_dragon_pos();
					check = 1;
				}
			}
		}

	}
	public void random_dragao() {
		int randomX;
		int randomY;
		while (true) {
			randomX = (int)(1 + Math.random()*Lab.size);
			randomY = (int)(1 + Math.random()*Lab.size);
			randomX--;
			randomY--;
			if (Lab.lab[randomX][randomY] == ' ')
				break;
		}
		x_coord = randomX;
		y_coord = randomY;
		change_dragon_pos();
	}

	public static boolean get_alive() {
		return alive;
	}

	
	public void set_alive(boolean a) {
		alive = a;
	}
}
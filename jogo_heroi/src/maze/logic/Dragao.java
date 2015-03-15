package maze.logic;

import maze.cli.console_interface;
import maze.logic.Jogo;

public class Dragao extends Object {

	private boolean alive;
	private int status;

	Dragao(int sleeper) {
		super(5, 5);
		alive = true;
		status = sleeper;		//0 - awake | 1 - asleep | 2 - non sleeper moving | 3 - non sleeper static
	}

	public int check_if_dead() {
		int ret;
		if ((Jogo.abs(x_coord - Jogo.heroi.get_x_coord()) > 1) || (Jogo.abs(y_coord - Jogo.heroi.get_y_coord()) > 1) ||
				((y_coord != Jogo.heroi.get_y_coord()) && (x_coord != Jogo.heroi.get_x_coord())))
			ret = -1;
		else if (Heroi.get_armado()) {
			if (alive) {
				console_interface.dragonKilled();
				if (x_coord != Jogo.heroi.get_x_coord())
					if (y_coord != Jogo.heroi.get_y_coord())
						Lab.lab[x_coord][y_coord] = ' ';
			}
			alive = false;
			ret = -1;
		}
		else if (status == 1)
			ret = -1;
		else
			ret = 5;
		change_dragon_pos();
		return ret;
	}
	public void change_dragon_pos() {
		if (alive) {
			if (Lab.lab[x_coord][y_coord] == 'E') {
				Lab.lab[x_coord][y_coord] = 'F';
			}
			else {
				if (status == 1)
					Lab.lab[x_coord][y_coord] = 'd';
				else
					Lab.lab[x_coord][y_coord] = 'D';
			}
		}
		else
			Lab.lab[x_coord][y_coord] = ' ';
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

	public boolean get_alive() {
		return alive;
	}
	public int get_status() {
		return status;
	}

	public void set_alive(boolean a) {
		alive = a;
	}

	public void change_status() {
		if (status != 0 && status != 1)
			return;
		int random = 1 + (int)(Math.random()*4);
		if (random == 1) {
			if (status == 1) {
				status = 0;
				change_dragon_pos();
			}
			else {
				status = 1;
				change_dragon_pos();
			}
		}
	}
}
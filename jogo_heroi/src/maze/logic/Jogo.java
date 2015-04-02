package maze.logic;
import maze.cli.console_interface;
import maze.gui.MazeDisplay;


public class Jogo {
	public static Dragao [] dragoes;
	public static Heroi heroi;
	public static Lab labirinto;
	public static Espada espada;
	public static Escudo escudo;
	public static Dardo [] dardos;
	public static int inter = 0;
	public static class gamePreferences {
		public static int mazeSize = 11;
		public static int numberOfDragons = 1;
		public static int type = 0;
		public static char up = 'w';
		public static char down = 's';
		public static char left = 'a';
		public static char right = 'd';
		public static char sUp = 'y';
		public static char sDown = 'h';
		public static char sLeft = 'g';
		public static char sRight = 'j';
		public static char exitKey = 'q';
	};
	
	public Jogo(int size){
		labirinto = new Lab(size);
		dragoes = new Dragao[0];
	}

	public static int abs(int i) {
		if (i < 0)
			return -i;
		return i;
	}

	public static int check_movimento(int x, int y, int choice) {
		//se for a espada		
		if (Lab.lab[heroi.get_x_coord()+x][heroi.get_y_coord()+y] == 'E') {
			Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()] = ' ';
			heroi.set_armado(true);
			if (x == -1)
				heroi.x_coord_dec();
			else if (x == 1)
				heroi.x_coord_inc();
			else if (y == -1)
				heroi.y_coord_dec();
			else
				heroi.y_coord_inc();
			heroi.change_hero_pos();
		}
		//se for um escudo
		if (Lab.lab[heroi.get_x_coord()+x][heroi.get_y_coord()+y] == 'O') {
			Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()] = ' ';
			heroi.set_shielded(true);
			escudo.set_caught(true);
			if (x == -1)
				heroi.x_coord_dec();
			else if (x == 1)
				heroi.x_coord_inc();
			else if (y == -1)
				heroi.y_coord_dec();
			else
				heroi.y_coord_inc();
			heroi.change_hero_pos();
		}
		
		//se for um dardo
		else if (Lab.lab[heroi.get_x_coord()+x][heroi.get_y_coord()+y] == '\\') {
			Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()] = ' ';
			heroi.inc_dardos();
			catch_dardo(heroi.get_x_coord()+x,heroi.get_y_coord()+y);
			if (x == -1)
				heroi.x_coord_dec();
			else if (x == 1)
				heroi.x_coord_inc();
			else if (y == -1)
				heroi.y_coord_dec();
			else
				heroi.y_coord_inc();
			heroi.change_hero_pos();
		}
		
		//se for um espaco
		else if (Lab.lab[heroi.get_x_coord()+x][heroi.get_y_coord()+y] == ' ') {
			Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()] = ' ';
			if (x == -1)
				heroi.x_coord_dec();
			else if (x == 1)
				heroi.x_coord_inc();
			else if (y == -1)
				heroi.y_coord_dec();
			else
				heroi.y_coord_inc();
			heroi.change_hero_pos();
		}
		
		//se for a saida
		else if (Lab.lab[heroi.get_x_coord()+x][heroi.get_y_coord()+y] == 'S') {
			if (checkIfDragonsAreAlive() == false) {
				if (inter == 0) console_interface.way_out();
				else if (inter == 3) MazeDisplay.way_out();
				else {}
				return 10;								//Para terminar o jogo
			}
			else {
				if (inter == 0)	console_interface.dragon_still_alive();
				else if (inter == 3) MazeDisplay.dragon_still_alive();
				else {}
				return 9;								//Esta na saida mas o jogo nao pode acabar, ha dragoes vivos no labirinto
			}
		}
		
		//se for um dragao
		else if (Lab.lab[heroi.get_x_coord()+x][heroi.get_y_coord()+y] == 'd')
			if (inter == 0) console_interface.dragon_sleeping();
			else if (inter == 3) MazeDisplay.dragon_sleeping();
			else {}
		
		//se for outra coisa/parede
		else
			if (inter == 0)	console_interface.wall();
			else if (inter == 3) MazeDisplay.wall();
			else {}
		return choice;
	}

	public static void shoot(int x, int y, int choice) {
		if (heroi.get_dardos() > 0) {
		//left
		if (x == 1) {
			int i = heroi.get_x_coord();
			while ((heroi.get_x_coord()-i) > 0 && (Lab.lab[heroi.get_x_coord()-i][heroi.get_y_coord()] != 'X')) {
				for (int j = 0; j < dragoes.length; j++) {
					if (dragoes[j].get_x_coord() == (heroi.get_x_coord()-i))
						if (dragoes[j].get_y_coord() == (heroi.get_y_coord())) {
							dragoes[j].set_alive(false);
							if (inter == 0)	console_interface.dragonKilled();
							else if (inter == 3) MazeDisplay.messageDragonKilled();
							else {}
						}
				}
				//-----------------------------------------------------------------
				i--;
			}
			if (inter == 0) console_interface.shotLeft();
			else if (inter == 3) {} //funcao de displaro
			else {}
		}
		
		//right
		else if (x == -1) {
			int i = heroi.get_x_coord();
			while ((heroi.get_x_coord()+i) < Lab.size && (Lab.lab[heroi.get_x_coord()+i][heroi.get_y_coord()] != 'X')) {
				for (int j = 0; j < dragoes.length; j++) {
					if (dragoes[j].get_x_coord() == (heroi.get_x_coord()+i))
						if (dragoes[j].get_y_coord() == (heroi.get_y_coord())) {
							dragoes[j].set_alive(false);
							if (inter == 0) console_interface.dragonKilled();
							else if (inter == 3) MazeDisplay.messageDragonKilled();
							else {}
						}
				}
				//-----------------------------------------------------------------
				i++;
			}
			if (inter == 0) console_interface.shotRight();
			else if (inter == 3) {} //funcao de displaro
			else {}
		}
		
		//up
		else if (y == 1) {
			int i = heroi.get_y_coord();
			while ((heroi.get_y_coord()-i) > 0 && (Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()-i] != 'X')) {
				for (int j = 0; j < dragoes.length; j++) {
					if (dragoes[j].get_y_coord() == (heroi.get_y_coord()-i))
						if (dragoes[j].get_x_coord() == (heroi.get_x_coord())) {
							dragoes[j].set_alive(false);
							if (inter == 0) console_interface.dragonKilled();
							else if (inter == 3) MazeDisplay.messageDragonKilled();
							else {}
						}
				}
				//-----------------------------------------------------------------
				i--;
			}
			if (inter == 0) console_interface.shotUp();
			else if (inter == 3) {} //funcao de displaro
			else {}
		}
		
		//down
		else {
			int i = heroi.get_y_coord();
			while ((i+heroi.get_y_coord()) < Lab.size && (Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()+i] != 'X')) {
				for (int j = 0; j < dragoes.length; j++) {
					if (dragoes[j].get_y_coord() == (heroi.get_y_coord()+i))
						if (dragoes[j].get_x_coord() == (heroi.get_x_coord())) {
							dragoes[j].set_alive(false);
							if (inter == 0) console_interface.dragonKilled();
							else if (inter == 3) MazeDisplay.messageDragonKilled();
							else {}
						}
				}
				//-----------------------------------------------------------------
				i++;
			}
			if (inter == 0) console_interface.shotDown();
			else if (inter == 3) {} //funcao de displaro
			else {}
		}
		heroi.dec_dardos();
		}
		else
			if (inter == 0) console_interface.no_dards();
			else if (inter == 3) MazeDisplay.noDards();
			else {}
		
	}
	
	private static void catch_dardo(int x, int y) {
		for (int i = 0; i < Lab.size / 4; i++) {
			if (dardos[i].get_x_coord() == x)
				if (dardos[i].get_y_coord() == y)
					dardos[i].set_caught(true);
		}	
	}

	public static int interpreta_opcao(int choice) {
		//3 -- left
		if (choice == 3) {
			choice = check_movimento(-1, 0, choice);
			if (choice == 10 || choice == 9)
				return choice;
			else {}
		}
		//4 -- right
		else if (choice == 4) {
			choice = check_movimento(1, 0, choice);
			if (choice == 10 || choice == 9)
				return choice;
			else {}
		}
		//1 -- up
		else if (choice == 1) {
			choice = check_movimento(0, -1, choice);
			if (choice == 10 || choice == 9)
				return choice;
			else {}
		}
		//2 -- down
		else if (choice == 2) {
			choice = check_movimento(0, 1, choice);
			if (choice == 10 || choice == 9)
				return choice;
			else {}
		}

		//101 -- shoot left
		else if (choice == 101) 
			shoot(0, 1, choice);
		//102 -- shoot left
		else if (choice == 102) 
			shoot(0, -1, choice);
		//103 -- shoot left
		else if (choice == 103) 
			shoot(-1, 0, choice);
		//104 -- shoot left
		else if (choice == 104) 
			shoot(1, 0, choice);

		if (choice == 0)
			return choice;
		return -1;
	}

	public static boolean checkIfDragonsAreAlive() {
		boolean fim = false;
		for (int i = 0; i < dragoes.length; i++) {
			if (dragoes[i].get_alive())
				fim = true;
		}
		return fim;
	}

	public static void displayDragoes() {
		for (int i = 0; i < dragoes.length; i++)
			dragoes[i].change_dragon_pos();
	}
	
	public static void moveDragoes() {
		for (int i = 0; i < dragoes.length; i++) {
			if (dragoes[i].get_status() == 0 || dragoes[i].get_status() == 2)
				dragoes[i].movimentar_dragao();
		}
	}
	
	public static int moveAndSpit_dragoes(int choice) {
		moveDragoes();
		for (int i = 0; i < dragoes.length; i++) {
			if (dragoes[i].get_alive()) {
				int choice2 = dragoes[i].random_dragao_fire(heroi);
				if (choice2 == 10) {
					if (inter == 0) console_interface.killedByFire();
					else if (inter == 3) MazeDisplay.killedByFire();
					else {}
					choice = 10;
				}
			}
		}
		return choice;
	}
	
	public static void displayDardos() {
		for (int i = 0; i < Lab.size/4; i++)
			dardos[i].change_dardo_pos();
	}
	
	public static int endOfTurn(int choice) {
		for (int i = 0; i < dragoes.length; i++) {
			if (dragoes[i].get_alive() && choice != 0) {
				choice = dragoes[i].check_if_dead(heroi);
				dragoes[i].change_status();
			}
			if (choice == 5) {
				if (inter == 0) console_interface.youDied();
				else if(inter == 3) MazeDisplay.youDied();
				else {}
				break;
			}
		}
		return choice;
	}
	
}
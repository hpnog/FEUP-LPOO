package maze.logic;
import java.io.Serializable;

import maze.cli.console_interface;
import maze.gui.MazeDisplay;


public class Jogo implements Serializable {
	private static final long serialVersionUID = 1470544749104492349L;
	private Dragao [] dragoes;
	private Heroi heroi;
	private Lab labirinto;
	private Espada espada;
	private Escudo escudo;
	private Dardo [] dardos;
	private static int inter = 0;
	private GamePreferences prefs;

	
	public static class GamePreferences {
		private static int mazeSize = 11;
		private static int numberOfDragons = 1;
		private static int type = 0;
		private static char up = 'w';
		private static char down = 's';
		private static char left = 'a';
		private static char right = 'd';
		private static char sUp = 'y';
		private static char sDown = 'h';
		private static char sLeft = 'g';
		private static char sRight = 'j';
		private static char exitKey = 'q';
		
		/**
		 * 
		 * @return size of maze
		 */
		public static int getMazeSize() {
			return mazeSize;
		}
		
		/**
		 * Sets size of maze
		 * @param mazeSize
		 */
		public static void setMazeSize(int mazeSize) {
			GamePreferences.mazeSize = mazeSize;
		}
		
		/**
		 * 
		 * @return number of dragons
		 */
		public static int getNumberOfDragons() {
			return numberOfDragons;
		}
		
		/**
		 * Sets number of dragons
		 * @param numberOfDragons
		 */
		public static void setNumberOfDragons(int numberOfDragons) {
			GamePreferences.numberOfDragons = numberOfDragons;
		}
		
		/**
		 * 
		 * @return type of dragon (awake, asleep, non sleeper moving or non sleeper static)
		 */
		public static int getType() {
			return type;
		}
		
		/**
		 * Sets the type for the dragons (awake, asleep, non sleeper moving or non sleeper static)
		 * @param type
		 */
		public static void setType(int type) {
			GamePreferences.type = type;
		}
		
		/**
		 * 
		 * @return the key that is used to move the hero up
		 */
		public static char getUp() {
			return up;
		}
		
		/**
		 * Sets the key that is used to move the hero up
		 * @param up
		 */
		public static void setUp(char up) {
			GamePreferences.up = up;
		}
		
		/**
		 * 
		 * @return the key that is used to move the hero down
		 */
		public static char getDown() {
			return down;
		}
		
		/**
		 * Sets the key that is used to move the hero down
		 * @param down
		 */
		public static void setDown(char down) {
			GamePreferences.down = down;
		}
		
		/**
		 * 
		 * @return the key that is used to move the hero left
		 */
		public static char getLeft() {
			return left;
		}
		
		/**
		 * Sets the key that is used to move the hero left
		 * @param left
		 */
		public static void setLeft(char left) {
			GamePreferences.left = left;
		}
		
		/**
		 * 
		 * @return the key that is used to move the hero right
		 */
		public static char getRight() {
			return right;
		}
		
		/**
		 * Sets the key that is used to move the hero right
		 * @param right
		 */
		public static void setRight(char right) {
			GamePreferences.right = right;
		}
		
		/**
		 * 
		 * @return the key that is used to shoot the dart up
		 */
		public static char getsUp() {
			return sUp;
		}
		
		/**
		 * Sets the key that is used to shoot the dart up
		 * @param sUp
		 */
		public static void setsUp(char sUp) {
			GamePreferences.sUp = sUp;
		}
		
		/**
		 * 
		 * @return the key that is used to shoot the dart down
		 */
		public static char getsDown() {
			return sDown;
		}
		
		/**
		 * Sets the key that is used to shoot the dart down
		 * @param sDown
		 */
		public static void setsDown(char sDown) {
			GamePreferences.sDown = sDown;
		}
		
		/**
		 * 
		 * @return the key that is used to shoot the dart left
		 */
		public static char getsLeft() {
			return sLeft;
		}
		
		/**
		 * Sets the key that is used to shoot the dart left
		 * @param sLeft
		 */
		public static void setsLeft(char sLeft) {
			GamePreferences.sLeft = sLeft;
		}
		
		/**
		 * 
		 * @return the key that is used to shoot the dart right
		 */
		public static char getsRight() {
			return sRight;
		}
		
		/**
		 * Sets the key that is used to shoot the dart right
		 * @param sRight
		 */
		public static void setsRight(char sRight) {
			GamePreferences.sRight = sRight;
		}
		
		/**
		 * 
		 * @return the key that is used to exit the game
		 */
		public static char getExitKey() {
			return exitKey;
		}
		
		/**
		 * Sets the key that is used to exit the game
		 * @param exitKey
		 */
		public static void setExitKey(char exitKey) {
			GamePreferences.exitKey = exitKey;
		}
	}
	
	/**
	 * 
	 * @param i
	 * @return the absolute value of i
	 */
	public int abs(int i) {
		if (i < 0)
			return -i;
		return i;
	}
	
	//Methods related with the gameplay itself
	
	/**
	 * 
	 * @param choice
	 * @return
	 */
	public int endOfTurn(int choice) {
		for (int i = 0; i < dragoes.length; i++) {
			if (dragoes[i].isAlive() && choice != 0) {
				choice = check_if_dead(i);
				change_status(i);
			}
			if (choice == 5) {
				if (inter == 0) 
					console_interface.youDied();
				else if(inter == 3) 
					MazeDisplay.youDied();
				else {}
				break;
			}
		}
		return choice;
	}
	public int interpreta_opcao(int choice) {
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
	private int check_if_dead(int i) {
		int ret;
		if ((abs(dragoes[i].getX_coord() - heroi.getX_coord()) > 1) || (abs(dragoes[i].getY_coord() - heroi.getY_coord()) > 1) ||
				((dragoes[i].getY_coord() != heroi.getY_coord()) && (dragoes[i].getX_coord() != heroi.getX_coord())))
			ret = -1;
		else if (heroi.isArmado()) {
			if (dragoes[i].isAlive()) {
				if (inter == 0)
					console_interface.dragonKilled();
				else if (inter == 3) 
					MazeDisplay.messageDragonKilled();
				else {}
				if (dragoes[i].getX_coord() != heroi.getX_coord())
					if (dragoes[i].getY_coord() != heroi.getY_coord())
						labirinto.setLabCell(dragoes[i].getX_coord(), dragoes[i].getY_coord(), ' ');
			}
			dragoes[i].setAlive(false);
			ret = -1;
		}
		else if (dragoes[i].getStatus() == 1)
			ret = -1;
		else
			ret = 5;
		change_dragon_pos(i);
		return ret;
	}
	private int check_movimento(int x, int y, int choice) {
		//se for a espada		
		if (labirinto.getLab()[heroi.getX_coord()+x][heroi.getY_coord()+y] == 'E') {
			labirinto.setLabCell(heroi.getX_coord(), heroi.getY_coord(), ' ');
			heroi.setArmado(true);
			if (x == -1)
				heroi.x_coord_dec();
			else if (x == 1)
				heroi.x_coord_inc();
			else if (y == -1)
				heroi.y_coord_dec();
			else
				heroi.y_coord_inc();
			change_hero_pos();
		}
		//se for um escudo
		if (labirinto.getLab()[heroi.getX_coord()+x][heroi.getY_coord()+y] == 'O') {
			labirinto.setLabCell(heroi.getX_coord(), heroi.getY_coord(), ' ');
			heroi.setShielded(true);
			escudo.set_caught(true);
			if (x == -1)
				heroi.x_coord_dec();
			else if (x == 1)
				heroi.x_coord_inc();
			else if (y == -1)
				heroi.y_coord_dec();
			else
				heroi.y_coord_inc();
			change_hero_pos();
		}

		//se for um dardo
		else if (labirinto.getLab()[heroi.getX_coord()+x][heroi.getY_coord()+y] == '\\') {
			labirinto.setLabCell(heroi.getX_coord(), heroi.getY_coord(), ' ');
			heroi.inc_dardos();
			catch_dardo(heroi.getX_coord()+x,heroi.getY_coord()+y);
			if (x == -1)
				heroi.x_coord_dec();
			else if (x == 1)
				heroi.x_coord_inc();
			else if (y == -1)
				heroi.y_coord_dec();
			else
				heroi.y_coord_inc();
			change_hero_pos();
		}

		//se for um espaco
		else if (labirinto.getLab()[heroi.getX_coord()+x][heroi.getY_coord()+y] == ' ') {
			labirinto.setLabCell(heroi.getX_coord(), heroi.getY_coord(), ' ');
			if (x == -1)
				heroi.x_coord_dec();
			else if (x == 1)
				heroi.x_coord_inc();
			else if (y == -1)
				heroi.y_coord_dec();
			else
				heroi.y_coord_inc();
			change_hero_pos();
		}

		//se for a saida
		else if (labirinto.getLab()[heroi.getX_coord()+x][heroi.getY_coord()+y] == 'S') {
			if (checkIfDragonsAreAlive() == false) {
				if (inter == 0) 
					console_interface.way_out();
				else if (inter == 3) 
					MazeDisplay.way_out();
				else {}
				return 10;								//Para terminar o jogo
			}
			else {
				if (inter == 0)	
					console_interface.dragon_still_alive();
				else if (inter == 3)
					MazeDisplay.dragon_still_alive();
				else {}
				return 9;								//Esta na saida mas o jogo nao pode acabar, ha dragoes vivos no labirinto
			}
		}

		//se for um dragao
		else if (labirinto.getLab()[heroi.getX_coord()+x][heroi.getY_coord()+y] == 'd')
			if (inter == 0) 
				console_interface.dragon_sleeping();
			else if (inter == 3) 
				MazeDisplay.dragon_sleeping();
			else {}

		//se for outra coisa/parede
		else
			if (inter == 0)	
				console_interface.wall();
			else if (inter == 3) 
				MazeDisplay.wall();
			else {}
		return choice;
	}
	
	//Methods related with dragons
	public int moveAndSpit_dragoes(int choice) {
		moveDragoes();
		for (int i = 0; i < dragoes.length; i++) {
			if (dragoes[i].isAlive()) {
				int choice2 = random_dragao_fire(dragoes[i]);
				if (choice2 == 10) {
					if (inter == 0) 
						console_interface.killedByFire();
					else if (inter == 3)
						MazeDisplay.killedByFire();
					else {}
					choice = 10;
				}
			}
		}
		return choice;
	}
	public void movimentar_dragao(int pos) {
		int check = 0;
		int counter = 0; //Ao fim de 1000 tentativas, provavelmente está encurralado e não se move
		while (check == 0 && counter < 1000) {
			int random = 1 + (int)(Math.random()*4);
			if (random == 1) {
				if (labirinto.getLab()[dragoes[pos].getX_coord()+1][dragoes[pos].getY_coord()] != 'X' && 
						labirinto.getLab()[dragoes[pos].getX_coord()+1][dragoes[pos].getY_coord()] != 'S' &&
						labirinto.getLab()[dragoes[pos].getX_coord()+1][dragoes[pos].getY_coord()] != 'D' && 
						labirinto.getLab()[dragoes[pos].getX_coord()+1][dragoes[pos].getY_coord()] != 'd' && 
						labirinto.getLab()[dragoes[pos].getX_coord()+1][dragoes[pos].getY_coord()] != 'O') {
					if (labirinto.getLab()[dragoes[pos].getX_coord()][dragoes[pos].getY_coord()] == 'F')
						labirinto.setLabCell(dragoes[pos].getX_coord(), dragoes[pos].getY_coord(), 'E');
					else
						labirinto.setLabCell(dragoes[pos].getX_coord(), dragoes[pos].getY_coord(), ' ');
					dragoes[pos].setX_coord(dragoes[pos].getX_coord()+1);
					check = 1;
				}
			}
			else if (random == 2) {
				if (labirinto.getLab()[dragoes[pos].getX_coord()-1][dragoes[pos].getY_coord()] != 'X' &&
						labirinto.getLab()[dragoes[pos].getX_coord()-1][dragoes[pos].getY_coord()] != 'S' &&
						labirinto.getLab()[dragoes[pos].getX_coord()-1][dragoes[pos].getY_coord()] != 'D' && 
						labirinto.getLab()[dragoes[pos].getX_coord()-1][dragoes[pos].getY_coord()] != 'd' && 
						labirinto.getLab()[dragoes[pos].getX_coord()-1][dragoes[pos].getY_coord()] != 'O') {
					if (labirinto.getLab()[dragoes[pos].getX_coord()][dragoes[pos].getY_coord()] == 'F')
						labirinto.setLabCell(dragoes[pos].getX_coord(), dragoes[pos].getY_coord(), 'E');
					else
						labirinto.setLabCell(dragoes[pos].getX_coord(), dragoes[pos].getY_coord(), ' ');
					dragoes[pos].setX_coord(dragoes[pos].getX_coord()-1);
					check = 1;
				}
			}
			else if (random == 3) {
				if (labirinto.getLab()[dragoes[pos].getX_coord()][dragoes[pos].getY_coord()-1] != 'X' &&
						labirinto.getLab()[dragoes[pos].getX_coord()][dragoes[pos].getY_coord()-1] != 'S' &&
						labirinto.getLab()[dragoes[pos].getX_coord()][dragoes[pos].getY_coord()-1] != 'D' && 
						labirinto.getLab()[dragoes[pos].getX_coord()][dragoes[pos].getY_coord()-1] != 'd' && 
						labirinto.getLab()[dragoes[pos].getX_coord()][dragoes[pos].getY_coord()-1] != 'O') {
					if (labirinto.getLab()[dragoes[pos].getX_coord()][dragoes[pos].getY_coord()] == 'F')
						labirinto.setLabCell(dragoes[pos].getX_coord(), dragoes[pos].getY_coord(), 'E');
					else
						labirinto.setLabCell(dragoes[pos].getX_coord(), dragoes[pos].getY_coord(), ' ');
					dragoes[pos].setY_coord(dragoes[pos].getY_coord()-1);
					check = 1;
				}
			}
			else {
				if (labirinto.getLab()[dragoes[pos].getX_coord()][dragoes[pos].getY_coord()+1] != 'X' &&
						labirinto.getLab()[dragoes[pos].getX_coord()][dragoes[pos].getY_coord()+1] != 'S' &&
						labirinto.getLab()[dragoes[pos].getX_coord()][dragoes[pos].getY_coord()+1] != 'D' && 
						labirinto.getLab()[dragoes[pos].getX_coord()][dragoes[pos].getY_coord()+1] != 'd' && 
						labirinto.getLab()[dragoes[pos].getX_coord()][dragoes[pos].getY_coord()+1] != 'O') {
					if (labirinto.getLab()[dragoes[pos].getX_coord()][dragoes[pos].getY_coord()] == 'F')
						labirinto.setLabCell(dragoes[pos].getX_coord(), dragoes[pos].getY_coord(), 'E');
					else
						labirinto.setLabCell(dragoes[pos].getX_coord(), dragoes[pos].getY_coord(), ' ');
					dragoes[pos].setY_coord(dragoes[pos].getY_coord()+1);
					check = 1;
				}
			}
			counter++;
		}
	}
	public void setDragao(int i, Dragao dragao) {
		dragoes[i] = dragao;
	}
	private int random_dragao_fire(Dragao dragao) {
		int spit = (int)(1 + Math.random()*5);		
		if (spit == 1) {
			int spitX = (int)(1 + Math.random()*2);
			spitX--;			
			int spitY = 0;
			if (spitX != 0) {
				spitY = (int)(1 + Math.random()*2);
				spitY--;
			}			
			return spit_fire (spitX, spitY, dragao);
		}
		return 0;
	}
	public void change_dragon_pos(int i) {
		if (dragoes[i].isAlive()) {
			if (labirinto.getLab()[dragoes[i].getX_coord()][dragoes[i].getY_coord()] == 'E') {
				labirinto.setLabCell(dragoes[i].getX_coord(), dragoes[i].getY_coord(), 'F');
			}
			else {
				if (dragoes[i].getStatus() == 1)
					labirinto.setLabCell(dragoes[i].getX_coord(), dragoes[i].getY_coord(), 'd');
				else
					labirinto.setLabCell(dragoes[i].getX_coord(), dragoes[i].getY_coord(), 'D');
			}
		}
		else {
			if (labirinto.getLab()[dragoes[i].getX_coord()][dragoes[i].getY_coord()] == 'd' || 
					labirinto.getLab()[dragoes[i].getX_coord()][dragoes[i].getY_coord()] == 'D')
				labirinto.setLabCell(dragoes[i].getX_coord(), dragoes[i].getY_coord(), ' ');
		}
	}
	private int spit_fire(int x, int y, Dragao dragao) {

		int count = 1;
		if (y == 1) {
			if (heroi.getY_coord() == dragao.getY_coord()) {
				while (count < 4) {
					if (heroi.getX_coord() == (dragao.getX_coord()+count)) {
						if (!heroi.isShielded())
							return 10;
						else if (labirinto.getLab()[dragao.getX_coord()+count][dragao.getY_coord()] == 'X')
							return 0;
					}
					count++;
				}
			}
		}
		else if (y == -1) {
			if (heroi.getY_coord() == dragao.getY_coord()) {
				while (count < 4) {
					if (heroi.getX_coord() == (dragao.getX_coord()-count)) {
						if (!heroi.isShielded())
							return 10;
						else if (labirinto.getLab()[dragao.getX_coord()-count][dragao.getY_coord()] == 'X')
							return 0;
					}
					count++;
				}
			}
		}
		else if (x == 1) {
			if (heroi.getX_coord() == dragao.getX_coord()) {
				while (count < 4) {
					if ((heroi.getY_coord()+count) == dragao.getY_coord()) {
						if (!heroi.isShielded())
							return 10;
						else if (labirinto.getLab()[dragao.getX_coord()][dragao.getY_coord()+count] == 'X')
							return 0;
					}
					count++;
				}
			}
		}
		else {
			if (heroi.getX_coord() == dragao.getX_coord()) {
				while (count < 4) {
					if ((heroi.getY_coord()-count) == dragao.getY_coord()) {
						if (!heroi.isShielded())
							return 10;
						else if (labirinto.getLab()[dragao.getX_coord()][dragao.getY_coord()-count] == 'X')
							return 0;
					}
					count++;
				}
			}
		}
		return 0;
	}
	public void moveDragoes() {
		for (int i = 0; i < dragoes.length; i++) {
			if (dragoes[i].getStatus() == 0 || dragoes[i].getStatus() == 2)
				movimentar_dragao(i);
		}
	}
	public void random_dragao(int pos) {
		int randomX = 1;
		int randomY = 1;
		while (true) {
			randomX = (int)(1 + Math.random()*labirinto.getSize());
			randomY = (int)(1 + Math.random()*labirinto.getSize());
			randomX--;
			randomY--;
			if (labirinto.getLab()[randomX][randomY] == ' ')
				break;
		}
		dragoes[pos].setX_coord(randomX);
		dragoes[pos].setY_coord(randomY);
		change_dragon_pos(pos);
	}
	public void change_status(int pos) {
		if (dragoes[pos].getStatus() != 0 && dragoes[pos].getStatus() != 1)
			return;
		int random = 1 + (int)(Math.random()*4);
		if (random == 1) {
			if (dragoes[pos].getStatus() == 1) {
				dragoes[pos].setStatus(0);
				change_dragon_pos(pos);
			}
			else {
				dragoes[pos].setStatus(1);
				change_dragon_pos(pos);
			}
		}
	}
	public void displayDragoes() {
		for (int i = 0; i < dragoes.length; i++)
			change_dragon_pos(i);
	}
	public boolean checkIfDragonsAreAlive() {
		boolean fim = false;
		for (int i = 0; i < dragoes.length; i++) {
			if (dragoes[i].isAlive())
				fim = true;
		}
		return fim;
	}
	
	//Getters and Setters
	public Dragao[] getDragoes() {
		return dragoes;
	}
	public void setDragoes(Dragao[] dragoes) {
		this.dragoes = dragoes;
	}
	public Heroi getHeroi() {
		return heroi;
	}
	public void setHeroi(Heroi heroi) {
		this.heroi = heroi;
	}
	public Lab getLabirinto() {
		return labirinto;
	}
	public void setLabirinto(Lab labirinto) {
		this.labirinto = labirinto;
	}
	public Espada getEspada() {
		return espada;
	}
	public void setEspada(Espada espada) {
		this.espada = espada;
	}
	public Escudo getEscudo() {
		return escudo;
	}
	public void setEscudo(Escudo escudo) {
		this.escudo = escudo;
	}
	public Dardo[] getDardos() {
		return dardos;
	}
	public void setDardos(Dardo[] dardos) {
		this.dardos = dardos;
	}
	public static int getInter() {
		return inter;
	}
	public void setInter(int inter) {
		this.inter = inter;
	}
	public GamePreferences getPrefs() {
		return prefs;
	}
	public void setPrefs(GamePreferences prefs) {
		this.prefs = prefs;
	}
	
	//Methods related with dards
	private void shoot(int x, int y, int choice) {
		if (heroi.getDardos() > 0) {
			//left
			if (x == 1) {
				int i = heroi.getX_coord();
				while ((heroi.getX_coord()-i) > 0 && (labirinto.getLab()[heroi.getX_coord()-i][heroi.getY_coord()] != 'X')) {
					for (int j = 0; j < dragoes.length; j++) {
						if (dragoes[j].getX_coord() == (heroi.getX_coord()-i))
							if (dragoes[j].getY_coord() == (heroi.getY_coord())) {
								dragoes[j].setAlive(false);
								if (inter == 0)	
									console_interface.dragonKilled();
								else if (inter == 3) 
									MazeDisplay.messageDragonKilled();
								else {}
							}
					}
					//-----------------------------------------------------------------
					i--;
				}
				if (inter == 0)
					console_interface.shotLeft();
				else if (inter == 3) {} //funcao de displaro
				else {}
			}

			//right
			else if (x == -1) {
				int i = heroi.getX_coord();
				while ((heroi.getX_coord()+i) < labirinto.getSize() && (labirinto.getLab()[heroi.getX_coord()+i][heroi.getY_coord()] != 'X')) {
					for (int j = 0; j < dragoes.length; j++) {
						if (dragoes[j].getX_coord() == (heroi.getX_coord()+i))
							if (dragoes[j].getY_coord() == (heroi.getY_coord())) {
								dragoes[j].setAlive(false);
								if (inter == 0) 
									console_interface.dragonKilled();
								else if (inter == 3) 
									MazeDisplay.messageDragonKilled();
								else {}
							}
					}
					//-----------------------------------------------------------------
					i++;
				}
				if (inter == 0)
					console_interface.shotRight();
				else if (inter == 3) {} //funcao de displaro
				else {}
			}

			//up
			else if (y == 1) {
				int i = heroi.getY_coord();
				while ((heroi.getY_coord()-i) > 0 && (labirinto.getLab()[heroi.getX_coord()][heroi.getY_coord()-i] != 'X')) {
					for (int j = 0; j < dragoes.length; j++) {
						if (dragoes[j].getY_coord() == (heroi.getY_coord()-i))
							if (dragoes[j].getX_coord() == (heroi.getX_coord())) {
								dragoes[j].setAlive(false);
								if (inter == 0) console_interface.dragonKilled();
								else if (inter == 3) MazeDisplay.messageDragonKilled();
								else {}
							}
					}
					//-----------------------------------------------------------------
					i--;
				}
				if (inter == 0)
					console_interface.shotUp();
				else if (inter == 3) {} //funcao de displaro
				else {}
			}

			//down
			else {
				int i = heroi.getY_coord();
				while ((i+heroi.getY_coord()) < labirinto.getSize() && (labirinto.getLab()[heroi.getX_coord()][heroi.getY_coord()+i] != 'X')) {
					for (int j = 0; j < dragoes.length; j++) {
						if (dragoes[j].getY_coord() == (heroi.getY_coord()+i))
							if (dragoes[j].getX_coord() == (heroi.getX_coord())) {
								dragoes[j].setAlive(false);
								if (inter == 0) console_interface.dragonKilled();
								else if (inter == 3) MazeDisplay.messageDragonKilled();
								else {}
							}
					}
					//-----------------------------------------------------------------
					i++;
				}
				if (inter == 0) 
					console_interface.shotDown();
				else if (inter == 3) {} //funcao de displaro
				else {}
			}
			heroi.dec_dardos();
		}
		else
			if (inter == 0)
				console_interface.no_dards();
			else if (inter == 3) MazeDisplay.noDards();
			else {}

	}
	public void setDard(int i, Dardo dardo) {
		dardos[i] = dardo;
	}
	public void random_dardo(int pos) {
		int randomX;
		int randomY;
		while (true) {
			randomX = (int)(1 + Math.random()*labirinto.getSize());
			randomY = (int)(1 + Math.random()*labirinto.getSize());
			randomX--;
			randomY--;
			if (labirinto.getLab()[randomX][randomY] == ' ')
				break;
		}
		dardos[pos].setX_coord(randomX);
		dardos[pos].setY_coord(randomY);
		change_dardo_pos(pos);
	}
	public void change_dardo_pos(int pos) {
		if (!dardos[pos].isCaught()) {
			if (labirinto.getLab()[dardos[pos].getX_coord()][dardos[pos].getY_coord()] == ' ' && !dardos[pos].isCaught())
				labirinto.setLabCell(dardos[pos].getX_coord(), dardos[pos].getY_coord(), '\\');
		}
	}
	public void displayDardos() {
		for (int i = 0; i < labirinto.getSize()/4; i++)
			change_dardo_pos(i);
	}
	private void catch_dardo(int x, int y) {
		for (int i = 0; i < labirinto.getSize() / 4; i++) {
			if (dardos[i].getX_coord() == x)
				if (dardos[i].getY_coord() == y)
					dardos[i].set_caught(true);
		}	
	}

	//Methods related with the Shield
	public void shield_random_start() {
		int randomX;
		int randomY;
		while (true) {
			randomX = (int)(1 + Math.random()*labirinto.getSize());
			randomY = (int)(1 + Math.random()*labirinto.getSize());
			randomX--;
			randomY--;
			if (labirinto.getLab()[randomX][randomY] == ' ')
				break;
		}
		escudo.setX_coord(randomX); 
		escudo.setY_coord(randomY);
		change_escudo_pos();
	}
	public void change_escudo_pos() {
		if (!escudo.isCaught())
			labirinto.lab[escudo.getX_coord()][escudo.getY_coord()] = 'O';
	}

	//Methods related with the Sword
	public void random_sword() {
		int randomX = 1;
		int randomY = 1;
		while (true) {
			randomX = (int)(1 + Math.random()*labirinto.getSize());
			randomY = (int)(1 + Math.random()*labirinto.getSize());
			randomX--;
			randomY--;
			if (labirinto.getLab()[randomX][randomY] == ' ')
				break;
		}
		espada.setX_coord(randomX);
		espada.setY_coord(randomY);
		change_sword_pos();
	}
	public void change_sword_pos() {
		labirinto.setLabCell(espada.getX_coord(), espada.getY_coord(), 'E');
	}

	//Methods related with the Hero
	public void change_hero_pos() {
		if (labirinto.getLab()[heroi.getX_coord()][heroi.getY_coord()] == 'O')
			heroi.setShielded(true);
		if (heroi.isArmado())
			labirinto.setLabCell(heroi.getX_coord(), heroi.getY_coord(), 'A');
		else
			labirinto.setLabCell(heroi.getX_coord(), heroi.getY_coord(), 'H');
	}
	public void random_hero_start() {
		int randomX;
		int randomY;
		while (true) {
			randomX = (int)(1 + Math.random()*labirinto.getSize());
			randomY = (int)(1 + Math.random()*labirinto.getSize());
			randomX--;
			randomY--;
			if (labirinto.getLab()[randomX][randomY] == ' ')
				break;
		}
		heroi.setX_coord(randomX);
		heroi.setY_coord(randomY);
		change_hero_pos();
	}
}
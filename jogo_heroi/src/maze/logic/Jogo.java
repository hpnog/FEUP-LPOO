package maze.logic;
import java.io.Serializable;

import maze.cli.console_interface;
import maze.gui.MazeDisplay;


// TODO: Auto-generated Javadoc
/**
 * The Class Jogo.
 */
public class Jogo implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1470544749104492349L;
	
	/** The dragoes. */
	private Dragao [] dragoes;
	
	/** The heroi. */
	private Heroi heroi;
	
	/** The labirinto. */
	private Lab labirinto;
	
	/** The espada. */
	private Espada espada;
	
	/** The escudo. */
	private Escudo escudo;
	
	/** The dardos. */
	private Dardo [] dardos;
	
	/** The inter. */
	private static int inter = 0;
	
	/** The prefs. */
	private GamePreferences prefs;

	
	/**
	 * The Class GamePreferences.
	 */
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
		 * Gets the maze size.
		 *
		 * @return size of maze
		 */
		public static int getMazeSize() {
			return mazeSize;
		}
		
		/**
		 * Sets size of maze.
		 *
		 * @param mazeSize the new maze size
		 */
		public static void setMazeSize(int mazeSize) {
			GamePreferences.mazeSize = mazeSize;
		}
		
		/**
		 * Gets the number of dragons.
		 *
		 * @return number of dragons
		 */
		public static int getNumberOfDragons() {
			return numberOfDragons;
		}
		
		/**
		 * Sets number of dragons.
		 *
		 * @param numberOfDragons the new number of dragons
		 */
		public static void setNumberOfDragons(int numberOfDragons) {
			GamePreferences.numberOfDragons = numberOfDragons;
		}
		
		/**
		 * Gets the type.
		 *
		 * @return type of dragon (awake, asleep, non sleeper moving or non sleeper static)
		 */
		public static int getType() {
			return type;
		}
		
		/**
		 * Sets the type for the dragons (awake, asleep, non sleeper moving or non sleeper static).
		 *
		 * @param type the new type
		 */
		public static void setType(int type) {
			GamePreferences.type = type;
		}
		
		/**
		 * Gets the up.
		 *
		 * @return the key that is used to move the hero up
		 */
		public static char getUp() {
			return up;
		}
		
		/**
		 * Sets the key that is used to move the hero up.
		 *
		 * @param up the new up
		 */
		public static void setUp(char up) {
			GamePreferences.up = up;
		}
		
		/**
		 * Gets the down.
		 *
		 * @return the key that is used to move the hero down
		 */
		public static char getDown() {
			return down;
		}
		
		/**
		 * Sets the key that is used to move the hero down.
		 *
		 * @param down the new down
		 */
		public static void setDown(char down) {
			GamePreferences.down = down;
		}
		
		/**
		 * Gets the left.
		 *
		 * @return the key that is used to move the hero left
		 */
		public static char getLeft() {
			return left;
		}
		
		/**
		 * Sets the key that is used to move the hero left.
		 *
		 * @param left the new left
		 */
		public static void setLeft(char left) {
			GamePreferences.left = left;
		}
		
		/**
		 * Gets the right.
		 *
		 * @return the key that is used to move the hero right
		 */
		public static char getRight() {
			return right;
		}
		
		/**
		 * Sets the key that is used to move the hero right.
		 *
		 * @param right the new right
		 */
		public static void setRight(char right) {
			GamePreferences.right = right;
		}
		
		/**
		 * Gets the s up.
		 *
		 * @return the key that is used to shoot the dart up
		 */
		public static char getsUp() {
			return sUp;
		}
		
		/**
		 * Sets the key that is used to shoot the dart up.
		 *
		 * @param sUp the new s up
		 */
		public static void setsUp(char sUp) {
			GamePreferences.sUp = sUp;
		}
		
		/**
		 * Gets the s down.
		 *
		 * @return the key that is used to shoot the dart down
		 */
		public static char getsDown() {
			return sDown;
		}
		
		/**
		 * Sets the key that is used to shoot the dart down.
		 *
		 * @param sDown the new s down
		 */
		public static void setsDown(char sDown) {
			GamePreferences.sDown = sDown;
		}
		
		/**
		 * Gets the s left.
		 *
		 * @return the key that is used to shoot the dart left
		 */
		public static char getsLeft() {
			return sLeft;
		}
		
		/**
		 * Sets the key that is used to shoot the dart left.
		 *
		 * @param sLeft the new s left
		 */
		public static void setsLeft(char sLeft) {
			GamePreferences.sLeft = sLeft;
		}
		
		/**
		 * Gets the s right.
		 *
		 * @return the key that is used to shoot the dart right
		 */
		public static char getsRight() {
			return sRight;
		}
		
		/**
		 * Sets the key that is used to shoot the dart right.
		 *
		 * @param sRight the new s right
		 */
		public static void setsRight(char sRight) {
			GamePreferences.sRight = sRight;
		}
		
		/**
		 * Gets the exit key.
		 *
		 * @return the key that is used to exit the game
		 */
		public static char getExitKey() {
			return exitKey;
		}
		
		/**
		 * Sets the key that is used to exit the game.
		 *
		 * @param exitKey the new exit key
		 */
		public static void setExitKey(char exitKey) {
			GamePreferences.exitKey = exitKey;
		}
	}
	
	/**
	 * Abs.
	 *
	 * @param i the i
	 * @return the absolute value of i
	 */
	public int abs(int i) {
		if (i < 0)
			return -i;
		return i;
	}
	
	//Methods related with the gameplay itself
	
	/**
	 * Verifies if the dragon and the hero are alive or dead after the movement.
	 *
	 * @param choice the choice
	 * @return the status of the hero (dead or alive)
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
	
	/**
	 * Checks the option that the player selected (the key) 
	 *
	 * @param choice the choice
	 * @return what is going to happen after the choice that the player chose
	 */
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
	
	/**
	 * Verifies if the dragon dies after the hero moves
	 *
	 * @param i the i
	 * @return the int
	 */
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
	
	/**
	 * Checks what to do depending on the objects around the hero
	 *
	 * @param x the x
	 * @param y the y
	 * @param choice the choice
	 * @return the int
	 */
	private int check_movimento(int x, int y, int choice) {
		//if sword	
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
		//if shield
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

		//if dart
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

		//if blank space
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

		//if exit
		else if (labirinto.getLab()[heroi.getX_coord()+x][heroi.getY_coord()+y] == 'S') {
			if (checkIfDragonsAreAlive() == false) {
				if (inter == 0) 
					console_interface.way_out();
				else if (inter == 3) 
					MazeDisplay.way_out();
				else {}
				return 10;								//finish the game
			}
			else {
				if (inter == 0)	
					console_interface.dragon_still_alive();
				else if (inter == 3)
					MazeDisplay.dragon_still_alive();
				else {}
				return 9;								//there are dragons alive in the maze therefore the hero cannot exit 
			}
		}

		//if dragon
		else if (labirinto.getLab()[heroi.getX_coord()+x][heroi.getY_coord()+y] == 'd')
			if (inter == 0) 
				console_interface.dragon_sleeping();
			else if (inter == 3) 
				MazeDisplay.dragon_sleeping();
			else {}

		//else/if wall
		else
			if (inter == 0)	
				console_interface.wall();
			else if (inter == 3) 
				MazeDisplay.wall();
			else {}
		return choice;
	}
	
	//Methods related with dragons
	
	/**
	 * Does the random movement of the dragons and if they spit fire or not
	 *
	 * @param choice the choice
	 * @return the int
	 */
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
	
	/**
	 * Moves the dragon 
	 *
	 * @param pos the position
	 */
	public void movimentar_dragao(int pos) {
		int check = 0;
		int counter = 0; //After 1000 tries it means that the dragon is trapped and cant move either way
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
	
	/**
	 * Sets the dragon number i
	 *
	 * @param i the i
	 * @param dragao the dragon
	 */
	public void setDragao(int i, Dragao dragao) {
		dragoes[i] = dragao;
	}
	
	/**
	 * Chooses randomly when the dragon spits fire
	 *
	 * @param dragao the dragon
	 * @return the int
	 */
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
	
	/**
	 * Changes the position of the dragon number i
	 *
	 * @param i the 
	 */
	private void change_dragon_pos(int i) {
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
	
	/**
	 * Makes the dragon spit fire 
	 *
	 * @param x the x
	 * @param y the y
	 * @param dragao the dragon
	 * @return the int
	 */
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
	
	/**
	 * Moves all the dragons
	 */
	public void moveDragoes() {
		for (int i = 0; i < dragoes.length; i++) {
			if (dragoes[i].getStatus() == 0 || dragoes[i].getStatus() == 2)
				movimentar_dragao(i);
		}
	}
	
	/**
	 * Sets a random position for the dragons
	 *
	 * @param pos the position
	 */
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
	
	/**
	 * Change the status of the dragons
	 *
	 * @param pos the position
	 */
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
	
	/**
	 * Display the dragons
	 */
	public void displayDragoes() {
		for (int i = 0; i < dragoes.length; i++)
			change_dragon_pos(i);
	}
	
	/**
	 * Check if dragons are alive.
	 *
	 * @return true, if successful
	 */
	public boolean checkIfDragonsAreAlive() {
		boolean fim = false;
		for (int i = 0; i < dragoes.length; i++) {
			if (dragoes[i].isAlive())
				fim = true;
		}
		return fim;
	}
	
	//Getters and Setters
	/**
	 * Gets the dragons
	 *
	 * @return the dragons
	 */
	public Dragao[] getDragoes() {
		return dragoes;
	}
	
	/**
	 * Sets the dragons
	 *
	 * @param dragoes the new dragons
	 */
	public void setDragoes(Dragao[] dragoes) {
		this.dragoes = dragoes;
	}
	
	/**
	 * Gets the hero
	 *
	 * @return the hero
	 */
	public Heroi getHeroi() {
		return heroi;
	}
	
	/**
	 * Sets the hero
	 *
	 * @param heroi the new hero
	 */
	public void setHeroi(Heroi heroi) {
		this.heroi = heroi;
	}
	
	/**
	 * Gets the maze.
	 *
	 * @return the maze
	 */
	public Lab getLabirinto() {
		return labirinto;
	}
	
	/**
	 * Sets the maze.
	 *
	 * @param labirinto the new maze
	 */
	public void setLabirinto(Lab labirinto) {
		this.labirinto = labirinto;
	}
	
	/**
	 * Gets the sword.
	 *
	 * @return the sword
	 */
	public Espada getEspada() {
		return espada;
	}
	
	/**
	 * Sets the sword.
	 *
	 * @param espada the new sword
	 */
	public void setEspada(Espada espada) {
		this.espada = espada;
	}
	
	/**
	 * Gets the shield.
	 *
	 * @return the shield
	 */
	public Escudo getEscudo() {
		return escudo;
	}
	
	/**
	 * Sets the shield.
	 *
	 * @param escudo the new shield
	 */
	public void setEscudo(Escudo escudo) {
		this.escudo = escudo;
	}
	
	/**
	 * Gets the darts.
	 *
	 * @return the darts
	 */
	public Dardo[] getDardos() {
		return dardos;
	}
	
	/**
	 * Sets the darts.
	 *
	 * @param dardos the new darts
	 */
	public void setDardos(Dardo[] dardos) {
		this.dardos = dardos;
	}
	
	/**
	 * Gets the interaction
	 *
	 * @return the interaction
	 */
	public static int getInter() {
		return inter;
	}
	
	/**
	 * Sets the interaction
	 *
	 * @param inter the new interaction
	 */
	public void setInter(int inter) {
		this.inter = inter;
	}
	
	/**
	 * Gets the game preferences
	 *
	 * @return the game preferences
	 */
	public GamePreferences getPrefs() {
		return prefs;
	}
	
	/**
	 * Sets the game preferences.
	 *
	 * @param prefs the new game preferences
	 */
	public void setPrefs(GamePreferences prefs) {
		this.prefs = prefs;
	}
	
	//Methods related with dards
	/**
	 * Shoot the darts
	 *
	 * @param x the x
	 * @param y the y
	 * @param choice the choice
	 */
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
				else if (inter == 3) {} //shooting function
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
				else if (inter == 3) {} //shooting function
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
				else if (inter == 3) {} //shooting function
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
				else if (inter == 3) {} //shooting function
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
	
	/**
	 * Sets the dart.
	 *
	 * @param i the i
	 * @param dardo the dart
	 */
	public void setDard(int i, Dardo dardo) {
		dardos[i] = dardo;
	}
	
	/**
	 * Random position of the dart.
	 *
	 * @param pos the position
	 */
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
	
	/**
	 * Changes dart position.
	 *
	 * @param pos the position
	 */
	public void change_dardo_pos(int pos) {
		if (!dardos[pos].isCaught()) {
			if (labirinto.getLab()[dardos[pos].getX_coord()][dardos[pos].getY_coord()] == ' ' && !dardos[pos].isCaught())
				labirinto.setLabCell(dardos[pos].getX_coord(), dardos[pos].getY_coord(), '\\');
		}
	}
	
	/**
	 * Display the darts.
	 */
	public void displayDardos() {
		for (int i = 0; i < labirinto.getSize()/4; i++)
			change_dardo_pos(i);
	}
	
	/**
	 * Catches the darts.
	 *
	 * @param x the x
	 * @param y the y
	 */
	private void catch_dardo(int x, int y) {
		for (int i = 0; i < labirinto.getSize() / 4; i++) {
			if (dardos[i].getX_coord() == x)
				if (dardos[i].getY_coord() == y)
					dardos[i].set_caught(true);
		}	
	}

	//Methods related with the Shield
	/**
	 * Sets the random start of the shield.
	 */
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
	
	/**
	 * Updates the position of the shield (if the hero catches it).
	 */
	public void change_escudo_pos() {
		if (!escudo.isCaught())
			labirinto.lab[escudo.getX_coord()][escudo.getY_coord()] = 'O';
	}

	//Methods related with the Sword
	/**
	 * Sets the random start of the sword.
	 */
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
	
	/**
	 * Updates the position of the sword (if the hero catches it).
	 */
	public void change_sword_pos() {
		labirinto.setLabCell(espada.getX_coord(), espada.getY_coord(), 'E');
	}

	//Methods related with the Hero
	/**
	 * Updates the position of the hero and if he has a sword or a shield, or both.
	 */
	public void change_hero_pos() {
		if (labirinto.getLab()[heroi.getX_coord()][heroi.getY_coord()] == 'O')
			heroi.setShielded(true);
		if (heroi.isArmado())
			labirinto.setLabCell(heroi.getX_coord(), heroi.getY_coord(), 'A');
		else
			labirinto.setLabCell(heroi.getX_coord(), heroi.getY_coord(), 'H');
	}
	
	/**
	 * Sets the random start of the hero.
	 */
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
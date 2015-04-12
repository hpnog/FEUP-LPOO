package maze.cli;

import java.util.Scanner;

import maze.logic.Dart;
import maze.logic.Default_maze;
import maze.logic.Dragao;
import maze.logic.Escudo;
import maze.logic.Espada;
import maze.logic.Heroi;
import maze.logic.Lab;
import maze.logic.Jogo;
import maze.logic.Random_generator;

// TODO: Auto-generated Javadoc
/**
 * The Class console_interface.
 */
@SuppressWarnings("resource")
public class console_interface {
	
	/** The game c. */
	private static Jogo jogoC;
	
	/**
	 * Prints lab.
	 *
	 * @param lab the lab
	 */
	public static void imprimir_lab(Lab lab) {
		if (Jogo.getInter() == 0) {
			for (int i = 0; i < lab.getSize(); i++) {
				for (int j = 0; j < lab.getSize(); j++) {
					System.out.print(lab.getLab()[j][i]);
					System.out.print(' ');
				}
				System.out.println();
			}
		} 
	}
	
	/**
	 * Gets the movement.
	 *
	 * @return the _movimento
	 */
	public static int get_movimento() {
		int choice = -1;
		Scanner cin = null;
		cin = new Scanner(System.in);
		String choice_str;
		choice_str = cin.nextLine();
		if (choice_str.equals("w"))
			choice = 1;
		else if (choice_str.equals("a"))
			choice = 3;
		else if (choice_str.equals("d"))
			choice = 4;
		else if (choice_str.equals("s"))
			choice = 2;
		else if (choice_str.equals("0"))
			choice = 0;
		else if (choice_str.equals("y"))
			choice = 101;
		else if (choice_str.equals("h"))
			choice = 102;
		else if (choice_str.equals("g"))
			choice = 104;
		else if (choice_str.equals("j"))
			choice = 103;
		 
		return choice;
	}
	
	/**
	 * Print options.
	 */
	public static void print_options() {
		if (Jogo.getInter() == 0) {

			System.out.println();
			System.out.println("Play with the following controls:");
			System.out.println("0 - Exit");
			System.out.println("w - up");
			System.out.println("s - down");
			System.out.println("a - left");
			System.out.println("d - right");
			System.out.println();
			System.out.println("Shoot darts:");
			System.out.println("y - up");
			System.out.println("h - down");
			System.out.println("g - left");
			System.out.println("j - right");
			System.out.println();
		}
	}
	
	/**
	 * When the hero finds the exit and everything is ok.
	 */
	public static void way_out() {
		if (Jogo.getInter() == 0) {

			System.out.println();
			System.out.println("Congratulations, you just found your way out!");
		}
	}
	
	/**
	 * When the hero finds the exit but the dragon is alive.
	 */
	public static void dragon_still_alive() {
		if (Jogo.getInter() == 0) {

			System.out.println();
			System.out.println("You cannot leave while dragon is alive!");
			System.out.println();
		}
	}
	
	/**
	 * When the hero goes against a wall.
	 */
	public static void wall() {
		if (Jogo.getInter() == 0) {

			System.out.println();
			System.out.println("Sorry, you can't go through the wall.");
			System.out.println();
		}
	}
	
	/**
	 * Choose if wants a random maze or default.
	 *
	 * @return the int
	 */
	public static int choose_maze() {
		if (Jogo.getInter() == 0) {

			System.out.println("Do you want a random maze?");
			System.out.println("Y/N");

			Scanner cin = null;
			cin = new Scanner(System.in);
			String choice_str;
			choice_str = cin.nextLine();
			if (choice_str.equals("Y")) {
				System.out.println("Choose an odd number for the side of the maze.");
				System.out.println("In the case of you choosing an even number, it will be chosen then number plus one.");

				int number = cin.nextInt();
				if (number % 2 == 0)
					number++;
				 
				return number;
			}
			else {
				return -1;
			}
		}
		
		return 0;
	}
	
	/**
	 * When the hero goes against a sleeping dragon.
	 */
	public static void dragon_sleeping() {
		if (Jogo.getInter() == 0) {

			System.out.printf("\n\nCannot go over sleeping dragon!\n\n");
		}
	}
	
	/**
	 * Asks how many dragons.
	 *
	 * @return the int
	 */
	public static int askHowManyDragons() {
		if (Jogo.getInter() == 0) {

			int num = -1;

			Scanner cin = null;
			cin = new Scanner(System.in);
			while (num > 10 || num < 0) {
				System.out.printf("\n\nHow many Dragons do you wish to play with?\n\n");
				
				num = cin.nextInt();
				
			}
			 
			return num;
		}
		return 0;
	}
	
	/**
	 * Asks type of dragon.
	 *
	 * @return the int
	 */
	public static int askTypeOfDragon() {
		if (Jogo.getInter() == 0) {

			int num = -1;

			Scanner cin = null;

			while (num > 3 || num < 0) {
				System.out.printf("\n\nWhat type do you want the dragons to be?\n"
						+ "0 - awake\n"
						+ "1 - asleep\n"
						+ "2 - non sleeper moving\n"
						+ "3 - non sleeper static\n\n");
				cin = new Scanner(System.in);
				num = cin.nextInt();
				 
			}
			return num;
		}
		return 0;
	}
	
	/**
	 * When the hero kills the dragon.
	 */
	public static void dragonKilled() {
		if (Jogo.getInter() == 0) {

			System.out.println();
			System.out.println("You just killed the dragon. You can now find your way out!");
			System.out.println();
		}
	}
	
	/**
	 * Prints hero status
	 *
	 * @param heroi the hero
	 */
	public static void imprimir_heroi_status(Heroi heroi) {
		if (Jogo.getInter() == 0) {


			if (heroi.isShielded())
				System.out.printf("\n\nHeroi - protegido");
			else
				System.out.print("\n\nHeroi - não protegido");
			System.out.printf("\nNumero de dardos - %d\n\n", heroi.getDardos());
		}
	}
	
	/**
	 * When the hero has no darts
	 */
	public static void no_darts() {
		if (Jogo.getInter() == 0) {

			System.out.printf("\n\nYou have no darts.\n\n");
		}
	}
	
	/**
	 * Shot left.
	 */
	public static void shotLeft() {
		if (Jogo.getInter() == 0)
			System.out.printf("\nYou shot left.\n");
	}
	
	/**
	 * Shot right.
	 */
	public static void shotRight() {
		if (Jogo.getInter() == 0)
			System.out.printf("\nYou shot right.\n");
	}
	
	/**
	 * Shot up.
	 */
	public static void shotUp() {
		if (Jogo.getInter() == 0)
			System.out.printf("\nYou shot up.\n");
	}
	
	/**
	 * Shot down.
	 */
	public static void shotDown() {
		if (Jogo.getInter() == 0)
			System.out.printf("\nYou shot down.\n");
	}
	
	/**
	 * Killed by fire.
	 */
	public static void killedByFire() {
		if (Jogo.getInter() == 0) {
			System.out.println();
			System.out.println("You just died. Killed by fire. Game Over!");
			System.out.println();
		}
	}
	
	/**
	 * You died.
	 */
	public static void youDied() {
		if (Jogo.getInter() == 0) {
			System.out.println();
			System.out.println("You just died. Game Over!");
			System.out.println();
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		//----------pergunta-qual-o-labirinto.labirinto--------------
		int choice = choose_maze();
		if (choice == -1) {
			jogoC = new Jogo();
			jogoC.setLabirinto(new Default_maze(10));
		}
		else {
			jogoC = new Jogo();
			jogoC.setLabirinto(new Random_generator(choice));
		}
		jogar();
	}
	
	/**
	 * Main function that initializes the game
	 */
	public static void jogar() {
		
		//--------------Beggining of the game--------------------

		int dragonNumber = askHowManyDragons();
		jogoC.setDragoes(new Dragao[dragonNumber]);
		int typeOfDragon = askTypeOfDragon();
		for (int i = 0; i < dragonNumber; i++)
			jogoC.setDragao(i, new Dragao(typeOfDragon));
		jogoC.setHeroi(new Heroi());
		jogoC.setEspada(new Espada());
		jogoC.setEscudo(new Escudo());
		jogoC.setDardos(new Dart [dragonNumber]);

		jogoC.random_sword();
		jogoC.random_hero_start();
		jogoC.shield_random_start();
		
		for (int i = 0; i < jogoC.getDardos().length; i++) {
			jogoC.setDart(i, new Dart(1, 1));
			jogoC.random_dardo(i);
		}
		
		for (int i = 0; i < dragonNumber; i++)
			jogoC.random_dragao(i);
		
		movimentar_heroi();
	}
	
	/**
	 * Moves hero
	 */
	public static void movimentar_heroi() {

		print_options();

		int choice = -1;
 
		while ((choice != 5) && (choice != 0) && choice != 10) {
			jogoC.change_sword_pos();
			jogoC.change_escudo_pos();
			jogoC.displayDragoes();
			imprimir_lab(jogoC.getLabirinto());
			imprimir_heroi_status(jogoC.getHeroi());
			choice = get_movimento();
			choice = jogoC.moveAndSpit_dragoes(choice);
			if (choice == 10)
				return;
			choice = jogoC.interpreta_opcao(choice);
			jogoC.displayDardos();
			choice = jogoC.endOfTurn(choice);
		}
	}
	
	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	public Jogo getJogo() {
		return jogoC;
	}
	
	/**
	 * Sets the game.
	 *
	 * @param jogo the new game
	 */
	public void setJogo(Jogo jogo) {
		console_interface.jogoC = jogo;
	}
}
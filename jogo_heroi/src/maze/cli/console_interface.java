package maze.cli;

import java.util.Scanner;

import maze.logic.Dardo;
import maze.logic.Default_maze;
import maze.logic.Dragao;
import maze.logic.Escudo;
import maze.logic.Espada;
import maze.logic.Heroi;
import maze.logic.Lab;
import maze.logic.Jogo;
import maze.logic.Random_generator;

public class console_interface {
	public static Jogo jogo;
	
	public static void imprimir_lab() {
		if (Jogo.inter == 0) {
			for (int i = 0; i < Lab.size; i++) {
				for (int j = 0; j < Lab.size; j++) {
					System.out.print(Lab.lab[j][i]);
					System.out.print(' ');
				}
				System.out.println();
			}
		}
	}
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
	public static void print_options() {
		if (Jogo.inter == 0) {

			System.out.println();
			System.out.println("Play with the following controls:");
			System.out.println("0 - Exit");
			System.out.println("w - up");
			System.out.println("s - down");
			System.out.println("a - left");
			System.out.println("d - right");
			System.out.println();
			System.out.println("Shoot dards:");
			System.out.println("y - up");
			System.out.println("h - down");
			System.out.println("g - left");
			System.out.println("j - right");
			System.out.println();
		}
	}
	public static void way_out() {
		if (Jogo.inter == 0) {

			System.out.println();
			System.out.println("Congratulations, you just found your way out!");
		}
	}
	public static void dragon_still_alive() {
		if (Jogo.inter == 0) {

			System.out.println();
			System.out.println("You cannot leave while dragon is alive!");
			System.out.println();
		}
	}
	public static void wall() {
		if (Jogo.inter == 0) {

			System.out.println();
			System.out.println("Sorry, you can't go through the wall.");
			System.out.println();
		}
	}
	public static int choose_maze() {
		if (Jogo.inter == 0) {

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
			else
				return -1;
		}
		return 0;
	}
	public static void dragon_sleeping() {
		if (Jogo.inter == 0) {

			System.out.printf("\n\nCannot go over sleeping dragon!\n\n");
		}
	}
	public static int askHowManyDragons() {
		if (Jogo.inter == 0) {

			int num = -1;

			Scanner cin = null;

			while (num > 10 || num < 0) {
				System.out.printf("\n\nHow many Dragons do you wish to play with?\n\n");
				cin = new Scanner(System.in);
				num = cin.nextInt();
			}
			return num;
		}
		return 0;
	}
	public static int askTypeOfDragon() {
		if (Jogo.inter == 0) {

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
	public static void dragonKilled() {
		if (Jogo.inter == 0) {

			System.out.println();
			System.out.println("You just killed the dragon. You can now find your way out!");
			System.out.println();
		}
	}
	public static void imprimir_heroi_status(Heroi heroi) {
		if (Jogo.inter == 0) {


			if (heroi.get_shielded())
				System.out.printf("\n\nHeroi - protegido");
			else
				System.out.print("\n\nHeroi - não protegido");
			System.out.printf("\nNumero de dardos - %d\n\n", heroi.get_dardos());
		}
	}
	public static void no_dards() {
		if (Jogo.inter == 0) {

			System.out.printf("\n\nYou have no dards.\n\n");
		}
	}
	public static void shotLeft() {
		if (Jogo.inter == 0)
			System.out.printf("\nYou shot left.\n");
	}
	public static void shotRight() {
		if (Jogo.inter == 0)
			System.out.printf("\nYou shot right.\n");
	}
	public static void shotUp() {
		if (Jogo.inter == 0)
			System.out.printf("\nYou shot up.\n");
	}
	public static void shotDown() {
		if (Jogo.inter == 0)
			System.out.printf("\nYou shot down.\n");
	}
	public static void killedByFire() {
		if (Jogo.inter == 0) {
			System.out.println();
			System.out.println("You just died. Killed by fire. Game Over!");
			System.out.println();
		}
	}
	public static void youDied() {
		if (Jogo.inter == 0) {
			System.out.println();
			System.out.println("You just died. Game Over!");
			System.out.println();
		}
	}

	public static void main(String[] args) {
		//----------pergunta-qual-o-labirinto.labirinto--------------
		int choice = choose_maze();
		if (choice == -1) {
			jogo = new Jogo(10);
			Default_maze maze = new Default_maze(10);
		}
		else {
			jogo = new Jogo(choice);
			Random_generator maze = new Random_generator(choice);
		}
		jogar();
	}
	public static void jogar() {
		
		//--------------Inicio-do-jogo---------------------

		int dragonNumber = askHowManyDragons();
		Jogo.dragoes = new Dragao[dragonNumber];
		int typeOfDragon = askTypeOfDragon();
		for (int i = 0; i < dragonNumber; i++)
			Jogo.dragoes[i] = new Dragao(typeOfDragon);
		Jogo.heroi = new Heroi();
		Jogo.espada = new Espada();
		Jogo.escudo = new Escudo();
		Jogo.dardos = new Dardo [Lab.size / 4];

		Jogo.espada.random_sword();
		Jogo.heroi.random_start();
		Jogo.escudo.random_start();
		
		for (int i = 0; i < Lab.size / 4; i++) {
			Jogo.dardos[i] = new Dardo(1, 1);
			Jogo.dardos[i].random_dardo();
		}
		
		for (int i = 0; i < dragonNumber; i++)
			Jogo.dragoes[i].random_dragao();
		
		movimentar_heroi();
	}
	public static void movimentar_heroi() {

		print_options();

		int choice = -1;

		while ((choice != 5) && (choice != 0) && choice != 10) {
			Jogo.displayDragoes();
			imprimir_lab();
			imprimir_heroi_status(Jogo.heroi);
			choice = get_movimento();
			choice = Jogo.moveAndSpit_dragoes(choice);
			if (choice == 10)
				return;
			choice = Jogo.interpreta_opcao(choice);
			Jogo.displayDardos();
			choice = Jogo.endOfTurn(choice);
		}
	}
}


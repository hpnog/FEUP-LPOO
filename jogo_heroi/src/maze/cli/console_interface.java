package maze.cli;

import java.util.Scanner;

import maze.logic.Lab;

public class console_interface {
	public static void imprimir_lab() {
		for (int i = 0; i < Lab.size; i++) {
			for (int j = 0; j < Lab.size; j++) {
				System.out.print(Lab.lab[j][i]);
				System.out.print('|');
			}
			System.out.println();
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
		return choice;
	}
	public static void print_options() {
		System.out.println();
		System.out.println("Play with the following controls:");
		System.out.println("0 - Exit");
		System.out.println("w - up");
		System.out.println("s - down");
		System.out.println("a - left");
		System.out.println("d - right");
		System.out.println();
	}
	public static void way_out() {
		System.out.println();
		System.out.println("Congratulations, you just found your way out!");
	}
	public static void dragon_still_alive() {
		System.out.println();
		System.out.println("You cannot leave while dragon is alive!");
		System.out.println();
	}
	public static void wall() {
		System.out.println();
		System.out.println("Sorry, you can't go through the wall.");
		System.out.println();
	}
	public static int choose_maze() {
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
	public static void dragon_sleeping() {
		System.out.printf("\n\nCannot go over sleeping dragon!\n\n");
	}
	public static int askHowManyDragons() {
		int num = -1;
		
		Scanner cin = null;
		
		while (num > 10 || num < 0) {
			System.out.printf("\n\nHow many Dragons do you wish to play with?\n\n");
			cin = new Scanner(System.in);
			num = cin.nextInt();
		}
		return num;
	}
	public static int askTypeOfDragon() {
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
	public static void dragonKilled() {
		System.out.println();
		System.out.println("You just killed the dragon. You can now find your way out!");
		System.out.println();
	}
}


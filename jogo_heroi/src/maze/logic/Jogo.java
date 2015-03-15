package maze.logic;
import maze.cli.console_interface;


public class Jogo {
	public static Dragao [] dragoes;
	public static Heroi heroi;
	public static Lab labirinto;
	public static Espada espada;
	
	public Jogo(int size){
		labirinto = new Lab(size);
	}

	public static int abs(int i) {
		if (i < 0)
			return -i;
		return i;
	}

	public static int interpreta_opcao(int choice) {
		//3 -- left
		if (choice == 3) {
			if (Lab.lab[heroi.get_x_coord()-1][heroi.get_y_coord()] != 'X' &&
					Lab.lab[heroi.get_x_coord()-1][heroi.get_y_coord()] != 'S' &&
					Lab.lab[heroi.get_x_coord()-1][heroi.get_y_coord()] != 'd') {
				if (Lab.lab[heroi.get_x_coord()-1][heroi.get_y_coord()] == 'E')
					heroi.set_armado(true);
				Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()] = ' ';
				heroi.x_coord_dec();
				heroi.change_hero_pos();
			}
			else if (Lab.lab[heroi.get_x_coord()-1][heroi.get_y_coord()] == 'S') {
				if (checkIfDragonsAreAlive() == false) {
					console_interface.way_out();
					choice = 10;
				}
				else {
					console_interface.dragon_still_alive();
				}
			}
			else if (Lab.lab[heroi.get_x_coord()-1][heroi.get_y_coord()] == 'd')
				console_interface.dragon_sleeping();
			else {
				console_interface.wall();
			}
		}
		//4 -- right
		else if (choice == 4) {
			if (Lab.lab[heroi.get_x_coord()+1][heroi.get_y_coord()] == 'E')
				heroi.set_armado(true);
			if (Lab.lab[heroi.get_x_coord()+1][heroi.get_y_coord()] == ' ' || Lab.lab[heroi.get_x_coord()+1][heroi.get_y_coord()] == 'E') {
				Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()] = ' ';
				heroi.x_coord_inc();
				heroi.change_hero_pos();
			}
			else if (Lab.lab[heroi.get_x_coord()+1][heroi.get_y_coord()] == 'S') {
				if (checkIfDragonsAreAlive() == false) {
					console_interface.way_out();
					choice = 10;
				}
				else {
					console_interface.dragon_still_alive();
				}
			}
			else if (Lab.lab[heroi.get_x_coord()+1][heroi.get_y_coord()] == 'd')
				console_interface.dragon_sleeping();
			else {
				console_interface.wall();
			}
		}
		//1 -- up
		else if (choice == 1) {
			if (Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()-1] != 'X' &&
					Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()-1] != 'S' &&
					Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()-1] != 'd') {
				if (Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()-1] == 'E')
					heroi.set_armado(true);
				Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()] = ' ';
				heroi.y_coord_dec();
				heroi.change_hero_pos();
			}
			else if (Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()-1] == 'S') {
				if (checkIfDragonsAreAlive() == false) {
					console_interface.way_out();
					choice = 10;
				}
				else {
					console_interface.dragon_still_alive();}
			}
			else if (Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()-1] == 'd')
				console_interface.dragon_sleeping();
			else {		
				console_interface.wall();
			}
		}
		//2 -- down
		else if (choice == 2) {
			if (Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()+1] != 'X' && 
					Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()+1] != 'S' &&
					Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()+1] != 'd') {
				if (Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()+1] == 'E')
					heroi.set_armado(true);
				Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()] = ' ';
				heroi.y_coord_inc();
				heroi.change_hero_pos();
			}
			else if (Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()+1] == 'S') {
				if (checkIfDragonsAreAlive() == false) {
					console_interface.way_out();
					choice = 10;
				}
				else {
					console_interface.dragon_still_alive();
				}
			}
			else if (Lab.lab[heroi.get_x_coord()][heroi.get_y_coord()+1] == 'd')
				console_interface.dragon_sleeping();
			else {
				console_interface.wall();
			}
		}
		return choice;
	}

	private static boolean checkIfDragonsAreAlive() {
		boolean fim = false;
		for (int i = 0; i < dragoes.length; i++) {
			if (dragoes[i].get_alive())
				fim = true;
		}
		return fim;
	}

	public static void movimentar_heroi() {

		console_interface.print_options();

		int choice = -1;

		while ((choice != 5) && (choice != 0) && choice != 10) {

			console_interface.imprimir_lab();

			choice = console_interface.get_movimento();

			for (int i = 0; i < dragoes.length; i++) {
				if (dragoes[i].get_status() == 0 || dragoes[i].get_status() == 2)
					dragoes[i].movimentar_dragao();
				dragoes[i].change_dragon_pos();
			}

			choice = interpreta_opcao(choice);

			for (int i = 0; i < dragoes.length; i++) {
				if (dragoes[i].get_alive() && choice != 0) {
					choice = dragoes[i].check_if_dead();
					dragoes[i].change_status();
				}
				if (choice == 5) {
					System.out.println();
					System.out.println("You just died. Game Over!");
					System.out.println();
					break;
				}
			}

		}
		
	}

	public static void jogar() {
		
		//--------------Inicio-do-jogo---------------------

		int dragonNumber = console_interface.askHowManyDragons();
		dragoes = new Dragao[dragonNumber];
		int typeOfDragon = console_interface.askTypeOfDragon();
		for (int i = 0; i < dragonNumber; i++)
			dragoes[i] = new Dragao(typeOfDragon);
		heroi = new Heroi();
		espada = new Espada();

		espada.random_sword();
		heroi.random_start();
		for (int i = 0; i < dragonNumber; i++)
			dragoes[i].random_dragao();
		movimentar_heroi();
	}

	public static void main(String[] args) {
		Jogo jogo;
		//----------pergunta-qual-o-labirinto.labirinto--------------
		int choice = console_interface.choose_maze();
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
}
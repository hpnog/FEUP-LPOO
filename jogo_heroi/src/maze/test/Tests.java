package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import maze.logic.Dragao;
import maze.logic.Escudo;
import maze.logic.Espada;
import maze.logic.Heroi;
import maze.logic.Jogo;
import maze.logic.Lab;
import maze.logic.Default_maze;
import maze.logic.Object;
import maze.logic.Random_generator;


public class Tests {

	Object tester_1 = new Object(3,3);
	Object tester_2 = new Object(3,2);
	Object tester_3 = new Object(2,3);

	/**
	 * Repetidamente gera uma instância do tipo T usando a função geradora, e verifica se a instância
	 * gerada obedece a um dos predicados (funções de T em Boolean). No caso de não obedecer a nenhum, 
	 * falha, mostrando a mensagem gerada pela função errorMessage (de T em String).
	 * Repete até cada teste ter sucedido pelo menos uma vez, num mínimo de numIter iterações.
	 * @param generator - gera uma instância (função de () em T);
	 * @param errorMessage - gera uma mensagem em caso de erro (função de T em String);
	 * @param predicates - lista de predicados de teste (funções de T em Boolean).
	 */
	@SafeVarargs
	public final <T> void testAlt(int minIter, 
			Supplier<T> generator, Function<T, String> errorMessage, Predicate<T> ... predicates) {
		boolean [] tested = new boolean[predicates.length];
		int checked = 0;
		for (int iter = 0; iter < minIter && checked < predicates.length; iter++ ) {
			T x = generator.get();
			boolean found = false;
			for (int i = 0; i < predicates.length; i++)
				if (predicates[i].test(x)) {
					found = true;
					if (!tested[i]) {
						checked++;
						tested[i] = true;
					}
				}
			if (! found)		
				fail(errorMessage.apply(x));
			iter++;
		}
	}

	/* a) the maze boundaries must have exactly one exit and everything else walls
	// b) the exist cannot be a corner*/
	public boolean checkBoundaries(Lab m) {
		int countExit = 0;
		int n = m.getSize();
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (i == 0 || j == 0 || i == n - 1 || j == n - 1)
					if (m.getChar(i, j) == 'S')
						if ((i == 0 || i == n-1) && (j == 0 || j == n-1))
							return false;
						else
							countExit++;
					else if (m.getChar(i, j) != 'X')
						return false;
		return countExit == 1;
	}

	/* d) there cannot exist 2x2 (or greater) squares with blanks only 
	// e) there cannot exit 2x2 (or greater) squares with blanks in one diagonal and walls in the other
	// d) there cannot exist 3x3 (or greater) squares with walls only*/
	public boolean hasSquare(Lab maze, char[][] square) {
		char [][] m = maze.getMatrix();
		for (int i = 0; i < m.length - square.length; i++)
			for (int j = 0; j < m.length - square.length; j++) {
				boolean match = true;
				for (int x = 0; x < square.length; x++)
					for (int y = 0; y < square.length; y++) {
						if (m[i+x][j+y] != square[x][y])
							match = false;
					}
				if (match)
					return true;
			}		
		return false; 
	}

	// c) there must exist a path between any blank cell and the maze exit 
	public boolean checkExitReachable(Lab maze) {
		Object p = maze.getExitPosition();
		char [][] m = deepClone(maze.getMatrix());
		visit(m, p.get_x_coord(), p.get_y_coord());

		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m.length; j++) {				
				if (m[i][j] != 'X' && m[i][j] != 'V') {

					return false;
				}
			}

		return true; 
	}

	/* auxiliary method used by checkExitReachable
	// marks a cell as visited (V) and proceeds recursively to its neighbors*/
	public void visit(char[][] m, int i, int j) {
		if (i < 0 || i >= m.length || j < 0 || j >= m.length)
			return;
		if (m[i][j] == 'X' || m[i][j] == 'V')
			return;
		m[i][j] = 'V';
		visit(m, i-1, j);
		visit(m, i+1, j);
		visit(m, i, j-1);
		visit(m, i, j+1);
	}

	/* Auxiliary method used by checkExitReachable.
	// Gets a deep clone of a char matrix.*/
	public char[][] deepClone(char[][] m) {
		char[][] c = m.clone();
		for (int i = 0; i < m.length; i++)
			c[i] = m[i].clone();
		return c;
	}

	// Checks if all the arguments (in the variable arguments list) are not null and distinct
	public <T> boolean notNullAndDistinct(T ... args) {
		for (int i = 0; i < args.length - 1; i++)
			for (int j = i + 1; j < args.length ; j++)
				if (args[i] == null || args[j] == null || args[i].equals(args[j]))
					return false;
		return true;
	}

	//----------------------------------------------------------TESTS----------------------------------------------------------
	@Test(timeout=1000)
	public void testRandomDragon() {
		char [][] m1 = {
				{'X', 'X', 'X', 'X', 'X'},
				{'X', 'H', ' ', ' ', 'X'},
				{'X', ' ', 'X', ' ', 'S'},
				{'X', ' ', ' ', 'D', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};

		testAlt(1000,
				() -> {
					Lab maze = new Default_maze(10);
					maze.size = 5;
					maze.lab = m1;

					Heroi heroi = new Heroi();
					heroi.set_x_coord(1);
					heroi.set_y_coord(1);

					Dragao dragao = new Dragao(2);
					dragao.set_x_coord(3);
					dragao.set_y_coord(3);

					heroi.set_x_coord(heroi.get_x_coord()+1);
					heroi.change_hero_pos();

					return maze;
				},
				(m) -> "Dragão em posição inválida: " + m, 
				(m) -> m.getDragonPosition().equals(tester_1), 
				(m) -> m.getDragonPosition().equals(tester_2), 
				(m) -> m.getDragonPosition().equals(tester_3)); 
	}
	@Test(timeout=5000)
	public void testRandomMazeGenerator() throws Exception {
		int numMazes = 1000;
		int maxSize = 101; // can change to any odd number >= 5

		Random_generator builder = new Random_generator(5);
		char[][] badWalls = {
				{'X', 'X', 'X'},
				{'X', 'X', 'X'},
				{'X', 'X', 'X'}};
		char[][] badSpaces = {
				{' ', ' '},
				{' ', ' '}};
		char[][] badDiag1 = {
				{'X', ' '},
				{' ', 'X'}};
		char[][] badDiag2 = {
				{' ', 'X'},
				{'X', ' '}};
		Random rand = new Random(); 
		for (int i = 0; i < numMazes; i++) {
			int size = maxSize == 5? 5 : 5 + 2 * rand.nextInt((maxSize - 5)/2);
			Lab m = new Random_generator(size);

			Heroi heroi = new Heroi();
			Dragao dragao = new Dragao(2);
			Espada espada = new Espada();
			heroi.random_start();
			dragao.random_dragao();
			espada.random_sword();

			assertTrue("Invalid maze boundaries in maze:\n" + m, checkBoundaries(m));
			assertTrue("Maze exit not reachable in maze:\n" + m, checkExitReachable(m));
			assertNotNull("Invalid walls in maze:\n" + m, ! hasSquare(m, badWalls));
			assertNotNull("Invalid spaces in maze:\n" + m, ! hasSquare(m, badSpaces));
			assertNotNull("Invalid diagonals in maze:\n" + m, ! hasSquare(m, badDiag1));
			assertNotNull("Invalid diagonals in maze:\n" + m, ! hasSquare(m, badDiag2));
			assertTrue("Missing or overlapping objects in maze:\n" + m, 
					notNullAndDistinct(m.getExitPosition(), m.getHeroPosition(),
							m.getDragonPosition(), m.getSpadePosition()));			
		}	
	}
	@Test(timeout=5000)
	public void testHeroMovement() {
		for (int testNum = 0; testNum < 100; testNum++) {
			int size = 5 + (int)(Math.random()*(100));
			if ((size % 2) == 0)
				size++;
			Jogo jogo = new Jogo(size);
			jogo.inter = 1;
			jogo.labirinto = new Random_generator(size);
			jogo.heroi = new Heroi();
			jogo.heroi.random_start();
			for (int j = 0; j < 20; j++) {
				int choice = 1 + (int)(Math.random()*4);
				int actual_x_pos = jogo.heroi.get_x_coord();
				int actual_y_pos = jogo.heroi.get_y_coord();
				if (choice == 1) {
					if (jogo.labirinto.lab[jogo.heroi.get_x_coord()][jogo.heroi.get_y_coord()-1] == ' ') {
						jogo.interpreta_opcao(choice);
						assertEquals(actual_y_pos, jogo.heroi.get_y_coord()+1);
					}
					else {
						jogo.interpreta_opcao(choice);
						assertEquals(actual_y_pos, jogo.heroi.get_y_coord());
					}
					assertEquals(actual_x_pos, jogo.heroi.get_x_coord());
				}
				else if (choice == 2) {
					if (jogo.labirinto.lab[jogo.heroi.get_x_coord()][jogo.heroi.get_y_coord()+1] == ' ') {
						jogo.interpreta_opcao(choice);
						assertEquals(actual_y_pos, jogo.heroi.get_y_coord()-1);
					}
					else {
						jogo.interpreta_opcao(choice);
						assertEquals(actual_y_pos, jogo.heroi.get_y_coord());
					}
					assertEquals(actual_x_pos, jogo.heroi.get_x_coord());
				}
				else if (choice == 3) {
					if (jogo.labirinto.lab[jogo.heroi.get_x_coord()-1][jogo.heroi.get_y_coord()] == ' ') {
						jogo.interpreta_opcao(choice);
						assertEquals(actual_x_pos, jogo.heroi.get_x_coord()+1);
					}
					else {
						jogo.interpreta_opcao(choice);
						assertEquals(actual_x_pos, jogo.heroi.get_x_coord());
					}
					assertEquals(actual_y_pos, jogo.heroi.get_y_coord());
				}
				else {
					if (jogo.labirinto.lab[jogo.heroi.get_x_coord()+1][jogo.heroi.get_y_coord()] == ' ') {
						jogo.interpreta_opcao(choice);
						assertEquals(actual_x_pos, jogo.heroi.get_x_coord()-1);
					}
					else {
						jogo.interpreta_opcao(choice);
						assertEquals(actual_x_pos, jogo.heroi.get_x_coord());
					}
					assertEquals(actual_y_pos, jogo.heroi.get_y_coord());
				}

			}

		}
	}
	@Test(timeout=5000)
	public void testCatchesSword() {
		for (int testNum = 0; testNum < 1000; testNum++) {
			int size = 5 + (int)(Math.random()*15);
			if ((size % 2) == 0)
				size++;
			Jogo jogo = new Jogo(size);
			jogo.inter = 1;
			jogo.labirinto = new Random_generator(size);
			jogo.heroi = new Heroi();
			jogo.espada = new Espada();
			jogo.espada.random_sword();
			jogo.heroi.random_start();

			assertTrue(!jogo.heroi.get_armado());
			for (int j = 0; j < 20; j++) {
				int choice = 1 + (int)(Math.random()*4);
				int actual_x_pos = jogo.heroi.get_x_coord();
				int actual_y_pos = jogo.heroi.get_y_coord();
				if (choice == 1) {
					if (jogo.labirinto.lab[jogo.heroi.get_x_coord()][jogo.heroi.get_y_coord()-1] == 'E') {
						jogo.interpreta_opcao(choice);
						assertTrue(jogo.heroi.get_armado());
						break;
					}
					else
						jogo.interpreta_opcao(choice);
				}
				else if (choice == 2) {
					if (jogo.labirinto.lab[jogo.heroi.get_x_coord()][jogo.heroi.get_y_coord()+1] == 'E') {
						jogo.interpreta_opcao(choice);
						assertTrue(jogo.heroi.get_armado());
						break;
					}
					else
						jogo.interpreta_opcao(choice);
				}
				else if (choice == 3) {
					if (jogo.labirinto.lab[jogo.heroi.get_x_coord()-1][jogo.heroi.get_y_coord()] == 'E') {
						jogo.interpreta_opcao(choice);
						assertTrue(jogo.heroi.get_armado());
						break;
					}
					else
						jogo.interpreta_opcao(choice);
				}
				else {
					if (jogo.labirinto.lab[jogo.heroi.get_x_coord()+1][jogo.heroi.get_y_coord()] == 'E') {
						jogo.interpreta_opcao(choice);
						assertTrue(jogo.heroi.get_armado());
						break;
					}
					else
						jogo.interpreta_opcao(choice);
				}

			}

		}
	}
	@Test(timeout=5000)
	public void testIfDragonKills() {
		for (int testNum = 0; testNum < 1000; testNum++) {

			int size = 21 + (int)(Math.random()*31);
			if ((size % 2) == 0)
				size++;

			Jogo jogo = new Jogo(size);
			jogo.inter = 1;
			jogo.labirinto = new Random_generator(size);
			jogo.heroi = new Heroi();
			jogo.heroi.random_start();
			jogo.dragoes = new Dragao [10];
			for (int i = 0; i < jogo.dragoes.length; i++) {
				jogo.dragoes[i] = new Dragao(2);
				jogo.dragoes[i].random_dragao();
			}
						
			int choice = -1;
			for (int j = 0; j < 20 && choice != 5; j++) {
				choice = 1 + (int)(Math.random()*4);

				for (int i = 0; i < jogo.dragoes.length; i++)
					jogo.dragoes[i].change_dragon_pos();

				for (int i = 0; i < jogo.dragoes.length; i++) {
					if (jogo.dragoes[i].get_status() == 0 || jogo.dragoes[i].get_status() == 2)
						jogo.dragoes[i].movimentar_dragao();
				}

				jogo.interpreta_opcao(choice);

				for (int i = 0; i < jogo.dragoes.length; i++) {
					if (jogo.dragoes[i].get_alive() && choice != 0) {
						choice = jogo.dragoes[i].check_if_dead(jogo.heroi);
						jogo.dragoes[i].change_status();
					}

				}

			}

			assertTrue(choice == 5 || choice == -1);
		}
		
	}
	@Test(timeout=5000)
	public void testIfHeroKills() {
		for (int testNum = 0; testNum < 1000; testNum++) {

			int size = 21 + (int)(Math.random()*31);
			if ((size % 2) == 0)
				size++;

			Jogo jogo = new Jogo(size);
			jogo.inter = 1;
			jogo.labirinto = new Random_generator(size);
			jogo.heroi = new Heroi();
			jogo.heroi.set_armado(true);
			jogo.heroi.random_start();
			jogo.dragoes = new Dragao [10];
			for (int i = 0; i < jogo.dragoes.length; i++) {
				jogo.dragoes[i] = new Dragao(2);
				jogo.dragoes[i].random_dragao();
			}
						
			int choice = -1;
			for (int j = 0; j < 20 && choice != 5; j++) {
				choice = 1 + (int)(Math.random()*4);

				for (int i = 0; i < jogo.dragoes.length; i++)
					jogo.dragoes[i].change_dragon_pos();

				for (int i = 0; i < jogo.dragoes.length; i++) {
					if (jogo.dragoes[i].get_status() == 0 || jogo.dragoes[i].get_status() == 2)
						jogo.dragoes[i].movimentar_dragao();
				}

				jogo.interpreta_opcao(choice);

				for (int i = 0; i < jogo.dragoes.length; i++) {
					if (jogo.dragoes[i].get_alive() && choice != 0) {
						choice = jogo.dragoes[i].check_if_dead(jogo.heroi);
						jogo.dragoes[i].change_status();
					}

				}

			}

			assertTrue(choice == -1);
		}
	}
	@Test(timeout=5000)
	public void testIfKillsDragonAndFindsExit() {
		int winCounter = 0; //Muit provavel que ganhe
		for (int testNum = 0; testNum < 1000; testNum++) {

			int size = 5 + (int)(Math.random()*11);
			if ((size % 2) == 0)
				size++;

			Jogo jogo = new Jogo(size);
			jogo.inter = 1;
			jogo.labirinto = new Random_generator(size);
			jogo.heroi = new Heroi();
			jogo.heroi.set_armado(true);
			jogo.heroi.random_start();
			jogo.dragoes = new Dragao [1];
			for (int i = 0; i < jogo.dragoes.length; i++) {
				jogo.dragoes[i] = new Dragao(2);
				jogo.dragoes[i].random_dragao();
			}
						
			int choice = -1;
			for (int j = 0; j < 200 && choice != 5 && choice != 10; j++) {
				choice = 1 + (int)(Math.random()*4);

				for (int i = 0; i < jogo.dragoes.length; i++)
					jogo.dragoes[i].change_dragon_pos();

				for (int i = 0; i < jogo.dragoes.length; i++) {
					if (jogo.dragoes[i].get_status() == 0 || jogo.dragoes[i].get_status() == 2)
						jogo.dragoes[i].movimentar_dragao();
				}

				choice = jogo.interpreta_opcao(choice);
				if (choice == 10)
					break;

				for (int i = 0; i < jogo.dragoes.length; i++) {
					if (jogo.dragoes[i].get_alive() && choice != 0) {
						choice = jogo.dragoes[i].check_if_dead(jogo.heroi);
						jogo.dragoes[i].change_status();
					}

				}

			}
						
			assertTrue(choice == -1 || choice == 10 || choice == 9);
			if (choice == 10)
				winCounter++;
		}
		assertTrue(winCounter > 0);
	}
	@Test(timeout=5000)
	public void testIfFindsExitButDragonAlive() {
		int exitCounter = 0;
		for (int testNum = 0; testNum < 1000; testNum++) {

			int size = 5 + (int)(Math.random()*11);
			if ((size % 2) == 0)
				size++;

			Jogo jogo = new Jogo(size);
			jogo.inter = 1;
			jogo.labirinto = new Random_generator(size);
			jogo.heroi = new Heroi();
			jogo.heroi.set_armado(true);
			jogo.heroi.random_start();
			jogo.dragoes = new Dragao [1];
			for (int i = 0; i < jogo.dragoes.length; i++) {
				jogo.dragoes[i] = new Dragao(1);
				jogo.dragoes[i].random_dragao();
			}
						
			int choice = -1;
			for (int j = 0; j < 200 && choice != 5 && choice != 10; j++) {
				choice = 1 + (int)(Math.random()*4);

				for (int i = 0; i < jogo.dragoes.length; i++)
					jogo.dragoes[i].change_dragon_pos();

				for (int i = 0; i < jogo.dragoes.length; i++) {
					if (jogo.dragoes[i].get_status() == 0 || jogo.dragoes[i].get_status() == 2)
						jogo.dragoes[i].movimentar_dragao();
				}

				choice = jogo.interpreta_opcao(choice);
				if (choice == 9)
					exitCounter++;

				for (int i = 0; i < jogo.dragoes.length; i++) {
					if (jogo.dragoes[i].get_alive() && choice != 0) {
						choice = jogo.dragoes[i].check_if_dead(jogo.heroi);
						jogo.dragoes[i].change_status();
					}

				}

			}
						
			assertTrue(choice == -1 || choice == 10 || choice == 9);
		}
		assertTrue(exitCounter > 0);
	}
	@Test(timeout=5000)
	public void testIfDragonSleepsAndWakes() {
		int awakeningCounter = 0;
		int asleepCounter = 0;
		for (int testNum = 0; testNum < 1000; testNum++) {

			int size = 21 + (int)(Math.random()*31);
			if ((size % 2) == 0)
				size++;

			Jogo jogo = new Jogo(size);
			jogo.inter = 1;
			jogo.labirinto = new Random_generator(size);
			jogo.heroi = new Heroi();
			jogo.heroi.set_armado(false);
			jogo.heroi.random_start();
			jogo.dragoes = new Dragao [10];
			for (int i = 0; i < jogo.dragoes.length; i++) {
				jogo.dragoes[i] = new Dragao(0);
				jogo.dragoes[i].random_dragao();
			}
						
			int choice = -1;
			for (int j = 0; j < 20 && choice != 5 && choice != 10; j++) {
				choice = 1 + (int)(Math.random()*4);

				for (int i = 0; i < jogo.dragoes.length; i++)
					jogo.dragoes[i].change_dragon_pos();

				for (int i = 0; i < jogo.dragoes.length; i++) {
					if (jogo.dragoes[i].get_status() == 0 || jogo.dragoes[i].get_status() == 2)
						jogo.dragoes[i].movimentar_dragao();
				}

				choice = jogo.interpreta_opcao(choice);

				int [] dragoes_status = new int[jogo.dragoes.length];
				for (int a = 0; a < jogo.dragoes.length; a++)
					dragoes_status[a] = jogo.dragoes[a].get_status();
				
				for (int i = 0; i < jogo.dragoes.length; i++) {
					if (jogo.dragoes[i].get_alive() && choice != 0) {
						choice = jogo.dragoes[i].check_if_dead(jogo.heroi);
						jogo.dragoes[i].change_status();
					}
				}
				
				for (int a = 0; a < dragoes_status.length; a++) {
					if (dragoes_status[a] == 0)
						if (jogo.dragoes[a].get_status() == 1)
							asleepCounter++;
						else {}
					else if (dragoes_status[a] == 1)
						if (jogo.dragoes[a].get_status() == 0)
							awakeningCounter++;
				}

			}
		}
		assertTrue(awakeningCounter > 0);
		assertTrue(asleepCounter > 0);
	}
	@Test(timeout=5000)
	public void testCatchesShield() {
		int shieldCounter = 0;
		for (int testNum = 0; testNum < 1000; testNum++) {
			int size = 5 + (int)(Math.random()*15);
			if ((size % 2) == 0)
				size++;
			Jogo jogo = new Jogo(size);
			jogo.inter = 1;
			jogo.labirinto = new Random_generator(size);
			jogo.heroi = new Heroi();
			jogo.escudo = new Escudo();
			jogo.escudo.random_start();
			jogo.heroi.random_start();

			assertTrue(!jogo.heroi.get_shielded());
			for (int j = 0; j < 200; j++) {
				int choice = 1 + (int)(Math.random()*4);
				int actual_x_pos = jogo.heroi.get_x_coord();
				int actual_y_pos = jogo.heroi.get_y_coord();
				if (choice == 1) {
					if (jogo.labirinto.lab[jogo.heroi.get_x_coord()][jogo.heroi.get_y_coord()-1] == 'E') {
						jogo.interpreta_opcao(choice);
						assertTrue(jogo.heroi.get_armado());
						break;
					}
					else
						jogo.interpreta_opcao(choice);
				}
				else if (choice == 2) {
					if (jogo.labirinto.lab[jogo.heroi.get_x_coord()][jogo.heroi.get_y_coord()+1] == 'E') {
						jogo.interpreta_opcao(choice);
						assertTrue(jogo.heroi.get_armado());
						break;
					}
					else
						jogo.interpreta_opcao(choice);
				}
				else if (choice == 3) {
					if (jogo.labirinto.lab[jogo.heroi.get_x_coord()-1][jogo.heroi.get_y_coord()] == 'E') {
						jogo.interpreta_opcao(choice);
						assertTrue(jogo.heroi.get_armado());
						break;
					}
					else
						jogo.interpreta_opcao(choice);
				}
				else {
					if (jogo.labirinto.lab[jogo.heroi.get_x_coord()+1][jogo.heroi.get_y_coord()] == 'E') {
						jogo.interpreta_opcao(choice);
						assertTrue(jogo.heroi.get_armado());
						break;
					}
					else
						jogo.interpreta_opcao(choice);
				}

			}
			if (jogo.heroi.get_shielded())
				shieldCounter++;

		}
		assertTrue(shieldCounter > 0);
	}
	@Test(timeout=5000)
	public void testFireKill() {
		int fireCounter = 0;
		for (int testNum = 0; testNum < 1000; testNum++) {
			int size = 21 + (int)(Math.random()*31);
			if ((size % 2) == 0)
				size++;
			
			Jogo jogo = new Jogo(size);
			jogo.inter = 1;
			jogo.labirinto = new Random_generator(size);
			jogo.heroi = new Heroi();
			jogo.heroi.random_start();
			jogo.dragoes = new Dragao [5];
			
			for (int a = 0; a < jogo.dragoes.length; a++)
				jogo.dragoes[a] = new Dragao(1);
			
			int choice = -1;
			for (int j = 0; j < 200 && choice != 5 && choice != 10; j++) {
				choice = 1 + (int)(Math.random()*4);

				for (int i = 0; i < jogo.dragoes.length; i++)
					jogo.dragoes[i].change_dragon_pos();

				for (int i = 0; i < jogo.dragoes.length; i++) {
					if (jogo.dragoes[i].get_status() == 0 || jogo.dragoes[i].get_status() == 2)
						jogo.dragoes[i].movimentar_dragao();
					
					if (jogo.dragoes[i].get_alive()) {
						int choice2 = jogo.dragoes[i].random_dragao_fire(jogo.heroi);
						if (choice2 == 10) {
							fireCounter++;
							choice = 10;
						}
					}
				}
				
				if (choice == 10)
					break;

				choice = jogo.interpreta_opcao(choice);

				for (int i = 0; i < jogo.dragoes.length; i++) {
					if (jogo.dragoes[i].get_alive() && choice != 0) {
						choice = jogo.dragoes[i].check_if_dead(jogo.heroi);
						jogo.dragoes[i].change_status();
					}
				}

			}
		}
		assertTrue(fireCounter > 0);
	}

	//-------------------------TERMINAR TESTE DE DARDOS---------------------------------
	@Test(timeout=5000)
	public void testCatchesDards() {
		int fireCounter = 0;
		for (int testNum = 0; testNum < 1000; testNum++) {
			int size = 21 + (int)(Math.random()*31);
			if ((size % 2) == 0)
				size++;
			
			Jogo jogo = new Jogo(size);
			jogo.inter = 1;
			jogo.labirinto = new Random_generator(size);
			jogo.heroi = new Heroi();
			jogo.heroi.random_start();
			
			int choice = -1;
			for (int j = 0; j < 200 && choice != 5 && choice != 10; j++) {
				choice = 1 + (int)(Math.random()*4);
				choice = jogo.interpreta_opcao(choice);

			}
		}
		assertTrue(fireCounter > 0);
	}
	//Testa apanhar dardos - heroi
	//Testa lancar dardos
}

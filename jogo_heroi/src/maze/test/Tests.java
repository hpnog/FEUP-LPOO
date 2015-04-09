package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import maze.logic.Dart;
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
	private Object tester_1 = new Object(3,3);
	private Object tester_2 = new Object(3,2);
	private Object tester_3 = new Object(2,3);

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
		visit(m, p.getX_coord(), p.getY_coord());

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
	public <T> boolean notNullAndDistinct(@SuppressWarnings("unchecked") T ... args) {
		for (int i = 0; i < args.length - 1; i++)
			for (int j = i + 1; j < args.length ; j++)
				if (args[i] == null || args[j] == null || args[i].equals(args[j]))
					return false;
		return true;
	}

	//----------------------------------------------------------TESTS----------------------------------------------------------
	@Test(timeout=1000)
	public void testRandomDragon() {
		System.out.println("Starting test: Test if a Dragons position is randomly chosen correctly");
		char [][] m1 = {
				{'X', 'X', 'X', 'X', 'X'},
				{'X', 'H', ' ', ' ', 'X'},
				{'X', ' ', 'X', ' ', 'S'},
				{'X', ' ', ' ', 'D', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};

		testAlt(1000,
				() -> {
					Lab maze = new Default_maze(10);
					maze.setSize(5);
					maze.setLab(m1);

					Heroi heroi = new Heroi();
					heroi.setX_coord(1);
					heroi.setY_coord(1);

					Dragao dragao = new Dragao(2);
					dragao.setX_coord(3);
					dragao.setY_coord(3);

					heroi.setX_coord(heroi.getX_coord()+1);
					if (maze.getLab()[heroi.getX_coord()][heroi.getY_coord()] == 'O')
						heroi.setShielded(true);
					if (heroi.isArmado())
						maze.setLabCell(heroi.getX_coord(), heroi.getY_coord(), 'A');
					else
						maze.setLabCell(heroi.getX_coord(), heroi.getY_coord(), 'H');

					return maze;
				},
				(m) -> "Dragão em posição inválida: " + m, 
				(m) -> m.getDragonPosition().equals(tester_1), 
				(m) -> m.getDragonPosition().equals(tester_2), 
				(m) -> m.getDragonPosition().equals(tester_3)); 
		System.out.println("Ended test: Test if a Dragons position is randomly chosen correctly");
	}
	@Test(timeout=5000)
	public void testRandomMazeGenerator() throws Exception {
		System.out.println("Starting test: Test if a Maze is properly created");
		int numMazes = 1000;
		int maxSize = 101; // can change to any odd number >= 5

		new Random_generator(5);
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
			int randomX;
			int randomY;
			while (true) {
				randomX = (int)(1 + Math.random()*m.getSize());
				randomY = (int)(1 + Math.random()*m.getSize());
				randomX--;
				randomY--;
				if (m.getLab()[randomX][randomY] == ' ')
					break;
			}
			heroi.setX_coord(randomX);
			heroi.setY_coord(randomY);
			if (m.getLab()[heroi.getX_coord()][heroi.getY_coord()] == 'O')
				heroi.setShielded(true);
			if (heroi.isArmado())
				m.setLabCell(heroi.getX_coord(), heroi.getY_coord(), 'A');
			else
				m.setLabCell(heroi.getX_coord(), heroi.getY_coord(), 'H');
			while (true) {
				randomX = (int)(1 + Math.random()*m.getSize());
				randomY = (int)(1 + Math.random()*m.getSize());
				randomX--;
				randomY--;
				if (m.getLab()[randomX][randomY] == ' ')
					break;
			}
			dragao.setX_coord(randomX);
			dragao.setY_coord(randomY);
			if (dragao.isAlive()) {
				if (m.getLab()[dragao.getX_coord()][dragao.getY_coord()] == 'E') {
					m.setLabCell(dragao.getX_coord(), dragao.getY_coord(), 'F');
				}
				else {
					if (dragao.getStatus() == 1)
						m.setLabCell(dragao.getX_coord(), dragao.getY_coord(), 'd');
					else
						m.setLabCell(dragao.getX_coord(), dragao.getY_coord(), 'D');
				}
			}
			else {
				if (m.getLab()[dragao.getX_coord()][dragao.getY_coord()] == 'd' || 
						m.getLab()[dragao.getX_coord()][dragao.getY_coord()] == 'D')
					m.setLabCell(dragao.getX_coord(), dragao.getY_coord(), ' ');
			}
			while (true) {
				randomX = (int)(1 + Math.random()*m.getSize());
				randomY = (int)(1 + Math.random()*m.getSize());
				randomX--;
				randomY--;
				if (m.getLab()[randomX][randomY] == ' ')
					break;
			}
			espada.setX_coord(randomX);
			espada.setY_coord(randomY);
			m.setLabCell(espada.getX_coord(), espada.getY_coord(), 'E');

			assertTrue("Invalid maze boundaries in maze:\n" + m, checkBoundaries(m));
			assertTrue("Maze exit not reachable in maze:\n" + m, checkExitReachable(m));
			assertNotNull("Invalid walls in maze:\n" + m, ! hasSquare(m, badWalls));
			assertNotNull("Invalid spaces in maze:\n" + m, ! hasSquare(m, badSpaces));
			assertNotNull("Invalid diagonals in maze:\n" + m, ! hasSquare(m, badDiag1));
			assertNotNull("Invalid diagonals in maze:\n" + m, ! hasSquare(m, badDiag2));
			assertTrue("Missing or overlapping objects in maze:\n" + m, 
					notNullAndDistinct(m.getExitPosition(), m.getHeroPosition(),
							m.getDragonPosition(), m.getSwordPosition()));			
		}
		System.out.println("Ended test: Test if a Maze is properly created");
	}
	@Test (timeout = 5000)
	public void testHeroMovementLeft() {
		System.out.println("Starting test: Test if Hero moves properly (left)");
		Jogo jogo = new Jogo();
		jogo.setInter(1);
		jogo.setLabirinto(new Random_generator(10));
		jogo.setHeroi(new Heroi());
		jogo.setLabirinto(new Default_maze(10));
		jogo.getHeroi().setX_coord(5);
		jogo.getHeroi().setY_coord(1);
		jogo.change_hero_pos();
		int choice = 3; //Move left
		if (jogo.getLabirinto().getLab()[jogo.getHeroi().getX_coord()-1][jogo.getHeroi().getY_coord()] == ' ') {
			jogo.interpreta_opcao(choice);
			assertEquals(4, jogo.getHeroi().getX_coord());
		}
		else {
			jogo.interpreta_opcao(choice);
			assertEquals(5, jogo.getHeroi().getX_coord());
		}
		assertEquals(1, jogo.getHeroi().getY_coord());
		
		System.out.println("Ended test: Test if Hero moves properly (left)");
	}
	@Test (timeout = 5000)
	public void testHeroMovementRight() {
		System.out.println("Starting test: Test if Hero moves properly (right)");
		Jogo jogo = new Jogo();
		jogo.setInter(1);
		jogo.setLabirinto(new Random_generator(10));
		jogo.setHeroi(new Heroi());
		jogo.setLabirinto(new Default_maze(10));
		jogo.getHeroi().setX_coord(5);
		jogo.getHeroi().setY_coord(1);
		jogo.change_hero_pos();
		int choice = 4; //Move right
		if (jogo.getLabirinto().getLab()[jogo.getHeroi().getX_coord()+1][jogo.getHeroi().getY_coord()] == ' ') {
			jogo.interpreta_opcao(choice);
			assertEquals(6, jogo.getHeroi().getX_coord());
		}
		else {
			jogo.interpreta_opcao(choice);
			assertEquals(5, jogo.getHeroi().getX_coord());
		}
		assertEquals(1, jogo.getHeroi().getY_coord());
		
		System.out.println("Ended test: Test if Hero moves properly (right)");
	}
	@Test (timeout = 5000)
	public void testHeroMovementDown() {
		System.out.println("Starting test: Test if Hero moves properly (down)");
		Jogo jogo = new Jogo();
		jogo.setInter(1);
		jogo.setLabirinto(new Random_generator(10));
		jogo.setHeroi(new Heroi());
		jogo.setLabirinto(new Default_maze(10));
		jogo.getHeroi().setX_coord(1);
		jogo.getHeroi().setY_coord(4);
		jogo.change_hero_pos();
		int choice = 2; //Move down
		if (jogo.getLabirinto().getLab()[jogo.getHeroi().getX_coord()][jogo.getHeroi().getY_coord()+1] == ' ') {
			jogo.interpreta_opcao(choice);
			assertEquals(5, jogo.getHeroi().getY_coord());
		}
		else {
			jogo.interpreta_opcao(choice);
			assertEquals(4, jogo.getHeroi().getY_coord());
		}
		assertEquals(1, jogo.getHeroi().getX_coord());
		
		System.out.println("Ended test: Test if Hero moves properly (down)");
	}
	@Test (timeout = 5000)
	public void testHeroMovementUp() {
		System.out.println("Starting test: Test if Hero moves properly (up)");
		Jogo jogo = new Jogo();
		jogo.setInter(1);
		jogo.setLabirinto(new Random_generator(10));
		jogo.setHeroi(new Heroi());
		jogo.setLabirinto(new Default_maze(10));
		jogo.getHeroi().setX_coord(1);
		jogo.getHeroi().setY_coord(4);
		jogo.change_hero_pos();
		int choice = 1; //Move up
		if (jogo.getLabirinto().getLab()[jogo.getHeroi().getX_coord()][jogo.getHeroi().getY_coord()-1] == ' ') {
			jogo.interpreta_opcao(choice);
			assertEquals(3, jogo.getHeroi().getY_coord());
		}
		else {
			jogo.interpreta_opcao(choice);
			assertEquals(4, jogo.getHeroi().getY_coord());
		}
		assertEquals(1, jogo.getHeroi().getX_coord());
		
		System.out.println("Ended test: Test if Hero moves properly (up)");
	}
	@Test (timeout = 5000)
	public void testIfCatchesSword() {
		System.out.println("Starting test: Test if Hero catches Sword");
		Jogo jogo = new Jogo();
		jogo.setInter(1);
		jogo.setLabirinto(new Random_generator(10));
		jogo.setHeroi(new Heroi());
		jogo.setLabirinto(new Default_maze(10));
		jogo.getHeroi().setX_coord(1);
		jogo.getHeroi().setY_coord(4);
		jogo.change_hero_pos();
		jogo.setEspada(new Espada());
		jogo.getEspada().setX_coord(1);
		jogo.getEspada().setY_coord(3);
		jogo.change_sword_pos();
		int choice = 1; //Move up
		jogo.interpreta_opcao(choice);
		assertEquals(3, jogo.getHeroi().getY_coord());
		assertTrue(jogo.getHeroi().isArmado());
		System.out.println("Finished test: Test if Hero catches Sword");
	}
	@Test (timeout = 5000)
	public void testIfCatchesShield() {
		System.out.println("Starting test: Test if Hero catches Shield");
		Jogo jogo = new Jogo();
		jogo.setInter(1);
		jogo.setLabirinto(new Random_generator(10));
		jogo.setHeroi(new Heroi());
		jogo.setLabirinto(new Default_maze(10));
		jogo.getHeroi().setX_coord(1);
		jogo.getHeroi().setY_coord(4);
		jogo.change_hero_pos();
		jogo.setEscudo(new Escudo());
		jogo.getEscudo().setX_coord(1);
		jogo.getEscudo().setY_coord(5);
		jogo.change_escudo_pos();
		int choice = 2; //Move down
		jogo.interpreta_opcao(choice);
		assertTrue(jogo.getHeroi().isShielded());
		System.out.println("Finished test: Test if Hero catches Shield");
	}
	@Test (timeout = 5000)
	public void testIfCatchesDard() {
		System.out.println("Starting test: Test if Hero catches Dards");
		Jogo jogo = new Jogo();
		jogo.setInter(1);
		jogo.setLabirinto(new Random_generator(10));
		jogo.setHeroi(new Heroi());
		jogo.setLabirinto(new Default_maze(10));
		jogo.getHeroi().setX_coord(1);
		jogo.getHeroi().setY_coord(4);
		jogo.change_hero_pos();
		jogo.setDardos(new Dart[3]);
		jogo.setDart(0, new Dart(1,1));
		jogo.setDart(1, new Dart(1,2));
		jogo.setDart(2, new Dart(1,3));
		jogo.change_dardo_pos(0);
		jogo.change_dardo_pos(1);
		jogo.change_dardo_pos(2);
		int choice = 1; //Move up
		assertEquals(0, jogo.getHeroi().getDardos());
		jogo.interpreta_opcao(choice);
		assertEquals(1, jogo.getHeroi().getDardos());
		jogo.interpreta_opcao(choice);
		assertEquals(2, jogo.getHeroi().getDardos());
		jogo.interpreta_opcao(choice);
		assertEquals(3, jogo.getHeroi().getDardos());
		System.out.println("Finished test: Test if Hero catches Dards");
	}
	@Test (timeout = 5000)
	public void testIfDragonKills() {
		System.out.println("Starting test: Test if Dragon kills without fire");
		Jogo jogo = new Jogo();
		jogo.setInter(1);
		jogo.setLabirinto(new Random_generator(10));
		jogo.setHeroi(new Heroi());
		jogo.setLabirinto(new Default_maze(10));
		jogo.getHeroi().setX_coord(1);
		jogo.getHeroi().setY_coord(4);
		jogo.change_hero_pos();
		jogo.setDragoes(new Dragao[1]);
		jogo.setDragao(0, new Dragao(2));
		jogo.getDragoes()[0].setX_coord(1);
		jogo.getDragoes()[0].setY_coord(3);
		int choice = 1;
		assertTrue(jogo.getDragoes()[0].isAlive());
		choice = jogo.interpreta_opcao(choice);
		choice = jogo.endOfTurn(choice);
		assertTrue(choice == 5);
		assertTrue(jogo.getDragoes()[0].isAlive());
		System.out.println("Ended test: Test if Dragon kills without fire");
	}
	@Test(timeout = 5000)
	public void testIfDragonKills_multipleGames() {
		System.out.println("Starting test: Test if Dragon kills without fire - multiple games");
		for (int testNum = 0; testNum < 1000; testNum++) {
			int size = 21 + (int)(Math.random()*31);
			if ((size % 2) == 0)
				size++;
			Jogo jogo = new Jogo();
			jogo.setInter(1);
			jogo.setLabirinto(new Random_generator(size));
			jogo.setHeroi(new Heroi());
			jogo.random_hero_start();
			jogo.setDragoes(new Dragao [10]);
			for (int i = 0; i < jogo.getDragoes().length; i++) {
				jogo.setDragao(i, new Dragao(2));
				jogo.random_dragao(i);
			}			
			int choice = -1;
			for (int j = 0; j < 20 && choice != 5; j++) {
				choice = 1 + (int)(Math.random()*4);
				jogo.displayDragoes();
				jogo.moveDragoes();
				choice = jogo.interpreta_opcao(choice);
				assertTrue(choice == 9 || choice == 10 || choice == -1);
				choice = jogo.endOfTurn(choice);
			}
			assertTrue(choice == 5 || choice == -1);
		}
		System.out.println("Ended test: Test if Dragon kills without fire - multiple games");
	}
	@Test(timeout=5000)
	public void testIfHeroKills() {
		System.out.println("Starting test: Test if Hero kills without fire");
		Jogo jogo = new Jogo();
		jogo.setInter(1);
		jogo.setLabirinto(new Random_generator(10));
		jogo.setHeroi(new Heroi());
		jogo.setLabirinto(new Default_maze(10));
		jogo.getHeroi().setX_coord(1);
		jogo.getHeroi().setY_coord(4);
		jogo.change_hero_pos();
		jogo.getHeroi().setArmado(true);
		jogo.setDragoes(new Dragao[1]);
		jogo.setDragao(0, new Dragao(3));
		jogo.getDragoes()[0].setX_coord(1);
		jogo.getDragoes()[0].setY_coord(3);
		int choice = -1;
		choice = jogo.endOfTurn(choice);
		assertTrue(!jogo.getDragoes()[0].isAlive());
		System.out.println("Ended test: Test if Hero kills without fire");
	}
	@Test (timeout = 5000)
	public void testIfKillsDragonAndFindsExit() {
		System.out.println("Starting test: Test if Hero kills without fire and finds the Exit");
		Jogo jogo = new Jogo();
		jogo.setInter(1);
		jogo.setLabirinto(new Random_generator(10));
		jogo.setHeroi(new Heroi());
		jogo.setLabirinto(new Default_maze(10));
		jogo.getHeroi().setX_coord(8);
		jogo.getHeroi().setY_coord(3);
		jogo.change_hero_pos();
		jogo.getHeroi().setArmado(true);
		jogo.setDragoes(new Dragao[1]);
		jogo.setDragao(0, new Dragao(3));
		jogo.getDragoes()[0].setX_coord(8);
		jogo.getDragoes()[0].setY_coord(6);
		int choice = 2;
		assertTrue(jogo.getDragoes()[0].isAlive());
		choice = jogo.interpreta_opcao(choice);
		choice = jogo.endOfTurn(choice);
		assertTrue(jogo.getDragoes()[0].isAlive());
		assertTrue(choice != 5);
		choice = 2;
		choice = jogo.interpreta_opcao(choice);
		assertTrue(choice == 9 || choice == 10 || choice == -1);
		choice = jogo.endOfTurn(choice);
		assertTrue(!jogo.getDragoes()[0].isAlive());
		assertTrue(choice != 5);
		choice = 4;
		choice = jogo.interpreta_opcao(choice);
		assertTrue(choice == 10);
		System.out.println("Ended test: Test if Hero kills without fire and finds the Exit");
	}
	@Test (timeout = 5000)
	public void testIfFindsExitButDragonAlive() {
		System.out.println("Starting test: Test if Hero gets to the exit but game does not end due to dragons alive");
		Jogo jogo = new Jogo();
		jogo.setInter(1);
		jogo.setLabirinto(new Random_generator(10));
		jogo.setHeroi(new Heroi());
		jogo.setLabirinto(new Default_maze(10));
		jogo.getHeroi().setX_coord(8);
		jogo.getHeroi().setY_coord(3);
		jogo.change_hero_pos();
		jogo.getHeroi().setArmado(true);
		jogo.setDragoes(new Dragao[1]);
		jogo.setDragao(0, new Dragao(3));
		jogo.getDragoes()[0].setX_coord(1);
		jogo.getDragoes()[0].setY_coord(1);
		int choice = 2;
		assertTrue(jogo.getDragoes()[0].isAlive());
		choice = jogo.interpreta_opcao(choice);
		assertTrue(choice == 9 || choice == 10 || choice == -1);
		choice = jogo.endOfTurn(choice);
		assertTrue(jogo.getDragoes()[0].isAlive());
		assertTrue(choice != 5);
		choice = 2;
		choice = jogo.interpreta_opcao(choice);
		assertTrue(choice == 9 || choice == 10 || choice == -1);
		choice = jogo.endOfTurn(choice);
		assertTrue(choice != 5);
		choice = 4;
		choice = jogo.interpreta_opcao(choice);
		assertTrue(choice == 9);
		System.out.println("Ended test: Test if Hero gets to the exit but game does not end due to dragons alive");
	}
	@Test(timeout=5000)
	public void testIfDragonSleepsAndWakes() {
		System.out.println("Starting test: Test if Dragons sleep properly");
		int awakeningCounter = 0;
		int asleepCounter = 0;
		for (int testNum = 0; testNum < 1000; testNum++) {

			int size = 21 + (int)(Math.random()*31);
			if ((size % 2) == 0)
				size++;

			Jogo jogo = new Jogo();
			jogo.setInter(1);
			jogo.setLabirinto(new Random_generator(size));
			jogo.setHeroi(new Heroi());
			jogo.getHeroi().setArmado(false);
			jogo.random_hero_start();
			jogo.setEscudo(new Escudo());
			jogo.shield_random_start();
			jogo.setDragoes(new Dragao [10]);
			for (int i = 0; i < jogo.getDragoes().length; i++) {
				jogo.setDragao(i, new Dragao(0));
				jogo.random_dragao(i);
			}
						
			int choice = -1;
			for (int j = 0; j < 20 && choice != 5 && choice != 10; j++) {
				choice = 1 + (int)(Math.random()*4);
				jogo.displayDragoes();
				jogo.moveDragoes();
				choice = jogo.interpreta_opcao(choice);
				assertTrue(choice == 9 || choice == 10 || choice == -1);
				
				int [] dragoes_status = new int[jogo.getDragoes().length];
				for (int a = 0; a < jogo.getDragoes().length; a++)
					dragoes_status[a] = jogo.getDragoes()[a].getStatus();
				
				choice = jogo.endOfTurn(choice);
				
				for (int a = 0; a < dragoes_status.length; a++) {
					if (dragoes_status[a] == 0)
						if (jogo.getDragoes()[a].getStatus() == 1)
							asleepCounter++;
						else {}
					else if (dragoes_status[a] == 1)
						if (jogo.getDragoes()[a].getStatus() == 0)
							awakeningCounter++;
				}
			}
		}
		assertTrue(awakeningCounter > 0);
		assertTrue(asleepCounter > 0);
		System.out.println("Ended test: Test if Dragons sleep properly");
	}
	@Test(timeout=5000)
	public void testFireKill() {
		System.out.println("Starting test: Test if Dragons kill with fire");
		int fireCounter = 0;
		for (int testNum = 0; testNum < 1000; testNum++) {
			int size = 21 + (int)(Math.random()*31);
			if ((size % 2) == 0)
				size++;
			
			Jogo jogo = new Jogo();
			jogo.setInter(1);
			jogo.setLabirinto(new Random_generator(size));
			jogo.setHeroi(new Heroi());
			jogo.random_hero_start();
			jogo.setDragoes(new Dragao [5]);
			
			for (int a = 0; a < jogo.getDragoes().length; a++)
				jogo.setDragao(a, new Dragao(1));			
			int choice = -1;
			for (int j = 0; j < 200 && choice != 5 && choice != 10; j++) {
				choice = 1 + (int)(Math.random()*4);
				jogo.displayDragoes();
				choice = jogo.moveAndSpit_dragoes(choice);				
				if (choice == 10) {
					fireCounter++;
					break;
				}
				choice = jogo.interpreta_opcao(choice);
				assertTrue(choice == 9 || choice == 10 || choice == -1);
				choice = jogo.endOfTurn(choice);
			}
		}
		assertTrue(fireCounter > 0);
		System.out.println("Ended test: Test if Dragons kill with fire");
	}
	@Test(timeout=50000)
	public void testshootsDards() {
		System.out.println("Starting test: Test if shoots Dards");
		int killCounter = 0;
		for (int testNum = 0; testNum < 1000; testNum++) {
			int size = 15 + (int)(Math.random()*25);
			if ((size % 2) == 0)
				size++;
			
			Jogo jogo = new Jogo();
			jogo.setInter(1);
			jogo.setLabirinto(new Random_generator(size));
			jogo.setHeroi(new Heroi());
			jogo.setEscudo(new Escudo());
			jogo.shield_random_start();
			jogo.random_hero_start();
			jogo.getHeroi().setDardos(1000);
			jogo.getHeroi().setArmado(false);
			jogo.setDragoes(new Dragao [10]);
			for (int i = 0; i < jogo.getDragoes().length; i++) {
				jogo.setDragao(i, new Dragao(0));
				jogo.random_dragao(i);
			}
			
			int choice = -1;
			for (int j = 0; j < 200 && choice != 5 && choice != 10; j++) {
				jogo.displayDragoes();
				jogo.moveDragoes();
				choice = 1 + (int)(Math.random()*4);
				choice += 100;
				choice = jogo.interpreta_opcao(choice);
				assertTrue(choice == 9 || choice == 10 || choice == -1);
				choice = jogo.endOfTurn(choice);
			}
			
			for (int i = 0; i < jogo.getDragoes().length; i++) {
				if (!jogo.getDragoes()[i].isAlive())
					killCounter++;
			}
			assertTrue((jogo.getEscudo().isCaught() && jogo.getHeroi().isArmado()) || (!jogo.getEscudo().isCaught() && !jogo.getHeroi().isArmado()));
		}
		assertTrue(killCounter > 0);
		System.out.println("Ended test: Test if shoots Dards");
	}
	@Test(timeout=5000)
	public void testIfKillsDragonAndFindsExit_multipleGames() {
		System.out.println("Starting test: Test if Hero gets to the exit and game ends due to dragons killed - multiple games");
		int winCounter = 0; //Muit provavel que ganhe
		for (int testNum = 0; testNum < 1000; testNum++) {
			int size = 5 + (int)(Math.random()*11);
			if ((size % 2) == 0)
				size++;
			Jogo jogo = new Jogo();
			jogo.setInter(1);
			jogo.setLabirinto(new Random_generator(size));
			jogo.setHeroi(new Heroi());
			jogo.getHeroi().setArmado(true);
			jogo.random_hero_start();
			jogo.setDragoes(new Dragao [1]);
			for (int i = 0; i < jogo.getDragoes().length; i++) {
				jogo.setDragao(i, new Dragao(2));
				jogo.random_dragao(i);
			}
						
			int choice = -1;
			for (int j = 0; j < 200 && choice != 5 && choice != 10; j++) {
				choice = 1 + (int)(Math.random()*4);
				jogo.displayDragoes();
				jogo.moveDragoes();
				choice = jogo.interpreta_opcao(choice);
				assertTrue(choice == 9 || choice == 10 || choice == -1);
				if (choice == 10)
					break;
				choice = jogo.endOfTurn(choice);
			}			
			assertTrue(choice == -1 || choice == 10 || choice == 9);
			if (choice == 10)
				winCounter++;
		}
		assertTrue(winCounter > 0);
		System.out.println("Ended test: Test if Hero gets to the exit and game ends due to dragons killed - multiple games");
	}
	@Test(timeout=5000)
	public void testIfHeroKills_multipleGames() {
		System.out.println("Starting test: Test if Hero kills with Sword - multiple games");
		for (int testNum = 0; testNum < 1000; testNum++) {

			int size = 21 + (int)(Math.random()*31);
			if ((size % 2) == 0)
				size++;

			Jogo jogo = new Jogo();
			jogo.setInter(1);
			jogo.setLabirinto(new Random_generator(size));
			jogo.setHeroi(new Heroi());
			jogo.getHeroi().setArmado(true);
			jogo.random_hero_start();
			jogo.setDragoes(new Dragao [10]);
			for (int i = 0; i < jogo.getDragoes().length; i++) {
				jogo.setDragao(i, new Dragao(2));
				jogo.random_dragao(i);
			}
						
			int choice = -1;
			for (int j = 0; j < 20 && choice != 5; j++) {
				choice = 1 + (int)(Math.random()*4);
				jogo.displayDragoes();
				jogo.moveDragoes();
				choice = jogo.interpreta_opcao(choice);
				assertTrue(choice == 9 || choice == 10 || choice == -1);
				choice = jogo.endOfTurn(choice);

			}

			assertTrue(choice == -1);
		}
		System.out.println("Ended test: Test if Hero kills with Sword - multiple games");
	}
	@Test(timeout=5000)
	public void testIfFindsExitButDragonAlive_multipleGames() {
		System.out.println("Starting test: Test if Hero gets to the exit but game does not end due to dragons alive - multiple games");
		int exitCounter = 0;
		for (int testNum = 0; testNum < 1000; testNum++) {
			int size = 5 + (int)(Math.random()*11);
			if ((size % 2) == 0)
				size++;
			Jogo jogo = new Jogo();
			jogo.setInter(1);
			jogo.setLabirinto(new Random_generator(size));
			jogo.setHeroi(new Heroi());
			jogo.getHeroi().setArmado(true);
			jogo.random_hero_start();
			jogo.setDragoes(new Dragao [1]);
			for (int i = 0; i < jogo.getDragoes().length; i++) {
				jogo.setDragao(i, new Dragao(2));
				jogo.random_dragao(i);
			}
						
			int choice = -1;
			for (int j = 0; j < 200 && choice != 5 && choice != 10; j++) {
				choice = 1 + (int)(Math.random()*4);
				jogo.displayDragoes();
				jogo.moveDragoes();
				choice = jogo.interpreta_opcao(choice);
				if (choice == 9)
					exitCounter++;
				choice = jogo.endOfTurn(choice);
			}
						
			assertTrue(choice == -1 || choice == 10 || choice == 9);
		}
		assertTrue(exitCounter > 0);
		System.out.println("Ended test: Test if Hero gets to the exit but game does not end due to dragons alive - multiple games");
	}
}

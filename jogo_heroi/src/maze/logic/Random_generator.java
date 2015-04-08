package maze.logic;

import java.io.Serializable;
import java.util.Stack;

// TODO: Auto-generated Javadoc
/**
 * The Class Random_generator.
 */
public class Random_generator extends Lab implements Serializable {
	

	private static final long serialVersionUID = -3817543882507696922L;
	private char [][] celulasVisitadas;
	private int randomXcoord;
	private int randomYcoord;
	private Stack <Coord> coord;
	
	/**
	 * Instantiates a new random generator.
	 *
	 * @param s the s
	 */
	public Random_generator(int s) {
		super(s);
		randomXcoord = 0;
		randomYcoord = 0;
		coord = new Stack <Coord> ();
		//Inicializa o array auxiliar de celulas visitadas
		celulasVisitadas = new char [(size - 1) / 2][(size - 1) / 2];
			
		random_maze();
	}
	
	/**
	 * The Class Coord.
	 */
	private class Coord {
		
		/** The x. */
		public int x;
		
		/** The y. */
		public int y;
		
		/**
		 * Instantiates a new coordinate 
		 *
		 * @param a the a
		 * @param b the b
		 */
		Coord(int a, int b) {
			x = a;
			y = b;
		}
	}
	
	/**
	 * Fills the odd coordinates.
	 */
	private void preenche_impares() {
		for (int i = 0; i < size - 1; i++)
			for (int j = 0; j < size - 1; j++)
				if (i % 2 != 0 && j % 2 != 0 && i != size - 1 && j != size - 1)
					lab[i][j] = ' ';
	}
	
	/**
	 * Counts the options.
	 *
	 * @param number_of_options the number of options
	 * @return the int
	 */
	private int conta_opcoes(int number_of_options) {
		if (coord.peek().x-1 < 0)
			number_of_options--;
		else if (celulasVisitadas[coord.peek().x-1][coord.peek().y] != '-')
			number_of_options--;

		if (coord.peek().x+1 > ((size - 1) / 2)-1)
			number_of_options--;
		else if (celulasVisitadas[coord.peek().x+1][coord.peek().y] != '-')
			number_of_options--;

		if (coord.peek().y-1 < 0)
			number_of_options--;
		else if (celulasVisitadas[coord.peek().x][coord.peek().y-1] != '-')
			number_of_options--;

		if (coord.peek().y+1 > ((size - 1) / 2)-1)
			number_of_options--;
		else if (celulasVisitadas[coord.peek().x][coord.peek().y+1] != '-')
			number_of_options--;
		return number_of_options;
	}
	
	/**
	 * Picks an exit.
	 */
	private void escolhe_saida() {
		if (randomXcoord*2+1 == 1)					//checks if it is on the left
			if (randomYcoord*2+1 == 1 || randomYcoord*2+1 == size-1) {	//checks if it is on the left corner
				if (randomYcoord*2+1 == 1) {		//if upper left corner
					int randomSaida = 1+(int)(Math.random()*2);
					if (randomSaida == 1)
						lab[1][0] = 'S';
					else
						lab[0][1] = 'S';
				}
				else {								//if bottom left corner
					int randomSaida = 1+(int)(Math.random()*2);
					if (randomSaida == 1)
						lab[1][size-1] = 'S';
					else
						lab[0][size-2] = 'S';
				}
			}
			else {									//its on the left but not on a corner
				lab[0][randomYcoord*2+1] = 'S';
			}
		else if (randomXcoord*2+1 == ((size - 1) / 2)-1){	//checks if right
			if (randomYcoord*2+1 == 1 || randomYcoord*2+1 == size-1) {	//checks if right corner
				if (randomYcoord*2+1 == 1) {		//if upper right corner
					int randomSaida = 1+(int)(Math.random()*2);
					if (randomSaida == 1)
						lab[size-2][0] = 'S';
					else
						lab[1][size-1] = 'S';
				}
				else {								//if bottom right corner
					int randomSaida = 1+(int)(Math.random()*2);
					if (randomSaida == 1)
						lab[size-2][0] = 'S';
					else
						lab[1][size-1] = 'S';
				}
			}
			else {									//if its on the right but not on a corner
				lab[size-1][randomYcoord*2+1] = 'S';
			}
		}
		else {	//if not on the left or right
			if (randomYcoord == 0) {		//if up
				lab[randomXcoord*2+1][randomYcoord] = 'S';
			}
			else {							//if down
				lab[randomXcoord*2+1][randomYcoord*2+2] = 'S';
			}
		}
	}
	
	/**
	 * First random cell
	 */
	private void primeira_celula_random() {
		randomXcoord = 0 + (int)(Math.random()*(((size - 1) / 2)-1));
		if (randomXcoord == 0 || randomXcoord == (((size - 1) / 2)-1))
			randomYcoord = 0 + (int)(Math.random()*(((size - 1) / 2)-1));
		else {
			randomYcoord = 1 + (int)(Math.random()*2);
			if (randomYcoord == 1)
				randomYcoord = 0;
			else
				randomYcoord = (((size - 1) / 2)-1);
		}
		celulasVisitadas[randomXcoord][randomYcoord] = '+';
	}
	
	/**
	 * Fills aux
	 */
	private void preenche_auxiliar() {
		for (int i = 0; i < (size - 1) / 2; i++)
			for (int j = 0; j < (size - 1) / 2; j++)
				celulasVisitadas[i][j] = '-';
	}
	
	/**
	 * Fills borders
	 */
	private void preenche_bordas() {
		//Preenche as bordas do labirinto && preenche o numero impar das celulas
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				if (i == 0 || i == (size - 1) || j == 0 || j == (size-1) || (i % 2 == 0) || (j % 2 == 0))
					lab[i][j] = 'X';
			}
	}
	
	/**
	 * Creates the random maze
	 */
	private void random_maze() {
		
		preenche_bordas();
		preenche_auxiliar();
		primeira_celula_random();
		
		Coord initial = new Coord(randomXcoord, randomYcoord);
		coord.push(initial);

		lab[randomXcoord*2+1][randomYcoord*2+1] = '+';
		escolhe_saida();
		
		while (!coord.empty()) {
			int number_of_options = 4;
			number_of_options = conta_opcoes(number_of_options);
			
																		//int option = (int)(Math.random()*number_of_options);

			if (number_of_options == 0)
				coord.pop();
			else {
				int breaker = 0;					
				while (breaker == 0) {
					int random = 1 + (int)(Math.random()*4);
					if (random == 4) {
						if (coord.peek().y+1 <= ((size - 1) / 2)-1)
							if (celulasVisitadas[coord.peek().x][coord.peek().y+1] == '-') {
								celulasVisitadas[coord.peek().x][coord.peek().y+1] = '+';
								lab[coord.peek().x*2+1][coord.peek().y*2+2] = ' ';
								Coord temp = new Coord(coord.peek().x, coord.peek().y+1);
								coord.push(temp);
								breaker++;
							}
							else {}
					}
					else if (random == 1) {
						if (coord.peek().y-1 >= 0)
							if (celulasVisitadas[coord.peek().x][coord.peek().y-1] == '-') {
								celulasVisitadas[coord.peek().x][coord.peek().y-1] = '+';
								lab[coord.peek().x*2+1][coord.peek().y*2] = ' ';
								Coord temp = new Coord(coord.peek().x, coord.peek().y-1);
								coord.push(temp);
								breaker++;
							}
							else {}
					}
					else if (random == 2) {
						if (coord.peek().x+1 <= ((size - 1) / 2)-1)
							if (celulasVisitadas[coord.peek().x+1][coord.peek().y] == '-') {
								celulasVisitadas[coord.peek().x+1][coord.peek().y] = '+';
								lab[coord.peek().x*2+2][coord.peek().y*2+1] = ' ';
								Coord temp = new Coord(coord.peek().x+1, coord.peek().y);
								coord.push(temp);
								breaker++;
							}
							else {}
					}
					else if (random == 3) {
						if (coord.peek().x-1 >= 0)
							if (celulasVisitadas[coord.peek().x-1][coord.peek().y] == '-') {
								celulasVisitadas[coord.peek().x-1][coord.peek().y] = '+';
								lab[coord.peek().x*2][coord.peek().y*2+1] = ' ';
								Coord temp = new Coord(coord.peek().x-1, coord.peek().y);
								coord.push(temp);
								breaker++;
							}
							else {}
					}
				}
			}
		}

		preenche_impares();
	}
	
	/**
	 * Gets the visited cells.
	 *
	 * @return the visited cells
	 */
	public char[][] getCelulasVisitadas() {
		return celulasVisitadas;
	}
	
	/**
	 * Sets the visited cells.
	 *
	 * @param celulasVisitadas the new visited cells
	 */
	public void setCelulasVisitadas(char[][] celulasVisitadas) {
		this.celulasVisitadas = celulasVisitadas;
	}
	
	/**
	 * Gets the random xcoord.
	 *
	 * @return the random xcoord
	 */
	public int getRandomXcoord() {
		return randomXcoord;
	}
	
	/**
	 * Sets the random xcoord.
	 *
	 * @param randomXcoord the new random xcoord
	 */
	public void setRandomXcoord(int randomXcoord) {
		this.randomXcoord = randomXcoord;
	}
	
	/**
	 * Gets the random ycoord.
	 *
	 * @return the random ycoord
	 */
	public int getRandomYcoord() {
		return randomYcoord;
	}
	
	/**
	 * Sets the random ycoord.
	 *
	 * @param randomYcoord the new random ycoord
	 */
	public void setRandomYcoord(int randomYcoord) {
		this.randomYcoord = randomYcoord;
	}
	
	/**
	 * Gets the coord.
	 *
	 * @return the coord
	 */
	public Stack<Coord> getCoord() {
		return coord;
	}
	
	/**
	 * Sets the coord.
	 *
	 * @param coord the new coord
	 */
	public void setCoord(Stack<Coord> coord) {
		this.coord = coord;
	}	
}

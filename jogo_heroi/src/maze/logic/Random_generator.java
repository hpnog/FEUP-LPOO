package maze.logic;

import java.io.Serializable;
import java.util.Stack;

public class Random_generator extends Lab implements Serializable {
	private static final long serialVersionUID = -3817543882507696922L;
	private char [][] celulasVisitadas;
	private int randomXcoord;
	private int randomYcoord;
	private Stack <Coord> coord;
	
	public Random_generator(int s) {
		super(s);
		randomXcoord = 0;
		randomYcoord = 0;
		coord = new Stack <Coord> ();
		//Inicializa o array auxiliar de celulas visitadas
		celulasVisitadas = new char [(size - 1) / 2][(size - 1) / 2];
			
		random_maze();
	}
	
	private class Coord {
		public int x;
		public int y;
		
		Coord(int a, int b) {
			x = a;
			y = b;
		}
	}
	
	private void preenche_impares() {
		for (int i = 0; i < size - 1; i++)
			for (int j = 0; j < size - 1; j++)
				if (i % 2 != 0 && j % 2 != 0 && i != size - 1 && j != size - 1)
					lab[i][j] = ' ';
	}
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
	private void escolhe_saida() {
		if (randomXcoord*2+1 == 1)					//verifica se esta a esquerda
			if (randomYcoord*2+1 == 1 || randomYcoord*2+1 == size-1) {	//verifica se esta num canto esquerdo
				if (randomYcoord*2+1 == 1) {		//esta no canto superior esquerdo
					int randomSaida = 1+(int)(Math.random()*2);
					if (randomSaida == 1)
						lab[1][0] = 'S';
					else
						lab[0][1] = 'S';
				}
				else {								//esta no canto inferior esquerdo
					int randomSaida = 1+(int)(Math.random()*2);
					if (randomSaida == 1)
						lab[1][size-1] = 'S';
					else
						lab[0][size-2] = 'S';
				}
			}
			else {									//esta a esuqerda mas nao num canto
				lab[0][randomYcoord*2+1] = 'S';
			}
		else if (randomXcoord*2+1 == ((size - 1) / 2)-1){	//verifica se está a direita
			if (randomYcoord*2+1 == 1 || randomYcoord*2+1 == size-1) {	//verifica se esta num canto direito
				if (randomYcoord*2+1 == 1) {		//esta no canto superior direito
					int randomSaida = 1+(int)(Math.random()*2);
					if (randomSaida == 1)
						lab[size-2][0] = 'S';
					else
						lab[1][size-1] = 'S';
				}
				else {								//esta no canto inferior direito
					int randomSaida = 1+(int)(Math.random()*2);
					if (randomSaida == 1)
						lab[size-2][0] = 'S';
					else
						lab[1][size-1] = 'S';
				}
			}
			else {									//esta a direita mas nao num canto
				lab[size-1][randomYcoord*2+1] = 'S';
			}
		}
		else {	//nao estando a esquerda nem a direita
			if (randomYcoord == 0) {		//estando em cima
				lab[randomXcoord*2+1][randomYcoord] = 'S';
			}
			else {							//estando em baixo
				lab[randomXcoord*2+1][randomYcoord*2+2] = 'S';
			}
		}
	}
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
	private void preenche_auxiliar() {
		for (int i = 0; i < (size - 1) / 2; i++)
			for (int j = 0; j < (size - 1) / 2; j++)
				celulasVisitadas[i][j] = '-';
	}
	private void preenche_bordas() {
		//Preenche as bordas do labirinto && preenche o numero impar das celulas
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				if (i == 0 || i == (size - 1) || j == 0 || j == (size-1) || (i % 2 == 0) || (j % 2 == 0))
					lab[i][j] = 'X';
			}
	}
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
	
	public char[][] getCelulasVisitadas() {
		return celulasVisitadas;
	}
	public void setCelulasVisitadas(char[][] celulasVisitadas) {
		this.celulasVisitadas = celulasVisitadas;
	}
	public int getRandomXcoord() {
		return randomXcoord;
	}
	public void setRandomXcoord(int randomXcoord) {
		this.randomXcoord = randomXcoord;
	}
	public int getRandomYcoord() {
		return randomYcoord;
	}
	public void setRandomYcoord(int randomYcoord) {
		this.randomYcoord = randomYcoord;
	}
	public Stack<Coord> getCoord() {
		return coord;
	}
	public void setCoord(Stack<Coord> coord) {
		this.coord = coord;
	}	
}

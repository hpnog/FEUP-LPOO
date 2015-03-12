package maze.logic;

import java.util.Stack;

public class Lab {
	public static char [][] lab ;
	public static int size;
	
	Lab(int s) {
		size = s;
		lab = new char [size][size];
	}
	
	public static void random_maze(int n) {
		class Coord {
			public int x;
			public int y;
			
			Coord(int a, int b) {
				x = a;
				y = b;
			}
		}
		size = n;			
		//Preenche as bordas do labirinto && preenche o numero impar das celulas
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				if (i == 0 || i == (n - 1) || j == 0 || j == (n-1) || (i % 2 == 0) || (j % 2 == 0))
					lab[i][j] = 'X';
			}

		//Inicializa o array auxiliar de celulas visitadas
		char [][] celulasVisitadas = new char [(size - 1) / 2][(size - 1) / 2];
		for (int i = 0; i < (size - 1) / 2; i++)
			for (int j = 0; j < (size - 1) / 2; j++)
				celulasVisitadas[i][j] = '-';

		int randomXcoord = 0 + (int)(Math.random()*(((size - 1) / 2)-1));
		int randomYcoord;
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
		Coord initial = new Coord(randomXcoord, randomYcoord);
		lab[randomXcoord*2+1][randomYcoord*2+1] = '+';
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
				int randomSaida = 1 + (int)(Math.random()*((size - 1) / 2)-1);
				lab[size-1][randomSaida] = 'S';
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
		Stack <Coord> coord = new Stack <Coord> ();
		coord.push(initial);
		while (!coord.empty()) {
			int number_of_options = 4;
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

			int option = (int)(Math.random()*number_of_options);

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

		for (int i = 0; i < size - 1; i++)
			for (int j = 0; j < size - 1; j++)
				if (i % 2 != 0 && j % 2 != 0 && i != size - 1 && j != size - 1)
					lab[i][j] = ' ';
	}
	public static void first_maze() {
		size = 10;
		
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 10; j++)
				lab[i][j] = ' ';
		
		for(int i = 0; i < 10; i++)
		{
			lab[0][i] = 'X';
			lab[9][i] = 'X';
			lab[i][0] = 'X';
			lab[i][9] = 'X';
		}
		
		lab[9][5] = 'S';
		
		lab[2][2] = 'X';
		lab[2][3] = 'X';
		lab[2][4] = 'X';
		lab[3][3] = 'X';
		lab[3][2] = 'X';
		lab[3][4] = 'X';
		
		lab[2][6] = 'X';
		lab[2][7] = 'X';
		lab[2][8] = 'X';
		lab[3][6] = 'X';
		lab[3][7] = 'X';
		lab[3][8] = 'X';
		
		lab[5][2] = 'X';
		lab[5][3] = 'X';
		lab[5][4] = 'X';
		lab[5][6] = 'X';
		lab[5][7] = 'X';
		
		lab[7][2] = 'X';
		lab[7][3] = 'X';
		lab[7][4] = 'X';
		lab[7][5] = 'X';
		lab[7][6] = 'X';
		lab[7][7] = 'X';
		
	}
	
}

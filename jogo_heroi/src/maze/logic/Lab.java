package maze.logic;

public class Lab {
	public static char [][] lab ;
	public static int size;
	
	Lab(int s) {
		size = s;
		lab = new char [size][size];
	}

	//Funcao apenas para efeito de testes
	public Object getDragonPosition() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (lab[i][j] == 'D')
					return new Object(i,j);
			}
		}
		return null;
	}
	//-----------------------------------
	
	public int getSize() {
		return size;
	}
	
	public char getChar(int x, int y) {
		return lab[x][y];
	}

	public char[][] getMatrix() {
		return lab;
	}

	public Object getExitPosition() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (lab[i][j] == 'S')
					return new Object(i,j);
			}
		}
		return null;
	}

	public Object getHeroPosition() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (lab[i][j] == 'H' || lab[i][j] == 'A')
					return new Object(i,j);
			}
		}
		return null;
	}

	public Object getSpadePosition() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (lab[i][j] == 'E')
					return new Object(i,j);
			}
		}
		return null;
	}
}

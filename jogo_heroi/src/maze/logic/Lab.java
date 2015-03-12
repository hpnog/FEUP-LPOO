package maze.logic;

public class Lab {
	public static char [][] lab ;
	public static int size;
	
	Lab(int s) {
		size = s;
		lab = new char [size][size];
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

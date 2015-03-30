package maze.logic;

public class Espada extends Object {
	
	public Espada() {
		super(1, 1);
	}
	
	public void random_sword() {
		int randomX;
		int randomY;
		while (true) {
			randomX = (int)(1 + Math.random()*Lab.size);
			randomY = (int)(1 + Math.random()*Lab.size);
			randomX--;
			randomY--;
			if (Lab.lab[randomX][randomY] == ' ')
				break;
		}
		x_coord = randomX;
		y_coord = randomY;
		change_pos();
	}
	
	public void change_pos() {
		Lab.lab[x_coord][y_coord] = 'E';
	}
}

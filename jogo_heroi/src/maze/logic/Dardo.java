package maze.logic;

public class Dardo extends Object{
	boolean caught = false;

	Dardo(int x, int y) {
		super (x, y);
	}


	public void random_dardo() {
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
		change_dardo_pos();
	}

	public void change_dardo_pos() {
		if (!caught) {
			Lab.lab[x_coord][y_coord] = '\\';
		}
	}
}

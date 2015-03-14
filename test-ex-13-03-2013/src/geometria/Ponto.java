package geometria;

public class Ponto {
	private int x;
	private int y;
	
	public Ponto(int i, int j) {
		x = i;
		y = j;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean equals(Ponto p1) {
		if (this.getX() == p1.getX())
			if (this.getY() == p1.getY())
				return true;
		return false;
	}
	
}

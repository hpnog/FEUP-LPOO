package geometria;

public class Ponto implements Comparable<Ponto>{
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ponto))
			return false;
		Ponto other = (Ponto) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	@Override
	public int compareTo(Ponto a) {
		if (this.x > a.x)
			return 1;
		else if (this.x < a.x)
			return -1;
		else if (this.y > a.y)
			return 1;
		else if (this.y < a.y)
			return -1;
		return 0;
	}
}


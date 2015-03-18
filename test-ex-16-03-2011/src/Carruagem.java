
public class Carruagem {
	int numLugares;

	public Carruagem(int i) {
		numLugares = i;
	}

	public int getNumLugares() {
		return numLugares;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numLugares;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Carruagem))
			return false;
		Carruagem other = (Carruagem) obj;
		if (numLugares != other.numLugares)
			return false;
		return true;
	}

	

}

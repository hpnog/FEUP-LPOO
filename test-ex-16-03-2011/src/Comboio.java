import java.util.ArrayList;


public class Comboio {
	String nome;
	ArrayList <Carruagem> carruagens;
	int numLugares;
	int numPassageiros;
	ServicoABordo servico;

	public Comboio(String string) {
		nome = string;
		carruagens = new ArrayList <Carruagem> ();
		numLugares = 0;
		numPassageiros = 0;
		servico = new ServicoRegular();
	}

	public String getNome() {
		return nome;
	}

	public void addCarruagem(Carruagem a1) {
		carruagens.add(a1);
		numLugares += carruagens.get(carruagens.size()-1).getNumLugares();
	}

	public int getNumLugares() {
		return numLugares;
	}

	public int getNumCarruagens() {
		return carruagens.size();
	}

	public Carruagem getCarruagemByOrder(int i) {
		return carruagens.get(i);
	}

	public void removeAllCarruagens(int i) {
		for (int j = 0; j < carruagens.size(); j++) {
			if (carruagens.get(j).getNumLugares() == i) {
				carruagens.remove(j);
				j--;
				numLugares -= i;
			}
		}
	}

	
	public int getNumPassageiros() {
		return numPassageiros;
	}

	public int getLugaresLivres() {
		return (numLugares - numPassageiros);
	}


	public void addPassageiros(int i) throws PassengerCapacityExceeded {
		if ((numPassageiros + i) > numLugares)
			throw new PassengerCapacityExceeded();
		numPassageiros += i;
	}

	public void removePassageiros(int i) {
		numPassageiros -= i;
	}

	public void removePassageiros() {
		numPassageiros = 0;
	}

	
	@Override
	public String toString() {
		return "Comboio " + nome + ", " + carruagens.size() + " carruagens, "
				+ numLugares +
				" lugares, " +  numPassageiros + " passageiros";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((carruagens == null) ? 0 : carruagens.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Comboio))
			return false;
		Comboio other = (Comboio) obj;
		if (carruagens == null) {
			if (other.carruagens != null)
				return false;
		} else if (!carruagens.equals(other.carruagens))
			return false;
		return true;
	}

	public void setServicoABordo(ServicoABordo a) {
		servico = a;
	}

	public ServicoABordo getServicoABordo() {
		return servico;
	}

	public String getDescricaoServico() {
		return servico.getDescricao();
	}



}

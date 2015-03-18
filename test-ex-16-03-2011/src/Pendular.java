
public class Pendular extends Comboio {

	public Pendular(String string) {
		super(string);
		setServicoABordo(new ServicoSemEnjoos());
	}

	@Override
	public String toString() {
		return "Pendular [nome=" + nome + ", carruagens=" + carruagens
				+ ", numLugares=" + numLugares + ", numPassageiros="
				+ numPassageiros + "]";
	}

	
	
}

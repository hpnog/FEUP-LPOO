package geometria;

public class Circulo extends Figura{
	private Ponto po;
	private int raio;
	
	public Circulo(Ponto p, int i) {
		super((double)i*i*Math.PI, (double)2*Math.PI*i);
		po = p;
		raio = i;
	}

	public int getRaio() {
		return raio;
	}

	public Ponto getCentro() {
		return po;
	}

}

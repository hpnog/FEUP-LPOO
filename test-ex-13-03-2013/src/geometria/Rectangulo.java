package geometria;

public class Rectangulo extends Figura {
	private Ponto p1;
	private Ponto p2;
	private Ponto p3;
	private Ponto p4;
	
	public Rectangulo(int x1, int y1, int x2, int y2) {
		super((double)(abs(x2-x1)*abs(y2-y1)), (double)(2*abs(x2-x1)+2*abs(y2-y1)));
		p1 = new Ponto(x1,y1);
		p2 = new Ponto(x1,y2);
		p3 = new Ponto(x2,y1);
		p4 = new Ponto(x2,y2);
	}

	private static int abs(int i) {
		if (i < 0)
			return -i;
		else
			return i;
	}

}

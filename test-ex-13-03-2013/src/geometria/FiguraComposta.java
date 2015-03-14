package geometria;

public class FiguraComposta extends Figura {
	private Figura [] figs;
	public FiguraComposta(Figura[] figuras) {
		super(calc_area(figuras),calc_perimetro(figuras));
		figs = figuras;
	}
	
	
	private static double calc_perimetro(Figura[] figuras) {
		double perimetro = 0;
		for (int i = 0; i < figuras.length; i++) {
			perimetro += figuras[i].getPerimetro();
		}	
		return perimetro;
	}


	private static double calc_area(Figura[] figuras) {
		double area = 0;
		for (int i = 0; i < figuras.length; i++) {
			area += figuras[i].getArea();
		}
		return area;
	}


	public int count() {
		return figs.length;
	}

}

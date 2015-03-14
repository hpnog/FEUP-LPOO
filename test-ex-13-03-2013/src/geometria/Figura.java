package geometria;

public class Figura {
	private double area;
	private double perimetro;
	
	Figura(double a, double b) {
		area = a;
		perimetro = b;
	}
	
	public double getArea() {
		return area;
	}

	public double getPerimetro() {
		return perimetro;
	}

}

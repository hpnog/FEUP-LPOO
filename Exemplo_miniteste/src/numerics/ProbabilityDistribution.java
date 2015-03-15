package numerics;

public class ProbabilityDistribution {
	double stddev;
	double mean;
	
	ProbabilityDistribution() {}
	
	ProbabilityDistribution(double d, double e) {
		mean = d;
		if (e == 0)
			throw new IllegalArgumentException();
		stddev = e;
	}
	
	public double getMean() {
		return mean;
	}

	public double getStddev() {
		return stddev;
	}

	public double probabilityDensityFunction(double d) {
		return (1/(stddev*Math.sqrt(2*Math.PI))*Math.exp(-(((Math.pow((d-mean), 2)/(2*Math.pow(stddev, 2)))))));
	}

	public double calcRangeProbability(int i, int j) {
		return (probabilityDensityFunction(j)  probabilityDensityFunction(i));
	}
}

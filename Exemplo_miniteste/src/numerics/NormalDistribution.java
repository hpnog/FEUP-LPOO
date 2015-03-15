package numerics;

public class NormalDistribution extends ProbabilityDistribution {
	
	public NormalDistribution(double d, double e) {
		super(d, e);
	}

	public NormalDistribution() {
		super();
		stddev = 1.0;
		mean = 0.0;
	}

	

}

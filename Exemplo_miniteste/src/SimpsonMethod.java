package numerics;

/**
 * Class that implements the Simpson method for numerical integration.
 * 
 * @author João Pascoal Faria
 * @version 2.0
 * @created 26-Set-2011
 * 
 */
public class SimpsonMethod {

	/**
	 * Calculates an approximate value of the integral of a given function, between
	 * the given lower and upper limits, within a specified maximum error, by the
	 * Simpson method.
	 */
	public static double calcIntegral(Function func, double lower, double upper, double maxError){
		// work parameters
		int n = 10;  // number of segments, initially 10 (hard-coded)
		double g1;   // approximate integral value for n segments
		double g2;   // approximate integral value for 2*n segments
		
		if (lower > upper)
			throw new IllegalArgumentException("violates: lower <= upper");
		
		if (maxError <= 0.0)
			throw new IllegalArgumentException("violates: maxError > 0");		

		// Compute an approximate integral (g1) value with the previous method and n segments
		g1 = calcIntegralWithNumSegments(func, lower, upper, n);

		// Repeat until an acceptable approximation is found
		while(true) {
			g2 = calcIntegralWithNumSegments(func, lower, upper, 2*n);
		
			if (Math.abs(g2 - g1) <= maxError)
				return g2;
		
			g1 = g2;
			n *= 2;
		}
	}
	
	/**
	 * Auxiliary method.
	 * Calculates an approximate value of the integral of the function given, between
	 * the given lower and upper limits, using a specified number of segments, by the
	 * Simpson formula.
	 */
	private static double calcIntegralWithNumSegments(Function func, double lower, double upper, int numSegments){
		// Declare work parameters: width, oddSum, evenSum
		double width;
		double oddSum = 0.0, evenSum = 0.0;
		
		
		// Compute the segment with
		width = (upper - lower) / numSegments;
		
		// Sum up the function values for odd segments (1, ..., numSegments - 1),
		// and compute oddSum
		for ( int i = 1; i <= numSegments - 1; i += 2)
			oddSum += func.evaluate(lower + i * width);
		
		// Sum up the function values for even segments (2, ..., numSegments - 2),
		// and compute evenSum
		for ( int i = 2; i <= numSegments - 2; i += 2)
			evenSum += func.evaluate(lower + i * width);
		
		// Compute and return the final result			
		return width / 3 * (func.evaluate(lower) + 4 * oddSum + 2 * evenSum + func.evaluate(upper)); 
	}
}
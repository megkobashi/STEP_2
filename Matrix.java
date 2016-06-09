package matrix_multiplication;

class Matrix {
	private final static int ITERATIONS = 10;
	private final static int MAX_DIMENSIONS = 200;

	public static void main(String args[]) {
		// if (args.length != 1) {
		// System.out.println("usage: java Matrix N");
		// return;
		// }
		//
		// int n = Integer.parseInt(args[0]);
		
		double totalTime = 0;
		for (int n = 2; n <= MAX_DIMENSIONS; n++) {
			// Get multiple samples to compute average
			for (int i = 0; i < ITERATIONS; i++) {
				totalTime += multiplyMatrices(n);
			}
			System.out.printf("%.6f\n", totalTime / ITERATIONS);
		}
	}

	public static double multiplyMatrices(int n) {
		double[][] a = new double[n][n];
		double[][] b = new double[n][n];
		double[][] c = new double[n][n];
		// Initialize the matrices to some values.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = i * n + j;
				b[i][j] = j * n + i;
				c[i][j] = 0;
			}
		}
		
		long begin = System.currentTimeMillis();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					c[i][j] += a[i][k] * b[k][j];
				}
			}
		}

		// Print C for debugging. Comment out the print before measuring the
		// execution time.
//		double sum = 0;
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				sum += c[i][j];
//				// System.out.printf("c[%d][%d]=%f\n", i, j, c[i][j]);
//			}
//		}
		// Print out the sum of all values in C.
		// This should be 450 for N=3, 3680 for N=4, and 18250 for N=5.
//		System.out.printf("sum: %.6f\n", sum);
		long end = System.currentTimeMillis();
		return (end - begin) / 1000.0;
	}
}

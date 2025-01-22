package spacevisuals.functions;


public class Matrix {

	public static double[][] identity = new double[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};

	public static double[][] posXY(double rotationRate) {
		return new double[][] {{Math.cos(rotationRate), -Math.sin(rotationRate), 0},
							   {Math.sin(rotationRate),  Math.cos(rotationRate), 0},
							   {0, 0, 1}};
	}
	public static double[] posXYTest(double rotationRate, double[] input) {
		return new double[]{input[0]*Math.cos(rotationRate) - input[1]*Math.sin(rotationRate),
							input[0]*Math.sin(rotationRate) + input[1]*Math.cos(rotationRate),
							input[2]};
	}

	public static double[][] negXY(double rotationRate) {
		return new double[][] {{Math.cos(rotationRate), Math.sin(rotationRate), 0},
							   {-Math.sin(rotationRate), Math.cos(rotationRate), 0},
							   {0, 0, 1}};
	}
	public static double[] negXYTest(double rotationRate, double[] input) {
		return new double[]{input[0]*Math.cos(rotationRate) + input[1]*Math.sin(rotationRate),
							-input[0]*Math.sin(rotationRate) + input[1]*Math.cos(rotationRate),
							input[2]};
	}

	public static double[][] posYZ(double rotationRate) {
		return new double[][] {{1, 0, 0},
							   {0, Math.cos(rotationRate), -Math.sin(rotationRate)},
							   {0, Math.sin(rotationRate), Math.cos(rotationRate)}};
	}
	public static double[] posYZTest(double rotationRate, double[] input) {
		return new double[]{input[0],
							input[1]*Math.cos(rotationRate) - input[2]*Math.sin(rotationRate),
							input[1]*Math.sin(rotationRate) + input[2]*Math.cos(rotationRate)};
	}

	public static double[][] negYZ(double rotationRate) {
		return new double[][] {{1, 0, 0},
							   {0, Math.cos(rotationRate), Math.sin(rotationRate)},
							   {0, -Math.sin(rotationRate), Math.cos(rotationRate)}};
	}
	public static double[] negYZTest(double rotationRate, double[] input) {
		return new double[]{input[0],
							input[1]*Math.cos(rotationRate) + input[2]*Math.sin(rotationRate),
							-input[1]*Math.sin(rotationRate) + input[2]*Math.cos(rotationRate)};
	}

	public static double[][] posXZ(double rotationRate) {
		return new double[][] {{Math.cos(rotationRate), 0, Math.sin(rotationRate)},
							   {0, 1, 0},
							   {-Math.sin(rotationRate), 0, Math.cos(rotationRate)}};
	}
	public static double[] posXZTest(double rotationRate, double[] input) {
		return new double[]{input[0]*Math.cos(rotationRate) + input[2]*Math.sin(rotationRate),
							input[1],
							-input[0]*Math.sin(rotationRate) + input[2]*Math.cos(rotationRate)};
	}

	public static double[][] negXZ(double rotationRate) {
		return new double[][] {{Math.cos(rotationRate), 0, -Math.sin(rotationRate)},
							   {0, 1, 0},
							   {Math.sin(rotationRate), 0, Math.cos(rotationRate)}};
	}
	public static double[] negXZTest(double rotationRate, double[] input) {
		return new double[]{input[0]*Math.cos(rotationRate) - input[2]*Math.sin(rotationRate),
							input[1],
							input[0]*Math.sin(rotationRate) + input[2]*Math.cos(rotationRate)};
	}

	public static double[] matrixVectorMultiplication2x2(double[][] matrix, double[] vector) {
		double[] vectorNew = new double[2];
		vectorNew[0] = matrix[0][0] * vector[0] + matrix[0][1] * vector[1];
		vectorNew[1] = matrix[1][0] * vector[0] + matrix[1][1] * vector[1];
		return vectorNew;
	}

	public static double[] matrixVectorMultiplication(double[][] a, double[] x) {
		double[] vector = new double[a.length];
		for(int i = 0; i < a.length; ++i) {
			for(int j = 0; j < a[0].length; ++j) {
				vector[i] += a[i][j] * x[j];
			}
		}
		return vector;
	}

	public static double[][] matrixMatrixMultiplication(double[][] a, double[][] b) {
		/*
		 * matrix a = m x n
		 * matrix b = n x s
		 * 
		 * matrix product = ab = m x s
		 * 
		assert(a[0].length == b.length);
		double[][] productMatrix = new double[a.length][b[0].length];
		for(int i = 0; i < b[0].length; ++i){
			double[] vectorProd = matrixVectorMultiplication(a, b[i]);
		}*/

	    double value = 0;
	    double[][] product = new double[a.length][b[0].length];
	    for(int i = 0; i < product.length; ++i){
	      for(int j = 0; j < product[0].length; ++j){
	        value = 0;
	        for(int x = 0; x < b.length; ++x){
	          value += a[i][x]*b[x][j];
	        }
	        product[i][j] = value;
	      }
	    }
			return product;
	}

	static void printMatrix(double[][] matrix) {
		for(int i = 0; i < matrix.length; ++i) {
			for(int j = 0; j < matrix[0].length; ++j) {
				System.out.print(matrix[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		double[][] a = new double[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
		printMatrix(a);
	}

}

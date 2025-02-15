package spacevisuals.functions;

public class Matrix3D {

	public static double[][] identity = new double[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};

	public static double[][] XY(double angle) {
		return new double[][] {{Math.cos(angle), -Math.sin(angle), 0},
							   {Math.sin(angle),  Math.cos(angle), 0},
							   {0, 0, 1}};
	}
	
	public static double[] XYRotate(double angle, double[] input) {
		return new double[]{input[0]*Math.cos(angle) - input[1]*Math.sin(angle),
							input[0]*Math.sin(angle) + input[1]*Math.cos(angle),
							input[2]};
	}

	public static double[][] YZ(double angle) {
		return new double[][] {{1, 0, 0},
							   {0, Math.cos(angle), -Math.sin(angle)},
							   {0, Math.sin(angle), Math.cos(angle)}};
	}
	
	public static double[] YZRotate(double angle, double[] input) {
		return new double[]{input[0],
							input[1]*Math.cos(angle) - input[2]*Math.sin(angle),
							input[1]*Math.sin(angle) + input[2]*Math.cos(angle)};
	}

	public static double[][] XZ(double angle) {
		return new double[][] {{Math.cos(angle), 0, -Math.sin(angle)},
							   {0, 1, 0},
							   {Math.sin(angle), 0, Math.cos(angle)}};
	}
	
	public static double[] XZRotate(double angle, double[] input) {
		return new double[]{input[0]*Math.cos(angle) - input[2]*Math.sin(angle),
							input[1],
							input[0]*Math.sin(angle) + input[2]*Math.cos(angle)};
	}

	public static double[] matrixVectorR2x2_R2(double[][] matrix, double[] vector) {
		double[] vectorProd = new double[2];
		vectorProd[0] = matrix[0][0] * vector[0] + matrix[0][1] * vector[1];
		vectorProd[1] = matrix[1][0] * vector[0] + matrix[1][1] * vector[1];
		return vectorProd;
	}

	public static double[] matrixVectorRmxnRn_Rm(double[][] a, double[] x) {
		double[] vectorProd = new double[a.length];
		for(int i = 0; i < a.length; ++i) {
			vectorProd[i] = Rn_R.dotProduct(a[i], x);
		}
		return vectorProd;
	}

	public static double[][] matrixMatrixRmxnRnxp_Rmxp(double[][] a, double[][] b) {
	    double[][] matrixProd = new double[a.length][b[0].length];
	    double value = 0;
	    for(int i = 0; i < matrixProd.length; ++i){
	      for(int j = 0; j < matrixProd[0].length; ++j){
	        value = 0;
	        for(int x = 0; x < b.length; ++x){
	        	value += a[i][x]*b[x][j];
	        }
	        matrixProd[i][j] = value;
	    	}
	    }
		return matrixProd;
	}

	public static double[][] transposeMatrix(double[][] matrix) {
		double[][] transposed = new double[matrix[0].length][matrix.length];
		for(int i = 0; i < matrix[0].length; ++i) {
			for(int j = 0; j < matrix.length; ++j) {
				transposed[i][j] = matrix[j][i];
			}
		}
		return transposed;
	}

	public static void printMatrix(double[][] matrix) {
		int maxChars = 0;
		for(double[] row : matrix) {
			for(double element : row) {
				if(Double.toString(element).length() > maxChars) {
					maxChars = Double.toString(element).length();
				}
			}
		}
		int elementLength = 0;
		for(int i = 0; i < matrix.length; ++i) {
			System.out.print("[ ");
			for(int j = 0; j < matrix[0].length; ++j) {
				elementLength = Double.toString(matrix[i][j]).length();
				System.out.print(matrix[i][j]);
				for(int k = 0; k < maxChars - elementLength + 1; ++k) {
					System.out.print(" ");
				}
			}
			System.out.println("]");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		double[][] a = new double[][] {{1, 2, 3.1415926535}, {4, 5, 6}};
		printMatrix(a);
		System.out.println();
		printMatrix(transposeMatrix(a));
	}

}

package spacevisuals.functions;

public class Matrix {

    public static double[][] identity(int n) {
        double[][] identity = new double[n][n];
        for(int i = 0; i < n; ++i) {
            identity[i][i] = 1;
        }
        return identity;
    }

    public static double[][] add(double[][] a, double[][] b) {
        double[][] sum = new double[a.length][a[0].length];
        for(int i = 0; i < a.length; ++i) {
            for(int j = 0; j < a[0].length; ++j) {
                sum[i][j] = a[i][j] + b[i][j];
            }
        }
        return sum;
    }
    public static double[][] scalarMultiply(double[][] a, double scalar) {
        double[][] product = new double[a.length][a[0].length];
        for(int i = 0; i < a.length; ++i) {
            for(int j = 0; j < a[0].length; ++j) {
                product[i][j] = a[i][j] * scalar;
            }
        }
        return product;
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

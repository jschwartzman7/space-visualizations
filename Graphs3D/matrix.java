import edu.princeton.cs.introcs.StdDraw;

public class matrix {
	private static final int x = 0;
	private static final int y = 1;
	public static double rotationRate = Math.PI/32;
	public static double[][] posXY = new double[][] {{Math.cos(rotationRate), -Math.sin(rotationRate), 0},
	 											   {Math.sin(rotationRate),  Math.cos(rotationRate), 0},
	  											   {0,                         0,                              1}};

	public static double[][] negXY = new double[][] {{Math.cos(rotationRate), Math.sin(rotationRate), 0},
												   {-Math.sin(rotationRate), Math.cos(rotationRate), 0},
	 											   {0,                         0,                              1}};

	public static double[][] posYZ = new double[][] {{1,                        0,                              0},
	 											   {0, Math.cos(rotationRate), -Math.sin(rotationRate)},
	  											   {0, Math.sin(rotationRate), Math.cos(rotationRate)}};

	public static double[][] negYZ = new double[][] {{1,                           0,                             0},
												   {0, Math.cos(rotationRate), Math.sin(rotationRate)},
	 											   {0, -Math.sin(rotationRate), Math.cos(rotationRate)}};

	public static double[][] posXZ = new double[][] {{Math.cos(rotationRate), 0, Math.sin(rotationRate)},
												   {0,                          1,                            0},
	 											   {-Math.sin(rotationRate), 0, Math.cos(rotationRate)}};

	public static double[][] negXZ = new double[][] {{Math.cos(rotationRate), 0, -Math.sin(rotationRate)},
	 											   {0,                         1,                             0},
	  											   {Math.sin(rotationRate), 0, Math.cos(rotationRate)}};



	public static double[] matrixVectorMultiplication2x2(double[][] matrix, double[] vector) {
		double[] vectorNew = new double[2];
		vectorNew[x] = matrix[0][0] * vector[x] + matrix[0][1] * vector[y];
		vectorNew[y] = matrix[1][0] * vector[x] + matrix[1][1] * vector[y];
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
		 
		assert(a[0].length == b.length);
		double[][] productMatrix = new double[a.length][b[0].length];
		for(int i = 0; i < b[0].length; ++i){
			double[] vectorProd = matrixVectorMultiplication(a, b[i]);
		}*/


	    int n = a.length;
	    int value = 0;
	    double[][] product = new double[n][n];
	    for(int i = 0; i < n; ++i){
	      for(int j = 0; j < n; ++j){
	        value = 0;
	        for(int x = 0; x < n; ++x){
	          value += a[i][x]*b[x][j];
	        }
	        product[i][j] = value;
	      }
	    }
			return product;
	}

	public static void main(String[] args) {
		double[] damn = new double[12];
		System.out.println(damn[10]);
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-10, 10);
		StdDraw.line(0, -10, 0, 10);
		StdDraw.line(-10, 0, 10, 0);
		double[] vector = new double[2];
		double[] xCords = new double[] {2, -2, -2, 2};
		double[] yCords = new double[] {2, 2, -2, -2};
		double[][] matrix = new double[][] {{Math.cos(Math.PI/16), -Math.sin(Math.PI/16)}, {Math.sin(Math.PI/16), Math.cos(Math.PI/16)}};
		while(true) {
			StdDraw.clear();
			//StdDraw.setPenColor(Color.BLUE);
			StdDraw.polygon(xCords, yCords);
		//	StdDraw.filledCircle(vector[x], vector[y], .25);
			StdDraw.line(0, -10, 0, 10);
			StdDraw.line(-10, 0, 10, 0);
			//StdDraw.setPenColor(Color.GREEN);
			//StdDraw.text(vector[x], vector[y], ""+i);
			//System.out.println("(" + vector[x] + ", " + vector[y] + ")");
			while(StdDraw.isKeyPressed(32));
			/*for(int i = 0; i < xCords.length; ++i) {
				vector[0] = xCords[i];
				vector[1] = yCords[i];
				vector = matrixVectorMultiplication(matrix, vector);
				xCords[i] = vector[0];
				yCords[i] = vector[1];
			}*/
		//	vector = matrixVectorMultiplication(matrix, vector);
			StdDraw.show(50);
		}
//		int[] vector = new int[] {4, 8};
//		int[][] matrix = new int[][] {{5, 3}, {1, 7}};
//		System.out.println(matrixVectorMultiplication(matrix, vector)[0]);
//		System.out.println(matrixVectorMultiplication(matrix, vector)[1]);

	}

}

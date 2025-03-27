package spacevisuals.functions;

public class Matrix3D extends Matrix {

	public static double[][] XY(double angle) {
		return new double[][] {{Math.cos(angle), -Math.sin(angle), 0},
							   {Math.sin(angle),  Math.cos(angle), 0},
							   {0, 0, 1}};
	}

	public static double[][] YZ(double angle) {
		return new double[][] {{1, 0, 0},
							   {0, Math.cos(angle), -Math.sin(angle)},
							   {0, Math.sin(angle), Math.cos(angle)}};
	}

	public static double[][] XZ(double angle) {
		return new double[][] {{Math.cos(angle), 0, -Math.sin(angle)},
							   {0, 1, 0},
							   {Math.sin(angle), 0, Math.cos(angle)}};
	}

	public static double[] crossProduct(double[] vector1, double[] vector2) {
		double[] crossProduct = new double[3];
		crossProduct[0] = vector1[1] * vector2[2] - vector1[2] * vector2[1];
		crossProduct[1] = vector1[2] * vector2[0] - vector1[0] * vector2[2];
		crossProduct[2] = vector1[0] * vector2[1] - vector1[1] * vector2[0];
		return crossProduct;
	}

	public static double[] matrixVectorR2x2_R2(double[][] matrix, double[] vector) {
		double[] vectorProd = new double[2];
		vectorProd[0] = matrix[0][0] * vector[0] + matrix[0][1] * vector[1];
		vectorProd[1] = matrix[1][0] * vector[0] + matrix[1][1] * vector[1];
		return vectorProd;
	}
}

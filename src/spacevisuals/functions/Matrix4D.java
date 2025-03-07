package spacevisuals.functions;

public class Matrix4D extends Matrix{
    
    public static double[][] XY(double angle) {
		return new double[][] {{Math.cos(angle), -Math.sin(angle), 0, 0},
							   {Math.sin(angle),  Math.cos(angle), 0, 0},
							   {0, 0, 1, 0},
							   {0, 0, 0, 1}};
	}
	public static double[][] XZ(double angle) {
		return new double[][] {{Math.cos(angle), 0, -Math.sin(angle), 0},
							   {0, 1, 0, 0},
							   {Math.sin(angle), 0, Math.cos(angle), 0},
							   {0, 0, 0, 1}};
	}
	public static double[][] XW(double angle) {
		return new double[][] {{Math.cos(angle), 0, 0, -Math.sin(angle)},
							   {0, 1, 0, 0},
							   {0, 0, 1, 0},
							   {Math.sin(angle), 0, 0, Math.cos(angle)}};
	}
	
    public static double[][] YZ(double angle) {
		return new double[][] {{1, 0, 0, 0},
							   {0, Math.cos(angle), -Math.sin(angle), 0},
							   {0, Math.sin(angle), Math.cos(angle), 0},
							   {0, 0, 0, 1}};
	}
	public static double[][] YW(double angle) {
		return new double[][] {{1, 0, 0, 0},
							   {0, Math.cos(angle), 0, -Math.sin(angle)},
							   {0, 0, 1, 0},
							   {0, Math.sin(angle), 0, Math.cos(angle)}};
	}
	public static double[][] ZW(double angle) {
		return new double[][] {{1, 0, 0, 0},
							   {0, 1, 0, 0},
							   {0, 0, Math.cos(angle), -Math.sin(angle)},
							   {0, 0, Math.sin(angle), Math.cos(angle)}};
	}

   

    public static double[][] XY2x4(double angle) {
		return new double[][] {{Math.cos(angle), -Math.sin(angle), 0, 0},
							   {Math.sin(angle),  Math.cos(angle), 0, 0}};
	}
    public static double[][] XZ2x4(double angle) {
		return new double[][] {{Math.cos(angle), 0, -Math.sin(angle), 0},
							   {Math.sin(angle),  0, Math.cos(angle), 0}};
	}
    public static double[][] XW2x4(double angle) {
		return new double[][] {{Math.cos(angle), 0, 0, -Math.sin(angle)},
							   {Math.sin(angle), 0, 0, Math.cos(angle)}};
	}

    public static double[][] YZ2x4(double angle) {
		return new double[][] {{0, Math.cos(angle), -Math.sin(angle), 0},
							   {0, Math.sin(angle),  Math.cos(angle), 0}};
	}
    public static double[][] YW2x4(double angle) {
		return new double[][] {{0, Math.cos(angle), 0, -Math.sin(angle)},
							   {0, Math.sin(angle), 0, Math.cos(angle)}};
	}
    public static double[][] ZW2x4(double angle) {
		return new double[][] {{0, 0, Math.cos(angle), -Math.sin(angle)},
							   {0, 0, Math.sin(angle),  Math.cos(angle)}};
	}
}


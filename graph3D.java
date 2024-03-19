

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;
//import support.cse131.ArgsProcessor;
//import ArgsProcessor

/*
 * Refactoring Plan:
 * 
 * Class for functions, inputs
 * Class for graph base visuals and key input
 * Class for high level animation loop that calls functions 
 * 
 */

public class graph3D {

	public static final double BOX_DIAMETER = 30;
	public static final double DEFAULT_SCALE = 25;
	public static double scale = DEFAULT_SCALE;
    public static double X_MIN = -10;
    public static double X_MAX = 10;
    public static double Y_MIN = -10;
    public static double Y_MAX = 10;
	public static double[][] posXY = matrix.posXY;
	public static double[][] negXY = matrix.negXY;
	public static double[][] posYZ = matrix.posYZ;
	public static double[][] negYZ = matrix.negYZ;
	public static double[][] posXZ = matrix.posXZ;
	public static double[][] negXZ = matrix.negXZ;
	public static double[][] xaxis;
	public static double[][] yaxis;
	public static double[][] zaxis;
	public static LinkedList<double[][]> gridCurrent = new LinkedList<>();
	public static HashSet<double[]> pointsCurrent = new HashSet<>();
	public static Hashtable<Double[], Double> pointColors = new Hashtable<>();
	public static ArrayList<double[][]> gridStart = new ArrayList<>();
	public static ArrayList<double[]> pointsStart = new ArrayList<>();
	public static functions3D functions = new functions3D();
	//                                         t^3, t^2,t,  1, cos, sin,t^.5

	public static double[] to2D(double[] cord) {
		double[] cord2D = new double[] {Math.sqrt(3)/2*(cord[1]-cord[0]), cord[2] - .5*(cord[1]+cord[0])};
		//double[] cord2D = new double[] {Math.sqrt(3)/2*(cord[1]-cord[0]), Math.cos(cord[2]) - .5*(cord[1]+cord[0])};
	//	StdDraw.filledCircle(cord2D[0], cord2D[1], 0.2);
		return cord2D;
	}

	/*public static void addGridLines() {
		for(double i = -10; i <= 10; i+=2) {
			for(double j = -10; j <= 10; j+=2) {
					gridCurrent.add(new double[][] {{-10, i, j}, {10, i, j}});
					gridCurrent.add(new double[][] {{i, -10, j}, {i, 10, j}});
					gridCurrent.add(new double[][] {{i, j, -10}, {i, j, 10}});
			}
		}
	}*/

	public static void addAxis() {
		xaxis = new double[][] {{-BOX_DIAMETER, 0, 0}, {BOX_DIAMETER, 0, 0}};
		yaxis = new double[][] {{0, -BOX_DIAMETER, 0}, {0,BOX_DIAMETER, 0}};
		zaxis = new double[][] {{0, 0, -BOX_DIAMETER}, {0, 0, BOX_DIAMETER}};
		double[][] xaxisCopy = new double[][] {{-BOX_DIAMETER, 0, 0}, {BOX_DIAMETER, 0, 0}};
		double[][] yaxisCopy = new double[][] {{0, -BOX_DIAMETER, 0}, {0,BOX_DIAMETER, 0}};
		double[][] zaxisCopy = new double[][] {{0, 0, -BOX_DIAMETER}, {0, 0, BOX_DIAMETER}};
		gridCurrent.add(xaxis);
		gridCurrent.add(yaxis);
		gridCurrent.add(zaxis);
		gridStart.add(xaxisCopy);
		gridStart.add(yaxisCopy);
		gridStart.add(zaxisCopy);
	}

	public static void addEdges() {
		/*grid.add(new double[][] {{-BOX_DIAMETER, -BOX_DIAMETER, -BOX_DIAMETER}, {-BOX_DIAMETER, -BOX_DIAMETER, BOX_DIAMETER}});
		grid.add(new double[][] {{BOX_DIAMETER, -BOX_DIAMETER, -BOX_DIAMETER}, {BOX_DIAMETER, -BOX_DIAMETER, BOX_DIAMETER}});
		grid.add(new double[][] {{-BOX_DIAMETER, BOX_DIAMETER, -BOX_DIAMETER}, {-BOX_DIAMETER, BOX_DIAMETER, BOX_DIAMETER}});
		grid.add(new double[][] {{BOX_DIAMETER, BOX_DIAMETER, -BOX_DIAMETER}, {BOX_DIAMETER, BOX_DIAMETER, BOX_DIAMETER}});

		grid.add(new double[][] {{-BOX_DIAMETER, -BOX_DIAMETER, BOX_DIAMETER}, {BOX_DIAMETER, -BOX_DIAMETER, BOX_DIAMETER}});
		grid.add(new double[][] {{-BOX_DIAMETER, BOX_DIAMETER, BOX_DIAMETER}, {BOX_DIAMETER, BOX_DIAMETER, BOX_DIAMETER}});
		grid.add(new double[][] {{-BOX_DIAMETER, -BOX_DIAMETER, BOX_DIAMETER}, {-BOX_DIAMETER, BOX_DIAMETER, BOX_DIAMETER}});
		grid.add(new double[][] {{BOX_DIAMETER, -BOX_DIAMETER, BOX_DIAMETER}, {BOX_DIAMETER, BOX_DIAMETER, BOX_DIAMETER}});

		grid.add(new double[][] {{-BOX_DIAMETER, -BOX_DIAMETER, -BOX_DIAMETER}, {BOX_DIAMETER, -BOX_DIAMETER, -BOX_DIAMETER}});
		grid.add(new double[][] {{-BOX_DIAMETER, BOX_DIAMETER, -BOX_DIAMETER}, {BOX_DIAMETER, BOX_DIAMETER, -BOX_DIAMETER}});
		grid.add(new double[][] {{-BOX_DIAMETER, -BOX_DIAMETER, -BOX_DIAMETER}, {-BOX_DIAMETER, BOX_DIAMETER, -BOX_DIAMETER}});
		grid.add(new double[][] {{BOX_DIAMETER, -BOX_DIAMETER, -BOX_DIAMETER}, {BOX_DIAMETER, BOX_DIAMETER, -BOX_DIAMETER}});


		gridStart.add(new double[][] {{-BOX_DIAMETER, -BOX_DIAMETER, -BOX_DIAMETER}, {-BOX_DIAMETER, -BOX_DIAMETER, BOX_DIAMETER}});
		gridStart.add(new double[][] {{BOX_DIAMETER, -BOX_DIAMETER, -BOX_DIAMETER}, {BOX_DIAMETER, -BOX_DIAMETER, BOX_DIAMETER}});
		gridStart.add(new double[][] {{-BOX_DIAMETER, BOX_DIAMETER, -BOX_DIAMETER}, {-BOX_DIAMETER, BOX_DIAMETER, BOX_DIAMETER}});
		gridStart.add(new double[][] {{BOX_DIAMETER, BOX_DIAMETER, -BOX_DIAMETER}, {BOX_DIAMETER, BOX_DIAMETER, BOX_DIAMETER}});

		gridStart.add(new double[][] {{-BOX_DIAMETER, -BOX_DIAMETER, BOX_DIAMETER}, {BOX_DIAMETER, -BOX_DIAMETER, BOX_DIAMETER}});
		gridStart.add(new double[][] {{-BOX_DIAMETER, BOX_DIAMETER, BOX_DIAMETER}, {BOX_DIAMETER, BOX_DIAMETER, BOX_DIAMETER}});
		gridStart.add(new double[][] {{-BOX_DIAMETER, -BOX_DIAMETER, BOX_DIAMETER}, {-BOX_DIAMETER, BOX_DIAMETER, BOX_DIAMETER}});
		gridStart.add(new double[][] {{BOX_DIAMETER, -BOX_DIAMETER, BOX_DIAMETER}, {BOX_DIAMETER, BOX_DIAMETER, BOX_DIAMETER}});

		gridStart.add(new double[][] {{-BOX_DIAMETER, -BOX_DIAMETER, -BOX_DIAMETER}, {BOX_DIAMETER, -BOX_DIAMETER, -BOX_DIAMETER}});
		gridStart.add(new double[][] {{-BOX_DIAMETER, BOX_DIAMETER, -BOX_DIAMETER}, {BOX_DIAMETER, BOX_DIAMETER, -BOX_DIAMETER}});
		gridStart.add(new double[][] {{-BOX_DIAMETER, -BOX_DIAMETER, -BOX_DIAMETER}, {-BOX_DIAMETER, BOX_DIAMETER, -BOX_DIAMETER}});
		gridStart.add(new double[][] {{BOX_DIAMETER, -BOX_DIAMETER, -BOX_DIAMETER}, {BOX_DIAMETER, BOX_DIAMETER, -BOX_DIAMETER}}); */
	}

	public static void addPoints() {
		// x^3, x^2, x, 1, cos(x), sin(x), x^.5
		//  0    1   2  3    4       5      6
		for(double i = X_MIN; i < X_MAX; i += 0.25) {
			for(double j = Y_MIN; j < Y_MAX; j += 0.25) {
				pointsCurrent.add(new double[]{i, j, functions3D.f(i, j)});
				pointsStart.add(new double[]{i, j, functions3D.f(i, j)});
				pointColors.put(new Double[]{i, j}, 37.0);
				//points.add(new double[] {i, j, xEqn[0]*i*i*i + xEqn[1]*i*i + xEqn[2]*i + xEqn[3] + xEqn[4]*Math.cos(i)+ xEqn[5]*Math.sin(i) + xEqn[6]*Math.pow(Math.abs(i), .5) + yEqn[0]*j*j*j + yEqn[1]*j*j + yEqn[2]*j + yEqn[3] + yEqn[4]*Math.cos(j)+ yEqn[5]*Math.sin(j) + yEqn[6]*Math.pow(Math.abs(j), .5)});
				//pointsStart.add(new double[] {i, j, xEqn[0]*i*i*i + xEqn[1]*i*i + xEqn[2]*i + xEqn[3] + xEqn[4]*Math.cos(i)+ xEqn[5]*Math.sin(i) + yEqn[0]*j*j*j + yEqn[1]*j*j + yEqn[2]*j + yEqn[3] + yEqn[4]*Math.cos(j)+ yEqn[5]*Math.sin(j)});
			}
		}
	}

	/*public static boolean setScale(boolean setScale) {
		StdDraw.setScale();
		StdDraw.setPenColor();
		StdDraw.rectangle(.85, .05, .09, .035);
		StdDraw.text(.85, .05, "Set Scale");
		while(StdDraw.mousePressed() && StdDraw.mouseX()>.76) {// && StdDraw.mouseX()<.94 && StdDraw.mouseY()>.015 && StdDraw.mouseX()<.085) {
			System.out.println(StdDraw.mouseX());
			setScale = true;
		}
		StdDraw.setScale(-scale, scale);
		return setScale;
	}*/

	public static void drawLines() {
		for(double[][] line : gridCurrent) {
			StdDraw.setPenRadius(0.0007);
			StdDraw.setPenColor();
			if(line.equals(xaxis) || line.equals(yaxis) || line.equals(zaxis)) {
				StdDraw.setPenRadius(0.004);
				if(line.equals(xaxis)) {
					StdDraw.text(1.1*to2D(line[1])[0], 1.1*to2D(line[1])[1], "x");
					StdDraw.setPenColor(Color.BLUE);
				}
				if(line.equals(yaxis)) {
					StdDraw.text(1.1*to2D(line[1])[0], 1.1*to2D(line[1])[1], "y");
					StdDraw.setPenColor(Color.GREEN);
				}
				if(line.equals(zaxis)) {
					StdDraw.text(1.1*to2D(line[1])[0], 1.1*to2D(line[1])[1], "z");
					StdDraw.setPenColor(Color.RED);
				}
			}
			double[] linePointOne = to2D(line[0]);
			double[] linePointTwo = to2D(line[1]);
			StdDraw.line(linePointOne[0], linePointOne[1], linePointTwo[0], linePointTwo[1]);
		}
	}

	public static void drawPoints() {
		StdDraw.setPenColor();
		for(double[] point : pointsCurrent) {
			double[] point2D = to2D(point);
			StdDraw.filledCircle(point2D[0], point2D[1], 0.1);
	//		threeDimensionalCords.drawCord(point[0], point[1], point[2]);
		}
	}

	public static void resetOrientation(){
//		grid = gridStart;
//		points = pointsStart;
		pointsCurrent.clear();
		for(double[] point : pointsStart) {
			pointsCurrent.add(new double[] {point[0], point[1], point[2]});
		}
		int i = 0;
		for(double[][] line : gridCurrent) {
			line[0][0] = gridStart.get(i)[0][0];
			line[0][1] = gridStart.get(i)[0][1];
			line[0][2] = gridStart.get(i)[0][2];
			line[1][0] = gridStart.get(i)[1][0];
			line[1][1] = gridStart.get(i)[1][1];
			line[1][2] = gridStart.get(i)[1][2];
			++i;
		}
	}

	public static void rotateGrid() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			for(double[][] line : gridCurrent) {
				line[0] = matrix.matrixVectorMultiplication(posXY, line[0]);
				line[1] = matrix.matrixVectorMultiplication(posXY, line[1]);
			}
			for(double[] point : pointsCurrent) {
				double[] newPoint = matrix.matrixVectorMultiplication(posXY, point);
				point[0] = newPoint[0];
				point[1] = newPoint[1];
				point[2] = newPoint[2];
			}
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			for(double[][] line : gridCurrent) {
				line[0] = matrix.matrixVectorMultiplication(negXY, line[0]);
				line[1] = matrix.matrixVectorMultiplication(negXY, line[1]);
			}
			for(double[] point : pointsCurrent) {
				double[] newPoint = matrix.matrixVectorMultiplication(negXY, point);
				point[0] = newPoint[0];
				point[1] = newPoint[1];
				point[2] = newPoint[2];
			}
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			for(double[][] line : gridCurrent) {
				line[0] = matrix.matrixVectorMultiplication(posXZ, line[0]);
				line[1] = matrix.matrixVectorMultiplication(posXZ, line[1]);
			}
			for(double[] point : pointsCurrent) {
				double[] newPoint = matrix.matrixVectorMultiplication(posXZ, point);
				point[0] = newPoint[0];
				point[1] = newPoint[1];
				point[2] = newPoint[2];
			}
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			for(double[][] line : gridCurrent) {
				line[0] = matrix.matrixVectorMultiplication(negXZ, line[0]);
				line[1] = matrix.matrixVectorMultiplication(negXZ, line[1]);
			}
			for(double[] point : pointsCurrent) {
				double[] newPoint = matrix.matrixVectorMultiplication(negXZ, point);
				point[0] = newPoint[0];
				point[1] = newPoint[1];
				point[2] = newPoint[2];
			}
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
			for(double[][] line : gridCurrent) {
				line[0] = matrix.matrixVectorMultiplication(posYZ, line[0]);
				line[1] = matrix.matrixVectorMultiplication(posYZ, line[1]);
			}
			for(double[] point : pointsCurrent) {
				double[] newPoint = matrix.matrixVectorMultiplication(posYZ, point);
				point[0] = newPoint[0];
				point[1] = newPoint[1];
				point[2] = newPoint[2];
			}
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
			for(double[][] line : gridCurrent) {
				line[0] = matrix.matrixVectorMultiplication(negYZ, line[0]);
				line[1] = matrix.matrixVectorMultiplication(negYZ, line[1]);
			}
			for(double[] point : pointsCurrent) {
				double[] newPoint = matrix.matrixVectorMultiplication(negYZ, point);
				point[0] = newPoint[0];
				point[1] = newPoint[1];
				point[2] = newPoint[2];
			}
		}
	}

	public static void zoomGraph() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_UP)) { // zoom out
			++scale;
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && scale > 1) { // zoom in
			--scale;
		}
	}

	public static void main(String[] args) {
		//ArgsProcessor ap = new ArgsProcessor(args);
		StdDraw.enableDoubleBuffering();
		addAxis();
		addEdges();
		addPoints();
//		addGridLines();
		while(true) {
			StdDraw.clear();
			StdDraw.setScale(-scale, scale);
			drawLines();
			drawPoints();

			if(StdDraw.isKeyPressed(KeyEvent.VK_R)) {
				resetOrientation();
				scale = DEFAULT_SCALE;
			}

			zoomGraph();

		//	StdDraw.setScale(-scale, scale);

			/*if(setScale) {
				double xMin = ap.nextDouble("xMin");
				double xMax = ap.nextDouble("xMax");
				double yMin = ap.nextDouble("yMin");
				double yMax = ap.nextDouble("yMax");
				setEdges(xMin, xMax, yMin, yMax);
				setPoints(xMin, xMax, yMin, yMax);
				setScale = false;
			}*/


			rotateGrid();




			StdDraw.show(30);
		}
	}
}

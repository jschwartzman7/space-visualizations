import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.LinkedList;

import javax.swing.LayoutFocusTraversalPolicy;

import edu.princeton.cs.introcs.StdDraw;

public class euclideanR3 implements abstractSpaceVisuals{


    
    public int DEFAULT_XY_SCALE;
    public static double X_MIN;
    public static double X_MAX;
    public static double Y_MIN;
    public static double Y_MAX;
    public static double Z_MIN;
    public static double Z_MAX;
    public static double zoomScale = 5;
    private int labelInterval;
    private boolean viewLabels;
    LinkedList<double[][]> lines = new LinkedList<double[][]>();

    public euclideanR3(int defaultScale, int labelInterval, boolean viewLabels){
        DEFAULT_XY_SCALE = defaultScale;
        X_MIN = -defaultScale;
        X_MAX = defaultScale;
        Y_MIN = -defaultScale;
        Y_MAX = defaultScale;
        Z_MIN = -defaultScale;
        Z_MAX = defaultScale;
        this.labelInterval = labelInterval;
        this.viewLabels = viewLabels;
        lines.add(new double[][]{{-defaultScale, 0, 0}, {defaultScale, 0, 0}});
        lines.add(new double[][]{{0, -defaultScale, 0}, {0, defaultScale, 0}});
        lines.add(new double[][]{{0, 0, -defaultScale}, {0, 0, defaultScale}});
    }


    public euclideanR3(){
        int defaultScale = 5;
        DEFAULT_XY_SCALE = defaultScale;
        X_MIN = -defaultScale;
        X_MAX = defaultScale;
        Y_MIN = -defaultScale;
        Y_MAX = defaultScale;
        Z_MIN = -defaultScale;
        Z_MAX = defaultScale;
        this.labelInterval = 0;
        this.viewLabels = false;
        lines.add(new double[][]{{-defaultScale, 0, 0}, {defaultScale, 0, 0}});
        lines.add(new double[][]{{0, -defaultScale, 0}, {0, defaultScale, 0}});
        lines.add(new double[][]{{0, 0, -defaultScale}, {0, 0, defaultScale}});
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-DEFAULT_XY_SCALE, DEFAULT_XY_SCALE);
    }

	public double[] to2D(double[] cord) {
		double[] cord2D = new double[] {Math.sqrt(3)/2*(cord[1]-cord[0]), cord[2] - .5*(cord[1]+cord[0])};
		return cord2D;
	}

    public void draw(){
        StdDraw.setPenColor();
        StdDraw.text(1.1*to2D(lines.get(0)[1])[0], 1.1*to2D(lines.get(0)[1])[1], "x");
		StdDraw.setPenColor(Color.blue);
        double[] xaxisP1 = to2D(lines.get(0)[0]);
        double[] xaxisP2 = to2D(lines.get(0)[1]);
        StdDraw.line(xaxisP1[0], xaxisP1[1], xaxisP2[0], xaxisP2[1]);

        StdDraw.setPenColor();
        StdDraw.text(1.1*to2D(lines.get(1)[1])[0], 1.1*to2D(lines.get(1)[1])[1], "y");
		StdDraw.setPenColor(Color.green);
        double[] yaxisP1 = to2D(lines.get(1)[0]);
        double[] yaxisP2 = to2D(lines.get(1)[1]);
        StdDraw.line(yaxisP1[0], yaxisP1[1], yaxisP2[0], yaxisP2[1]);

        StdDraw.setPenColor();
        StdDraw.text(1.1*to2D(lines.get(2)[1])[0], 1.1*to2D(lines.get(2)[1])[1], "z");
		StdDraw.setPenColor(Color.red);
        double[] zaxisP1 = to2D(lines.get(2)[0]);
        double[] zaxisP2 = to2D(lines.get(2)[1]);
        StdDraw.line(zaxisP1[0], zaxisP1[1], zaxisP2[0], zaxisP2[1]);

    }

    public void updatePoints(LinkedList<double[]> points){

        if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			for(double[] point : points) {
				double[] newPoint = matrix.matrixVectorMultiplication(matrix.posXY, point);
                point[0] = newPoint[0];
                point[1] = newPoint[1];
                point[2] = newPoint[2];
			}
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			for(double[] point : points) {
				double[] newPoint = matrix.matrixVectorMultiplication(matrix.negXY, point);
                point[0] = newPoint[0];
                point[1] = newPoint[1];
                point[2] = newPoint[2];
			}
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			for(double[] point : points) {
				double[] newPoint = matrix.matrixVectorMultiplication(matrix.posXZ, point);
                point[0] = newPoint[0];
                point[1] = newPoint[1];
                point[2] = newPoint[2];
			}
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			for(double[] point : points) {
				double[] newPoint = matrix.matrixVectorMultiplication(matrix.negXZ, point);
                point[0] = newPoint[0];
                point[1] = newPoint[1];
                point[2] = newPoint[2];
			}
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
			for(double[] point : points) {
				double[] newPoint = matrix.matrixVectorMultiplication(matrix.posYZ, point);
                point[0] = newPoint[0];
                point[1] = newPoint[1];
                point[2] = newPoint[2];
			}
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
			for(double[] point : points) {
				double[] newPoint = matrix.matrixVectorMultiplication(matrix.negYZ, point);
                point[0] = newPoint[0];
                point[1] = newPoint[1];
                point[2] = newPoint[2];
			}
		}
    }



    public void update(){

        if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			for(double[][] line : lines) {
				line[0] = matrix.matrixVectorMultiplication(matrix.posXY, line[0]);
				line[1] = matrix.matrixVectorMultiplication(matrix.posXY, line[1]);
			}
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			for(double[][] line : lines) {
				line[0] = matrix.matrixVectorMultiplication(matrix.negXY, line[0]);
				line[1] = matrix.matrixVectorMultiplication(matrix.negXY, line[1]);
			}
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			for(double[][] line : lines) {
				line[0] = matrix.matrixVectorMultiplication(matrix.posXZ, line[0]);
				line[1] = matrix.matrixVectorMultiplication(matrix.posXZ, line[1]);
			}
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			for(double[][] line : lines) {
				line[0] = matrix.matrixVectorMultiplication(matrix.negXZ, line[0]);
				line[1] = matrix.matrixVectorMultiplication(matrix.negXZ, line[1]);
			}
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
			for(double[][] line : lines) {
				line[0] = matrix.matrixVectorMultiplication(matrix.posYZ, line[0]);
				line[1] = matrix.matrixVectorMultiplication(matrix.posYZ, line[1]);
			}
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
			for(double[][] line : lines) {
				line[0] = matrix.matrixVectorMultiplication(matrix.negYZ, line[0]);
				line[1] = matrix.matrixVectorMultiplication(matrix.negYZ, line[1]);
			}
		}
        if (StdDraw.isKeyPressed(KeyEvent.VK_R)){
            X_MIN = -DEFAULT_XY_SCALE;
            X_MAX = DEFAULT_XY_SCALE;
            Y_MIN = -DEFAULT_XY_SCALE;
            Y_MAX = DEFAULT_XY_SCALE;
        }

        if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
            zoomScale++;
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
            if(zoomScale > 1){
                zoomScale--;
            }
            
        }

        StdDraw.setScale(-zoomScale, zoomScale);

	}


    public void resetDraw(){

    };


}


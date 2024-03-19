import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;

public class euclideanR3 implements abstractSpaceVisuals{


    private double labelInterval;
    private boolean viewLabels;
    private int maxInput = 10;
    private double[][] xaxis = new double[][] {{-maxInput, 0, 0}, {maxInput, 0, 0}};
    private double[][] yaxis = new double[][] {{0, -maxInput, 0}, {0,maxInput, 0}};
    private double[][] zaxis = new double[][] {{0, 0, -maxInput}, {0, 0, maxInput}};
    LinkedList<double[][]> lines = new LinkedList<double[][]>();

    public euclideanR3(double labelInterval, boolean viewLabels){
        this.labelInterval = labelInterval;
        this.viewLabels = viewLabels;
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-2*maxInput, 2*maxInput);
        lines.add(xaxis);
        lines.add(yaxis);
        lines.add(zaxis);
    }


    public void drawGrid(){
        StdDraw.setPenColor();
        for(double i = -maxInput/2.0; i <= maxInput/2.0; ++i){
            for(double j = -maxInput/2.0; j <= maxInput/2.0; ++j){
                for(double k = -maxInput/2.0; k <= maxInput/2.0; ++k){
                    double[] point = new double[]{i, j, k};
                    StdDraw.point(to2D(point)[0], to2D(point)[1]);
                }
            }
        }
        for(double i = -maxInput/2.0; i <= maxInput/2.0; i+=4){
            for(double j = -maxInput/2.0; j <= maxInput/2.0; j+=4){
                for(double k = -maxInput/2.0; k <= maxInput/2.0; k+=4){
                    //double[] point = new double[]{i, j, k};
                    double[] pointX1 = new double[]{maxInput, j, k};
                    double[] pointX2 = new double[]{-maxInput, j, k};
                    double[] pointY1 = new double[]{i, maxInput, k};
                    double[] pointY2 = new double[]{i, -maxInput, k};
                    double[] pointZ1 = new double[]{i, j, maxInput};
                    double[] pointZ2 = new double[]{i, j, -maxInput};
                    
                    StdDraw.line(to2D(pointX1)[0], to2D(pointX1)[1], to2D(pointX2)[0], to2D(pointX2)[1]);
                    StdDraw.line(to2D(pointY1)[0], to2D(pointY1)[1], to2D(pointY2)[0], to2D(pointY2)[1]);
                    StdDraw.line(to2D(pointZ1)[0], to2D(pointZ1)[1], to2D(pointZ2)[0], to2D(pointZ2)[1]);
                    //StdDraw.line(0, 0, 0, 0);
                }
            }
        }
    }

	public double[] to2D(double[] cord) {
		double[] cord2D = new double[] {Math.sqrt(3)/2*(cord[1]-cord[0]), cord[2] - .5*(cord[1]+cord[0])};
		return cord2D;
	}

    public void draw(){
        drawGrid();
        StdDraw.setPenColor();
        StdDraw.text(1.1*to2D(xaxis[1])[0], 1.1*to2D(xaxis[1])[1], "x");
		StdDraw.setPenColor(Color.blue);
        double[] xaxisP1 = to2D(xaxis[0]);
        double[] xaxisP2 = to2D(xaxis[1]);
        StdDraw.line(xaxisP1[0], xaxisP1[1], xaxisP2[0], xaxisP2[1]);

        StdDraw.setPenColor();
        StdDraw.text(1.1*to2D(yaxis[1])[0], 1.1*to2D(yaxis[1])[1], "y");
		StdDraw.setPenColor(Color.green);
        double[] yaxisP1 = to2D(yaxis[0]);
        double[] yaxisP2 = to2D(yaxis[1]);
        StdDraw.line(yaxisP1[0], yaxisP1[1], yaxisP2[0], yaxisP2[1]);

        StdDraw.setPenColor();
        StdDraw.text(1.1*to2D(zaxis[1])[0], 1.1*to2D(zaxis[1])[1], "z");
		StdDraw.setPenColor(Color.red);
        double[] zaxisP1 = to2D(zaxis[0]);
        double[] zaxisP2 = to2D(zaxis[1]);
        StdDraw.line(zaxisP1[0], zaxisP1[1], zaxisP2[0], zaxisP2[1]);

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
	}


    public void resetDraw(){

    };

}


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.LinkedList;

import javax.swing.LayoutFocusTraversalPolicy;

import edu.princeton.cs.introcs.StdDraw;

public class euclideanR3 extends abstractSpaceVisuals{


    
    //public int DEFAULT_FRAME_SCALE;
    public double X_MIN;
    public double X_MAX;
    public double Y_MIN;
    public double Y_MAX;
    public double Z_MIN;
    public double Z_MAX;
    public double frameScale;
    LinkedList<double[][]> lines = new LinkedList<double[][]>();

    public euclideanR3(int defaultScale, int labelInterval, boolean viewLabels){
        super(defaultScale, viewLabels, labelInterval);
        frameScale = DEFAULT_VIEW_RADIUS;
        X_MIN = -frameScale;
        X_MAX = frameScale;
        Y_MIN = -frameScale;
        Y_MAX = frameScale;
        lines.add(new double[][]{{-defaultScale, 0, 0}, {defaultScale, 0, 0}});
        lines.add(new double[][]{{0, -defaultScale, 0}, {0, defaultScale, 0}});
        lines.add(new double[][]{{0, 0, -defaultScale}, {0, 0, defaultScale}});
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

        StdDraw.setPenColor();
        for(double x = -frameScale; x <= frameScale; ++x){
            double[] lineP1 = new double[]{x, -frameScale, 0};
            double[] lineP2 = new double[]{x, frameScale, 0};
            double[] lineP12D = to2D(lineP1);
            double[] lineP22D = to2D(lineP2);
            StdDraw.setPenColor(150, 150, 150);
            StdDraw.setPenRadius(0.001);
            StdDraw.line(lineP12D[0], lineP12D[1], lineP22D[0], lineP22D[1]);
            
        }
        for(double y = -frameScale; y <= frameScale; ++y){
            double[] lp1 = new double[]{-frameScale, y, 0};
            double[] lp2 = new double[]{frameScale, y, 0};
            double[] lp12d = to2D(lp1);
            double[] lp22d = to2D(lp2);
            StdDraw.line(lp12d[0], lp12d[1], lp22d[0], lp22d[1]);

        }
        StdDraw.setPenRadius();




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
		else if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			for(double[] point : points) {
				double[] newPoint = matrix.matrixVectorMultiplication(matrix.negXY, point);
                point[0] = newPoint[0];
                point[1] = newPoint[1];
                point[2] = newPoint[2];
			}
		}
		else if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			for(double[] point : points) {
				double[] newPoint = matrix.matrixVectorMultiplication(matrix.posXZ, point);
                point[0] = newPoint[0];
                point[1] = newPoint[1];
                point[2] = newPoint[2];
			}
		}
		else if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			for(double[] point : points) {
				double[] newPoint = matrix.matrixVectorMultiplication(matrix.negXZ, point);
                point[0] = newPoint[0];
                point[1] = newPoint[1];
                point[2] = newPoint[2];
			}
		}
		else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
			for(double[] point : points) {
				double[] newPoint = matrix.matrixVectorMultiplication(matrix.posYZ, point);
                point[0] = newPoint[0];
                point[1] = newPoint[1];
                point[2] = newPoint[2];
			}
		}
		else if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
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
		else if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			for(double[][] line : lines) {
				line[0] = matrix.matrixVectorMultiplication(matrix.negXY, line[0]);
				line[1] = matrix.matrixVectorMultiplication(matrix.negXY, line[1]);
			}
		}
		else if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			for(double[][] line : lines) {
				line[0] = matrix.matrixVectorMultiplication(matrix.posXZ, line[0]);
				line[1] = matrix.matrixVectorMultiplication(matrix.posXZ, line[1]);
			}
		}
		else if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			for(double[][] line : lines) {
				line[0] = matrix.matrixVectorMultiplication(matrix.negXZ, line[0]);
				line[1] = matrix.matrixVectorMultiplication(matrix.negXZ, line[1]);
			}
		}
		else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
			for(double[][] line : lines) {
				line[0] = matrix.matrixVectorMultiplication(matrix.posYZ, line[0]);
				line[1] = matrix.matrixVectorMultiplication(matrix.posYZ, line[1]);
			}
		}
		else if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
			for(double[][] line : lines) {
				line[0] = matrix.matrixVectorMultiplication(matrix.negYZ, line[0]);
				line[1] = matrix.matrixVectorMultiplication(matrix.negYZ, line[1]);
			}
		}
        else if (StdDraw.isKeyPressed(KeyEvent.VK_R)){
           
            frameScale=DEFAULT_VIEW_RADIUS;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
            frameScale++;
            X_MIN--;
            X_MAX++;
            Y_MIN--;
            Y_MAX++;

        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
            if(frameScale > 1){
                frameScale--;
                X_MIN++;
                X_MAX--;
                Y_MIN++;
                Y_MAX--;
            }
            
        }

        StdDraw.setScale(-frameScale, frameScale);
	}



}


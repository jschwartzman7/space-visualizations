import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.LinkedList;

import javax.swing.LayoutFocusTraversalPolicy;

import edu.princeton.cs.introcs.StdDraw;

public class euclidR3v2 extends abstractSpaceVisuals{

    public double cx;
    public double cy;
    public double cz;
    public double[] b1;
    public double[] b2;
    public double[][] xAxis;
    public double[][] yAxis;
    public double[][] zAxis;

    // <(x-cx, y-cy, z-cz), (cx, cy, cz)> = 0

    public euclidR3v2(int defaultScale, int labelInterval, boolean viewLabels){
        super(defaultScale, viewLabels, labelInterval);
        this.cx = Math.sqrt(3)*defaultScale/3;
        this.cy = Math.sqrt(3)*defaultScale/3;
        this.cz = Math.sqrt(3)*defaultScale/3;
        this.xAxis = new double[][]{{-defaultScale, 0, 0}, {defaultScale, 0, 0}};
        this.yAxis = new double[][]{{0, -defaultScale, 0}, {0, defaultScale, 0}};
        this.zAxis = new double[][]{{0, 0, -defaultScale}, {0, 0, defaultScale}};
        this.b1 = new double[]{0, 0, (Math.pow(cx, 2)+Math.pow(cy, 2)+Math.pow(cz, 2))/cz};
    }


	public double[] to2D(double[] projP) {
		double[] cord2D = new double[] {Math.sqrt(3)/2*(cord[1]-cord[0]), cord[2] - .5*(cord[1]+cord[0])};
		return cord2D;
	}
    
    public double[] projectPoint(double[] point){
        // p - <n, (p-c)>n
        double[] norm = new double[]{-cx/frameDistance, -cy/frameDistance, -cz/frameDistance};
        double[] cpBar = new double[]{point[0]-cx, point[1]-cy, point[2]-cz};
        double projMag = matrix.innerProduct(norm, cpBar);
        double[] projPoint = new double[]{point[0]-norm[0]*projMag, point[1]-norm[1]*projMag, point[2]-norm[2]*projMag};
        return projPoint;
    }


    public void draw(){
        //StdDraw.setPenColor();
        //StdDraw.text(1.1*to2D(axiss.get(0)[1])[0], 1.1*to2D(axiss.get(0)[1])[1], "x");
		StdDraw.setPenColor(Color.blue);
        double[] xaxisP1 = to2D(axiss.get(0)[0]);
        double[] xaxisP2 = to2D(axiss.get(0)[1]);
        StdDraw.line(xaxisP1[0], xaxisP1[1], xaxisP2[0], xaxisP2[1]);

        //StdDraw.setPenColor();
        //StdDraw.text(1.1*to2D(axiss.get(1)[1])[0], 1.1*to2D(axiss.get(1)[1])[1], "y");
		StdDraw.setPenColor(Color.green);
        double[] yaxisP1 = to2D(axiss.get(1)[0]);
        double[] yaxisP2 = to2D(axiss.get(1)[1]);
        StdDraw.line(yaxisP1[0], yaxisP1[1], yaxisP2[0], yaxisP2[1]);

        //StdDraw.setPenColor();
        //StdDraw.text(1.1*to2D(axiss.get(2)[1])[0], 1.1*to2D(axiss.get(2)[1])[1], "z");
		StdDraw.setPenColor(Color.red);
        double[] zaxisP1 = to2D(axiss.get(2)[0]);
        double[] zaxisP2 = to2D(axiss.get(2)[1]);
        StdDraw.line(zaxisP1[0], zaxisP1[1], zaxisP2[0], zaxisP2[1]);

    }

    public void update(){
        if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            double[] newLocation = matrix.matrixVectorMultiplication(matrix.posXY, new double[]{cx, cy, cz});
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			double[] newLocation = matrix.matrixVectorMultiplication(matrix.negXY, new double[]{cx, cy, cz});
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			double[] newLocation = matrix.matrixVectorMultiplication(matrix.posXZ, new double[]{cx, cy, cz});
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			double[] newLocation = matrix.matrixVectorMultiplication(matrix.negXZ, new double[]{cx, cy, cz});
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
			double[] newLocation = matrix.matrixVectorMultiplication(matrix.posYZ, new double[]{cx, cy, cz});
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
			double[] newLocation = matrix.matrixVectorMultiplication(matrix.negYZ, new double[]{cx, cy, cz});
		}
        if (StdDraw.isKeyPressed(KeyEvent.VK_R)){
            frameDistance = 5;
        }

        if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
            frameDistance++;
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
            if(frameDistance > 1){
                frameDistance--;
            }
            
        }
    }




    public void reset(){
        StdDraw.setScale(-DEFAULT_XY_SCALE, DEFAULT_XY_SCALE);
    };


}


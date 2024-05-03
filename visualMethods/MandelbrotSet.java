import java.awt.Color;
import java.util.HashMap;
import java.util.Hashtable;

import edu.princeton.cs.introcs.StdDraw;

public class MandelbrotSet extends abstractVisualMethod{
    

	
	public static int resolution = 500;
	//public static int[][] iterationsArray = new int[n][n];
	public static int maxIterations = 50;

    abstractFunction function;
    euclideanR2 space;
	
	public MandelbrotSet(){
        this.function =  new ComplexFunction();
        this.space = new euclideanR2(3, true, 2);
    }



	public int mandelbrotStatusHelper(double[] zCur, double[] z, int iterationNum) {
		if(iterationNum > maxIterations){ // in set
			return -1;
		}
		else if(Math.hypot(zCur[0], zCur[1]) > 2) { // not in set
			return iterationNum;
		}
		else {
			return mandelbrotStatusHelper(ComplexFunction.add(ComplexFunction.multiply(zCur, zCur), z), z, iterationNum + 1);
		}
	}
	

	public int mandelbrotStatus(double[] z) {
		return mandelbrotStatusHelper(new double[]{0, 0}, z, 0);
	}
	
	public Color getColor(){
		return null;
	}
	
	public void drawPoints() {
		HashMap<double[], Double> map  = new HashMap<double[], Double>();
	    int iterationsMin = maxIterations;
		int iterationsMax = 0;
        double xStep = (space.X_MAX-space.X_MIN)/resolution;
        double yStep = (space.Y_MAX-space.Y_MIN)/resolution;
		for(double x = space.X_MIN; x < space.X_MAX+xStep; x += xStep){
			for(double y = space.Y_MIN; y < space.Y_MAX+yStep; y += yStep){
				double[] point = new double[]{x, y};
                int MandelbrotStatus = mandelbrotStatus(point);
				if(MandelbrotStatus < iterationsMin) {
						iterationsMin = MandelbrotStatus;
				}
				if(MandelbrotStatus > iterationsMax) {
						iterationsMax = MandelbrotStatus;
				}
				map.put(point, (double)MandelbrotStatus);
		}
	}

	double iterationsRange = iterationsMax-iterationsMin;
		for(double[] point : map.keySet()){
			double setStatus = map.get(point);
			if(setStatus == -1){
				StdDraw.setPenColor();
				StdDraw.filledSquare(point[0], point[1], (xStep + yStep)/4);
				continue;
			}
			double hue = (iterationsMax-setStatus)/(iterationsRange);
			double saturation = (iterationsRange - setStatus)/(iterationsRange);
			double brightness = (iterationsRange - setStatus)/(iterationsRange);
			StdDraw.setPenColor(new Color(Color.HSBtoRGB((float)hue, (float)saturation, (float)brightness)));
			StdDraw.filledSquare(point[0], point[1], (xStep + yStep)/4);
		}
	}
	
	
	public void run() {
        StdDraw.setPenRadius(0.005);
		while(true) {
            StdDraw.clear();
            drawPoints();
            space.draw();
            space.update();
			StdDraw.show(50);
		}
        /*
         * StdDraw.clear();
            drawPoints();
            space.draw();
            space.update();
            StdDraw.show(50);
         */
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
        MandelbrotSet test = new MandelbrotSet();
		test.run();
		// TODO Auto-generated method stub

	}

}

    


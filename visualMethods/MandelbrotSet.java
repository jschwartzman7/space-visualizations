import java.awt.Color;
import java.util.HashMap;
import java.util.Hashtable;

import edu.princeton.cs.introcs.StdDraw;

public class MandelbrotSet extends abstractAnimation{
    

	
	public static int maxIterations = 50;

    euclideanR2 space;
	
	public MandelbrotSet(abstractFunction function, euclideanR2 space, int pixelResolution, int frameSpeed){
		super(function, space, pixelResolution, frameSpeed);
        this.space = space;
    }




	public int mandelbrotStatusHelper(double[] zCur, double[] z, int iterationNum) {
		if(iterationNum > maxIterations){ // in set
			return -1;
		}
		else if(Math.hypot(zCur[0], zCur[1]) > 2) { // not in set
			return iterationNum;
		}
		else {
			return mandelbrotStatusHelper(FunctionC_C.add(FunctionC_C.multiply(zCur, zCur), z), z, iterationNum + 1);
		}
	}
	

	public int mandelbrotStatus(double[] z) {
		return mandelbrotStatusHelper(new double[]{0, 0}, z, 0);
	}
	
	public Color getColor(){
		return null;
	}
	
	public void draw() {
		HashMap<double[], Double> map  = new HashMap<double[], Double>();
	    int iterationsMin = maxIterations;
		int iterationsMax = 0;
        double xStep = (space.X_MAX-space.X_MIN)/this.pixelResolution;
        double yStep = (space.Y_MAX-space.Y_MIN)/this.pixelResolution;
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
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
        MandelbrotSet test = new MandelbrotSet(null, new euclideanR2(5, 10, false), 200, 25);
		test.run();
		// TODO Auto-generated method stub

	}

}

    


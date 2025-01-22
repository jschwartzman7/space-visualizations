package spacevisuals.animations;

import java.awt.Color;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.function.Function;

import edu.princeton.cs.introcs.StdDraw;

public class MandelbrotSet extends abstractLatticeAnimation<Euclidean2D> {
    
	public static int maxIterations = 70;
	public static double thresholdRadius = 4;
	
	public MandelbrotSet(Euclidean2D space, int frameSpeed, int pixelResolution){
		super(space, frameSpeed, pixelResolution, MandelbrotSet::mandelbrotStatus);
    }

	// number of iterations for location to surpass threshold radius
	public static int mandelbrotStatusHelper(double[] zCur, double[] location, int iterationNum) {
		if(Rn_R.magnitude(zCur) > thresholdRadius) { // point crossed radius threshold: NOT in Mandelbrot set
			return iterationNum;
		}
		if(iterationNum > maxIterations){ // point never crossed radius threshold: IN Mandelbrot set
			return Integer.MAX_VALUE;
		}
		return mandelbrotStatusHelper(C_C.add(C_C.multiply(zCur, zCur), location), location, iterationNum + 1);
	}
	
	public static double[] mandelbrotStatus(double[] z) {
		return new double[]{mandelbrotStatusHelper(C_C.zero, z, 0)};
	}

	public void handleImage(double[] input, double[] numIterations) {
		double iterationsToEscape = numIterations[0];
		/*if(MandelbrotStatus < iterationsMin) {
				iterationsMin = MandelbrotStatus;
		}
		if(MandelbrotStatus > iterationsMax) {
				iterationsMax = MandelbrotStatus;
		}*/

		// in Mandelbrot set
		if(iterationsToEscape > maxIterations) {
			StdDraw.setPenColor();
		}
		else{
			StdDraw.setPenColor(new Color(Color.HSBtoRGB((float)hue(iterationsToEscape), (float)saturation(iterationsToEscape), (float)brightness(iterationsToEscape))));
		}
		double numericXMin = space.X_MIN*space.primaryDistortion;
		double numericXMax = space.X_MAX*space.primaryDistortion;
		double numericYMin = space.Y_MIN*space.secondaryDistortion;
		double numericYMax = space.Y_MAX*space.secondaryDistortion;
		StdDraw.filledRectangle(input[0]/space.primaryDistortion, input[1]/space.secondaryDistortion, (numericXMax-numericXMin)/(2*this.pixelResolution), (numericYMax-numericYMin)/(2*this.pixelResolution));
	}

	private double hue(double z) {
		return Math.sin(3*z/maxIterations);
	}
	private double saturation(double z) {
		return Math.exp(-.01*z);
	}
	private double brightness(double z) {
		return Math.exp(-.01*z);
	}


	public void updateAnimation() {
	}
	
	public static void main(String[] args) {
        new MandelbrotSet(new Euclidean2D(2, 1, false), 30, 300).run();
	}

}

    


package spacevisuals.animations;

import spacevisuals.functions.*;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.helpers.Lattice2DHelper2D;

import java.awt.Color;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.function.Consumer;
import java.util.function.Function;

import edu.princeton.cs.introcs.StdDraw;

public class MandelbrotSet extends PointSetAnimation<Euclidean2D> {
    
	public static int maxIterations = 70;
	public static double thresholdRadius = 4;
	Lattice2DHelper2D traverser;
	
	public MandelbrotSet(Euclidean2D space, int frameRate, int pixelResolution){
		super(space, frameRate, MandelbrotSet::mandelbrotStatus);
		this.traverser = new Lattice2DHelper2D(space, pixelResolution);
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
		return new double[]{mandelbrotStatusHelper(C_C.zero, new double[]{z[0], z[1]}, 0)};
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
		double[] z = new double[]{input[0], input[1]};
		if(iterationsToEscape > maxIterations) {
			traverser.drawPointRectangle(z, Color.BLACK);
		}
		else{
			traverser.drawPointRectangle(z, new Color(Color.HSBtoRGB((float)hue(iterationsToEscape), (float)saturation(iterationsToEscape), (float)brightness(iterationsToEscape))));
		}
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

	public void traverseDomain(Consumer<double[]> handlePoint) {
		traverser.traverseDomain(handlePoint);
	}

	public void updateAnimation() {
	}
	
}

    


package spacevisuals.animations.fractalanimations;

import java.util.ArrayList;
import java.util.function.Consumer;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.functions.C_C;
import spacevisuals.functions.Rn_R;
import spacevisuals.helpers.FunctionHandler;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.spacetraversers.*;

import java.awt.Color;
import java.awt.event.KeyEvent;

import spacevisuals.*;
import spacevisuals.colors.*;

public class JuliaSet extends Animation2DSpace implements PointSetAnimation{

	public static double[][] juliaSetConstants = new double[][]{
												new double[]{.355, .355},
												new double[]{-2, 0},
												new double[]{0, 1},
												new double[]{-.7, .2}};
	private static final int DEFAULT_PIXEL_RESOLUTION = 300;
	public int iterationMax = 100;
	ClippingTraverser traverser;
    double[] c;
	ArrayList<Integer> escapeIterations = new ArrayList<Integer>();
	ColorStrategy colorHelper = new JuliaSetColorStrategy();
	
	public JuliaSet(){
		super();
		this.traverser = new ClippingTraverser(space, DEFAULT_PIXEL_RESOLUTION);
        this.c = null;
    }
	public JuliaSet(int pixelResolution){
		super();
		this.traverser = new ClippingTraverser(space, pixelResolution);
        this.c = juliaSetConstants[juliaSetConstants.length-1];
    }

	@Override
    public void buildAnimation(String[] parameters) {
		if(parameters.length < 2) {
			this.c = juliaSetConstants[juliaSetConstants.length-1];
			return;
		}
        this.c = new double[]{Double.parseDouble(parameters[0]), Double.parseDouble(parameters[1])};
    }

	public int juliaSetStatusHelper(double[] z, double[] c, int iterationNum) {
		if(Rn_R.magnitude(z) > 2 || iterationNum > iterationMax) { // point crossed radius threshold: NOT in Mandelbrot set
			return iterationNum;
		}
		return juliaSetStatusHelper(C_C.add(C_C.multiply(z, z), c), c, iterationNum + 1);
	}
	
	public double getJuliaSetStatus(double[] input) {
		return juliaSetStatusHelper(input, c, 0);
	}

	@Override
	public void handlePoint(double[] input) {
		double iterationsToEscape = getJuliaSetStatus(input);
		double[] z = new double[]{input[0], input[1]};
		if(iterationsToEscape > iterationMax) {
			traverser.drawPointRectangle(z, Color.BLACK);
		}
		else{
			escapeIterations.add((int)iterationsToEscape);
			traverser.drawPointRectangle(z, colorHelper.getColor(new double[]{iterationsToEscape}));
		}
	}

	@Override
	public void drawAnimation() {
		escapeIterations.clear();
		traverseDomain(this::handlePoint);
	}

	@Override
	public void traverseDomain(Consumer<double[]> handlePoint) {
		traverser.traverseDomain(handlePoint);
	}
}

    



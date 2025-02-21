package spacevisuals.animations.fractalanimations;

import java.util.function.Consumer;
import spacevisuals.functions.C_C;
import spacevisuals.functions.Rn_R;
import spacevisuals.spaces.spacetraversers.*;
import java.awt.Color;
import spacevisuals.*;
import spacevisuals.colors.*;

public class JuliaSet extends SpaceFunction2D implements PointSetAnimation{


	private static final int DEFAULT_PIXEL_RESOLUTION = 300;
	public static double[][] juliaSetConstants = new double[][]{
												new double[]{.355, .355},
												new double[]{-2, 0},
												new double[]{0, 1},
												new double[]{-.7, .2}};
    public double[] c;
	private int maxIterations = 100;
	private double magnitudeThreshold = 2;
	private ClippingTraverser traverser;
	private ColorStrategy colorHelper = new JuliaSetColorStrategy();
	
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
		if(parameters == null){
			this.c = juliaSetConstants[juliaSetConstants.length-1];
			return;
		}
		if(parameters.length < 2) {
			this.c = juliaSetConstants[juliaSetConstants.length-1];
		}
		else{
			this.c = new double[]{Double.parseDouble(parameters[0]), Double.parseDouble(parameters[1])};
		}
    }

	public int juliaSetStatusHelper(double[] z, double[] c, int iterationNum) {
		if(Rn_R.magnitude(z) > magnitudeThreshold || iterationNum > maxIterations) { // point crossed radius threshold: NOT in julia set
			return iterationNum;
		}
		return juliaSetStatusHelper(C_C.add(C_C.multiply(z, z), c), c, iterationNum + 1);
	}
	
	public double getJuliaSetStatus(double[] input) {
		return juliaSetStatusHelper(input, c, 0);
	}

	@Override
	public void drawAnimation() {
		traverseDomain(this::handlePoint);
	}
	@Override
	public void traverseDomain(Consumer<double[]> handlePoint) {
		traverser.traverseDomain(handlePoint);
	}
	@Override
	public void handlePoint(double[] input) {
		double iterationsToEscape = getJuliaSetStatus(input);
		double[] z = new double[]{input[0], input[1]};
		if(iterationsToEscape > maxIterations) {
			traverser.drawPointRectangle(z, Color.BLACK);
		}
		else{
			traverser.drawPointRectangle(z, colorHelper.getColor(new double[]{iterationsToEscape}));
		}
	}
}

    



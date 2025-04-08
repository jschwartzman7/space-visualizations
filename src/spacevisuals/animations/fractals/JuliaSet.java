package spacevisuals.animations.fractals;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.functions.*;
import spacevisuals.spaces.*;
import spacevisuals.spaces.spacetraversers.*;
import spacevisuals.utils.Constants;

import java.awt.Color;
import spacevisuals.animations.SpaceTraverserAnimation;
import spacevisuals.colors.colorstrategies.ColorStrategy;
import spacevisuals.colors.colorstrategies.JuliaSetColorStrategy;

public class JuliaSet extends SpaceTraverserAnimation implements SpaceUser2D{

	public static double[][] juliaSetConstants = new double[][]{
												new double[]{.355, .355},
												new double[]{-2, 0},
												new double[]{0, 1},
												new double[]{-.7, .2}};
    public double[] c;
	private int maxIterations = 100;
	private double magnitudeThreshold = 2;
	private ColorStrategy colorHelper = new JuliaSetColorStrategy();
	
	public JuliaSet(){
		super(new ClippingTraverser());
		this.traverser = new ClippingTraverser();
        this.c = juliaSetConstants[0];
    }
	public JuliaSet(int pixelResolution){
		super(new ClippingTraverser());
		this.traverser = new ClippingTraverser();
        this.c = juliaSetConstants[0];
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
	public void handlePoint(double[] input) {
		double iterationsToEscape = getJuliaSetStatus(input);
		double[] z = new double[]{input[0], input[1]};
		double halfWidth = (space().xClipMax-space().xClipMin)/(Constants.PIXEL_RESOLUTION_MEDIUM*2);
        double halfHeight = (space().yClipMax-space().yClipMin)/(Constants.PIXEL_RESOLUTION_MEDIUM*2);
		if(iterationsToEscape > maxIterations) {
			StdDraw.setPenColor(Color.BLACK);
		}
		else{
			StdDraw.setPenColor(colorHelper.getColor(new double[]{iterationsToEscape}));
		}
		StdDraw.filledRectangle(z[0], z[1], halfWidth, halfHeight);
	}

	@Override
    public void configureAnimation(String[] parameters) {
		if(parameters == null){
			this.c = juliaSetConstants[juliaSetConstants.length-1];
			return;
		}
		if(parameters.length < 2) {
			this.c = juliaSetConstants[juliaSetConstants.length-1];
			return;
		}
		this.c = new double[]{Double.parseDouble(parameters[0]), Double.parseDouble(parameters[1])};
    }
}

    



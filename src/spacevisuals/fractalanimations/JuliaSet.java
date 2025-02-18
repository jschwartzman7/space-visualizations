package spacevisuals.fractalanimations;

import java.util.ArrayList;
import java.util.function.Consumer;
import spacevisuals.helpers.*;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.functions.C_C;
import spacevisuals.functions.Rn_R;
import spacevisuals.functions.statistics;
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
    
	public int iterationMax = 100;
	ClippingTraverser traverser;
    double[] c;
	ArrayList<Integer> escapeIterations = new ArrayList<Integer>();
	ColorStrategy colorHelper = new JuliaSetColorStrategy();
	public String fileName = "JuliaSetINSIDE";
	public int fileCounter = 0;
	
	public JuliaSet(Euclidean2D space){
		super(space);					
		this.traverser = new ClippingTraverser(space);
		this.c = juliaSetConstants[juliaSetConstants.length-1];
    }
	public JuliaSet(Euclidean2D space, double[] c){
		super(space);
		this.traverser = new ClippingTraverser(space);
        this.c = c;
    }
    public JuliaSet(Euclidean2D space, double[] c, int pixelResolution){
		super(space);
		this.traverser = new ClippingTraverser(space, pixelResolution);
        this.c = c;
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

	public void traverseDomain(Consumer<double[]> handlePoint) {
		traverser.traverseDomain(handlePoint);
	}

	public void updateAnimation() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
			System.err.println("Space pressed");
			StdDraw.save(fileName + fileCounter + ".png");
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < escapeIterations.size(); i++) {
				sb.append(escapeIterations.get(i) + "\n");
			}
			sb.insert(0, fileName + fileCounter + "\n");
			fileCounter++;
		}
	}
	
	public static void main(String[] args) {
	}
}

    



package spacevisuals.fractalanimations;

import java.util.ArrayList;
import java.util.function.Consumer;
import spacevisuals.helpers.*;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.functions.C_C;
import spacevisuals.functions.Rn_R;
import spacevisuals.functions.statistics;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.helpers.Euclidean2DTraverser;
import spacevisuals.other.fractalIterStats;

import java.awt.Color;
import java.awt.event.KeyEvent;

import spacevisuals.*;

public class JuliaSet implements PointSetAnimation {

	public static double[][] juliaSetConstants = new double[][]{
												new double[]{.355, .355},
												new double[]{-2, 0},
												new double[]{0, 1},
												new double[]{-.7, .2}};
    
	public int iterationMax = 70;
	Euclidean2DTraverser traverser;
    double[] c;
	ArrayList<Integer> escapeIterations = new ArrayList<Integer>();
	ColorHelper colorHelper = new ColorHelper(1);
	public String fileName = "JuliaSetINSIDE";
	public int fileCounter = 0;
	Euclidean2D space;

	
	public JuliaSet(Euclidean2D space){
		this.space = space;							
		this.traverser = new Euclidean2DTraverser(space);
		this.c = juliaSetConstants[juliaSetConstants.length-1];
    }
    public JuliaSet(Euclidean2D space, double[] c){
		this.space = space;
		this.traverser = new Euclidean2DTraverser(space);
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
			double escapeMean = statistics.mean(escapeIterations.stream().mapToDouble(i -> i).toArray());
			traverser.drawPointRectangle(z, colorHelper.getColor(new double[]{iterationsToEscape, escapeMean}));
		}
	}

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
			fractalIterStats.writeToNewFile(fileName + fileCounter, sb.toString());
			fileCounter++;
		}
	}
	
	public static void main(String[] args) {
	}
}

    



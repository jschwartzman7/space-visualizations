package spacevisuals.fractalanimations;

import spacevisuals.spaces.Euclidean2D;

public class MandelbrotSet extends JuliaSet {
	
	public MandelbrotSet(Euclidean2D space){
		super(space);
    }

	@Override
	public double getJuliaSetStatus(double[] input) {
		return juliaSetStatusHelper(input, input, 0);
	}
}

    


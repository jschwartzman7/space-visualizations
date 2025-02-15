package spacevisuals.fractalanimations;

import spacevisuals.spaces.Euclidean2D;

public class MandelbrotSet extends JuliaSet {
	
	private static final int DEFAULT_PIXEL_RESOLUTION = 350;

	public MandelbrotSet(Euclidean2D space){
		super(space, null, DEFAULT_PIXEL_RESOLUTION);
	}

	@Override
	public double getJuliaSetStatus(double[] input) {
		return juliaSetStatusHelper(input, input, 0);
	}
}

    


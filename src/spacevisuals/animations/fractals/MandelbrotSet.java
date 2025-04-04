package spacevisuals.animations.fractals;

public class MandelbrotSet extends JuliaSet {
	
	private static final int DEFAULT_PIXEL_RESOLUTION = 500;

	public MandelbrotSet(){
		super(DEFAULT_PIXEL_RESOLUTION);
	}

	@Override
	public double getJuliaSetStatus(double[] input) {
		return juliaSetStatusHelper(input, input, 0);
	}
}

    


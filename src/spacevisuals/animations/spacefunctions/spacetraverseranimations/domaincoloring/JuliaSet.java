package spacevisuals.animations.spacefunctions.spacetraverseranimations.domaincoloring;
import spacevisuals.functions.*;
import spacevisuals.spaces.spacetraversers.*;
import java.util.function.Function;

public class JuliaSet extends DomainColor{

	public static double[][] juliaSetConstants = new double[][]{
												new double[]{.355, .355},
												new double[]{-2, 0},
												new double[]{0, 1},
												new double[]{-.7, .2}};

	private static final double[] defaultC = juliaSetConstants[0];
	private static int maxIterations = 100;
	private static double magnitudeThreshold = 2;
    public double[] c;

	public JuliaSet(){
		super(input -> getJuliaSetStatus(input, defaultC));
		this.traverser = new ClippingTraverser();
        this.c = juliaSetConstants[0];
    }
	public JuliaSet(Function<double[], double[]> function){
		super(function);
		this.traverser = new ClippingTraverser();
        this.c = juliaSetConstants[0];
    }

	public static int juliaSetStatusHelper(double[] z, double[] c, int iterationNum) {
		if(Rn_R.magnitude(z) > magnitudeThreshold || iterationNum > maxIterations) { // point crossed radius threshold: NOT in julia set
			return iterationNum;
		}
		return juliaSetStatusHelper(C_C.add(C_C.multiply(z, z), c), c, iterationNum + 1);
	}
	
	public static double[] getJuliaSetStatus(double[] input, double[] c) {
		return new double[]{juliaSetStatusHelper(input, c, 0)};
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

    



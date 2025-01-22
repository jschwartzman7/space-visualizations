package spacevisuals.animations;

import spacevisuals.spaces.AbstractSpace;
import java.util.function.Function;

public abstract class abstractLatticeAnimation<ConcreteSpaceVisual extends AbstractSpace> extends BasicAnimation<ConcreteSpaceVisual>{

    public int pixelResolution;
    public Function<double[], double[]> function;

    public abstractLatticeAnimation(ConcreteSpaceVisual space, int frameRate, int pixelResolution, Function<double[], double[]> function){
        super(space, frameRate);
        this.pixelResolution = pixelResolution;
        this.function = function;
    }

    public void drawAnimation(){
        double xStep = (space.X_MAX-space.X_MIN)/(this.pixelResolution*space.primaryDistortion);
        double yStep = (space.Y_MAX-space.Y_MIN)/(this.pixelResolution*space.secondaryDistortion);
        double numericXMin = space.X_MIN*space.primaryDistortion;
        double numericXMax = space.X_MAX*space.primaryDistortion;
        double numericYMin = space.Y_MIN*space.secondaryDistortion;
        double numericYMax = space.Y_MAX*space.secondaryDistortion;
        for(double x = numericXMin; x <= numericXMax; x += xStep){
            for(double y = numericYMin; y <= numericYMax; y += yStep){
                double[] input = new double[]{x, y};
                handleImage(input, function.apply(input));
            }
        }
    }
    abstract void handleImage(double[] input, double[] output);
}

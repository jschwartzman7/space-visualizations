package spacevisuals.animations;
import spacevisuals.spaces.AbstractSpace;

import java.util.function.Consumer;
import java.util.function.Function;
import edu.princeton.cs.introcs.StdDraw;


public abstract class PointSetAnimation<ConcreteSpace extends AbstractSpace> extends BasicAnimation<ConcreteSpace> {
    
    private Function<double[], double[]> function;

    public PointSetAnimation(ConcreteSpace space, int frameRate, Function<double[], double[]> function){
        super(space, frameRate);
        this.function = function;
    }

    @Override
    public void drawAnimation(){
        traverseDomain(this::handlePoint);
    };

    public void handlePoint(double[] input){
        handleImage(input, function.apply(input));
    };

    public abstract void handleImage(double[] input, double[] output);
    public abstract void traverseDomain(Consumer<double[]> handlePoint);
}

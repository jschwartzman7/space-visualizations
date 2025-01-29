package spacevisuals.animations;
import spacevisuals.spaces.AbstractSpace;

import java.util.function.Consumer;
import java.util.function.Function;
import edu.princeton.cs.introcs.StdDraw;


public abstract class PointSetAnimation<ConcreteSpace extends AbstractSpace> extends BasicAnimation<ConcreteSpace> {
    
    private Function<Double[], Double[]> function;

    public PointSetAnimation(ConcreteSpace space, int frameRate, Function<Double[], Double[]> function){
        super(space, frameRate);
        this.function = function;
    }

    @Override
    public void drawAnimation(){
        traverseDomain(this::handlePoint);
    };

    public void handlePoint(Double[] input){
        handleImage(input, function.apply(input));
    };

    public abstract void handleImage(Double[] input, Double[] output);
    public abstract void traverseDomain(Consumer<Double[]> handlePoint);
}

package spacevisuals.animations;
import spacevisuals.spaces.AbstractSpace;

import java.util.function.Function;
import edu.princeton.cs.introcs.StdDraw;


public abstract class PointSetAnimation<Traverser extends PointSetHelper> extends BasicAnimation<AbstractSpace> {
    
    private Function<Double[], Double[]> function;
    protected Traverser traverser;

    public PointSetAnimation(AbstractSpace space, int frameRate, Function<Double[], Double[]> function, Traverser traverser){
        super(space, frameRate);
        this.function = function;
        this.traverser = traverser;
    }
    public void drawAnimation(){
        traverser.traverseDomain(this::handlePoint);
    };
    public void handlePoint(Double[] input){
        handleImage(input, function.apply(input));
    };
    public abstract void handleImage(Double[] input, Double[] output);
}

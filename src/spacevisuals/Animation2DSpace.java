package spacevisuals;

import java.util.function.Function;
import spacevisuals.helpers.FunctionHandler;
import spacevisuals.spaces.Euclidean2D;

public class Animation2DSpace implements SpaceAnimation{

    public Euclidean2D space;
    public Function<double[], double[]> function;

    public Animation2DSpace(){
        this.space = Euclidean2D.Euclidean2DGet();
        this.function = null;
    }
    public Animation2DSpace(Function<double[], double[]> function){
        this.space = Euclidean2D.Euclidean2DGet();
        this.function = function;
    }
    
    @Override
    public void drawAnimation() {
        space.drawSpace();
    }
    @Override
    public void buildAnimation(String[] parameters) {
        this.function = FunctionHandler.from(parameters[0]).getFunction();
    }
}

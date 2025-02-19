package spacevisuals;

import java.util.function.Function;
import spacevisuals.helpers.FunctionHandler;
import spacevisuals.spaces.Euclidean3D;

public class Animation3DSpace implements SpaceAnimation{

    public Euclidean3D space;
    public Function<double[], double[]> function;

    public Animation3DSpace(){
        this.space = Euclidean3D.Euclidean3DGet();
        this.function = null;
    }
    public Animation3DSpace(Function<double[], double[]> function){
        this.space = Euclidean3D.Euclidean3DGet();
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

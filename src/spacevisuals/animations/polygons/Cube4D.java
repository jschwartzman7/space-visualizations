package spacevisuals.animations.polygons;

import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.Euclidean4D;

public class Cube4D extends Polygons{

    public Cube4D(){
        super();
        addCube(new double[]{0, 0, 0, 0}, 1);
    }
    public Cube4D(double[] center, double size){
        super();
        addCube(center, size);
    }
    @Override
    public AbstractSpace space() {
        return Euclidean4D.Get();
    }

}

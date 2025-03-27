package spacevisuals.animations.polygons;

import spacevisuals.spaces.Euclidean4D;

public class Cube4D extends Polygons<Euclidean4D>{

    public Cube4D(){
        super(Euclidean4D.Get());
        addCube(new double[]{0, 0, 0, 0}, 1);
    }
    public Cube4D(double[] center, double size){
        super(Euclidean4D.Get());
        addCube(center, size);
    }
}

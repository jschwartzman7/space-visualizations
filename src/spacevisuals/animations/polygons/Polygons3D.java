package spacevisuals.animations.polygons;

import spacevisuals.spaces.Euclidean3D;

public class Polygons3D extends Polygons<Euclidean3D>{

    public Polygons3D(){
        super(Euclidean3D.Get());
        addCube(new double[]{-1, -1, -1}, 1);
        addSimplex(new double[]{1, 1, 2}, 1.5);
    }
}

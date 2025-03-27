package spacevisuals.animations.polygons;

import spacevisuals.spaces.Euclidean4D;

public class Simplex4D  extends Polygons<Euclidean4D>{
    
    public Simplex4D(){
        super(Euclidean4D.Get());
        addSimplex(new double[]{0, 0, 0, 0}, 1);
    }
    public Simplex4D(double[] center, double size){
        super(Euclidean4D.Get());
        addSimplex(center, size);
    }

}

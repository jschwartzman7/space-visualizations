package spacevisuals.animations.polygons;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.polygons.solids.Simplex;
import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.Euclidean4D;

public class Simplex4D extends Polygons{
    
    public Simplex4D(){
        super();
        addSimplex(new double[]{0, 0, 0, 0}, 1);
    }
    public Simplex4D(double[] center, double size){
        super();
        addSimplex(center, size);
    }
    @Override
    public AbstractSpace space() {
        return Euclidean4D.Get();
    }

}

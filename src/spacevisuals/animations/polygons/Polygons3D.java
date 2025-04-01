package spacevisuals.animations.polygons;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.polygons.solids.Simplex;
import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.SpaceUser;
import spacevisuals.spaces.SpaceUser3D;

public class Polygons3D extends Polygons {

    public Polygons3D(){
        super();
        addCube(new double[]{-1, -1, -1}, 1);
        addSimplex(new double[]{1, 1, 2}, 1.5);
    }

    @Override
    public AbstractSpace space() {
        return Euclidean3D.Get();
    }
}

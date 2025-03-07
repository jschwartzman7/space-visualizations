package spacevisuals.animations.polygons;

import java.util.LinkedList;
import java.util.List;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.SpaceAnimation;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.SpaceUser;

public class Polygons3D extends Polygons{

    public Polygons3D(){
        super(Euclidean3D.Get());

        addCube(new double[]{-1, -1, -1}, 1);
        addSimplex(new double[]{1, 1, 2}, 1.5);
    }
}

package spacevisuals.animations.polygons;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.SpaceAnimation;
import spacevisuals.spaces.Euclidean4D;
import spacevisuals.spaces.SpaceUser;

public class Polygons4D extends Polygons{


    public Polygons4D(){
        super(Euclidean4D.Get());

        addCube(new double[]{0, 0, 0, 0}, 2);
    }
}

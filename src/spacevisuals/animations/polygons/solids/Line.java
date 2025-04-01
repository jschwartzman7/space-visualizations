package spacevisuals.animations.polygons.solids;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.AbstractSpace;

public class Line extends Simplex {

    public Line(){
        super();
    }

    public Line(double[][] vertices){
        super(vertices);
    }

    public void draw(AbstractSpace space){
        double[] p1 = space.toViewScreenPoint(shape[0]);
        double[] p2 = space.toViewScreenPoint(shape[1]);
        if(p1 == null || p2 == null){
            return;
        }
        if(Double.isNaN(p1[0]) || Double.isNaN(p1[1]) || Double.isNaN(p2[0]) || Double.isNaN(p2[1])){
            return;
        }
        if(Double.isNaN(p1[0]) || Double.isNaN(p1[1]) || Double.isNaN(p2[0]) || Double.isNaN(p2[1])){
            return;
        }
        StdDraw.line(p1[0], p1[1], p2[0], p2[1]);
    }
}

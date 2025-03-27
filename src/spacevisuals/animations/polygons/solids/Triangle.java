package spacevisuals.animations.polygons.solids;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.AbstractSpace;

public class Triangle extends Simplex {

    public Triangle(){
        super();
    }

    public Triangle(double[][] vertices){
        super(vertices);
    }

    public void draw(AbstractSpace space){
        double[] p1 = space.toViewScreenPoint(shape[0]);
        double[] p2 = space.toViewScreenPoint(shape[1]);
        double[] p3 = space.toViewScreenPoint(shape[2]);
        if(p1 == null || p2 == null || p3 == null){
            return;
        }
        if(Double.isNaN(p1[0]) || Double.isNaN(p1[1]) || Double.isNaN(p2[0]) || Double.isNaN(p2[1]) || Double.isNaN(p3[0]) || Double.isNaN(p3[1])){
            return;
        }
        StdDraw.filledPolygon(new double[]{p1[0], p2[0], p3[0]}, new double[]{p1[1], p2[1], p3[1]});
    }
    
    
}

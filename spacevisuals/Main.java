package spacevisuals;

import java.util.function.*;
import spacevisuals.animations.*;
import spacevisuals.spaces.*;
import spacevisuals.functions.*;
import java.util.HashSet;

public class Main {

    HashSet<String> commands = new HashSet<String>(){
        {
            add("basic");
                // create 2D space
                    add("vectorfield2D");
                    add("domaincolor2D");
                    add("freeform2D");
                // create 3D space
                    add("vectorfield3D");
                    add("domaincolor3D");
                    add("graph3D");
                    add("polygons");
            
        }
    };


    public static void main(String[] args) {
        //System.out.println(args[0]);
        //System.out.println("^^");
        Euclidean2D euclidean2d = new Euclidean2D(10, 1, true);
        Euclidean3D euclidean3d = new Euclidean3D(10, 1, true);
        Perspective3D perspective3D = new Perspective3D(10, 1, false);
        BasicAnimation<Euclidean2D> basic2DAnimation = new BasicAnimation<Euclidean2D>(euclidean2d, 25);
        BasicAnimation<Euclidean3D> basic3DAnimation = new BasicAnimation<Euclidean3D>(euclidean3d, 25);
        BasicAnimation<Perspective3D> perspective3DAnimation = new BasicAnimation<Perspective3D>(perspective3D, 25);
        Function<Double[], Double[]> function = (Double[] point) -> {
            return new Double[]{Math.sin(point[0]*Math.cos(point[1]))};
        };
        Polygons polygons = new Polygons(perspective3D, 25);
        polygons.run();
    }
}

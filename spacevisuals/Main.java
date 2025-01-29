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
        System.out.println(args[0]);
        System.out.println("^^");
        Euclidean2D euclidean2d = new Euclidean2D(10, 1, true);
        Euclidean3D euclidean3d = new Euclidean3D(10, 1, true);
        BasicAnimation<Euclidean2D> basic2DAnimation = new BasicAnimation<Euclidean2D>(euclidean2d, 25);
        BasicAnimation<Euclidean3D> basic3DAnimation = new BasicAnimation<Euclidean3D>(euclidean3d, 25);
        Function<Double[], Double[]> function = input -> Rn_Rm.add(input, new Double[]{3.,2.,1.});
        Polygons polygonsAnimation = new Polygons(euclidean3d, 25);
        polygonsAnimation.run();
    }
}

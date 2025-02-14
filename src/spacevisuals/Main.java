package spacevisuals;

import java.util.function.*;
import spacevisuals.pointsetanimations.*;
import spacevisuals.fractalanimations.*;
import spacevisuals.functions.*;
import spacevisuals.vectorfields.*;
import spacevisuals.spaces.*;
import spacevisuals.otheranimations.*;
import java.util.HashMap;


public class Main {

    static Euclidean2D euclidean2d = new Euclidean2D(5, .08, true);
    static Euclidean3D euclidean3d = new Euclidean3D(5, .08, true);
    static Function<double[], double[]> function = (double[] point) -> C_C.multiply(point, point);
    static SpaceAnimationRunner animationRunner;
    static SpaceAnimationRunner animationRunner2d = new SpaceAnimationRunner(euclidean2d, 25);
    static SpaceAnimationRunner animationRunner3d = new SpaceAnimationRunner(euclidean3d, 25);
    static HashMap<String, HashMap<String, Animation>> visualCommands = new HashMap<String, HashMap<String, Animation>>(){
        {   put("2D", new HashMap<String, Animation>(){
                {   put("basic2d", euclidean2d);
                    put("vectorfield2d", new VectorField2D(euclidean2d, function));
                    put("domaincolor", new DomainColor(euclidean2d, function));
                    put("freeform2d", new FreeformR2_R2(euclidean2d, function));
                    put("juliaset", new JuliaSet(euclidean2d));
                    put("mandelbrot", new MandelbrotSet(euclidean2d));
                }
            });
            put("3D", new HashMap<String, Animation>(){
                {   put("basic3d", euclidean3d);
                    put("vectorfield3d", new VectorField3D(euclidean3d, function));
                    put("graph3d", new Euclidean3DGraph(euclidean3d, function));
                    put("polygons", new Polygons(euclidean3d));
                }
            });
        }
    };

    public static void runAnimationBuilder(){
        if(animationRunner3d.animations.size() > 0){
            animationRunner = animationRunner3d;
        }
        else{
            animationRunner = animationRunner2d;
        }
        animationRunner.run();
    }

    public static void handleCommandLineArgs(String[] args){
        if(args.length > 0){
            for(String arg : args){
                for(String key : visualCommands.get("2D").keySet()){
                    if(key.equals(arg)){
                        animationRunner2d.animations.add(visualCommands.get("2D").get(key));
                    }
                }
                for(String key : visualCommands.get("3D").keySet()){
                    if(key.equals(arg)){
                        animationRunner3d.animations.add(visualCommands.get("3D").get(key));
                    }
                }
            }
        }
        else {
            System.out.println("No arguments provided.");
        }
    }

    public static void main(String[] args) {
        handleCommandLineArgs(args);
        runAnimationBuilder();
    }
}

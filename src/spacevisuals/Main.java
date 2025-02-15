package spacevisuals;

import java.util.function.*;
import spacevisuals.pointsetanimations.*;
import spacevisuals.fractalanimations.*;
import spacevisuals.functions.*;
import spacevisuals.vectorfieldanimations.*;
import spacevisuals.spaces.*;
import spacevisuals.spaces.axislabelers.*;
import spacevisuals.otheranimations.*;
import java.util.HashMap;


public class Main {
    static Euclidean2D euclidean2d = new Euclidean2D(5, .08, true);
    static Euclidean3D euclidean3d = new Euclidean3D(5, .08, true);
    static SpaceAnimationRunner<Euclidean2D> animationRunner2d = new SpaceAnimationRunner<Euclidean2D>(euclidean2d, 25);
    static SpaceAnimationRunner<Euclidean3D> animationRunner3d = new SpaceAnimationRunner<Euclidean3D>(euclidean3d, 25);
    static HashMap<String, SpaceAnimation<Euclidean2D>> animations2d = new HashMap<String, SpaceAnimation<Euclidean2D>>(){
        {     
        put("vectorfield2d", new VectorField2D(euclidean2d, FunctionHandler.functions.get("pairwisesine")));
        put("domaincolor", new DomainColor(euclidean2d, FunctionHandler.functions.get("pairwisesine")));
        put("freeform2d", new FreeformR2_R2(euclidean2d, C_C::essentialSingularity));
        put("juliaset", new JuliaSet(euclidean2d));
        put("mandelbrot", new MandelbrotSet(euclidean2d));
        };
    };
    // animations3d keys = "vectorfield3d", "graph3d", "polygons", "spheremagnet"
    static HashMap<String, SpaceAnimation<Euclidean3D>> animations3d = new HashMap<String, SpaceAnimation<Euclidean3D>>(){
       {
        put("vectorfield3d", new VectorField3D(euclidean3d, FunctionHandler.functions.get("sumsines")));
        put("graph3d", new Euclidean3DGraph(euclidean3d, FunctionHandler.functions.get("sunsquares")));
        put("polygons", new Polygons(euclidean3d));
        put("spheremagnet", new SphereMagnet(euclidean3d));
        };
    };

    public static void runAnimationBuilder(){
        if(animationRunner3d.animations.size() > 0){
            System.out.println("Running 3D animations.");
            animationRunner3d.run();
        }
        else{
            animationRunner2d.run();
        }
    }

    public static void handleCommandLineArgs(String[] args){
        if(args.length > 0){
            for(String arg : args){
                for(String key : animations2d.keySet()){
                    if(key.equals(arg)){
                        animationRunner2d.animations.add(animations2d.get(key));
                    }
                }
                for(String key : animations3d.keySet()){
                    if(key.equals(arg)){
                        animationRunner3d.animations.add(animations3d.get(key));
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
        //handleCommandLineArgs(new String[]{"domaincolor"});
        runAnimationBuilder();
    }
}

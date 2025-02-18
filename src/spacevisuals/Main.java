package spacevisuals;

import java.util.function.*;
import spacevisuals.pointsetanimations.*;
import spacevisuals.fractalanimations.*;
import spacevisuals.functions.*;
import spacevisuals.vectorfieldanimations.*;
import spacevisuals.spaces.*;
import spacevisuals.spaces.axisintervals.*;
import spacevisuals.otheranimations.*;
import java.util.HashMap;
import java.util.LinkedList;


public class Main {
    
    static Euclidean2D euclidean2d = new Euclidean2D(5, .08, true);
    static Euclidean3D euclidean3d = new Euclidean3D(5, .08, false);
    static Euclidean4DTranslation euclidean4d = new Euclidean4DTranslation(true);
    static SpaceAnimationRunner animationRunner2d = new SpaceAnimationRunner(euclidean2d, 25);
    static SpaceAnimationRunner animationRunner3d = new SpaceAnimationRunner(euclidean3d, 25);
    static SpaceAnimationRunner animationRunner4d = new SpaceAnimationRunner(euclidean4d, 25);
    static SpaceAnimationRunner runner;
    static HashMap<String, HashMap<String, Function<String[], SpaceAnimation>>> animations = new HashMap<String, HashMap<String, Function<String[], SpaceAnimation>>>(){
        {
        put("2D", new HashMap<String, Function<String[], SpaceAnimation>>(){
            {
                put("basic2d", (String[] params) -> new Animation2DSpace(euclidean2d));
                put("vectorfield2d", (String[] params) -> new VectorField2D(euclidean2d, FunctionHandler.functions.get(params[0])));
                put("domaincolor", (String[] params) -> new DomainColor(euclidean2d, FunctionHandler.functions.get(params[0])));
                put("freeform2d", (String[] params) -> new FreeformR2_R2(euclidean2d, FunctionHandler.functions.get(params[0])));
                put("juliaset", (String[] params) -> new JuliaSet(euclidean2d, new double[]{Double.parseDouble(params[0]), Double.parseDouble(params[1])}));
                put("mandelbrot", (String[] params) -> new MandelbrotSet(euclidean2d));
                put("gradient", (String[] params) -> new Gradient(euclidean2d, FunctionHandler.functions.get(params[0])));
            }
        });
        put("3D", new HashMap<String, Function<String[], SpaceAnimation>>(){
            {
                put("basic3d", (String[] params) -> new Animation3DSpace(euclidean3d));
                put("vectorfield3d", (String[] params) -> new VectorField3D(euclidean3d, FunctionHandler.functions.get(params[0])));
                put("graph3d", (String[] params) -> new Euclidean3DGraph(euclidean3d, FunctionHandler.functions.get(params[0])));
                put("polygons", (String[] params) -> new Polygons(euclidean3d));
                put("spheremagnet", (String[] params) -> new SphereMagnet(euclidean3d));
            }
        });
        }
    };

    public static String[] handleCommandAnimationArgs(String[] args, int index){
        LinkedList<String> params = new LinkedList<String>();
        while(index < args.length){
            if(args[index].equals(",")){
                break;
            }
            params.add(args[index]);
            ++index;
        }
        if(params.size() == 0){
            return null;
        }
        return params.toArray(new String[params.size()]);

    }

    public static void handleCommandLineArgs(String[] args){
        if(args.length == 0){
            System.out.println("No arguments provided.");
            return;
        }
        int i = 0;
        while(i < args.length){
            for(String key : animations.get("2D").keySet()){
                if(args[i].equals(key)){
                    runner = animationRunner2d;
                    String[] params = handleCommandAnimationArgs(args, i+1);
                    if(params == null){
                        animationRunner2d.animations.add(animations.get("2D").get(key).apply(new String[0]));
                    }
                    else{
                        i = i + params.length;
                        animationRunner2d.animations.add(animations.get("2D").get(key).apply(params));
                    }
                    break;
                }
            }
            for(String key : animations.get("3D").keySet()){
                if(args[i].equals(key)){
                    runner = animationRunner3d;
                    String[] params = handleCommandAnimationArgs(args, i+1);
                    if(params == null){
                        animationRunner3d.animations.add(animations.get("3D").get(key).apply(new String[0]));
                    }
                    else{
                        i = i + params.length;
                        animationRunner3d.animations.add(animations.get("3D").get(key).apply(params));
                    }
                    break;
                }
            }
            ++i;
        }
        if(runner == null){
            System.out.println("No valid animation arguments provided.");
            return;
        }
        runner.run();
    }

    public static void main(String[] args) {
        handleCommandLineArgs(args);
        //handleCommandLineArgs(new String[]{"domaincolor"});
    }
}

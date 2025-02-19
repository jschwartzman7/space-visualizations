package spacevisuals;

import spacevisuals.animations.pointsetanimations.*;
import spacevisuals.animations.fractalanimations.*;
import spacevisuals.helpers.AnimationHandler;
import spacevisuals.animations.vectorfieldanimations.*;
import spacevisuals.spaces.*;
import spacevisuals.animations.otheranimations.*;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedList;


public class Main {
    
    static Euclidean2D euclidean2d = Euclidean2D.Euclidean2DGet(5, .08, true);
    static Euclidean3D euclidean3d = Euclidean3D.Euclidean3DGet(5, .08, false);
    static Euclidean4DTranslation euclidean4d = new Euclidean4DTranslation(true);
    static SpaceAnimationRunner animationRunner2d = new SpaceAnimationRunner(euclidean2d, 25);
    static SpaceAnimationRunner animationRunner3d = new SpaceAnimationRunner(euclidean3d, 25);
    static SpaceAnimationRunner animationRunner4d = new SpaceAnimationRunner(euclidean4d, 25);
    static SpaceAnimationRunner runner;
    static HashMap<String, EnumMap<AnimationHandler, SpaceAnimation>> animations = new HashMap<String, EnumMap<AnimationHandler, SpaceAnimation>>(){
        {
        put("2D", new EnumMap<AnimationHandler, SpaceAnimation>(AnimationHandler.class){
            {
                put(AnimationHandler.basic2d, new Animation2DSpace());
                put(AnimationHandler.vectorfield2d, new VectorField2D());
                put(AnimationHandler.domaincolor, new DomainColor());
                put(AnimationHandler.pointmap2d, new PointMap2D());
                put(AnimationHandler.juliaset, new JuliaSet());
                put(AnimationHandler.mandelbrot, new MandelbrotSet());
                put(AnimationHandler.gradient, new Gradient());
                put(AnimationHandler.parametriccurve, new ParametricCurve());
            }
        });
        put("3D", new EnumMap<AnimationHandler, SpaceAnimation>(AnimationHandler.class){
            {
                put(AnimationHandler.basic3d, new Animation3DSpace());
                put(AnimationHandler.vectorfield3d, new VectorField3D());
                put(AnimationHandler.graph3d, new Graph3D());
                put(AnimationHandler.polygons, new Polygons());
                put(AnimationHandler.spheremagnet, new SphereMagnet());
            }
        });
        }
    };

    public static String[] buildAnimationParameters(String[] args, int index){
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

    public static int checkForAnimation(String[] args, int index, String dimensions, SpaceAnimationRunner newRunner){
        for(AnimationHandler key : animations.get(dimensions).keySet()){
            if(args[index].equals(key.toString())){
                runner = newRunner;
                SpaceAnimation animation = animations.get(dimensions).get(key);
                String[] params = buildAnimationParameters(args, index+1);
                    if(params != null){
                        index = index + params.length;
                    }
                    else{
                        params = new String[1];
                    }
                    animation.buildAnimation(params);
                    runner.animations.add(animations.get(dimensions).get(key));
                    break;
            }
        }
        return index;
    }

    public static void handleCommandLineArgs(String[] args){
        if(args.length == 0){
            System.out.println("No arguments provided.");
            return;
        }
        int i = 0;
        while(i < args.length){
            i = checkForAnimation(args, i, "2D", animationRunner2d);
            if(i >= args.length){break;}
            i = checkForAnimation(args, i, "3D", animationRunner3d);
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

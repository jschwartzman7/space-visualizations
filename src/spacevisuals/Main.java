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
    
    static String[] arguments;
    static Euclidean2D euclidean2d = Euclidean2D.Euclidean2DGet(5, .08, true);
    static Euclidean3D euclidean3d = Euclidean3D.Euclidean3DGet(5, .08, false);
    static Euclidean4DTranslation euclidean4d = new Euclidean4DTranslation(true);
    static SpaceAnimationRunner animationRunner2d = new SpaceAnimationRunner(euclidean2d, 25);
    static SpaceAnimationRunner animationRunner3d = new SpaceAnimationRunner(euclidean3d, 25);
    static SpaceAnimationRunner animationRunner4d = new SpaceAnimationRunner(euclidean4d, 25);
    static SpaceAnimationRunner runner;
    static AnimationHandler[] animations = AnimationHandler.values();

    public static String[] buildAnimationParameters(int index){
        LinkedList<String> params = new LinkedList<String>();
        while(index < arguments.length){
            if(arguments[index].equals(",")){
                break;
            }
            params.add(arguments[index]);
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
        for(int i = 0; i < args.length; ++i){
            for(AnimationHandler key : animations){
                if(arguments[i].equals(key.toString())){
                    SpaceAnimation animation = key.getAnimation();
                    String[] params = buildAnimationParameters(i+1);

                    for(String f: params){
                        System.out.println(f);
                    }
                    animation.buildAnimation(params);
                    runner = key.getDimensions().equals("3d") ? animationRunner3d : animationRunner2d;
                    runner.animations.add(key.getAnimation());
                    break;
                }
            }
        }
        if(runner == null){
            System.out.println("No valid animation arguments provided.");
            return;
        }
        runner.run();
    }

    public static void main(String[] args) {
        arguments = args;
        handleCommandLineArgs(args);
        //handleCommandLineArgs(new String[]{"domaincolor"});
    }
}

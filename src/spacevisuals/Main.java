package spacevisuals;
import spacevisuals.animations.AnimationsEnum;
import spacevisuals.animations.SpaceAnimation;
import spacevisuals.spaces.*;
import java.util.LinkedList;

/*
 * Parses comma separated animation function arguments
 */
public class Main {
    
    static String[] arguments;
    static Euclidean2D euclidean2d = Euclidean2D.Get();
    static Euclidean3D euclidean3d = Euclidean3D.Get();
    static Euclidean4D euclidean4d = Euclidean4D.Get();
    static SpaceAnimationRunner runner = new SpaceAnimationRunner(25);
    static AnimationsEnum[] animationOptions = AnimationsEnum.values();

    public static String[] buildAnimationParameters(int index){
        LinkedList<String> params = new LinkedList<String>();
        while(index < arguments.length){
            if(arguments[index].equals(",")){break;}
            params.add(arguments[index++]);
        }
        if(params.size() == 0){return null;}
        return params.toArray(new String[params.size()]);
    }

    public static void handleCommandLineArgs(String[] argumentArray){
        if(argumentArray.length == 0){
            System.out.println("No arguments provided.");
            return;
        }
        for(int i = 0; i < argumentArray.length; ++i){
            AnimationsEnum result = runner.addAnimation(argumentArray[i]);
            if(result != null){
                String[] params = buildAnimationParameters(i+1);
                result.animation.buildAnimation(params);
            }
        }
        runner.run();
    }

    public static void main(String[] args) {
        arguments = args;
        handleCommandLineArgs(arguments);
        //handleCommandLineArgs(new String[]{"domaincolor"});
    }
}

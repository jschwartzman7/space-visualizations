package spacevisuals;
import spacevisuals.animations.SpaceAnimation;
import spacevisuals.enums.AnimationsEnum;
import spacevisuals.spaces.*;

import java.util.ArrayList;

/*
 * Parses comma separated animation function arguments and runs a built SpaceAnimationRunner
 */
public class Main {
    
    static final char animationSeparator = ',';
    static String[] arguments;
    static Euclidean2D euclidean2d = Euclidean2D.Get();
    static Euclidean3D euclidean3d = Euclidean3D.Get(30, 0.01, true);
    static Euclidean4D euclidean4d = Euclidean4D.Get();
    static SpaceAnimationRunner runner = new SpaceAnimationRunner(20);
    static AnimationsEnum[] animationSelections = AnimationsEnum.values();

    public static String[] getUntilSeparator(int index){
        ArrayList<String> params = new ArrayList<String>();
        while(index < arguments.length){
            if(arguments[index].charAt(0) == animationSeparator){break;}
            params.add(arguments[index++]);
        }
        return params.toArray(new String[params.size()]);
    }

    public static void handleCommandLineArgs(String[] argumentArray){
        if(argumentArray.length == 0){
            System.out.println("No arguments provided.");
            return;
        }
        int i = 0;
        while(i < argumentArray.length){
            SpaceAnimation result = runner.addAnimation(argumentArray[i]);
            if(result != null){
                String[] params = getUntilSeparator(i+1);
                result.buildAnimation(params);
                i += params.length;
            }
            i++;
        }
        runner.run();
    }

    public static void main(String[] args) {
        arguments = args;
        handleCommandLineArgs(arguments);
        //handleCommandLineArgs(new String[]{"domaincolor"});
    }
}

package spacevisuals;
import spacevisuals.animations.SpaceAnimation;
import spacevisuals.enums.AnimationsEnum;
import spacevisuals.spaces.*;
import java.util.LinkedList;

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

    public static String[] getAnimationArguments(int index){
        LinkedList<String> params = new LinkedList<String>();
        while(index < arguments.length){
            if(arguments[index].charAt(0) == animationSeparator){break;}
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
        int i = 0;
        while(i < argumentArray.length){
            SpaceAnimation result = runner.addAnimation(argumentArray[i]);
            if(result != null){
                String[] params = getAnimationArguments(i+1);
                if(params != null){
                    result.buildAnimation(params);
                    i += params.length;
                }
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

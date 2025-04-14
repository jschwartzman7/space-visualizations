package spacevisuals.animations.spacefunctions.spacetraverseranimations.functiongraphs;


import spacevisuals.animations.SpaceTraverserAnimation;
import spacevisuals.colors.colorstrategies.ColorStrategy;
import spacevisuals.colors.colorstrategies.SingleColorStrategy;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.spacetraversers.*;
import spacevisuals.spaces.spacetraversers.steppers.ConstantResolutionStepper;
import spacevisuals.utils.TextBox;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.enums.FunctionsEnum;

import java.util.function.Function;

public class Graph3D extends SpaceTraverserAnimation{

    public static final Function<double[], double[]> DEFAULT_FUNCTION = FunctionsEnum.hyperbolicparabaloid.function;
    private SpaceTraverser traverser;
    private ColorStrategy colorHelper;
    private TextBox textBox;

    public Graph3D(){
        super(DEFAULT_FUNCTION, new RectangleTraverser3D());
        this.colorHelper = new SingleColorStrategy(Euclidean3D.Get().colorScheme.labelColor);
        this.textBox = new TextBox();
    }

    @Override
    public void handlePoint(double[] input){
        double[] output = function.apply(input);
        if (output[0] < Euclidean3D.Get().zAxisMin || output[0] > Euclidean3D.Get().zAxisMax){
            return;
        }
        for(double outputPoint: output){
            double[] point = Euclidean3D.Get().toViewScreenPoint(new double[]{input[0], input[1], outputPoint});
            if(point == null){
                continue;
            }
            StdDraw.setPenColor(colorHelper.getColor(input));
            StdDraw.point(point[0], point[1]);
        }
    }
}

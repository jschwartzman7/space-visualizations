package spacevisuals.animations.spacefunctions.functiongraph;


import spacevisuals.animations.PointSetAnimation;
import spacevisuals.animations.SpaceTraverserAnimation;
import spacevisuals.colors.*;
import spacevisuals.colors.colorstrategies.ColorStrategy;
import spacevisuals.colors.colorstrategies.SingleColorStrategy;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.spacetraversers.*;
import spacevisuals.spaces.spacetraversers.steppers.ConstantResolutionTraverser;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.enums.FunctionsEnum;
import spacevisuals.helpers.TextBox;

import java.awt.Color;
import java.util.function.Consumer;
import java.util.function.Function;

public class Graph3D extends SpaceTraverserAnimation<Euclidean3D>{

    public static final Function<double[], double[]> DEFAULT_FUNCTION = FunctionsEnum.hyperbolicparabaloid.function;
    private SpaceTraverser<Euclidean3D> traverser;
    private ColorStrategy colorHelper;
    private TextBox textBox;

    public Graph3D(){
        super(Euclidean3D.Get(), DEFAULT_FUNCTION, new RectangleTraverser3D(Euclidean3D.Get(), new ConstantResolutionTraverser()));
        this.colorHelper = new SingleColorStrategy(getSpace().colorScheme.labelColor);
        this.textBox = new TextBox(getSpace());
    }

    @Override
    public void handlePoint(double[] input){
        double[] output = function.apply(input);
        if (output[0] < getSpace().zAxisMin || output[0] > getSpace().zAxisMax){
            return;
        }
        for(double outputPoint: output){
            double[] point = getSpace().toViewScreenPoint(new double[]{input[0], input[1], outputPoint});
            if(point == null){
                continue;
            }
            StdDraw.setPenColor(colorHelper.getColor(input));
            StdDraw.point(point[0], point[1]);
        }
    }
}

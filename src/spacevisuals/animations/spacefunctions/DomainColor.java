package spacevisuals.animations.spacefunctions;

import spacevisuals.animations.SpaceTraverserAnimation;
import spacevisuals.colors.colorstrategies.ColorStrategy;
import spacevisuals.colors.colorstrategies.DomainColorStrategy;
import spacevisuals.enums.FunctionsEnum;
import java.util.function.Function;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;

import spacevisuals.spaces.SpaceUser2D;
import spacevisuals.spaces.spacetraversers.ClippingTraverser;

public class DomainColor extends SpaceTraverserAnimation implements SpaceUser2D{

    public static final double DEFAULT_PIXEL_RESOLUTION = 200;
    private ColorStrategy colorHelper;

    public DomainColor(){
        super(FunctionsEnum.sin.function, new ClippingTraverser());
        this.colorHelper = new DomainColorStrategy();
    }
    public DomainColor(Function<double[], double[]> function){
        super(function, new ClippingTraverser());
        this.colorHelper = new DomainColorStrategy();
    }
    @Override
    public void handlePoint(double[] z){
        double[] w = function.apply(z);
        if(w == null){
            return;
        }
        Color color = colorHelper.getColor(w);
        double[] z2 = new double[]{z[0], z[1]};
        double radius = space().getXRange()/(2*DEFAULT_PIXEL_RESOLUTION);
        StdDraw.filledSquare(z[0], z[1], radius);
    }
}

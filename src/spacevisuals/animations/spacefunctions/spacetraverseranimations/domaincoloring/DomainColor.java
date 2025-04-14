package spacevisuals.animations.spacefunctions.spacetraverseranimations.domaincoloring;

import spacevisuals.animations.SpaceTraverserAnimation;
import spacevisuals.colors.colorstrategies.ColorStrategy;
import spacevisuals.colors.colorstrategies.DomainColorStrategy;
import spacevisuals.enums.FunctionsEnum;
import java.util.function.Function;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;

import spacevisuals.spaces.SpaceUser2D;
import spacevisuals.spaces.spacetraversers.ClippingTraverser;
import spacevisuals.utils.Constants;

public class DomainColor extends SpaceTraverserAnimation implements SpaceUser2D{

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
        if(w.length < 2){
            return;
        }
        Color color = colorHelper.getColor(w);
        StdDraw.setPenColor(color);
        double halfWidth = (space().xClipMax-space().xClipMin)/(Constants.PIXEL_RESOLUTION_MEDIUM*2);
        double halfHeight = (space().yClipMax-space().yClipMin)/(Constants.PIXEL_RESOLUTION_MEDIUM*2);
        StdDraw.filledRectangle(z[0], z[1], halfWidth, halfHeight);
    }
}

package spacevisuals.animations.functionanimations.spacetraverseranimations.domaincoloring;

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
    private static int resolution = Constants.PIXEL_RESOLUTION_MEDIUM;

    public DomainColor(){
        super(new ClippingTraverser(resolution));
        this.colorHelper = new DomainColorStrategy();
        this.defaultFunction = FunctionsEnum.sin.function;
    }
    public DomainColor(Function<double[], double[]> function){
        super(function, new ClippingTraverser(resolution));
        this.colorHelper = new DomainColorStrategy();
        this.defaultFunction = FunctionsEnum.sin.function;
    }
    @Override
    public void handleInputOutput(double[] z, double[] w){
        if(w == null){
            return;
        }
        if(w.length < 2){
            return;
        }
        Color color = colorHelper.getColor(w);
        StdDraw.setPenColor(color);
        double halfWidth = (space().xClipMax-space().xClipMin)/(resolution*2);
        double halfHeight = (space().yClipMax-space().yClipMin)/(resolution*2);
        StdDraw.filledRectangle(z[0], z[1], halfWidth, halfHeight);
    }
}

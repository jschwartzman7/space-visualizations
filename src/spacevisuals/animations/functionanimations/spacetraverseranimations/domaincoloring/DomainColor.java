package spacevisuals.animations.functionanimations.spacetraverseranimations.domaincoloring;

import spacevisuals.animations.SpaceTraverserAnimation;
import spacevisuals.colors.colorstrategies.ColorStrategy;
import spacevisuals.colors.colorstrategies.DomainColorStrategy;
import spacevisuals.enums.FunctionsEnum;
import java.util.function.Function;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.SpaceUser2D;
import spacevisuals.spaces.spacetraversers.ClippingTraverser;
import spacevisuals.utils.Constants;

public class DomainColor extends SpaceTraverserAnimation implements SpaceUser2D{

    private static int resolution = Constants.PIXEL_RESOLUTION_MEDIUM;

    public DomainColor(){
        super(new ClippingTraverser(resolution));
        this.colorHelper = new DomainColorStrategy();
        this.defaultFunction = FunctionsEnum.sin.function;
    }
    public DomainColor(Function<double[], double[]> function, int resolution){
        super(function, new ClippingTraverser(resolution));
        this.colorHelper = new DomainColorStrategy();
        this.defaultFunction = FunctionsEnum.sin.function;
    }
    @Override
    public void handleInputOutput(double[] z, double[] w){
        if(w == null || w.length == 0 || Double.isNaN(w[0])|| Double.isInfinite(w[0])){
            return;
        }
        if(w.length == 2){
            if(Double.isNaN(w[1])|| Double.isInfinite(w[1])){
                return;
            }
            StdDraw.setPenColor(colorHelper.getColor(w));
        }
        double halfWidth = (space().xClipMax-space().xClipMin)/(resolution*2);
        double halfHeight = (space().yClipMax-space().yClipMin)/(resolution*2);
        StdDraw.filledRectangle(z[0], z[1], halfWidth, halfHeight);
    }
}

package spacevisuals.animations.spacefunctions;

import spacevisuals.animations.SpaceTraverserAnimation;
import spacevisuals.colors.colorstrategies.ColorStrategy;
import spacevisuals.colors.colorstrategies.DomainColorStrategy;
import spacevisuals.enums.FunctionsEnum;
import spacevisuals.spaces.Euclidean2D;
import java.util.function.Function;
import java.awt.Color;
import spacevisuals.spaces.spacetraversers.ClippingTraverser;
import spacevisuals.spaces.spacetraversers.steppers.ConstantResolutionTraverser;

public class DomainColor extends SpaceTraverserAnimation<Euclidean2D>{

    public static final double DEFAULT_PIXEL_RESOLUTION = 250;
    private ClippingTraverser traverser;
    private ColorStrategy colorHelper;

    public DomainColor(){
        super(Euclidean2D.Get(), FunctionsEnum.sin.function, new ClippingTraverser(Euclidean2D.Get(), new ConstantResolutionTraverser()));
        this.colorHelper = new DomainColorStrategy();
    }
    public DomainColor(Function<double[], double[]> function){
        super(Euclidean2D.Get(), function, new ClippingTraverser(Euclidean2D.Get(), new ConstantResolutionTraverser()));
        this.traverser = new ClippingTraverser(getSpace(), new ConstantResolutionTraverser());
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
        traverser.drawPointRectangle(z2, color);
        //double radius = (space.X_MAX-space.X_MIN)/(2*this.pixelResolution);
        //StdDraw.filledSquare(z[0], z[1], radius);
    }
}

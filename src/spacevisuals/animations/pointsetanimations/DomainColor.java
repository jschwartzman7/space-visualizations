package spacevisuals.animations.pointsetanimations;

import spacevisuals.animations.PointSetAnimation;
import spacevisuals.colorstrategies.*;
import spacevisuals.enums.FunctionsEnum;
import spacevisuals.functionhandling.SpaceFunction2D;
import java.util.function.Consumer;
import java.util.function.Function;
import java.awt.Color;

import spacevisuals.spaces.spacetraversers.ClippingTraverser;

public class DomainColor extends SpaceFunction2D implements PointSetAnimation{

    public static final Function<double[], double[]> DEFAULT_FUNCTION = FunctionsEnum.sin.function;
    public static final double DEFAULT_PIXEL_RESOLUTION = 250;
    private ClippingTraverser traverser;
    private ColorStrategy colorHelper;

    public DomainColor(){
        super();
        this.traverser = new ClippingTraverser(space, DEFAULT_PIXEL_RESOLUTION);
        this.colorHelper = new DomainColorStrategy();
        setFunction(DEFAULT_FUNCTION);
    }
    public DomainColor(Function<double[], double[]> function){
        super(function);
        this.traverser = new ClippingTraverser(space, DEFAULT_PIXEL_RESOLUTION);
        this.colorHelper = new DomainColorStrategy();
        setFunction(DEFAULT_FUNCTION);
    }
    @Override
	public void drawAnimation() {
		PointSetAnimation.super.drawAnimation();
	}
    @Override
    public void traverseDomain(Consumer<double[]> handlePoint){
        traverser.traverseDomain(this::handlePoint);
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
    @Override
    public void buildAnimation(String[] parameters) {
        setFunctionStringArray(parameters);
    }
}

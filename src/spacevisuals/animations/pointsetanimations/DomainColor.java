package spacevisuals.animations.pointsetanimations;

import spacevisuals.*;
import spacevisuals.colors.ColorStrategy;
import spacevisuals.colors.DomainColorStrategy;
import java.util.function.Consumer;
import java.util.function.Function;
import java.awt.Color;
import spacevisuals.spaces.spacetraversers.ClippingTraverser;

public class DomainColor extends Animation2DSpace implements PointSetAnimation{

    ClippingTraverser traverser;
    ColorStrategy colorHelper = new DomainColorStrategy();

    public DomainColor(){
        super();
        this.traverser = new ClippingTraverser (space);
    }
    public DomainColor(Function<double[], double[]> function){
        super(function);
        this.traverser = new ClippingTraverser (space);
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
    public void traverseDomain(Consumer<double[]> handlePoint){
        traverser.traverseDomain(this::handlePoint);
    }
    @Override
	public void drawAnimation() {
		PointSetAnimation.super.drawAnimation();
	}
}

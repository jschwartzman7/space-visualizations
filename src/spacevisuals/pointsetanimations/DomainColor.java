package spacevisuals.pointsetanimations;

import spacevisuals.*;
import spacevisuals.helpers.ColorHelper;
import edu.princeton.cs.introcs.StdDraw;
import java.lang.Math;
import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.awt.Color;
import java.awt.event.KeyEvent;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.helpers.Euclidean2DTraverser;

public class DomainColor implements PointSetAnimation{

    Euclidean2DTraverser traverser;
    Function<double[], double[]> function;
    ColorHelper colorHelper = new ColorHelper(2);
    Euclidean2D space;

    public DomainColor(Euclidean2D space, Function<double[], double[]> function){
        this.space = space;
        this.traverser = new Euclidean2DTraverser (space);
        this.function = function;
    }

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
    public void traverseDomain(Consumer<double[]> handlePoint){
        traverser.traverseDomain(this::handlePoint);
    }
}

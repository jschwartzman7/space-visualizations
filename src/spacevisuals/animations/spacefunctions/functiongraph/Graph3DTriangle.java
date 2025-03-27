package spacevisuals.animations.spacefunctions.functiongraph;


import spacevisuals.animations.PointSetAnimation;
import spacevisuals.animations.polygons.solids.Triangle;
import spacevisuals.colors.*;
import spacevisuals.colors.colorstrategies.DomainColorStrategy;
import spacevisuals.colors.colorstrategies.SingleColorStrategy;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.spacemovers.SpaceMover3D;
import spacevisuals.SpaceFunction;
import spacevisuals.spaces.spacetraversers.*;
import spacevisuals.spaces.spacetraversers.steppers.ConstantResolutionTraverser;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.enums.FunctionsEnum;
import spacevisuals.functions.Matrix3D;
import spacevisuals.functions.Rn_R;
import spacevisuals.functions.Rn_Rn;
import spacevisuals.helpers.Camera3D;
import spacevisuals.helpers.TextBox;

import java.awt.Color;
import java.util.function.Consumer;
import java.util.function.Function;

public class Graph3DTriangle extends SpaceFunction<Euclidean3D> implements PointSetAnimation{

    public static final Function<double[], double[]> DEFAULT_FUNCTION = FunctionsEnum.hyperbolicparabaloid.function;
    private SpaceTraverser<Euclidean3D> traverser;
    private shader colorHelper;
    private Camera3D lightSource;

    public Graph3DTriangle(){
        super(Euclidean3D.Get(), DEFAULT_FUNCTION);
        this.traverser = new RectangleTraverser3DTriangle(getSpace(), new ConstantResolutionTraverser());
        this.colorHelper = new shader(new DomainColorStrategy());
        this.lightSource = new Camera3D(0,0,0,30,30,30,10);
    }

    @Override
    public void traverseDomain(Consumer<double[]> handlePoint){
        traverser.traverseDomain(this::handlePoint);
    }
    @Override
    public void handlePoint(double[] triangle){
        double[] p1Output = function.apply(new double[]{triangle[0], triangle[3]});
        double[] p2Output = function.apply(new double[]{triangle[1], triangle[4]});
        double[] p3Output = function.apply(new double[]{triangle[2], triangle[5]});
        for(int i = 0; i < p1Output.length; i++){
            double output1 = p1Output[i];
            double output2 = p2Output[i];
            double output3 = p3Output[i];
            if(Double.isNaN(output1) || Double.isNaN(output2) || Double.isNaN(output3)){
                continue;
            }
            double[] p1 = new double[]{triangle[0], triangle[3], output1};
            double[] p2 = new double[]{triangle[1], triangle[4], output2};
            double[] p3 = new double[]{triangle[2], triangle[5], output3};
            double[][] triangleCords = new double[][]{p1, p2, p3};
            Triangle Triangle = new Triangle(triangleCords);
            double dotProduct = Rn_R.dotProduct(lightSource.toCameraPosition(new double[]{1,1,1}), Matrix3D.crossProduct(Rn_Rn.pairwiseSubtract(p2, p1), Rn_Rn.pairwiseSubtract(p3, p1)));
            colorHelper.correlation = Math.abs(dotProduct);
            StdDraw.setPenColor(colorHelper.getColor(p1));
            Triangle.draw(getSpace());
        }
    }
    @Override
    public void configureAnimation(String[] parameters) {
        setCustomFunctionStringArray(parameters);
    }
}

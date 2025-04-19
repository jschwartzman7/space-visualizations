package spacevisuals.animations.functionanimations.spacetraverseranimations.functiongraphs;


import spacevisuals.animations.SpaceTraverserAnimation;
import spacevisuals.animations.polygons.solids.Triangle;
import spacevisuals.colors.*;
import spacevisuals.colors.colorstrategies.DomainColorStrategy;
import spacevisuals.colors.colorstrategies.SingleColorStrategy;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.spacetraversers.*;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.enums.FunctionsEnum;

import java.awt.Color;
import java.util.function.Function;

public class Graph3DTriangle extends SpaceTraverserAnimation{

    public static final Function<double[], double[]> DEFAULT_FUNCTION = FunctionsEnum.hyperbolicparabaloid.function;
    private Shader colorHelper;

    public Graph3DTriangle(){
        super(DEFAULT_FUNCTION, new RectangleTraverser3DTriangle());
        this.colorHelper = new Shader(new SingleColorStrategy(Color.orange));
    }

    @Override
    public void handlePoint(double[] triangle){
        double[] p1Output = applyFunction(new double[]{triangle[0], triangle[3]});
        double[] p2Output = applyFunction(new double[]{triangle[1], triangle[4]});
        double[] p3Output = applyFunction(new double[]{triangle[2], triangle[5]});
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
            StdDraw.setPenColor(colorHelper.getColor(Triangle));
            Triangle.draw(Euclidean3D.Get());
        }
    }

    @Override
    public void handleInputOutput(double[] input, double[] output) {
        return;
    }
}

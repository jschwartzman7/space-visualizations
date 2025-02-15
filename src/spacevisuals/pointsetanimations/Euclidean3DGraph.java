package spacevisuals.pointsetanimations;


import spacevisuals.*;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.spacetraversers.Euclidean3DPlaneTraverser;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.colors.*;
import java.awt.Color;
import java.util.function.Consumer;
import java.util.function.Function;

public class Euclidean3DGraph extends PointSetAnimation<Euclidean3D> {

    Euclidean3DPlaneTraverser traverser;
    ColorStrategy colorHelper;

    public Euclidean3DGraph(Euclidean3D space, Function<double[], double[]> function){
        super(space, function);
        this.traverser = new Euclidean3DPlaneTraverser(space);
        this.colorHelper = new DomainColorStrategy();
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        traverser.traverseDomain(this::handlePoint);
    }

    @Override
    public void handlePoint(double[] input){
        double[] output = function.apply(input);
        if (output[0] < space.zAxisMin || output[0] > space.zAxisMax){
            return;
        }
        if(output.length > 1){
            for(double outputPoint: output){
                double[] point = space.toViewScreenPoint(new double[]{input[0], input[1], outputPoint});
                StdDraw.setPenColor(colorHelper.getColor(input));
                StdDraw.point(point[0], point[1]);
            }
        }
        
    }

}

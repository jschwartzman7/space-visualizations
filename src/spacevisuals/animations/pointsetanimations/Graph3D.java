package spacevisuals.animations.pointsetanimations;


import spacevisuals.*;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.spacetraversers.*;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.colors.*;
import spacevisuals.helpers.TextBox;
import java.util.function.Consumer;

public class Graph3D extends Animation3DSpace implements PointSetAnimation{

    EuclideanSpaceTraverser<Euclidean3D> traverser;
    ColorStrategy colorHelper;
    TextBox textBox;

    public Graph3D(){
        super();
        this.traverser = new Euclidean3DDiskTraverser(space);
        this.colorHelper = new DomainColorStrategy();
        this.textBox = new TextBox(space);
    }
    
    @Override
    public void traverseDomain(Consumer<double[]> handlePoint){
        traverser.traverseDomain(this::handlePoint);
        textBox.clearText();
        textBox.addText("pitch", space.camera.pitch/Math.PI + "PI");
        textBox.addText("roll", space.camera.roll/Math.PI + "PI");
        textBox.addText("yaw", space.camera.yaw/Math.PI + "PI");
        textBox.addText("x", space.camera.x + "");
        textBox.addText("y", space.camera.y + "");
        textBox.addText("z", space.camera.z + "");
        textBox.drawTextBox();
    }

    @Override
    public void drawAnimation(){
        PointSetAnimation.super.drawAnimation();
    }
    @Override
    public void handlePoint(double[] input){
        double[] output = function.apply(input);
        if (output[0] < space.zAxisMin || output[0] > space.zAxisMax){
            return;
        }
        for(double outputPoint: output){
            double[] point = space.toViewScreenPoint(new double[]{input[0], input[1], outputPoint});
            StdDraw.setPenColor(colorHelper.getColor(input));
            StdDraw.point(point[0], point[1]);
        }
    }
}

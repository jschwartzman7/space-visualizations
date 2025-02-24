package spacevisuals.animations.pointsetanimations;


import spacevisuals.*;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.SpaceFunction3D;
import spacevisuals.spaces.spacetraversers.*;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.colors.*;
import spacevisuals.colors.colorstrategies.ColorStrategy;
import spacevisuals.colors.colorstrategies.DomainColorStrategy;
import spacevisuals.functions.functionhandling.FunctionsEnum;
import spacevisuals.helpers.TextBox;
import java.util.function.Consumer;
import java.util.function.Function;

public class Graph3D extends SpaceFunction3D implements PointSetAnimation{

    private SpaceTraverser<Euclidean3D> traverser;
    private ColorStrategy colorHelper;
    private TextBox textBox;

    public Graph3D(){
        super();
        this.traverser = new DiskTraverser3D(space);
        this.colorHelper = new DomainColorStrategy();
        this.textBox = new TextBox(space);
    }

    public void buildAnimation(Function<double[], double[]> function) {
        this.function = function;
    }

    @Override
    public void drawAnimation(){
        PointSetAnimation.super.drawAnimation();
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
    @Override
    public void buildAnimation(String[] parameters) {
        setFunctionStringArray(parameters);
    }
}

package spacevisuals.animations.spacefunctions.functiongraph;


import spacevisuals.animations.PointSetAnimation;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.SpaceFunction;
import spacevisuals.spaces.spacetraversers.*;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.colorstrategies.*;
import spacevisuals.enums.FunctionsEnum;
import spacevisuals.helpers.TextBox;
import java.util.function.Consumer;
import java.util.function.Function;

public class Graph3D extends SpaceFunction<Euclidean3D> implements PointSetAnimation{

    public static final Function<double[], double[]> DEFAULT_FUNCTION = FunctionsEnum.hyperbolicparabaloid.function;
    private SpaceTraverser<Euclidean3D> traverser;
    private ColorStrategy colorHelper;
    private TextBox textBox;

    public Graph3D(){
        super(Euclidean3D.Get(), DEFAULT_FUNCTION);
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
        textBox.addText("pitch", space.mover.pitch/Math.PI + "PI");
        textBox.addText("roll", space.mover.roll/Math.PI + "PI");
        textBox.addText("yaw", space.mover.yaw/Math.PI + "PI");
        textBox.addText("x", space.mover.x + "");
        textBox.addText("y", space.mover.y + "");
        textBox.addText("z", space.mover.z + "");
        textBox.addText("focal length", space.mover.focalLength + "");
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
            if(point == null){
                continue;
            }
            StdDraw.setPenColor(colorHelper.getColor(input));
            StdDraw.point(point[0], point[1]);
        }
    }
    @Override
    public void configureAnimation(String[] parameters) {
        setCustomFunctionStringArray(parameters);
    }
}

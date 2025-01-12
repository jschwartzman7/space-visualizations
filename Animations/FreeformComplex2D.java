import edu.princeton.cs.introcs.StdDraw;
import java.lang.Math;
import java.util.HashMap;
import java.awt.Color;
import java.awt.event.KeyEvent;


public class FreeformComplex2D extends abstractAnimation {
      final int resolution = 500;

    // improve resolution as zooms in, same with fractals

    abstractFunction function;
    Euclidean2D space;
    HashMap<double[], double[]> functionMap;
  
    public FreeformComplex2D(){
        super(function, space, pixelResolution, frameSpeed);
        this.function =  new FunctionC_C();
        this.space = new Euclidean2D(5, 10, true);
        functionMap = new HashMap<double[], double[]>();
    }


    public Color getColor2(double[] w){
        double magnitude = Math.hypot(w[0], w[1]);

        double hue = Math.atan2(w[1], w[0])/(2*Math.PI);
        double saturation = Math.exp(-.1*magnitude);
        double brightness = Math.exp(-.1*magnitude);
        return Color.getHSBColor((float)hue, (float)saturation, (float)brightness);
        
    }

    public void draw(){
        StdDraw.setPenRadius(0.01);
        for(double[] point : functionMap.keySet()){
            double[] mappedPoint = functionMap.get(point);
            StdDraw.setPenColor(getColor2(mappedPoint));
            StdDraw.point(point[0], point[1]);
            StdDraw.point(mappedPoint[0], mappedPoint[1]);
        }
    }

    public void updateNewPoints(){
        if(StdDraw.isMousePressed()){
            double [] newPoint = new double[]{StdDraw.mouseX(), StdDraw.mouseY()};
            functionMap.put(newPoint, function.identity(newPoint));
        }
    }


    public static void main(String[] args) {
        FreeformComplex2D test = new FreeformComplex2D();
        test.run();
    }
}

import edu.princeton.cs.introcs.StdDraw;
import java.util.function.Function;
public class R3Graph extends abstractAnimation {

    public Euclidean3D space;
    public abstractFunction function;
    public int pixelResolution;
    public Function<double[], double[]> hype;
    public Object x;
    
    public R3Graph(abstractFunction function, Euclidean3D space, int pixelResolution){
        super(space, 25);
        this.space = space;
        this.function = function;
        this.pixelResolution = pixelResolution;
    }

    public void draw(){
        double numericXMin = space.X_MIN*space.xyDistortion;
        double numericXMax = space.X_MAX*space.xyDistortion;
        double numericYMin = space.Y_MIN*space.xyDistortion;
        double numericYMax = space.Y_MAX*space.xyDistortion;
        double numericZMin = space.Z_MIN*space.zDistortion;
        double numericZMax = space.Z_MAX*space.zDistortion;
        for(double numericX = numericXMin; numericX <= numericXMax; numericX += (numericXMax-numericXMin)/pixelResolution){
            for(double numericY = numericYMin; numericY <= numericYMax; numericY += (numericYMax-numericYMin)/pixelResolution){
                double[] point = new double[]{numericX/space.xyDistortion, numericY/space.xyDistortion, function.hype(new double[]{numericX, numericY})[0]/space.zDistortion};
                point = Matrix.matrixVectorMultiplication(space.currentPosition, point);
                double[] point2D = space.to2D(point);
                StdDraw.filledCircle(point2D[0], point2D[1], 0.01);
                
                //pointValues.add(get3D(new double[]{x, y, output[0], output[1]}));
                //pointValues.add(get4D(new double[]{x, y, output[0], output[1]}));

            }
        }
        
    }

    public void update(){}

    
}

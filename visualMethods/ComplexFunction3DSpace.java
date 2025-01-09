import java.util.Hashtable;
import java.awt.Color;
import java.util.HashSet;
import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;

public class ComplexFunction3DSpace extends abstractAnimation{

    public euclideanR3 space;
    public FunctionR2_R function;
    public int pixelResolution;
    
    public ComplexFunction3DSpace(FunctionR2_R function, euclideanR3 space, int pixelResolution, int frameSpeed){
        super(space, frameSpeed);
        this.space = space;
        this.function = function;
        this.pixelResolution = pixelResolution;
    }

    public void draw(){
        StdDraw.setPenColor(Color.blue);
        double numericXMin = space.X_MIN*space.xyDistortion;
        double numericXMax = space.X_MAX*space.xyDistortion;
        double numericYMin = space.Y_MIN*space.xyDistortion;
        double numericYMax = space.Y_MAX*space.xyDistortion;

        for(double numericX = numericXMin; numericX <= numericXMax; numericX += (numericXMax-numericXMin)/pixelResolution){
            for(double numericY = numericYMin; numericY <= numericYMax; numericY += (numericYMax-numericYMin)/pixelResolution){
                /*
                 * double[] labelLocation = to2D(matrix.matrixVectorMultiplication(currentPosition, new double[]{numericX/xyDistortion, 0, 0}));
                 * StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericX));
                 */
                double[] point = new double[]{numericX/space.xyDistortion, numericY/space.xyDistortion, function.hype(new double[]{numericX, numericY})[0]/space.zDistortion};
                point = matrix.matrixVectorMultiplication(space.currentPosition, point);
                double[] point2D = space.to2D(point);
                StdDraw.filledCircle(point2D[0], point2D[1], 0.01);
                
                //pointValues.add(get3D(new double[]{x, y, output[0], output[1]}));
                //pointValues.add(get4D(new double[]{x, y, output[0], output[1]}));

            }
        }
        
    }

    public void update(){
        
    }

    // Determines what will be graphed on the z axis

    public double get3D(double[] w){
        return w[0];
        //return new double[]{point[0], point[1], Math.atan2(point[3], point[2])};
        //return new double[]{point[0], point[1], point[2]};
    }

    public static void main(String[] args) {
        //System.out.println("HERE: "+args[0]);
       new ComplexFunction3DSpace(new FunctionR2_R(), new euclideanR3(5, 10, true), 100, 25).run();
        // <x+3, yxz, 6z-y>
        //run(inputs)
        
    }
}

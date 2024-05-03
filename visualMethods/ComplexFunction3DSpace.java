import java.util.Hashtable;
import java.awt.Color;
import java.util.HashSet;
import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;

public class ComplexFunction3DSpace extends abstractVisualMethod{
    
    public abstractFunction function = new ComplexFunction();
    public euclideanR3 space = new euclideanR3(5, 0, false);
    public double resolution = 100;
    public LinkedList<double[]> pointValues = new LinkedList<double[]>();
    //public static LinkedList<HashSet<double[]>> functionPoints = new LinkedList<HashSet<double[]>>();

    public ComplexFunction3DSpace(){

    }

    public void drawPoints(){
        double xStep = (space.X_MAX-space.X_MIN)/resolution;
        double yStep = (space.Y_MAX-space.Y_MIN)/resolution;
        StdDraw.setPenColor(Color.blue);
        //pointValues.clear();
        for(double x = space.X_MIN; x <= space.X_MAX; x += xStep){
            for(double y = space.Y_MIN; y <= space.Y_MAX; y += yStep){
                    double[] output = function.f(new double[]{x, y});
                    double[] point3D = new double[]{x, y, get3D(output)};
                    pointValues.add(point3D);
                    double[] point2D = space.to2D(point3D);
                    StdDraw.filledCircle(point2D[0], point2D[1], 0.01);
                    
                   // pointValues.add(get3D(new double[]{x, y, output[0], output[1]}));
                   // pointValues.add(get4D(new double[]{x, y, output[0], output[1]}));

            }
        }
    }

    // Determines what will be graphed on the z axis

    public double get3D(double[] w){
        return Math.hypot(w[0], w[1]);
        //return new double[]{point[0], point[1], Math.atan2(point[3], point[2])};
        //return new double[]{point[0], point[1], point[2]};
    }

    public double get4D(double[] w){
        //return new double[]{point[0], point[1], Math.hypot(point[2], point[3])};
        //return new double[]{point[0], point[1], Math.atan2(point[3], point[2])};
        //return new double[]{point[0], point[1], point[3]};
        return 0;
        
    }


    public void drawPointsOld(){
        

        StdDraw.setPenColor();
		for(double[] point : pointValues) {
			double[] point2D = space.to2D(point);
            StdDraw.setPenColor(Color.blue);
            
			StdDraw.filledCircle(point2D[0], point2D[1], 0.01);
	//		threeDimensionalCords.drawCord(point[0], point[1], point[2]);
		}
    }

    public void run(){
        
        while(true){
            StdDraw.clear();
            space.draw();
            drawPoints();
            space.updatePoints(pointValues);
            space.update();
            StdDraw.show(50);

        }
    }


    public static void main(String[] args) {
        //System.out.println("HERE: "+args[0]);
       new ComplexFunction3DSpace().run();
        // <x+3, yxz, 6z-y>
        //run(inputs)
        
    }
}

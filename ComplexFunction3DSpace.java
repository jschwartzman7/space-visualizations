import java.util.Hashtable;
import java.awt.Color;
import java.util.HashSet;
import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;

public class ComplexFunction3DSpace {
    
    public abstractFunction function = new ComplexFunction();
    public euclideanR3 space = new euclideanR3();
    public static double domainStep = .07;
    public static double X_MIN = -3;
    public static double X_MAX = 3;
    public static double Y_MIN = -3;
    public static double Y_MAX = 3;
    public static LinkedList<double[]> pointValues = new LinkedList<double[]>();
    public static LinkedList<HashSet<double[]>> functionPoints = new LinkedList<HashSet<double[]>>();



    public void addPointValues(){
        for(double x = X_MIN; x <= X_MAX; x += domainStep){
            for(double y = Y_MIN; y <= Y_MAX; y += domainStep){
                    double[] output = function.f(new double[]{x, y});
                    pointValues.add(get3D(new double[]{x, y, output[0], output[1]}));
                    //pointValues.add(get4D(new double[]{x, y, output[0], output[1]}));
            }
        }
    }

    public double[] get3D(double[] point){
        return new double[]{point[0], point[1], Math.hypot(point[2], point[3])};
        //return new double[]{point[0], point[1], Math.atan2(point[3], point[2])};
        //eturn new double[]{point[0], point[1], point[2]};
    }

    public void get4D(double[] point){
        //return new double[]{point[0], point[1], Math.hypot(point[2], point[3])};
        //return new double[]{point[0], point[1], Math.atan2(point[3], point[2])};
        //return new double[]{point[0], point[1], point[3]};
        
    }


    public void drawPoints(){
        StdDraw.setPenColor();
        int i = 0;
		for(double[] point : pointValues) {
			double[] point2D = space.to2D(point);
            if(i % 2 == 0){
                StdDraw.setPenColor(Color.blue);
            }
            else{
                StdDraw.setPenColor(Color.green);
            }
            ++i;
			StdDraw.filledCircle(point2D[0], point2D[1], 0.01);
	//		threeDimensionalCords.drawCord(point[0], point[1], point[2]);
		}
    }

    public void run(){
        addPointValues();
        StdDraw.setScale(-space.DEFAULT_XY_SCALE, space.DEFAULT_XY_SCALE);
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

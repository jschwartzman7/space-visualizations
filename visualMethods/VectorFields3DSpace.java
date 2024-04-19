import edu.princeton.cs.introcs.StdDraw;
import java.util.Hashtable;

class VectorFields3DSpace {
    
    public abstractFunction function = new functionR3_R3();
    public abstractSpaceVisuals space = new euclideanR3();
    public static double domainStep = 1;
    public static double X_MIN = -10;
    public static double X_MAX = 10;
    public static double Y_MIN = -10;
    public static double Y_MAX = 10;
    public static double Z_MIN = -10;
    public static double Z_MAX = 10;
    public static Hashtable<double[], double[]> pointValues = new Hashtable<double[], double[]>();



    public void addPointValues(){
        for(double x = X_MIN; x < X_MAX; x += domainStep){
            for(double y = Y_MIN; y < Y_MAX; y += domainStep){
                for(double z = Z_MIN; z < Z_MAX; z += domainStep){
                    double[] input = new double[]{x, y, z};
                    pointValues.put(input, function.f(input));
            
                }
            }
        }
    }

    public void drawVectors(){
        
    }

    public void run(){
        addPointValues();
        while(true){
            StdDraw.clear();
            space.draw();
            drawVectors();
            space.update();
            StdDraw.show(50);

        }
    }


    public static void main(String[] args) {
        //System.out.println("HERE: "+args[0]);
       new VectorFields3DSpace().run();
        // <x+3, yxz, 6z-y>
        //run(inputs)
        
    }
}

  
import edu.princeton.cs.introcs.StdDraw;
import java.util.Hashtable;


public class VectorFields2DSpace {
  
        
        //public abstractFunction function = new functionR2_R2();
        public abstractSpaceVisuals space = new euclideanR2();
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
                        double[] input = new double[]{x, y};
                        pointValues.put(input, function.f(input));
                }
            }
        }
    
        public void drawVectors(){
            for(double x = X_MIN; x < X_MAX; x += domainStep){
                for(double y = Y_MIN; y < Y_MAX; y += domainStep){
                        
                }
            }
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
    



}

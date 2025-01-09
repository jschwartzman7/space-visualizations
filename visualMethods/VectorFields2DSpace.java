  
import edu.princeton.cs.introcs.StdDraw;
import java.util.Hashtable;


public class VectorFields2DSpace extends abstractAnimation{
  
        public double resolution = 50;
        public FunctionR2_R2 function = new FunctionR2_R2();
        public euclideanR2 space = new euclideanR2(5, 10, true, true);
    
    
    
        public void draw(){
            double xRange = space.X_MAX-space.X_MIN;
            double yRange = space.Y_MAX-space.Y_MIN;
            for(double x = space.X_MIN; x <= space.X_MAX; x += xRange/resolution){
                for(double y = space.Y_MIN; y <= space.Y_MAX; y += yRange/resolution){
                        double[] input = new double[]{x, y};
                        drawVector(input);
                }
            }
        }
    
        public void drawVector(double[] input){
            /*
             * Add vector lines to list to send to space to draw
             */
            double[] output = function.f(input);
            double[] vector = function.subtract(input, output);
            double angle = Math.atan2(vector[1], vector[0]);
            double m = Math.hypot(vector[0], vector[1]);
            vector[0] /= m;
            vector[1] /= m;
            double range = Math.min(space.X_MAX-space.X_MIN, space.Y_MAX-space.Y_MIN);
            StdDraw.line(input[0], input[1], input[0]+Math.cos(angle)*(0.01*range), input[1]+Math.sin(angle)*(0.01*range));
    
        }
    
    
    
        public static void main(String[] args) {
            //System.out.println("HERE: "+args[0]);
           new VectorFields2DSpace().run();
            // <x+3, yxz, 6z-y>
            //run(inputs)
            
        }
    }
    





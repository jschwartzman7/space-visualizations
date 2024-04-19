  
import edu.princeton.cs.introcs.StdDraw;
import java.util.Hashtable;


public class VectorFields2DSpace {
  
        public double domainStep = 0.4;
        public double vectorLength = 0.1;
        public functionR2_R2 function = new functionR2_R2();
        public euclideanR2 space = new euclideanR2(5, false, 0);
        public static Hashtable<double[], double[]> mappedPoints = new Hashtable<double[], double[]>();
    
    
    
        public void addPointValues(){
            for(double x = space.X_MIN; x < space.X_MAX; x += domainStep){
                for(double y = space.Y_MIN; y < space.Y_MAX; y += domainStep){
                        double[] input = new double[]{x, y};
                        mappedPoints.put(input, function.f(input));
                }
            }
        }
    
        public void drawVectors(){
            for(double[] input : mappedPoints.keySet()){
                double[] vector = function.subtract(input, mappedPoints.get(input));
                double angle = Math.atan2(vector[1], vector[0]);
                double m = Math.hypot(vector[0], vector[1]);
                vector[0] /= m;
                vector[1] /= m;
                StdDraw.line(input[0], input[1], input[0]+Math.cos(angle)*vectorLength, input[1]+Math.sin(angle)*vectorLength);
            }
        }
    
        public void run(){
            while(true){
                StdDraw.clear();
                space.draw();
                addPointValues();
                drawVectors();
                space.update();
                StdDraw.show(50);
    
            }
        }
    
    
        public static void main(String[] args) {
            //System.out.println("HERE: "+args[0]);
           new VectorFields2DSpace().run();
            // <x+3, yxz, 6z-y>
            //run(inputs)
            
        }
    }
    





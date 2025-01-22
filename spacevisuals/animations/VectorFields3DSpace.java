package spacevisuals.animations;

import edu.princeton.cs.introcs.StdDraw;
import java.util.Hashtable;
import java.util.function.Function;


public class VectorFields3DSpace extends abstractLatticeAnimation<Euclidean3D> {
    
        public VectorFields3DSpace(Euclidean3D space, int frameSpeed, int resolution, Function<double[], double[]> function){
            super(space, frameSpeed, resolution, function);
        }

        public void updateAnimation(){
        }

        public void drawAnimation(){
            double numericXMin = space.X_MIN*space.xyDistortion;
            double numericXMax = space.X_MAX*space.xyDistortion;
            double numericYMin = space.Y_MIN*space.xyDistortion;
            double numericYMax = space.Y_MAX*space.xyDistortion;
            double numericZMin = space.Z_MIN*space.zDistortion;
            double numericZMax = space.Z_MAX*space.zDistortion;
            double xStep = (numericXMax-numericXMin)/this.pixelResolution;
            double yStep = (numericYMax-numericYMin)/this.pixelResolution;
            double zStep = (numericZMax-numericZMin)/this.pixelResolution;
            for(double x = numericXMin; x <= numericXMax; x += xStep){
                for(double y = numericYMin; y <= numericYMax; y += yStep){
                    for(double z = numericZMin; z <= numericZMax; z += zStep){
                        double[] input = new double[]{x, y, z};
                        handleImage(input, function.apply(input));
                    }
                }
            }
        }
    
        public void handleImage(double[] input3D, double[] output){
            /*
             * Add vector lines to list to send to space to draw
             */
            
            //double[] vector = FunctionR3_R3.subtract(output, input3D);
            //double angle = Math.atan2(vector[1], vector[0]);
            //double m = Math.hypot(vector[0], vector[1]);
            //vector[0] /= m;
            //vector[1] /= m;
            double[] start = Matrix.matrixVectorMultiplication(space.currentPosition, input3D);
            double[] end = Matrix.matrixVectorMultiplication(space.currentPosition, R3_R3.add(input3D, output));
            double[] start2D = space.to2D(start);
            double[] end2D = space.to2D(end);
            StdDraw.setPenColor();
            double range = Math.min(space.X_MAX-space.X_MIN, space.Y_MAX-space.Y_MIN);
            StdDraw.line(start2D[0], start2D[1], end2D[0], end2D[1]);
            //StdDraw.line(input[0], input[1], input[0]+Math.cos(angle)*(0.01*range), input[1]+Math.sin(angle)*(0.01*range));
        }
    
        public static void main(String[] args) {
            //System.out.println("HERE: "+args[0]);
            new VectorFields3DSpace(new Euclidean3D(3, 5, false), 25, 10, FunctionR3_R3::one).run();
            // <x+3, yxz, 6z-y>
            //run(inputs)
            
        }
    }
    





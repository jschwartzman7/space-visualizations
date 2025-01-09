import java.awt.Color;
import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;

public class ArcDeformation extends abstractAnimation{

    // draw "continuously" deforming semi-circles starting from upper hemisphere ending at lower hemisphere
    // StdDraw.arc(x, y, radius, angle1, angle2);
    // want center of arc (0, h) to move at constant speed
    // t ranges from -1 to 1

    double resoution = 0.008;
    LinkedList<Double> shadow;
    int shadowLength;
    double displayScale;
    double r0;
    double t = -1;
    double tStep = 0.008;
    
    public ArcDeformation(abstractFunction function, abstractSpaceVisual space, int pixelResolution, int frameSpeed, double displayScale, double minRadius, int shadowLength){
        super(function, space, pixelResolution, frameSpeed);
        this.displayScale = displayScale;
        this.r0 = minRadius;
        this.shadowLength = shadowLength;
        this.shadow = new LinkedList<Double>();
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setScale(-displayScale, displayScale);
        StdDraw.enableDoubleBuffering();
    }

    public void draw(){
        if(shadow.size() < shadowLength){
            shadow.addLast(t);
        }
        else{
            shadow.removeFirst();
            shadow.addLast(t);
        }
        int timePassed = shadowLength - shadow.size();
        for(Double pasT : shadow){
            StdDraw.setPenColor((int)(255*(1-(double)timePassed/shadowLength)),(int)(255*(1-(double)timePassed/shadowLength)),255);
            if(Math.abs(pasT) > resoution){
                double r = (r0*(1+pasT*pasT))/(2*pasT);
                double y = r-pasT*r0;
                double angle1 = Math.atan2(-y, -r0*Math.signum(pasT))*180/Math.PI;
                if(t < 0){
                    StdDraw.arc(0, y, Math.abs(r), angle1, 180-angle1);
                }
                else{
                    StdDraw.arc(0, y, Math.abs(r), angle1, -180-angle1);
                }
                
            }
            timePassed++;
        }
        if(Math.abs(t + tStep) > 1){
            tStep = -tStep;
        }
            t += tStep;

    }   

    public static void main(String[] args) {
        
        ArcDeformation ad = new ArcDeformation(null, new euclideanR2(5, 10, true, true), 100, 25, 10, 4, 250);
        ad.run();
    }
}

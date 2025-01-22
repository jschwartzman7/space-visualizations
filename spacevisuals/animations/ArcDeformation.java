package spacevisuals.animations;

import java.awt.Color;
import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;

public class ArcDeformation  extends BasicAnimation<Euclidean2D>{

    // draw "continuously" deforming semi-circles starting from upper hemisphere ending at lower hemisphere
    // StdDraw.arc(x, y, radius, angle1, angle2);
    // want center of arc (0, h) to move at constant speed
    // t ranges from -1 to 1

    double resoution = 0.008;
    LinkedList<Double> shadow;
    int shadowLength;
    double r0;
    double t;
    double tStep = 0.008;
    
    public ArcDeformation(Euclidean2D space, int frameSpeed, int shadowLength){
        super(space, frameSpeed);
        this.shadowLength = shadowLength;
        this.shadow = new LinkedList<Double>();
        this.t = -1;
        this.r0 = 1;
    }

    public void drawAnimation(){
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
    }   

    public void updateAnimation(){
        if(shadow.size() < shadowLength){
            shadow.addLast(t);
        }
        else{
            shadow.removeFirst();
            shadow.addLast(t);
        }

        if(Math.abs(t + tStep) > 1){
            tStep = -tStep;
        }
            t += tStep;
    }

    public static void main(String[] args) {
        new ArcDeformation(new Euclidean2D(5, 1, true), 25, 10).run();
    }
}

package spacevisuals.animations;

import java.awt.Color;
import java.util.LinkedList;
import spacevisuals.spaces.Euclidean2D;
import edu.princeton.cs.introcs.StdDraw;

public class ArcDeformation extends BasicAnimation<Euclidean2D>{

    // draw "continuously" deforming semi-circles starting from upper hemisphere ending at lower hemisphere
    // StdDraw.arc(x, y, radius, angle1, angle2);
    // want center of arc (0, h) to move at constant speed
    // t ranges from -1 to 1

    //LinkedList<Double> shadow;
    //int shadowLength;
    double r0 = 1;
    TimeInterval T;
    
    public ArcDeformation(Euclidean2D space, int frameRate, int shadowLength){
        super(space, frameRate);
        //this.shadowLength = shadowLength;
        //this.shadow = new LinkedList<Double>();
        this.T = new TimeInterval(-1, 1, 0.008);
    }

    public void drawAnimation(){
        if(Math.abs(T.t) > T.tStep){
            double r = (r0*(1+T.t*T.t))/(2*T.t);
            double y = r-T.t*r0;
            double angle1 = Math.atan2(-y, -r0*Math.signum(T.t))*180/Math.PI;
            if(T.t < 0){
                StdDraw.arc(0, y, Math.abs(r), angle1, 180-angle1);
            }
            else{
                StdDraw.arc(0, y, Math.abs(r), angle1, -180-angle1);
            }
            
        }

        /*int timePassed = shadowLength - shadow.size();
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
        }*/
    }   

    public void updateAnimation(){
        T.updateT();
    }
}

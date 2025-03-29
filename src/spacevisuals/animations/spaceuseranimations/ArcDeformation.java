package spacevisuals.animations.spaceuseranimations;

import spacevisuals.helpers.timeintervals.TimeInterval;
import spacevisuals.helpers.timeintervals.TimeIntervalBounce;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.SpaceUser;
import spacevisuals.animations.SpaceAnimation;
import edu.princeton.cs.introcs.StdDraw;

public class ArcDeformation extends SpaceUser<Euclidean2D> implements SpaceAnimation{

    // draw "continuously" deforming semi-circles starting from upper hemisphere ending at lower hemisphere
    // StdDraw.arc(x, y, radius, angle1, angle2);
    // want center of arc (0, h) to move at constant speed
    // t ranges from -1 to 1

    //LinkedList<Double> shadow;
    int shadowLength  = 10;
    private double circleRadius = 5;
    private TimeInterval T;
    private double penWidth = 0.008;
    
    public ArcDeformation(){
        super();
        //this.shadowLength = shadowLength;
        //this.shadow = new LinkedList<Double>();
        this.T = new TimeIntervalBounce(-1, 1, 0.03);
    }

    @Override
    public void drawAnimation(){
        double shadowT = T.t;
        for(int i = 0; i < shadowLength; i++){
            drawArc(shadowT);
            shadowT = T.incrementValue(shadowT);
        }
    }

    public void drawArc(double t){
        if(Math.abs(t) > T.tStep){
            double rr = (circleRadius/t+circleRadius*t)/2;
            double y = rr-t*circleRadius;
            double angle1 = Math.atan2(-y, -circleRadius*Math.signum(t))*180/Math.PI;
            StdDraw.setPenColor();
            StdDraw.setPenRadius(penWidth);
            if(T.t < 0){
                StdDraw.arc(0, y, Math.abs(rr), angle1, 180-angle1);
            }
            else{
                StdDraw.arc(0, y, Math.abs(rr), angle1, -180-angle1);
            }
            StdDraw.setPenRadius();
            StdDraw.circle(0, y, Math.abs(rr));
            
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

    @Override
    public void configureAnimation(String[] parameters) {
        if(parameters == null){
            return;
        }
        try{
            this.circleRadius = Math.abs(Double.parseDouble(parameters[0]));
        }
        catch(Exception e){
        }
    }
}

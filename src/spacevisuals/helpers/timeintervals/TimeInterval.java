package spacevisuals.helpers.timeintervals;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public abstract class TimeInterval{

    public double t;
    public double tMin;
    public double tMax;
    public double tStep;

    public  TimeInterval(double tMin, double tMax, double tStep){
        this.t = tMin;
        this.tMin = tMin;
        this.tMax = tMax;
        this.tStep = tStep;
    }

    public void updateT(){
        if(!StdDraw.isKeyPressed(KeyEvent.VK_SPACE)){
            concreteUpdateT();
        }
    };

    public abstract void concreteUpdateT();

    public abstract double update(double t);
}

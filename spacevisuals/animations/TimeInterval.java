package spacevisuals.animations;

public class TimeInterval{

    public double t;
    public double tMin;
    public double tMax;
    public double tStep;

    public TimeInterval(double tMin, double tMax, double tStep){
        this.t = tMin;
        this.tMin = tMin;
        this.tMax = tMax;
        this.tStep = tStep;
    }

    public void updateT(){
        double tNext = t + tStep;
        if(tNext > tMax || tNext < tMin){
            tStep = -tStep;
        }
        t += tStep;
    }
}

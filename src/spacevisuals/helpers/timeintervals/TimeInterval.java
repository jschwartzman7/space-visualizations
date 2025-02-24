package spacevisuals.helpers.timeintervals;

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

    public abstract void updateT();
}

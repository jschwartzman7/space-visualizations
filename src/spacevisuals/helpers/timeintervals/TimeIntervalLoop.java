package spacevisuals.helpers.timeintervals;

public class TimeIntervalLoop extends TimeInterval{
    
    public TimeIntervalLoop(double tMin, double tMax, double tStep){
        super(tMin, tMax, tStep);
    }

    @Override
    public void concreteUpdateT() {
        double tNext = t + tStep;
        if(tNext > tMax){
            t = tMin;
        }
        else{
            t += tStep;
        }
    }

    @Override
    public double update(double t) {
        double tNext = t + tStep;
        if(tNext > tMax){
            t = tMin;
        }
        else{
            t += tStep;
        }
        return t;
    }
    
}

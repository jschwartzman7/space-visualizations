package spacevisuals.helpers.timeintervals;

public class TimeIntervalBounce extends TimeInterval{
    
    public TimeIntervalBounce(double tMin, double tMax, double tStep){
        super(tMin, tMax, tStep);
    }

    @Override
    public void concreteUpdateT() {
        double tNext = t + tStep;
        if(tNext > tMax || tNext < tMin){
            tStep = -tStep;
        }
        t += tStep;
    }

    @Override
    public double update(double t) {
        double tNext = t + tStep;
        if(tNext > tMax || tNext < tMin){
            tStep = -tStep;
        }
        t += tStep;
        return t;
    }
    
}

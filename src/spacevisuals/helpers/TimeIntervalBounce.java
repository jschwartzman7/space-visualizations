package spacevisuals.helpers;

public class TimeIntervalBounce extends TimeInterval{
    
    public TimeIntervalBounce(double tMin, double tMax, double tStep){
        super(tMin, tMax, tStep);
    }

    @Override
    public void updateT() {
        double tNext = t + tStep;
        if(tNext > tMax || tNext < tMin){
            tStep = -tStep;
        }
        t += tStep;
    }
    
}

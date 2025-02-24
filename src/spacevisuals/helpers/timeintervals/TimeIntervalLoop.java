package spacevisuals.helpers.timeintervals;

public class TimeIntervalLoop extends TimeInterval{
    
    public TimeIntervalLoop(double tMin, double tMax, double tStep){
        super(tMin, tMax, tStep);
    }

    @Override
    public void updateT() {
        double tNext = t + tStep;
        if(tNext > tMax){
            t = tMin;
        }
        else{
            t += tStep;
        }
    }
    
}

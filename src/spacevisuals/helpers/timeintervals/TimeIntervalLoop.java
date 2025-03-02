package spacevisuals.helpers.timeintervals;

public class TimeIntervalLoop extends TimeInterval{
    
    public TimeIntervalLoop(double tMin, double tMax, double tStep){
        super(tMin, tMax, tStep);
    }

    @Override
    public void concreteUpdateT() {
        double tNext = this.t + this.tStep;
        if(tNext > this.tMax){
            this.t = this.tMin;
        }
        else{
            this.t += this.tStep;
        }
    }

    @Override
    public double update(double t) {
        return t + this.tStep;
    }
}

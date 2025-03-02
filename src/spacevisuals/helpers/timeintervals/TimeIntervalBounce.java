package spacevisuals.helpers.timeintervals;

public class TimeIntervalBounce extends TimeInterval{
    
    public TimeIntervalBounce(double tMin, double tMax, double tStep){
        super(tMin, tMax, tStep);
    }

    @Override
    public void concreteUpdateT() {
        double tNext = this.t + this.tStep;
        if(tNext > this.tMax || tNext < this.tMin){
            this.tStep = -this.tStep;
        }
        this.t += this.tStep;
    }

    @Override
    public double update(double t) {
        if(t < tMin || t > tMax){
            return t + this.tStep;
        }
        double tNext = t + this.tStep;
        if(tNext > this.tMax || tNext < this.tMin){
            return t - this.tStep;
        }
        return t + this.tStep;
    }
    
}

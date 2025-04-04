package spacevisuals.utils.timeintervals;

public class TimeIntervalBounce extends TimeInterval{
    
    public TimeIntervalBounce(double tMin, double tMax, double tStep){
        super(tMin, tMax, tStep);
    }

    @Override
    public void updateT() {
        double tNext = this.t + this.tStep;
        if(tNext > this.tMax || tNext < this.tMin){
            this.tStep = -this.tStep;
        }
        this.t += this.tStep;
    }

    @Override
    public double incrementValue(double t) {
        if(t < tMin || t > tMax){
            return t;
        }
        double tNext = t + this.tStep;
        if(tNext > this.tMax || tNext < this.tMin){
            return t - this.tStep;
        }
        return t + this.tStep;
    }
    
}

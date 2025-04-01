package spacevisuals.utils.timeintervals;

public class TimeIntervalLoop extends TimeInterval{
    
    public TimeIntervalLoop(double tMin, double tMax, double tStep){
        super(tMin, tMax, tStep);
    }

    @Override
    public void updateT() {
        double tNext = this.t + this.tStep;
        if(tNext > this.tMax){
            this.t = this.tMin;
        }
        else{
            this.t += this.tStep;
        }
    }
}

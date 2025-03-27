package spacevisuals.helpers.timeintervals;

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
        double tNext = this.t + this.tStep;
        if(tNext < this.tMax){
            this.t = tNext;
        }
    };

    public double incrementValue(double t){
        return t + this.tStep;
    };
}

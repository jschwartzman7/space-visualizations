package spacevisuals.animations.pointsetanimations;

import java.awt.Color;
import java.util.LinkedList;
import java.util.function.Consumer;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.SpaceAnimation;
import spacevisuals.enums.FunctionsEnum;
import spacevisuals.functionhandling.SpaceFunction3D;
import spacevisuals.helpers.timeintervals.TimeInterval;
import spacevisuals.helpers.timeintervals.TimeIntervalLoop;

public class LorenzAttractor extends SpaceFunction3D implements SpaceAnimation{

    private double pointRadius = 0.01;
    private int shadowLength = 50;
    private double[] point;
    private double[][] pointShadow;
    private TimeInterval timeInterval;
    private double sigma;
    private double rho;
    private double beta;

    public LorenzAttractor(){
        super();
        this.timeInterval = new TimeIntervalLoop(0, 100, 0.05);
        this.pointShadow = new double[shadowLength][3];
        this.sigma = 10;
        this.rho = 28;
        this.beta = 8/3;
        for(int i = 0; i < pointShadow.length; i++){
            pointShadow[i] = new double[]{1, 0, 0};
        }
    }

    private double dx(double[] point){
        return sigma*(point[1] - point[1]);
    }
    private double dy(double[] point){
        return point[0]*(rho - point[2]) - point[1];
    }
    private double dz(double[] point){
        return point[0]*point[1] - beta*point[2];
    }

    @Override
    public void drawAnimation() {
        for(int i = pointShadow.length-1; i > 0; i--){
            pointShadow[i] = pointShadow[i - 1];
        }
        point = pointShadow[0];
        double[] dp = new double[]{dx(point), dy(point), dz(point)};
        double[] newPoint = new double[]{point[0] + dp[0]*timeInterval.tStep, point[1] + dp[1]*timeInterval.tStep, point[2] + dp[2]*timeInterval.tStep};
        pointShadow[0] = newPoint;
        StdDraw.setPenRadius(pointRadius);
        for(int i = 0; i < pointShadow.length; i++){
            double[] point2D = space.toViewScreenPoint(pointShadow[i]);
            StdDraw.setPenColor(Color.black);
            StdDraw.point(point2D[0], point2D[1]);
        }
    }
    @Override
    public void updateAnimation(){
        timeInterval.updateT();
    }
    @Override
    public void buildAnimation(String[] parameters) {
        this.function = FunctionsEnum.from(parameters[0]).function;
    }
    
}

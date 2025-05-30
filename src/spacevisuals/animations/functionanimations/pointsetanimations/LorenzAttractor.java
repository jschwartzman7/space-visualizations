package spacevisuals.animations.functionanimations.pointsetanimations;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.colors.colorstrategies.PointMapColorStrategy;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.utils.Constants;

public class LorenzAttractor extends PointSetAnimation{

    private ArrayList<Color> pointColors;
    private double tStep;
    private double sigma;
    private double rho;
    private double beta;

    public LorenzAttractor(){
        super();
        this.pointColors = new ArrayList<Color>();
        this.colorHelper = new PointMapColorStrategy();
        this.tStep = 0.02;
        this.sigma = 10;
        this.rho = 28;
        this.beta = 8/3;
        resetPoints();
    }

    private void resetPoints(){
        points.clear();
        pointColors.clear();
        this.points.add(new double[]{1, 0, 0});
        this.pointColors.add(colorHelper.getColor(null));
    }

    private double dx(double[] point){
        return sigma*(point[1] - point[0]);
    }
    private double dy(double[] point){
        return point[0]*(rho - point[2]) - point[1];
    }
    private double dz(double[] point){
        return point[0]*point[1] - beta*point[2];
    }

    @Override
    public void handlePoint(double[] point) {
        double[] point2D = Euclidean3D.Get().toViewScreenPoint(point);
        if(point2D == null || Double.isNaN(point2D[0]) || Double.isNaN(point2D[1])){
            return;
        }
        StdDraw.filledCircle(point2D[0], point2D[1], Constants.POINT_WIDTH);
    }
    @Override
    public void updateAnimation(){
        double[] dp = new double[]{dx(points.get(0)), dy(points.get(0)), dz(points.get(0))};
        double[] newPoint = new double[]{points.get(0)[0] + dp[0]*tStep, points.get(0)[1] + dp[1]*tStep, points.get(0)[2] + dp[2]*tStep};
        points.add(0, newPoint);
        pointColors.add(0, colorHelper.getColor(newPoint));
        if(StdDraw.isKeyPressed(KeyEvent.VK_R)){
            resetPoints();
        }
    }
    @Override
    public void configureAnimation(String[] parameters) {
        if(parameters.length != 3){
            return;
        }
        this.sigma = Double.parseDouble(parameters[0]);
        this.rho = Double.parseDouble(parameters[1]);
        this.beta = Double.parseDouble(parameters[2]);
    }

    @Override
    public void handleInputOutput(double[] input, double[] output) {
        return;
    }
    
}

package spacevisuals.utils;

import spacevisuals.functions.Rn_R;
import spacevisuals.functions.Rn_Rn;
import spacevisuals.spaces.SpaceUser3D;

public class PointFinderPlane3D implements PointFinder, SpaceUser3D{
    
    private double tolerance = 0.1;

    @Override
    public double[] findPoint(double[] point2d){
        double x = 0;
        double y = 0;
        double distance = Rn_R.magnitude(Rn_Rn.pairwiseSubtract(space().toViewScreenPoint(new double[]{x, y, 0}), point2d));
        while(distance > tolerance){
            for(double[] dp : new double[][]{{Constants.DISTANCE_STEP, 0},{-Constants.DISTANCE_STEP, 0},{0, Constants.DISTANCE_STEP},{0, -Constants.DISTANCE_STEP}}){
                double[] tempXY = Rn_Rn.pairwiseAdd(new double[]{x,y}, dp);
                double tempDistance = Rn_R.magnitude(Rn_Rn.pairwiseSubtract(space().toViewScreenPoint(tempXY), point2d));
                if(tempDistance < distance){
                    x = tempXY[0];
                    y = tempXY[1];
                    distance = tempDistance;
                }
                if(distance < tolerance){
                    x = tempXY[0];
                    y = tempXY[1];
                    break;
                }
            }
        }
        return new double[]{x, y};
    }
}

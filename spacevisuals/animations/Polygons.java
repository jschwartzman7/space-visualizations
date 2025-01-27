package spacevisuals.animations;

import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.functions.*;;

public class Polygons extends BasicAnimation<Euclidean3D>{

    private List<double[][]> linePointPairs;

    public Polygons(Euclidean3D space, int frameRate){
        super(space, frameRate);
        this.linePointPairs = new LinkedList<double[][]>();
        addCube(3, new double[]{1, 0, 0});
    }

    private void addCube(double radius, double[] center){
        double[][] cube = new double[8][3];
        cube[0] = new double[]{center[0]-radius, center[1]-radius, center[2]-radius};
        cube[1] = new double[]{center[0]+radius, center[1]-radius, center[2]-radius};
        cube[2] = new double[]{center[0]+radius, center[1]+radius, center[2]-radius};
        cube[3] = new double[]{center[0]-radius, center[1]+radius, center[2]-radius};
        cube[4] = new double[]{center[0]-radius, center[1]-radius, center[2]+radius};
        cube[5] = new double[]{center[0]+radius, center[1]-radius, center[2]+radius};
        cube[6] = new double[]{center[0]+radius, center[1]+radius, center[2]+radius};
        cube[7] = new double[]{center[0]-radius, center[1]+radius, center[2]+radius};
        
        linePointPairs.add(new double[][]{cube[0], cube[1]});
        linePointPairs.add(new double[][]{cube[0], cube[3]});
        linePointPairs.add(new double[][]{cube[0], cube[4]});


        linePointPairs.add(new double[][]{cube[2], cube[1]});
        linePointPairs.add(new double[][]{cube[2], cube[6]});
        linePointPairs.add(new double[][]{cube[2], cube[3]});

        linePointPairs.add(new double[][]{cube[5], cube[1]});
        linePointPairs.add(new double[][]{cube[5], cube[6]});
        linePointPairs.add(new double[][]{cube[5], cube[4]});

        linePointPairs.add(new double[][]{cube[7], cube[6]});
        linePointPairs.add(new double[][]{cube[7], cube[4]});
        linePointPairs.add(new double[][]{cube[7], cube[3]});




    }

    public void drawAnimation(){
        for(double[][] linePointPair: linePointPairs){
            double[] point1 = Matrix.matrixVectorMultiplication(space.currentPosition, linePointPair[0]);
            double[] point2 = Matrix.matrixVectorMultiplication(space.currentPosition, linePointPair[1]);
            double[] point2D1 = space.to2D(point1);
            double[] point2D2 = space.to2D(point2);
            StdDraw.setPenColor();
            StdDraw.line(point2D1[0], point2D1[1], point2D2[0], point2D2[1]);
        }
    }
}

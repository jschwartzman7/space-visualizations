package spacevisuals.animations;

import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.functions.*;;

public class Polygons extends BasicAnimation<AbstractSpace>{

    private List<double[][]> linePointPairs;

    public Polygons(AbstractSpace space, int frameRate){
        super(space, frameRate);
        this.linePointPairs = new LinkedList<double[][]>();
        addCube(3, new double[]{2, 0, 0});
        addTetrahedron(new double[]{0, 0, 2}, 1);
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
    private void addTetrahedron(double[] center, double radius){
        double[][] tetrahedron = new double[8][3];
        tetrahedron[0] = new double[]{center[0], center[1], center[2]};
        tetrahedron[1] = new double[]{center[0]+radius, center[1], center[2]};
        tetrahedron[2] = new double[]{center[0], center[1]+radius, center[2]};
        tetrahedron[3] = new double[]{center[0], center[1], center[2]+radius};
        
        linePointPairs.add(new double[][]{tetrahedron[0], tetrahedron[1]});
        linePointPairs.add(new double[][]{tetrahedron[0], tetrahedron[2]});
        linePointPairs.add(new double[][]{tetrahedron[0], tetrahedron[3]});

        linePointPairs.add(new double[][]{tetrahedron[1], tetrahedron[2]});
        linePointPairs.add(new double[][]{tetrahedron[2], tetrahedron[3]});
        linePointPairs.add(new double[][]{tetrahedron[3], tetrahedron[1]});

    }

    public void drawAnimation(){
        for(double[][] linePointPair: linePointPairs){
            double[] point2D1 = space.toDrawablePoint(linePointPair[0]);
            double[] point2D2 = space.toDrawablePoint(linePointPair[1]);
            StdDraw.setPenColor();
            StdDraw.line(point2D1[0], point2D1[1], point2D2[0], point2D2[1]);
        }
    }
}

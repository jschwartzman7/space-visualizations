package spacevisuals.animations.polygonanimations;

import java.util.LinkedList;
import java.util.List;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.SpaceAnimation;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.SpaceUser;

public class Polygons3D extends Polygons<Euclidean3D>{

    private List<double[][]> linePointPairs;
    
    public Polygons3D(){
        setSpace(Euclidean3D.Get());
        addCube(new double[]{2, 0, 0}, 3);
        addSimplex(new double[]{1, 1, 2}, 2);
    }

    /*private void addCube(double radius, double[] center){
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

    }*/

}

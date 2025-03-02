package spacevisuals.animations.polygonanimations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.SpaceAnimation;
import spacevisuals.spaces.Euclidean4D;
import spacevisuals.spaces.SpaceUser;

public class Polygons4D extends Polygons<Euclidean4D>{


    public Polygons4D(){
        setSpace(Euclidean4D.Get());
        addCube(new double[]{0, 0, 0, 0}, 2);
        addSimplex(new double[]{1, 1, 1, 1}, 2);
    }

    private List<double[][]> linePointPairs = new ArrayList<double[][]>();

    /*public static double[][] unitHyperCube = new double[][]{
        {0, 0, 0, 0},
        {1, 0, 0, 0},
        {0, 1, 0, 0},
        {0, 0, 1, 0},
        {0, 0, 0, 1},
        {1, 1, 0, 0},
        {1, 0, 1, 0},
        {1, 0, 0, 1},
        {0, 1, 1, 0},
        {0, 1, 0, 1},
        {0, 0, 1, 1},
        {1, 1, 1, 0},
        {1, 1, 0, 1},
        {1, 0, 1, 1},
        {0, 1, 1, 1},
        {1, 1, 1, 1}
    };

    public static double[][] simplex4D = new double[][]{
        {0, 0, 0, 0},
        {1, 0, 0, 0},
        {0, 1, 0, 0},
        {0, 0, 1, 0},
        {0, 0, 0, 1},
    };

    private HashSet<double[]> adjacentVertices(double[] vertex){
        HashSet<double[]> adjacentVertices = new HashSet<double[]>();
        for(double[] checkVertex : unitHyperCube){
            int distance = 0;
            for(int i = 0; i < vertex.length; i++){
                distance += Math.abs(vertex[i] - checkVertex[i]);
            }
            if(distance == 1){
                adjacentVertices.add(checkVertex);
            }
        }
        return adjacentVertices;
    }

    public void addCube(){
        for(int i = 0; i < unitHyperCube.length; i++){
            double[] point1 = unitHyperCube[i];
            for(double[] adjacentVertex : adjacentVertices(point1)){
                linePointPairs.add(new double[][]{point1, adjacentVertex});
            }
        }
    }

    public void addSimplex(){
        for(int i = 0; i < simplex4D.length; i++){
            double[] point1 = simplex4D[i];
            for(int j = 0; j < simplex4D.length; j++){
                linePointPairs.add(new double[][]{point1, simplex4D[j]});
            }
        }
    }


    @Override
    public void drawAnimation() {
        for(double[][] linePointPair: linePointPairs){
            double[] point2D1 = space.toViewScreenPoint(linePointPair[0]);
            double[] point2D2 = space.toViewScreenPoint(linePointPair[1]);
            StdDraw.setPenColor();
            StdDraw.line(point2D1[0], point2D1[1], point2D2[0], point2D2[1]);
        }
    }

    @Override
    public void buildAnimation(String[] parameters) {
        addSimplex();
    }*/
    
}

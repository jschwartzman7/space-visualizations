package spacevisuals.animations.polygons;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.SpaceAnimation;
import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.SpaceUser;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public abstract class Polygons extends SpaceUser<AbstractSpace> implements SpaceAnimation{

    private List<double[][]> lineSegments;

    public Polygons(AbstractSpace space){
        super(space);
        this.lineSegments = new LinkedList<double[][]>();
    }
    
    private static double[][] cube(double[] center, double halfRadius){
        int dimensions = center.length;
        double[][] cube = new double[(int)Math.pow(2, dimensions)][dimensions];
        for(int i = 0; i < cube.length; i++){
            int v = i;
            for(int j = 0; j < dimensions; j++){
                cube[i][j] = center[j] + halfRadius * (v % 2 == 0 ? -1 : 1);
                v /= 2;
            }
        }
        return cube;
    }

    private HashSet<double[]> adjacentVertices(double[][] cube, double[] vertex){
        HashSet<double[]> adjacentVertices = new HashSet<double[]>();
        for(double[] checkVertex : cube){
            int distance = 0;
            for(int i = 0; i < vertex.length; i++){
                distance += Math.signum(Math.abs(vertex[i] - checkVertex[i]));
            }
            if(distance == 1){
                adjacentVertices.add(checkVertex);
            }
        }
        return adjacentVertices;
    }


    public void addCube(double[] center, double halfRadius){
        double[][] cube = cube(center, halfRadius);
        for(int i = 0; i < cube.length; i++){
            double[] point1 = cube[i];
            for(double[] adjacentVertex : adjacentVertices(cube, point1)){
                lineSegments.add(new double[][]{point1, adjacentVertex});
            }
        }
    }

    private static double[][] simplex(double[] base, double width){
        double[][] simplex = new double[base.length+1][base.length];
        for(int i = 0; i < simplex.length; i++){
            simplex[i] = base.clone();
            if(i > 0){
                simplex[i][i-1] += width;
            }
        }
        return simplex;
    }

    public void addSimplex(double[] base, double width){
        double[][] simplex = simplex(base, width);
        for(int i = 0; i < simplex.length; i++){
            double[] point1 = simplex[i];
            for(int j = 0; j < simplex.length; j++){
                lineSegments.add(new double[][]{point1, simplex[j]});
            }
        }
    }

    @Override
    public void drawAnimation() {
        for(double[][] linePointPair: lineSegments){
            double[] point2D1 = space.toViewScreenPoint(linePointPair[0]);
            double[] point2D2 = space.toViewScreenPoint(linePointPair[1]);
            StdDraw.setPenColor(space.colorScheme.labelColor);
            StdDraw.setPenRadius();
            if(point2D1 == null & point2D2 == null){
                continue;
            }
            else if(point2D1 == null){
                StdDraw.line(Double.MAX_VALUE, Double.MAX_VALUE, point2D2[0], point2D2[1]);
                continue;
            }
            else if(point2D2 == null){
                StdDraw.line(point2D1[0], point2D1[1], Double.MAX_VALUE, Double.MAX_VALUE);
                continue;
            }
            StdDraw.line(point2D1[0], point2D1[1], point2D2[0], point2D2[1]);
        }
    }
}

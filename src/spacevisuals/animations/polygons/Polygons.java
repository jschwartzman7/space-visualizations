package spacevisuals.animations.polygons;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.SpaceAnimation;
import spacevisuals.animations.polygons.solids.Line;
import spacevisuals.animations.polygons.solids.Simplex;
import spacevisuals.animations.polygons.solids.Triangle;
import spacevisuals.spaces.AbstractSpace;
import spacevisuals.SpaceUser;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public abstract class Polygons<T extends AbstractSpace> extends SpaceUser<T> implements SpaceAnimation{

    private List<Triangle> triangles;
    private List<Line> lines;

    public Polygons(T space){
        super(space);
        this.triangles = new LinkedList<Triangle>();
        this.lines = new LinkedList<Line>();
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
            HashSet<double[]> adjacentVertices = adjacentVertices(cube, point1);
            for(int j = 0; j < adjacentVertices.size(); j++){
                triangles.add(new Triangle(new double[][]{point1, (double[])adjacentVertices.toArray()[j], (double[])adjacentVertices.toArray()[(j+1)%adjacentVertices.size()]}));
                lines.add(new Line(new double[][]{point1, (double[])adjacentVertices.toArray()[j]}));
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
            double[] point2 = simplex[(i+1)%simplex.length];
            double[] point3 = simplex[(i+2)%simplex.length];
            triangles.add(new Triangle(new double[][]{point1, point2, point3}));
            lines.add(new Line(new double[][]{point1, point2}));
        }
    }

    @Override
    public void drawAnimation() {
        for(Simplex triangle: triangles){
            StdDraw.setPenColor(getSpace().colorScheme.labelColor);
            StdDraw.setPenRadius();
            triangle.draw(getSpace());
        }
        for(Simplex line : lines){
            StdDraw.setPenColor(getSpace().colorScheme.backgroundColor);
            StdDraw.setPenRadius();
            line.draw(getSpace());
        }
    }
}

package spacevisuals.animations.polygons;

import spacevisuals.SpaceAnimation;
import spacevisuals.animations.polygons.solids.Line;
import spacevisuals.animations.polygons.solids.Simplex;
import spacevisuals.animations.polygons.solids.Triangle;
import spacevisuals.colors.Shader;
import spacevisuals.colors.colorstrategies.ColorStrategy;
import spacevisuals.colors.colorstrategies.SingleColorStrategy;
import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.SpaceUser;

import java.awt.Color;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.introcs.StdDraw;

public abstract class Polygons implements SpaceUser<AbstractSpace>, SpaceAnimation{

    private List<Triangle> triangles;
    private List<Line> lines;
    public Shader colorHelper;

    public Polygons(){
        this.triangles = new LinkedList<Triangle>();
        this.lines = new LinkedList<Line>();
        this.colorHelper = new Shader(new SingleColorStrategy(Color.BLUE));
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

    public void drawAnimation() {
        for(Triangle triangle: triangles){
            StdDraw.setPenColor(colorHelper.getColor(triangle));
            drawTriangle(triangle);
        }
        for(Simplex line : lines){
            drawLine(line);
        }
    }

    public void drawLine(Simplex simplex){
        StdDraw.setPenColor(space().colorScheme.backgroundColor);
        StdDraw.setPenRadius();
        simplex.draw(space());
    };

    public void drawTriangle(Simplex simplex){
        StdDraw.setPenRadius();
        simplex.draw(space());
    };
}

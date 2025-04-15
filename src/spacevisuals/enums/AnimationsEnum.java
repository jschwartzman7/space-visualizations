package spacevisuals.enums;

import spacevisuals.animations.basicanimations.ArcDeformation;
import spacevisuals.animations.basicanimations.Basic2D;
import spacevisuals.animations.basicanimations.Basic3D;
import spacevisuals.animations.basicanimations.Basic4D;
import spacevisuals.animations.basicanimations.Sphere4D;
import spacevisuals.animations.polygons.Cube4D;
import spacevisuals.animations.polygons.Polygons3D;
import spacevisuals.animations.polygons.Simplex4D;
import spacevisuals.animations.spacefunctions.Gradient;
import spacevisuals.animations.spacefunctions.pointsetanimations.LorenzAttractor;
import spacevisuals.animations.spacefunctions.pointsetanimations.ParametricCurve;
import spacevisuals.animations.spacefunctions.pointsetanimations.PointMap2D;
import spacevisuals.animations.spacefunctions.pointsetanimations.SphereMagnet;
import spacevisuals.animations.spacefunctions.spacetraverseranimations.domaincoloring.DomainColor;
import spacevisuals.animations.spacefunctions.spacetraverseranimations.domaincoloring.JuliaSet;
import spacevisuals.animations.spacefunctions.spacetraverseranimations.domaincoloring.MandelbrotSet;
import spacevisuals.animations.spacefunctions.spacetraverseranimations.functiongraphs.Graph2D;
import spacevisuals.animations.spacefunctions.spacetraverseranimations.functiongraphs.Graph2DLine;
import spacevisuals.animations.spacefunctions.spacetraverseranimations.functiongraphs.Graph3D;
import spacevisuals.animations.spacefunctions.spacetraverseranimations.functiongraphs.Graph3DTriangle;
import spacevisuals.animations.spacefunctions.spacetraverseranimations.vectorfields.VectorField2D;
import spacevisuals.animations.spacefunctions.spacetraverseranimations.vectorfields.VectorField3D;
import spacevisuals.utils.ConfigurableAnimation;

import java.util.function.Supplier;

public enum AnimationsEnum {
    // 2d
    basic2d(2, Basic2D::new),
    graph2d(2, Graph2D::new),
    graph2dline(2, Graph2DLine::new),
    vectorfield2d(2, VectorField2D::new),
    domaincolor(2, DomainColor::new),
    pointmap2d(2, PointMap2D::new),
    juliaset(2, JuliaSet::new),
    mandelbrot(2, MandelbrotSet::new),
    gradient(2, Gradient::new),
    parametriccurve(2, ParametricCurve::new),
    arcdeformation(2, ArcDeformation::new),
    // 3d
    basic3d(3, Basic3D::new),
    vectorfield3d(3, VectorField3D::new),
    graph3d(3, Graph3D::new),
    graph3dtriangle(3, Graph3DTriangle::new),
    polygons3d(3, Polygons3D::new),
    spheremagnet(3, SphereMagnet::new),
    lorenzattractor(3, LorenzAttractor::new),
    // 4d
    basic4d(4, Basic4D::new),
    cube4d(4, Cube4D::new),
    simplex4d(4, Simplex4D::new),
    sphere4d(4, Sphere4D::new);
    
    public final int dimensions;  
    public final Supplier<ConfigurableAnimation> animationConstructor ;

    AnimationsEnum(int dimensions, Supplier<ConfigurableAnimation> constructor) {
        this.dimensions = dimensions;
        this.animationConstructor = constructor;
    }
}

package spacevisuals.animations;

import spacevisuals.animations.pointsetanimations.*;
import spacevisuals.animations.fractalanimations.*;
import spacevisuals.animations.vectorfieldanimations.*;
import spacevisuals.animations.otheranimations.*;

public enum AnimationsEnum {
    // 2d
    basic2d(2, new Basic2D()),
    vectorfield2d(2, new VectorField2D()),
    domaincolor(2, new DomainColor()),
    pointmap2d(2, new PointMap2D()),
    juliaset(2, new JuliaSet()),
    mandelbrot(2, new MandelbrotSet()),
    gradient(2, new Gradient()),
    //parametriccurve(2, new ParametricCurve()),
    // 3d
    basic3d(3, new Basic3D()),
    vectorfield3d(3, new VectorField3D()),
    graph3d(3, new Graph3D()),
    polygons(3, new Polygons()),
    //spheremagnet(3, new SphereMagnet()),
    //lorenzattractor(3, new LorenzAttractor()),
    // 4d
    basic4d(4, new Basic4D());
    
    public final int dimensions;  
    public final SpaceAnimation animation;

    AnimationsEnum(int dimensions, SpaceAnimation animation) {
        this.dimensions = dimensions;
        this.animation = animation;
    }
}

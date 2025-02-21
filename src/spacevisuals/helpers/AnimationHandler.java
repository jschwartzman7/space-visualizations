package spacevisuals.helpers;

import spacevisuals.animations.pointsetanimations.*;
import spacevisuals.animations.fractalanimations.*;
import spacevisuals.animations.vectorfieldanimations.*;
import spacevisuals.animations.otheranimations.*;


import java.util.EnumMap;
import java.util.HashMap;

import spacevisuals.SpaceFunction2D;
import spacevisuals.SpaceFunction3D;
import spacevisuals.SpaceAnimation;

public enum AnimationHandler {
    // 2d
    basic2d("2d", new Basic2D()),
    vectorfield2d("2d", new VectorField2D()),
    domaincolor("2d", new DomainColor()),
    pointmap2d("2d", new PointMap2D()),
    juliaset("2d", new JuliaSet()),
    mandelbrot("2d", new MandelbrotSet()),
    gradient("2d", new Gradient()),
    parametriccurve("2d", new ParametricCurve()),
    // 3d
    basic3d("3d", new Basic3D()),
    vectorfield3d("3d", new VectorField3D()),
    graph3d("3d", new Graph3D()),
    polygons("3d", new Polygons()),
    spheremagnet("3d", new SphereMagnet()),
    lorenzattractor("3d", new LorenzAttractor());
    
    private String dimensions;  
    private SpaceAnimation animation;

    AnimationHandler(String dimensions, SpaceAnimation animation) {
        this.dimensions = dimensions;
        this.animation = animation;
    }

    public String getDimensions() {
        return dimensions;
    }
    public SpaceAnimation getAnimation() {
        return animation;
    }


    
    
}

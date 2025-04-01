package spacevisuals.animations.polygons.solids;

import spacevisuals.spaces.AbstractSpace;

public class Simplex{

    public double[][] shape;

    public Simplex(){
    }
    public Simplex(double[][] vertices){
        this.shape = vertices;
    }

    public void draw(AbstractSpace space){}
    
}

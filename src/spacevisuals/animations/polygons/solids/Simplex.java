package spacevisuals.animations.polygons.solids;

import spacevisuals.spaces.AbstractSpace;
import spacevisuals.SpaceUser;

public class Simplex{

    public double[][] shape;

    public Simplex(){
        super();
    }
    public Simplex(double[][] vertices){
        super();
        this.shape = vertices;
    }

    public void draw(AbstractSpace space){}
    
}

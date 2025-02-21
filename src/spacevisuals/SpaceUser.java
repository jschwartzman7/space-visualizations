package spacevisuals;

import spacevisuals.spaces.AbstractSpace;

public class SpaceUser<T extends AbstractSpace> {

    public T space;

    public SpaceUser(){;
    }
    public SpaceUser(T space){
        this.space = space;
    }

    protected T getSpace(){
        return this.space;
    }   
    protected void setSpace(T space){
        this.space = space;
    }
}

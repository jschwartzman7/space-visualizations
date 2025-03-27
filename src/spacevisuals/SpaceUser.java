package spacevisuals;

import spacevisuals.spaces.AbstractSpace;

public class SpaceUser<T extends AbstractSpace> {

    private T space;

    public SpaceUser(){;
    }
    public SpaceUser(T space){
        this.space = space;
    }

    public T getSpace(){
        return this.space;
    }   
    public void setSpace(T space){
        this.space = space;
    }
}

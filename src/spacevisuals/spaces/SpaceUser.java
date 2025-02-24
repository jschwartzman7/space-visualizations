package spacevisuals.spaces;

public class SpaceUser<T extends AbstractSpace> {

    protected T space;

    public SpaceUser(){;
    }
    public SpaceUser(T space){
        this.space = space;
    }

    public T getSpace(){
        return this.space;
    }   
    protected void setSpace(T space){
        this.space = space;
    }
}

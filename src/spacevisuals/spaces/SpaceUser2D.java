package spacevisuals.spaces;

public interface SpaceUser2D extends SpaceUser<Euclidean2D>{
    
    @Override
    public default Euclidean2D space(){
        return Euclidean2D.Get();
    }
}

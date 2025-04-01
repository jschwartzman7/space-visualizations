package spacevisuals.spaces;

public interface SpaceUser4D extends SpaceUser<Euclidean4D>{
    
    @Override
    public default Euclidean4D space(){
        return Euclidean4D.Get();
    }
}

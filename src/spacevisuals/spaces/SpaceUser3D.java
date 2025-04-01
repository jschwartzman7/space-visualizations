package spacevisuals.spaces;

public interface SpaceUser3D extends SpaceUser<Euclidean3D>{

    @Override
    public default Euclidean3D space(){
        return Euclidean3D.Get();
    }
}

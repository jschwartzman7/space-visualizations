public class R3Basic extends abstractAnimation {

    public R3Basic(Euclidean3D space, int frameSpeed){
        super(space, frameSpeed);
    }

    public void update(){}

    public void draw(){}

    public static void main(String[] args) {
        new R3Basic(new Euclidean3D(5, 5, true), 25).run();
    }
    
}

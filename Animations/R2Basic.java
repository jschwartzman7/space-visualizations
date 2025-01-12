public class R2Basic extends abstractAnimation {

    public R2Basic(Euclidean2D space, int frameSpeed){
        super(space, frameSpeed);
    }

    public void update(){}

    public void draw(){}

    public static void main(String[] args) {
        new R2Basic(new Euclidean2D(10, 1, true), 25).run();
    }
    
}

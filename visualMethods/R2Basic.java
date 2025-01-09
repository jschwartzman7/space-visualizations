public class R2Basic extends abstractAnimation {


    public R2Basic(euclideanR2 space, int frameSpeed){
        super(space, frameSpeed);
    }

    public void update(){

    }

    public void draw(){

    }

    public static void main(String[] args) {
        new R2Basic(new euclideanR2(5, 1, true), 25).run();
    }
    
}

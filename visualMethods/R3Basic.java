public class R3Basic extends abstractAnimation {


    public R3Basic(euclideanR3 space, int frameSpeed){
        super(space, frameSpeed);
    }

    public void update(){

    }
    public void draw(){

    }

    public static void main(String[] args) {
        new R3Basic(new euclideanR3(5, 5, true), 25).run();
    }
    
}

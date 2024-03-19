import edu.princeton.cs.introcs.StdDraw;

class VectorFields3DSpace {
    
    public abstractFunction function = new functionR3_R3();
    public abstractSpaceVisuals space = new euclideanR3(5, false);


    public void run(){
        while(true){
            space.draw();
            space.update();
            StdDraw.show(50);

        }
    }


    public static void main(String[] args) {
        //System.out.println("HERE: "+args[0]);
       new VectorFields3DSpace().run();
        // <x+3, yxz, 6z-y>
        //run(inputs)
        
    }
}

import edu.princeton.cs.introcs.StdDraw;
import java.awt.event.KeyEvent;

public class euclideanR2 implements abstractSpaceVisuals{

    
    public int DEFAULT_XY_SCALE;
    public static double X_MIN;
    public static double X_MAX;
    public static double Y_MIN;
    public static double Y_MAX;
    private int labelInterval;
    private boolean viewLabels;

    public euclideanR2(int defaultScale, int labelInterval, boolean viewLabels){
        DEFAULT_XY_SCALE = defaultScale;
        X_MIN = -defaultScale;
        X_MAX = defaultScale;
        Y_MIN = -defaultScale;
        Y_MAX = defaultScale;
        this.labelInterval = labelInterval;
        this.viewLabels = viewLabels;
    }

    public euclideanR2(){
        int defaultScale = 5;
        DEFAULT_XY_SCALE = defaultScale;
        X_MIN = -defaultScale;
        X_MAX = defaultScale;
        Y_MIN = -defaultScale;
        Y_MAX = defaultScale;
        this.labelInterval = 0;
        this.viewLabels = false;
    }


    public void resetDraw(){
        StdDraw.setXscale(X_MIN, X_MAX);
        StdDraw.setYscale(Y_MIN, Y_MAX);
    };



    public void draw(){
        StdDraw.setPenColor();
        StdDraw.line(X_MIN, 0, X_MAX, 0);
        StdDraw.line(0, Y_MIN, 0, Y_MAX);

        if(viewLabels){
            int xCur = (int)X_MIN;
            while(xCur % labelInterval != 0){
                xCur++;
            }
            for(int x = xCur; x <= X_MAX; x+=labelInterval){
                if(x != 0){
                    StdDraw.text(x, 0, (int)(x)+"");
                }
            }
            int yCur = (int)Y_MIN;
            while(yCur % labelInterval != 0){
                yCur++;
            }
            for(int y = yCur; y <= Y_MAX; y+=labelInterval){
                if(y != 0){
                    StdDraw.text(0, y, (int)(y)+"");
                }
            }
        }
    }

    public void update(){

        // translate along x axis
        if(StdDraw.isKeyPressed(KeyEvent.VK_D)){
            X_MIN += 1;
            X_MAX += 1;
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_A)){
            X_MIN -= 1;
            X_MAX -= 1;
        }

        // translate along y axis
        if(StdDraw.isKeyPressed(KeyEvent.VK_W)){
            Y_MIN += 1;
            Y_MAX += 1;
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_S)){
            Y_MIN -= 1;
            Y_MAX -= 1;
        }
        // zoom in / zoom out
        if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){
            if(Y_MAX - Y_MIN < 3){
                System.out.println("Max Y zoom reached");
         
            }
            if(X_MAX - X_MIN < 3){
                System.out.println("Max X zoom reached");
      
            }
            Y_MIN += 1;
            Y_MAX -= 1;
            X_MIN += 1;
            X_MAX -= 1;
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_E)){
            Y_MIN -= 1;
            Y_MAX += 1;
            X_MIN -= 1;
            X_MAX += 1;
        }

        // y axis zoom in / zoom out
        if (StdDraw.isKeyPressed(KeyEvent.VK_UP)){
            Y_MIN -= 1;
            Y_MAX += 1;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
            if(Y_MAX - Y_MIN < 3){
                System.out.println("Max Y zoom reached");

            }
            Y_MIN += 1;
            Y_MAX -= 1;

        }

        // x axis zoom in / zoom out
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
            X_MIN -= 1;
            X_MAX += 1;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
            if(X_MAX - X_MIN < 3){
                System.out.println("Max X zoom reached");
        
            }
            X_MIN += 1;
            X_MAX -= 1;

        }

        if (StdDraw.isKeyPressed(KeyEvent.VK_R)){
            X_MIN = -DEFAULT_XY_SCALE;
            X_MAX = DEFAULT_XY_SCALE;
            Y_MIN = -DEFAULT_XY_SCALE;
            Y_MAX = DEFAULT_XY_SCALE;
        }

        StdDraw.setXscale(X_MIN, X_MAX);
        StdDraw.setYscale(Y_MIN, Y_MAX);
    }
}


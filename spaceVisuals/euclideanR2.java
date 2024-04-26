import edu.princeton.cs.introcs.StdDraw;
import java.awt.event.KeyEvent;

public class euclideanR2 extends abstractSpaceVisuals{

    
    public double X_MIN;
    public double X_MAX;
    public double Y_MIN;
    public double Y_MAX;
    private double moveFrac = 0.7;
    private double cutOffFrac = 0.3;

    public euclideanR2(int defaultScale, boolean viewLabels, int labelInterval){
        super(defaultScale, viewLabels, labelInterval);
        X_MIN = -DEFAULT_VIEW_RADIUS;
        X_MAX = DEFAULT_VIEW_RADIUS;
        Y_MIN = -DEFAULT_VIEW_RADIUS;
        Y_MAX = DEFAULT_VIEW_RADIUS;
    }



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
        double xRange = X_MAX-X_MIN;
        double yRange = Y_MAX-Y_MIN;
        // translate along x axis
        if(StdDraw.isKeyPressed(KeyEvent.VK_D)){
            X_MIN += xRange*moveFrac;
            X_MAX += xRange*moveFrac;
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_A)){
            X_MIN -= xRange*moveFrac;
            X_MAX -= xRange*moveFrac;
        }

        // translate along y axis
        else if(StdDraw.isKeyPressed(KeyEvent.VK_W)){
            Y_MIN += yRange*moveFrac;
            Y_MAX += yRange*moveFrac;
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_S)){
            Y_MIN -= yRange*moveFrac;
            Y_MAX -= yRange*moveFrac;
        }
        // zoom in / zoom out
        else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){
            if(Y_MAX <= Y_MIN){
                System.out.println("Max Y zoom reached");
            }
            else{
                System.out.println(yRange);
                Y_MIN += yRange*cutOffFrac;
                Y_MAX -= yRange*cutOffFrac;
            }
            if(X_MAX <= X_MIN){
                System.out.println("Max X zoom reached");
            }
            else{
                X_MIN += xRange*cutOffFrac;
                X_MAX -= xRange*cutOffFrac;
            }
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_E)){
            Y_MIN -= yRange*cutOffFrac;
            Y_MAX += yRange*cutOffFrac;
            X_MIN -= xRange*cutOffFrac;
            X_MAX += xRange*cutOffFrac;
        }

        // y axis zoom in / zoom out
        else if (StdDraw.isKeyPressed(KeyEvent.VK_UP)){
            Y_MIN -= yRange*cutOffFrac;;
            Y_MAX += yRange*cutOffFrac;;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
            if(Y_MAX <= Y_MIN){
                System.out.println("Max Y zoom reached");

            }
            else{
                Y_MIN += yRange*cutOffFrac;;
                Y_MAX -= yRange*cutOffFrac;;
            }
        }

        // x axis zoom in / zoom out
        else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
            X_MIN -= xRange*cutOffFrac;
            X_MAX += xRange*cutOffFrac;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
            if(X_MAX <= X_MIN){
                System.out.println("Max X zoom reached");
        
            }
            else{
                X_MIN += xRange*cutOffFrac;
                X_MAX -= xRange*cutOffFrac;
            }
        }

        // reset scale
       else if (StdDraw.isKeyPressed(KeyEvent.VK_R)){
            X_MIN = -DEFAULT_VIEW_RADIUS;
            X_MAX = DEFAULT_VIEW_RADIUS;
            Y_MIN = -DEFAULT_VIEW_RADIUS;
            Y_MAX = DEFAULT_VIEW_RADIUS;
        }

        StdDraw.setXscale(X_MIN, X_MAX);
        StdDraw.setYscale(Y_MIN, Y_MAX);
    }
}


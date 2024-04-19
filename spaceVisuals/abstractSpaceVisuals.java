import edu.princeton.cs.introcs.StdDraw;

public abstract class abstractSpaceVisuals {
    /*
     * Base class for some space to be displayed
     * Functions are defined over this space
     * 
     * 
     * 
     */
    public final int defaultRange;
    public boolean viewLabels;
    public abstractSpaceVisuals(int range, boolean viewLabels){
        this.defaultRange = 5;
        this.viewLabels = viewLabels;
        StdDraw.enableDoubleBuffering();
    }

    abstract void draw(); // axis and labels / other space info
    abstract void update(); // check user movement and update scale accordingly
    abstract void resetDraw(); // set scale to default values
}

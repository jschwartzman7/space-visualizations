import edu.princeton.cs.introcs.StdDraw;

public abstract class abstractSpaceVisuals {
    /*
     * Base class for some space to be displayed
     * Functions are defined over this space
     * 
     * 
     * 
     */
    final int DEFAULT_VIEW_RADIUS;
    final boolean viewLabels;
    final int labelInterval;

    public abstractSpaceVisuals(int defaultScale, boolean viewLabels, int labelInterval){
        this.DEFAULT_VIEW_RADIUS = defaultScale;
        this.viewLabels = viewLabels;
        this.labelInterval = labelInterval;
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-DEFAULT_VIEW_RADIUS, DEFAULT_VIEW_RADIUS);
    }

    abstract void draw(); // axis and labels / other space info
    abstract void update(); // check user movement and update scale accordingly
}

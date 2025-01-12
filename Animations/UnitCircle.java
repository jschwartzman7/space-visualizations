import edu.princeton.cs.introcs.StdDraw;

public class UnitCircle extends abstractAnimation {

    private double maxPointRadius = 70;
    private int numPoints = 250;
    private double[][] points;
    private double t = 0;
    private double tStep = 0.008;



    public UnitCircle(abstractFunction function, abstractSpaceVisual space, int pixelResolution, int frameSpeed){
        super(function, space, pixelResolution, frameSpeed);
        this.points = new double[numPoints][2];
        for(int i = 0; i < points.length; i++){
            double theta = 2*Math.PI*Math.random();
            double radius = Math.random()*maxPointRadius;
            double x = radius*Math.cos(theta);
            double y = radius*Math.sin(theta);
            points[i][0] = x;
            points[i][1] = y;
        }
    }

    public void draw(){
        StdDraw.circle(0, 0, 1);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLUE);
        for(int i = 0; i < points.length; i++){
            if(Math.sqrt(Math.pow(points[i][0], 2)+Math.pow(points[i][1], 2)) <= Double.MIN_VALUE){
                StdDraw.point(points[i][0], points[i][1]);
            }
            else{
                double x = points[i][0]*(1+t*(1/Math.sqrt(Math.pow(points[i][0], 2)+Math.pow(points[i][1], 2))-1));
                double y = points[i][1]*(1+t*(1/Math.sqrt(Math.pow(points[i][0], 2)+Math.pow(points[i][1], 2))-1));
                StdDraw.point(x, y);
            }
        }
        StdDraw.setPenColor();
        StdDraw.setPenRadius();

        if(t + tStep > 1 || t + tStep < 0){
            tStep = -tStep;
        }
            t += tStep;
    }

    public static void main(String[] args){
        new UnitCircle(null, new Euclidean2D(5, 1, true), 200, 25).run();
    }
}

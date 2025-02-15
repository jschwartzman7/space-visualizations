package spacevisuals.spaces;

import java.util.HashMap;
import spacevisuals.spaces.Euclidean3D;

public class Euclidean4D extends AbstractSpace {

    // w -> 3D(x,y,x)
    public HashMap<Double, Euclidean3D> wAxis;
    public double wMin;
    public double wMax;
    public double wViewRadius;
    public double wResolution;

    public Euclidean4D(boolean viewSpaceInfo){
        super(viewSpaceInfo);
    }

    @Override
    public double[] toViewScreenPoint(double[] worldPoint) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toViewScreenPoint'");
    }
    @Override
    public void updateView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateView'");
    }
    @Override
    public void drawAxes() {
        for(double w = wMin; w < wMax; w += wResolution){
            double distance = Math.abs(w - wViewRadius);
            Euclidean3D euclidean3D = wAxis.get(w);
            euclidean3D.drawAxes();
        }
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'drawAxes'");
    }
    @Override
    public void drawLabels() {
        
    }

    @Override
    public void initializeLabeler() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initializeLabeler'");
    }

    
}

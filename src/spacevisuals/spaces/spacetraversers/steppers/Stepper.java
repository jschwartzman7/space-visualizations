package spacevisuals.spaces.spacetraversers.steppers;

public interface Stepper {
    public double getStep(double axisRange, double pixelResolution);
}

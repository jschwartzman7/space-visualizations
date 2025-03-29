package spacevisuals;

import java.util.function.Function;

import spacevisuals.enums.FunctionsEnum;

public class Constants {

    public static final int CANVAS_HEIGHT = 700;
    public static final int CANVAS_WIDTH = 700;
    public static final int FRAME_RATE = 25;
    public static final double DEFAULT_CLIP_RADIUS = 3;
    public static final double MOVE_SENSITIVITY = 0.025;
    public static final double ZERO_TOLERANCE = 0.000001;
    public static final double DEFAULT_AXIS_RADIUS = DEFAULT_CLIP_RADIUS;
    public static final double DEFAULT_LABEL_INTERVAL = 1;
    public static final int AXIS_PARTITIONS = 10;
    public static final double LABEL_INTERVAL_RANGE_MIN = 8;
    public static final double LABEL_INTERVAL_RANGE_MAX = 20;
    public static final double LABEL_INTERVAL_SCALE_FACTOR = 2;
    public static final double PIXEL_RESOLUTION_HIGH = 300;
    public static final double PIXEL_RESOLUTION_MEDIUM = 100;
    public static final double PIXEL_RESOLUTION_LOW = 25;
    public static final double DISTANCE_STEP = 0.1;
    public static final char ANIMATION_SEPARATOR = ',';
    public static final Function<double[], double[]> DEFAULT_FUNCTION = FunctionsEnum.identity.function;

    public static final double CAMERA3D_PITCH = 0;
    public static final double CAMERA3D_ROLL = 0;
    public static final double CAMERA3D_YAW = 0;
    public static final double CAMERA3D_X = 0;
    public static final double CAMERA3D_Y = 0;
    public static final double CAMERA3D_Z = -50;
    public static final double CAMERA3D_FOCAL_LENGTH = 25;
}

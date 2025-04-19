package spacevisuals.utils;

import java.awt.Color;
import java.util.function.Function;

import spacevisuals.enums.FunctionsEnum;

public class Constants {
    /* Default program variables */
    public static final int CANVAS_HEIGHT = 700;
    public static final int CANVAS_WIDTH = 700;
    public static final int FRAME_RATE = 25;
    public static final double DEFAULT_CLIP_RADIUS = 2;
    public static final double MOVE_SENSITIVITY = 0.025;
    public static final double ZERO_TOLERANCE = 0.000001;
    public static final double DEFAULT_AXIS_RADIUS = DEFAULT_CLIP_RADIUS;
    public static final double DEFAULT_LABEL_INTERVAL = 1;
    public static final int AXIS_PARTITIONS_LOW = 5;
    public static final int AXIS_PARTITIONS_HIGH = 20;
    public static final int LABEL_INTERVAL_RANGE_MIN = 8;
    public static final int LABEL_INTERVAL_RANGE_MAX = 20;
    public static final int LABEL_INTERVAL_SCALE_FACTOR = 2;

    public static final int PIXEL_RESOLUTION_HIGHEST = 500;
    public static final int PIXEL_RESOLUTION_HIGH = 300;
    public static final int PIXEL_RESOLUTION_MEDIUM = 100;
    public static final int PIXEL_RESOLUTION_LOW = 25;
    public static final int PIXEL_RESOLUTION_LOWEST = 10;

    public static final double DISTANCE_STEP = 0.05;
    public static final double POINT_WIDTH = 0.02;
    public static final char ANIMATION_SEPARATOR = ',';
    public static final Color GRAPH_COLOR = Color.BLACK;
    public static final Color TEXT_COLOR = Color.WHITE;

    public static final Function<double[], double[]> DEFAULT_FUNCTION = FunctionsEnum.identity.function;
    public static final Function<double[], double[]> DEFAULT_FUNCTION1D = FunctionsEnum.magnitude.function;
    public static final Function<double[], double[]> DEFAULT_FUNCTION2D = input -> new double[]{input[0], input[0]};
    public static final Function<double[], double[]> DEFAULT_FUNCTION3D = input -> new double[]{input[0], input[0], input[0]};

    public static final double CAMERA3D_PITCH = 0;
    public static final double CAMERA3D_ROLL = 0;
    public static final double CAMERA3D_YAW = 0;
    public static final double CAMERA3D_X = 0;
    public static final double CAMERA3D_Y = 0;
    public static final double CAMERA3D_Z = -20;
    public static final double CAMERA3D_FOCAL_LENGTH = 5;
}

package spacevisuals.enums;

import java.awt.Color;

public enum SpaceColorScheme {

    DARK(Color.blue, Color.green, Color.red, new Color(23, 46, 93), Color.white),
    RED(Color.blue, new Color(0, 211, 56), new Color(223, 0, 243), new Color(155, 3, 29), Color.white),
    DEFAULT(Color.blue, Color.green, Color.red, Color.white, Color.black);

    public final Color xAxisColor;
    public final Color yAxisColor;
    public final Color zAxisColor;
    public final Color backgroundColor;
    public final Color labelColor;

    SpaceColorScheme(Color xAxisColor, Color yAxisColor, Color zAxisColor, Color backgroundColor, Color labelColor) {
        this.xAxisColor = xAxisColor;
        this.yAxisColor = yAxisColor;
        this.zAxisColor = zAxisColor;
        this.backgroundColor = backgroundColor;
        this.labelColor = labelColor;
    }

    public static SpaceColorScheme from(String colorScheme) {
        switch(colorScheme.toLowerCase()) {
            case "dark":
                return SpaceColorScheme.DARK;
            case "red":
                return SpaceColorScheme.RED;
            default:
                return SpaceColorScheme.DEFAULT;
        }
    }
}

package spacevisuals.colors;

import java.awt.Color;

public class ColorScheme {

    public final Color xAxisColor;
    public Color yAxisColor;
    public Color zAxisColor;
    public Color backgroundColor;
    public Color labelColor;

    public ColorScheme(String colorScheme){
        switch(colorScheme){
            case "dark":
                xAxisColor = Color.blue;
                yAxisColor = Color.green;
                zAxisColor = Color.red;
                backgroundColor = new Color(23, 46, 93);
                labelColor = Color.white;
                break;
            default:
                xAxisColor = Color.blue;
                yAxisColor = Color.green;
                zAxisColor = Color.red;
                backgroundColor = Color.white;
                labelColor = Color.black;
                break;
        }
    }
}

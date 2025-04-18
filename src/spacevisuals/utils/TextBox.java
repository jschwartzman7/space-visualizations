package spacevisuals.utils;

import java.util.LinkedList;
import edu.princeton.cs.introcs.StdDraw;

public class TextBox{

    LinkedList<String[]> text;
    private int width = 0;
    private double marginLeftProportion = 0.05;
    private double marginBottomProportion = 0.05;
    private double textHeightProportion = 0.03;
    private double lineLeftProportion = 2;

    public TextBox(){
        this.text = new LinkedList<String[]>();
    }

    public void addText(String key, String value){
        text.add(new String[]{key, value});
    }
    public void clearText(){
        text.clear();
    }

    public void drawTextBox(spacevisuals.spaces.AbstractSpace space){
        width = 0;
        double xClipRange = space.getXRange();
        double yClipRange = space.getYRange();
        double xLeft = space.xClipMin+marginLeftProportion*xClipRange;
        double textHeight = yClipRange*textHeightProportion;
        double height = space.yClipMin+yClipRange*marginBottomProportion;
        StdDraw.setPenColor(space.colorScheme.labelColor);
        for(String[] key : text){
            StdDraw.textLeft(xLeft, height, key[0] + " = " + key[1]);
            height += textHeight;
            int size = key[0].length() + key[1].length();
            if(size > width){
                width = size;
            }
        }
        StdDraw.line(space.xClipMin+marginLeftProportion*xClipRange/lineLeftProportion, space.yClipMin+yClipRange*marginBottomProportion-textHeight/2, space.xClipMin+marginLeftProportion*xClipRange/lineLeftProportion, height-textHeight/2);
    }
    
}

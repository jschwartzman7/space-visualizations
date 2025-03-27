package spacevisuals.helpers;

import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.AbstractSpace;
import spacevisuals.SpaceUser;

public class TextBox extends SpaceUser<AbstractSpace>{

    LinkedList<String[]> text;
    private int width = 0;
    private double marginLeftProportion = 0.05;
    private double marginBottomProportion = 0.05;
    private double textHeightProportion = 0.03;
    private double lineLeftProportion = 2;

    public TextBox(AbstractSpace space){
        super(space);
        this.text = new LinkedList<String[]>();
    }

    public void addText(String key, String value){
        text.add(new String[]{key, value});
    }
    public void clearText(){
        text.clear();
    }

    public void drawTextBox(){
        width = 0;
        double xClipRange = getSpace().getXRange();
        double yClipRange = getSpace().getYRange();
        double xLeft = getSpace().xClipMin+marginLeftProportion*xClipRange;
        double textHeight = yClipRange*textHeightProportion;
        double height = getSpace().yClipMin+yClipRange*marginBottomProportion;
        StdDraw.setPenColor(getSpace().colorScheme.labelColor);
        for(String[] key : text){
            StdDraw.textLeft(xLeft, height, key[0] + " = " + key[1]);
            height += textHeight;
            int size = key[0].length() + key[1].length();
            if(size > width){
                width = size;
            }
        }
        StdDraw.line(getSpace().xClipMin+marginLeftProportion*xClipRange/lineLeftProportion, getSpace().yClipMin+yClipRange*marginBottomProportion-textHeight/2, getSpace().xClipMin+marginLeftProportion*xClipRange/lineLeftProportion, height-textHeight/2);
    }
    
}

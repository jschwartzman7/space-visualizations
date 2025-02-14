package spacevisuals.helpers;

import java.util.HashMap;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.AbstractSpace;

public class TextBox {

    AbstractSpace space;
    private double sizeProportion = 0.4;

    public TextBox(AbstractSpace space){
        this.space = space;
    }

    public void drawTextBox(HashMap<String, String> text, double xDepth){
        double xClipRange = space.xClipMax - space.xClipMin;
        double yClipRange = space.yClipMax - space.yClipMin;
        double textSpace = yClipRange * sizeProportion;
        double height = space.yClipMin + textSpace*0.5;
        double xLeft = space.xClipMax - xClipRange*xDepth;
        for(String key : text.keySet()){
            StdDraw.text(xLeft, height, key+": "+text.get(key));
            height += textSpace;
        }
    }
    
}

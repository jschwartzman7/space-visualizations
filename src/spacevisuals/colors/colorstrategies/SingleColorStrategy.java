package spacevisuals.colors.colorstrategies;

import java.awt.Color;

import spacevisuals.utils.Constants;

public class SingleColorStrategy implements ColorStrategy {

    private final Color DEFAULT_COLOR = Constants.GRAPH_COLOR;
    private Color color;

    public SingleColorStrategy(){
        this.color = DEFAULT_COLOR;
    }
    public SingleColorStrategy(Color color){
        this.color = color;
    }

    @Override
    public Color getColor(double[] input) {
        return color;
    }
    public Color getColor(){
        return color;
    }
    
}

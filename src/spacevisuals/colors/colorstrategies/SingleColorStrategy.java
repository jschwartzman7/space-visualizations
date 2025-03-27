package spacevisuals.colors.colorstrategies;

import java.awt.Color;

public class SingleColorStrategy implements ColorStrategy {

    private final Color DEFAULT_COLOR = Color.BLACK;
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

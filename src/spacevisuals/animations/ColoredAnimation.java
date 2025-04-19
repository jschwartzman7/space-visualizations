package spacevisuals.animations;

import spacevisuals.colors.colorstrategies.*;
import spacevisuals.utils.ConfigurableAnimation;
/*
 * Loweset level animation class that has a color strategy for drawing
 */
public abstract class ColoredAnimation implements ConfigurableAnimation{
    
    protected ColorStrategy colorHelper;

    public ColoredAnimation(ColorStrategy colorHelper) {
        if(colorHelper == null) {
            this.colorHelper = new SingleColorStrategy();
            return;
        }
        this.colorHelper = colorHelper;
    }
    public ColoredAnimation() {
        this.colorHelper = new SingleColorStrategy();
    }
}

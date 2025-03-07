package spacevisuals.animations;

import spacevisuals.animations.SpaceAnimation;
import spacevisuals.helpers.TextBox;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.SpaceUser;

public class Basic3D extends SpaceUser<Euclidean3D> implements SpaceAnimation{

    TextBox details;

    public Basic3D(){
        super(Euclidean3D.Get());
        this.details = new TextBox(space);
    }

    @Override
    public void drawAnimation() {
        space.drawSpace();
        details.clearText();
        details.addText("focal length", space.mover.focalLength+"");
        details.addText("z", space.mover.z+"");
        details.drawTextBox();
    }
}

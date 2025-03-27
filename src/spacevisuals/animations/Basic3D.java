package spacevisuals.animations;

import spacevisuals.helpers.TextBox;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.SpaceUser;

public class Basic3D extends SpaceUser<Euclidean3D> implements SpaceAnimation{

    TextBox details;

    public Basic3D(){
        super(Euclidean3D.Get());
        this.details = new TextBox(getSpace());
    }

    @Override
    public void drawAnimation() {
        getSpace().drawSpace();
        details.clearText();
        details.addText("focal length", getSpace().camera.focalLength+"");
        details.addText("z", getSpace().camera.z+"");
        details.drawTextBox();
    }
}

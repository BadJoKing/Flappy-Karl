package entities;

import controller.Constants;

public class Background extends Entity {

    public Background(){
        super(Constants.backgroundPath);
    }

    public Background(String path) {
        super(path);
    }

    public void move(){
        this.moveBy(-2,0);
    }
}

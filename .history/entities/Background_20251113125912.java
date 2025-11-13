package entities;

import controller.Constants;

public class Background extends Entity {
    private int offset;
    public Background(int x){
        super(Constants.backgroundPath);
        this.offset = x;
        this.moveTo(0+this.offset*this.getIconWidth(), 0);
    }

    public Background(String path) {
        super(path);
    }

    public void move(){
        if(this.getPos()[0] <= -this.getIconWidth()){
            this.moveTo(this.getIconWidth(),0);
        }
        this.moveBy(-2,0);

    }
}

package entities;

import java.awt.Image;

import controller.Constants;

public class Background extends Entity {
    private int offset;

    public Background(String path){
        super(path);
        this.setImage(this.getImage().getScaledInstance(this.getIconWidth(), 780, Image.SCALE_DEFAULT));
    }
    
    public void move(){
        if(this.getPos()[0] <= -this.getIconWidth()){
            this.moveTo(this.getIconWidth(),0);
        }
        this.moveBy(-2,0);

    }
    public void setOffset(int x){
        this.offset = x;
    }   

    public void reset(){
        this.moveTo(this.getIconWidth()*this.offset, 0);
    }

}

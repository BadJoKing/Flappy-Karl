package entities;

import controller.Constants;

public class Background extends Entity {
    private int offset;

    public Background(String path){
        super(path);
    }
    
    public void setOffset(int x){
        this.offset = x;
    }   

    public void reset(){
        this.moveTo(this.getIconWidth()*this.offset, 0);
    }

    public void move(){
        if(this.getPos()[0] <= -this.getIconWidth()){
            this.moveTo(this.getIconWidth(),0);
        }
        this.moveBy(-2,0);

    }
}

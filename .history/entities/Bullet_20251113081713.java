package entities;

import java.awt.Image;

import controller.Constants;

public class Bullet extends Entity{

    private double x,y;
    private Image flying;
    private Image still;

    private boolean shooting;

    public Bullet(String path, double[] workerPos, int[] workerSize) {
        super(path);
        this.reload(workerPos, workerSize);
        this.flying = new ImageIcon(path).getImage().getScaledInstance(Constants.bullet_width*Constants.bullet_scale, Constants.bullet_height*Constants.bullet_scale, Image.SCALE_DEFAULT)
    }

    public void shoot(){
        this.setImage(flying);
        this.shooting = true;
    }


    public void move(double[] workerPos, int[] workerSize){
        //600 is the window width, so if the bullet leaves the screen, it will reload
        if(this.shooting && (this.x>600)){
            this.shooting = false;
        }
        if(!shooting){
            this.reload(workerPos, workerSize);
        } else {
            this.x += 10;
        }
    }

    public void reload(double[] workerPos, int[] workerSize){
        this.x = workerPos[0]+workerSize[0]/2-(this.getIconWidth()/2);
        this.y = workerPos[1]+workerSize[1]/2-(this.getIconHeight()/2);
    }

    public double[] getPos(){
        return new double[] {this.x, this.y};
    }
    
}

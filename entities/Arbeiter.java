package entities;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Arbeiter extends Entity {
    private Bullet bullet;
    private String flapPath;
    private String path;
    public Arbeiter(String path, String flapPath) {
        super(path);
        this.path = path;
        this.bullet = new Bullet("Assets/phb.png", this.getPos());
        this.flapPath = flapPath;
    }

    public void move(){
        this.y += this.vy/5;
        this.updateSpeed();
        if(this.vy < 0){
            this.setImage(new ImageIcon(this.flapPath).getImage());
        } else {
            this.setImage(new ImageIcon(this.path).getImage());
        }
        this.bullet.move(this.getPos());

    }

    public void updateSpeed(){
        this.vy += 2; //downwards acceleration of 5px/frame
    }

    //
    public void jump(){
        System.out.println("jump");
        this.setSpeed(0, -40);
    }

    public double getPosY(){
        return this.getPos()[1];
    }

    public double getPosX(){
        return this.getPos()[0];
    }

    public void shoot(){
        this.bullet.shoot();
    }
    
    public Image getBulletImg(){
        return this.bullet.getImage();
    }

    public double[] getBulletPos(){
        return this.bullet.getPos();
    }

    @Override
    public void moveTo(double x, double y){
        super.moveTo(x,y);
        this.bullet.reload(this.getPos());
    }
}

package entities;
import javax.swing.ImageIcon;

public class Entity extends ImageIcon{
    protected double x,y;
    protected double vx,vy;

    public Entity(String path){
        super(path);
    }

    
    public void moveTo(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void moveBy(double dx,double dy){
        this.x += dx;
        this.y += dy;
    }

    public void setSpeed(double vx, double vy){
        this.vx = vx;
        this.vy = vy;
    }

    public double[] getPos(){
        return new double[] {this.x, this.y};
    }
}
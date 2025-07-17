package entities;
public class Bullet extends Entity{

    private double x,y;

    private int hb_width, hb_height, hbx, hby;

    private boolean shooting;

    public Bullet(String path, double[] workerPos, int[] workerSize) {
        super(path);
        this.reload(workerPos, workerSize);
        this.hbx = (int)this.x+16;
        this.hby = (int)this.x;
        this.hb_width = this.getIconWidth()-((int)Math.abs(this.x-this.hbx));
        this.hb_height = this.getIconHeight();
    }

    public void shoot(){
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
            this.x += 5;
        }
    }

    public void reload(double[] workerPos, int[] workerSize){
        this.x = workerPos[0]+workerSize[0]/2-(this.getIconWidth()/2);
        this.y = workerPos[1]+workerSize[1]/2-(this.getIconHeight()/2);
    }

    public double[] getPos(){
        return new double[] {this.x, this.y};
    }

    public int[] getHBPos(){
        return new int[] {this.hbx, this.hby};
    }

    public int[] getHBSize(){
        return new int[] {this.hb_width, this.hb_height};
    }
    
}

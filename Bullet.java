public class Bullet extends Entity{

    private double x,y;

    private boolean shooting;

    public Bullet(String path, double[] workerPos) {
        super(path);
        this.reload(workerPos);
    }

    public void shoot(){
        this.shooting = true;
    }


    public void move(double[] workerPos){
        //600 is the window width, so if the bullet leaves the screen, it will reload
        if(this.shooting && (this.x>600)){
            this.shooting = false;
        }
        if(!shooting){
            this.reload(workerPos);
        } else {
            this.x += 5;
        }
    }

    public void reload(double[] workerPos){
        this.x = workerPos[0]+(this.getIconWidth()/2);
        this.y = workerPos[1]+(this.getIconHeight()/2);
    }

    public double[] getPos(){
        return new double[] {this.x, this.y};
    }
    
}

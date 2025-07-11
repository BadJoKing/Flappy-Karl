public class Bullet extends Entity{

    private double x,y;

    private boolean shooting;

    public Bullet(String path, double[] workerPos) {
        super(path);
        this.x = workerPos[0]+16;
        this.y = workerPos[1]+16;
    }

    public void shoot(){

    }


    public void move(double playerY){
        if(!shooting){
            this.y = playerY;
        } else {
            this.x += 5;
        }
    }

    public void reload(double[] workerPos){
        this.x = workerPos[0]+16;
        this.y = workerPos[1]+16;
    }
    
}

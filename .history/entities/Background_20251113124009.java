package entities;

public class Background extends Entity {

    public Background(String path) {
        super(path);
    }

    public void move(){
        this.moveBy(-2,0);
    }
}

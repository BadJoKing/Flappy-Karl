public class Arbeiter extends Entity {
    private Bullet bullet;
    public Arbeiter(String path) {
        super(path);
        this.bullet = new Bullet("Assets/phb.png", this.getPos());
    }

    public void move(){
        this.y += this.vy/5;
        this.updateSpeed();

    }

    public void updateSpeed(){
        this.vy += 2; //downwards acceleration of 5px/frame
    }

    //
    public void jump(){
        System.out.println("jump");
        this.setSpeed(0, -50);
    }

    public double getPosY(){
        return this.getPos()[1];
    }

    public double getPosX(){
        return this.getPos()[0];
    }
    
}

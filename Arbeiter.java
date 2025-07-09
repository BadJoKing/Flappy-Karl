public class Arbeiter extends Entity {
    public Arbeiter(String path) {
        super(path);
        //TODO Auto-generated constructor stub
    }

    public void move(){
        this.y += this.vy/5;
        this.updateSpeed();

    }

    public void updateSpeed(){
        this.vy += 2; //downwards acceleration of 5px/frame
    }

    public void jump(){
        System.out.println("jump");
        this.setSpeed(0, -50);
    }

    public double getPosY(){
        return this.getPos()[1];
    }
    
}

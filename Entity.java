import javax.swing.ImageIcon;

public class Entity extends ImageIcon{
    private int x,y;


    public Entity(String path){
        super(path);
    }

    public void move(int dx,int dy){
        this.x += dx;
        this.y += dy;
    }

    public int[] getPos(){
        return new int[] {this.x, this.y};
    }
}
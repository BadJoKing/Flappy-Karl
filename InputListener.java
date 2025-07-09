import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputListener implements KeyListener{

    private Arbeiter a;

    public InputListener(Arbeiter a){
        this.a = a;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.a.jump();
                break;
            case KeyEvent.VK_RIGHT:
                break;
            
            default:
                break;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
}

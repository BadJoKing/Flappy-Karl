package ui;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controller.Constants;
import controller.Controller;

public class InputListener implements KeyListener{

    private Controller partei;

    public InputListener(Controller partei){
        this.partei = partei;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.partei.workerAction(Constants.JUMP);
                break;
            case KeyEvent.VK_RIGHT:
                this.partei.workerAction(Constants.ATK_01);
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

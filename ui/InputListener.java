package ui;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import backend.GameThread;

public class InputListener implements KeyListener{

    private GameThread gt;

    public InputListener(GameThread gt){
        this.gt = gt;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.gt.playerAction(GameThread.JUMP);
                break;
            case KeyEvent.VK_RIGHT:
                this.gt.playerAction(GameThread.ATK_01);
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

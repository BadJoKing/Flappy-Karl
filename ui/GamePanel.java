package ui;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import entities.Arbeiter;
import entities.Kapitalist;

import java.awt.Graphics;
import java.awt.Toolkit;
import backend.GameThread;
import controller.Constants;
import controller.Controller;

public class GamePanel extends JPanel {

    private ImageIcon background;

    private GameThread GT;

    private int score = 0;

    private Controller partei;

    private Arbeiter worker;

    public GamePanel(Controller partei) {
        super();
        this.background = new ImageIcon(Constants.backgroundPath);
        this.partei = partei;
        this.worker = this.partei.getWorker();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.drawFrame(g);
    }

    private void drawFrame(Graphics g){
        g.drawImage(this.background.getImage(),0,0,this);
        for(Kapitalist k: partei.getFirma()){
            g.drawImage(k.getImage(), (int)k.getPos()[0], (int)k.getPos()[1], this);
        }
        

        g.drawImage(this.worker.getBulletImg(), (int)this.worker.getBulletPos()[0], (int)this.worker.getBulletPos()[1], this);
        g.drawImage(this.worker.getImage(), 64, (int)this.worker.getPosY(), this);
        g.drawChars(Integer.toString(this.score).toCharArray(), 0, Integer.toString(this.score).toCharArray().length, 0,0);
        Toolkit.getDefaultToolkit().sync();
    }

    public void stopGameThread(){
        System.out.println("stopping Game");
        this.GT.interrupt();
    }

    public void score(){
        this.score++;
        System.out.println(this.score);
    }

}

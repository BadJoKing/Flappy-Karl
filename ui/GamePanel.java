package ui;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import entities.Arbeiter;
import entities.Kapitalist;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Random;
import backend.GameThread;

public class GamePanel extends JPanel {
    private Arbeiter player;
    private Kapitalist[] firma;
    private ImageIcon background;

    private String workerPath = "Assets/player_placeholder_64.png";
    private String workerFlapPath = "Assets/player_flap.png";
    private String capitalistPath = "Assets/placeholder_pipe_64x800.png";
    private String backgroundPath = "Assets/background.gif";

    private GameThread GT;

    private int score = 0;
    public GamePanel() {
        super();
        this.background = new ImageIcon(backgroundPath);

        this.player = new Arbeiter(workerPath, workerFlapPath);


        this.firma = new Kapitalist[10];
        int min_height = 64;
        int cap_gap = 200;
        int min_top_cap_y = -800+min_height;
        int max_top_cap_y = -min_height-cap_gap;
        int cap_distance = 500;
        
        Random ran = new Random();
        for(int i = 0; i<(this.firma.length/2);i++){
            for(int j = 0; j<2; j++){
                this.firma[2*i] = new Kapitalist(capitalistPath);
                this.firma[2*i].moveTo(600+(cap_distance*i),ran.nextInt(min_top_cap_y,max_top_cap_y));
                System.out.println(this.firma[2*i].getPos()[1]);
                this.firma[2*i+1] = new Kapitalist(capitalistPath);
                this.firma[2*i+1].moveTo(600+(cap_distance*i), (int)this.firma[2*i].getPos()[1]+800+cap_gap);
                System.out.println(this.firma[2*i+1].getPos()[1]);
            }
        }
        this.GT = new GameThread(this, this.player, this.firma);
        this.addKeyListener(new InputListener(this.GT));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.drawFrame(g);
    }

    private void drawFrame(Graphics g){
        g.drawImage(this.background.getImage(),0,0,this);
        for(Kapitalist k: firma){
            g.drawImage(k.getImage(), (int)k.getPos()[0], (int)k.getPos()[1], this);
        }
        g.drawImage(this.player.getBulletImg(), (int)this.player.getBulletPos()[0], (int)this.player.getBulletPos()[1], this);
        g.drawImage(this.player.getImage(), 64, (int)this.player.getPosY(), this);
        g.drawChars(Integer.toString(this.score).toCharArray(), 0, Integer.toString(this.score).toCharArray().length, 0,0);
        Toolkit.getDefaultToolkit().sync();
    }

    public void stopGameThread(){
        System.out.println("stopping Game");
        this.GT.interrupt();
    }

    public void startGame(){
        this.GT.start();
        this.setFocusable(true);
    }

    public void score(){
        this.score++;
        System.out.println(this.score);
    }

}

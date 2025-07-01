import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class GamePanel extends JPanel implements Runnable{

    private final int TICKS_PER_SECOND = 60;
    private final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    private final int MAX_FRAMESKIP = 10;
    private long next_game_tick = System.currentTimeMillis();

    private Arbeiter player;
    private int player_y;
    private int player_vy;

    private Thread GameThread;

    public GamePanel() {
        super();
        this.player = new Arbeiter("Assets/phb.png");
        this.GameThread = new Thread(this);
        this.GameThread.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.drawFrame(g);
    }

    private void drawFrame(Graphics g){
        g.drawImage(this.player.getImage(), 0, player_y, this);

        Toolkit.getDefaultToolkit().sync();
    }

    public void stopGameThread(){
        System.out.println("stopping Game");
        this.GameThread.interrupt();
    }

    @Override
    public void run() {
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(600,800));
        // int frames = 0;
        // long stime = System.currentTimeMillis();
        // try {
        //     Thread.sleep(1);
        // } catch (InterruptedException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        long vtime = System.currentTimeMillis();
        while (true) {
            
            int loops = 0;
            while( System.currentTimeMillis() > next_game_tick && loops < MAX_FRAMESKIP) {
                // ---- Game Code Here ----
                //System.out.println(System.currentTimeMillis()-vtime);
                if((System.currentTimeMillis()-vtime) >= 100){
                    player_vy += 1;
                    //frames = 0;
                    vtime = System.currentTimeMillis();
                }

                player_y+=player_vy;
                player_y %= (800-32);

                next_game_tick += SKIP_TICKS;
                loops++;
            }

            //System.out.println(x + " " + y);
            
            repaint();
            //frames++;
            // if((System.currentTimeMillis()-stime) >= 1000){
            //     System.out.println(frames/(System.currentTimeMillis()-stime));
            //     //frames = 0;
            //     //stime = System.currentTimeMillis();
            // }
        }
    }

}

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

public class GamePanel extends JPanel implements Runnable{

    private final int FRAMERATE = 60; //specifies the desired framerate in frames per second
    private final int SKIP_TICKS = 1000/FRAMERATE; // this is the amount of milliseconds in each frame
    private long next_game_tick = System.currentTimeMillis();

    private Arbeiter player;

    private Thread GameThread;
    private boolean running = false;
    private long current_game_tick = 0;
    private long last_movement_tick = 0;

    public GamePanel() {
        super();
        this.player = new Arbeiter("Assets/phb.png");
        this.addKeyListener(new InputListener(this.player));
        this.GameThread = new Thread(this);
        
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.drawFrame(g);
    }

    private void drawFrame(Graphics g){
        g.drawImage(this.player.getImage(), 64, (int)this.player.getPosY(), this);

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
        System.out.println("game started");
        while (true) {
            
            update_game();
            //System.out.println("a");
            repaint();

            
            this.current_game_tick++;
            
            next_game_tick += SKIP_TICKS;// = the time that the next frame should happen at
            int sleep_time = (int) (next_game_tick - System.currentTimeMillis());// = the time that is between now and next_game_tick
            if( sleep_time >= 0 ) {
                try {
                    Thread.sleep(sleep_time); //wait for the right time for the next frame to appear.
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            //frames++;
            // if((System.currentTimeMillis()-stime) >= 1000){
            //     System.out.println(frames/(System.currentTimeMillis()-stime));
            //     //frames = 0;
            //     //stime = System.currentTimeMillis();
            // }
        }
    }

    public void startGame(){
        this.next_game_tick = System.currentTimeMillis();
        this.GameThread.start();
        this.setFocusable(true);
    }

    public void update_game(){
        //System.out.println(this.current_game_tick);
        //this if statement makes the game only update the movement every 100 ticks
        this.player.move();
        //System.out.println(this.player.getPosY());

    }

}

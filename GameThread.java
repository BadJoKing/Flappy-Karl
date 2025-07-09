import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

public class GameThread extends Thread{
    public static final int JUMP = 0; // the 'action code' for jumping
    public static final int ATK_01 = 1; //the 'action code' for attacking
    public static final int LOSE = 2;

    private final int FRAMERATE = 60; //specifies the desired framerate in frames per second
    private final int SKIP_TICKS = 1000/FRAMERATE; // this is the amount of milliseconds in each frame
    private long next_game_tick = System.currentTimeMillis();
    private boolean running = false;



    private GamePanel display;
    private Arbeiter player;
    private Kapitalist[] firma;
    private int min_height = 64; //minimum height for the capitalists
    private int cap_speed = 5; //capitalist speed
    private int cap_gap = 200; //how big the gap to fligh through is
    private int min_top_cap_y = -800+min_height; //minimum y-coordinate for the top capitalist
    private int max_top_cap_y = -min_height-cap_gap; //maximum y-coordinate for the bottom capitalist
    private int cap_space = 500;


    public GameThread(GamePanel display, Arbeiter player, Kapitalist[] firma) {
        super();
        this.display = display;
        this.player = player;
        this.firma = firma;
    }

    @Override
    public void run() {
        this.display.setBackground(Color.BLACK);
        this.display.setPreferredSize(new Dimension(600,800));
        // int frames = 0;
        // long stime = System.currentTimeMillis();
        // try {
        //     Thread.sleep(1);
        // } catch (InterruptedException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        this.player.moveTo(64, (800-this.player.getIconHeight())/2);
        while (true) {
            while (!this.running) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            this.next_game_tick = System.currentTimeMillis();
            while (running) {
                
                update_game();
                //System.out.println("a");
                this.display.repaint();
                
                next_game_tick += SKIP_TICKS;// = the time that the next frame should happen at
                int sleep_time = (int) (next_game_tick - System.currentTimeMillis());// = the time that is between now and next_game_tick
                if( sleep_time >= 0 ) {
                    try {
                        Thread.sleep(sleep_time); //wait for the right time for the next frame to appear.
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        //e.printStackTrace();
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
    }
    

    /**
     * ----------- updates the game state --------------
     */
    public void update_game(){
        //System.out.println(this.current_game_tick);
        //this if statement makes the game only update the movement every 100 ticks
        if((this.player.getPosY() >= (800-this.player.getIconHeight())) || (this.player.getPosY() <= -10)){
            this.running = false;
            System.exit(0);
        }
        this.player.move();
        this.updateCapitalists();

        //System.out.println(this.player.getPosY());

    }

    @Override
    public void start(){
        this.next_game_tick = System.currentTimeMillis();
        super.start();

    }


    public void playerAction(int action){
        if(!running) running = true;
        switch (action) {
            case JUMP:
                this.player.jump();
                break;
            case ATK_01:
                break;
            case LOSE:
                this.interrupt();
                System.exit(0);
                break;
            default:
                break;
        }
    }

    private void updateCapitalists(){
        Random ran = new Random();
        for(int i = 0; i<(this.firma.length/2); i++){
            if(this.firma[2*i].getPos()[0] <= -32){
                this.firma[2*i].moveBy((this.firma.length/2)*cap_space,0);
                this.firma[2*i].moveTo((int)this.firma[2*i].getPos()[0], ran.nextInt(min_top_cap_y, max_top_cap_y));

                this.firma[2*i+1].moveTo((int)this.firma[2*i].getPos()[0], (int)this.firma[2*i].getPos()[1]+800+cap_gap);
            }
            this.firma[2*i].moveBy(-cap_speed,0);
            this.firma[2*i+1].moveBy(-cap_speed,0);
        }

        for (int i = 0; i<(this.firma.length/2); i++) {
            Kapitalist k = this.firma[2*i];
            if(
                ((this.player.getPosX()+this.player.getIconWidth()) >= k.getPos()[0])
                &&(
                    (this.player.getPosY() <= (k.getPos()[1]+800))
                    || ((this.player.getPosY()+this.player.getIconHeight())>= k.getPos()[1]+800+cap_gap)
                )
            ){
                System.exit(0);
            }
        }

    }
}

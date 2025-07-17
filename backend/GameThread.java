package backend;

import java.awt.Dimension;
import java.util.Random;

import entities.Arbeiter;
import entities.Bullet;
import entities.Kapitalist;
import ui.GamePanel;
import controller.Constants;
import controller.Controller;

public class GameThread extends Thread{
    private final int SKIP_TICKS = 1000/Constants.FRAMERATE; // this is the amount of milliseconds in each frame
    private long next_game_tick = System.currentTimeMillis();
    private boolean running = false;



    private GamePanel display;      
    private Arbeiter worker;
    private Kapitalist[] top_caps;
    private Kapitalist[] bot_caps;
    private Kapitalist[] barricades;
    private boolean[] move_bar = new boolean[5];
    private Bullet bullet;

    private byte score_ticks;


    private Controller partei;


    public GameThread(Controller partei) {
        super();
        this.partei = partei;
        this.display = partei.getGamePanel();
        this.worker = partei.getWorker();
        this.top_caps = partei.getTopCaps();
        this.bot_caps = partei.getBotCaps();
        this.barricades = partei.getBarricades();
        this.bullet = this.worker.getBullet();
        for(boolean i: move_bar){
            i = false;
        }
    }

    @Override
    public void run() {
        this.display.setPreferredSize(new Dimension(600,800));
        // int frames = 0;
        // long stime = System.currentTimeMillis();
        // try {
        //     Thread.sleep(1);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        this.partei.resetWorker();
        //System.out.println("a");
        while (true) {
            while (!this.running) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
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
    

    /*
     * updates the game state
     */
    public void update_game(){
        //System.out.println(this.current_game_tick);
        //this if statement makes the game only update the movement every 100 ticks
        if((this.worker.getPosY() >= (800-this.worker.getIconHeight())) || (this.worker.getPosY() <= -10)){
            this.running = false;
            this.partei.workerAction(Constants.LOSE);
        }
        this.update_capitalists();
        this.worker.move();


        this.score_ticks++;
        //System.out.println(this.worker.getPosY());

    }

    @Override
    public void start(){
        this.next_game_tick = System.currentTimeMillis();
        super.start();

    }

    


    /**
     * handles the capitalists' movement and collision with the worker
     */
    private void update_capitalists(){
        Random ran = new Random();
        //iterate through every capitalist
        for(int i = 0; i<(this.top_caps.length); i++){
            Kapitalist k = this.top_caps[i];
            //if the i-th top capitalist is completely off the left side of the screen
            if(k.getPos()[0] <= -k.getIconWidth()){
                //move the i-th top capitalist i spaces to the right
                k.moveBy((this.top_caps.length)*Constants.cap_distance,0);
                //put the new gap at a random height
                k.moveTo((int)k.getPos()[0], ran.nextInt(Constants.min_top_cap_y, Constants.max_top_cap_y));

                //move a barricade to its corresponding capitalist with a one in 5 chance
                if(!this.move_bar[i] && (ran.nextInt()%5 == 0)){
                    this.barricades[i].moveTo(k.getPos()[0], k.getPos()[1]+k.getIconHeight());
                    this.move_bar[i] = true;
                }

                //move the corresponding i-th bottom capitalist to the same
                //x-position as the top capitalist and then adjust the y-position
                //to keep the gap size at Constants.cap_gap
                this.bot_caps[i].moveTo((int)k.getPos()[0], (int)k.getPos()[1]+800+Constants.cap_gap);
            }


            if(barricades[i].getPos()[0] <= -barricades[i].getIconWidth()){
                this.move_bar[i] = false;
            }

            //if any part of the bullet is
            if(
                (this.move_bar[i])
                &&
                //below the top edge of the barricade
                ((this.bullet.getPos()[1]+this.bullet.getIconHeight())>=(this.barricades[i].getPos()[1]))
                &&//AND
                //above the bottom edge of the barricade
                (this.bullet.getPos()[1]<=(this.barricades[i].getPos()[1]+this.barricades[i].getIconHeight()))
                &&//AND
                //to the right of the leftmost edge of the barricade
                ((this.bullet.getPos()[0]+this.bullet.getIconWidth())>=barricades[i].getPos()[0])
            ){//then set the barricade to non-moving and move the barricade off the screen
                //System.out.println("bullet collision");
                this.move_bar[i] = false;
                this.barricades[i].moveTo(-200,0);
            }


            //---basic movement---
            k.moveBy(-Constants.cap_speed,0);
            this.bot_caps[i].moveBy(-Constants.cap_speed,0);
            if(this.move_bar[i]) this.barricades[i].moveBy(-Constants.cap_speed, 0);


            //---capitalist-worker interactions---
            double[] kPos = k.getPos();

            double workerPosX = this.worker.getPosX();
            double workerPosY = this.worker.getPosY();
            //every 100 ticks, check if the worker passed by a capitalist
            if ((this.score_ticks >= 100)&&((kPos[0]+k.getIconWidth())<=workerPosX)) {
                this.display.score();
                this.score_ticks = 0;
            }
            //collision of worker and capitalist
            if( 
                //if the difference between the worker's center x coordinate and the capitalist's center x coordinate 
                (Math.abs((workerPosX+this.worker.getIconWidth()/2) - (kPos[0]+k.getIconWidth()/2))<(this.worker.getIconWidth()/2 - 10 + k.getIconWidth()/2))
                //AND
                &&(
                    //the worker is above the bottom edge of the top capitalist
                    (workerPosY <= (kPos[1]+800))
                    //OR
                    ||
                    //the worker's bottom edge is below the bottom capitalist's top edge
                    ((workerPosY+this.worker.getIconHeight())>= kPos[1]+800+Constants.cap_gap)
                    //OR
                    ||
                    //The worker's rightmost edge is at the same position as or to the right of the barricade's leftmost edge
                    //while that barrier is moving
                    ((this.move_bar[i])&&((workerPosX+this.worker.getIconWidth())>=this.barricades[i].getPos()[0]))
                )
            ){//then the player is colliding with a barrier or a capitalist and loses
                //System.out.println("collision");
                this.partei.workerAction(Constants.LOSE);
            }
            
        }




    }

    public boolean isRunning(){
        return this.running;
    }

    public void setRunning(boolean running){
        this.running = running;
    }

}

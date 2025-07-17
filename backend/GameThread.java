package backend;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import entities.Arbeiter;
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
    private Kapitalist[] firma;

    private byte score_ticks;


    private Controller partei;


    public GameThread(Controller partei) {
        super();
        this.partei = partei;
        this.display = partei.getGamePanel();
        this.worker = partei.getWorker();
        this.firma = partei.getFirma();
    }

    @Override
    public void run() {
        this.display.setPreferredSize(new Dimension(600,800));
        // int frames = 0;
        // long stime = System.currentTimeMillis();
        // try {
        //     Thread.sleep(1);
        // } catch (InterruptedException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        this.partei.resetWorker();
        System.out.println("a");
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
    

    /*
     * updates the game state
     */
    public void update_game(){
        //System.out.println(this.current_game_tick);
        //this if statement makes the game only update the movement every 100 ticks
        if((this.worker.getPosY() >= (800-this.worker.getIconHeight())) || (this.worker.getPosY() <= -10)){
            this.running = false;
            System.exit(0);
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
        //iterate through every second index of the firma
        for(int i = 0; i<(this.firma.length/2); i++){
            Kapitalist k = this.firma[2*i];
            //---collision---
            //if the i-th top capitalist is completely off the left side of the screen
            if(k.getPos()[0] <= -k.getIconWidth()){
                //move the i-th top capitalist i spaces to the right
                k.moveBy((this.firma.length/2)*Constants.cap_distance,0);
                //put the new gap at a random height
                k.moveTo((int)k.getPos()[0], ran.nextInt(Constants.min_top_cap_y, Constants.max_top_cap_y));
                //move the corresponding i-th bottom capitalist to the same
                //x-position as the top capitalist and then adjust the y-position
                //to keep the gap size at Constants.cap_gap
                this.firma[2*i+1].moveTo((int)k.getPos()[0], (int)k.getPos()[1]+800+Constants.cap_gap);
            }
            //---basic movement---
            k.moveBy(-Constants.cap_speed,0);
            this.firma[2*i+1].moveBy(-Constants.cap_speed,0);
        }

        for (int i = 0; i<(this.firma.length/2); i++) {
            Kapitalist k = this.firma[2*i];
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
                //if the worker's leftmost edge is greater than the capitalist's leftmost edge
                (Math.abs(workerPosX+this.worker.getIconWidth() - kPos[0])<5)
                //AND
                &&(
                    //the worker is above the bottom edge of the top capitalist
                    (workerPosY <= (kPos[1]+800))
                    //OR
                    ||
                    //the worker's bottom edge is below the bottom capitalist's top edge
                    ((workerPosY+this.worker.getIconHeight())>= kPos[1]+800+Constants.cap_gap)
                )
            ){
                System.out.println("collision");
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

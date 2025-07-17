package controller;

import java.awt.Image;
import java.util.Random;

import backend.GameThread;
import entities.Arbeiter;
import entities.Kapitalist;
import ui.GamePanel;
import ui.InputListener;
import ui.Interface;

public class Controller {
    // Game objects
    private Arbeiter worker = new Arbeiter(Constants.workerPath, Constants.workerFlapPath);
    private Kapitalist[] firma = new Kapitalist[10];


    //other objects
    private GamePanel gp;
    private GameThread gt;

    public Controller() {
        
        
        Random ran = new Random();
        for(int i = 0; i<(this.firma.length/2);i++){
            for(int j = 0; j<2; j++){
                this.firma[2*i] = new Kapitalist(Constants.capitalistPath);
                this.firma[2*i].moveTo(600+(Constants.cap_distance*i),ran.nextInt(Constants.min_top_cap_y,Constants.max_top_cap_y));
                System.out.println(this.firma[2*i].getPos()[1]);
                this.firma[2*i+1] = new Kapitalist(Constants.capitalistPath);
                this.firma[2*i+1].moveTo(600+(Constants.cap_distance*i), (int)this.firma[2*i].getPos()[1]+800+Constants.cap_gap);
                System.out.println(this.firma[2*i+1].getPos()[1]);
            }
        }

        this.gp = new GamePanel(this);
        this.gt = new GameThread(this);
        this.gp.addKeyListener(new InputListener(this));

        new Interface(this, this.gp);
    }


    public void startGame(){
        //tells the GamePanel to start the GameThread
        this.gt.start();
        //sets the GamePanel focusable
        this.gp.setFocusable(true);
        //this requests focus for the GamePanel. It's necessary for the InputListener to work
        this.gp.requestFocus();
    }


    //----Getters----
    public Kapitalist[] getFirma(){
        return this.firma;
    }

    public Arbeiter getWorker(){
        return this.worker;
    }

    public GamePanel getGamePanel(){
        return this.gp;
    }

    /**
     * Does whatever the InputListener tells it to do
     * @param action an integer that determines what the worker should do.
     */
    public void workerAction(int action){
        if(!this.gt.isRunning()) this.gt.setRunning(true);
        switch (action) {
            case Constants.JUMP:
                this.worker.jump();
                break;
            case Constants.ATK_01:
                this.worker.shoot();
                break;
            case Constants.LOSE:
                this.gt.interrupt();
                System.exit(0);
                break;
            default:
                break;
        }
    }

    public void resetWorker(){
        this.worker.moveTo(64, (800-this.worker.getIconHeight())/2);
    }

}

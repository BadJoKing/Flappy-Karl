package controller;

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
    private Kapitalist[] top_caps = new Kapitalist[5];
    private Kapitalist[] bot_caps = new Kapitalist[5];
    private Kapitalist[] barricades = new Kapitalist[5];

    //other objects
    private GamePanel gp;
    private GameThread gt;

    public Controller() {
        
        
        Random ran = new Random();
        for(int i = 0; i<(this.top_caps.length);i++){
            for(int j = 0; j<2; j++){
                this.top_caps[i] = new Kapitalist(Constants.capitalistPath);
                this.top_caps[i].moveTo(600+(Constants.cap_distance*i),ran.nextInt(Constants.min_top_cap_y,Constants.max_top_cap_y));
                //System.out.println(this.top_caps[i].getPos()[1]);

                this.bot_caps[i] = new Kapitalist(Constants.capitalistPath);
                this.bot_caps[i].moveTo(600+(Constants.cap_distance*i), (int)this.top_caps[i].getPos()[1]+800+Constants.cap_gap);
                //System.out.println(this.bot_caps[i].getPos()[1]);

                this.barricades[i] = new Kapitalist(Constants.barricadePath);
                this.barricades[i].moveTo(-200, 0);
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
    public Kapitalist[] getTopCaps(){
        return this.top_caps;
    }

    public Kapitalist[] getBotCaps(){
        return this.bot_caps;
    }

    public Arbeiter getWorker(){
        return this.worker;
    }

    public GamePanel getGamePanel(){
        return this.gp;
    }

    public Kapitalist[] getBarricades(){
        return this.barricades;
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener implements ActionListener{
    public static final int EXIT = 0;
    public static final int SETTINGS = 1;
    public static final int PLAY = 2;
    private Interface I;
    private int command = 0;

    public MenuListener(Interface I, int command){
        this.command = command;
        this.I = I;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (this.command) {
            case EXIT:
                this.I.dispose();
                break;
            case SETTINGS:
                System.out.println("Karl");
                break;
            case PLAY:
                System.out.println("Friedrich");
                break;
            default:
                break;
        }
    }
    
}

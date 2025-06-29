import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{
    private JButton[] menu;
    private Interface parent;
    public MenuPanel(Interface parent){
        this.parent = parent;

        /*
        * Erstellt 3 Knöpfe (Namen sind noch Platzhalter)
        * der erste Knopf beendet das Programm, der Zweite öffnet die
        * Einstellungen und der Dritte startet das Spiel.
        */ 
        this.menu = new JButton[3];
        this.menu[0] = new JButton("Quit");
        this.menu[0].addActionListener(new MenuListener(this.parent, MenuListener.EXIT));
        this.add(menu[0]);
        
        this.menu[1] = new JButton("Settings");
        this.menu[1].addActionListener(new MenuListener(this.parent, MenuListener.SETTINGS));
        this.add(menu[1]);

        this.menu[2] = new JButton("Play");
        this.menu[2].addActionListener(new MenuListener(this.parent, MenuListener.PLAY));
        this.add(menu[2]);
    }
}

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;


public class Interface extends JFrame{
    private JPanel panel;
    private MenuPanel mp;
    private GamePanel gp;
    private CardLayout layout;

    public Interface() {
        super();
        //sets size and layout
        this.setSize(600,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.layout = new CardLayout();
        //creates the main panel
        this.panel = new JPanel();
        //sets main panel layout to the previously created CardLayout
        this.panel.setLayout(this.layout);
        //adds the main Panel to the Frame
        this.add(this.panel);

        //creates the MenuPanel
        this.mp = new MenuPanel(this);

        //adds the MenuPanel to the main Panel
        this.panel.add(this.mp);
        //adds the MenuPanel as a LayoutComponent
        this.layout.addLayoutComponent(this.mp, "menu");
        
        //creates the GamePanel
        this.gp = new GamePanel();
        
        this.panel.add(this.gp);
        this.layout.addLayoutComponent(this.gp, "game");

        //sets the frame visible
        this.setVisible(true);
        this.layout.show(this.panel,"menu");
    }

    public void showGame(){
        this.layout.show(this.panel, "game");
    }
}

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;


public class Interface extends JFrame{
    private JPanel menuPanel;
    private JButton[] menu;
    private CardLayout layout;

    public Interface() {
        super();
        this.layout = new CardLayout();

        this.setSize(600,800);
        this.setLayout(new CardLayout());

        this.menuPanel = new JPanel();
        this.menuPanel.setLayout(new FlowLayout());
        this.add(this.menuPanel);
        this.layout.addLayoutComponent(this.menuPanel, "menu");
        
        this.menu = new JButton[3];
        this.menu[0] = new JButton("Jeremy");
        this.menu[0].setSize(600,800/3);
        this.menu[0].addActionListener(new MenuListener(this, MenuListener.EXIT));
        this.menuPanel.add(menu[0]);
        
        this.menu[1] = new JButton("Karl");
        this.menu[1].setSize(600,800/3);
        this.menu[1].addActionListener(new MenuListener(this, MenuListener.SETTINGS));
        this.menuPanel.add(menu[1]);

        this.menu[2] = new JButton("Friedrich");
        this.menu[2].setSize(600,800/3);
        this.menu[2].addActionListener(new MenuListener(this, MenuListener.PLAY));
        this.menuPanel.add(menu[2]);


        this.layout.show(this,"menu");
        this.setVisible(true);
    }
}

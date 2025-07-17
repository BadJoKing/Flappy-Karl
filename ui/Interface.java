package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;



public class Interface extends JFrame{
    private JPanel panel;
    private MenuPanel mp;
    private GamePanel gp;
    private CardLayout layout;
    private Controller partei;
    private JPanel aboutPanel;
    private JLabel aboutLabel;

    public Interface(Controller partei, GamePanel gp, InputListener il) {
        super();
        //establishes the local party 
        this.partei = partei;

        //sets size and layout
        this.setSize(600,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.layout = new CardLayout();
        //creates the main panel
        this.panel = new JPanel();
        //sets main panel layout to the previously created CardLayout
        this.panel.setLayout(this.layout);
        //adds the main Panel to the Frame
        this.add(this.panel);

        //creates the MenuPanel
        this.mp = new MenuPanel(this);
        this.mp.setFocusable(true);
        this.mp.addKeyListener(il);

        //adds the MenuPanel to the main Panel
        this.panel.add(this.mp);
        //adds the MenuPanel as a LayoutComponent
        this.layout.addLayoutComponent(this.mp, "menu");
        
        //creates the GamePanel
        this.gp = gp;
        this.gp.setFocusable(true);
        this.gp.addKeyListener(il);

        this.panel.add(this.gp);
        this.layout.addLayoutComponent(this.gp, "game");

        this.aboutLabel = new JLabel("<html>Ein Spiel von<br/>Beni Antesberger<br/>Nox Kircher<br/>Felix Schmid-Burgk");
        this.aboutLabel.setSize(600,800);
        try {
            this.aboutLabel.setFont(Font.createFont(Font.TRUETYPE_FONT, new File("Assets/Anarchaos.otf")).deriveFont(60f));
        } catch (FontFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.aboutLabel.setForeground(new Color(1f, 1f, 0f, 1f));
        

        this.aboutPanel = new JPanel();
        this.aboutPanel.add(this.aboutLabel);
        this.aboutPanel.setFocusable(true);
        this.aboutPanel.addKeyListener(il);

        this.aboutPanel.setBackground(new Color(0.7f, 0.0f, 0.0f, 1.0f));
        this.panel.add(this.aboutPanel);
        this.layout.addLayoutComponent(this.aboutPanel, "about");

        //sets the frame visible
        this.setVisible(true);
        this.layout.show(this.panel,"menu");
    }

    public void showGame(){
        
        //shows the GamePanel
        this.layout.show(this.panel, "game");
        this.partei.startGame();

    }

    public void stopGame(){
        this.gp.stopGameThread();
    }

    public void showMenu(){
        this.layout.show(this.panel, "menu");
        this.mp.requestFocus();
    }

    public void showAbout(){
        this.layout.show(this.panel, "about");
        this.aboutPanel.requestFocus();
    }
    
}

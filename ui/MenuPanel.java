package ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MenuPanel extends JPanel {
    private JButton[] menu;
    private JLabel menulabel;
    private Interface parent;
    public Font customFont;
    public Font titel;
    private Color buttoncolor;
    private String backgroundPath = "Assets/menupanel_background.png";
    private ImageIcon background;


    public MenuPanel(Interface parent) {
        this.parent = parent;

        this.background = new ImageIcon(backgroundPath);
        //für leichte Transparanz bei den Knöpfen
        this.buttoncolor = new Color(1f,0f,0f,0.4f);

        try {
            //create the font to use. Specify the size!
            this.customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Assets/Anarchaos.otf")).deriveFont(30f);
            this.titel = Font.createFont(Font.TRUETYPE_FONT, new File("Assets/Anarchaos.otf")).deriveFont(60f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
            ge.registerFont(titel);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }

        /*
         * Erstellt 3 Knöpfe (Namen sind noch Platzhalter)
         * der erste Knopf beendet das Programm, der Zweite öffnet die
         * Einstellungen und der Dritte startet das Spiel.
         */

        this.setLayout(null);

        this.menu = new JButton[3];
        this.menu[0] = new JButton("Quit");
        this.menu[0].addActionListener(new MenuListener(this.parent, MenuListener.EXIT));
        this.menu[0].setBounds(265, 600, 70, 40);
        this.add(menu[0]);

        this.menu[1] = new JButton("Settings");
        this.menu[1].addActionListener(new MenuListener(this.parent, MenuListener.SETTINGS));
        this.menu[1].setBounds(245, 380, 110, 40);
        this.add(menu[1]);

        this.menu[2] = new JButton("Play");
        this.menu[2].addActionListener(new MenuListener(this.parent, MenuListener.PLAY));
        this.menu[2].setBounds(265, 180, 70, 40);
        this.add(menu[2]);

        this.menulabel = new JLabel("FLAPPY KARL");
        this.menulabel.setBounds(165, 50, 270, 50);
        this.menulabel.setFont(titel);
        this.add(menulabel);

        //Knöpfe konfigurieren
        for (int i = 0; i < 3; i++) {
            this.menu[i].setBackground(buttoncolor);
            //Hintergrund transparent
            //this.menu[i].setContentAreaFilled(false);
            //this.menu[i].setOpaque(false);
            this.menu[i].setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
            this.menu[i].setForeground(Color.yellow);
            this.menu[i].setFont(customFont);
        }

        //Hintergrund des Panels rot färben
        //this.setBackground(Color.red);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background.getImage(), 0, 0, this);
    }
}
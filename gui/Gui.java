package gui;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.draw.Draw;
import gui.menu.Menu;
import gui.tools.Tools;

import java.awt.BorderLayout;
import java.awt.Toolkit;

public class Gui extends JFrame
{
    int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public Gui()
    {
        //General settings
        super("eGraphe STUDENT");
        this.setSize((int)(width*0.8),(int)(height*0.8));
        this.setLocationRelativeTo(null); //Centering the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Changing style of the application
        try
        {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //The first thing we will add to the gui is the JMenuBar
        this.setJMenuBar(new Menu());
        this.add(new Tools(), BorderLayout.NORTH);
        this.add(new Draw());

        //The gui is finished, we display it
        this.setVisible(true);
    }
}

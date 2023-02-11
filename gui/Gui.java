package gui;
import javax.swing.JFrame;
import gui.menu.Menu;
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

        //The first thing we will add to the gui is the JMenuBar
        this.setJMenuBar(new Menu());





        //The gui is finished, we display it
        this.setVisible(true);
    }
}

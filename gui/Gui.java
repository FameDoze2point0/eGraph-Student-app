package gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.draw.Draw;
import gui.draw.PanelPaint;
import gui.menu.Menu;
import gui.tools.Tools;
import settings.Settings;
import settings.SettingsDialog;
import util.Edge;
import util.Graph;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.util.HashMap;
import java.awt.geom.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Gui extends JFrame
{
    private int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    //Each tabulation is associated with an object
    private HashMap<JPanel,Graph> tabulations;

    //Components of the gui
    private Menu menu;
    private Tools tools;
    private Draw draw;
    private static Settings settings;

    /* State of the program :
     * - 0 : Mouse is selected
     * - 1 : new Vertex Mode
     * - 2 : new Edge Mode
     */ int state = 0;

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

        //We load saved settings
        try
        {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/settings/settings");
            ObjectInputStream ois = new ObjectInputStream(fis);

            //Reading the settings
            settings = (Settings)ois.readObject();
            ois.close();
            fis.close();
        }

        catch(Exception e)
        {
            System.out.println("No existing settings save, creating one.");
            settings = new Settings();
        }

        //We create the settings JDialog
        settings.setWindow(new SettingsDialog(this));

        //Init the elements then adding them in correct order
        draw = new Draw();
        menu = new Menu(this, draw);
        tools = new Tools(this, draw);

        this.setJMenuBar(menu);
        this.add(tools, BorderLayout.NORTH);
        this.add(draw);

        //The gui is finished, we display it
        this.setVisible(true);

        //Initiating the list of tabulations
        tabulations = new HashMap<JPanel,Graph>();

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public HashMap<JPanel, Graph> getTabulations() {
        return tabulations;
    }

    public void setTabulations(HashMap<JPanel, Graph> tabulations) {
        this.tabulations = tabulations;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Tools getTools() {
        return tools;
    }

    public void setTools(Tools tools) {
        this.tools = tools;
    }

    public Draw getDraw() {
        return draw;
    }

    public void setDraw(Draw draw) {
        this.draw = draw;
    }

    public static Settings getSettings() {
        return settings;
    }

    public static void setSettings(Settings settings) {
        Gui.settings = settings;
    }
}
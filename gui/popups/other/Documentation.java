package gui.popups.other;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;

import gui.Gui;

public class Documentation extends JDialog
{
    //Tabulations
    private JTabbedPane tabs;

    //Jpanels associated to the tabs
    private JScrollPane graphCreation,
                        toolbar,
                        extendsButton,
                        rightClick,
                        launchAlgorithms,
                        settings,
                        shortcuts;

    //Viewports to display images in jscrollpane
    private JViewport graphCreationView,
                      toolbarView,
                      extendsButtonView,
                      rightClickView,
                      launchAlgorithmsView,
                      settingsView,
                      shortcutsView;

    public Documentation(Gui gui)
    {
        super(gui, "Documentation",true);
        this.setMinimumSize(new Dimension(900,800));
        this.setSize(new Dimension(900,800));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        //This JDialog is divided in several topics : graph creation, toolbar, extends button, right click, launch an algorithms, settings, shortcuts
        tabs = new JTabbedPane();
        
        //graph creation
        graphCreation = new JScrollPane();
        graphCreation.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        graphCreation.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        try
        {
            //Part 0 contains the images
            JPanel part0 = new JPanel();
            part0.setLayout(new BoxLayout(part0, BoxLayout.Y_AXIS));
            JLabel part1 = new JLabel(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/gui/popups/other/ressources/graphCreation1.png"))));
            part0.add(part1);
            JLabel part2 = new JLabel(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/gui/popups/other/ressources/graphCreation2.png"))));
            part0.add(part2);

            //Settings up the viewport to display the images
            graphCreationView = new JViewport();
            graphCreationView.setView(part0);
            graphCreation.setViewport(graphCreationView);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tabs.add("Create a graph", graphCreation);



        //ToolBar
        toolbar = new JScrollPane();
        toolbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        toolbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        try
        {
            //Part 0 contains the images
            JPanel part0 = new JPanel();
            part0.setLayout(new BoxLayout(part0, BoxLayout.Y_AXIS));
            JLabel part1 = new JLabel(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/gui/popups/other/ressources/toolbar1.png"))));
            part0.add(part1);
            JLabel part2 = new JLabel(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/gui/popups/other/ressources/toolbar2.png"))));
            part0.add(part2);

            //Settings up the viewport to display the images
            toolbarView = new JViewport();
            toolbarView.setView(part0);
            toolbar.setViewport(toolbarView);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tabs.add("The Toolbar", toolbar);



        //Extends button
        extendsButton = new JScrollPane();
        extendsButton.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        extendsButton.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        try
        {
            //Part 0 contains the images
            JPanel part0 = new JPanel();
            part0.setLayout(new BoxLayout(part0, BoxLayout.Y_AXIS));
            JLabel part1 = new JLabel(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/gui/popups/other/ressources/extendsButton1.png"))));
            part0.add(part1);

            //Settings up the viewport to display the images
            extendsButtonView = new JViewport();
            extendsButtonView.setView(part0);
            extendsButton.setViewport(extendsButtonView);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tabs.add("Extends button", extendsButton);



        //Right Click
        rightClick = new JScrollPane();
        rightClick.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        rightClick.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        try
        {
            //Part 0 contains the images
            JPanel part0 = new JPanel();
            part0.setLayout(new BoxLayout(part0, BoxLayout.Y_AXIS));
            JLabel part1 = new JLabel(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/gui/popups/other/ressources/rightClick1.png"))));
            part0.add(part1);

            //Settings up the viewport to display the images
            rightClickView = new JViewport();
            rightClickView.setView(part0);
            rightClick.setViewport(rightClickView);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tabs.add("Edit a graph", rightClick);



        //Launch algorithms
        launchAlgorithms = new JScrollPane();
        launchAlgorithms.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        launchAlgorithms.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        try
        {
            //Part 0 contains the images
            JPanel part0 = new JPanel();
            part0.setLayout(new BoxLayout(part0, BoxLayout.Y_AXIS));
            JLabel part1 = new JLabel(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/gui/popups/other/ressources/launchAlgorithms1.png"))));
            part0.add(part1);

            //Settings up the viewport to display the images
            launchAlgorithmsView = new JViewport();
            launchAlgorithmsView.setView(part0);
            launchAlgorithms.setViewport(launchAlgorithmsView);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tabs.add("Launch algorithms", launchAlgorithms);



        //Settings
        settings = new JScrollPane();
        settings.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        settings.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        try
        {
            //Part 0 contains the images
            JPanel part0 = new JPanel();
            part0.setLayout(new BoxLayout(part0, BoxLayout.Y_AXIS));
            JLabel part1 = new JLabel(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/gui/popups/other/ressources/settings1.png"))));
            part0.add(part1);

            //Settings up the viewport to display the images
            settingsView = new JViewport();
            settingsView.setView(part0);
            settings.setViewport(settingsView);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tabs.add("Settings", settings);



        //Shortcuts
        shortcuts = new JScrollPane();
        shortcuts.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        shortcuts.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        try
        {
            //Part 0 contains the images
            JPanel part0 = new JPanel();
            part0.setLayout(new BoxLayout(part0, BoxLayout.Y_AXIS));
            JLabel part1 = new JLabel(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/gui/popups/other/ressources/shortcuts1.png"))));
            part0.add(part1);

            //Settings up the viewport to display the images
            shortcutsView = new JViewport();
            shortcutsView.setView(part0);
            shortcuts.setViewport(shortcutsView);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tabs.add("Shortcuts", shortcuts);

        add(tabs);
        setVisible(true);
    }
}

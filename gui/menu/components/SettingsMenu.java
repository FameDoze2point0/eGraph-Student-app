package gui.menu.components;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import gui.Gui;
import settings.Settings;

import java.awt.event.*;
import java.awt.*;

public class SettingsMenu extends JMenuItem
{
    public SettingsMenu(Gui gui)
    {
        super("Settings");
        // - Settings (Shortcuts, style(Default,...), Default automaton, Default graph)

        //Settings
        //settings = new JMenuItem("Settings...");
        this.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                gui.setState(3);
                Gui.getSettings().getWindow().setVisible(true);
            }
        });

        this.setMaximumSize(new Dimension(65,20));
        //this.add(settings);

        // //Shortcuts
        // shortcuts = new JMenuItem("Shortcuts...");
        // shortcuts.addActionListener(new ActionListener()
        // {
        //     public void actionPerformed(ActionEvent e)
        //     {
        //         System.out.println("shortcut page");
        //     }
        // });
        // shortcuts.setEnabled(false);
        // this.add(shortcuts);

        // //Default Graph
        // defaultGraph = new JMenuItem("Default graph...");
        // defaultGraph.addActionListener(new ActionListener()
        // {
        //     public void actionPerformed(ActionEvent e)
        //     {
        //         System.out.println("default graph");
        //     }
        // });
        // defaultGraph.setEnabled(false);
        // this.add(defaultGraph);

        // //Default automaton
        // defaultAutomaton = new JMenuItem("Default automaton...");
        // defaultAutomaton.addActionListener(new ActionListener()
        // {
        //     public void actionPerformed(ActionEvent e)
        //     {
        //         System.out.println("default automaton");
        //     }
        // });
        // defaultAutomaton.setEnabled(false);
        // this.add(defaultAutomaton);
    }
}

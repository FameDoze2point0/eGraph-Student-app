package gui.menu.components;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import gui.Gui;
import settings.Settings;

import java.awt.event.*;

public class SettingsMenu extends JMenu
{
    private JMenuItem settings;
    private JMenuItem shortcuts;
    private JMenuItem defaultGraph;
    private JMenuItem defaultAutomaton;

    public SettingsMenu(Gui gui)
    {
        super("Settings");
        // - Settings (Shortcuts, style(Default,...), Default automaton, Default graph)

        //Settings
        settings = new JMenuItem("Settings...");
        settings.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Gui.getSettings().getWindow().setVisible(true);
            }
        });
        this.add(settings);

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

    public JMenuItem getShortcuts() {
        return shortcuts;
    }

    public void setShortcuts(JMenuItem shortcuts) {
        this.shortcuts = shortcuts;
    }

    public JMenuItem getDefaultGraph() {
        return defaultGraph;
    }

    public void setDefaultGraph(JMenuItem defaultGraph) {
        this.defaultGraph = defaultGraph;
    }

    public JMenuItem getDefaultAutomaton() {
        return defaultAutomaton;
    }

    public void setDefaultAutomaton(JMenuItem defaultAutomaton) {
        this.defaultAutomaton = defaultAutomaton;
    }
}

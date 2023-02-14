package gui.menu.components;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.*;

public class Settings extends JMenu
{
    private JMenuItem shortcuts;
    private JMenuItem defaultGraph;
    private JMenuItem defaultAutomaton;

    public Settings()
    {
        super("Settings");
        // - Settings (Shortcuts, style(Default,...), Default automaton, Default graph)

        //Shortcuts
        shortcuts = new JMenuItem("Shortcuts...");
        shortcuts.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("shortcut page");
            }
        });
        this.add(shortcuts);

        //Default Graph
        defaultGraph = new JMenuItem("Default graph...");
        defaultGraph.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("default graph");
            }
        });
        this.add(defaultGraph);

        //Default automaton
        defaultAutomaton = new JMenuItem("Default automaton...");
        defaultAutomaton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("default automaton");
            }
        });
        this.add(defaultAutomaton);
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

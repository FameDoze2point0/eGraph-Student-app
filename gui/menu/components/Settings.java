package gui.menu.components;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.*;

public class Settings extends JMenu
{
    JMenuItem shortcuts;

    JMenu style;
        //style have few sub components
        JMenuItem defaultStyle;
        JMenuItem crossStyle;

    JMenuItem defaultGraph;
    JMenuItem defaultAutomaton;

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

        //style
        style = new JMenu("Style...");

            //Classic look and feel
            defaultStyle = new JMenuItem("Default");
            defaultStyle.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    }
                    catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1)
                    {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            });
            style.add(defaultStyle);

            //Cross look and feel
            crossStyle = new JMenuItem("CrossPlateform");
            crossStyle.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                    }
                    catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1)
                    {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            });
            style.add(crossStyle);
        this.add(style);

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

    public JMenu getStyle() {
        return style;
    }

    public void setStyle(JMenu style) {
        this.style = style;
    }

    public JMenuItem getDefaultStyle() {
        return defaultStyle;
    }

    public void setDefaultStyle(JMenuItem defaultStyle) {
        this.defaultStyle = defaultStyle;
    }

    public JMenuItem getCrossStyle() {
        return crossStyle;
    }

    public void setCrossStyle(JMenuItem crossStyle) {
        this.crossStyle = crossStyle;
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

package gui.menu.components;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.*;

import gui.Gui;
import gui.draw.Draw;
import gui.popups.algorithms.algorithmsPage;;

public class Algorithms extends JMenu
{
    private JMenuItem launch;
    private JMenuItem add;

    public Algorithms(Gui gui, Draw drawArea)
    {
        super("Algorithms");
        // - Algorithms (Launch, Add)

        //Launch
        launch = new JMenuItem("Launch...");
        launch.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                new algorithmsPage(gui, drawArea);
            }
        });
        this.add(launch);

        //Add
        add = new JMenuItem("Add...");
        add.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("add algo");
            }
        });
        this.add(add);
    }

    public JMenuItem getLaunch() {
        return launch;
    }

    public void setLaunch(JMenuItem launch) {
        this.launch = launch;
    }

    public JMenuItem getAdd() {
        return add;
    }

    public void setAdd(JMenuItem add) {
        this.add = add;
    }
}

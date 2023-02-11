package gui.menu.components;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.*;

public class Algorithms extends JMenu
{
    JMenuItem launch;
    JMenuItem add;

    public Algorithms()
    {
        super("Algorithms");
        // - Algorithms (Launch, Add)

        //Launch
        launch = new JMenuItem("Launch...");
        launch.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("launch algo");
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

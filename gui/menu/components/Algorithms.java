package gui.menu.components;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import gui.Gui;
import gui.draw.Draw;
import gui.popups.algorithms.algorithmsPage;;

public class Algorithms extends JMenu
{
    private JMenuItem launch;

    public Algorithms(Gui gui, Draw drawArea)
    {
        super("Algorithms");
        // - Algorithms (Launch, Add)

        //Launch
        launch = new JMenuItem("Launch...", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_usewizards.png"));
        launch.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                new algorithmsPage(gui, drawArea);
            }
        });
        launch.setMnemonic(KeyEvent.VK_A);
        launch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
        this.add(launch);
    }

    public JMenuItem getLaunch() {
        return launch;
    }

    public void setLaunch(JMenuItem launch) {
        this.launch = launch;
    }
}

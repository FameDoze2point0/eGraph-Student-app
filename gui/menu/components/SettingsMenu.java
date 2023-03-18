package gui.menu.components;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import gui.Gui;

public class SettingsMenu extends JMenu
{
    public SettingsMenu(Gui gui)
    {
        super("Settings");
        // - Settings (Shortcuts, style(Default,...), Default automaton, Default graph)

        //Settings
        JMenuItem settings = new JMenuItem("Settings...", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_choosemacro.png"));
        settings.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                gui.setState(3);
                Gui.getSettings().getWindow().setVisible(true);
            }
        });

        this.add(settings);
    }
}

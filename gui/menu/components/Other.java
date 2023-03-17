package gui.menu.components;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.*;
import javax.swing.KeyStroke;

import gui.Gui;
import gui.popups.other.About;
import gui.popups.other.Contacts;
import gui.popups.other.License;

import java.awt.event.KeyEvent;
import java.awt.Event;

public class Other extends JMenu
{
    private JMenuItem documentation;
    private JMenuItem license;
    private JMenuItem contacts;
    private JMenuItem about;

    public Other(Gui gui)
    {
        super("Other");
        // - Other (Documentation, Licence, Contacts, About)

        //Documentation
        documentation = new JMenuItem("Documentation...", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_comparedocuments.png"));
        documentation.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("open doc");
            }
        });
        documentation.setMnemonic(KeyEvent.VK_D);
        documentation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, Event.CTRL_MASK));

        this.add(documentation);

        //License
        license = new JMenuItem("License...", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_editheaderandfooter.png"));
        license.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                new License(gui);
            }
        });
        license.setMnemonic(KeyEvent.VK_L);
        license.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK));

        this.add(license);

        //Contacts
        contacts = new JMenuItem("Contacts...", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_drawcaption.png"));
        contacts.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                new Contacts(gui);
            }
        });
        contacts.setMnemonic(KeyEvent.VK_C);
        contacts.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
        this.add(contacts);

        //About...
        about = new JMenuItem("About...", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_extendedhelp.png"));
        about.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                new About(gui);
            }
        });
        this.add(about);
    }
}

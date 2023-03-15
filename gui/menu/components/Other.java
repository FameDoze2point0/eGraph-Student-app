package gui.menu.components;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.*;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.Event;

public class Other extends JMenu
{
    private JMenuItem documentation;
    private JMenuItem licence;
    private JMenuItem contacts;
    private JMenuItem about;

    public Other()
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

        //Licence
        licence = new JMenuItem("Licence...", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_editheaderandfooter.png"));
        licence.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("open licence");
            }
        });
        licence.setMnemonic(KeyEvent.VK_L);
        licence.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK));

        this.add(licence);

        //Contacts
        contacts = new JMenuItem("Contacts...", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_drawcaption.png"));
        contacts.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("open contatcs");
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
                System.out.println("about");
            }
        });
        this.add(about);
    }
}

package gui.menu.components;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.*;

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
        documentation = new JMenuItem("Documentation...");
        documentation.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("open doc");
            }
        });
        documentation.setEnabled(false);
        this.add(documentation);

        //Licence
        licence = new JMenuItem("Licence...");
        licence.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("open licence");
            }
        });
        licence.setEnabled(false);
        this.add(licence);

        //Contacts
        contacts = new JMenuItem("Contacts...");
        contacts.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("open contatcs");
            }
        });
        contacts.setEnabled(false);
        this.add(contacts);

        //About...
        about = new JMenuItem("About...");
        about.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("about");
            }
        });
        about.setEnabled(false);
        this.add(about);
    }
}

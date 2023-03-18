package gui.popups.other;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import gui.Gui;

public class Contacts extends JDialog
{
    public Contacts(Gui gui)
    {
        super(gui, "Contacts",true);
        this.setMinimumSize(new Dimension(370,165));
        this.setSize(new Dimension(370,165));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel contacts = new JLabel("<html><p>This project has been made by <b>Antonin C.</b> and <b> François P</b>.<br>If you have any question, feel free to ask us at : <ul><li>antonin60120@gmail.com</li><li>fpicard.info@gmail.com</li></ul><br>Thanks you for using eGraph STUDENT.</html>");

        //To quit the jdialog when pressing echap
        AbstractAction quitAction = new AbstractAction("Cancel") {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();                
            }
        };
        quitAction.putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        contacts.getActionMap().put("quitContacts", quitAction);
        contacts.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke)quitAction.getValue(AbstractAction.ACCELERATOR_KEY), "quitContacts");

        contacts.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Contacts"));
        add(contacts);
        this.setVisible(true);
    }
}

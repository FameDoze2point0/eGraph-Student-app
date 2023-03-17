package gui.popups.other;

import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.Gui;

public class Contacts extends JDialog
{
    public Contacts(Gui gui)
    {
        super(gui, "Contacts",true);
        this.setMinimumSize(new Dimension(370,165));
        this.setSize(new Dimension(370,165));
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel contacts = new JLabel("<html><p>This project has been made by <b>Antonin C.</b> and <b> François P</b>.<br>If you have any question, feel free to ask us at : <ul><li>antonin60120@gmail.com</li><li>fpicard.info@gmail.com</li></ul><br>Thanks you for using eGraph STUDENT.</html>");
        contacts.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Contacts"));
        add(contacts);
        this.setVisible(true);
    }
}

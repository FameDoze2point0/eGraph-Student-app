package gui.menu.components;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import gui.Gui;
import gui.draw.PanelPaint;

import java.awt.event.*;

public class Edit extends JMenu
{
    private JMenuItem undo;
    private JMenuItem redo;

    public Edit(Gui gui)
    {
        super("Edit");
        // - Edit (Undo, Redo, Cut, Copy, Paste)

        //Undo
        undo = new JMenuItem("Undo", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_undo.png"));
        undo.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                PanelPaint pp = (PanelPaint)gui.getDraw().getSelectedComponent();
                pp.undo();
            }
        });
        this.add(undo);

        //Redo
        redo = new JMenuItem("Redo", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_redo.png"));
        redo.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                PanelPaint pp = (PanelPaint)gui.getDraw().getSelectedComponent();
                pp.redo();
            }
        });
        this.add(redo);
    }

    public JMenuItem getUndo() {
        return undo;
    }

    public void setUndo(JMenuItem undo) {
        this.undo = undo;
    }

    public JMenuItem getRedo() {
        return redo;
    }

    public void setRedo(JMenuItem redo) {
        this.redo = redo;
    }
}

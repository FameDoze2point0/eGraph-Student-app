package gui.menu.components;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.*;

public class Edit extends JMenu
{
    private JMenuItem undo;
    private JMenuItem redo;
    private JMenuItem cut;
    private JMenuItem copy;
    private JMenuItem paste;

    public Edit()
    {
        super("Edit");
        // - Edit (Undo, Redo, Cut, Copy, Paste)

        //Undo
        undo = new JMenuItem("Undo");
        undo.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("undo");
            }
        });
        undo.setEnabled(false);
        this.add(undo);

        //Redo
        redo = new JMenuItem("Redo");
        redo.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("redo");
            }
        });
        redo.setEnabled(false);
        this.add(redo);

        //Cut
        cut = new JMenuItem("Cut");
        cut.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("cut");
            }
        });
        cut.setEnabled(false);
        this.add(cut);

        //Copy
        copy = new JMenuItem("Copy");
        copy.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("copy");
            }
        });
        copy.setEnabled(false);
        this.add(copy);

        //Paste
        paste = new JMenuItem("Paste");
        cut.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("paste");
            }
        });
        paste.setEnabled(false);
        this.add(paste);
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

    public JMenuItem getCut() {
        return cut;
    }

    public void setCut(JMenuItem cut) {
        this.cut = cut;
    }

    public JMenuItem getCopy() {
        return copy;
    }

    public void setCopy(JMenuItem copy) {
        this.copy = copy;
    }

    public JMenuItem getPaste() {
        return paste;
    }

    public void setPaste(JMenuItem paste) {
        this.paste = paste;
    }
}

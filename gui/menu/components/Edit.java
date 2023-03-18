package gui.menu.components;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import gui.Gui;
import gui.draw.Draw;
import gui.draw.PanelPaint;
import util.Graph;

public class Edit extends JMenu
{
    private JMenuItem undo;
    private JMenuItem redo;
    private JMenuItem clear;

    public Edit(Gui gui, Draw drawArea)
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
        undo.setEnabled(false);
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
        redo.setEnabled(false);
        this.add(redo);

        //clear
        clear = new JMenuItem("Clear", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_clickchangerotation.png"));
        clear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                //We clear the current graph (if opened)
                Graph current = gui.getTabulations().get(drawArea.getSelectedComponent());
                current.getEdges().clear();
                current.getVertices().clear();
                current.getExistingEdge().clear();
                current.setCpt(0);
                current.getPanelPaint().repaint();
            }
        });
        clear.setEnabled(false);
        this.add(clear);
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

    public JMenuItem getClear() {
        return clear;
    }

    public void setClear(JMenuItem clear) {
        this.clear = clear;
    }
}

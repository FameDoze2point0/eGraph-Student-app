package gui.tools;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JToolBar;

import gui.Gui;
import gui.draw.Draw;
import gui.popups.newElement.NewElement;
import util.Graph;

public class Tools extends JToolBar
{
    //New graph/automaton
    JButton newElement;

    //Add elements to a graph/automaton
    JButton cursor;
    JButton newVertex;
    JButton newEdge;
    JButton addWeight;

    public Tools(Gui gui, Draw drawArea)
    {
        super("Tools", JToolBar.HORIZONTAL);

        //Create Graph
        newElement = new JButton("New...");
        newElement.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewElement(gui, drawArea);
            }
        });
        this.add(newElement);

        //Adding a separator
        this.add(new JToolBar.Separator());

        //Use cursor
        cursor = new JButton("Cursor");
        cursor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Programme enter state 0
                gui.setState(0);
            }
        });
        this.add(cursor);

        // Action cancelAction = new AbstractAction("Cancel") {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         dispose();
        //     }
        // };
        // cancelAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        // cancel = new JButton(cancelAction);
        // cancel.getActionMap().put("cancelNew", cancelAction);
        // cancel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke)cancelAction.getValue(Action.ACCELERATOR_KEY), "cancelNew");



        //New Vertex/State
        newVertex = new JButton("Vertex +");
        newVertex.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Programme enter state 1
                gui.setState(1);
            }
        });
        this.add(newVertex);

        //New edge
        newEdge = new JButton("Edge +");
        newEdge.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Programme enter state 2
                gui.setState(2);
            }
        });
        this.add(newEdge);
    }

    public JButton getNewVertex() {
        return newVertex;
    }


    public void setNewVertex(JButton newVertex) {
        this.newVertex = newVertex;
    }


    public JButton getNewEdge() {
        return newEdge;
    }


    public void setNewEdge(JButton newEdge) {
        this.newEdge = newEdge;
    }


    public JButton getAddWeight() {
        return addWeight;
    }


    public void setAddWeight(JButton addWeight) {
        this.addWeight = addWeight;
    }

    public JButton getNewElement() {
        return newElement;
    }

    public void setNewElement(JButton newElement) {
        this.newElement = newElement;
    }
}

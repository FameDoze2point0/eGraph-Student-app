package gui.tools;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JToolBar;

import gui.Gui;
import gui.draw.Draw;
import gui.draw.PanelPaint;
import gui.popups.algorithms.AlgorithmsPage;
import gui.popups.newElement.NewElement;
import util.Graph;

public class Tools extends JToolBar
{
    //New graph/automaton
    JButton newElement;
    JButton newElementMatrix;

    //Add elements to a graph/automaton
    JButton cursor;
    JButton newVertex;
    JButton newEdge;
    JButton addWeight;
    JButton undo;
    JButton redo;
    JButton save;
    JButton load;
    JButton settings;
    JButton close;
    JButton launchAlgo;

    public Tools(Gui gui, Draw drawArea)
    {
        super("Tools", JToolBar.HORIZONTAL);

        //Create Graph
        newElement = new JButton("New Graph", new ImageIcon(System.getProperty("user.dir")+"/ressources/lc_bezierinsert.png"));
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
        cursor = new JButton("Cursor", new ImageIcon(System.getProperty("user.dir")+"/ressources/lc_drawselect.png"));
        cursor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Programme enter state 0
                gui.setState(0);
            }
        });
        this.add(cursor);

        //New Vertex/State
        newVertex = new JButton("New Vertex", new ImageIcon(System.getProperty("user.dir")+"/ressources/lc_sphere.png"));
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
        newEdge = new JButton("New Edge", new ImageIcon(System.getProperty("user.dir")+"/ressources/lc_connectorlinescircles.png"));
        newEdge.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Programme enter state 2
                gui.setState(2);
            }
        });
        this.add(newEdge);

        //Adding a separator
        this.add(new JToolBar.Separator());

        //undo
        undo = new JButton("Undo", new ImageIcon(System.getProperty("user.dir")+"/ressources/lc_redo.png"));
        undo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelPaint pp = (PanelPaint)gui.getDraw().getSelectedComponent();
                pp.undo();
            }
        });
        undo.setEnabled(false);
        this.add(undo);

        //redo
        redo = new JButton("Redo", new ImageIcon(System.getProperty("user.dir")+"/ressources/lc_undo.png"));
        redo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                PanelPaint pp = (PanelPaint)gui.getDraw().getSelectedComponent();
                pp.redo();
            }
        });
        redo.setEnabled(false);
        this.add(redo);

        //Adding a separator
        this.add(new JToolBar.Separator());

        //save
        save = new JButton("Save...", new ImageIcon(System.getProperty("user.dir")+"/ressources/lc_recsave.png"));
        save.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawArea.saveTabulation(gui);
            }
        });
        this.add(save);

        //load
        load = new JButton("Load...", new ImageIcon(System.getProperty("user.dir")+"/ressources/lc_insertmasterpage.png"));
        load.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawArea.loadTabulation(gui);
            }
        });
        this.add(load);

        //Adding a separator
        this.add(new JToolBar.Separator());

        //Launch algorithms
        launchAlgo = new JButton("Launch algorithm...", new ImageIcon(System.getProperty("user.dir")+"/ressources/lc_usewizards.png"));
        launchAlgo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AlgorithmsPage(gui, drawArea);
            }
            
        });
        this.add(launchAlgo);

        //settings
        settings = new JButton("Settings...", new ImageIcon(System.getProperty("user.dir")+"/ressources/lc_choosemacro.png"));
        settings.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.setState(3);
                Gui.getSettings().getWindow().setVisible(true);
            }
        });
        this.add(settings);

        //close tab
        close = new JButton("Close Tabulation", new ImageIcon(System.getProperty("user.dir")+"/ressources/lc_dbformdelete.png"));
        close.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (drawArea.getTabCount() > 1) {
                    
                    
                    gui.getTabulations().remove(drawArea.getSelectedComponent());
                    drawArea.remove(drawArea.getSelectedIndex());
                    drawArea.setSelectedIndex(0); 
                }
            }
        });
        this.add(close);
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

    public JButton getUndo()
    {
        return undo;
    }

    public JButton getRedo()
    {
        return redo;
    }
}

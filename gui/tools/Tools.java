package gui.tools;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import gui.Gui;
import gui.draw.Draw;
import gui.draw.PanelPaint;
import gui.popups.algorithms.AlgorithmsPage;
import gui.popups.newElement.NewElement;
import util.Graph;

public class Tools extends JToolBar
{
    //New graph/automaton
    private JButton newElement;

    //Add elements to a graph/automaton
    private JButton cursor;
    private JButton newVertex;
    private JButton newEdge;
    private JButton undo;
    private JButton redo;
    private JButton save;
    private JButton load;
    private JButton settings;
    private JButton close;
    private JButton launchAlgo;
    private JButton clear;

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


        AbstractAction setCursor = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.setState(0);
                cursor.setBackground(Color.LIGHT_GRAY);
                newVertex.setBackground(null);
                newEdge.setBackground(null);
            }
        };
        setCursor.putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        cursor = new JButton(setCursor);
        cursor.setText("Cursor");
        cursor.setIcon(new ImageIcon(System.getProperty("user.dir")+"/ressources/lc_drawselect.png"));
        cursor.getActionMap().put("setCursor", setCursor);
        cursor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke)setCursor.getValue(AbstractAction.ACCELERATOR_KEY), "setCursor");
        cursor.setEnabled(false);

        this.add(cursor);

        //New Vertex/State
        newVertex = new JButton("New Vertex", new ImageIcon(System.getProperty("user.dir")+"/ressources/lc_sphere.png"));
        newVertex.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Programme enter state 1
                gui.setState(1);
                cursor.setBackground(null);
                newVertex.setBackground(Color.LIGHT_GRAY);
                newEdge.setBackground(null);
            }
        });
        newVertex.setEnabled(false);
        this.add(newVertex);

        //New edge
        newEdge = new JButton("New Edge", new ImageIcon(System.getProperty("user.dir")+"/ressources/lc_connectorlinescircles.png"));
        newEdge.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Programme enter state 2
                gui.setState(2);
                cursor.setBackground(null);
                newVertex.setBackground(null);
                newEdge.setBackground(Color.LIGHT_GRAY);
            }
        });
        newEdge.setEnabled(false);
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
        launchAlgo.setEnabled(false);
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

        //Adding a separator
        this.add(new JToolBar.Separator());

        //clear
        clear = new JButton("Clear", new ImageIcon(System.getProperty("user.dir")+"/ressources/lc_clickchangerotation.png"));
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

                    //If less than 2 tab, disable close a tabulation
                    if(drawArea.getTabCount() < 2)
                    {
                        close.setEnabled(false);
                        gui.getMenu().getFile().getCloseTabulation().setEnabled(false);
                        
                        for(PanelPaint pp : gui.getTabulations().keySet())
                        {
                            pp.getRightClickMenu().getClose().setEnabled(false);
                        }
                    }
                }
            }
        });
        close.setEnabled(false);
        this.add(close);
    }

    public JButton getCursorButton() {
        return cursor;
    }

    public JButton getNewVertex() {
        return newVertex;
    }

    public JButton getNewEdge() {
        return newEdge;
    }

    public JButton getLaunchAlgo() {
        return launchAlgo;
    }

    public JButton getClear() {
        return clear;
    }

    public JButton getClose() {
        return close;
    }

    public JButton getUndo() {
        return undo;
    }

    public JButton getRedo() {
        return redo;
    }

}
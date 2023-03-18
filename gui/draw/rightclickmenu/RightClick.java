package gui.draw.rightclickmenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import gui.Gui;
import gui.draw.Draw;
import gui.draw.PanelPaint;
import gui.popups.algorithms.AlgorithmsPage;
import gui.popups.newElement.NewElement;
import gui.popups.textInput.TextInput;
import util.Edge;
import util.Graph;
import util.Vertex;

public class RightClick extends JPopupMenu
{

    private Object rightClickedOnElement; //Element that got right clicked on and will be affected by right click option

    //When right click on an element
    private JMenuItem deleteElement;

    //Graph elements
    private JMenuItem renameElement;

    //Edge elements
    private JMenuItem changeWeight;

    //Always available elements
    private JMenuItem undo;
    private JMenuItem redo;
    private JMenu mode;
        private JMenuItem cursorMode;
        private JMenuItem newVertexMode;
        private JMenuItem newEdgeMode;
    private JMenuItem newElement;
    private JMenuItem openElement;
    private JMenuItem saveElement;
    private JMenu export;
        private JMenuItem exportPDF;
        private JMenuItem exportSVG;
    private JMenuItem launchAlgo;
    private JMenuItem clear;
    private JMenuItem close;
    
    public RightClick(Draw drawArea, PanelPaint panel, Gui gui)
    {
        super("Quick menu");
        //We first elements for edges and vertexs
        deleteElement = new JMenuItem("Delete", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_cancel.png"));
        deleteElement.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //We either delete the selectionned vertex/edge
                Graph graph = gui.getTabulations().get(drawArea.getSelectedComponent());

                if(rightClickedOnElement instanceof Vertex)
                {
                    Vertex toDelete = (Vertex)rightClickedOnElement;
                    ArrayList<Edge> edgeToDelete = new ArrayList<Edge>();
                    //We remove all the edges, then the vertex
                    for(Edge edge : graph.getEdges())
                    {
                        if(edge.getStart().equals(toDelete) || edge.getEnd().equals(toDelete))
                        {
                            edgeToDelete.add(edge);
                        }
                    }
                    for (Edge edge : edgeToDelete) {
                        graph.removeEdge(edge);
                    }
                    //We then remove the vertex
                    graph.getVertices().remove(toDelete);
                }

                //Removing edge
                else if(rightClickedOnElement instanceof Edge)
                {
                    //We remove the edge
                    Edge edgeTemp = (Edge)rightClickedOnElement;
                    if (!graph.getOriented()) {
                        for (Edge edge : graph.getEdges()) {
                            if (edge.getEnd().equals(edgeTemp.getStart()) && edge.getStart().equals(edgeTemp.getEnd())) {
                                graph.removeEdge(edge);
                                break;
                            }
                        }
                    }
                    graph.removeEdge(edgeTemp);
                }

                //We the element is deleted, we redraw the area
                panel.repaint();
            }
        });
        this.add(deleteElement);
        //We then build vertex's elements
        renameElement = new JMenuItem("Rename...", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_drawtext.png"));
        renameElement.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TextInput(gui, rightClickedOnElement);
            }    
        });
        this.add(renameElement);
        //We then build edge's elements
        changeWeight = new JMenuItem("Edit weight...", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_fontwork.png"));
        changeWeight.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TextInput(gui, rightClickedOnElement);
            }    
        });
        this.add(changeWeight);

        this.add(new Separator()); // === NEW CATEGORY ===

        //We then add elements that are always there
        undo = new JMenuItem("Undo", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_undo.png"));
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

        redo = new JMenuItem("Redo", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_redo.png"));
        redo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelPaint pp = (PanelPaint)gui.getDraw().getSelectedComponent();
                pp.redo();
            }
        });
        redo.setEnabled(false);
        this.add(redo);

        // === NEW CATEGORY ===
        this.add(new Separator());

        mode = new JMenu("Mode");
            cursorMode = new JMenuItem("Cursor Mode", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_drawselect.png"));
            cursorMode.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gui.setState(0);
                }
            });
            mode.add(cursorMode);

            newVertexMode = new JMenuItem("New Vertex Mode", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_sphere.png"));
            newVertexMode.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gui.setState(1);
                }
            });
            mode.add(newVertexMode);

            newEdgeMode = new JMenuItem("New Edge Mode", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_connectorlinescircles.png"));
            newEdgeMode.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gui.setState(2);
                }
            });
            mode.add(newEdgeMode);
        this.add(mode);

        this.add(new Separator()); // === NEW CATEGORY ===

        newElement = new JMenuItem("New...", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_bezierinsert.png"));
        newElement.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                new NewElement(gui, drawArea);
            }
        });
        this.add(newElement);

        openElement = new JMenuItem("Open...", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_insertmasterpage.png"));
        openElement.addActionListener(new ActionListener()
        {

			@Override
			public void actionPerformed(ActionEvent e) {
				drawArea.loadTabulation(gui);
			}
            
        });
        this.add(openElement);

        saveElement = new JMenuItem("Save...", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_recsave.png"));
        saveElement.addActionListener(new ActionListener()
        {

			@Override
			public void actionPerformed(ActionEvent e) {
				drawArea.saveTabulation(gui);
			}
            
        });
        this.add(saveElement);

        this.add(new Separator()); // === NEW CATEGORY ===

        export = new JMenu("Export");
            exportPDF = new JMenuItem("PDF...");
            export.add(exportPDF);
            exportSVG = new JMenuItem("SVG...");
            export.add(exportSVG);
        export.setEnabled(false);
        this.add(export);

        this.add(new Separator()); // === NEW CATEGORY ===

        launchAlgo = new JMenuItem("Launch algorithm...", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_usewizards.png"));
        launchAlgo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AlgorithmsPage(gui, drawArea);
            }
            
        });
        this.add(launchAlgo);

        this.add(new Separator()); // === NEW CATEGORY ===

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
        this.add(clear);

        //close tab
        close = new JMenuItem("Close Tabulation", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_dbformdelete.png"));
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
                        gui.getTools().getClose().setEnabled(false);
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


	public void changeState(Boolean changeWeight, Boolean renameElement, Boolean deleteElement, Object itemSelected){
        this.changeWeight.setVisible(changeWeight);
        this.renameElement.setVisible(renameElement);
        this.deleteElement.setVisible(deleteElement);
        rightClickedOnElement = itemSelected;
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


    public JMenuItem getClose() {
        return close;
    }

    
}
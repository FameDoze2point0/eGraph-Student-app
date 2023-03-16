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

    Object rightClickedOnElement; //Element that got right clicked on and will be affected by right click option

    //When right click on an element
    JMenuItem deleteElement;

    //Graph elements
    JMenuItem renameElement;

    //Edge elements
    JMenuItem changeWeight;

    //Always available elements
    JMenuItem undo;
    JMenuItem redo;
    JMenu mode;
        JMenuItem cursorMode;
        JMenuItem newVertexMode;
        JMenuItem newEdgeMode;
    JMenuItem newElement;
    JMenuItem openElement;
    JMenuItem saveElement;
    JMenu export;
        JMenuItem exportPDF;
        JMenuItem exportSVG;
    JMenuItem launchAlgo;
    JMenuItem close;
    
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
        undo = new JMenuItem("Undo", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_redo.png"));
        undo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelPaint pp = (PanelPaint)gui.getDraw().getSelectedComponent();
                pp.undo();
            }
        });
        this.add(undo);

        redo = new JMenuItem("Redo", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_undo.png"));
        redo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelPaint pp = (PanelPaint)gui.getDraw().getSelectedComponent();
                pp.redo();
            }
        });
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
                }
            }
        });
        this.add(close);
    }

    public void changeState(Boolean changeWeight, Boolean renameElement, Boolean deleteElement, Object itemSelected){
        this.changeWeight.setVisible(changeWeight);
        this.renameElement.setVisible(renameElement);
        this.deleteElement.setVisible(deleteElement);
        rightClickedOnElement = itemSelected;
    }
    public JMenuItem getDeleteElement() {
        return deleteElement;
    }

    public void setDeleteElement(JMenuItem deleteElement) {
        this.deleteElement = deleteElement;
    }

    public JMenuItem getRenameElement() {
        return renameElement;
    }

    public void setRenameElement(JMenuItem renameElement) {
        this.renameElement = renameElement;
    }

    public JMenuItem getChangeWeight() {
        return changeWeight;
    }

    public void setChangeWeight(JMenuItem changeWeight) {
        this.changeWeight = changeWeight;
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

    public JMenu getMode() {
        return mode;
    }

    public void setMode(JMenu mode) {
        this.mode = mode;
    }

    public JMenuItem getNewEdgeMode() {
        return newEdgeMode;
    }

    public void setNewEdgeMode(JMenuItem newEdgeMode) {
        this.newEdgeMode = newEdgeMode;
    }

    public JMenuItem getNewVertexMode() {
        return newVertexMode;
    }

    public void setNewVertexMode(JMenuItem newVertexMode) {
        this.newVertexMode = newVertexMode;
    }

    public JMenuItem getNewElement() {
        return newElement;
    }

    public void setNewElement(JMenuItem newElement) {
        this.newElement = newElement;
    }

    public JMenuItem getOpenElement() {
        return openElement;
    }

    public void setOpenElement(JMenuItem openElement) {
        this.openElement = openElement;
    }

    public JMenuItem getSaveElement() {
        return saveElement;
    }

    public void setSaveElement(JMenuItem saveElement) {
        this.saveElement = saveElement;
    }

    public JMenu getExport() {
        return export;
    }

    public void setExport(JMenu export) {
        this.export = export;
    }

    public JMenuItem getExportPDF() {
        return exportPDF;
    }

    public void setExportPDF(JMenuItem exportPDF) {
        this.exportPDF = exportPDF;
    }

    public JMenuItem getExportSVG() {
        return exportSVG;
    }

    public void setExportSVG(JMenuItem exportSVG) {
        this.exportSVG = exportSVG;
    }

    public JMenuItem getLaunchAlgo() {
        return launchAlgo;
    }

    public void setLaunchAlgo(JMenuItem launchAlgo) {
        this.launchAlgo = launchAlgo;
    }

    public Object getRightClickedOnElement() {
        return rightClickedOnElement;
    }

    public void setRightClickedOnElement(Object rightClickedOnElement) {
        this.rightClickedOnElement = rightClickedOnElement;
    }

    public JMenuItem getCursorMode() {
        return cursorMode;
    }

    public void setCursorMode(JMenuItem cursorMode) {
        this.cursorMode = cursorMode;
    }   

    
}

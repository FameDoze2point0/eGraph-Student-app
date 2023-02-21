package gui.draw.rightclickmenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import gui.Gui;
import gui.draw.Draw;
import gui.draw.PanelPaint;
import gui.popups.textInput.TextInput;
import util.Edge;
import util.Graph;
import util.Vertex;

public class RightClick extends JPopupMenu
{
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
    
    public RightClick(Draw drawArea, PanelPaint panel, Gui gui)
    {
        super("Quick menu");
        //We first elements for edges and vertexs
        deleteElement = new JMenuItem("Delete");
        deleteElement.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //We either delete the selectionned vertex/edge
                Graph graph = gui.getTabulations().get(drawArea.getSelectedComponent());

                if(panel.getRightClickedOnElement() instanceof Vertex)
                {
                    Vertex toDelete = (Vertex)panel.getRightClickedOnElement();
                    //We remove all the edges, then the vertex
                    for(Edge edge : graph.getEdges())
                    {
                        if(edge.getStart().equals(toDelete) || edge.getEnd().equals(toDelete))
                        {
                            graph.removeEdge(edge);
                        }
                    }

                    //We then remove the vertex
                    graph.getVertices().remove(toDelete);
                }

                //Removing edge
                else if(panel.getRightClickedOnElement() instanceof Edge)
                {
                    //We remove the edge
                    Edge edgeTemp = (Edge)panel.getRightClickedOnElement();
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

                //No more "right click element"
                panel.setRightClickedOnElement(null);

                //We the element is deleted, we redraw the area
                panel.repaint();
            }
        });
        this.add(deleteElement);
        //We then build vertex's elements
        renameElement = new JMenuItem("Rename...");
        renameElement.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TextInput(gui, panel.getRightClickedOnElement());
            }    
        });
        this.add(renameElement);
        //We then build edge's elements
        changeWeight = new JMenuItem("Edit weight...");
        changeWeight.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TextInput(gui, panel.getRightClickedOnElement());
            }    
        });
        this.add(changeWeight);

        this.add(new Separator()); // === NEW CATEGORY ===

        //We then add elements that are always there
        undo = new JMenuItem("Undo");
        this.add(undo);
        redo = new JMenuItem("Redo");
        this.add(redo);

        this.add(new Separator()); // === NEW CATEGORY ===

        mode = new JMenu("Mode");
            cursorMode = new JMenuItem("Cursor Mode");
            mode.add(cursorMode);
            newVertexMode = new JMenuItem("New Vertex Mode");
            mode.add(newVertexMode);
            newEdgeMode = new JMenuItem("New Edge Mode");
            mode.add(newEdgeMode);
        this.add(mode);

        this.add(new Separator()); // === NEW CATEGORY ===

        newElement = new JMenuItem("New...");
        this.add(newElement);
        openElement = new JMenuItem("Open...");
        this.add(openElement);
        saveElement = new JMenuItem("Save...");
        this.add(saveElement);

        this.add(new Separator()); // === NEW CATEGORY ===

        export = new JMenu("Export");
            exportPDF = new JMenuItem("PDF...");
            export.add(exportPDF);
            exportSVG = new JMenuItem("SVG...");
            export.add(exportSVG);
        this.add(export);

        this.add(new Separator()); // === NEW CATEGORY ===

        launchAlgo = new JMenuItem("Launch algorithm...");
        this.add(launchAlgo);
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
}
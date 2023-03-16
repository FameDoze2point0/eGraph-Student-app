package gui.draw;
import java.awt.event.*;
import java.util.Stack;

import javax.swing.JPanel;
import gui.Gui;
import gui.draw.rightclickmenu.RightClick;
import gui.popups.newElement.AskWeight;
import settings.Settings;
import settings.SettingsDialog;
import util.Edge;
import util.Graph;
import util.Vertex;
import java.awt.*;

public class PanelPaint extends JPanel implements MouseListener, MouseMotionListener
{
    private transient Gui gui;
    private transient Draw drawArea;

    //When we add an edge, if the starting point is already selected
    private Vertex start = null; //If not selected = null

    private int X;
    private int Y;
    private Vertex isDragged = null; //Vertex that is being mouse dragged

    // //Menu when we right click
    private RightClick rightClickMenu;

    //Stack to do the undo/redo
    Stack<Graph> undo;
    Stack<Graph> redo;


    public RightClick getRightClickMenu() {
        return rightClickMenu;
    }

    public void setRightClickMenu(RightClick rightClickMenu) {
        this.rightClickMenu = rightClickMenu;
    }

    public PanelPaint(Gui gui, Draw drawArea)
    {
        super();
        //Retreiving the gui and the draw area
        this.gui = gui;
        this.drawArea = drawArea;

        //MouseListener
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //Right click menu
        rightClickMenu = new RightClick(drawArea, this, gui);
        this.add(rightClickMenu);

        undo = new Stack<Graph>();
        redo = new Stack<Graph>();
    }

    public void paint(Graphics graphics)
    {
        super.paint(graphics); //Refresh the JPanel with blank space
        Graph graph;
        if(gui.getState() == 3)
        {
            graph = SettingsDialog.getExample_graph();
        }
        else
        {
            graph = gui.getTabulations().get(drawArea.getSelectedComponent());
        }

        if (graph != null) {
            Object itemCollision = graph.vertexCollision(X, Y);
            if (itemCollision == null) {
                itemCollision = graph.edgeCollision( X, Y);
            }
            graph.paint(graphics, itemCollision);    
        }

        /// === FINALLY, IF WE ARE IN STATE 1 OR 2, SHOW A PREVIEW OF THE NEW EDGE/VERTEX ===
        //If we are in state 1, we previzualize the new vertex
        if(gui.getState() == 1)
        {
            int radius = Gui.getSettings().getVertexDiameter()/2;
            int diameter = Gui.getSettings().getVertexDiameter();
            //We draw a vertex on top of the mouse with 50% opacity
            //Setting 50% opacity
            ((Graphics2D)graphics).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.35f));
            //Borders
            graphics.setColor(Gui.getSettings().getVertexOutsideColor());
            graphics.fillOval(X-radius, Y-radius, diameter, diameter);

            //Inside
            graphics.setColor(Gui.getSettings().getVertexInsideColor());
            graphics.fillOval((X+((int)(radius*0.2)))-(radius), (Y+((int)(radius*0.2)))-(radius), (int)(diameter*0.8), (int)(diameter*0.8));
        }

        //If we are in state 2 and the first vertex is selectionned, we previzualize the new edge (if first vertex selectionned)
        else if(gui.getState() == 2 && start != null)
        {
            //Setting 50% opacity
            ((Graphics2D)graphics).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.35f));
            //Drawing the line
            graphics.setColor(Gui.getSettings().getEdgeStrokeColor());
            ((Graphics2D)graphics).setStroke(new BasicStroke(Gui.getSettings().getEdgeStrokeWidth())); //Change stroke
            graphics.drawLine(start.getCoordX()+Gui.getSettings().getVertexDiameter()/2, start.getCoordY()+Gui.getSettings().getVertexDiameter()/2, X, Y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        Graph graph = gui.getTabulations().get(drawArea.getSelectedComponent());
        Vertex vertex;
        Edge edge;
        int radius = Gui.getSettings().getVertexDiameter()/2;
        //=== LEFT CLICK DETECTION === to create new elements and interact
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            switch(gui.getState())
            {
                case(1): {
                    //In state 1, when the mouse is clicked, we create a new vertex/state
                    //We save for undo
                    updateUndo();
                    
                    //We retrieve the actual graph/automaton
                    int cpt = graph.getCpt();
                    graph.addVertex(new Vertex(cpt,e.getX() - radius, e.getY() - radius, Gui.getSettings().getVertexDiameter(), Gui.getSettings().getVertexStrokeWidth(), Gui.getSettings().getVertexInsideColor(), Gui.getSettings().getVertexOutsideColor(), ""+(cpt), Gui.getSettings().getVertexNameColor())); //We add the new vertex to the graph
                    break;
                }

                case(2): {
                    //In state 2, we want the user to click on 2 differents vertex/state to create an edge
                    //We first look if the user clicked on a vertex
                    if((vertex = graph.vertexCollision(X,Y)) != null)
                    {
                        //The user clicked on this vertex
                        //If the start vertex is != null, then we have both vertex required to draw the edge
                        if(start == null)
                        {
                            start = vertex;
                        }
                        else
                        {
                            //Saving the graph before we add the edge
                            updateUndo();
                            Integer weight = null;
                            if (graph.getWeighted()) {
                                AskWeight askWeightPage = new AskWeight(gui, drawArea);
                                weight = askWeightPage.getWeight();
                            }
                            graph.addEdge(new Edge(start, vertex, weight, Gui.getSettings().getEdgeStrokeWidth(), Gui.getSettings().getEdgeStrokeColor(), Gui.getSettings().getEdgeHighlightColor(), Gui.getSettings().getEdgeArrowTipColor(), Gui.getSettings().getEdgeWeightColor(), Gui.getSettings().getEdgeWeightBorderColor(), graph,Gui.getSettings().getArrowLength()));
                            start = null;
                        }
                    }
                    break;
                }

                default: {
                    break;
                }
            }
            this.repaint();
        }



        // === RIGHT CLICK DETECTION === to open up the quick interaction menu
        else if(e.getButton() == MouseEvent.BUTTON3 && gui.getState() != 3)
        {
            //When we right click, we detect if we are on top of a vertex or an edge to add some quick interactions to them
            if((vertex = graph.vertexCollision(e.getX(), e.getY())) != null)
            {

                rightClickMenu.changeState(false, true, true, vertex);
            }
            else if((edge = graph.edgeCollision(e.getX(), e.getY())) != null)
            {
                rightClickMenu.changeState(true, false, true, edge);
            }
            else
            {
                rightClickMenu.changeState(false, false, false, null);
            }
            //We then have some default features, always there
            rightClickMenu.show(drawArea.getSelectedComponent() , e.getX(), e.getY()); 
        }
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if(isDragged != null)
        {
            isDragged.setCoordX(e.getX()-isDragged.getDiameter()/2);
            isDragged.setCoordY(e.getY()-isDragged.getDiameter()/2);   
            //We must reset every path
            Graph graph = gui.getTabulations().get(drawArea.getSelectedComponent());
            for(Edge edge : graph.getEdges())
            {
                edge.setCollisionArea(edge.refreshCollisionArea(graph.getOriented(), graph.bothDirections(edge)));
            }

            repaint();
        }
    }

    @Override //Is used to register the vertex we click on (to drag it)
    public void mousePressed(MouseEvent e)
    {
        //If we are on top of a vertex, we drag it
        Graph graph = gui.getTabulations().get(drawArea.getSelectedComponent());
        Vertex vertex;
        if((vertex = graph.vertexCollision(X,Y)) != null)
        {
            isDragged = vertex;
        }
    }

    @Override //We release the dragged vertex
    public void mouseReleased(MouseEvent e) {
        if(isDragged != null)
        {
            isDragged = null;
        }
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        //We don't want to be able to drag vertex outside the draw area
        isDragged = null; //We remove it, so it cannot be dragged anymore
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        //We always repaint, to see if we are on top of an element to highlight it, to preview when we are creating a new edge/vertex, ...
        X = e.getX();
        Y = e.getY();
        repaint();
    }

    public void updateUndo()
    {
        
        Graph graphCopy = gui.getTabulations().get(this).clone();
        undo.add(graphCopy); //Adding a copy

        getRightClickMenu().getUndo().setEnabled(true);
        gui.getTools().getUndo().setEnabled(true);
        gui.getMenu().getEdit().getUndo().setEnabled(true);
        this.repaint();

        if(!redo.isEmpty())
        {
            redo.clear();
            getRightClickMenu().getRedo().setEnabled(false);
            gui.getTools().getRedo().setEnabled(false);
            gui.getMenu().getEdit().getRedo().setEnabled(false);
        }
    }

    public void undo()
    {
        redo.add(gui.getTabulations().get(this));
        gui.getTabulations().replace(this,undo.pop());
        
        if(undo.empty())
        {
            getRightClickMenu().getUndo().setEnabled(false);
            gui.getTools().getUndo().setEnabled(false);
            gui.getMenu().getEdit().getUndo().setEnabled(false);
        }

        if(!redo.empty())
        {
            getRightClickMenu().getRedo().setEnabled(true);
            gui.getTools().getRedo().setEnabled(true);
            gui.getMenu().getEdit().getRedo().setEnabled(true);
        }
        repaint();
    }

    public void redo()
    {
        undo.add(gui.getTabulations().get(this));
        gui.getTabulations().replace(this,redo.pop());

        if(redo.empty())
        {
            getRightClickMenu().getRedo().setEnabled(false);
            gui.getTools().getRedo().setEnabled(false);
            gui.getMenu().getEdit().getRedo().setEnabled(false);
        }

        if(!undo.empty())
        {
            getRightClickMenu().getUndo().setEnabled(true);
            gui.getTools().getUndo().setEnabled(true);
            gui.getMenu().getEdit().getUndo().setEnabled(true);
        }
        repaint();
    }

    public Draw getDrawArea() {
        return drawArea;
    }

    public void setDrawArea(Draw drawArea) {
        this.drawArea = drawArea;
    }

    public Gui getGui() {
        return gui;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    public Stack<Graph> getUndo() {
        return undo;
    }

    public void setUndo(Stack<Graph> undo) {
        this.undo = undo;
    }

    public Stack<Graph> getRedo() {
        return redo;
    }

    public void setRedo(Stack<Graph> redo) {
        this.redo = redo;
    }
}
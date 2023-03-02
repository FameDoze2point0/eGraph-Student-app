package gui.draw;
import java.awt.event.*;
import javax.swing.JPanel;
import gui.Gui;
import gui.draw.rightclickmenu.RightClick;
import util.Edge;
import util.Graph;
import util.Vertex;
import java.awt.*;
import java.util.*;

public class PanelPaint extends JPanel implements MouseListener, MouseMotionListener
{
    private Gui gui;
    private Draw drawArea;

    //When we add an edge, if the starting point is already selected
    private Vertex start = null; //If not selected = null

    private int X;
    private int Y;

    // //Menu when we right click
    RightClick rightClickMenu;

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
    }















    public void paint(Graphics graphics)
    {
        super.paint(graphics); //Refresh the JPanel with blank space
        Graph graph = gui.getTabulations().get(drawArea.getSelectedComponent());
        if (graph != null) {
            Object itemCollision = graph.vertexCollision(X, Y);
            if (itemCollision == null) {
                itemCollision = graph.edgeCollision( X, Y);
            }
            graph.paint(graphics, itemCollision);    
        }

        //save
        {
            // // === IF THE CURSOR IS ON TOP OF A VERTEX/EGDE, WE HIGHLIGHT THE EDGE/VERTEX ===
        // Vertex vertex;
        // if((vertex = this.vertexCollision(graph,X,Y)) != null)
        // {
        //     //We highlight this vertex
        //     //Borders
        //     graphics.setColor(vertex.getBorderColor());
        //     graphics.fillOval(vertex.getCoordX(), vertex.getCoordY(), vertex.getDiameter(), vertex.getDiameter());

        //     //Inside
        //     graphics.setColor(vertex.getInsideColor());
        //     graphics.fillOval(vertex.getCoordX()+((int)(vertex.getDiameter()*0.4)/2), vertex.getCoordY()+((int)(vertex.getDiameter()*0.4)/2), (int)(vertex.getDiameter()*0.6), (int)(vertex.getDiameter()*0.6));

        //     //We draw vertex name on top of the vertex (color of border)
        //     graphics.setColor(vertex.getBorderColor());
        //     graphics.drawString(vertex.getName(), (int)(vertex.getCoordX()+vertex.getDiameter()/2.7), (int)(vertex.getCoordY()+vertex.getDiameter()/1.5));
        // }

        // //We then search for every edges
        // Edge edge;
        // //We look in a rectangular pattern
        // if((edge = this.edgeCollision(graph,X,Y)) != null && vertex == null)   //Y Coord
        // {
        //     graphics.setColor(edge.getStart().getInsideColor());
        //     ((Graphics2D)graphics).setStroke(new BasicStroke(edge.getStrokeWidth()/edge.getStart().getStrokeWidth())); //Change stroke

        //     //Case where the starting and ending point is the same
        //     if(edge.getStart().equals(edge.getEnd()) == false)
        //     {
        //         graphics.drawLine((int)(edge.getEdgePoints(edge).get(0).getX()), (int)(edge.getEdgePoints(edge).get(0).getY()), (int)(edge.getEdgePoints(edge).get(1).getX()), (int)(edge.getEdgePoints(edge).get(1).getY()));
        //         if(graph.getOriented()) //If the graph is oriented, we also have to draw an arrow displaying the arrival vertex
        //         {
        //             graphics.drawLine((int)(edge.getArrow(edge, 15).get(0).getX()),(int)(edge.getArrow(edge, 15).get(0).getY()), (int)(edge.getArrow(edge, 15).get(1).getX()), (int)(edge.getArrow(edge, 15).get(1).getY()));
        //             graphics.drawLine((int)(edge.getArrow(edge, 15).get(0).getX()),(int)(edge.getArrow(edge, 15).get(0).getY()), (int)(edge.getArrow(edge, 15).get(2).getX()), (int)(edge.getArrow(edge, 15).get(2).getY()));
        //         }
        //     }
        // }
        }
        


        /// === FINALLY, IF WE ARE IN STATE 1 OR 2, SHOW A PREVIEW OF THE NEW EDGE/VERTEX ===
        //If we are in state 1, we previzualize the new vertex
        if(gui.getState() == 1)
        {
            int radius = graph.getVertexDiameter()/2;
            int diameter = graph.getVertexDiameter();
            //We draw a vertex on top of the mouse with 50% opacity
            //Setting 50% opacity
            ((Graphics2D)graphics).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.35f));
            //Borders
            graphics.setColor(graph.getVertexOutsideColor());
            graphics.fillOval(X-radius, Y-radius, diameter, diameter);

            //Inside
            graphics.setColor(graph.getVertexInsideColor());
            graphics.fillOval((X+((int)(radius*0.2)))-(radius), (Y+((int)(radius*0.2)))-(radius), (int)(diameter*0.8), (int)(diameter*0.8));
        }

        //If we are in state 2 and the first vertex is selectionned, we previzualize the new edge (if first vertex selectionned)
        else if(gui.getState() == 2 && start != null)
        {
            //Setting 50% opacity
            ((Graphics2D)graphics).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.35f));
            //Drawing the line
            graphics.setColor(graph.getEdgeStrokeColor());
            ((Graphics2D)graphics).setStroke(new BasicStroke(graph.getEdgeStrokeWidth())); //Change stroke
            graphics.drawLine(start.getCoordX()+graph.getVertexDiameter()/2, start.getCoordY()+graph.getVertexDiameter()/2, X, Y);
        }
    }




















    @Override
    public void mouseClicked(MouseEvent e)
    {
        Graph graph = gui.getTabulations().get(drawArea.getSelectedComponent());
        Vertex vertex;
        Edge edge;
        int radius = graph.getVertexDiameter()/2, diameter = graph.getVertexDiameter();
        //=== LEFT CLICK DETECTION === to create new elements and interact
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            switch(gui.getState())
            {
                case(1): {
                    //In state 1, when the mouse is clicked, we create a new vertex/state
                    //We retrieve the actual graph/automaton
                    graph.addVertex(new Vertex(e.getX() - radius, e.getY() - radius, diameter, graph.getVertexStrokeWidth(), graph.getVertexInsideColor(), graph.getVertexOutsideColor(), ""+(graph.getVertices().size()), graph.getVertexNameColor())); //We add the new vertex to the graph
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
                            //Adding the edge to the graph
                            graph.addEdge(new Edge(start, vertex, null, graph.getEdgeStrokeWidth(), graph.getEdgeStrokeColor(), graph.getEdgeHighlightColor(), graph.getEdgeArrowTipColor()));
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
        else if(e.getButton() == MouseEvent.BUTTON3)
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
    public void mouseDragged(MouseEvent e) {
        //If we are on top of a vertex, we move it to the new mouse coordinates
        Graph graph = gui.getTabulations().get(drawArea.getSelectedComponent());
        for(Vertex vertex : graph.getVertices())
        {
            if(((e.getX() - (vertex.getCoordX()+vertex.getDiameter()/2))*(e.getX() - (vertex.getCoordX()+vertex.getDiameter()/2)) + (e.getY() - (vertex.getCoordY()+vertex.getDiameter()/2))*(e.getY() - (vertex.getCoordY()+vertex.getDiameter()/2))) <= (vertex.getDiameter()/2)*(vertex.getDiameter()/2))
            {
                X = e.getX();
                Y = e.getY();
                vertex.setCoordX(e.getX()-vertex.getDiameter()/2);
                vertex.setCoordY(e.getY()-vertex.getDiameter()/2);
                //We must reset every path
                
                for(Edge edge : graph.getEdges())
                {
                    edge.setCollisionArea(edge.refreshCollisionArea());
                }

                repaint();
                break;
            }
        }
    }



    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        //We always repaint, to see if we are on top of an element to highlight it, to preview when we are creating a new edge/vertex, ...
        X = e.getX();
        Y = e.getY();
        repaint();
    }



    
}
package gui.popups.newElement;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JPanel;
import gui.Gui;
import gui.draw.Draw;
import gui.popups.newElement.rightclickmenu.RightClick;
import util.Edge;
import util.Graph;
import util.Vertex;
import java.awt.*;

public class PanelPaint extends JPanel implements MouseListener, MouseMotionListener
{
    private Gui gui;
    private Draw drawArea;

    //Settings, might be implemented later
    private int defaultWidth = 5; //For edges
    private int defaultRadius = 30; //For vertexs
    private Color defaultBorder = Color.BLACK;
    private Color defaultInside = Color.WHITE;
    private Color defaultEdgeColor = Color.BLACK;

    //When we add an edge, if the starting point is already selected
    private Vertex start = null; //If not selected = null

    private int X;
    private int Y;

    //Menu when we right click
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
        rightClickMenu = new RightClick();
        this.add(rightClickMenu);   
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //If this is a left click
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            switch(gui.getState())
            {
                case(0): {
                    //Nothing happen in state 0 when the mouse is clicked
                    break;
                }

                case(1): {
                    //In state 1, when the mouse is clicked, we create a new vertex/state
                    //We retrieve the actual graph/automaton
                    Graph graph = gui.getTabulations().get(drawArea.getSelectedComponent());
                    Vertex vertex = new Vertex(e.getX() - defaultRadius/2, e.getY() - defaultRadius/2, defaultRadius, defaultWidth, defaultInside, defaultBorder, "A");
                    graph.addVertex(vertex); //We add the new vertex to the graph
                    //We repaint
                    this.repaint();
                    break;
                }

                case(2): {
                    //In state 2, we want the user to click on 2 differents vertex/state to create an edge
                    //We first look if the user clicked on a vertex
                    Graph graph = gui.getTabulations().get(drawArea.getSelectedComponent());
                    for(Vertex vertex : graph.getVertices())
                    {
                        if(((e.getX() - (vertex.getCoordX()+vertex.getRadius()/2))*(e.getX() - (vertex.getCoordX()+vertex.getRadius()/2)) + (e.getY() - (vertex.getCoordY()+vertex.getRadius()/2))*(e.getY() - (vertex.getCoordY()+vertex.getRadius()/2))) <= (vertex.getRadius()/2)*(vertex.getRadius()/2))
                        {
                            //The user clicked on this vertex
                            //If the start vertex is != null, then we have both vertex required to draw the edge
                            if(start == null)
                            {
                                start = vertex;
                                break;
                            }
                            else
                            {
                                start.addEdge(new Edge(start, vertex, null, defaultWidth, defaultEdgeColor));
                                start = null;
                                this.repaint();
                                break;
                            }
                        }
                    }
                    break;
                }

                default: {
                    break;
                }
            }
        }

        //Right click
        else if(e.getButton() == MouseEvent.BUTTON3)
        {
            rightClickMenu.show(drawArea.getSelectedComponent() , e.getX(), e.getY()); 
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

    public void paint(Graphics graphics)
    {
        super.paint(graphics); //Refresh the JPanel with blank space
        Graph graph = gui.getTabulations().get(drawArea.getSelectedComponent());

        // === WE FIRST DRAW THE GRAPH / AUTOMATON ===
        //Draw every edges
        for(Vertex vertex : graph.getVertices())
        {
            for(Edge edge : vertex.getEdgeList())
            {
                graphics.setColor(edge.getStrokeColor());
                ((Graphics2D)graphics).setStroke(new BasicStroke(edge.getStrokeWidth())); //Change stroke
                graphics.drawLine(edge.getStart().getCoordX()+vertex.getRadius()/2, edge.getStart().getCoordY()+vertex.getRadius()/2, edge.getEnd().getCoordX()+vertex.getRadius()/2, edge.getEnd().getCoordY()+vertex.getRadius()/2);
            }
        }

        //We then draw every vertex
        for(Vertex vertex : graph.getVertices())
        {
            //Borders
            graphics.setColor(vertex.getBorderColor());
            graphics.fillOval(vertex.getCoordX(), vertex.getCoordY(), vertex.getRadius(), vertex.getRadius());

            //Inside
            graphics.setColor(vertex.getInsideColor());
            graphics.fillOval(vertex.getCoordX()+((int)(vertex.getRadius()*0.2)/2), vertex.getCoordY()+((int)(vertex.getRadius()*0.2)/2), (int)(vertex.getRadius()*0.8), (int)(vertex.getRadius()*0.8));
        }



        // === IF THE CURSOR IS ON TOP OF A VERTEX/EGDE, WE HIGHLIGHT THE EDGE/VERTEX IF WE ARE ON STATE 0 OR 2===
        //WE SEARCH FOR EVERY EDGE
        if(gui.getState() == 0 || gui.getState() == 2)
        {
            for(Vertex vertex : graph.getVertices())
            {
                if(((X - (vertex.getCoordX()+vertex.getRadius()/2))*(X - (vertex.getCoordX()+vertex.getRadius()/2)) + (Y - (vertex.getCoordY()+vertex.getRadius()/2))*(Y - (vertex.getCoordY()+vertex.getRadius()/2))) <= (vertex.getRadius()/2)*(vertex.getRadius()/2))
                {
                    //We highlight this vertex
                    //Borders
                    graphics.setColor(vertex.getBorderColor());
                    graphics.fillOval(vertex.getCoordX(), vertex.getCoordY(), vertex.getRadius(), vertex.getRadius());

                    //Inside
                    graphics.setColor(vertex.getInsideColor());
                    graphics.fillOval(vertex.getCoordX()+((int)(vertex.getRadius()*0.4)/2), vertex.getCoordY()+((int)(vertex.getRadius()*0.4)/2), (int)(vertex.getRadius()*0.6), (int)(vertex.getRadius()*0.6));
                }
                else
                {
                    //We search if we are on top of one of this vertex edges
                }
            }
        }


        /// === WE FINALLY, IF WE ARE IN STATE 1 OR 2, SHOW A PREVIEW OF THE NEW EDGE/VERTEX ===
        //If we are in state 1, we previzualize the new vertex
        if(gui.getState() == 1)
        {
            //We draw a vertex on top of the mouse with 50% opacity
            //Setting 50% opacity
            ((Graphics2D)graphics).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.35f));
            //Borders
            graphics.setColor(defaultBorder);
            graphics.fillOval(X-defaultRadius/2, Y-defaultRadius/2, defaultRadius, defaultRadius);

            //Inside
            graphics.setColor(defaultInside);
            graphics.fillOval((X+((int)(defaultRadius*0.2)/2))-(defaultRadius/2), (Y+((int)(defaultRadius*0.2)/2))-(defaultRadius/2), (int)(defaultRadius*0.8), (int)(defaultRadius*0.8));
        }

        //If we are in state 2 and the first vertex is selectionned, we previzualize the new edge (if first vertex selectionned)
        else if(gui.getState() == 2 && start != null)
        {
            //Setting 50% opacity
            ((Graphics2D)graphics).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.35f));
            //Drawing the line
            graphics.setColor(defaultEdgeColor);
            ((Graphics2D)graphics).setStroke(new BasicStroke(defaultWidth)); //Change stroke
            graphics.drawLine(start.getCoordX()+defaultRadius/2, start.getCoordY()+defaultRadius/2, X, Y);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //If we are on top of a vertex, we move it to the new mouse coordinates
        Graph graph = gui.getTabulations().get(drawArea.getSelectedComponent());
        for(Vertex vertex : graph.getVertices())
        {
            if(((e.getX() - (vertex.getCoordX()+vertex.getRadius()/2))*(e.getX() - (vertex.getCoordX()+vertex.getRadius()/2)) + (e.getY() - (vertex.getCoordY()+vertex.getRadius()/2))*(e.getY() - (vertex.getCoordY()+vertex.getRadius()/2))) <= (vertex.getRadius()/2)*(vertex.getRadius()/2))
            {
                X = e.getX();
                Y = e.getY();
                vertex.setCoordX(e.getX()-vertex.getRadius()/2);
                vertex.setCoordY(e.getY()-vertex.getRadius()/2);
                repaint();
                break;
            }
        }
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
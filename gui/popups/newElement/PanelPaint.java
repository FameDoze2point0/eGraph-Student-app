package gui.popups.newElement;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import gui.Gui;
import gui.draw.Draw;
import util.Edge;
import util.Graph;
import util.Vertex;
import java.awt.Graphics;

public class PanelPaint extends JPanel implements MouseListener
{
    private Gui gui;
    private Draw drawArea;

    //Settings, might be implemented later
    private int defaultWidth = 30; //For edges
    private int defaultRadius = 30; //For vertexs
    private Color defaultBorder = Color.BLACK;
    private Color defaultInside = Color.WHITE;


    public PanelPaint(Gui gui, Draw drawArea)
    {
        super();
        //Retreiving the gui and the draw area
        this.gui = gui;
        this.drawArea = drawArea;

        //MouseListener
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
                Vertex vertex = new Vertex(e.getX(), e.getY(), defaultRadius, defaultWidth, defaultInside, defaultBorder, "A");
                graph.addVertex(vertex); //We add the new vertex to the graph
                //We repaint
                this.repaint();
                break;
            }

            case(2): {
                //In state 2, we want the user to click on 2 differents vertex/state to create an edge

                break;
            }

            default: {
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
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void paint(Graphics graphics)
    {
        //graphics.setColor(Color.WHITE);
        //graphics.fillRect(0,0,gui.getWidth(),gui.getHeight());
        super.paint(graphics);
        Graph graph = gui.getTabulations().get(drawArea.getSelectedComponent());

        //Draw every edges
        for(Vertex vertex : graph.getVertices())
        {
            for(Edge edge : vertex.getEdgeList())
            {
                graphics.setColor(edge.getStrokeColor());
                graphics.drawLine(edge.getStart().getCoordX(), edge.getStart().getCoordY(), edge.getEnd().getCoordX(), edge.getEnd().getCoordY());
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
    }
}

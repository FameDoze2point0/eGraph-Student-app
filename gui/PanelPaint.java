package gui;
import javax.swing.JPanel;
import components.Automaton;
import components.Edge;
import components.Graph;
import components.Vertex;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Font;
import java.awt.BasicStroke;

public class PanelPaint<T> extends JPanel
{
    //Copy of the graphic component of the panel
    Graphics g = this.getGraphics();

    public PanelPaint()
    {
        super();
        this.addMouseMotionListener(new MouseMotionListener()
        {
            @Override
            public void mouseDragged(MouseEvent e) {
                //When grabbing an element of the graph
                //We first look if we "grabbed" an element of the graph
                Graph<T> graph = (Graph<T>)Gui.getOpened().get(Gui.getTabsAndDraw().getSelectedIndex());
                for(Vertex<T> vertex : graph.getVertices())
                {
                    //If true, it mean we are dragging 
                    if(Math.sqrt(Math.pow(e.getX() - vertex.getX(), 2) + Math.pow(e.getY() - vertex.getY(), 2)) <= vertex.getRadius())
                    {
                        vertex.setX(e.getX());
                        vertex.setY(e.getY());
                        Gui.getTabsAndDraw().getSelectedComponent().paint(Gui.getTabsAndDraw().getSelectedComponent().getGraphics());
                        break;
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //Not usefull here, but have to implement it
            }
        });
    }

    public void paint(Graphics g)
    {
        //We first reset the whole jpanel
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 4096, 4096);

        //Drawing either automaton or graph
        Graph<T> graph = (Graph<T>)Gui.getOpened().get(Gui.getTabsAndDraw().getSelectedIndex());

        //STEP 1 : WE DRAW EVERY EDGES FIRST SO THEY ARE BEHIND EVERY VERTEX
        for(Vertex<T> vertex : graph.getVertices())
        {
            //Wa start to draw all his edges so that they are behind
            for(Edge<T> edge : vertex.getEdges())
            {
                //We draw every edges
                g.setColor(edge.getInside());
                ((Graphics2D)(g)).setStroke(new BasicStroke(edge.getRadius()));
                g.drawLine((int)edge.getStart().getX(), (int)edge.getStart().getY(), (int)edge.getArrival().getX(), (int)edge.getArrival().getY());
                //If its an oriented graph OR an automaton, we must draw an arrow
            }
        }

        //STEP 2 : WE DRAW EVERY VERTEX, THEY WILL BE IN FRONT OF EVERY EDGES SO IT WILL LOOK BETTER
        for(Vertex<T> vertex : graph.getVertices())
        {
            //We then draw the vertex
            //We first draw the border
            g.setColor(vertex.getOutside()); 
            g.fillOval((int)vertex.getX() - vertex.getRadius()/2, (int)vertex.getY() - vertex.getRadius()/2, vertex.getRadius(), vertex.getRadius());

            //We then draw the interior
            g.setColor(vertex.getInside()); //Note that the border is 20% of the whole radius
            g.fillOval((int)(vertex.getX() - (vertex.getRadius()*0.8)/2), (int)(vertex.getY() - (vertex.getRadius()*0.8)/2), (int)(vertex.getRadius()*0.8), (int)(vertex.getRadius()*0.8));

            //We draw a letter from inside the vertex to dinstinguish it
            g.setColor(vertex.getOutside()); //Border of the letter
            g.setFont(new Font("Arial", Font.BOLD, (int)(vertex.getRadius()*0.5))); //50% of total radius 
            g.drawString(vertex.getName(), (int)(vertex.getX() - vertex.getRadius()*0.15), (int)(vertex.getY() + vertex.getRadius()*0.2));
        }

        //If this is an automaton, we also have to draw entry and exit point
        if(Gui.getOpened().get(Gui.getTabsAndDraw().getSelectedIndex()) instanceof Automaton);
        {

        }    
    }

    public Graphics getG() {
        return g;
    }

    public void setG(Graphics g) {
        this.g = g;
    }
}

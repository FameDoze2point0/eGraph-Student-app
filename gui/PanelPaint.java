package gui;
import javax.swing.JPanel;
import components.Automaton;
import components.Edge;
import components.Graph;
import components.Vertex;
import java.awt.Graphics;
import java.awt.Color;

public class PanelPaint<T> extends JPanel
{
    PanelPaint()
    {
        super();
    }

    public void paint(Graphics g)
    {
        //We first reset the whole jpanel
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 4096, 4096);

        //Drawing either automaton or graph
        Graph<T> graph = (Graph<T>)Gui.getOpened().get(Gui.getTabsAndDraw().getSelectedIndex());
        //We now draw the graph
        //First the states
        for(Vertex<T> vertex : graph.getVertices())
        {
            //We change the color for the vertex one
            //We first draw the border
            g.setColor(vertex.getOutside()); 
            g.fillOval((int)vertex.getX(), (int)vertex.getY(), vertex.getRadius(), vertex.getRadius());
            //We then draw the interior
            g.setColor(vertex.getInside()); //Note that the border is 10% of the whole radius
            g.fillOval((int)vertex.getX(), (int)vertex.getY(), vertex.getRadius()-(int)(vertex.getRadius()*0.9), vertex.getRadius()-(int)(vertex.getRadius()*0.9));

            //Now that the vertex is drawned, we draw all his edges
            for(Edge<T> edge : vertex.getEdges())
            {
                //We draw every edges
                g.setColor(edge.getInside());
                g.drawLine((int)edge.getStart().getX(), (int)edge.getStart().getY(), (int)edge.getArrival().getX(), (int)edge.getArrival().getY());
                //If its an oriented graph OR an automaton, we must draw an arrow
                System.out.println("draw arrow");
            }
        }
        
        //If this is an automaton, we also have to draw entry and exit point
        if(Gui.getOpened().get(Gui.getTabsAndDraw().getSelectedIndex()) instanceof Automaton);
        {
            System.out.println("draw entry n exit");
        }    
    }
}

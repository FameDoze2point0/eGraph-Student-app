/*
 *  Author : Antonin C. & François P.
 *  File : Node.java
 *
 *  Description : a node is represented by coordinates (x,y), a name and
 *  a list of edges.
 */

package components;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Color;

public class Node extends Point
{
    //Name of the node
    private String name;

    //List of edges
    private ArrayList<Edge> edges;

    //Graphical aspect of the node
    private Color outside, inside; //Color of the node
    private int radius; //Determine the size of the node

    //Constructor
    public Node(String name, int x, int y)
    {
        super(x,y); //Setting coordinates
        this.name = name; //Setting name
        edges = new ArrayList<Edge>(); //Setting the edge list

        //By defaults, colors are black and radius is 10 pixels
        outside = Color.BLACK;
        inside = Color.BLACK;
        radius = 10;
    }

    // === Getters and setters ===
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<Edge> getEdges()
    {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges)
    {
        this.edges = edges;
    }

    public Color getOutside()
    {
        return outside;
    }

    public void setOutside(Color outside)
    {
        this.outside = outside;
    }

    public Color getInside()
    {
        return inside;
    }

    public void setInside(Color inside)
    {
        this.inside = inside;
    }

    public int getRadius()
    {
        return radius;
    }

    public void setRadius(int radius)
    {
        this.radius = radius;
    }
}
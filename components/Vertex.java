/*
 *  Author : Antonin C. & François P.
 *  File : Vertex.java
 *
 *  Description : a vertex is represented by coordinates (x,y), a name and
 *  a list of edges.
 */

package components;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Color;

public class Vertex<T> extends Point
{
    //Name of the vertex
    private static int cpt = 0;
    private int id;
    private String name;

    //List of edges
    private ArrayList<Edge<T>> edges;

    //Graphical aspect of the vertex
    private Color outside, inside; //Color of the vertex
    private int radius; //Determine the size of the vertex

    //Constructor
    public Vertex(String name, int x, int y, ArrayList<Edge<T>> edges)
    {
        super(x,y); //Setting coordinates
        this.id = cpt++;
        this.name = name; //Setting name
        this.edges = edges;
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

    public ArrayList<Edge<T>> getEdges()
    {
        return edges;
    }

    public void setEdges(ArrayList<Edge<T>> edges)
    {
        this.edges = edges;
    }

    public static int getCpt() {
        return cpt;
    }

    public static void setCpt(int cpt) {
        Vertex.cpt = cpt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
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
        inside = Color.WHITE;
        radius = 30;
    }

    // === Getters and setters ===
    
    public ArrayList<Edge<T>> getEdges() {
        return edges;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addEdge(T weight, Vertex<T> end){
        edges.add(new Edge<T>(this, end, weight));
    }

    public void addEdge(Vertex<T> end){
        edges.add(new Edge<T>(this, end));
    }

    @Override
    public String toString() {
        return "Vertex [name=" + name + ", edges=" + edges + "]";
    }

    public static int getCpt() {
        return cpt;
    }

    public static void setCpt(int cpt) {
        Vertex.cpt = cpt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEdges(ArrayList<Edge<T>> edges) {
        this.edges = edges;
    }

    public Color getOutside() {
        return outside;
    }

    public void setOutside(Color outside) {
        this.outside = outside;
    }

    public Color getInside() {
        return inside;
    }

    public void setInside(Color inside) {
        this.inside = inside;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}
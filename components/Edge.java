/*
 *  Author : Antonin C. & François P.
 *  File : Edge.java
 *
 *  Description : an edge is represented by a weight and the node it join
 */

package components;
import java.io.Serializable;
import java.awt.Color;

public class Edge<T> implements Serializable
{
    //Weight of the edge
    private T weight;

    //Starting vertex
    private Vertex<T> start;

    //The vertex of arrival
    private Vertex<T> arrival;

    //Graphical aspect of the edge
    private Color inside; //Color of the edge
    private int radius; //Determine the size of the edge

    public Edge(Vertex<T> start, Vertex<T> arrival, T weight)
    {
        this.start = start;
        this.arrival = arrival;
        this.weight = weight;

        //By defaults, the inside color is black and the radius is 2 pixels
        inside = Color.BLACK;
        radius = 2;
    }

    // === Getters and setters ===
    public T getWeight()
    {
        return weight;
    }

    public void setWeight(T weight) 
    {
        this.weight = weight;
    }

    public Vertex<T> getArrival() 
    {
        return arrival;
    }

    public void setArrival(Vertex<T> arrival) 
    {
        this.arrival = arrival;
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

    public Vertex<T> getStart() {
        return start;
    }

    public void setStart(Vertex<T> start) {
        this.start = start;
    }
}

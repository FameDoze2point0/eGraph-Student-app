/*
 *  Author : Antonin C. & François P.
 *  File : Edge.java
 *
 *  Description : an edge is represented by a weight and the node it join
 */

package components;
import java.io.Serializable;
import java.awt.Color;

public class Edge implements Serializable
{
    //Weight of the edge
    private int weight;

    //The node of arrival
    private Node arrival;

    //Graphical aspect of the edge
    private Color inside; //Color of the edge
    private int radius; //Determine the size of the edge

    public Edge(Node arrival, int weight)
    {
        this.arrival = arrival;
        this.weight = weight;

        //By defaults, the inside color is black and the radius is 2 pixels
        inside = Color.BLACK;
        radius = 2;
    }

    // === Getters and setters ===
    public int getWeight()
    {
        return weight;
    }

    public void setWeight(int weight) 
    {
        this.weight = weight;
    }

    public Node getArrival() 
    {
        return arrival;
    }

    public void setArrival(Node arrival) 
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
}

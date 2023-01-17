/*
 *  Author : Antonin C. & François P.
 *  File : Graph.java
 *
 *  Description : a graph have a name, is represented by a list of node and
 *  have 2 flags (isWeighted and isOriented)
 */

package components;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Graph<T> implements Serializable
{
    //Name of the graph
    private String name;

    //List of node
    private LinkedList<Vertex<T>> vertices;

    //Flags
    private Boolean isOriented; //if the graph is oriented
    private Boolean isWeighted; //if the graph is weighted

    public Graph(String name, Boolean isOriented, Boolean isWeighted)
    {
        name = this.name; //Name
        vertices = new LinkedList<Vertex<T>>(); //List of node
        this.isOriented = isOriented; //if the graph is oriented
        this.isWeighted = isWeighted; //if the graph is weighted
    }

    //Add a vertex to the graph
    public int addVertex(String name)
    {
        //Search if the vertex with this name already exist
        for(Vertex<T> vertex : vertices)
        {
            if(vertex.getName().equals(name))
            {
                System.out.println("A vertex with this name already exist !");
                return 0; //FAILURE
            }
        }
        
        //Creation of a list of generic type
        ArrayList<Edge<T>> edges = new ArrayList<Edge<T>>();
        vertices.add(new Vertex<T>(name, 0, 0, edges));
        return 1; //SUCCESS
    }

    //Remove vertex from the graph
    public int removeVertex(String name)
    {
        for(Vertex<T> vertex : vertices)
        {
            if(vertex.getName().equals(name))
            {
                vertices.remove(vertex);
                return 1; //SUCCESS
            }
        }

        //FAILURE
        return 0;
    }

    //Get a vertex with name
    public Vertex<T> getVertex(String name)
    {
        for(Vertex<T> vertex : vertices)
        {
            if(vertex.getName().equals(name))
            {
                //SUCCESS
                return vertex;
            }
        }

        //FAILURE
        return null;
    }

    /*
     * Conversion list of vertices to 2d array of vertices
     * Java ArrayList is a 2 dimensionnal array
     * Java LinkedList is a list which uses less memory
     * Here, we want to convert our LinkedList to an ArrayList to reduce complexity of some algorithmes
     */
    public ArrayList<ArrayList<T>> toArray()
    {
        // creation of the 2D arraylist
        ArrayList<ArrayList<T>> array = new ArrayList<ArrayList<T>>();

        //initialization of cells to null
        for (int i = 0; i < vertices.size(); i++)
        {
            array.add(new ArrayList<T>());
            for (int j = 0; j < vertices.size(); j++)
                array.get(i).add(null);
        }

        //we now replace each value by its weight. If the edge doesn't exist, the weight will remain equal to null.
        for(Vertex<T> vertex : this.vertices)
        {
            for(Edge<T> edge : vertex.getEdges())
            {
                int arrival = edge.getArrival().getId();
                int start = edge.getStart().getId();
                array.get(start).set(arrival, edge.getWeight());
            }
        }
        return array;
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

    public Boolean getIsOriented()
    {
        return isOriented;
    }

    public void setIsOriented(Boolean isOriented)
    {
        this.isOriented = isOriented;
    }

    public Boolean getIsWeighted()
    {
        return isWeighted;
    }

    public void setIsWeighted(Boolean isWeighted)
    {
        this.isWeighted = isWeighted;
    }

    public LinkedList<Vertex<T>> getVertices()
    {
        return vertices;
    }

    public void setVertices(LinkedList<Vertex<T>> vertices)
    {
        this.vertices = vertices;
    }    
}

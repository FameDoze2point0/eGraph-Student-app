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

public class Graph implements Serializable
{
    //Name of the graph
    private String name;

    //List of node
    private ArrayList<Node> nodes;

    //Flags
    private Boolean isOriented; //if the graph is oriented
    private Boolean isWeighted; //if the graph is weighted

    public Graph(String name, Boolean isOriented, Boolean isWeighted)
    {
        name = this.name; //Name
        nodes = new ArrayList<Node>(); //List of node
        this.isOriented = isOriented; //if the graph is oriented
        this.isWeighted = isWeighted; //if the graph is weighted
    }

    //Add a node to the graph
    public int addNode(String name, int weight)
    {
        //Search if the node with this name already exist
        for(Node node : nodes)
        {
            if(node.getName() == name)
            {
                System.out.println("A node with this name already exist !");
                return 0; //FAILURE
            }
        }

        nodes.add(new Node(name, 0, 0));
        return 1; //SUCCESS
    }

    //Remove node from the graph
    public int removeNode(String name)
    {
        for(Node node : nodes)
        {
            if(node.getName() == name)
            {
                nodes.remove(node);
                return 1; //SUCCESS
            }
        }

        //FAILURE
        return 0;
    }

    //Get a specific node from the name
    public Node getNode(String name)
    {
        for(Node node : nodes)
        {
            if(node.getName() == name)
            {
                //SUCCESS
                return node;
            }
        }

        //FAILURE
        return null;
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

    public ArrayList<Node> getNodes()
    {
        return nodes;
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
}

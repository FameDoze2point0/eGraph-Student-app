/*
 *  Author : Antonin C. & François P.
 *  File : Automaton.java
 *
 *  Description : an automaton is a weighted, oriented graph with entry and exit nodes  
 */

package components;
import java.util.ArrayList;

public class Automaton extends Graph<String>
{
    //List of entry nodes
    private ArrayList<Vertex<String>> entryNodes;

    //List of exit nodes
    private ArrayList<Vertex<String>> exitNodes;

    public Automaton(String name)
    {
        //Oriented and weighted
        super(name, true, true);
        entryNodes = new ArrayList<Vertex<String>>(); //List of entry nodes
        exitNodes = new ArrayList<Vertex<String>>(); //List of exit nodes
    }

    // === Getters and setters ===
    public ArrayList<Vertex<String>> getEntryNodes()
    {
        return entryNodes;
    }

    public void setEntryNodes(ArrayList<Vertex<String>> entryNodes)
    {
        this.entryNodes = entryNodes;
    }

    public ArrayList<Vertex<String>> getExitNodes()
    {
        return exitNodes;
    }

    public void setExitNodes(ArrayList<Vertex<String>> exitNodes)
    {
        this.exitNodes = exitNodes;
    }
}

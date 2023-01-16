/*
 *  Author : Antonin C. & François P.
 *  File : Automaton.java
 *
 *  Description : an automaton is a weighted, oriented graph with entry and exit nodes  
 */

package components;
import java.util.ArrayList;

public class Automaton extends Graph
{
    //List of entry nodes
    private ArrayList<Boolean> entryNodes;

    //List of exit nodes
    private ArrayList<Boolean> exitNodes;

    public Automaton(String name)
    {
        //Oriented and weighted
        super(name, true, true);
        entryNodes = new ArrayList<Boolean>(); //List of entry nodes
        exitNodes = new ArrayList<Boolean>(); //List of exit nodes
    }

    // === Getters and setters ===
    public ArrayList<Boolean> getEntryNodes()
    {
        return entryNodes;
    }

    public void setEntryNodes(ArrayList<Boolean> entryNodes)
    {
        this.entryNodes = entryNodes;
    }

    public ArrayList<Boolean> getExitNodes()
    {
        return exitNodes;
    }

    public void setExitNodes(ArrayList<Boolean> exitNodes)
    {
        this.exitNodes = exitNodes;
    }
}

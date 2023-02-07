/*
 *  Author : Antonin C. & François P.
 *  File : Main.java
 *
 *  Description : this class is used to launch our program
 */

//Graph and automaton
import components.*;
//Graphic interface
import gui.*;

//Other
import java.util.*;
import java.lang.Integer;
import java.awt.Color;

public class Main
{
    //Main function
    public static void main(String[] args)
    {
        Gui page = new Gui();

        //ARTIFICIAL GRAPH TO TEST
        Graph<Integer> g = new Graph<Integer>("test", false, false);
        
        Vertex<Integer> v1 =  new Vertex<Integer>("1", 50, 50,null);
        Vertex<Integer> v2 =  new Vertex<Integer>("2", 450, 50,null);
        Vertex<Integer> v3 =  new Vertex<Integer>("3", 250 , 150,null);
        Vertex<Integer> v4 =  new Vertex<Integer>("4", 450, 300,null);
        Vertex<Integer> v5 =  new Vertex<Integer>("5", 50, 300,null);
        

        v1.addEdge(1, v3);

        v2.addEdge(2, v1);

        v3.addEdge(3, v2);
        v3.addEdge(3, v4);
        v3.addEdge(3, v5);

        v4.addEdge(4, v2);
        v4.addEdge(4, v5);

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        //
<<<<<<< Updated upstream

=======
        System.out.println("GUI");
>>>>>>> Stashed changes
        Gui.getTabsAndDraw().addTab("test", null, new PanelPaint(),null);
        Gui.getOpened().add(g);
    }
}
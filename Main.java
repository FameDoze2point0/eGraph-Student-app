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

public class Main
{
    //Main function
    public static void main(String[] args)
    {
        Gui page = new Gui();
        //TEMP
        //Creating a graph
        Graph<Integer> test = new Graph<>("graphtest", false, false);
        //adding some vertex
        test.addVertex("A");
        test.addVertex("B");
        test.addVertex("C");
        test.addVertex("D");
        test.addVertex("E");
        //Adding some edge
        test.getVertex("A").addEdge(1, test.getVertex("B"));
        test.getVertex("B").addEdge(2, test.getVertex("C"));
        test.getVertex("C").addEdge(3, test.getVertex("D"));
        test.getVertex("D").addEdge(4, test.getVertex("E"));
        test.getVertex("E").addEdge(5, test.getVertex("A"));
    }
}



/*
 *  System.out.println("New graph made !");
 *      Graph<Integer> g = new Graph<Integer>("Mon graphe", false, false);
 *       graphs.add(g);
 *
 *        Vertex<Integer> a = new Vertex<Integer>("A", 0, 0, new ArrayList<Edge<Integer>>());
 *        Vertex<Integer> b = new Vertex<Integer>("B", 0, 0, new ArrayList<Edge<Integer>>());
 *        Vertex<Integer> c = new Vertex<Integer>("C", 0, 0, new ArrayList<Edge<Integer>>());
 *        Vertex<Integer> d = new Vertex<Integer>("D", 0, 0, new ArrayList<Edge<Integer>>());
 *        g.addVertex(a);
 *        g.addVertex(b);
 *        g.addVertex(c);
 *        g.addVertex(d);
 *
 *        a.addEdge(1, d);
 *
 *        b.addEdge(1,c);
 *        b.addEdge(1,d);
 *
 *        c.addEdge(1,a);
 *        c.addEdge(1,d);
 *
 *      
 *        System.out.println(g.toString());
 */
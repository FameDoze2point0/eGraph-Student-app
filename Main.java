/*
 *  Author : Antonin C. & François P.
 *  File : Main.java
 *
 *  Description : this class is used to launch our program
 */

//Graph and automaton
import util.*;

//Other
import java.util.*;
import java.lang.Integer;
import java.awt.Color;

public class Main
{
    //Main function
    public static void main(String[] args)
    {
        Graph g = new Graph("Test", false, true);

        Vertex vA = new Vertex("A");
        Vertex vB = new Vertex("B");
        Vertex vC = new Vertex("C");
        Vertex vD = new Vertex("D");
        Vertex vE = new Vertex("E");
        Vertex vF = new Vertex("F");
        Vertex vG = new Vertex("G");

        
        g.addVertex(vA);
        g.addVertex(vB);
        g.addVertex(vC);
        g.addVertex(vD);
        g.addVertex(vE);
        g.addVertex(vF);
        g.addVertex(vG);

        vA.addEdge( vB, 2, 1, Color.black);
        vA.addEdge( vC, 1, 1, Color.black);

        vB.addEdge( vC, 1, 1, Color.black);

        vC.addEdge( vD, 1, 1, Color.black);
        vC.addEdge( vE, 1, 1, Color.black);
        vC.addEdge( vF, 1, 1, Color.black);

        vD.addEdge( vA, 1, 1, Color.black);

        vE.addEdge( vG, 1, 1, Color.black);

        vG.addEdge( vB, 1, 1, Color.black);

        System.out.println(g.toString());

        System.out.println("\nBFS :");
        g.algo_BFS(vC);

        System.out.println("\nDFS :");
        g.algo_DFS(vC);

        System.out.println("\nRS :");
        g.algo_RS(vC);

        System.out.println("\nDijkstra :");
        g.algo_Dijkstra(vA);

        System.out.println("\nBellman-Ford");
        g.algo_BellmanFord(vA);

    }
}
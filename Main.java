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
import gui.Gui;

public class Main
{
    //Main function
    public static void main(String[] args)
    {
        
        Gui gui = new Gui();

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

        g.addEdge(vA,vB, 2, 1, Color.black);
        g.addEdge(vA,vC, 1, 1, Color.black);

        g.addEdge(vB,vC, 3, 1, Color.black);

        g.addEdge(vC,vD, 3, 1, Color.black);
        g.addEdge(vC,vE ,2, 1, Color.black);
        g.addEdge(vC,vF, 1, 1, Color.black);

        g.addEdge(vD,vA, 3, 1, Color.black);

        g.addEdge(vE,vG, 3, 1, Color.black);

        g.addEdge(vF,vD, 3, 1, Color.black);

        g.addEdge(vG,vB, 2, 1, Color.black);

        System.out.println(g.toString());

        System.out.println("\nBFS :");
        g.algo_BFS(vA);

        System.out.println("\nDFS :");
        g.algo_DFS(vA);

        System.out.println("\nRS :");
        g.algo_RS(vA);

        try {
            System.out.println("\nDijkstra :");
            g.algo_Dijkstra(vA);

            System.out.println("\nBellman-Ford :");
            g.algo_BellmanFord(vA);

            System.out.println("\nFloyd-Warshall :");
            g.algo_FloydWarshall();

            // System.out.println("\nPrim :");
            // g.algo_Prim(vA);

            // System.out.println("\nKruskal :");
            // g.algo_Kruskal();


        } catch (Exception e) {
            System.out.println(e);
        }
        

    }
}
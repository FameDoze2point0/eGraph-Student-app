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

import javax.swing.JPanel;

import java.lang.Integer;
import java.awt.Color;
import gui.Gui;
import gui.draw.PanelPaint;

public class Main
{
    //Main function
    public static void main(String[] args)
    {        
        Gui gui = new Gui();


        //TEMP
        int defaultWidth = 5; //For edges
        int defaultRadius = 30; //For vertexs
        Color defaultBorder = Color.BLACK;
        Color defaultInside = Color.WHITE;
        Color defaultEdgeColor = Color.BLACK;

        Graph g = new Graph("Test", false, true);

        Vertex v0 = new Vertex(100 - defaultRadius/2, 100 - defaultRadius/2, defaultRadius, defaultWidth, defaultInside, defaultBorder, ""+(g.getVertices().size()+1));
        Vertex v1 = new Vertex(300 - defaultRadius/2, 100 - defaultRadius/2, defaultRadius, defaultWidth, defaultInside, defaultBorder, ""+(g.getVertices().size()+1));
        Vertex v2 = new Vertex(100 - defaultRadius/2, 300 - defaultRadius/2, defaultRadius, defaultWidth, defaultInside, defaultBorder, ""+(g.getVertices().size()+1));
        Vertex v3 = new Vertex(100 - defaultRadius/2, 100 - defaultRadius/2, defaultRadius, defaultWidth, defaultInside, defaultBorder, ""+(g.getVertices().size()+1));
        Vertex v4 = new Vertex(300 - defaultRadius/2, 200 - defaultRadius/2, defaultRadius, defaultWidth, defaultInside, defaultBorder, ""+(g.getVertices().size()+1));
        Vertex v5 = new Vertex(450 - defaultRadius/2, 300 - defaultRadius/2, defaultRadius, defaultWidth, defaultInside, defaultBorder, ""+(g.getVertices().size()+1));
        Vertex v6 = new Vertex(450 - defaultRadius/2, 100 - defaultRadius/2, defaultRadius, defaultWidth, defaultInside, defaultBorder, ""+(g.getVertices().size()+1));

        
        g.addVertex(v0);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);

        g.addEdge(v0,v1, 2, defaultWidth, Color.black);
        g.addEdge(v0,v2, 1, defaultWidth, Color.black);

        g.addEdge(v1,v2, 3, defaultWidth, Color.black);

        g.addEdge(v2,v3, 3, defaultWidth, Color.black);
        g.addEdge(v2,v4 ,2, defaultWidth, Color.black);
        g.addEdge(v2,v5, 1, defaultWidth, Color.black);

        g.addEdge(v3,v0, 3, defaultWidth, Color.black);

        g.addEdge(v4,v6, 3, defaultWidth, Color.black);

        g.addEdge(v5,v4, 3, defaultWidth, Color.black);

        g.addEdge(v6,v1, 2, defaultWidth, Color.black);

        System.out.println(g.toString());

        System.out.println("\nBFS :");
        g.algo_BFS(v0);

        System.out.println("\nDFS :");
        g.algo_DFS(v0);

        System.out.println("\nRS :");
        g.algo_RS(v0);

        //On ajoute le graph g temporairement
        gui.getDraw().addTab("temp_graph", null, new PanelPaint(gui, gui.getDraw()), "temporary graph");
        gui.getTabulations().put((JPanel) gui.getDraw().getSelectedComponent(),g);

        try {
            System.out.println("\nDijkstra :");
            g.algo_Dijkstra(v0);

            System.out.println("\nBellman-Ford :");
            g.algo_BellmanFord(v0);

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
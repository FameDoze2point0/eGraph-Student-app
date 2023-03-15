/*
 *  Author : Antonin C. & François P.
 *  File : Main.java
 *
 *  Description : this class is used to launch our program
 */

import gui.Gui;
import gui.draw.PanelPaint;
import util.Graph;
import util.Vertex;

import java.awt.Color;

import javax.swing.JPanel;

public class Main
{
    //Main function
    public static void main(String[] args)
    {
        Gui gui = new Gui();

        //TEMP
        int defaultWidth = 5; //For edges
        int defaultRadius = 50; //For vertexs
        Color defaultBorder = Color.BLACK;
        Color defaultInside = Color.WHITE;
        Color defaultEdgeColor = Color.BLACK;
        Color defaultNameColor = Color.BLACK;

        PanelPaint pp = new PanelPaint(gui, gui.getDraw());
        gui.getDraw().addTab("temp_graph", null, pp, "temporary graph");
        Graph g = new Graph("Test", true, true, pp);

        Vertex v0 = new Vertex(0,200 - defaultRadius/2, 100 - defaultRadius/2, defaultRadius, defaultWidth, defaultInside, defaultBorder, "0", defaultNameColor);
        Vertex v1 = new Vertex(1,400 - defaultRadius/2, 100 - defaultRadius/2, defaultRadius, defaultWidth, defaultInside, defaultBorder, "1", defaultNameColor);
        Vertex v2 = new Vertex(2,400 - defaultRadius/2, 300 - defaultRadius/2, defaultRadius, defaultWidth, defaultInside, defaultBorder, "2",defaultNameColor);
        Vertex v3 = new Vertex(3,200 - defaultRadius/2, 300 - defaultRadius/2, defaultRadius, defaultWidth, defaultInside, defaultBorder, "3",defaultNameColor);
        Vertex v4 = new Vertex(4,600 - defaultRadius/2, 300 - defaultRadius/2, defaultRadius, defaultWidth, defaultInside, defaultBorder, "4",defaultNameColor);
        Vertex v5 = new Vertex(5,400 - defaultRadius/2, 500 - defaultRadius/2, defaultRadius, defaultWidth, defaultInside, defaultBorder, "5",defaultNameColor);
        Vertex v6 = new Vertex(6,600 - defaultRadius/2, 100 - defaultRadius/2, defaultRadius, defaultWidth, defaultInside, defaultBorder, "6",defaultNameColor);

        
        g.addVertex(v0);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);

        g.addEdge(v0,v1, 2);
        g.addEdge(v0,v2, 1);

        g.addEdge(v1,v2, 3);

        g.addEdge(v2,v3, 3);
        g.addEdge(v2,v4 ,2);
        g.addEdge(v2,v5, 1);

        g.addEdge(v3,v0, 3);

        g.addEdge(v4,v6, 3);

        g.addEdge(v5,v3, 3);

        g.addEdge(v6,v1, 2);

        //System.out.println("\nBFS :");
        //g.algo_BFS(v0);

        //System.out.println("\nDFS :");
        //g.algo_DFS(v0);

        //System.out.println("\nRS :");
        //g.algo_RS(v0);

        //On ajoute le graph g temporairement
        
        gui.getTabulations().put((PanelPaint) gui.getDraw().getSelectedComponent(),g);

        try {
            //System.out.println("\nDijkstra :");
            //g.algo_Dijkstra(v0);

            //System.out.println("\nBellman-Ford :");
            //g.algo_BellmanFord(v0);

            //System.out.println("\nFloyd-Warshall :");
            //g.algo_FloydWarshall();

            //System.out.println("\nPrim :");
            //g.algo_Prim(v0);

            //System.out.println("\nKruskal :");
            //g.algo_Kruskal();


        } catch (Exception e) {
            System.out.println(e);
        }
        

    }
}
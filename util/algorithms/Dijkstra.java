package util.algorithms;
import java.awt.Color;
import java.util.ArrayList;

import gui.Gui;
import gui.draw.Draw;
import util.Edge;
import util.Graph;
import util.Vertex;

public class Dijkstra extends Thread
{
    private Graph graph;
    private Vertex start;
    private Color vertexDefaultColor, edgeDefaultColor;
    private AnimationAlgorithm animAlgo;

    public Dijkstra(Graph g, Vertex start,Boolean isAnimated, Gui gui, Draw draw){
        this.graph = g;
        this.start = start;
        this.vertexDefaultColor = g.getVertices().get(0).getBorderColor();
        this.edgeDefaultColor = g.getEdges().get(0).getStrokeColor();
        this.animAlgo = new AnimationAlgorithm(g, vertexDefaultColor,edgeDefaultColor,isAnimated);
    }

    // function used to find the shortest distance from the visited vertex to another one
    public int minDistance(int dist[],Boolean sptSet[], int size){
        
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int i = 0; i < size; i++) 
            if (!sptSet[i] && dist[i] <= min) {
                min = dist[i];
                min_index = i;
            }
        
        return min_index;
    }

    // transform a graph into a 2D array
    public int[][] graphToArray(int size){

        int graphArr[][] = new int[size][size];
        for (int i = 0; i < graphArr.length; i++) 
            for (int j = 0; j < graphArr.length; j++) 
                graphArr[i][j] = 0;
            
        for (Edge edge : graph.getEdges()) 
            graphArr[edge.getStart().getId()][edge.getEnd().getId()] = (int)edge.getWeight();
            
        return graphArr;
    }

    //function which search an edge from a starting and ending vertices
    public void searchEdge(int idStart, int idEnd, ArrayList<Edge> browsed){
        ArrayList<Edge> edges = graph.getEdges();
        
        for (Edge edge : edges) {
            if (edge.getStart().getId() == idStart && edge.getEnd().getId() == idEnd && !browsed.contains(edge)) {
                browsed.add(edge);
            }
            if (!graph.getOriented() && edge.getStart().getId() == idEnd && edge.getEnd().getId() == idStart && !browsed.contains(edge)) {
                browsed.add(edge);
            }
        }
    }
    
    public void searchVertex(int idVertex, ArrayList<Vertex> visited){
    
        for (Vertex vertex : graph.getVertices()) {
            if (vertex.getId() == idVertex) {
                visited.add(vertex);
                break;
            }
        }
    }

    @Override
    public void run() {
        // if (!graph.getWeighted())
        //     throw new Exception("The graph has to be weighted to launch Dijkstra's Algorithm !");
        ArrayList<Vertex> vertices = graph.getVertices();
        int size = vertices.size();
        int graphArr[][] = graphToArray(size);

        ArrayList<Vertex> vertexVisited = new ArrayList<Vertex>();
        ArrayList<Edge> edgeBrowsed = new ArrayList<Edge>();

        int dist[] = new int[size];
        Boolean sptSet[] = new Boolean[size];

        for (int i = 0; i < size; i++) {
            dist[i] = Integer.MAX_VALUE/2;
            sptSet[i] = false;
        }
        dist[start.getId()] = 0;

        String text = "<html><body><blockquote><h1>Dijkstra</h1><p><h2>Step 0 </h2><br><center>"+animAlgo.matrixString()+"</center><br><br>visited (vertex) : "+vertexVisited.toString()+"<br>edgeBrowsed (start,end[,weight]): "+edgeBrowsed.toString()+"<br>";


        for (int count = 0; count < size ; count++) {
            text += "<h2>Step "+(count+1)+"</h2><br><br>visited (vertex) : "+vertexVisited.toString()+"<br>edgeBrowsed (start,end[,weight]): "+edgeBrowsed.toString()+"<br><br>";
            int u = minDistance(dist,sptSet, size);
            searchVertex(u, vertexVisited);
            sptSet[u] = true;

            for (int i = 0; i < size; i++) 
                if (!sptSet[i] && graphArr[u][i] != 0 && dist[u] != Integer.MAX_VALUE/2 && dist[u] + graphArr[u][i] < dist[i]){
                    dist[i] = dist[u] + graphArr[u][i];
                    text += "The shorter way from the visited vertices is the edge between "+u+" and "+i+" with the weight : "+dist[i]+"<br>";
                    searchEdge(u, i, edgeBrowsed);
                } 
            animAlgo.changeColor(vertexVisited, null, edgeBrowsed);   
        }

        text += "</p></body></blockquote></html>";

        animAlgo.displayLog(text);
        animAlgo.reset();
    }


}

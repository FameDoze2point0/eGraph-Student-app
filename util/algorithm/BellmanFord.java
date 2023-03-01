package util.algorithm;
import util.*;
import java.util.*;
import java.awt.*;
public class BellmanFord extends Thread{
    private Graph graph;
    private Vertex start;
    private Color vertexDefaultColor, edgeDefaultColor;
    private AnimationAlgorithm animAlgo;

    public BellmanFord(Graph g, Vertex start){
        this.graph = g;
        this.start = start;
        this.vertexDefaultColor = g.getVertices().get(0).getBorderColor();
        this.edgeDefaultColor = g.getEdges().get(0).getStrokeColor();
        this.animAlgo = new AnimationAlgorithm(g, vertexDefaultColor,edgeDefaultColor, start);
    }

    public int[][] graphToArray(int size){

        int graphArr[][] = new int[size][size];
        for (int i = 0; i < graphArr.length; i++) 
            for (int j = 0; j < graphArr.length; j++) 
                graphArr[i][j] = 0;
            
        for (Edge edge : graph.getEdges()) 
            graphArr[edge.getStart().getId()][edge.getEnd().getId()] = (int)edge.getWeight();
            
        return graphArr;
    }

    Boolean search(int[] pred, ArrayList<Vertex> visited, ArrayList<Edge> browsed){
        Boolean flag = false;
        ArrayList<Vertex> vertices = graph.getVertices();
        ArrayList<Edge> edges = graph.getEdges();

        for (int i = 0; i < pred.length; i++) {
            if (pred[i] != -1) {
               if (!visited.contains(vertices.get(i))) {
                    visited.add(vertices.get(i));
                    flag = true;
               } 
               for (Edge edge : edges) {
                    if (edge.getStart().getId() == pred[i] && edge.getEnd().getId() == i && !browsed.contains(edge)) {
                        browsed.add(edge);
                        flag = true;
                    }
                    if (!graph.getOriented() && edge.getStart().getId() == i && edge.getEnd().getId() == pred[i] && !browsed.contains(edge)) {
                        browsed.add(edge);
                        flag = true;
                    }
               }
            }
        }
        return flag;
    }
    void clearEdge(int start, int end, ArrayList<Edge> browsed){
        Edge e1 = null, e2 = null;
        for (Edge edge : browsed) {
            if (edge.getStart().getId() == start && edge.getEnd().getId() == end) 
                e1 = edge;

            if (!graph.getOriented() && edge.getStart().getId() == end && edge.getEnd().getId() == start) 
                e2 = edge;
        } 
        if (e1 != null){
            e1.setStrokeColor(edgeDefaultColor);
            browsed.remove(e1);
        }
        if (e2 != null){
            e2.setStrokeColor(edgeDefaultColor);
            browsed.remove(e2);
        }   
    }

    @Override
    public void run() {
        // if (!weighted)
        //     throw new Exception("The graph has to be weighted to launch Bellman-Ford's Algorithm !");
        int size = graph.getVertices().size();
        int graphArr[][] = graphToArray(size);
        int dist[] = new int[size];
        int pred[] = new int[size];

        ArrayList<Vertex> visited = new ArrayList<Vertex>();
        ArrayList<Edge> browsed = new ArrayList<Edge>();

        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE/2;
            pred[i] = -1; 
        }
        dist[start.getId()] = 0;
        visited.add(start);

        for (int k = 0; k < size; k++) {
            
            for (int i = 0; i < graphArr.length; i++) {
                for (int j = 0; j < graphArr.length; j++) {
                    if (graphArr[i][j] != 0 && dist[i] + graphArr[i][j] < dist[j]) {
                        dist[j] = dist[i] + graphArr[i][j];
                        if(pred[j] != -1)
                            clearEdge(pred[j], j, browsed);
                        pred[j] = i;   
                    }
                }
                if (search(pred, visited, browsed)) { 
                    animAlgo.changeColor(visited, null, browsed);
                }
            }
        }
        animAlgo.changeColor(visited, null, browsed);
        animAlgo.reset();
        //printArray(dist); 
    }
}


    // @Override
    // public void run() {
    //     // if (!graph.getWeighted())
    //     //     throw new Exception("The graph has to be weighted to launch Dijkstra's Algorithm !");
    //     ArrayList<Vertex> vertices = graph.getVertices();
    //     int size = vertices.size();
    //     int graphArr[][] = graphToArray(size);

    //     ArrayList<Vertex> vertexVisited = new ArrayList<Vertex>();
    //     ArrayList<Edge> edgeBrowsed = new ArrayList<Edge>();

    //     int dist[] = new int[size];
    //     Boolean sptSet[] = new Boolean[size];

    //     for (int i = 0; i < size; i++) {
    //         dist[i] = Integer.MAX_VALUE;
    //         sptSet[i] = false;
    //     }
    //     dist[start.getId()] = 0;

    //     for (int count = 0; count < size ; count++) {
    //         int u = minDistance(dist,sptSet, size);
    //         searchVertex(u, vertexVisited);
    //         sptSet[u] = true;

    //         for (int i = 0; i < size; i++) 
    //             if (!sptSet[i] && graphArr[u][i] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graphArr[u][i] < dist[i]){
    //                 dist[i] = dist[u] + graphArr[u][i];
    //                 searchEdge(u, i, edgeBrowsed);
    //                 System.out.println("u = " + u + "i = " + i);
    //             } 
    //         animAlgo.changeColor(vertexVisited, null, edgeBrowsed);   
    //     }

    //     printArray(dist, vertices);
    //     animAlgo.reset();
    // }

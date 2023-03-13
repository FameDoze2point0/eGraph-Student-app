package util.algorithm;
import util.*;
import java.util.*;
import java.awt.*;

public class DFS extends Thread{
    private Graph graph;
    private Vertex start;
    private Color vertexDefaultColor, edgeDefaultColor;
    private AnimationAlgorithm animAlgo;

    public DFS(Graph g, Vertex start){
        this.graph = g;
        this.start = start;
        this.vertexDefaultColor = g.getVertices().get(0).getBorderColor();
        this.edgeDefaultColor = g.getEdges().get(0).getStrokeColor();
        this.animAlgo = new AnimationAlgorithm(g, vertexDefaultColor,edgeDefaultColor, start);
    }

    @Override
    public void run() {

        ArrayList<Vertex> visited = new ArrayList<Vertex>();
        ArrayList<Vertex> list = new ArrayList<Vertex>();
        ArrayList<Edge> edgeBrowsed = new ArrayList<Edge>();
        Vertex vertex;
        String rep = "answer";

        list.add(start);

        while (!list.isEmpty()) {
            vertex = list.get(0);
            list.remove(0);
            if (!visited.contains(vertex)) {
                    rep += " > " + vertex.getName();
                for (Edge edge : graph.getEdges()) {
                    if (vertex.equals(edge.getStart())) {
                        if (!list.contains(edge.getEnd()) && !visited.contains(edge.getEnd())) {
                            list.add(0,edge.getEnd()); 
                        }
                        edgeBrowsed.add(edge);
                    }
                    if (!graph.getOriented() && vertex.equals(edge.getEnd())) {
                        edgeBrowsed.add(edge);
                    }   
                }
                visited.add(vertex);
                animAlgo.changeColor(visited, list, edgeBrowsed);    
            }
        }
        System.out.println(rep);
        animAlgo.reset();  
    }

}
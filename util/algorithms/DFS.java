package util.algorithms;
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
        String text = "<html><body><blockquote><h1>Depth First Search</h1><p><h2>Step 0 </h2><br>"+animAlgo.matrixString()+"<br><br>visited (vertex) : "+visited.toString()+"<br>list (vertex): "+list.toString()+"<br>edgeBrowsed (start,end[,weight]): "+edgeBrowsed.toString()+"<br>";
        int i = 1;
        

        while (!list.isEmpty()) {
            text += "<h2>Step "+i+"</h2><br>visited (vertex) : "+visited.toString()+"<br>list (vertex): "+list.toString()+"<br>edgeBrowsed (start,end[,weight]): "+edgeBrowsed.toString()+"<br>";
            i++;
            vertex = list.get(0);
            list.remove(0);
            if (!visited.contains(vertex)) {
                    rep += " > " + vertex.getName();
                for (Edge edge : graph.getEdges()) {
                    if (vertex.equals(edge.getStart())) {
                        if (!list.contains(edge.getEnd()) && !visited.contains(edge.getEnd())) {
                            list.add(0,edge.getEnd()); 
                            text += edge.toString()+" isn't in neither list nor visited so we add it in list"+"<br>";
                        }
                        edgeBrowsed.add(edge);
                    }
                    if (!graph.getOriented() && vertex.equals(edge.getEnd())) {
                        edgeBrowsed.add(edge);
                    }   
                }
                visited.add(vertex);
                text += vertex.toString()+" is now visited !<br><br>";
                animAlgo.changeColor(visited, list, edgeBrowsed);    
            }
        }
        text += "<h2>Step "+i+"</h2><br>visited (vertex) : "+visited.toString()+"<br>list (vertex): "+list.toString()+"<br>edgeBrowsed (start,end[,weight]): "+edgeBrowsed.toString()+"<br><br></p></body></blockquote></html>";
        animAlgo.displayLog(text);
        System.out.println(rep);
        animAlgo.reset();  
    }

}

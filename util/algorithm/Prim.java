package util.algorithm;
import util.*;
import java.util.*;
import java.awt.*;
public class Prim extends Thread{
    private Graph graph;
    private Vertex start;
    private Color vertexDefaultColor, edgeDefaultColor;
    private AnimationAlgorithm animAlgo;

    public Prim(Graph g, Vertex start){
        this.graph = g;
        this.start = start;
        this.vertexDefaultColor = g.getVertices().get(0).getBorderColor();
        this.edgeDefaultColor = g.getEdges().get(0).getStrokeColor();
        this.animAlgo = new AnimationAlgorithm(g, vertexDefaultColor,edgeDefaultColor, start);
    }

    public ArrayList<Edge> getNeighbours(Vertex vertex, ArrayList<Edge> edges){

        ArrayList<Edge> neighbours = new ArrayList<Edge>();

        for (Edge edge : edges) {
            if (vertex.equals(edge.getStart())) {
                neighbours.add(edge);
            }
        }
        return neighbours;
    }
    
    @Override
    public void run() {
        ArrayList<Vertex> visited = new ArrayList<Vertex>();
        ArrayList<Edge> browsed = new ArrayList<Edge>();
        ArrayList<Triplet> inter = new ArrayList<Triplet>();
        int id = 0, max;

        ArrayList<Vertex> vertices = graph.getVertices();
        ArrayList<Edge> edges = graph.getEdges();
        
        visited.add(start);
        String text = "<html><body><blockquote><h1>Prim</h1><p><h2>Step 0 </h2><br>"+animAlgo.matrixString()+"<br><br>visited (vertex) : "+visited.toString()+"<br>edgeBrowsed (start,end[,weight]): "+browsed.toString()+"<br>";
        int n = 1;
        for (Edge edge : getNeighbours(start, edges)) 
            inter.add(new Triplet(edge.getEnd(), edge, (int)edge.getWeight()));
        

        while (!inter.isEmpty()) {
            max = Integer.MAX_VALUE;
            for (int i = 0; i < inter.size(); i++) 
                if (max > inter.get(i).getWeight()) {
                    max = inter.get(i).getWeight();
                    id = i;
                }
            
            Triplet temp = inter.get(id);
            inter.remove(temp);
            text += "<br><h2>Step "+n+"</h2><br>visited (vertex) : "+visited.toString()+"<br>edgeBrowsed (start,end[,weight]): "+browsed.toString()+"<br>edge select (start,end[,weight]): "+temp.getEdge().toString()+"<br>";
            n++;
            if (!visited.contains(temp.getVertex())) {
                visited.add(temp.getVertex());
                browsed.add(temp.getEdge());
                for (Edge edge : edges) {
                    if (temp.getEdge().getStart() == edge.getEnd() && temp.getEdge().getEnd() == edge.getStart()) {
                        browsed.add(edge);
                        break;
                    }
                }
                animAlgo.changeColor(visited, null, browsed);
                for (Edge edge : getNeighbours(temp.getVertex(), edges)) 
                    if (!visited.contains(edge.getEnd())) 
                        inter.add(new Triplet(edge.getEnd(), edge, (int)edge.getWeight()));
            }
        }
        text += "<br><h2>Step "+n+"</h2><br>visited (vertex) : "+visited.toString()+"<br>edgeBrowsed (start,end[,weight]): "+browsed.toString()+"<br>";
        animAlgo.displayLog(text);
        animAlgo.reset();
    }
}

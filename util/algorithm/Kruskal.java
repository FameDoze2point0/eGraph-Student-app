package util.algorithm;
import util.*;
import java.util.*;
import java.awt.*;
public class Kruskal extends Thread {
    
    private Graph graph;
    private Color vertexDefaultColor, edgeDefaultColor;
    private AnimationAlgorithm animAlgo;

    public Kruskal(Graph g){
        this.graph = g;
        this.vertexDefaultColor = g.getVertices().get(0).getBorderColor();
        this.edgeDefaultColor = g.getEdges().get(0).getStrokeColor();
        this.animAlgo = new AnimationAlgorithm(g, vertexDefaultColor,edgeDefaultColor);
    }

    // Breadth-first search / Parcours largeur
    private ArrayList<Vertex> BFS(Vertex start, Graph graph){
        ArrayList<Vertex> visited = new ArrayList<Vertex>();
        ArrayList<Vertex> list = new ArrayList<Vertex>();
        ArrayList<Edge> edges = graph.getEdges();

        list.add(start);
        visited.add(start);
        while (!list.isEmpty()) {
            Vertex vertex = list.get(0);
            list.remove(0);

            for (Edge edge : edges) {
                if (vertex.equals(edge.getStart()) && !list.contains(edge.getEnd()) && !visited.contains(edge.getEnd())){
                    list.add(edge.getEnd());
                }      
            }
            visited.add(vertex);
        }
        return visited;
    }



    public Boolean isInCc(Vertex a, Graph g, Vertex b){
        ArrayList<Vertex> visited =  BFS(a,g);
        return (visited.contains(b)?true:false);
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

        ArrayList<Vertex> vertices = graph.getVertices();
        ArrayList<Edge> edges = graph.getEdges();
        ArrayList<Triplet> L = new ArrayList<Triplet>();
        ArrayList<Edge> browsed = new ArrayList<Edge>();
        ArrayList<Vertex> visited = new ArrayList<Vertex>();

        Graph ACPM = new Graph("ACPM", false, true, null);
        for (Vertex vertex : vertices) 
            ACPM.addVertex(vertex);

        for (Vertex vertex : vertices) {
            for (Edge edge : getNeighbours(vertex, edges)) {
                L.add(new Triplet(null, edge, (int)edge.getWeight()));
            }
        }
        Collections.sort(L);
        for (Triplet tri : L) {
            System.out.println(tri);
        }


        while (!L.isEmpty()) {
            Triplet temp = L.get(0);
            L.remove(0);
            if (!isInCc(temp.getEdge().getStart(), ACPM, temp.getEdge().getEnd())) {
                ACPM.addEdge(temp.getEdge());
                browsed.add(temp.getEdge());
                for (Edge edge : edges) {
                    if (temp.getEdge().getStart() == edge.getEnd() && temp.getEdge().getEnd() == edge.getStart()) {
                        browsed.add(edge);
                        break;
                    }
                }
                if (!visited.contains(temp.getEdge().getStart())) {
                    visited.add(temp.getEdge().getStart());
                }
                if (!visited.contains(temp.getEdge().getEnd())) {
                    visited.add(temp.getEdge().getEnd());
                }
                animAlgo.changeColor(visited, null, browsed);
            }
        }

        animAlgo.reset();
    }
}

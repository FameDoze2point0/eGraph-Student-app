package util;
public class Triplet implements Comparable<Triplet>{
    private Vertex vertex;
    private Edge edge;
    private int weight;

    public Triplet(Vertex vertex, Edge edge, int weight) {
        this.vertex = vertex;
        this.edge = edge;
        this.weight = weight;
    }

    public int compareTo(Triplet compareTo){
        if (weight == compareTo.weight){
            return 0;
        }
        if (weight > compareTo.weight){
            return 1;
        }
        return -1;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    @Override
    public String toString() {
        String rep = "";
        if (vertex != null) {
            rep = "Vertex : "+vertex.getId()+" to "+edge.getEnd().getId()+" with "+edge.getWeight(); 
        }else{
            rep = "edge "+edge.getStart().getId()+"-->"+edge.getEnd().getId()+" with "+edge.getWeight();
        }
        return rep;
    }
    
}

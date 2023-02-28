package util;
public class Triplet implements Comparable<Triplet>{
    Vertex vertex;
    Edge edge;
    int weight;

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
}

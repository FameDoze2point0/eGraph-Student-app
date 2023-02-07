package util;

import java.util.ArrayList;

public class Graph {
    
    private String name;
    private ArrayList<Vertex> vertices;
    private Boolean oriented, weighted;

    public Graph(String name, Boolean oriented, Boolean weighted) {
        this.name = name;
        this.vertices = new ArrayList<>();
        this.oriented = oriented;
        this.weighted = weighted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public Boolean getOriented() {
        return oriented;
    }

    public void setOriented(Boolean oriented) {
        this.oriented = oriented;
    }

    public Boolean getWeighted() {
        return weighted;
    }

    public void setWeighted(Boolean weighted) {
        this.weighted = weighted;
    }

        


}

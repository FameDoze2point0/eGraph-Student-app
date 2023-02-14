package util;

import java.util.ArrayList;

public class Graph {
    
    private String name;
    private ArrayList<Vertex> vertices;
    private Boolean oriented, weighted;

    public Graph(String name, Boolean oriented, Boolean weighted) {
        this.name = name;
        this.vertices = new ArrayList<Vertex>();
        this.oriented = oriented;
        this.weighted = weighted;
    }



    // Algorithm Part
    //Breadth-first search / Parcours largeur
    public ArrayList<Vertex> algo_BFS(Vertex start){

        ArrayList<Vertex> visited = new ArrayList<Vertex>();
        ArrayList<Vertex> list = new ArrayList<Vertex>();
        Vertex vertex;
        String rep = "answer";

        list.add(start);
        visited.add(start);

        while (!list.isEmpty()) {
            vertex = list.get(0);
            list.remove(0);
            rep += " > " + vertex.getName();
            for (Edge edge : vertex.getEdgeList()) {
                if (!list.contains(edge.getEnd()) && !visited.contains(edge.getEnd())) 
                    list.add(edge.getEnd());
            }
            visited.add(vertex);
        }
        System.out.println(rep);
        
        return visited;
    }

    //Deep-first search / Parcours profondeur
    public ArrayList<Vertex> algo_DFS(Vertex start){

        ArrayList<Vertex> visited = new ArrayList<Vertex>();
        ArrayList<Vertex> list = new ArrayList<Vertex>();
        Vertex vertex;
        String rep = "answer";

        list.add(start);
        visited.add(start);

        while (!list.isEmpty()) {
            vertex = list.get(0);
            list.remove(0);
            rep += " > " + vertex.getName();
            for (Edge edge : vertex.getEdgeList()) {
                if (!list.contains(edge.getEnd()) && !visited.contains(edge.getEnd())) 
                    list.add(0,edge.getEnd());
                    
            }
            visited.add(vertex);
        }
        System.out.println(rep);
        
        return visited;
    }

    //Random Search / Parcours quelconque
    public ArrayList<Vertex> algo_RS(Vertex start){

        ArrayList<Vertex> visited = new ArrayList<Vertex>();
        ArrayList<Vertex> list = new ArrayList<Vertex>();
        Vertex vertex;
        String rep = "answer";

        list.add(start);
        visited.add(start);

        while (!list.isEmpty()) {
            int i = (int)(Math.random()*list.size());
            vertex = list.get(i);
            list.remove(i);
            rep += " > " + vertex.getName();
            for (Edge edge : vertex.getEdgeList()) {
                if (!list.contains(edge.getEnd()) && !visited.contains(edge.getEnd())) 
                    list.add(edge.getEnd());
            }
            visited.add(vertex);
        }
        System.out.println(rep);
        
        return visited;
    }





    public int[][] graphToArray(){

        int graph[][] = new int[vertices.size()][vertices.size()];
        for (int i = 0; i < graph.length; i++) 
            for (int j = 0; j < graph.length; j++) 
                graph[i][j] = 0;
            
        for (Vertex vertex : vertices) 
            for (Edge edge : vertex.getEdgeList()) 
                graph[edge.getStart().getId()][edge.getEnd().getId()] = (int)edge.getWeight();
            
        return graph;
    }


    public int minDistance(int dist[],Boolean sptSet[]){
        
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int i = 0; i < vertices.size(); i++) 
            if (!sptSet[i] && dist[i] <= min) {
                min = dist[i];
                min_index = i;
            }
        
        return min_index;
    }

    public void printArray(int dist[]){
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < vertices.size(); i++)
            System.out.println(vertices.get(i).getName() + " \t\t " + dist[i]);
    }

    //Dijkstra
    public void algo_Dijkstra(Vertex start){

        int graph[][] = graphToArray();
        int dist[] = new int[vertices.size()];
        Boolean sptSet[] = new Boolean[vertices.size()];

        for (int i = 0; i < vertices.size(); i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
        dist[start.getId()] = 0;

        for (int count = 0; count < vertices.size() - 1; count++) {
            int u = minDistance(dist,sptSet);
            sptSet[u] = true;

            for (int i = 0; i < vertices.size(); i++) 
                if (!sptSet[i] && graph[u][i] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][i] < dist[i]) 
                    dist[i] = dist[u] + graph[u][i];
        }
        printArray(dist);
    }

    //Bellman-Ford
    public void algo_BellmanFord(Vertex start){

        int graph[][] = graphToArray();
        int dist[] = new int[vertices.size()];
        int pred[] = new int[vertices.size()];

        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1; 
        }
        dist[start.getId()] = 0;
        for (int k = 1; k < vertices.size(); k++) {
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++) {
                    if (graph[i][j] != 0 && dist[i] + graph[i][j] < dist[j]) {
                        dist[j] = dist[i] + graph[i][j];
                        pred[j] = i;
                    }
                }
            }
        }
        printArray(dist);
    }


    //Floyd-Warshall
    public void algo_FloydWarshall(Vertex start){

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

    public void addVertex(Vertex v){
        vertices.add(v);
    }        
    public void removeVertex(Vertex v){
        if (vertices.contains(v))
            vertices.remove(v);
    }

    @Override
    public String toString() {
        String rep = "Graph [name=" + name + ", nb vertices=" + vertices.size() + ", oriented=" + oriented + ", weighted=" + weighted + "]\n"; 
        
        for (Vertex vertex : vertices) {
            rep += "\t" + vertex.getName() + " {";
            for (Edge edge : vertex.getEdgeList())
                if (weighted)
                    rep += "("+edge.getEnd().getName()+","+edge.getWeight()+")";
                else
                    rep += "("+edge.getEnd().getName()+")";

                rep += "}\n";
        }
        return rep;
    }

    
}

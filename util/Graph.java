package util;

import java.util.*;

import javax.swing.*;

import java.awt.*;

public class Graph {
    
    private String name;
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;
    private Boolean oriented, weighted;

    public Graph(String name, Boolean oriented, Boolean weighted) {
        this.name = name;
        this.vertices = new ArrayList<Vertex>();
        this.oriented = oriented;
        this.weighted = weighted;
        this.edges = new ArrayList<Edge>();
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
            for (Edge edge : edges) {
                if (vertex.equals(edge.getStart()) && !list.contains(edge.getEnd()) && !visited.contains(edge.getEnd())) 
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

        while (!list.isEmpty()) {
            vertex = list.get(0);
            list.remove(0);
            if (!visited.contains(vertex)) {
                    rep += " > " + vertex.getName();
                for (Edge edge : edges) {
                    if (vertex.equals(edge.getStart()) && !visited.contains(edge.getEnd())) 
                        list.add(0,edge.getEnd());   
                }
                visited.add(vertex);    
            }
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
            for (Edge edge : edges) {
                if ( vertex.equals(edge.getStart())&& !list.contains(edge.getEnd()) && !visited.contains(edge.getEnd())) 
                    list.add(edge.getEnd());
            }
            visited.add(vertex);
        }
        System.out.println(rep);
        
        return visited;
    }





    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }



    public int[][] graphToArray(){

        int graph[][] = new int[vertices.size()][vertices.size()];
        for (int i = 0; i < graph.length; i++) 
            for (int j = 0; j < graph.length; j++) 
                graph[i][j] = 0;
            
        for (Edge edge : edges) 
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
    public void algo_Dijkstra(Vertex start) throws Exception{

        if (!weighted)
            throw new Exception("The graph has to be weighted to launch Dijkstra's Algorithm !");

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
    public void algo_BellmanFord(Vertex start) throws Exception{

        if (!weighted)
            throw new Exception("The graph has to be weighted to launch Bellman-Ford's Algorithm !");

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
    public void algo_FloydWarshall(){

        int n = vertices.size();
        int [][] M = new int [n][n];
        Graph g = new Graph("Floyd-Warshall", oriented, weighted);
        for (Vertex vertex : vertices)
            g.addVertex(vertex);
        
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                M[i][j] = Integer.MAX_VALUE/2;
                if (i == j)
                    M[i][j] = 0;
            }
        }
        for (Edge edge : edges) 
            M[vertices.indexOf(edge.getStart())][vertices.indexOf(edge.getEnd())] = (int)edge.getWeight();
        
        for (int k = 0; k < M.length; k++) {
            for (int i = 0; i < M.length; i++) {
                for (int j = 0; j < M.length; j++) {
                    M[i][j] = Integer.min(M[i][j], M[i][k] + M[k][j]);
                }
            }
        }

        String res = "";
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                res += M[i][j] + "\t";
            }
            res += "\n";
        }
        System.out.println(res);

        for (int i = 0; i < M.length; i++) 
            for (int j = 0; j < i; j++) 
                g.addEdge(vertices.get(i), vertices.get(j), M[i][j], 1, Color.black);
                    
        System.out.println(g.toString());

    }


    public ArrayList<Edge> getNeighbours(Vertex vertex){

        ArrayList<Edge> neighbours = new ArrayList<Edge>();

        for (Edge edge : edges) {
            if (vertex.equals(edge.getStart())) {
                neighbours.add(edge);
            }
        }
        return neighbours;
    }

    public void algo_Prim(Vertex start) throws Exception{

        if (oriented || !weighted)
            throw new Exception("The graph has to be not oriented and weighted to launch Prim's Algorithm !");

        Graph ACPM = new Graph("ACPM", oriented, weighted);
        ArrayList<Vertex> set = new ArrayList<Vertex>();
        ArrayList<Triplet> inter = new ArrayList<Triplet>();
        int id = 0, max;

        for (Vertex vertex : vertices) 
            ACPM.addVertex(vertex);
        
        set.add(start);
        for (Edge edge : getNeighbours(start)) 
            inter.add(new Triplet(edge.getEnd(), edge, (int)edge.getWeight()));
        

        while (!inter.isEmpty()) {
            max = Integer.MAX_VALUE;
            for (int i = 0; i < inter.size(); i++) 
                if (max > inter.get(i).weight) {
                    max = inter.get(i).weight;
                    id = i;
                }
            
            Triplet temp = inter.get(id);
            inter.remove(temp);

            if (!set.contains(temp.vertex)) {
                set.add(temp.vertex);
                ACPM.addEdge(temp.edge);
                for (Edge edge : getNeighbours(temp.vertex)) 
                    if (!set.contains(edge.getEnd())) 
                        inter.add(new Triplet(edge.getEnd(), edge, (int)edge.getWeight()));
            }
        }
        System.out.println("Minimum Spanning Tree with Prim : \n" + ACPM.toString());
    }

    public Boolean isInCc(Vertex a, Graph g, Vertex b){
        ArrayList<Vertex> visited =  g.algo_DFS(a);
        
        return (visited.contains(b)?true:false);
    }

    public void algo_Kruskal(){

        Graph ACPM = new Graph("ACPM", oriented, weighted);
        for (Vertex vertex : vertices) 
            ACPM.addVertex(vertex);
        ArrayList<Triplet> L = new ArrayList<Triplet>();

        for (Vertex vertex : vertices) {
            for (Edge edge : getNeighbours(vertex)) {
                L.add(new Triplet(null, edge, (int)edge.getWeight()));
            }
        }
        Collections.sort(L);


        while (!L.isEmpty()) {
            Triplet temp = L.get(0);
            L.remove(0);
            if (!isInCc(temp.edge.getStart(), ACPM, temp.edge.getEnd())) {
                ACPM.addEdge(temp.edge);
            }
        }

        System.out.println(ACPM.toString());
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

    public void addEdge(Edge e){
        
        Boolean existed = false;

        for (Edge edge : edges) 
            if (edge.getStart().equals(e.getStart()) && edge.getEnd().equals(e.getEnd())) {
                existed = true;
                JDialog jdg = new JDialog();
                JLabel jl = new JLabel("The edge that you've tried to add already exists");
                jl.setHorizontalAlignment(SwingConstants.CENTER);
                jl.setVerticalAlignment(SwingConstants.CENTER);
                jl.setForeground(Color.red);
                jl.setFont(new Font("Arial",Font.BOLD,20));


                jdg.add(jl);
                jdg.setSize(500,100);
                jdg.setModal(true);
                jdg.setLocationRelativeTo(null);
                jdg.setVisible(true);

                break;
            }
        if (!existed) {
            edges.add(e);
            if (!oriented && !e.getStart().equals(e.getEnd()))
                edges.add(new Edge(e.getEnd(), e.getStart(), e.getWeight(), e.getStrokeWidth(), e.getStrokeColor()));
        }
    }

    public void addEdge(Vertex v1, Vertex v2, Object weight, int strokeWidth, Color color){
        if (!oriented) 
            edges.add(new Edge(v2, v1, weight, strokeWidth, color));    
        
        edges.add(new Edge(v1, v2, weight, strokeWidth, color));
    }
    public void removeEdge(Edge e){
        if(edges.contains(e))
            edges.remove(e);
    }

    @Override
    public String toString() {
        String rep = "Graph [name=" + name + ", nb vertices=" + vertices.size() + ", oriented=" + oriented + ", weighted=" + weighted + "]\n"; 
        
        for (Vertex vertex : vertices) {
            rep += "\t" + vertex.getName() + " {";

            for (Edge edge : edges) {
                if (vertex.equals(edge.getStart())) {
                    if (weighted) 
                        rep += "("+edge.getEnd().getName()+","+edge.getWeight()+")";
                    else
                        rep += "("+edge.getEnd().getName()+")";
                }
            }

                rep += "}\n";
        }
        return rep;
    }

    
}

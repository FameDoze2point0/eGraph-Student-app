package util;

import java.util.*;

import javax.swing.*;

import gui.draw.PanelPaint;

import java.awt.*;

public class Graph {
    
    private String name;
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;
    private Boolean oriented, weighted;
    private PanelPaint panelPaint;
    private Color vertexInsideColor = Color.white, 
                  vertexOutsideColor = Color.black, 
                  edgeStrokeColor = Color.black, 
                  edgeHighlightColor = Color.white,
                  vertexNameColor = Color.black;
    private int edgeStrokeWidth = 5, arrowLength = 15; //For edges
    private int vertexDiameter = 30, vertexStrokeWidth = 5; //For vertexs




    public Graph(String name, Boolean oriented, Boolean weighted, PanelPaint panelPaint) {
        this.name = name;
        this.vertices = new ArrayList<Vertex>();
        this.oriented = oriented;
        this.weighted = weighted;
        this.edges = new ArrayList<Edge>();
        this.panelPaint = panelPaint;
    }

    public void paint(Graphics graphics, Object collision){
        for (Edge edge : edges) {
            edge.paint(graphics, oriented, weighted, collision);
        }
        for (Vertex vertex : vertices) {
            vertex.paint(graphics, collision);
        }
        
    }
    
    //DFS
    {
// Algorithm Part
    // Breadth-first search / Parcours largeur
    // public ArrayList<Vertex> algo_BFS(Vertex start){

        
        // ArrayList<Vertex> visited = new ArrayList<Vertex>();
        // ArrayList<Vertex> list = new ArrayList<Vertex>();
        // String rep = "answer";

        // list.add(start);
        // visited.add(start);

        // while (!list.isEmpty()) {
        //     Vertex vertex = list.get(0);
        //     list.remove(0);
        //     rep += " > " + vertex.getName();
        //     for (Edge edge : edges) {
        //         if (vertex.equals(edge.getStart()) && !list.contains(edge.getEnd()) && !visited.contains(edge.getEnd())){
        //             list.add(edge.getEnd());

        //             SwingUtilities.invokeLater(new Runnable() {
        //                 @Override
        //                 public void run() {
        //                     // TODO Auto-generated method stub
        //                     edge.getEnd().setBorderColor(Color.orange);
        //                 }
        //             });
        //         } 
                    
        //     }
        //     visited.add(vertex);
        //     SwingUtilities.invokeLater(new Runnable() {
        //         @Override
        //         public void run() {
        //             // TODO Auto-generated method stub
        //             vertex.setBorderColor(Color.red);
        //             try {
        //                 System.out.println(Thread.currentThread());
        //             } catch (Exception e) {
        //                 // TODO: handle exception
        //             }
        //         }
        //     });
        // }
        // //start.setBorderColor(Color.magenta);
        // System.out.println(rep);
        // //resetColor();
        // return visited;
    //}

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

    public void printArray(int dist[]){
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < vertices.size(); i++)
            System.out.println(vertices.get(i).getName() + " \t\t " + dist[i]);
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
                        System.out.println("je pars de i = " + i + " vers j = " + j);
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
        Graph g = new Graph("Floyd-Warshall", oriented, weighted, null);
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
                g.addEdge(vertices.get(i), vertices.get(j), M[i][j]);
                    
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

        Graph ACPM = new Graph("ACPM", oriented, weighted, null);
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

    // public Boolean isInCc(Vertex a, Graph g, Vertex b){
    //     ArrayList<Vertex> visited =  g.algo_DFS(a);
        
    //     return (visited.contains(b)?true:false);
    // }

    // public void algo_Kruskal(){

    //     Graph ACPM = new Graph("ACPM", oriented, weighted, null);
    //     for (Vertex vertex : vertices) 
    //         ACPM.addVertex(vertex);
    //     ArrayList<Triplet> L = new ArrayList<Triplet>();

    //     for (Vertex vertex : vertices) {
    //         for (Edge edge : getNeighbours(vertex)) {
    //             L.add(new Triplet(null, edge, (int)edge.getWeight()));
    //         }
    //     }
    //     Collections.sort(L);


    //     while (!L.isEmpty()) {
    //         Triplet temp = L.get(0);
    //         L.remove(0);
    //         if (!isInCc(temp.edge.getStart(), ACPM, temp.edge.getEnd())) {
    //             ACPM.addEdge(temp.edge);
    //         }
    //     }

    //     System.out.println(ACPM.toString());
    // }







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
                edges.add(new Edge(e.getEnd(), e.getStart(), e.getWeight(), edgeStrokeWidth, edgeStrokeColor,edgeHighlightColor));
        }
    }

    public void addEdge(Vertex v1, Vertex v2, Object weight){
        if (!oriented) 
            edges.add(new Edge(v2, v1, weight, edgeStrokeWidth, edgeStrokeColor,edgeHighlightColor));    
        
        edges.add(new Edge(v1, v2, weight, edgeStrokeWidth, edgeStrokeColor,edgeHighlightColor));
    }
    public void removeEdge(Edge e){
        if(edges.contains(e))
            edges.remove(e);
    }

    public PanelPaint getPanelPaint() {
        return panelPaint;
    }

    public void setPanelPaint(PanelPaint panelPaint) {
        this.panelPaint = panelPaint;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public Color getVertexInsideColor() {
        return vertexInsideColor;
    }

    public void setVertexInsideColor(Color vertexInsideColor) {
        this.vertexInsideColor = vertexInsideColor;
    }

    public Color getVertexOutsideColor() {
        return vertexOutsideColor;
    }

    public void setVertexOutsideColor(Color vertexOutsideColor) {
        this.vertexOutsideColor = vertexOutsideColor;
    }

    public Color getEdgeStrokeColor() {
        return edgeStrokeColor;
    }

    public void setEdgeStrokeColor(Color edgeStrokeColor) {
        this.edgeStrokeColor = edgeStrokeColor;
    }

    public Color getEdgeHighlightColor() {
        return edgeHighlightColor;
    }

    public void setEdgeHighlightColor(Color edgeHighlightColor) {
        this.edgeHighlightColor = edgeHighlightColor;
    }

    public int getEdgeStrokeWidth() {
        return edgeStrokeWidth;
    }

    public void setEdgeStrokeWidth(int edgeStrokeWidth) {
        this.edgeStrokeWidth = edgeStrokeWidth;
    }

    public int getVertexDiameter() {
        return vertexDiameter;
    }

    public void setVertexDiameter(int vertexDiameter) {
        this.vertexDiameter = vertexDiameter;
    }

    public Color getVertexNameColor() {
        return vertexNameColor;
    }

    public void setVertexNameColor(Color vertexNameColor) {
        this.vertexNameColor = vertexNameColor;
    }

    public int getVertexStrokeWidth() {
        return vertexStrokeWidth;
    }

    public void setVertexStrokeWidth(int vertexStrokeWidth) {
        this.vertexStrokeWidth = vertexStrokeWidth;
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

    public int getArrowLength() {
        return arrowLength;
    }

    public void setArrowLength(int arrowLength) {
        this.arrowLength = arrowLength;
    }

    
}

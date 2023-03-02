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
                  edgeArrowTipColor = Color.red,
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

    //Function that return the first edge we are on top of, else null
    public Edge edgeCollision( int X, int Y)
    {
        for(Edge edge : edges)
        {
            if(edge.getCollisionArea().contains(X,Y))
            {
                Collections.swap(edges, 0, edges.indexOf(edge));
                return edge;
            }
        }

        return null;
    }

    //Function that return the first vertex we are on top of, else null
    public Vertex vertexCollision( int X, int Y)
    {
        for(Vertex vertex : vertices)
        {
            //Formula to detect if the mouse is on top of a vertex
            if(((X - (vertex.getCoordX()+vertex.getDiameter()/2))*(X - (vertex.getCoordX()+vertex.getDiameter()/2)) + (Y - (vertex.getCoordY()+vertex.getDiameter()/2))*(Y - (vertex.getCoordY()+vertex.getDiameter()/2))) <= (vertex.getDiameter()/2)*(vertex.getDiameter()/2))
            {
                //We put the detected vertex on top of the list
                Collections.swap(vertices, 0, vertices.indexOf(vertex));
                return vertex;
            }
        }

        return null;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
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
                edges.add(new Edge(e.getEnd(), e.getStart(), e.getWeight(), edgeStrokeWidth, edgeStrokeColor,edgeHighlightColor, edgeArrowTipColor));
        }
    }

    public void addEdge(Vertex v1, Vertex v2, Object weight){
        if (!oriented) 
            edges.add(new Edge(v2, v1, weight, edgeStrokeWidth, edgeStrokeColor,edgeHighlightColor, edgeArrowTipColor));    
        
        edges.add(new Edge(v1, v2, weight, edgeStrokeWidth, edgeStrokeColor,edgeHighlightColor, edgeArrowTipColor));
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

    public Color getEdgeArrowTipColor() {
        return edgeArrowTipColor;
    }

    public void setEdgeArrowTipColor(Color edgeArrowTipColor) {
        this.edgeArrowTipColor = edgeArrowTipColor;
    }

    
    
}

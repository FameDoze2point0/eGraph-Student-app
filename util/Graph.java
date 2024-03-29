package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import gui.Gui;
import gui.draw.PanelPaint;

public class Graph implements Serializable, Cloneable
{    
    private int cpt = 0;
    private String name;
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;
    private Boolean oriented, weighted;
    private PanelPaint panelPaint;
    private ArrayList<ArrayList<Boolean>> existingEdge;
    private Boolean alertDisplay;

    

    public Graph(String name, Boolean oriented, Boolean weighted, PanelPaint panelPaint) {
        this.name = name;
        this.vertices = new ArrayList<Vertex>();
        this.oriented = oriented;
        this.weighted = weighted;
        this.edges = new ArrayList<Edge>();
        this.panelPaint = panelPaint;
        this.existingEdge = new ArrayList<>();
        this.alertDisplay = true;
    }

    public Graph clone()
    {
        try
        {
            Graph graph = (Graph)super.clone();
            graph.setName(name);
            graph.setVertices((ArrayList<Vertex>)vertices.clone());
            graph.setEdges((ArrayList<Edge>)edges.clone());
            graph.setExistingEdge((ArrayList<ArrayList<Boolean>>)existingEdge.clone());
            return graph;
        }
        
        catch (Exception e)
        {
            System.out.println(e.toString());
            return null;
        }
    }


    public void paint(Graphics graphics, Object collision){

        for (Edge edge : edges) {
            edge.paint(graphics, oriented, weighted, collision, this, bothDirections(edge));
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

     //Return true if there is two edges going from start to finish in the edge in parameter and finish to start in an other edge
     public Boolean bothDirections(Edge edge)
     {
         //The edge in parameter (start to finish) exist, we just check here if end to start also exist
        if(existingEdge.get(edge.getEnd().getId()).get(edge.getStart().getId()) == true)
        {
            return true;
        }
        else
        {
            return false;
        }
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

    public void addVertex(Vertex v)
    {
        vertices.add(v);
        existingEdge.add(new ArrayList<Boolean>());

        for(Vertex vertex : vertices)
        {
            existingEdge.get(v.getId()).add(false);
            existingEdge.get(vertex.getId()).add(false);
        }
    }       
    public void removeVertex(Vertex v){
        if (vertices.contains(v)){
            vertices.remove(v);
        }
            
    }

    public void addEdge(Edge e)
    {    
        Boolean existed = false;

        if (alertDisplay) {
            for (Edge edge : edges){
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
            }
        }     
        if (!existed )
        {
            edges.add(e);
            if (!e.getStart().equals(e.getEnd()))
            {
                if(!oriented)
                {
                    edges.add(new Edge(e.getEnd(), e.getStart(), e.getWeight(), Gui.getSettings().getEdgeStrokeWidth(), Gui.getSettings().getEdgeStrokeColor(),Gui.getSettings().getEdgeHighlightColor(), Gui.getSettings().getEdgeArrowTipColor(), Gui.getSettings().getEdgeWeightColor(), Gui.getSettings().getEdgeWeightBorderColor(), this, Gui.getSettings().getArrowLength()));
                    existingEdge.get(e.getStart().getId()).set(e.getEnd().getId(), true);
                    existingEdge.get(e.getEnd().getId()).set(e.getStart().getId(), true);
                }
                else
                {
                    //We add the new ending point
                    existingEdge.get(e.getStart().getId()).set(e.getEnd().getId(), true);
                }
            }

        }
    }

    public void addEdge(Vertex v1, Vertex v2, Object weight){
        if (!oriented) 
            edges.add(new Edge(v2, v1, weight, Gui.getSettings().getEdgeStrokeWidth(), Gui.getSettings().getEdgeStrokeColor(),Gui.getSettings().getEdgeHighlightColor(), Gui.getSettings().getEdgeArrowTipColor(), Gui.getSettings().getEdgeWeightColor(),Gui.getSettings().getEdgeWeightBorderColor(),this,Gui.getSettings().getArrowLength()));    
        
        edges.add(new Edge(v1, v2, weight, Gui.getSettings().getEdgeStrokeWidth(), Gui.getSettings().getEdgeStrokeColor(),Gui.getSettings().getEdgeHighlightColor(), Gui.getSettings().getEdgeArrowTipColor(), Gui.getSettings().getEdgeWeightColor(),Gui.getSettings().getEdgeWeightBorderColor(),this,Gui.getSettings().getArrowLength()));
    }
    public void removeEdge(Edge e){
        if(edges.contains(e)){
            edges.remove(e);
            existingEdge.get(e.getStart().getId()).set(e.getEnd().getId(), false);
        }
           
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

    public int getCpt() {
        return cpt++;
    }

    public void setCpt(int cpt) {
        this.cpt = cpt;
    }

    public ArrayList<ArrayList<Boolean>> getExistingEdge() {
        return existingEdge;
    }

    public void setExistingEdge(ArrayList<ArrayList<Boolean>> existingEdge) {
        this.existingEdge = existingEdge;
    }

    public Boolean getAlertDisplay() {
        return alertDisplay;
    }
    
    public void switchDisplay(){
        if (alertDisplay) {
            alertDisplay = false;
        }else{
            alertDisplay = true;
        }
    }
}

package util.algorithm;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import gui.draw.PanelPaint;
import util.*;

public class AnimationAlgorithm {

    private Graph graph;
    private Vertex start = null;
    private Color defaultColorVertex, defaultColorEdge;

    public AnimationAlgorithm(Graph graph, Color defaultColorVertex, Color defaultColorEdge){
        this.graph = graph;
        this.defaultColorVertex = defaultColorVertex;
        this.defaultColorEdge = defaultColorEdge;
    }
    public AnimationAlgorithm(Graph graph, Color defaultColor, Color defaultColorEdge, Vertex start){
        this.graph = graph;
        this.defaultColorVertex = defaultColor;
        this.start = start;
        this.defaultColorEdge = defaultColorEdge;
    }
    
    public void reset(){
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            // TODO: handle exception
        }
        for (Vertex vertex : graph.getVertices()) {
            vertex.setBorderColor(defaultColorVertex);
        }
        for (Edge edge : graph.getEdges()) {
            edge.setStrokeColor(defaultColorEdge);
        }
        graph.getPanelPaint().repaint();
    }

    public void changeColor(ArrayList<Vertex> visited, ArrayList<Vertex> toVisite, ArrayList<Edge> edgeBrowsed){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                for (Vertex vertex : toVisite) {
                    vertex.setBorderColor(Color.orange);
                }
                for (Vertex vertex : visited) {
                    vertex.setBorderColor(Color.red);
                }
                for (Edge edge : edgeBrowsed) {
                    edge.setStrokeColor(Color.orange);
                }
            }
        });
        
        graph.getPanelPaint().repaint();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

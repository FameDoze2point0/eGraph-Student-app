package util.algorithms;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import gui.draw.PanelPaint;
import util.*;

public class AnimationAlgorithm {

    private Graph graph;
    private Vertex start = null;
    private Color defaultColorVertex, defaultColorEdge;

    private Color red = new Color(255, 0, 0),
        darkOrange = new Color(255, 107, 51), 
        orange = new Color(255, 181, 102),
        lightred = new Color(255,139,139);

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
                if (toVisite != null) {
                    for (Vertex vertex : toVisite) {
                        vertex.setBorderColor(lightred);
                    }    
                }
                if (visited != null) {
                    for (Vertex vertex : visited) {
                        vertex.setBorderColor(red);
                    }
                }
                if (edgeBrowsed != null) {
                    for (Edge edge : edgeBrowsed) {
                        edge.setStrokeColor(orange);
                    }    
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

    public void refresh(){
        graph.getPanelPaint().repaint();
        try {
            Thread.sleep(2500);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private JDialog jd;
        private JScrollPane global;
        private JLabel content;

    public void displayLog(String log){
        jd = new JDialog();
        jd.setSize(850,600);
        jd.setLocationRelativeTo(null); //Centering the frame
        jd.setResizable(false);
        jd.setAlwaysOnTop(true);
        jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        global = new JScrollPane();
        global.setViewportView(new JLabel(log));      
        jd.add(global);
        jd.setVisible(true);
    }


    public String matrixString(){

        ArrayList<Vertex> vertices = graph.getVertices();
        ArrayList<Edge> edges = graph.getEdges();
        Object[][] matrice = new Object[vertices.size()][vertices.size()];

        if (graph.getWeighted()) {
            for (int i = 0; i < matrice.length; i++) {
                for (int j = 0; j < matrice.length; j++) {
                    matrice[i][j] = 0; 
                }
            }    
            for (Edge edge : edges) {
                matrice[edge.getStart().getId()][edge.getEnd().getId()] = edge.getWeight();
            }
        }else{
            for (int i = 0; i < matrice.length; i++) {
                for (int j = 0; j < matrice.length; j++) {
                    matrice[i][j] = "N"; 
                }
            } 
            for (Edge edge : edges) {
                matrice[edge.getStart().getId()][edge.getEnd().getId()] = "Y";
            }
        }

        String text = "";
        for (int i = 0; i < matrice.length; i++) {
            text += vertices.get(i).toString() + " :  [ ";
            for (int j = 0; j < matrice.length - 1; j++) {
                text += matrice[i][j] + ", ";
            }
            text += matrice[i][matrice.length - 1] + " ]<br>";
        }

        return text;
    }
}

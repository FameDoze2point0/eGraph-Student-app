package util.algorithms;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.dnd.DragGestureEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import gui.Gui;
import gui.draw.Draw;
import gui.draw.PanelPaint;
import util.*;

public class AnimationAlgorithm {

    private Graph graph;
    private Vertex start = null;
    private Color defaultColorVertex, defaultColorEdge;
    private ArrayList<String> textSteps;
    private ArrayList<PanelPaint> graphicSteps;
    private Gui gui;
    private Draw draw;
    private Boolean isAnimated;

    private Color red = new Color(255, 0, 0),
        darkOrange = new Color(255, 107, 51), 
        orange = new Color(255, 181, 102),
        lightred = new Color(255,139,139);

    public AnimationAlgorithm(Graph graph, Color defaultColorVertex, Color defaultColorEdge,Boolean isAnimated, Gui gui, Draw draw){
        this.graph = graph;
        this.defaultColorVertex = defaultColorVertex;
        this.defaultColorEdge = defaultColorEdge;
        this.textSteps = new ArrayList<String>();
        this.graphicSteps = new ArrayList<PanelPaint>();
        this.gui = gui;
        this.draw = draw;
        this.isAnimated = isAnimated;
    }
    public AnimationAlgorithm(Graph graph, Color defaultColor, Color defaultColorEdge, Vertex start,Boolean isAnimated, Gui gui, Draw draw){
        this.graph = graph;
        this.defaultColorVertex = defaultColor;
        this.start = start;
        this.defaultColorEdge = defaultColorEdge;
        this.textSteps = new ArrayList<String>();
        this.graphicSteps = new ArrayList<PanelPaint>();
        this.gui = gui;
        this.draw = draw;
        this.isAnimated = isAnimated;
    }
    
    public void reset(){
        if (isAnimated) {
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
    }

    public void changeColor(ArrayList<Vertex> visited, ArrayList<Vertex> toVisite, ArrayList<Edge> edgeBrowsed){
        if (isAnimated) {
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
    }

    public void refresh(){
        if (isAnimated) {
            graph.getPanelPaint().repaint();
            try {
                Thread.sleep(2500);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    private JDialog jd;
        private JScrollPane global;
        private JLabel content;

    // public void displayLog(){
    //     jd = new JDialog();
    //     jd.setSize(850,600);
    //     jd.setLocationRelativeTo(null); //Centering the frame
    //     jd.setResizable(false);
    //     jd.setAlwaysOnTop(true);
    //     jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    //     global = new JScrollPane();
    //     JPanel jp = new JPanel();
    //     jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
    //     for (int i = 0; i < textSteps.size(); i++) {
    //         jp.add(new JLabel(textSteps.get(i)));
    //         //System.out.println("test 1");
    //         //jp.add(graphicSteps.get(i));
    //     }
    //     global.setViewportView(jp);
           
    //     jd.add(global);
    //     jd.setVisible(true);
    // }

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


    // public void addStep(String textStep, Graph FigureStep){
    //     textSteps.add(textStep);
    //     PanelPaint pp = new PanelPaint(gui, draw);
    //     pp.setSize(new Dimension(850,600));
    //     //pp.getExtend().setVisible(false);
    //     FigureStep.setPanelPaint(pp);
    //     graphicSteps.add(pp);

    // }

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

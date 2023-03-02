package util.algorithm;
import util.*;
import java.util.*;

import gui.draw.PanelPaint;

import java.awt.*;
public class FloydWarshall extends Thread{
    
    private Graph graph;
    private Color vertexDefaultColor, edgeDefaultColor;
    private AnimationAlgorithm animAlgo;

    public FloydWarshall(Graph g){
        this.graph = g;
        this.vertexDefaultColor = g.getVertices().get(0).getBorderColor();
        this.edgeDefaultColor = g.getEdges().get(0).getStrokeColor();
        this.animAlgo = new AnimationAlgorithm(g, vertexDefaultColor,edgeDefaultColor);
    }

    @Override
    public void run() {
        ArrayList<Vertex> vertices = graph.getVertices();
        ArrayList<Edge> edges = graph.getEdges();
        int n = vertices.size();
        int [][] M = new int [n][n];
        Boolean [][] mem = new Boolean [n][n];

        
        
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                M[i][j] = Integer.MAX_VALUE/2;
                mem[i][j] = true;
                if (i == j){
                    M[i][j] = 0;
                    mem[i][j] = false;
                }       
            }
        }

        for (Edge edge : edges){
            int idS = vertices.indexOf(edge.getStart()),idE = vertices.indexOf(edge.getEnd());
            M[idS][idE] = (int)edge.getWeight();
            mem[idS][idE] = false;
        }

        for (int k = 0; k < M.length; k++) {
            for (int i = 0; i < M.length; i++) {
                for (int j = 0; j < M.length; j++) {
                    M[i][j] = Integer.min(M[i][j], M[i][k] + M[k][j]);
                }
            }
        }
        int width = edges.get(0).getStrokeWidth();
        PanelPaint pp = graph.getPanelPaint();
        int shift = n /3;
        
        ArrayList<Edge> toDelete = new ArrayList<Edge>();
        for (int i = 0; i < M.length; i++) {
            Color c = new Color((int)((Math.sin(i*Math.PI*2/n)+1)*127), (int)((Math.sin((i+shift)*Math.PI*2/n)+1)*127), (int)((Math.sin((i+2*shift)*Math.PI*2/n)+1)*127));
            for (int j = 0; j < M.length; j++) {
                if (mem[i][j]) {
                    Edge edgeTemp = new Edge(vertices.get(i),vertices.get(j) , M[i][j], width,c ,c, c);
                    toDelete.add(edgeTemp);
                    graph.addEdge(edgeTemp);
                }
            }
            animAlgo.refresh();
        }



        try {
            Thread.sleep(5000);
            for (Edge edge : toDelete) {
                edges.remove(edge);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        animAlgo.refresh();
    }
}

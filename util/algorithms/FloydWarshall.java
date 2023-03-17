package util.algorithms;
import util.*;
import java.util.*;
import gui.Gui;
import gui.draw.Draw;
import gui.draw.PanelPaint;

import java.awt.*;
public class FloydWarshall extends Thread{
    
    private Graph graph;
    private Color vertexDefaultColor, edgeDefaultColor;
    private AnimationAlgorithm animAlgo;

    public FloydWarshall(Graph g, Gui gui, Draw draw){
        this.graph = g;
        this.vertexDefaultColor = g.getVertices().get(0).getBorderColor();
        this.edgeDefaultColor = g.getEdges().get(0).getStrokeColor();
        this.animAlgo = new AnimationAlgorithm(g, vertexDefaultColor,edgeDefaultColor, gui, draw);
    }

    public String printArray(int [][] M){
        String result = "";

        for (int i = 0; i < M.length; i++) {
            result += i + " :  [ ";
            for (int j = 0; j < M.length - 1; j++) {
                if (M[i][j] == Integer.MAX_VALUE/2) {
                    result +=  "-, "; 
                }else{
                    result += M[i][j] + ", ";
                }
                
            }
            if (M[i][M.length-1] == Integer.MAX_VALUE/2) {
                result += "- ]<br>";
            }else{
                result += M[i][M.length-1] + " ]<br>";
            }
            
        }
        result += "<br>";
        return result;
    }

    @Override
    public void run() {
        graph.switchDisplay();
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
        String text = "<html><body><blockquote><h1>Floyd-Warshall</h1><br>"+animAlgo.matrixString()+"<br><p>";
        for (int k = 0; k < M.length; k++) {
            text += "<h2>Step "+k+" </h2><br>"+printArray(M)+"<br>";
            for (int i = 0; i < M.length; i++) {
                for (int j = 0; j < M.length; j++) {
                    if(M[i][j] > M[i][k] + M[k][j])
                        text += "There are a shorter way between "+i+" and "+j+"<br>";
                    M[i][j] = Integer.min(M[i][j], M[i][k] + M[k][j]);
                }
            }
            text+= "<br>";
        }
        text += "<h2>Step "+M.length+" </h2><br>"+printArray(M)+"<br>";
        text += "</p></blockquote></body></html>";

        int width = edges.get(0).getStrokeWidth();
        PanelPaint pp = graph.getPanelPaint();
        int shift = n /3;
        
        ArrayList<Edge> toDelete = new ArrayList<Edge>();
        for (int i = 0; i < M.length; i++) {
            Color c = new Color((int)((Math.sin(i*Math.PI*2/n)+1)*127), (int)((Math.sin((i+shift)*Math.PI*2/n)+1)*127), (int)((Math.sin((i+2*shift)*Math.PI*2/n)+1)*127));
            for (int j = 0; j < M.length; j++) {
                if (mem[i][j]) {
                    Edge edgeTemp = new Edge(vertices.get(i),vertices.get(j) , M[i][j], width,c ,c, c, c, Color.BLACK, graph, 15);
                    toDelete.add(edgeTemp);
                    graph.addEdge(edgeTemp);
                }
            }
            animAlgo.refresh();
        }


        animAlgo.displayLog(text);
        try {
            Thread.sleep(5000);
            for (Edge edge : toDelete) {
                graph.removeEdge(edge);
                edges.remove(edge);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        animAlgo.refresh();
        graph.switchDisplay();
    }
}

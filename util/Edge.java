package util;

import java.awt.*;

public class Edge {
    private Vertex start, end;
    private Object weight;
    private int strokeWidth;
    private Color strokeColor;

    public Edge(Vertex start, Vertex end, Object weight, int strokeWidth, Color strokeColor) {
        this.start = start;
        this.end = end;
        this.weight = weight;
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
    }

    public Vertex getStart() {
        return start;
    }

    public void setStart(Vertex start) {
        this.start = start;
    }

    public Vertex getEnd() {
        return end;
    }

    public void setEnd(Vertex end) {
        this.end = end;
    }

    public Object getWeight() {
        return weight;
    }

    public void setWeight(Object weight) {
        this.weight = weight;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }
    
}

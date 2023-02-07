/**
 * @author Antonin C.
 * @author François P.
 * The <b>vertex</b> class represents vertices (or nodes) which are used in graph theory.
 */

package util;
import java.awt.*;
import java.util.*;

public class Vertex
{
    private int coordX, coordY, radius, strokeWidth;
    private Color insideColor, borderColor;
    private String name;
    private ArrayList<Edge> edgeList;

    /**
     * Constructor of the vertex class
     * @param coordX X position of the vertex's center
     * @param coordY Y position of the vertex's center
     * @param radius Radius of the vertex
     * @param strokeWidth Width of the vertex's stroke
     * @param insideColor Color of the vertex's inside
     * @param borderColor Color of the vertex's outside
     * @param name Name of the vertex that will be displayed
     */
    public Vertex(int coordX, int coordY, int radius, int strokeWidth, Color insideColor, Color borderColor, String name)
    {
        this.coordX = coordX;
        this.coordY = coordY;
        this.radius = radius;
        this.strokeWidth = strokeWidth;
        this.insideColor = insideColor;
        this.borderColor = borderColor;
        this.name = name;
        this.edgeList = new ArrayList<Edge>();
    }

    /**
     * Method to get the x coordinate
     * @return Return the value of the X coordinate
     */
    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public Color getInsideColor() {
        return insideColor;
    }

    public void setInsideColor(Color insideColor) {
        this.insideColor = insideColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(ArrayList<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    public void addEdge(Edge e){
        edgeList.add(e);
    }

    public void addEdge(Vertex v1, Vertex v2, Object weight, int strokeWidth, Color color){
        edgeList.add(new Edge(v1, v2, weight, strokeWidth, color));
    }

    public void removeEdge(Edge e){
        if(edgeList.contains(e))
            edgeList.remove(e);
    }
}

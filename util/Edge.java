package util;

import java.awt.*;
import java.util.ArrayList;

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

    //Allow to get the precise starting and ending coordinates of the edge
    public ArrayList<Point> getEdgePoints(Edge edge)
    {
        //Step 1 : retreiving the center of the starting point and ending point
        Point startingVertexCenter = new Point((int)((edge.getStart().getCoordX())+(edge.getStart().getRadius()/2)), (int)((edge.getStart().getCoordY())+(edge.getStart().getRadius()/2)));
        Point endingVertexCenter = new Point((int)((edge.getEnd().getCoordX())+(edge.getEnd().getRadius()/2)), (int)((edge.getEnd().getCoordY())+(edge.getEnd().getRadius()/2)));

        //Step 2 : We must determine the edge direction
        Point vector = new Point((int)(endingVertexCenter.getX() - startingVertexCenter.getX()), (int)(endingVertexCenter.getY() - startingVertexCenter.getY()));

        //Step 3 : We now normalize the vector
        Double normalizedVectorX = (vector.getX()/Math.sqrt((vector.getX()*vector.getX()+vector.getY()*vector.getY())));
        Double normalizedVectorY = (vector.getY()/Math.sqrt((vector.getX()*vector.getX()+vector.getY()*vector.getY())));

        //Step 4 : getting the starting and ending coordinates thanks to the normalized vector
        ArrayList<Point> result = new ArrayList<>(); //Starting point first (index 0)
        result.add(new Point((int)(startingVertexCenter.getX() + ((edge.getStart().getRadius()/2)*normalizedVectorX)),
                             (int)(startingVertexCenter.getY() + ((edge.getStart().getRadius()/2)*normalizedVectorY))));
        //Ending point (index 1)
        result.add(new Point((int)(endingVertexCenter.getX() - ((edge.getEnd().getRadius()/2)*normalizedVectorX)),
                             (int)(endingVertexCenter.getY() - ((edge.getEnd().getRadius()/2)*normalizedVectorY))));

        //We can now return the result
        return result;
    }

    //Allow to get points to draw the arrow
    public ArrayList<Point> getArrow(Edge edge, int size)
    {
        //Tip of the arrow
        Point arrowTip = new Point((int)(getEdgePoints(edge).get(1).getX()), (int)(getEdgePoints(edge).get(1).getY()));

        //Now we need 2 more points to draw the arrow
        //To do so, we first have to retreive the angle of the edge
        Double edgeAngle = Math.atan2(edge.getEnd().getCoordY() - edge.getStart().getCoordY(),edge.getEnd().getCoordX() - edge.getStart().getCoordX());

        //Now that we have the angle, we can get the two sub line coord (We picked + and - Pi/1.2 because it seems to work best with this value)
        Point sub45plus = new Point((int)(arrowTip.getX()+(size*Math.cos(edgeAngle + Math.PI/1.2))),(int)(arrowTip.getY()+(size*Math.sin(edgeAngle + Math.PI/1.2))));
        Point sub45minus = new Point((int)(arrowTip.getX()+(size*Math.cos(edgeAngle - Math.PI/1.2))),(int)(arrowTip.getY()+(size*Math.sin(edgeAngle - Math.PI/1.2))));

        //Now we have the tip point and both subline point
        ArrayList<Point> result = new ArrayList<>();
        result.add(arrowTip);
        result.add(sub45plus);
        result.add(sub45minus);
        return result;
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

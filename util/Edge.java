package util;
import java.util.*;
import java.awt.geom.*;
import java.io.Serializable;
import java.lang.module.ResolutionException;
import java.awt.*;
import java.util.ArrayList;

public class Edge implements Serializable
{
    private Vertex start, end;
    private Object weight;
    private int strokeWidth, arrowTipWidth;
    private Color strokeColor, highlightColor, edgeArrowTipColor, weightColor, weightBorderColor;
    private Path2D.Float collisionArea;


    public Edge(Vertex start, Vertex end, Object weight, int strokeWidth, Color strokeColor, Color highlightColor, Color edgeArrowTipColor, Color weightColor, Color weightBorderColor, Graph graph, int arrowTipWidth) {
        this.start = start;
        this.end = end;
        this.weight = weight;
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
        this.highlightColor = highlightColor;
        this.edgeArrowTipColor = edgeArrowTipColor;
        this.weightColor = weightColor;
        this.weightBorderColor = weightBorderColor;
        this.arrowTipWidth = arrowTipWidth;
        collisionArea = refreshCollisionArea(graph.getOriented(), graph.bothDirections(this));
    }

    public Path2D.Float refreshCollisionArea(Boolean isOriented, Boolean bothDirections)
    { 
        Path2D.Float path = new Path2D.Float();

        //If the edge have the same starting and ending vertexs
        if(start == end)
        {
            ArrayList<Point> edgePoint = getEdgePoints(this);

            double startX = edgePoint.get(0).getX(),
                startY = edgePoint.get(0).getY(),
                endX = edgePoint.get(1).getX(),
                endY = edgePoint.get(1).getY();

            //It's a loop
            path.moveTo(startX, startY);
            path.curveTo(startX,  startY,  startX+strokeWidth*10,  startY-strokeWidth*9, endX, endY-strokeWidth*10);
            path.curveTo(endX, endY-strokeWidth*10,  startX-strokeWidth*10,  startY-strokeWidth*9, startX,  startY);
            path.closePath();
        }

        //If this is an edge that don't have the same starting and ending vertex
        else
        {
            ArrayList<Point> edgePoint = getEdgePoints(this);

            int index = 0;
            if(isOriented && bothDirections)
            {
                index = index + 2;
            }

            int startX = (int)edgePoint.get(index).getX(),
                startY = (int)edgePoint.get(index).getY(), 
                endX = (int)edgePoint.get(index + 1).getX(), 
                endY = (int)edgePoint.get(index + 1).getY(),
                width = getStrokeWidth() * 2;

            path.moveTo(startX-width, startY-width); //Starting point
            path.lineTo(startX+width, startY+width);
            path.lineTo(endX+width, endY+width); 
            path.lineTo(endX-width, endY-width); //End point
            path.closePath();
        }

        return path;
    }

    public void paint(Graphics graphics, Boolean isOriented, Boolean isWeighted, Object collision, Graph graph, Boolean bothDirections)
    {
        graphics.setColor(strokeColor);
        if (collision == this)
        {
            graphics.setColor(highlightColor);
            ((Graphics2D)graphics).setStroke(new BasicStroke(strokeWidth*1.5f)); //Change stroke
        }
        else
        {
            graphics.setColor(strokeColor);
            ((Graphics2D)graphics).setStroke(new BasicStroke(strokeWidth)); //Change stroke
        }

        ArrayList<Point> edgePoint = getEdgePoints(this), arrowTip;

        int index = 0;
        //If the graph is oriented and the edge go both direction (there is an edge with this edge end as a start and this edge start as an end) we move the edge a bit
        if(isOriented)
        {
            if(bothDirections)
            {
                index = index + 2;
            }
        }

        double startX = edgePoint.get(index).getX(),
            startY = edgePoint.get(index).getY(),
            endX = edgePoint.get(index + 1).getX(),
            endY = edgePoint.get(index + 1).getY();

        if (start == end)
        {
            //It's a loop
            GeneralPath GPath = new GeneralPath();
            GPath.moveTo(startX, startY);
            GPath.curveTo(startX,  startY,  startX+strokeWidth*10,  startY-strokeWidth*9, endX, endY-strokeWidth*10);
            GPath.curveTo(endX, endY-strokeWidth*10,  startX-strokeWidth*10,  startY-strokeWidth*9, startX,  startY);
            GPath.closePath();
            ((Graphics2D) graphics).draw(GPath);
        }
        else
        {
            //We draw the edge
            graphics.drawLine((int)startX, (int)startY, (int)endX, (int)endY);
        }

        
        if(isOriented && start != end) //If the graph is oriented, we also have to draw an arrow displaying the arrival vertex
        {
            graphics.setColor(edgeArrowTipColor);
            arrowTip = getArrow(this, index + 1, arrowTipWidth); // + 1 because we want the end vertex
            graphics.drawLine((int)(arrowTip.get(0).getX()),(int)(arrowTip.get(0).getY()), (int)(arrowTip.get(1).getX()), (int)(arrowTip.get(1).getY()));
            graphics.drawLine((int)(arrowTip.get(0).getX()),(int)(arrowTip.get(0).getY()), (int)(arrowTip.get(2).getX()), (int)(arrowTip.get(2).getY()));
        }
        
        //If the graph is weighted, we then draw every weight (after we have drawn every edge)
        if(isWeighted)
        {
            //We first retreive the string to draw
            String toDraw;
            if(weight == null)
            {
                toDraw = "0";
                //graphics.drawString("0", (int)((start.getCoordX()+end.getCoordX())/2), (int)((start.getCoordY()+end.getCoordY())/2));
            }
            else
            {
                toDraw = weight.toString();
                //graphics.drawString(weight.toString(), (int)((start.getCoordX()+end.getCoordX())/2), (int)((start.getCoordY()+end.getCoordY())/2));
            }

            //Style of the weight text
            graphics.setFont(new Font("Arial", 0, getIdealFontSize(weight.toString())));

            //Once we retreive the weight, we want to draw it in the middle of the edge a highlight of the same color as the edge
            int offsetX = (int)(startX + endX)/2 - graphics.getFontMetrics().stringWidth(weight.toString())/2;
            int offsetY = (int)(startY + endY)/2 + graphics.getFontMetrics().getHeight()/4;
        
            if(start == end)
            {
                //We move up the number a bit
                offsetY = offsetY - strokeWidth*10;
            }

            graphics.setColor(weightBorderColor); //Color of the edge
            //We draw the highlight (border of the text)
            graphics.drawString(toDraw, offsetX + 1, offsetY + 1);
            graphics.drawString(toDraw, offsetX - 1, offsetY + 1);
            graphics.drawString(toDraw, offsetX + 1, offsetY - 1);
            graphics.drawString(toDraw, offsetX - 1, offsetY - 1);

            //We draw the real string
            graphics.setColor(weightColor);
            graphics.drawString(toDraw, offsetX, offsetY);
        }
    }

    public int getIdealFontSize(String text)
    {
        switch(text.length())
        {
            case(1):return 28;
            case(2):return 22;
            case(3):return 16;
            case(4):return 10;
            default:return 8;
        }
    }

    //Allow to get the precise starting and ending coordinates of the edge
    public ArrayList<Point> getEdgePoints(Edge edge)
    {
        //Step 1 : retreiving the center of the starting point and ending point
        int startRadius = start.getDiameter()/2,
            endRadius = end.getDiameter()/2;
        int startingVertexCenterX = start.getCoordX() + startRadius,
            startingVertexCenterY = start.getCoordY() + startRadius,
            endingVertexCenterX = end.getCoordX() + endRadius,
            endingVertexCenterY = end.getCoordY() + endRadius;
        
        //Step 2 : We must determine the edge direction
        int vectorDirX = endingVertexCenterX - startingVertexCenterX,
            vectorDirY = endingVertexCenterY - startingVertexCenterY;

        //Step 3 : We now normalize the vector
        Double normalizedVectorX = (vectorDirX/Math.sqrt((vectorDirX*vectorDirX+vectorDirY*vectorDirY)));
        Double normalizedVectorY = (vectorDirY/Math.sqrt((vectorDirX*vectorDirX+vectorDirY*vectorDirY)));

        //Step 4 : getting the starting and ending coordinates thanks to the normalized vector
        ArrayList<Point> result = new ArrayList<Point>(); 

        // === POINTS TO DRAW A STRAIGHT LINE ===
        {
            if (start == end) {
                result.add(new Point(startingVertexCenterX,start.getCoordY()));
                result.add(new Point(endingVertexCenterX,end.getCoordY()));
                return result;
            }

            result.add(new Point((int)(startingVertexCenterX + ((startRadius)*normalizedVectorX)),//Starting point first (index 0)
                                (int)(startingVertexCenterY + (startRadius*normalizedVectorY))));
            result.add(new Point((int)(endingVertexCenterX - ((endRadius)*normalizedVectorX)),//Ending point (index 1)
                                (int)(endingVertexCenterY - ((endRadius)*normalizedVectorY))));
        }
        // === END POINTS TO DRAW A STRAIGHT LINE ===

        //If the graph is oriented, we want to get 2 others points : points that are not starting and ending the vertexs's centers
        // === POINTS NOT STARTING AND ENDING TO THE CENTER ===
        {

            double angle = Math.PI/6;

            double newX = result.get(0).getX() - startingVertexCenterX;
            double newY = result.get(0).getY() - startingVertexCenterY;
            int X = (int)(newX * Math.cos (angle) + newY * Math.sin (angle) + startingVertexCenterX);
            int Y = (int)(- newX * Math.sin (angle) + newY * Math.cos (angle) + startingVertexCenterY);
            result.add(new Point(X,Y));

            newX = result.get(1).getX() - endingVertexCenterX;
            newY = result.get(1).getY() - endingVertexCenterY;
            X = (int)(newX * Math.cos (Math.PI*2 - angle) + newY * Math.sin (Math.PI*2 - angle) + endingVertexCenterX);
            Y = (int)(- newX * Math.sin (Math.PI*2 - angle) + newY * Math.cos (Math.PI*2 - angle) + endingVertexCenterY);
            result.add(new Point(X,Y));
        }
        // === END POINTS NOT STARTING AND ENDING TO THE CENTER ===

        //We can now return the result
        return result;
    }

    //Allow to get points to draw the arrow
    public ArrayList<Point> getArrow(Edge edge, int index, int size)
    {
        //Tip of the arrow
        ArrayList<Point> edgePoint = getEdgePoints(edge);
        int arrowTipX = (int)edgePoint.get(index).getX(),
            arrowTipY = (int)edgePoint.get(index).getY();
        

        //Now we need 2 more points to draw the arrow
        //To do so, we first have to retreive the angle of the edge
        Double edgeAngle = Math.atan2(end.getCoordY() - start.getCoordY(),end.getCoordX() - start.getCoordX());

        //Now that we have the angle, we can get the two sub line coord (We picked + and - Pi/1.2 because it seems to work best with this value)
        Point sub45plus = new Point((int)(arrowTipX+(size*Math.cos(edgeAngle + Math.PI/1.2))),(int)(arrowTipY+(size*Math.sin(edgeAngle + Math.PI/1.2))));
        Point sub45minus = new Point((int)(arrowTipX+(size*Math.cos(edgeAngle - Math.PI/1.2))),(int)(arrowTipY+(size*Math.sin(edgeAngle - Math.PI/1.2))));

        //Now we have the tip point and both subline point
        ArrayList<Point> result = new ArrayList<Point>();
        result.add(new Point(arrowTipX,arrowTipY));
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

    public Path2D.Float getCollisionArea() {
        return collisionArea;
    }

    public void setCollisionArea(Path2D.Float collisionArea) {
        this.collisionArea = collisionArea;
    }

    public Color getHighlightColor() {
        return highlightColor;
    }

    public void setHighlightColor(Color highlightColor) {
        this.highlightColor = highlightColor;
    }

    public int getArrowTipWidth() {
        return arrowTipWidth;
    }

    public void setArrowTipWidth(int arrowTipWidth) {
        this.arrowTipWidth = arrowTipWidth;
    }

    public Color getEdgeArrowTipColor() {
        return edgeArrowTipColor;
    }

    public void setEdgeArrowTipColor(Color edgeArrowTipColor) {
        this.edgeArrowTipColor = edgeArrowTipColor;
    }
    @Override
    public String toString() {
        if (weight != null) {
            return "("+start +","+end+"," + weight + ")";
        }else{
            return "("+start +","+end+")";
        }
        
    }

    public Color getWeightColor() {
        return weightColor;
    }

    public void setWeightColor(Color weightColor) {
        this.weightColor = weightColor;
    }

    public Color getWeightBorderColor() {
        return weightBorderColor;
    }

    public void setWeightBorderColor(Color weightBorderColor) {
        this.weightBorderColor = weightBorderColor;
    }
    
}

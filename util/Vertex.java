/**
 * @author Antonin C.
 * @author François P.
 * The <b>vertex</b> class represents vertices (or nodes) which are used in graph theory.
 */

package util;
import java.awt.*;
import java.io.Serializable;
import java.util.*;

public class Vertex implements Serializable
{
    private static int cpt = 0;
    private int id, coordX, coordY, diameter, strokeWidth;
    private Color insideColor, borderColor, nameColor;
    private String name;

    /**
     * Constructor of the vertex class
     * @param coordX X position of the vertex's center
     * @param coordY Y position of the vertex's center
     * @param radius Diameter of the vertex
     * @param strokeWidth Width of the vertex's stroke
     * @param insideColor Color of the vertex's inside
     * @param borderColor Color of the vertex's outside
     * @param name Name of the vertex that will be displayed
     */
    public Vertex(int cpt, int coordX, int coordY, int diameter, int strokeWidth, Color insideColor, Color borderColor, String name, Color nameColor){
        this.id = cpt;
        this.coordX = coordX;
        this.coordY = coordY;
        this.diameter = diameter;
        this.strokeWidth = strokeWidth;
        this.insideColor = insideColor;
        this.borderColor = borderColor;
        this.name = name;
        this.nameColor = nameColor;
        System.out.println(name + " " + id);
    }

    public void paint(Graphics graphics, Object collision){
        //Borders
        
        if (collision == this) {
            ((Graphics2D)graphics).setStroke(new BasicStroke((int)(strokeWidth*1.5)));    
        }else{
            ((Graphics2D)graphics).setStroke(new BasicStroke(strokeWidth));
        }
        graphics.setColor(borderColor);
        graphics.drawOval(coordX, coordY, diameter, diameter);

        graphics.setColor(insideColor);
        graphics.fillOval(coordX+((int)(diameter*0.2)/2), coordY+((int)(diameter*0.2)/2), (int)(diameter*0.8), (int)(diameter*0.8));

        //We draw vertex name on top of the vertex (color of border)
        graphics.setColor(nameColor);


        graphics.setFont(new Font("Arial", 0, getIdealFontSize(name)));


        //We retreive the good text offset
        int offsetX = diameter/2 - graphics.getFontMetrics().stringWidth(name)/2;
        int offsetY = diameter/2 + graphics.getFontMetrics().getHeight()/4;
        graphics.drawString(name, (int)(coordX+offsetX), (int)(coordY+offsetY));
    }

    public int getIdealFontSize(String text)
    {
        switch(text.length())
        {
            case(1):return 28;
            case(2):return 24;
            case(3):return 20;
            case(4):return 16;
            default:return 10;
        }
    }

    /**
     * Method to get the x coordinate
     * @return Return the value of the X coordinate
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int radius) {
        this.diameter = radius;
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

    @Override
    public String toString() {
        return name;
    }
    
}

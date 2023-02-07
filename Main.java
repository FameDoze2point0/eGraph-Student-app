/*
 *  Author : Antonin C. & François P.
 *  File : Main.java
 *
 *  Description : this class is used to launch our program
 */

//Graph and automaton
import components.*;
//Graphic interface
import gui.*;

//Other
import java.util.*;
import java.lang.Integer;
import java.awt.Color;

public class Main
{
    //Main function
    public static void main(String[] args)
    {
        Gui page = new Gui();

        //ARTIFICIAL GRAPH TO TEST
        Graph g = new Graph("test", false, false);
        g.addVertex("A");
        //g.getVertex("A").setOutside(Color.GRAY);
        //g.getVertex("A").setInside(Color.RED);
        g.addVertex("B");
        g.addVertex("C");
        g.getVertex("A").addEdge(null, g.getVertex("B"));
        g.getVertex("B").addEdge(null, g.getVertex("C"));
        g.getVertex("C").addEdge(null, g.getVertex("A"));
        //

        Gui.getTabsAndDraw().addTab("test", null, new PanelPaint(),null);
        Gui.getOpened().add(g);
    }
}
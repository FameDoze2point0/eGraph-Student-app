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

public class Main
{
    public static void main(String[] args)
    {
        LinkedList<Graph<Integer>> graphs = new LinkedList<Graph<Integer>>();
            
        System.out.println("New graph made !");
        graphs.add(new Graph<Integer>("Mon graphe", false, false));
        

    }
}

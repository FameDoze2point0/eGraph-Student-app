package gui.popups.algorithms;

import javax.swing.*;
import java.awt.*;

import gui.Gui;
import gui.draw.Draw;
import util.*;
import util.algorithm.BFS;
import util.algorithm.BellmanFord;
import util.algorithm.DFS;
import util.algorithm.Dijkstra;
import util.algorithm.FloydWarshall;
import util.algorithm.Kruskal;
import util.algorithm.Prim;
import util.algorithm.RS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class AlgorithmsPage extends JDialog
{
    //OLD
    private JPanel global;
        private JPanel algoChoiceArea;
            private JLabel algoChoiceLabel;
            private JComboBox algoList;
            private String[] algoType = {"Breadth First Search", "Depth First Search","Random Search","Dijkstra","Bellman-Ford","Floyd-Warshall","Prim","Kruskal"};
        private JPanel startingVertexArea;
            private JLabel startingVertexLabel;
            private JComboBox<String> startingVertexList;
        private JPanel endingVertexArea;
            private JLabel endingVertexLabel;
            private JComboBox<String> endingVertexList;
        private JButton submit;

    //NEW
    private JPanel panel_listeAlgo,
                   panel_informations,
                   panel_choix;

    public AlgorithmsPage(Gui gui, Draw draw)
    {    
        super(gui, "Algorithm launching page", true);
        this.setSize(900,700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //List of algorithms
        panel_listeAlgo = new JPanel();
        panel_listeAlgo.setBackground(Color.RED);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.2;
        constraints.weighty = 1;

        this.add(panel_listeAlgo,constraints);



        //Informations
        panel_informations = new JPanel();
        panel_listeAlgo.setBackground(Color.GREEN);

        constraints.gridx = (int)(900*0.2);
        constraints.gridy = 0;
        constraints.weightx = 0.8;
        constraints.weighty = 0.8;

        this.add(panel_listeAlgo,constraints);



        //Choices
        panel_informations = new JPanel();
        panel_listeAlgo.setBackground(Color.BLUE);

        constraints.gridx = (int)(900*0.8);
        constraints.gridy = (int)(700*0.8);
        constraints.weightx = 0.8;
        constraints.weighty = 0.2;

        this.add(panel_listeAlgo,constraints);

        // global = new JPanel(new GridLayout(1,4));

        // //algorithm part
        // algoChoiceArea = new JPanel();
        // algoChoiceLabel = new JLabel("Algorithms :");
        // algoChoiceArea.add(algoChoiceLabel);
        // algoList = new JComboBox(algoType);

        // Graph graph = gui.getTabulations().get(draw.getSelectedComponent());
        // DefaultListSelectionModel model = new DefaultListSelectionModel();
        // model.addSelectionInterval(0, 2);
        // if (graph.getOriented() && graph.getWeighted()) {
        //     model.addSelectionInterval(3, 5);
        // }else if(graph.getWeighted()){
        //     model.addSelectionInterval(6, 7);
        // }
        // EnabledJComboBoxRenderer enableRenderer = new EnabledJComboBoxRenderer(model); 
        // algoList.setRenderer(enableRenderer);

        // addAlgoListener(gui,draw);
        // algoChoiceArea.add(algoList);
        // global.add(algoChoiceArea);

        // String[] vName = new String[graph.getVertices().size()];

        // for (int i = 0; i < vName.length; i++) 
        //     vName[i] = "" + graph.getVertices().get(i).getName();

        // //starting vertex part
        // startingVertexArea = new JPanel();
        // startingVertexLabel = new JLabel("Starting vertex :");
        // startingVertexArea.add(startingVertexLabel);
        // startingVertexList = new JComboBox<String>(vName);
        // startingVertexArea.add(startingVertexList);
        // global.add(startingVertexArea);


        // //ending vertex part
        // endingVertexArea = new JPanel();
        // endingVertexLabel = new JLabel("Ending vertex :");
        // endingVertexArea.add(endingVertexLabel);
        // endingVertexList = new JComboBox<String>(vName);
        // endingVertexList.setEnabled(false);
        // endingVertexArea.add(endingVertexList);
        // global.add(endingVertexArea);


        // submit = new JButton("Launch");
        // addSubmitListener(gui,draw);
        // global.add(submit);
        

        // this.add(global);
        this.setVisible(true);
    }

    public void addAlgoListener(Gui gui, Draw draw){

        ActionListener algorithmListener = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String s = (String)algoList.getSelectedItem();
                System.out.println(algoList.getSelectedItem()+"");

                switch (s) {
                    case "BFS": //BFS
                        startingVertexList.setEnabled(true);
                        endingVertexList.setEnabled(false);
                        break;

                    case "DFS": //DFS
                        startingVertexList.setEnabled(true);
                        endingVertexList.setEnabled(false);
                        break;
                
                    case "RS": //RS
                        startingVertexList.setEnabled(true);
                        endingVertexList.setEnabled(false);
                        break;

                    case "Dijkstra": //Dijkstra
                        startingVertexList.setEnabled(true);
                        endingVertexList.setEnabled(false);
                        break;

                    case "Bellman-Ford": // Bellman-Ford
                        startingVertexList.setEnabled(true);
                        endingVertexList.setEnabled(false);
                        break;

                    case "Floyd-Warshall": //Floyd Warshall
                        startingVertexList.setEnabled(false);
                        endingVertexList.setEnabled(false);
                        break;

                    case "Prim": // Prim
                        startingVertexList.setEnabled(true);
                        endingVertexList.setEnabled(false);
                        break;
                    
                    case "Kruskal": // Kruskal
                        startingVertexList.setEnabled(false);
                        endingVertexList.setEnabled(false);
                        break;

                    default:
                        break;
                }
            }
        };
        algoList.addActionListener(algorithmListener);
    }

    public void addSubmitListener(Gui gui, Draw draw){
        ActionListener submitListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String name1 = (String)startingVertexList.getSelectedItem();
                String name2 = (String)endingVertexList.getSelectedItem();

                // int name1 = Integer.parseInt((String)startingVertexList.getSelectedItem());
                // int name2 = Integer.parseInt((String)endingVertexList.getSelectedItem());
                Graph graph = gui.getTabulations().get(draw.getSelectedComponent());

                Vertex v1 = graph.getVertices().get(0), v2 = null;

                for (Vertex vertex : graph.getVertices()) {
                    if (vertex.getName() == name1) 
                        v1 = vertex;
                    if (vertex.getName() == name2) 
                        v2 = vertex;
                    
                }
                //fermer la page
                dispose();
                String s = (String)algoList.getSelectedItem();
                switch (s) {
                    case "Breadth First Search": //BFS
                        BFS algoBFS = new BFS(graph, v1);
                        algoBFS.start();
                        //graph.algo_BFS(v1);
                        break;

                    case "Depth First Search": //DFS
                        DFS algoDFS = new DFS(graph, v1);
                        algoDFS.start();;
                        break;
                
                    case "Random Search": //RS
                        RS algoRS = new RS(graph, v1);
                        algoRS.start();
                        break;

                    case "Dijkstra": //Dijkstra
                        try {
                            Dijkstra algoDijkstra = new Dijkstra(graph, v1);
                            algoDijkstra.start();
                        } catch (Exception exce) {
                            System.out.println(exce);
                        }  
                        break;

                    case "Bellman-Ford": // Bellman-Ford
                        try {
                            BellmanFord algoBellmanFord = new BellmanFord(graph, v1);
                            algoBellmanFord.start();
                        } catch (Exception exce) {
                            System.out.println(e);
                        }
                        break;

                    case "Floyd-Warshall": //Floyd Warshall
                        FloydWarshall algoFloydWarshall = new FloydWarshall(graph);
                        algoFloydWarshall.start();
                        break;

                    case "Prim": // Prim
                        try {
                            Prim algoPrim = new Prim(graph, v1);
                            algoPrim.start();
                        } catch (Exception exce) {
                            System.out.println(e);
                        }
                        break;
                    
                    case "Kruskal": // Kruskal
                        Kruskal algoKruskal = new Kruskal(graph); 
                        algoKruskal.start();
                        break;

                    default:
                        break;
                }
            }
        };

        submit.addActionListener(submitListener);
    }
}
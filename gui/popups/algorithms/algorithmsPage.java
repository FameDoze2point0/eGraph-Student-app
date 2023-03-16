package gui.popups.algorithms;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import gui.Gui;
import gui.draw.Draw;
import util.*;
import util.algorithms.BFS;
import util.algorithms.BellmanFord;
import util.algorithms.DFS;
import util.algorithms.Dijkstra;
import util.algorithms.FloydWarshall;
import util.algorithms.Kruskal;
import util.algorithms.Prim;
import util.algorithms.RS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JComboBox;
import java.awt.Image.*;
import java.io.*;

public class AlgorithmsPage extends JDialog
{
    private JComboBox algoList;
    private JLabel startingVertexLabel;
    private JComboBox<String> startingVertexList;
    private JLabel endingVertexLabel;
    private JComboBox<String> endingVertexList;
    private JButton submit;
    private JCheckBox skipAnimationCheck;

    //NEW
    private JPanel panel_listeAlgo,
                   panel_choix;

    private JScrollPane panel_informations;
        private JPanel textArea;
            private JLabel code;
            private JLabel informations;

    //Clickable buttons to display a specific algorithm
    private JButton BFS, DFS, RS, DIJKSTRA, BELLMAN, FLOYD, PRIM, KRUSKAL;
    private String selectedAlgo = "BFS";

    public AlgorithmsPage(Gui gui, Draw draw)
    {    
        super(gui, "Algorithm launching page", true);
        this.setSize(700,500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // === Disposition of elements on the page ===
        {
            //General
            this.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;

            //List of algorithms
            panel_listeAlgo = new JPanel();

            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weightx = 0.05;
            constraints.weighty = 0.9;
            constraints.gridheight = 1;
            // === List of algorithms ===
            {
                panel_listeAlgo.setLayout(new GridLayout(0,1));
                //BFS
                BFS = new JButton("Breadth First Search");
                BFS.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedAlgo = "BFS";
                        startingVertexList.setEnabled(true);
                        endingVertexList.setEnabled(false);

                        try
                        {
                            code.setIcon(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/gui/popups/algorithms/pseudocode/BFS.png"))));
                        }
                        
                        catch (IOException e1)
                        {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }


                        informations.setText("info BFS");
                    }
                });
                panel_listeAlgo.add(BFS);

                //DFS
                DFS = new JButton("Depth First Search");
                DFS.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedAlgo = "DFS";
                        startingVertexList.setEnabled(true);
                        endingVertexList.setEnabled(false);
                        try
                        {
                            code.setIcon(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/gui/popups/algorithms/pseudocode/DFS.png"))));
                        }
                        
                        catch (IOException e1)
                        {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                        informations.setText("info DFS");
                    }
                });
                panel_listeAlgo.add(DFS);

                //RS
                RS = new JButton("Random Search");
                RS.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedAlgo = "RS";
                        startingVertexList.setEnabled(true);
                        endingVertexList.setEnabled(false);
                        try
                        {
                            code.setIcon(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/gui/popups/algorithms/pseudocode/RS.png"))));
                        }
                        
                        catch (IOException e1)
                        {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                        informations.setText("info RS");
                    }
                });
                panel_listeAlgo.add(RS);

                //DIJKSTRA
                DIJKSTRA = new JButton("Dijkstra");
                DIJKSTRA.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedAlgo = "Dijkstra";
                        startingVertexList.setEnabled(true);
                        endingVertexList.setEnabled(false);
                        try
                        {
                            code.setIcon(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/gui/popups/algorithms/pseudocode/DIJKSTRA.png"))));
                        }
                        
                        catch (IOException e1)
                        {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                        informations.setText("info Dijkstra");

                    }
                });
                panel_listeAlgo.add(DIJKSTRA);

                //BELLMAN
                BELLMAN = new JButton("Bellman-Ford");
                BELLMAN.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedAlgo = "Bellman";
                        startingVertexList.setEnabled(true);
                        endingVertexList.setEnabled(false);
                        try
                        {
                            code.setIcon(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/gui/popups/algorithms/pseudocode/BELLMAN.png"))));
                        }
                        
                        catch (IOException e1)
                        {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                        informations.setText("info BELLMAN");
                    }
                });
                panel_listeAlgo.add(BELLMAN);

                //FLOYD
                FLOYD = new JButton("Floyd-Warshall");
                FLOYD.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedAlgo = "Floyd";
                        startingVertexList.setEnabled(false);
                        endingVertexList.setEnabled(false);
                        try
                        {
                            code.setIcon(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/gui/popups/algorithms/pseudocode/FLOYD.png"))));
                        }
                        
                        catch (IOException e1)
                        {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                        informations.setText("info FLOYD");
                    }
                });
                panel_listeAlgo.add(FLOYD);

                //PRIM
                PRIM = new JButton("Prim");
                PRIM.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedAlgo = "Prim";
                        startingVertexList.setEnabled(true);
                        endingVertexList.setEnabled(false);
                        try
                        {
                            code.setIcon(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/gui/popups/algorithms/pseudocode/PRIM.png"))));
                        }
                        
                        catch (IOException e1)
                        {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                        informations.setText("info PRIM");
                    }
                });
                panel_listeAlgo.add(PRIM);

                //KRUSKAL
                KRUSKAL = new JButton("Kruskal");
                KRUSKAL.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedAlgo = "Kruskal";
                        startingVertexList.setEnabled(false);
                        endingVertexList.setEnabled(false);
                        try
                        {
                            code.setIcon(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/gui/popups/algorithms/pseudocode/KRUSKAL.png"))));
                        }
                        
                        catch (IOException e1)
                        {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                        informations.setText("info KRUSKAL");
                    }
                });
                panel_listeAlgo.add(KRUSKAL);
            }
            this.add(panel_listeAlgo,constraints);
            constraints.gridheight = 1;

            //Informations
            textArea = new JPanel();
            textArea.setLayout(new BoxLayout(textArea, BoxLayout.Y_AXIS));
            panel_informations = new JScrollPane(textArea);
            panel_informations.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            panel_informations.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            constraints.gridx = 1;
            constraints.gridy = 0;
            constraints.weightx = 0.95;
            constraints.weighty = 0.9;

            // === INFORMATIONS ===
            {
                code = new JLabel();
                //By default, on BFS
                code.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(),"PSEUDOCODE"));
                try
                {
                    code.setIcon(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/gui/popups/algorithms/pseudocode/BFS.png"))));
                }
                
                catch (IOException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                textArea.add(code);

                informations = new JLabel("info BFS");
                informations.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(),"INFORMATIONS"));
                informations.setMinimumSize(new Dimension(512,40));
                informations.setMaximumSize(new Dimension(512,40));
                textArea.add(informations);
            }
            this.add(panel_informations,constraints);

            //Choices
            panel_choix = new JPanel(new GridBagLayout());

            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.weightx = 1;
            constraints.weighty = 0.1;
            constraints.gridwidth = 2;

            // === CHOICES ===
            {
                GridBagConstraints gbc = new GridBagConstraints();
                

                //One line
                gbc.gridy = 0;
                //gbc.gridwidth = 1;


                Graph graph = gui.getTabulations().get(draw.getSelectedComponent());
                String[] vName = new String[graph.getVertices().size()];

                for (int i = 0; i < vName.length; i++) 
                    vName[i] = "" + graph.getVertices().get(i).getName();

                //starting vertex part
                gbc.gridx = 0;
                gbc.weightx = 0.2;
                startingVertexLabel = new JLabel("Starting vertex :");
                panel_choix.add(startingVertexLabel,gbc);
                gbc.gridx = 1;
                gbc.weightx = 0.1;
                startingVertexList = new JComboBox<String>(vName);
                panel_choix.add(startingVertexList,gbc);


                //ending vertex part
                gbc.gridx = 2;
                gbc.weightx = 0.2;
                endingVertexLabel = new JLabel("Ending vertex :");
                panel_choix.add(endingVertexLabel,gbc);
                gbc.gridx = 3;
                gbc.weightx = 0.1;
                endingVertexList = new JComboBox<String>(vName);
                endingVertexList.setEnabled(false);
                panel_choix.add(endingVertexList,gbc);

                //animation
                gbc.gridx = 4;
                gbc.weightx = 0.2;
                skipAnimationCheck = new JCheckBox("Skip animation");
                panel_choix.add(skipAnimationCheck,gbc);

                //submit
                gbc.gridx = 5;
                gbc.weightx = 0.2;
                submit = new JButton("Launch");
                addSubmitListener(gui,draw);
                panel_choix.add(submit,gbc);
            }
            this.add(panel_choix,constraints);

            constraints.gridwidth = 1;
        }
        // === END Disposition of elements on the page ===
        this.setVisible(true);
    }

    public void addSubmitListener(Gui gui, Draw draw)
    {
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
                String s = selectedAlgo;
                switch (s) {
                    case "BFS": //BFS
                        BFS algoBFS = new BFS(graph, v1);
                        algoBFS.start();
                        //graph.algo_BFS(v1);
                        break;

                    case "DFS": //DFS
                        DFS algoDFS = new DFS(graph, v1);
                        algoDFS.start();;
                        break;
                
                    case "RS": //RS
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

                    case "Bellman": // Bellman-Ford
                        try {
                            BellmanFord algoBellmanFord = new BellmanFord(graph, v1);
                            algoBellmanFord.start();
                        } catch (Exception exce) {
                            System.out.println(e);
                        }
                        break;

                    case "Floyd": //Floyd Warshall
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
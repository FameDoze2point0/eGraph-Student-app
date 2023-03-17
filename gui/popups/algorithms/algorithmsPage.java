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
                        
                        informations.setText("<html><body><h1>Breadth-First Search</h1><br><br><h2>Principle</h2><p>This algorithm start from a vertex, then it takes all his neighbours that we will <br>visit. Again, it takes all neighbours of the neighbours and so on. This <br>algorithm uses a queue where we put the neighbours not visited at the end.</p><br><br><h2>Usage</h2><p>This algorithm is used to get connected components from a not oriented <br>graph with a linear complexity. Moreover, the principle of algorithm is also <br>used in different field as AI or Maze-solving algorithms.</p><br><br><h2>Complexity</h2><p>The time can be expressed as O(|V|+|E|) where every V means number of <br>vertices and E the number of edges. Note that O(|E|) may vary between O(1) <br>and O(|V*V|), depending on how much are there connections between <br>vertices. There are at most n*(n-1)/2 edges in a (complete) graph with n <br>vertices.</p></body></html>");


                        
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

                        informations.setText("<html><body><h1>Depth-First Search</h1><br><br><h2>Principle</h2><p>This algorithm start from a vertex, then it takes one of his neighbours that we <br>will visit. Again, it takes one of the neighbours of the neighbours and so on. <br>This algorithm uses a queue where we put the neighbours not visited at the <br>begining.</p><br><br><h2>Usage</h2><p>This algorithm is used to find strongly connected components from an <br>oriented graph. Moreover, the principle of algorithm is also used in solving <br>puzzles, AI, maze generation, finding biconnectivity in graphs and even <br>more.</p><br><br><h2>Complexity</h2><p>The time complexity can be expressed as O(|V|+|E|)for explicit graph without <br>repetition where every V means number of vertices and E the number of <br>edges. The space complexity can be expressed as O(|V|) if entire graph is <br>visited </p></body></html>");
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

                        informations.setText("<html><body><h1>Random Search</h1><h2>Principle</h2><p>This algorithm start from a vertex like BFS or DFS, however the selection <br>condition of the vertex is different. Indeed, the algorithm takes randomly a <br>vertex which is accessible and not already visited in the neighbours list.</p></body></html>");
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

                        informations.setText("<html><body><h1>Dijkstra's Algorithm</h1><br><br><h2>Principle</h2><p>The principle of this algorithm is to find the shortest path from a starting <br>vertex and the others (or a specific one). Firstly, we analyze all the neighbours <br>that we can access, then we picks the closest. Now, we only need to do again <br>the same thing while all vertices are not visited. Note that the graph has to be <br>oriented and weighted with positiv values.</p><br><br><h2>Usage</h2><p>This algorithm can have a lot of useful usages in our daily life, this most <br>famous example is, if we need to find the shortest way between two cities or <br>regions as GPS. We can also use different units for the edges.</p><br><br><h2>Complexity</h2><p>The time complexity can be expressed as O(|V|*log(|V|)+|E|) where log <br>denotes the binary log (log_2). For connected graphs the complexity can be <br>simplified to O(log(|V|)*|E|), the fibonacci heap can improve this one to <br>O(|V|*log(|V|)+|E|).</p></body></html>");

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

                        informations.setText("<html><body><h1>Bellman-Ford-Moore's Algorithm</h1><br><br><h2>Principle</h2><p>The Bellman-Ford algorithm is an algorithm which find the shotest path from <br>a starting vertex to others in a oriented and weighted graph. This algorithm <br>allow to have a graph with negative cycle (a cycle whoses edges sum to a <br>negative value</p><br><br><h2>Usage</h2><p>In the computer network, the algorithm is used to determine the messages <br>path through the RIP protocol. We can also use this algorithm to solve a <br>programming linear problem where the constraints are differences (x - y &le; 5 ; <br>y - t &le; 10 for example) </p><br><br><h2>Complexity</h2><p>The time complexity can be expressed as O(|V|*|E|) where V is the number of <br>vertices and E the number of edges.</p></body></html>");
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

                        informations.setText("<html><body><h1>Roy-Floyd-Warshall's Algorithm</h1><br><br><h2>Principle</h2><p>the Floyd-Warshall algorithm takes in input an oriented and weighted graph <br>where we try to find the shortest way between all vertices in the graph.</p><br><br><h2>Usage</h2><p>The Floyd-Warshall algorithm can be used in those situations : <br><ul><li>Shortest path in oriented and not weighted graph (we have to put <br>weights equal to 1)</li><li>Find a regular expression denoting the regular language defined by <br>a finite-state machine</li><li>Transitive closure of an oriented graph</li></ul></p><br><br><h2>Complexity</h2><p>The time complexity can be expressed as O(|V|*|V|*|V|) where V is the <br>number of vertices. Moreover the the space complexity is O(|V|*|V|).</p></body></html>");
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

                        informations.setText("<html><body><h1>Prim's Algorithm</h1><br><br><h2>Principle</h2><p>This algorithm is used to determine a minimum spanning tree in a graph from <br>a starting vertex in a connected, weighted and not oriented graph. The <br>Roy-Floyd-Warshall algorithm takes for each step the minimum weighted edge of <br>the available edges to the neighbours vertices. Note that we don't take an <br>edge if the starting and the ending vertices of the edge are already visited, because <br>it would create a cycle</p><br><br><h2>Complexity</h2><p>The time complexity can be expressed as O(|V|*|V|) where V is the number <br>of vertices.</p></body></html>");
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

                        informations.setText("<html><body><h1>Kruskal's Algorithm</h1><br><br><h2>Principle</h2><p>This algorithm is used to determine a minimum spanning tree in a graph from <br>a connected, weighted and not oriented graph. The Kruskal algorithm takes <br>for each step the minimum weighted edge in the graph in the case where a <br>cycle isn't create. The algorithm finish when all vertices are visited and in the <br>same connected component.</p><br><br><h2>Complexity</h2><p>The time complexity can be expressed as O(|E|*|V|) where V is the number of <br>vertices and E the number of edges. This complexity can be improved to <br>O(|E|*log(|V|)).</p></body></html>");
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

                informations = new JLabel("<html><body><h1>Breadth-First Search</h1><br><br><h2>Principle</h2><p>This algorithm start from a vertex, then it takes all his neighbours that we will <br>visit. Again, it takes all neighbours of the neighbours and so on. This <br>algorithm uses a queue where we put the neighbours not visited at the end.</p><br><br><h2>Usage</h2><p>This algorithm is used to get connected components from a not oriented <br>graph with a linear complexity. Moreover, the principle of algorithm is also <br>used in different field as AI or Maze-solving algorithms.</p><br><br><h2>Complexity</h2><p>The time complexity can be expressed as O(|V|+|E|) where every V means <br>number of vertices and E the number of edges. Note that O(|E|) may vary between O(1) <br>and O(|V*V|), depending on how much are there connections between <br>vertices. There are at most n*(n-1)/2 edges in a (complete) graph with n <br>vertices.</p></body></html>");
                informations.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(),"INFORMATIONS"));
                informations.setMinimumSize(new Dimension(500,40));
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
                if( gui.getTabulations().get(gui.getDraw().getSelectedComponent()).getVertices().isEmpty())
                    submit.setEnabled(false);
                addSubmitListener(gui,draw);
                panel_choix.add(submit,gbc);
            }
            this.add(panel_choix,constraints);

            constraints.gridwidth = 1;
        }
        // === END Disposition of elements on the page ===
        Graph graph = gui.getTabulations().get(gui.getDraw().getSelectedComponent());

        DIJKSTRA.setEnabled(false);
        BELLMAN.setEnabled(false);
        FLOYD.setEnabled(false);
        PRIM.setEnabled(false);
        KRUSKAL.setEnabled(false);

        if (graph.getOriented() && graph.getWeighted()) {
            DIJKSTRA.setEnabled(true);
            for (Edge edge : graph.getEdges()) {
                if ((int)edge.getWeight() <= 0) {
                    DIJKSTRA.setEnabled(false);
                    break;
                }
            }
            BELLMAN.setEnabled(true);
            FLOYD.setEnabled(true);

        }else if(graph.getWeighted()){
            PRIM.setEnabled(true);
            KRUSKAL.setEnabled(true);
        }


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
                    if (vertex.getName().equals(name1)) 
                        v1 = vertex;
                    if (vertex.getName().equals(name2)) 
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
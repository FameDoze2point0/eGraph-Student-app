package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gui extends JFrame
{
    /* Tell what state the program is currently in :
     * 0 = nothing is opened
     * 1 = a graph is opend
     * 2 = an automaton is opened
     * 3 = an algorithm is being executed
     */
    private static int state;

    //Elements of the gui
    JMenuBar menuBar; //The top menu

    //Menu to interact with the object
    JPanel interactionMenu;
    /*
     * The interaction menu have 3 states :
     * - When a Graph is opened
     * - When an automaton is opened
     * - When nothing is opened
     * - We have to create these states which will be displayed depending of the case
     * - When a graph or an automaton is opened, we have the launch algorithm and save option
     * - When nothing is opened, we have the open or new option
     */
        JPanel interactionMenu_Graph; //Graph
        JPanel interactionMenu_Automaton; //Automaton
        JPanel interactionMenu_Empty; //When nothing is opened
        JPanel interactionMenu_Opened; //When a graph or an automaton is opened

    public Gui()
    {
        super("eGraph Student");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setSize(960,540);
        this.setVisible(true); // we display the JFrame
        this.setLocationRelativeTo(null); // we center the JFrame

        //By default, nothing is opened
        state = 0;

        createMenuBar();
        this.setJMenuBar(menuBar);

        createInteractionMenu();
        //TEMPORAIRE
        interactionMenu = new JPanel(new FlowLayout(FlowLayout.LEFT));
        interactionMenu.setBackground(Color.RED);
        interactionMenu.add(interactionMenu_Graph);
        interactionMenu.add(interactionMenu_Opened);
        this.add(interactionMenu);
        //FIN TEMPORAIRE
        
        //Load the gui for the first time
        //reload();
    }

    private void createMenuBar()
    {
        // the menu bar
        menuBar = new JMenuBar();

        //File section
        JMenu menuFile = new JMenu("File");
            menuFile.setMnemonic('F');
        JMenuItem itemFile = new JMenuItem("New...");
            itemFile.setMnemonic('N');
            //icon
            itemFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
            //actionlistener
            menuFile.add(itemFile);

        itemFile = new JMenuItem("Open");
            itemFile.setMnemonic('O');
            //icon
            itemFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
            //actionlistener
            menuFile.addSeparator();
            menuFile.add(itemFile);

        itemFile = new JMenuItem("Save");
            itemFile.setMnemonic('S');
            //icon
            itemFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
            //actionlistener
            menuFile.addSeparator();
            menuFile.add(itemFile);

        itemFile = new JMenuItem("Export");
            itemFile.setMnemonic('E');
            //icon
            itemFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
            //actionlistener
            menuFile.addSeparator();
            menuFile.add(itemFile);
        
        itemFile = new JMenuItem("Exit");
            //icon
            itemFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
            itemFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    System.exit(0);
                }
            });
            menuFile.addSeparator();
            menuFile.add(itemFile);
            
        menuBar.add(menuFile);


        //Edit section
        JMenu menuEdit = new JMenu("Edit");
        menuFile.setMnemonic('E');

        JMenuItem itemEdit = new JMenuItem("Undo");
            //icon
            itemEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
            //actionlistener
            menuEdit.add(itemEdit);

        itemEdit = new JMenuItem("Redo");
            //icon
            itemEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
            //actionlistener
            menuEdit.addSeparator();
            menuEdit.add(itemEdit);
        
        itemEdit = new JMenuItem("Cut");
            //icon
            itemEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
            //actionlistener
            menuEdit.addSeparator();
            menuEdit.add(itemEdit);
    
        itemEdit = new JMenuItem("Copy");
            //icon
            itemEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
            //actionlistener
            menuEdit.addSeparator();
            menuEdit.add(itemEdit);

        itemEdit = new JMenuItem("Paste");
            //icon
            itemEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
            //actionlistener
            menuEdit.addSeparator();
            menuEdit.add(itemEdit);

        menuBar.add(menuEdit);






        //Algorithm section
        JMenu menuAlgorithm = new JMenu("Algorithm");
        menuFile.setMnemonic('A');

        JMenuItem itemAlgorithm = new JMenuItem("Launch Algorithm...");
            //icon
            itemAlgorithm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
            //actionlistener
            menuAlgorithm.add(itemAlgorithm);

            itemAlgorithm = new JMenuItem("Algorithm informations");
            //icon
            itemAlgorithm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
            //actionlistener
            menuAlgorithm.addSeparator();
            menuAlgorithm.add(itemAlgorithm);


        menuBar.add(menuAlgorithm);


        // //Settings section
        // JMenu menuSettings = new JMenu("Settings");
        // menuFile.setMnemonic('S');


        // menuBar.add(menuSettings);


        //Help section
        JMenu menuHelp = new JMenu("Help");
        menuHelp.setMnemonic('H');

        JMenuItem itemHelp = new JMenuItem("Shortcuts...");
            //icon
            //actionlistener
            menuHelp.add(itemHelp);
  
            itemHelp = new JMenuItem("Documentation...");
            //icon
            //actionlistener
            menuHelp.addSeparator();
            menuHelp.add(itemHelp);

            itemHelp = new JMenuItem("Licence...");
            //icon
            //actionlistener
            menuHelp.addSeparator();
            menuHelp.add(itemHelp);

            itemHelp = new JMenuItem("About...");
            //icon
            //actionlistener
            menuHelp.addSeparator();
            menuHelp.add(itemHelp);
        

        menuBar.add(menuHelp);




    }

    private void createInteractionMenu()
    {
        // === GRAPH ===
        interactionMenu_Graph = new JPanel();
        //Vertex button
        JButton newVertex = new JButton("Vertex +");
        interactionMenu_Graph.add(newVertex);
        //Edge button
        JButton newEdge = new JButton("Edge +");
        interactionMenu_Graph.add(newEdge);

        // === AUTOMATON ===
        interactionMenu_Automaton = new JPanel();
        //State button
        JButton newState = new JButton("State +");
        interactionMenu_Automaton.add(newState);
        //Starting state button
        JButton newStartingState = new JButton("Starting State +");
        interactionMenu_Automaton.add(newStartingState);
        //Ending state button
        JButton newAcceptingState = new JButton("Accepting State +");
        interactionMenu_Automaton.add(newAcceptingState);
        
        //WHEN A GRAPH OR AN AUTOMATON IS OPENED
        interactionMenu_Opened = new JPanel();
        //Launch algorithm button
        JButton algorithm = new JButton("Launch algorithm");
        interactionMenu_Opened.add(algorithm);
        //Save button
        JButton save = new JButton("Save");
        interactionMenu_Opened.add(save);

        //WHEN NOTHING IS OPENED
        interactionMenu_Empty = new JPanel();
        interactionMenu_Empty.add(new JLabel("Nothing is selected."));
        //New button
        JButton openWhenEmpty = new JButton("Open...");
        interactionMenu_Empty.add(openWhenEmpty);
        //Open button
        JButton newWhenEmpty = new JButton("New +");
        interactionMenu_Empty.add(newWhenEmpty);
    }

    public void reload()
    {
        switch(state)
        {
            //Nothing is opened
            case 0 : {
                //Interaction menu
                interactionMenu = new JPanel(); //Reset current interaction menu
                interactionMenu.add(interactionMenu_Empty);
            }

            //A graph is opened
            case 1 : {
                //Interaction menu
                interactionMenu = new JPanel(); //Reset current interaction menu
                interactionMenu.add(interactionMenu_Graph); //Graph buttons
                interactionMenu.add(interactionMenu_Opened); //Save and launch algorithm buttons
            }

            //An automaton is opened
            case 2 : {
                //Interaction menu
                interactionMenu = new JPanel(); //Reset current interaction menu
                interactionMenu.add(interactionMenu_Automaton); //Automaton buttons
                interactionMenu.add(interactionMenu_Opened); //Save and launch algorithm buttons
            }
        }
    }
}
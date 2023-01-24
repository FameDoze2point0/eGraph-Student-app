package gui;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import components.Graph;

public class Gui<T> extends JFrame
{

    //Dimensions of the window
    int height;
    int width;

    /* Tell what state the program is currently in :
     * 0 = nothing is opened
     * 1 = a graph is opend
     * 2 = an automaton is opened
     * 3 = an algorithm is being executed
     */
    private static int state;

    /* We must organize the gui :
     * - At the top, a JMenuBar (menu)
     * - The Main JPanel :
     *   - contains a JPanel (interaction menu)
     *   - Below, contains 3 JPanels that contains
     *      - An informations Panel
     *      - A list of open tabs
     *      - A draw area
     *   - At the bottom, a JPanel that contains a few informations
     */

    //Elements of the gui
    JMenuBar menuBar; //The top menu

    //The mainJPanel
    JPanel mainJPanel;

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

        //The JPanel that contains informations tab, tabs and drawArea
        JPanel middleArea; //The middle area
            //We have 2 JPanels in the middle area : left one (informations tab) and right one (tabs + draw area)
            JPanel middleDivison; //Divide the middle area in two
                JPanel informations; //informations panel

                //Contain a title and informations associated with selectionned item
                JPanel informationsTitle; //Title
                JPanel informationsPanel; //Informations of the selectionned item

                //This area is divided in two : tabs and draw area
                JPanel tabsAndDraw; //The JPanel that contain drawarea and tabs
                    JPanel drawArea; //Informations tab
                    //The tab menu contains a list of button
                    JPanel tabs;
                        JButton newGraph;
                        JButton newAutomaton;
                        ArrayList<Graph<T>> openedTabs;
                    
        //Bottom JPanel
        JPanel bottomJPanel;

    public Gui()
    {
        super("eGraph Student");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //we get the width and height of the user screen
        Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize(); //We get the screen size
        height = (int)screenDimensions.getHeight(); //Getting screen height
        width = (int)screenDimensions.getWidth(); //Getting screen width
        this.setSize(width,height); //Setting the default size
        this.setLocationRelativeTo(null); //We center the JFrame

        //By default, nothing is opened
        state = 0;

        //The JMenu (not inside the main JPanel)
        createMenuBar();
        this.setJMenuBar(menuBar);

        //The JPanel that contain everything
        mainJPanel = new JPanel();
        mainJPanel.setLayout(new BoxLayout(mainJPanel, BoxLayout.PAGE_AXIS));

        //The interaction menu
        createInteractionMenu();

        //TEMPORAIRE
        interactionMenu.add(interactionMenu_Graph);
        interactionMenu.add(interactionMenu_Opened);
        mainJPanel.add(interactionMenu);
        //FIN TEMPORAIRE

        //Area to draw, get infos, choose, etc...
        createDrawArea(); //Will create the tab menu, the informations panel and the draw area
        mainJPanel.add(middleArea);

        //Bottom area to see some infos
        createBottomArea();

        this.add(mainJPanel); //We add the main JPanel to the JFrame
        this.setVisible(true); //We display the JFrame
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
        //Creation of the menu
        interactionMenu = new JPanel(new FlowLayout(FlowLayout.LEFT));
        interactionMenu.setBackground(Color.LIGHT_GRAY); //Le menu d'interaction a une couleur de fond grise
        interactionMenu.setBorder(BorderFactory.createLineBorder(Color.black)); //Border
        interactionMenu.setMaximumSize(new Dimension(width,(int)(height*0.05))); //5% of the height

        // === GRAPH ===
        interactionMenu_Graph = new JPanel();
        interactionMenu_Graph.setBackground(Color.LIGHT_GRAY); //Ce JPanel a une couleur de fond grise
        //Vertex button
        JButton newVertex = new JButton("Vertex +");
        interactionMenu_Graph.add(newVertex);
        //Edge button
        JButton newEdge = new JButton("Edge +");
        interactionMenu_Graph.add(newEdge);

        // === AUTOMATON ===
        interactionMenu_Automaton = new JPanel();
        interactionMenu_Automaton.setBackground(Color.LIGHT_GRAY); //Ce JPanel a une couleur de fond grise
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
        interactionMenu_Opened.setBackground(Color.LIGHT_GRAY); //Ce JPanel a une couleur de fond grise
        //Launch algorithm button
        JButton algorithm = new JButton("Launch algorithm");
        interactionMenu_Opened.add(algorithm);
        //Save button
        JButton save = new JButton("Save");
        interactionMenu_Opened.add(save);

        //WHEN NOTHING IS OPENED
        interactionMenu_Empty = new JPanel();
        interactionMenu_Empty.setBackground(Color.LIGHT_GRAY); //Ce JPanel a une couleur de fond grise
        interactionMenu_Empty.add(new JLabel("Nothing is selected."));
        //New button
        JButton openWhenEmpty = new JButton("Open...");
        interactionMenu_Empty.add(openWhenEmpty);
        //Open button
        JButton newWhenEmpty = new JButton("New +");
        interactionMenu_Empty.add(newWhenEmpty);
    }

    private void createDrawArea()
    {
        //Two JPanels : left one (informations tab) and right one (tabs + draw area)
        //In the right JPanel, 2 JPanels : tabs and draw area, but in boxlayout

        //Initialisation of the area
        middleArea = new JPanel();
        middleArea.setLayout(new BoxLayout(middleArea, BoxLayout.LINE_AXIS));

            //Informations tab
            informations = new JPanel();
            informations.setLayout(new BoxLayout(informations, BoxLayout.PAGE_AXIS));
            informations.setBorder(BorderFactory.createLineBorder(Color.black)); //Border
            informations.setMaximumSize(new Dimension((int)(width*0.1),(int)(height*0.9))); //10% width, 90% height

                //The informations tab is divided
                //Title
                informationsTitle = new JPanel();
                informationsTitle.add(new JButton("INFORMATIONS"));
                informationsTitle.setBackground(Color.gray);
                //informationsTitle.setBorder(BorderFactory.createLineBorder(Color.black)); //Border
                informationsTitle.setMaximumSize(new Dimension((int)(width*0.1),(int)(height*0.1))); //10% width, 10% height
                informations.add(informationsTitle);

                //Infos
                informationsPanel = new JPanel();
                informationsPanel.add(new JLabel("blablabla"));
                informationsPanel.setBackground(Color.LIGHT_GRAY);
                informationsPanel.setBorder(BorderFactory.createLineBorder(Color.black)); //Border
                informationsPanel.setPreferredSize(new Dimension((int)(width*0.1),(int)(height*0.8))); //10% width, 80% height
                informations.add(informationsPanel);

            //Tabs + drawArea
            tabsAndDraw = new JPanel();
            tabsAndDraw.setLayout(new BoxLayout(tabsAndDraw, BoxLayout.PAGE_AXIS));
            tabsAndDraw.setBorder(BorderFactory.createLineBorder(Color.black)); //Border
            tabsAndDraw.setMaximumSize(new Dimension((int)(width*0.9),(int)(height*0.9))); //90% width, 90% height

                //Tabs area
                tabs = new JPanel(new FlowLayout(FlowLayout.LEFT));
                tabs.setBackground(Color.LIGHT_GRAY);
                tabs.setMaximumSize(new Dimension((int)(width*0.9),(int)(height*0.1))); //90% width, 5% height

                    //We add a few button in the tab area
                    //Create graph button
                    newGraph = new JButton("New Graph");
                    tabs.add(newGraph);
                    //Create automaton button
                    newAutomaton = new JButton("New Automaton");
                    tabs.add(newAutomaton);
                    //We init the tab arraylist
                    openedTabs = new ArrayList<Graph<T>>();

                //Draw area
                drawArea = new JPanel();
                drawArea.setBackground(Color.WHITE);
                drawArea.setBorder(BorderFactory.createLineBorder(Color.black)); //Border
                drawArea.setPreferredSize(new Dimension((int)(width*0.9),(int)(height*0.8))); //90% width, 80% height

            //we add the panels to tabsAndDraw
            tabsAndDraw.add(tabs);
            tabsAndDraw.add(drawArea);

        //We add the JPanels to middleArea
        middleArea.add(informations);
        middleArea.add(tabsAndDraw);
    }

    private void createBottomArea()
    {
        bottomJPanel = new JPanel();
        bottomJPanel.setBackground(Color.DARK_GRAY);
        bottomJPanel.setBorder(BorderFactory.createLineBorder(Color.black)); //Border
        bottomJPanel.setMaximumSize(new Dimension(width,(int)(height*0.05))); //5% height
        mainJPanel.add(bottomJPanel);
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
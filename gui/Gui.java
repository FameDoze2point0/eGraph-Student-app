package gui;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;
import components.Graph;
import components.Vertex;
import components.Automaton;

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

            //Buttons in the interaction menu
            JButton newVertex;
            JButton newEdge;
            JButton newState;
            JButton newStartingState;
            JButton newAcceptingState;
            JButton newTransition;
            JButton openWhenEmpty;
            JButton algorithm;
            JButton save;

        //The JPanel that contains informations tab, tabs and drawArea
        JPanel middleArea; //The middle area
            //We have 2 JPanels in the middle area : left one (informations tab) and right one (tabs + draw area)
            JPanel middleDivison; //Divide the middle area in two
                JPanel informations; //informations panel

                //Contain a title and informations associated with selectionned item
                JPanel informationsTitle; //Panel that contains the title
                JPanel informationsPanel; //Informations of the selectionned item

                //This area is divided in two : tabs and draw area
                static JTabbedPane tabsAndDraw; //The JPanel that contain drawarea and tabs
                    
        //Bottom JPanel
        JPanel bottomJPanel;
            JLabel bottomJPanel_coord; //Coordinates of the mouse on the draw area

        //Algorithm launcher page
        JDialog algorithmPage;

    public Gui()
    {
        super("eGraph Student");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Style of the application
        try
        {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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
        this.add(mainJPanel); //We add the main JPanel to the JFrame

        //The interaction menu
        reloadInteractionMenu();

        //Area to draw, get infos, choose, etc...
        reloadDrawArea(); //Will create the tab menu, the informations panel and the draw area

        //Bottom area to see some infos
        reloadBottomArea();

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
                    algorithmPage.setVisible(true);
                }
            });
            menuFile.addSeparator();
            menuFile.add(itemFile);
            
        menuBar.add(menuFile);


        //Edit section
        JMenu menuEdit = new JMenu("Edit");
        menuFile.setMnemonic('E');

        JMenuItem tabClose = new JMenuItem("Close tab");
            //icon
            tabClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
            tabClose.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(tabsAndDraw.getTabCount() != 0)
                    {
                        tabsAndDraw.remove(tabsAndDraw.getSelectedIndex());
                    }
                }
            });
            menuEdit.add(tabClose);
            menuEdit.addSeparator();

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
            itemFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    algorithmPage.setVisible(true);
                }
            });
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

    private void reloadInteractionMenu()
    {
        //Creation of the menu
        interactionMenu = new JPanel(new GridLayout(1,9));
        interactionMenu.setBackground(Color.LIGHT_GRAY); //Le menu d'interaction a une couleur de fond grise
        interactionMenu.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK)); //Border
        interactionMenu.setMaximumSize(new Dimension(width,0));

        // === GRAPH ===
        //Vertex button
        newVertex = new JButton("Vertex +");
        //Edge button
        newEdge = new JButton("Edge +");

        // === AUTOMATON ===
        //State button
        newState = new JButton("State +");
        //Starting state button
        newStartingState = new JButton("Starting State +");
        //Ending state button
        newAcceptingState = new JButton("Accepting State +");
        //Transition between 2 states button
        newTransition = new JButton("Transition +");
        
        //WHEN A GRAPH OR AN AUTOMATON IS OPENED
        //Launch algorithm button
        algorithm = new JButton("Launch algorithm");
        //Save button
        save = new JButton("Save");

        //WHEN NOTHING IS OPENED
        //Open button
        openWhenEmpty = new JButton("Open...");

        //Adding every button to the interaction menu
        //When empty
        interactionMenu.add(openWhenEmpty);
        //Graph related
        interactionMenu.add(newVertex);
        interactionMenu.add(newEdge);
        //Automaton related
        interactionMenu.add(newState);
        interactionMenu.add(newStartingState);
        interactionMenu.add(newAcceptingState);
        interactionMenu.add(newTransition);
        //Other
        interactionMenu.add(algorithm);
        interactionMenu.add(save);

        //Adding the interaction menu to the main JPanel
        mainJPanel.add(interactionMenu);
    }

    private void reloadDrawArea()
    {
        //Two JPanels : left one (informations tab) and right one (tabs + draw area)
        //In the right JPanel, 2 JPanels : tabs and draw area, but in boxlayout

        //Initialisation of the area
        middleArea = new JPanel();
        middleArea.setLayout(new BoxLayout(middleArea, BoxLayout.LINE_AXIS));
        middleArea.setMaximumSize(new Dimension(width,(int)(height*0.9))); //10% width, 90% height

            //Informations tab
            informations = new JPanel();
            informations.setLayout(new BoxLayout(informations, BoxLayout.PAGE_AXIS));
            informations.setMaximumSize(new Dimension(width,(int)(height*0.9))); //10% width, 90% height

                //The informations tab is divided
                informationsTitle = new JPanel(); //Title
                informationsTitle.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, Color.BLACK)); //Border

                //JButton
                JLabel titleLabel = new JLabel("Informations");
                titleLabel.setBorder(null);

                //We add the button
                informationsTitle.add(titleLabel);
                informationsTitle.setBackground(Color.gray);
                informations.add(informationsTitle);

                //Infos
                informationsPanel = new JPanel();
                informationsPanel.add(new JLabel("blablabla"));
                informationsPanel.setBackground(Color.LIGHT_GRAY);
                informationsPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK)); //Border
                informationsPanel.setPreferredSize(new Dimension((int)(width*0.1),height)); //10% width
                informations.add(informationsPanel);

            //Tabs + drawArea
            tabsAndDraw = new JTabbedPane();
            tabsAndDraw.setTabLayoutPolicy(1);
            
        //We add the JPanels to middleArea
        middleArea.add(informations);
        middleArea.add(tabsAndDraw);

        //We add the middleArea to the main JPanel
        mainJPanel.add(middleArea);
    }

    private void reloadBottomArea()
    {
        bottomJPanel = new JPanel(new GridLayout(1,10));
        bottomJPanel.setBackground(Color.DARK_GRAY);

        //Setting up the elements
        bottomJPanel_coord = new JLabel("(X,Y) = 0,0");
        bottomJPanel_coord.setForeground(Color.WHITE);
        bottomJPanel.add(bottomJPanel_coord);

        //TEMP
        JButton b = new JButton();
        b.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                newTab(new Graph<Integer>("TEST", true, true));
            }
        });
        bottomJPanel.add(b);
        //FINTEMP

        bottomJPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK)); //Border
        mainJPanel.add(bottomJPanel);
    }

    public void newTab(Object o)
    {
        try
        {
            //New graph
            if(o instanceof Graph)
            {
                tabsAndDraw.addTab("Tab n°"+tabsAndDraw.getTabCount(), null, new PanelPaint(),null);
            }

            //New automaton
            if(o instanceof Automaton)
            {
                tabsAndDraw.addTab("Tab n°"+tabsAndDraw.getTabCount(), null, new PanelPaint(),null);
            }

            //we add the way to get mouse coords
            tabsAndDraw.getSelectedComponent().addMouseMotionListener(new MouseMotionListener()
            {
                @Override
                public void mouseDragged(MouseEvent e) {
                    //Not useful for now but had to implement it
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    bottomJPanel_coord.setText("(X,Y)=("+e.getX()+","+e.getY()+")");
                }
            });

            return;
        }

        catch(Exception e)
        {
            System.out.println("newTab function called with wrong arguments, cannot create a new tab.");
            return;
        }
    }

    public void repaint()
    {

    }

    static public JTabbedPane getTabsAndDraw()
    {
        return tabsAndDraw;
    }

}
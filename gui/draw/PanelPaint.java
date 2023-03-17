package gui.draw;
import java.awt.event.*;
import java.lang.ProcessHandle.Info;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import gui.Gui;
import gui.draw.rightclickmenu.RightClick;
import gui.popups.newElement.AskWeight;
import gui.popups.textInput.TextInput;
import settings.Settings;
import settings.SettingsDialog;
import util.Edge;
import util.Graph;
import util.Vertex;
import java.awt.*;

public class PanelPaint extends JPanel implements MouseListener, MouseMotionListener
{
    private transient Gui gui;
    private transient Draw drawArea;

    //When we add an edge, if the starting point is already selected
    private Vertex start = null; //If not selected = null

    private int X;
    private int Y;
    private Vertex isDragged = null; //Vertex that is being mouse dragged

    // //Menu when we right click
    private RightClick rightClickMenu;

    //Stack to do the undo/redo
    private Stack<Graph> undo;
    private Stack<Graph> redo;

    //GridBagLayout constraints
    private GridBagConstraints gbc; //Used to place the button

    //Extend button
    private JButton extend;

    //Close button
    private JButton close;

    //The informations panel
    private boolean isOpened = false; //When the information panel is opened, we do not redraw the graph (except the graph in settings)
    private JPanel filter; //When the menu is opened we add a filter
    private JPanel informations;
        //Elements in the informations panel    
        private JLabel graphNameLabel, isOriented, isWeighted, nbrVertex, nbrEdges;
        private JButton nameChange, isOrientedSwitch, isWeightedSwitch, duplicate, applySettings;

















































    public PanelPaint(Gui gui, Draw drawArea)
    {
        super(new GridBagLayout());
        //Retreiving the gui and the draw area
        this.gui = gui;
        this.drawArea = drawArea;

        //MouseListener
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //Right click menu
        rightClickMenu = new RightClick(drawArea, this, gui);
        this.add(rightClickMenu);

        //Constraints
        gbc = new GridBagConstraints();

        //Close Button
        {
            close = new JButton("Close", new ImageIcon(System.getProperty("user.dir")+"/ressources/lc_downsearch.png"));
            close.setPreferredSize(new Dimension(32, 32));

            //When we click on this button, we want to reveal :
            //  - A JTabbedPane to modify the graph
            //  - A JButton to close back the new openend JTabbedPane
            close.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    close.setVisible(false);
                    informations.setVisible(false);
                    extend.setVisible(true);
                    isOpened = false;
                    filter.setVisible(false);
                    repaint();
                }
            });

            //Placement
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.weighty = 0.2;
            gbc.anchor = GridBagConstraints.PAGE_END;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            //Not visible by default
            close.setVisible(false);
            add(close, gbc);
        }

        //Informations panel
        {
            informations = new JPanel(new GridLayout(0,2));
            {
                //Graph name
                graphNameLabel = new JLabel();
                graphNameLabel.setHorizontalAlignment(JLabel.CENTER);
                informations.add(graphNameLabel);

                //Edit name
                nameChange = new JButton("Edit name...");
                nameChange.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new TextInput(gui, gui.getTabulations().get(drawArea.getSelectedComponent()));
                    }
                });
                informations.add(nameChange);
                
                //Is oriented
                isOriented = new JLabel();
                isOriented.setHorizontalAlignment(JLabel.CENTER);
                informations.add(isOriented);

                //Edit oriented
                isOrientedSwitch = new JButton("Switch");
                isOrientedSwitch.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Graph g = gui.getTabulations().get(gui.getDraw().getSelectedComponent());
                        if ( g.getOriented())
                        {
                            g.setOriented(false);
                            isOriented.setText("Oriented : No");

                            //We also have to put both direction the same weight so it do not overlap
                            for(Edge edge1 : g.getEdges())
                            {
                                for(Edge edge2 : g.getEdges())
                                {
                                    if((edge1.getStart() == edge2.getEnd()) && (edge1.getWeight() != edge2.getWeight()))
                                    {
                                        edge2.setWeight(edge1.getWeight());
                                    }
                                }
                            }
                        }               
                        else
                        {
                            g.setOriented(true);
                            isOriented.setText("Oriented : Yes");    
                        }   
                        
                    }
                });
                informations.add(isOrientedSwitch);

                //Is weighted
                isWeighted = new JLabel();
                isWeighted.setHorizontalAlignment(JLabel.CENTER);
                informations.add(isWeighted);

                //Edit weighted
                isWeightedSwitch = new JButton("Switch");
                isWeightedSwitch.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Graph g = gui.getTabulations().get(gui.getDraw().getSelectedComponent());
                        if ( g.getWeighted()){
                            g.setWeighted(false);
                            isWeighted.setText("Weighted : No");
                        }
                        else{
                            g.setWeighted(true);
                            isWeighted.setText("Weighted : Yes");
                        }
                    }
                });
                informations.add(isWeightedSwitch);

                //Number of vertex
                nbrVertex = new JLabel();
                nbrVertex.setHorizontalAlignment(JLabel.CENTER);
                informations.add(nbrVertex);

                //Duplicate button
                duplicate = new JButton("Duplicate");
                duplicate.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        //Getting graph to duplicate
                        Graph toDup = gui.getTabulations().get(drawArea.getSelectedComponent());
                        //Creating the tabulation
                        PanelPaint panelPaint = new PanelPaint(gui, drawArea);
                        drawArea.addTab(toDup.getName()+" - copy", null, panelPaint, toDup.getName()+" - copy"); //We create the new tab and retrieve it
                        //Creating a new Graph
                        //Each graph is associated with a tabulation
                        Graph graph = toDup.clone();
                        graph.setName(graph.getName()+" - copy");
                        //We then add these two components to the list of opened tabulations
                        gui.getTabulations().put(panelPaint,graph);

                        //We go to the new tabulation and draw the panel
                        drawArea.setSelectedComponent(panelPaint);
                        panelPaint.repaint();
                    }
                });
                informations.add(duplicate);

                //Number of edges
                nbrEdges = new JLabel();
                nbrEdges.setHorizontalAlignment(JLabel.CENTER);
                informations.add(nbrEdges);

                //Apply settings button
                applySettings = new JButton("Apply settings to graph");
                applySettings.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Settings settings = Gui.getSettings();
                        Graph graph = gui.getTabulations().get(drawArea.getSelectedComponent());
                        for(Vertex vertex : graph.getVertices())
                        {
                            vertex.setBorderColor(settings.getVertexOutsideColor());
                            vertex.setInsideColor(settings.getVertexInsideColor());
                            vertex.setNameColor(settings.getVertexNameColor());
                            vertex.setStrokeWidth(settings.getVertexStrokeWidth());
                            vertex.setDiameter(settings.getVertexDiameter());
                        }

                        for(Edge edge : graph.getEdges())
                        {
                            edge.setStrokeColor(settings.getEdgeStrokeColor());
                            edge.setHighlightColor(settings.getEdgeHighlightColor());
                            edge.setWeightColor(settings.getEdgeWeightColor());
                            edge.setWeightBorderColor(settings.getEdgeWeightBorderColor());
                            edge.setEdgeArrowTipColor(settings.getEdgeArrowTipColor());
                            edge.setStrokeWidth(settings.getEdgeStrokeWidth());
                            edge.setArrowTipWidth(settings.getArrowLength());
                        }
                    }
                });
                informations.add(applySettings);
            }

            //Placement of the informations panel
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weightx = 1;
            gbc.weighty = 0.01;
            gbc.anchor = GridBagConstraints.PAGE_END;
            gbc.fill = GridBagConstraints.BOTH;
            
            //Not visible by default
            informations.setVisible(false);
            add(informations,gbc);
        }

        //Extend button
        {
            extend = new JButton("Extends", new ImageIcon(System.getProperty("user.dir")+"/ressources/lc_upsearch.png"));
            extend.setPreferredSize(new Dimension(32, 32));

            //When we click on this button, we want to reveal :
            //  - A JTabbedPane to modify the graph
            //  - A JButton to close back the new openend JTabbedPane
            extend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    close.setVisible(true);
                    informations.setVisible(true);
                    extend.setVisible(false);
                    isOpened = true;
                    filter.setVisible(true);
                    filter.repaint();

                    //We display current graph informations
                    Graph graph;
                    if((graph = gui.getTabulations().get(drawArea.getSelectedComponent())) != null)
                    {
                        //Retreiving graph info
                        nbrVertex.setText("Vertices : "+graph.getVertices().size());
                        nbrEdges.setText("Edges : "+graph.getEdges().size());
                        graphNameLabel.setText("Name : "+graph.getName());
                        if(graph.getOriented())
                        {
                            isOriented.setText("Oriented : Yes");
                        }
                        else
                        {
                            isOriented.setText("Oriented : No");
                        }

                        if(graph.getWeighted())
                        {
                            isWeighted.setText("Weighted : Yes");
                        }
                        else
                        {
                            isWeighted.setText("Weighted : No");
                        }
                    }
                    else
                    {
                        graphNameLabel.setText("Name : Undefined");
                        isOriented.setText("Oriented : Undefined");
                        isWeighted.setText("Weighted : Undefined");
                    }
                }
            });

            //Placement
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weightx = 1;
            gbc.weighty = 0.1;
            gbc.anchor = GridBagConstraints.PAGE_END;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            add(extend, gbc);
        }

        //Filter (when the information panel is opened)
        filter = new JPanel();
        filter.setBackground(new Color(0, 0, 0, 80));
        //By default hidden, only showed when the menu is opened
        filter.setVisible(false);

        //We place the filter on the PanelPaint
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(filter, gbc);

        undo = new Stack<Graph>();
        redo = new Stack<Graph>();
    }




























    public void paint(Graphics graphics)
    {
        super.paint(graphics); //Refresh the JPanel with blank space
        Graph graph;
        if(gui.getState() == 3)
        {
            graph = SettingsDialog.getExample_graph();
        }
        else
        {
            graph = gui.getTabulations().get(drawArea.getSelectedComponent());
        }

        if (graph != null) {
            Object itemCollision = graph.vertexCollision(X, Y);
            if (itemCollision == null) {
                itemCollision = graph.edgeCollision( X, Y);
            }
            graph.paint(graphics, itemCollision);    
        }

        /// === FINALLY, IF WE ARE IN STATE 1 OR 2, SHOW A PREVIEW OF THE NEW EDGE/VERTEX ===
        //If we are in state 1, we previzualize the new vertex
        if(gui.getState() == 1)
        {
            int radius = Gui.getSettings().getVertexDiameter()/2;
            int diameter = Gui.getSettings().getVertexDiameter();
            //We draw a vertex on top of the mouse with 50% opacity
            //Setting 35% opacity
            ((Graphics2D)graphics).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.35f));
            //Borders
            graphics.setColor(Gui.getSettings().getVertexOutsideColor());
            graphics.fillOval(X-radius, Y-radius, diameter, diameter);

            //Inside
            graphics.setColor(Gui.getSettings().getVertexInsideColor());
            graphics.fillOval((X+((int)(radius*0.2)))-(radius), (Y+((int)(radius*0.2)))-(radius), (int)(diameter*0.8), (int)(diameter*0.8));
        }

        //If we are in state 2 and the first vertex is selectionned, we previzualize the new edge (if first vertex selectionned)
        else if(gui.getState() == 2 && start != null)
        {
            //Setting 50% opacity
            ((Graphics2D)graphics).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.35f));
            //Drawing the line
            graphics.setColor(Gui.getSettings().getEdgeStrokeColor());
            ((Graphics2D)graphics).setStroke(new BasicStroke(Gui.getSettings().getEdgeStrokeWidth())); //Change stroke
            graphics.drawLine(start.getCoordX()+Gui.getSettings().getVertexDiameter()/2, start.getCoordY()+Gui.getSettings().getVertexDiameter()/2, X, Y);
        }

        close.repaint();
        extend.repaint();
        informations.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        //Only redraw is the information panel is not opened
        if(!isOpened && gui.getState() != 3)
        {
            Graph graph = gui.getTabulations().get(drawArea.getSelectedComponent());
            Vertex vertex;
            Edge edge;
            int radius = Gui.getSettings().getVertexDiameter()/2;
            //=== LEFT CLICK DETECTION === to create new elements and interact
            if(e.getButton() == MouseEvent.BUTTON1)
            {
                switch(gui.getState())
                {
                    case(1): {
                        //In state 1, when the mouse is clicked, we create a new vertex/state
                        //We save for undo
                        updateUndo();
                        
                        //We retrieve the actual graph/automaton
                        int cpt = graph.getCpt();
                        graph.addVertex(new Vertex(cpt,e.getX() - radius, e.getY() - radius, Gui.getSettings().getVertexDiameter(), Gui.getSettings().getVertexStrokeWidth(), Gui.getSettings().getVertexInsideColor(), Gui.getSettings().getVertexOutsideColor(), ""+(cpt), Gui.getSettings().getVertexNameColor())); //We add the new vertex to the graph
                        break;
                    }

                    case(2): {
                        //In state 2, we want the user to click on 2 differents vertex/state to create an edge
                        //We first look if the user clicked on a vertex
                        if((vertex = graph.vertexCollision(X,Y)) != null)
                        {
                            //The user clicked on this vertex
                            //If the start vertex is != null, then we have both vertex required to draw the edge
                            if(start == null)
                            {
                                start = vertex;
                            }
                            else
                            {
                                //Saving the graph before we add the edge
                                updateUndo();
                                Integer weight = 0;
                                if (graph.getWeighted()) {
                                    AskWeight askWeightPage = new AskWeight(gui, drawArea);
                                    weight = askWeightPage.getWeight();
                                    if (weight == null){
                                        weight = 0;
                                    }
                                }
                                graph.addEdge(new Edge(start, vertex, weight, Gui.getSettings().getEdgeStrokeWidth(), Gui.getSettings().getEdgeStrokeColor(), Gui.getSettings().getEdgeHighlightColor(), Gui.getSettings().getEdgeArrowTipColor(), Gui.getSettings().getEdgeWeightColor(), Gui.getSettings().getEdgeWeightBorderColor(), graph,Gui.getSettings().getArrowLength()));
                                start = null;
                            }
                        }
                        break;
                    }

                    default: {
                        break;
                    }
                }
                this.repaint();
            }



            // === RIGHT CLICK DETECTION === to open up the quick interaction menu
            else if(e.getButton() == MouseEvent.BUTTON3 && gui.getState() != 3)
            {
                //When we right click, we detect if we are on top of a vertex or an edge to add some quick interactions to them
                if((vertex = graph.vertexCollision(e.getX(), e.getY())) != null)
                {

                    rightClickMenu.changeState(false, true, true, vertex);
                }
                else if((edge = graph.edgeCollision(e.getX(), e.getY())) != null)
                {
                    rightClickMenu.changeState(true, false, true, edge);
                }
                else
                {
                    rightClickMenu.changeState(false, false, false, null);
                }
                //We then have some default features, always there
                rightClickMenu.show(drawArea.getSelectedComponent() , e.getX(), e.getY()); 
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if(isDragged != null)
        {
            isDragged.setCoordX(e.getX()-isDragged.getDiameter()/2);
            isDragged.setCoordY(e.getY()-isDragged.getDiameter()/2);   
            //We must reset every path
            Graph graph = gui.getTabulations().get(drawArea.getSelectedComponent());
            for(Edge edge : graph.getEdges())
            {
                edge.setCollisionArea(edge.refreshCollisionArea(graph.getOriented(), graph.bothDirections(edge)));
            }

            repaint();
        }
    }

    @Override //Is used to register the vertex we click on (to drag it)
    public void mousePressed(MouseEvent e)
    {
        //Only if the information panel is not opened
        if(!isOpened && gui.getState() != 3)
        {
            //If we are on top of a vertex, we drag it
            Graph graph = gui.getTabulations().get(drawArea.getSelectedComponent());
            Vertex vertex;
            if((vertex = graph.vertexCollision(X,Y)) != null)
            {
                isDragged = vertex;
            }
        }    
    }

    @Override //We release the dragged vertex
    public void mouseReleased(MouseEvent e) {
        if(isDragged != null)
        {
            isDragged = null;
        }
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        //We don't want to be able to drag vertex outside the draw area
        isDragged = null; //We remove it, so it cannot be dragged anymore
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        //Only redraw is the information panel is not opened OR we are in settings
        if(!isOpened)
        {
            //We always repaint, to see if we are on top of an element to highlight it, to preview when we are creating a new edge/vertex, ...
            X = e.getX();
            Y = e.getY();
            repaint();
        }
    }

    public void updateUndo()
    {
        
        Graph graphCopy = gui.getTabulations().get(this).clone();
        undo.add(graphCopy); //Adding a copy

        getRightClickMenu().getUndo().setEnabled(true);
        gui.getTools().getUndo().setEnabled(true);
        gui.getMenu().getEdit().getUndo().setEnabled(true);
        this.repaint();

        if(!redo.isEmpty())
        {
            redo.clear();
            getRightClickMenu().getRedo().setEnabled(false);
            gui.getTools().getRedo().setEnabled(false);
            gui.getMenu().getEdit().getRedo().setEnabled(false);
        }
    }

    public void undo()
    {
        redo.add(gui.getTabulations().get(this));
        gui.getTabulations().replace(this,undo.pop());
        
        if(undo.empty())
        {
            getRightClickMenu().getUndo().setEnabled(false);
            gui.getTools().getUndo().setEnabled(false);
            gui.getMenu().getEdit().getUndo().setEnabled(false);
        }

        if(!redo.empty())
        {
            getRightClickMenu().getRedo().setEnabled(true);
            gui.getTools().getRedo().setEnabled(true);
            gui.getMenu().getEdit().getRedo().setEnabled(true);
        }
        repaint();
    }

    public void redo()
    {
        undo.add(gui.getTabulations().get(this));
        gui.getTabulations().replace(this,redo.pop());

        if(redo.empty())
        {
            getRightClickMenu().getRedo().setEnabled(false);
            gui.getTools().getRedo().setEnabled(false);
            gui.getMenu().getEdit().getRedo().setEnabled(false);
        }

        if(!undo.empty())
        {
            getRightClickMenu().getUndo().setEnabled(true);
            gui.getTools().getUndo().setEnabled(true);
            gui.getMenu().getEdit().getUndo().setEnabled(true);
        }
        repaint();
    }

    public Draw getDrawArea() {
        return drawArea;
    }

    public void setDrawArea(Draw drawArea) {
        this.drawArea = drawArea;
    }

    public Gui getGui() {
        return gui;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    public Stack<Graph> getUndo() {
        return undo;
    }

    public void setUndo(Stack<Graph> undo) {
        this.undo = undo;
    }

    public Stack<Graph> getRedo() {
        return redo;
    }

    public void setRedo(Stack<Graph> redo) {
        this.redo = redo;
    }

    public JButton getExtend() {
        return extend;
    }

    public void setExtend(JButton extend) {
        this.extend = extend;
    }

    public RightClick getRightClickMenu() {
        return rightClickMenu;
    }

    public void setRightClickMenu(RightClick rightClickMenu) {
        this.rightClickMenu = rightClickMenu;
    }

    public JLabel getGraphNameLabel() {
        return graphNameLabel;
    }
}
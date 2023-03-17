package gui.popups.newElement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import gui.Gui;
import gui.draw.Draw;
import util.Edge;
import util.Graph;
import util.Vertex;
import gui.draw.PanelPaint;
import gui.draw.rightclickmenu.RightClick;
import gui.tools.Tools;

public class NewElement extends JDialog
{
    JDialog main;
    // ===== Variables used to create the settings window =====
    JTabbedPane tabbedPane;

    // === BLANK ===
        JPanel blankPanel;

        // Radio Button Group
        private ButtonGroup elementsButton;
            //The radio buttons and the items inside
            private JRadioButton graphButton,
                                automatonButton;
        
        //Checkbox
            private JCheckBox isOriented,
                                isWeighted;
        
        // name
            private JLabel nameLabel;
            private JTextField nameTextField;
        
        // Buttons
            private JButton validation,
                            cancel;
    
    // === MATRIX ===
        JPanel matrixPanel,
                informationMatrixPanel,
                inputMatrixPanel;
        
            // figure name
                private JLabel nameFigureLabel;
                private JTextField nameFigureJTextField;
            // number of vertex
                private JLabel numberVertexLabel;
                private JTextField numberVertexTextField;
                private JButton numberVertexButton;
            // type of figure
                // Radio Button Group
                private ButtonGroup elementsMatrixButton;
                //The radio buttons and the items inside
                private JRadioButton graphMatrixButton,
                                    automatonMatrixButton;
                private int type = 1, nbVertex;
            // matrix
                private ArrayList<JTextField> cellsList;
                private JButton createFigure;


    private static int nbrElementCreated = 0;

    public NewElement(Gui gui, Draw drawArea)
    {

        super(gui, "New element",true);
        this.setMinimumSize(new Dimension(250,250));
        this.setSize(new Dimension(250,250));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        main = this;
        tabbedPane = new JTabbedPane();
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabbedPane.getSelectedIndex() == 0) {
                    main.setSize(new Dimension(250,250));
                    main.setLocationRelativeTo(null);
                }else{
                    main.setSize(new Dimension(350,200));
                    main.setLocationRelativeTo(null);
                }
            }
        });
        this.add(tabbedPane);

        this.addBlankTab(gui, drawArea);

        this.addMatrixTab(gui, drawArea);
        

        this.setVisible(true);
    }




    private void addBlankTab(Gui gui, Draw drawArea){

        
        // Blank tabulation
        blankPanel = new JPanel();
        blankPanel.setSize(new Dimension(250,250));
        blankPanel.setLayout(new GridLayout(4,2, 8,8));

        graphButton = new JRadioButton("Graph ");
        graphButton.setSelected(true);
        graphButton.setHorizontalAlignment(JRadioButton.CENTER);
        graphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //When Graph is selected, we enable the checkbox to create a weigthed/oriented graph
                isOriented.setEnabled(true);
                isWeighted.setEnabled(true);
            }
        });
        blankPanel.add(graphButton);

        automatonButton = new JRadioButton("Automaton ");
        automatonButton.setSelected(true);
        automatonButton.setHorizontalAlignment(JRadioButton.CENTER);
        automatonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //When Graph is selected, we enable the checkbox to create a weigthed/oriented graph
                isOriented.setEnabled(false);
                isWeighted.setEnabled(false);
            }
        });
        blankPanel.add(automatonButton);

        elementsButton = new ButtonGroup();
        elementsButton.add(graphButton);
        elementsButton.add(automatonButton);
        
        //Orientation

        isOriented = new JCheckBox("Oriented");
        isOriented.setHorizontalAlignment(JCheckBox.CENTER);
        blankPanel.add(isOriented);

        isWeighted = new JCheckBox("Weighted ");
        isWeighted.setHorizontalAlignment(JCheckBox.CENTER);
        blankPanel.add(isWeighted);

        // name figure
        nameLabel = new JLabel("Name :",SwingConstants.CENTER);
        blankPanel.add(nameLabel);

        nameTextField = new JTextField("Graph "+(nbrElementCreated++));
        nameTextField.setHorizontalAlignment(JTextField.CENTER);
        nameTextField.setPreferredSize(new Dimension(116,20));
        blankPanel.add(nameTextField);

        // buttons

        Action cancelAction = new AbstractAction("Cancel") {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };
        cancelAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        cancel = new JButton(cancelAction);
        cancel.getActionMap().put("cancelNew", cancelAction);
        cancel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke)cancelAction.getValue(Action.ACCELERATOR_KEY), "cancelNew");
        blankPanel.add(cancel);


        Action validationAction = new AbstractAction("Create") {
            @Override
            public void actionPerformed(ActionEvent e) {
                createElement(gui, drawArea);
            }
        };
        validationAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        validation = new JButton(validationAction);
        validation.getActionMap().put("createNew", validationAction);
        validation.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke)validationAction.getValue(Action.ACCELERATOR_KEY), "createNew");
        blankPanel.add(validation);


        tabbedPane.addTab("Blank", blankPanel);
    }

    private void addMatrixTab(Gui gui, Draw drawArea){
        // Matrix Tabulation

        matrixPanel = new JPanel();
        matrixPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 0.3;
        gbc.gridy = 0;

        informationMatrixPanel = new JPanel();
        informationMatrixPanel.setLayout(new GridLayout(5,2,8,8));

        nameFigureLabel = new JLabel("Name : ");
        informationMatrixPanel.add(nameFigureLabel);
        nameFigureJTextField = new JTextField("Figure");
        nameFigureJTextField.setHorizontalAlignment(JTextField.CENTER);
        nameFigureJTextField.setSize(new Dimension(20,20));
        informationMatrixPanel.add(nameFigureJTextField);

        numberVertexLabel = new JLabel("Vertices [min 1, max 32] : ");
        informationMatrixPanel.add(numberVertexLabel);

        numberVertexTextField = new JTextField("1");
        numberVertexTextField.setHorizontalAlignment(JTextField.CENTER);
        numberVertexTextField.setSize(new Dimension(20,20));
        informationMatrixPanel.add(numberVertexTextField);

        
        

        graphMatrixButton = new JRadioButton("Graph ");
        graphMatrixButton.setSelected(true);
        graphMatrixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = 1;
            }
        });
        informationMatrixPanel.add(graphMatrixButton);

        automatonMatrixButton = new JRadioButton("Automaton ");
        automatonMatrixButton.setSelected(true);
        automatonMatrixButton.setHorizontalAlignment(JRadioButton.CENTER);
        automatonMatrixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = 2;
            }
        });
        informationMatrixPanel.add(automatonMatrixButton);

        elementsMatrixButton = new ButtonGroup();
        elementsMatrixButton.add(graphMatrixButton);
        elementsMatrixButton.add(automatonMatrixButton);

        numberVertexButton = new JButton("Update");
        numberVertexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    if (Integer.parseInt(numberVertexTextField.getText()) <= 32) {
                        inputMatrixPanel.removeAll();
                        nbVertex = Integer.parseInt(numberVertexTextField.getText());
                        inputMatrixPanel.setLayout(new GridLayout(nbVertex+1,nbVertex+1,8,8));
                        inputMatrixPanel.add(new JLabel());
                        for (int j = 0; j < nbVertex; j++) {
                            inputMatrixPanel.add(new JLabel(""+j,SwingConstants.CENTER));
                        }

                        int n = 0;
                        cellsList = new ArrayList<JTextField>();
                        for (int i = 0; i < (nbVertex*nbVertex); i++) {
                            if (i%nbVertex == 0) {
                                inputMatrixPanel.add(new JLabel(""+(n++),SwingConstants.CENTER));
                            }
                            JTextField temp = new JTextField();
                            temp.setHorizontalAlignment(JTextField.CENTER);
                            temp.setSize(new Dimension(15,15));
                            cellsList.add(temp);
                            inputMatrixPanel.add(temp);
                        }
                        main.setSize(new Dimension(350+(nbVertex+1)*35,200+(nbVertex+1)*35));
                        main.setLocationRelativeTo(null);
                        createFigure.setEnabled(true);
                    }

                } catch (Exception exce) {
                    System.out.println(exce.toString());
                }                
            }
        });
        informationMatrixPanel.add(numberVertexButton,gbc);



        Action validationMatrixAction = new AbstractAction("Create") {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (nameFigureJTextField.getText().length() != 0 && nbVertex <= 32) {
                    createMatrix(gui, drawArea);
                    dispose();
                }
            }
        };
        validationMatrixAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        createFigure = new JButton(validationMatrixAction);
        createFigure.getActionMap().put("createMatrixNew", validationMatrixAction);
        createFigure.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke)validationMatrixAction.getValue(Action.ACCELERATOR_KEY), "createMatrixNew");
        createFigure.setEnabled(false);
        informationMatrixPanel.add(createFigure);
        matrixPanel.add(informationMatrixPanel);


        gbc.weighty = 0.8;
        gbc.gridy = 1;

        inputMatrixPanel = new JPanel();

        matrixPanel.add(inputMatrixPanel,gbc);
        
        

        tabbedPane.addTab("Matrix", matrixPanel);
    }

    private void createMatrix(Gui gui, Draw drawArea){
        try {
            PanelPaint pp = new PanelPaint(gui, drawArea);
            
            if (type == 1) {
                
                Graph graph = new Graph(nameFigureJTextField.getText(), true, true, pp);
                int centerX = (int)drawArea.getSize().getWidth()/2,
                    centerY = (int)drawArea.getSize().getHeight()/2,
                    radiusFigure = (int)(2*centerY/3),x,y;
                
                System.out.println("center x = "+centerX+" center Y = "+centerY);
                for (int i = 0; i < nbVertex; i++) {
                    int cpt = graph.getCpt();
                    x = (int)(centerX + radiusFigure*Math.cos(Math.PI*2*i/nbVertex));
                    y = (int)(centerY + radiusFigure*Math.sin(Math.PI*2*i/nbVertex));
                    
                    graph.addVertex(new Vertex(cpt,x-Gui.getSettings().getVertexDiameter()/2, y-Gui.getSettings().getVertexDiameter()/2, Gui.getSettings().getVertexDiameter(), Gui.getSettings().getVertexStrokeWidth(), Gui.getSettings().getVertexInsideColor(), Gui.getSettings().getVertexOutsideColor(), ""+(cpt), Gui.getSettings().getVertexNameColor())); //We add the new vertex to the graph
                }
                ArrayList<Vertex> vertices = graph.getVertices();

                for (int k = 0; k < nbVertex; k++) {
                    for (int j = 0; j < nbVertex; j++) {

                        if (cellsList.get(j+k*nbVertex).getText().length() != 0) {

                            if (cellsList.get(j+k*nbVertex).getText().length() > 5)
                            {
                                cellsList.get(j+k*nbVertex).setText(cellsList.get(j+k*nbVertex).getText().substring(0,5));   
                            }

                            graph.addEdge(new Edge(vertices.get(k), vertices.get(j), Integer.parseInt(cellsList.get(j+k*nbVertex).getText()), Gui.getSettings().getEdgeStrokeWidth(), Gui.getSettings().getEdgeStrokeColor(), Gui.getSettings().getEdgeHighlightColor(), Gui.getSettings().getEdgeArrowTipColor(), Gui.getSettings().getEdgeWeightColor(), Gui.getSettings().getEdgeWeightBorderColor(), graph,Gui.getSettings().getArrowLength()));

                        }
                    }
                }
                gui.getTabulations().put(pp, graph);


            }else{

            }

            drawArea.addTab(nameFigureJTextField.getText(), null,pp, nameFigureJTextField.getText());
            drawArea.setSelectedComponent(pp);
            enableIcon(gui, drawArea);
            pp.repaint();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    //Once everything is selected, we read user inputs to create the graph/automaton
    private void createElement(Gui gui, Draw drawArea)
    {
        //We first read if its an automaton or a graph
        if(graphButton.isSelected())
        {
            //Creating the tabulation
            PanelPaint panelPaint = new PanelPaint(gui, drawArea);
            drawArea.addTab(nameTextField.getText(), null, panelPaint, nameTextField.getText()); //We create the new tab and retrieve it
            //Creating a new Graph
            //Each graph is associated with a tabulation
            Graph graph = new Graph(nameTextField.getText(), isOriented.isSelected(), isWeighted.isSelected(), panelPaint);
            //We then add these two components to the list of opened tabulations
            gui.getTabulations().put(panelPaint,graph);

            //We go to the new tabulation and draw the panel
            drawArea.setSelectedComponent(panelPaint);
            panelPaint.repaint();
        }
        else if(automatonButton.isSelected())
        {
            //Creating new automaton
        }

        //Once the automaton/graph is created, we close the JDialog
        enableIcon(gui, drawArea);
        this.dispose();
    }

    //Enable disabled icon when we create the first figure
    private void enableIcon(Gui gui, Draw drawArea)
    {
        //Menu
        gui.getMenu().getEdit().getClear().setEnabled(true);
        gui.getMenu().getAlgorithms().getLaunch().setEnabled(true);

        //Toolbar
        Tools tools = gui.getTools();
        tools.getCursorButton().setEnabled(true);
        tools.getNewVertex().setEnabled(true);
        tools.getNewEdge().setEnabled(true);
        tools.getLaunchAlgo().setEnabled(true);
        tools.getClear().setEnabled(true);

        //If More than 1 tab, can close a tabulation
        if(drawArea.getTabCount() > 1)
        {
            gui.getTools().getClose().setEnabled(true);
            for(PanelPaint pp : gui.getTabulations().keySet())
            {
                pp.getRightClickMenu().getClose().setEnabled(true);
            }
        }
    }
}
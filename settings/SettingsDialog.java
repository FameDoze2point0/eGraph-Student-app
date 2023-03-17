package settings;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import gui.Gui;
import gui.draw.Draw;
import gui.draw.PanelPaint;
import util.Edge;
import util.Graph;
import util.Vertex;

public class SettingsDialog extends JDialog implements ChangeListener
{
     // ===== VARIABLES USED TO CREATE THE SETTINGS WINDOW =====
        JTabbedPane tabbedPane;
        GridBagConstraints gbc;
        // === vertices ===
        JPanel vertex_panel_settings;
            // == LABELS ==    
            private JLabel vertex_label_insideColor,
                           vertex_label_outsideColor,
                           vertex_label_nameColor,
                           vertex_label_diameter,
                           vertex_label_strokeWidth;

            // == DISPLAY CURRENT COLOR ==
            private JPanel vertex_panel_insideColor,
                           vertex_panel_outsideColor,
                           vertex_panel_nameColor;

            // == BUTTONS ==
            private JButton vertex_button_insideColor,
                            vertex_button_outsideColor,
                            vertex_button_nameColor;
                            
            private JSlider vertex_slider_diameter,
                            vertex_slider_strokeWidth;

            // == EDIT CURRENT VALUES ==
            private JTextField vertex_textField_diameter,
                               vertex_textField_strokeWidth;

        // === edges ===
        JPanel edgePanel;
            // == LABELS ==
            private JLabel edge_label_strokeColor,
                           edge_label_highlightColor,
                           edge_label_arrowTipColor,
                           edge_label_weightColor,
                           edge_label_weightBorderColor,
                           edge_label_strokeWidth,
                           edge_label_arrowLenght;

            // == DISPLAY CURRENT COLOR ==
            private JPanel edge_panel_strokeColor,
                           edge_panel_highlightColor,
                           edge_panel_arrowTipColor,
                           edge_panel_weightColor,
                           edge_panel_weightBorderColor;

            // == BUTTONS ==
            private JButton edge_button_strokeColor,
                            edge_button_highlightColor,
                            edge_button_arrowTipColor,
                            edge_button_weightColor,
                            edge_button_weightBorderColor;

            private JSlider edge_slider_strokeWidth,
                            edge_slider_arrowLenght;

            // == EDIT CURRENT VALUES ==
            private JTextField edge_textField_strokeWidth,
                               edge_textField_arrowLenght;
        // === shortcuts ===
        JPanel shortcutPanel;
            // == LABELS ==
            private JLabel new_label_shortcut,
                            open_label_shortcut,
                            save_label_shortcut,
                            closeTab_label_shortcut,
                            exit_label_shortcut,
                            algorithms_label_shortcut,
                            documentation_label_shortcut,
                            licence_label_shortcut,
                            contact_label_shortcut;

        // == DRAW AREA ===
        private static PanelPaint panel_drawArea;
        private static Graph example_graph;

        //RESET
        private JButton reset;


    // ===== END VARIABLES USED TO CREATE THE SETTINGS WINDOW =====

    public SettingsDialog(Gui gui, Draw drawArea, Settings settings)
    {
        //We create the settings page
        super(gui, "Settings", true);

        //this.setLayout(new GridLayout(0, 3,0,10));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setMinimumSize(new Dimension(500,450));
        this.setMaximumSize(new Dimension(500,450));
        this.setLocationRelativeTo(null); //Centering the frame
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setLayout(new GridLayout(0,1));

        this.addWindowListener(new WindowListener()
        {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e)
            {
                //When we close the Settings window, we want to save (serialize) the settings
                try
                {
                    FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+"/settings/settings");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);

                    //Writing the object
                    oos.writeObject(Gui.getSettings());

                    oos.close();
                    fos.close();
                }

                catch(Exception ex)
                {
                    System.out.println("ERROR : "+ex.getMessage());
                }

                //Cursor mode
                gui.setState(0);

                //We then hide the window
                Gui.getSettings().getWindow().setVisible(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        //The JDialog is composed of a JTabbedPane
        tabbedPane = new JTabbedPane();
        add(tabbedPane);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        // ===== VERTICES SETTINGS =====
        //vertex_panel_settings
        vertex_panel_settings = new JPanel(new GridBagLayout());
        //vertex_panel_settings.setLayout(new GridLayout(0, 3,0,10));
        //Vertexs settings area
        //INSIDE COLOR
        vertex_label_insideColor = new JLabel("Inside color : ",SwingConstants.CENTER);
        vertex_panel_settings.add(vertex_label_insideColor, gbc);

        vertex_panel_insideColor = new JPanel();
        gbc.gridx = 1;
        vertex_panel_insideColor.setMaximumSize(new Dimension(16,16));
        vertex_panel_insideColor.setBackground(Gui.getSettings().getVertexInsideColor());
        vertex_panel_insideColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        vertex_panel_settings.add(vertex_panel_insideColor, gbc);

        vertex_button_insideColor = new JButton("Change...");
        gbc.gridx = 2;
        vertex_button_insideColor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color tempColor;
                if((tempColor = JColorChooser.showDialog(gui, "Select a new color", Gui.getSettings().vertexInsideColor)) != null)
                {
                    Gui.getSettings().setVertexInsideColor(tempColor);
                    vertex_panel_insideColor.setBackground(Gui.getSettings().getVertexInsideColor());
                    //Changing the graph elements
                    example_graph.getVertices().get(0).setInsideColor(tempColor);
                    example_graph.getVertices().get(1).setInsideColor(tempColor);
                    panel_drawArea.repaint();
                }
            }   
        });
        vertex_panel_settings.add(vertex_button_insideColor,gbc);

        //OUTSIDE COLOR
        gbc.gridx = 0;
        gbc.gridy = 1;
        vertex_label_outsideColor = new JLabel("Outside color : ",SwingConstants.CENTER);
        vertex_panel_settings.add(vertex_label_outsideColor,gbc);

        vertex_panel_outsideColor = new JPanel();
        gbc.gridx = 1;
        vertex_panel_outsideColor.setMaximumSize(new Dimension(16,16));
        vertex_panel_outsideColor.setBackground(Gui.getSettings().getVertexOutsideColor());
        vertex_panel_outsideColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        vertex_panel_settings.add(vertex_panel_outsideColor,gbc);

        vertex_button_outsideColor = new JButton("Change...");
        gbc.gridx = 2;
        vertex_button_outsideColor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color tempColor;
                if((tempColor = JColorChooser.showDialog(gui, "Select a new color", Gui.getSettings().getVertexOutsideColor())) != null)
                {
                    Gui.getSettings().setVertexOutsideColor(tempColor);
                    vertex_panel_outsideColor.setBackground(Gui.getSettings().getVertexOutsideColor());
                    example_graph.getVertices().get(0).setBorderColor(tempColor);
                    example_graph.getVertices().get(1).setBorderColor(tempColor);
                    panel_drawArea.repaint();
                }
            }   
        });
        vertex_panel_settings.add(vertex_button_outsideColor,gbc);

        //NAME COLOR
        vertex_label_nameColor = new JLabel("Name color : ",SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        vertex_panel_settings.add(vertex_label_nameColor,gbc);

        vertex_panel_nameColor = new JPanel();
        gbc.gridx = 1;
        vertex_panel_nameColor.setMaximumSize(new Dimension(16,16));
        vertex_panel_nameColor.setBackground(Gui.getSettings().getVertexNameColor());
        vertex_panel_nameColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        vertex_panel_settings.add(vertex_panel_nameColor,gbc);

        vertex_button_nameColor = new JButton("Change...");
        gbc.gridx = 2;
        vertex_button_nameColor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color tempColor;
                if((tempColor = JColorChooser.showDialog(gui, "Select a new color", Gui.getSettings().getVertexNameColor())) != null)
                {
                    Gui.getSettings().setVertexNameColor(tempColor);
                    vertex_panel_nameColor.setBackground(Gui.getSettings().getVertexNameColor());
                    example_graph.getVertices().get(0).setNameColor(tempColor);
                    example_graph.getVertices().get(1).setNameColor(tempColor);
                    panel_drawArea.repaint();
                }
            }   
        });
        vertex_panel_settings.add(vertex_button_nameColor,gbc);

        //Vertex Diameter
        gbc.gridx = 0;
        gbc.gridy = 3;
        vertex_label_diameter = new JLabel("Diameter : ["+Gui.getSettings().getVertexDiameter()+"]",SwingConstants.CENTER);
        vertex_panel_settings.add(vertex_label_diameter,gbc);

        vertex_slider_diameter = new JSlider(0,100,settings.getVertexDiameter());
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        vertex_slider_diameter.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e) {
                try
                {
                    Gui.getSettings().setVertexDiameter(vertex_slider_diameter.getValue());
                    vertex_label_diameter.setText("Diameter : ["+Gui.getSettings().getVertexDiameter()+"]");
                    example_graph.getVertices().get(0).setDiameter(vertex_slider_diameter.getValue());
                    example_graph.getVertices().get(1).setDiameter(vertex_slider_diameter.getValue());
                    panel_drawArea.repaint();
                }

                catch(Exception error)
                {
                    System.out.println(error.getMessage());
                }
            }   
        });
        vertex_panel_settings.add(vertex_slider_diameter,gbc);
        gbc.gridwidth = 1;
        
        //Vertex StrokeWidth
        gbc.gridx = 0;
        gbc.gridy = 4;
        vertex_label_strokeWidth = new JLabel("Stroke : ["+Gui.getSettings().getVertexStrokeWidth()+"]",SwingConstants.CENTER);
        vertex_panel_settings.add(vertex_label_strokeWidth,gbc);

        vertex_slider_strokeWidth = new JSlider(0,100,settings.getEdgeStrokeWidth());
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        vertex_slider_strokeWidth.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e) {
                try
                {
                    Gui.getSettings().setVertexStrokeWidth(vertex_slider_strokeWidth.getValue());
                    vertex_label_strokeWidth.setText("Stroke : ["+Gui.getSettings().getVertexStrokeWidth()+"]");
                    example_graph.getVertices().get(0).setStrokeWidth(vertex_slider_strokeWidth.getValue());
                    example_graph.getVertices().get(1).setStrokeWidth(vertex_slider_strokeWidth.getValue());
                    panel_drawArea.repaint();
                }

                catch(Exception error)
                {
                    System.out.println(error.getMessage());
                }
            }   
        });
        vertex_panel_settings.add(vertex_slider_strokeWidth,gbc);
        gbc.gridwidth = 1;
        
        //We add vertex_panel_settings as a tabulation 
        tabbedPane.add("Vertex", vertex_panel_settings);
        // ===== END VERTEXS SETTINGS =====


        // ===== EDGES SETTINGS =====
        //EdgePanel
        edgePanel = new JPanel(new GridBagLayout());
        //Edges settings area
        //INSIDE COLOR
        edge_label_strokeColor = new JLabel("Stroke color : ",SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        edgePanel.add(edge_label_strokeColor,gbc);

        edge_panel_strokeColor = new JPanel();
        gbc.gridx = 1;
        edge_panel_strokeColor.setMaximumSize(new Dimension(16,16));
        edge_panel_strokeColor.setBackground(Gui.getSettings().getEdgeStrokeColor());
        edge_panel_strokeColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        edgePanel.add(edge_panel_strokeColor,gbc);

        edge_button_strokeColor = new JButton("Change...");
        gbc.gridx = 2;
        edge_button_strokeColor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color tempColor;
                if((tempColor = JColorChooser.showDialog(gui, "Select a new color", Gui.getSettings().getEdgeStrokeColor())) != null)
                {
                    Gui.getSettings().setEdgeStrokeColor(tempColor);
                    edge_panel_strokeColor.setBackground(Gui.getSettings().getEdgeStrokeColor());
                    //Changing the graph elements
                    example_graph.getEdges().get(0).setStrokeColor(tempColor);
                    panel_drawArea.repaint();
                }
            }   
        });
        edgePanel.add(edge_button_strokeColor,gbc);

        //HIGHTLIGHT COLOR
        edge_label_highlightColor = new JLabel("Highlight color : ",SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        edgePanel.add(edge_label_highlightColor,gbc);

        edge_panel_highlightColor = new JPanel();
        gbc.gridx = 1;
        edge_panel_highlightColor.setMaximumSize(new Dimension(16,16));
        edge_panel_highlightColor.setBackground(Gui.getSettings().getEdgeHighlightColor());
        edge_panel_highlightColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        edgePanel.add(edge_panel_highlightColor,gbc);

        edge_button_highlightColor = new JButton("Change...");
        gbc.gridx = 2;
        edge_button_highlightColor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color tempColor;
                if((tempColor = JColorChooser.showDialog(gui, "Select a new color", Gui.getSettings().getEdgeHighlightColor())) != null)
                {
                    Gui.getSettings().setEdgeHighlightColor(tempColor);
                    edge_panel_highlightColor.setBackground(Gui.getSettings().getEdgeHighlightColor());
                    //Changing the graph elements
                    example_graph.getEdges().get(0).setHighlightColor(tempColor);
                    panel_drawArea.repaint();

                }
            }   
        });
        edgePanel.add(edge_button_highlightColor,gbc);

        //ARROW TIP COLOR
        edge_label_arrowTipColor = new JLabel("Arrow Tip color : ",SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        edgePanel.add(edge_label_arrowTipColor,gbc);

        edge_panel_arrowTipColor = new JPanel();
        gbc.gridx = 1;
        edge_panel_arrowTipColor.setMaximumSize(new Dimension(16,16));
        edge_panel_arrowTipColor.setBackground(Gui.getSettings().getEdgeArrowTipColor());
        edge_panel_arrowTipColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        edgePanel.add(edge_panel_arrowTipColor,gbc);

        edge_button_arrowTipColor = new JButton("Change...");
        gbc.gridx = 2;
        edge_button_arrowTipColor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color tempColor;
                if((tempColor = JColorChooser.showDialog(gui, "Select a new color", Gui.getSettings().getEdgeArrowTipColor())) != null)
                {
                    Gui.getSettings().setEdgeArrowTipColor(tempColor);
                    edge_panel_arrowTipColor.setBackground(Gui.getSettings().getEdgeArrowTipColor());
                    //Changing the graph elements
                    example_graph.getEdges().get(0).setEdgeArrowTipColor(tempColor);
                    panel_drawArea.repaint();
                }
            }   
        });
        edgePanel.add(edge_button_arrowTipColor,gbc);

        //WEIGHT COLOR
        edge_label_weightColor = new JLabel("Weight color : ",SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 3;
        edgePanel.add(edge_label_weightColor,gbc);

        edge_panel_weightColor = new JPanel();
        gbc.gridx = 1;
        edge_panel_weightColor.setMaximumSize(new Dimension(16,16));
        edge_panel_weightColor.setBackground(Gui.getSettings().getEdgeWeightColor());
        edge_panel_weightColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        edgePanel.add(edge_panel_weightColor,gbc);

        edge_button_weightColor = new JButton("Change...");
        gbc.gridx = 2;
        edge_button_weightColor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color tempColor;
                if((tempColor = JColorChooser.showDialog(gui, "Select a new color", Gui.getSettings().getEdgeWeightColor())) != null)
                {
                    Gui.getSettings().setEdgeWeightColor(tempColor);
                    edge_panel_weightColor.setBackground(Gui.getSettings().getEdgeWeightColor());
                    //Changing the graph elements
                    example_graph.getEdges().get(0).setWeightColor(tempColor);
                    panel_drawArea.repaint();

                }
            }   
        });
        edgePanel.add(edge_button_weightColor,gbc);

        //WEIGHT BORDER COLOR
        edge_label_weightBorderColor = new JLabel("Weight border color : ",SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 4;
        edgePanel.add(edge_label_weightBorderColor,gbc);

        edge_panel_weightBorderColor = new JPanel();
        gbc.gridx = 1;
        edge_panel_weightBorderColor.setMaximumSize(new Dimension(16,16));
        edge_panel_weightBorderColor.setBackground(Gui.getSettings().getEdgeWeightBorderColor());
        edge_panel_weightBorderColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        edgePanel.add(edge_panel_weightBorderColor,gbc);

        edge_button_weightBorderColor = new JButton("Change...");
        gbc.gridx = 2;
        edge_button_weightBorderColor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color tempColor;
                if((tempColor = JColorChooser.showDialog(gui, "Select a new color", Gui.getSettings().getEdgeWeightBorderColor())) != null)
                {
                    Gui.getSettings().setEdgeWeightBorderColor(tempColor);
                    edge_panel_weightBorderColor.setBackground(Gui.getSettings().getEdgeWeightBorderColor());
                    //Changing the graph elements
                    example_graph.getEdges().get(0).setWeightBorderColor(tempColor);
                    panel_drawArea.repaint();
                }
            }   
        });
        edgePanel.add(edge_button_weightBorderColor,gbc);

        //Stroke width
        edge_label_strokeWidth = new JLabel("Stroke width : ["+Gui.getSettings().getEdgeStrokeWidth()+"]",SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 5;
        edgePanel.add(edge_label_strokeWidth,gbc);

        edge_slider_strokeWidth = new JSlider(0,100,settings.getEdgeStrokeWidth());
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        edge_slider_strokeWidth.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e) {
                try
                {
                    Gui.getSettings().setEdgeStrokeWidth(edge_slider_strokeWidth.getValue());
                    edge_label_strokeWidth.setText("Stroke width : ["+Gui.getSettings().getEdgeStrokeWidth()+"]");
                    //Changing the graph elements
                    example_graph.getEdges().get(0).setStrokeWidth(edge_slider_strokeWidth.getValue());
                    panel_drawArea.repaint();
                }

                catch(Exception error)
                {
                    System.out.println(error.getMessage());
                }
            }   
        });
        edgePanel.add(edge_slider_strokeWidth,gbc);
        gbc.gridwidth = 1;

        //Arrow Lenght
        edge_label_arrowLenght = new JLabel("Arrow lenght : ["+Gui.getSettings().getArrowLength()+"]",SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 6;
        edgePanel.add(edge_label_arrowLenght,gbc);

        edge_slider_arrowLenght = new JSlider(0,100,settings.getArrowLength());
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        edge_slider_arrowLenght.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e) {
                try
                {
                    Gui.getSettings().setArrowLength(edge_slider_arrowLenght.getValue());
                    edge_label_arrowLenght.setText("Arrow lenght : ["+Gui.getSettings().getArrowLength()+"]");
                    //Changing the graph elements
                    example_graph.getEdges().get(0).setArrowTipWidth(edge_slider_arrowLenght.getValue());
                    panel_drawArea.repaint();
                }

                catch(Exception error)
                {
                    System.out.println(error.getMessage());
                }
            }   
        });
        edgePanel.add(edge_slider_arrowLenght,gbc);
                
        //We add edgePanel as a tabulation 
        tabbedPane.addTab("Edges", edgePanel);
        // ===== END EDGES SETTINGS =====


        // ===== SHORTCUTS SETTINGS =====
        
        //ShortcutPanel
        shortcutPanel = new JPanel();
        shortcutPanel.setLayout(new GridLayout(0, 2,0,2));
        //shortcuts settings area
        //New shortcut
        new_label_shortcut = new JLabel("New graph/automaton : ");
        shortcutPanel.add(new_label_shortcut);
        shortcutPanel.add(new JLabel("CTRL + N"));

        //Open shortcut
        open_label_shortcut = new JLabel("Open graph/automaton : ");
        shortcutPanel.add(open_label_shortcut);
        shortcutPanel.add(new JLabel("CTRL + O"));

        //Save shortcut
        save_label_shortcut = new JLabel("Save graph/automaton : ");
        shortcutPanel.add(save_label_shortcut);
        shortcutPanel.add(new JLabel("CTRL + S"));

        //Close tabulation shortcut
        closeTab_label_shortcut = new JLabel("Close a tabulation : ");
        shortcutPanel.add(closeTab_label_shortcut);
        shortcutPanel.add(new JLabel("CTRL + W"));

        //Exit shortcut
        documentation_label_shortcut = new JLabel("Open the documentation page : ");
        shortcutPanel.add(documentation_label_shortcut);
        shortcutPanel.add(new JLabel("CTRL + D"));

        //Exit shortcut
        licence_label_shortcut = new JLabel("Open the licence page : ");
        shortcutPanel.add(licence_label_shortcut);
        shortcutPanel.add(new JLabel("CTRL + L"));

        //Exit shortcut
        contact_label_shortcut = new JLabel("Open the contact page : ");
        shortcutPanel.add(contact_label_shortcut);
        shortcutPanel.add(new JLabel("CTRL + C"));

        //Exit shortcut
        exit_label_shortcut = new JLabel("Exit the application : ");
        shortcutPanel.add(exit_label_shortcut);
        shortcutPanel.add(new JLabel("ESCAPE"));

        //We add vertex_panel_settings as a tabulation 
        tabbedPane.addTab("Shortcuts", shortcutPanel);
        // ===== END SHORTCUTS SETTINGS =====

        this.add(tabbedPane);

        // ===== EXAMPLE DRAW =====
        //We create the PanelPaint
        panel_drawArea = new PanelPaint(gui, drawArea);
        panel_drawArea.getExtend().setVisible(false);

        //We create an example graph
        example_graph = new Graph("example", true, true, panel_drawArea);
        example_graph.addVertex(new Vertex(0, 50, 30, settings.getVertexDiameter(), settings.getVertexStrokeWidth(), settings.getVertexInsideColor(), settings.getVertexOutsideColor(), "A", settings.getVertexNameColor()));

        example_graph.addVertex(new Vertex(0, 400, 110, settings.getVertexDiameter(), settings.getVertexStrokeWidth(), settings.getVertexInsideColor(), settings.getVertexOutsideColor(), "B", settings.getVertexNameColor()));

        example_graph.addEdge(example_graph.getVertices().get(0), example_graph.getVertices().get(1), 27);

        //Reset button
        reset = new JButton("Reset to default settings");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                for(Vertex vertex : example_graph.getVertices())
                {
                    vertex.setBorderColor(Color.BLACK);
                    vertex.setInsideColor(Color.WHITE);
                    vertex.setNameColor(Color.BLACK);
                    vertex.setStrokeWidth(5);
                    vertex.setDiameter(50);
                }

                for(Edge edge : example_graph.getEdges())
                {
                    edge.setStrokeColor(Color.BLACK);
                    edge.setHighlightColor(Color.BLACK);
                    edge.setWeightColor(Color.WHITE);
                    edge.setWeightBorderColor(Color.BLACK);
                    edge.setEdgeArrowTipColor(Color.BLACK);
                    edge.setStrokeWidth(5);
                    edge.setArrowTipWidth(15);
                }

                //We reset the panels/Jlabels
                //Vertex
                vertex_panel_insideColor.setBackground(Color.WHITE);
                vertex_panel_outsideColor.setBackground(Color.BLACK);
                vertex_panel_nameColor.setBackground(Color.BLACK);
                vertex_label_diameter.setText("Diameter : [50]");
                vertex_label_strokeWidth.setText("Diameter : [5]");
                vertex_slider_diameter.setValue(50);
                vertex_slider_strokeWidth.setValue(5);
                
                //Edges
                edge_panel_strokeColor.setBackground(Color.BLACK);
                edge_panel_highlightColor.setBackground(Color.BLACK);
                edge_panel_weightColor.setBackground(Color.WHITE);
                edge_panel_weightBorderColor.setBackground(Color.BLACK);
                edge_panel_arrowTipColor.setBackground(Color.BLACK);
                edge_label_arrowLenght.setText("Arrow lenght : [15]");
                edge_label_strokeWidth.setText("Stroke width : [5]");
                edge_slider_arrowLenght.setValue(15);
                edge_slider_strokeWidth.setValue(5);


                //We repaint
                example_graph.getPanelPaint().repaint();
            }
        });

        //Button placement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        panel_drawArea.add(reset,gbc);

        //We draw the graph
        panel_drawArea.repaint();
        this.add(panel_drawArea);
        // ===== END DRAW =====
    }

    public JLabel getVertex_label_insideColor() {
        return vertex_label_insideColor;
    }

    public void setVertex_label_insideColor(JLabel vertex_label_insideColor) {
        this.vertex_label_insideColor = vertex_label_insideColor;
    }

    public JLabel getVertex_label_outsideColor() {
        return vertex_label_outsideColor;
    }

    public void setVertex_label_outsideColor(JLabel vertex_label_outsideColor) {
        this.vertex_label_outsideColor = vertex_label_outsideColor;
    }

    public JLabel getVertex_label_nameColor() {
        return vertex_label_nameColor;
    }

    public void setVertex_label_nameColor(JLabel vertex_label_nameColor) {
        this.vertex_label_nameColor = vertex_label_nameColor;
    }

    public JLabel getVertex_label_diameter() {
        return vertex_label_diameter;
    }

    public void setVertex_label_diameter(JLabel vertex_label_diameter) {
        this.vertex_label_diameter = vertex_label_diameter;
    }

    public JPanel getVertex_panel_insideColor() {
        return vertex_panel_insideColor;
    }

    public void setVertex_panel_insideColor(JPanel vertex_panel_insideColor) {
        this.vertex_panel_insideColor = vertex_panel_insideColor;
    }

    public JPanel getVertex_panel_outsideColor() {
        return vertex_panel_outsideColor;
    }

    public void setVertex_panel_outsideColor(JPanel vertex_panel_outsideColor) {
        this.vertex_panel_outsideColor = vertex_panel_outsideColor;
    }

    public JPanel getVertex_panel_nameColor() {
        return vertex_panel_nameColor;
    }

    public void setVertex_panel_nameColor(JPanel vertex_panel_nameColor) {
        this.vertex_panel_nameColor = vertex_panel_nameColor;
    }

    public JButton getVertex_button_insideColor() {
        return vertex_button_insideColor;
    }

    public void setVertex_button_insideColor(JButton vertex_button_insideColor) {
        this.vertex_button_insideColor = vertex_button_insideColor;
    }

    public JButton getVertex_button_outsideColor() {
        return vertex_button_outsideColor;
    }

    public void setVertex_button_outsideColor(JButton vertex_button_outsideColor) {
        this.vertex_button_outsideColor = vertex_button_outsideColor;
    }

    public JButton getVertex_button_nameColor() {
        return vertex_button_nameColor;
    }

    public void setVertex_button_nameColor(JButton vertex_button_nameColor) {
        this.vertex_button_nameColor = vertex_button_nameColor;
    }

    public JTextField getVertex_textField_diameter() {
        return vertex_textField_diameter;
    }

    public void setVertex_textField_diameter(JTextField vertex_textField_diameter) {
        this.vertex_textField_diameter = vertex_textField_diameter;
    }

    public JTextField getVertex_textField_strokeWidth() {
        return vertex_textField_strokeWidth;
    }

    public void setVertex_textField_strokeWidth(JTextField vertex_textField_strokeWidth) {
        this.vertex_textField_strokeWidth = vertex_textField_strokeWidth;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public JPanel getvertex_panel_settings() {
        return vertex_panel_settings;
    }

    public void setvertex_panel_settings(JPanel vertex_panel_settings) {
        this.vertex_panel_settings = vertex_panel_settings;
    }

    public JPanel getEdgePanel() {
        return edgePanel;
    }

    public void setEdgePanel(JPanel edgePanel) {
        this.edgePanel = edgePanel;
    }

    public JLabel getEdge_label_strokeColor() {
        return edge_label_strokeColor;
    }

    public void setEdge_label_strokeColor(JLabel edge_label_strokeColor) {
        this.edge_label_strokeColor = edge_label_strokeColor;
    }

    public JLabel getEdge_label_highlightColor() {
        return edge_label_highlightColor;
    }

    public void setEdge_label_highlightColor(JLabel edge_label_highlightColor) {
        this.edge_label_highlightColor = edge_label_highlightColor;
    }

    public JLabel getEdge_label_arrowTipColor() {
        return edge_label_arrowTipColor;
    }

    public void setEdge_label_arrowTipColor(JLabel edge_label_arrowTipColor) {
        this.edge_label_arrowTipColor = edge_label_arrowTipColor;
    }

    public JLabel getEdge_label_weightColor() {
        return edge_label_weightColor;
    }

    public void setEdge_label_weightColor(JLabel edge_label_weightColor) {
        this.edge_label_weightColor = edge_label_weightColor;
    }

    public JLabel getEdge_label_weightBorderColor() {
        return edge_label_weightBorderColor;
    }

    public void setEdge_label_weightBorderColor(JLabel edge_label_weightBorderColor) {
        this.edge_label_weightBorderColor = edge_label_weightBorderColor;
    }

    public JLabel getEdge_label_strokeWidth() {
        return edge_label_strokeWidth;
    }

    public void setEdge_label_strokeWidth(JLabel edge_label_strokeWidth) {
        this.edge_label_strokeWidth = edge_label_strokeWidth;
    }

    public JLabel getEdge_label_arrowLenght() {
        return edge_label_arrowLenght;
    }

    public void setEdge_label_arrowLenght(JLabel edge_label_arrowLenght) {
        this.edge_label_arrowLenght = edge_label_arrowLenght;
    }

    public JPanel getEdge_panel_strokeColor() {
        return edge_panel_strokeColor;
    }

    public void setEdge_panel_strokeColor(JPanel edge_panel_strokeColor) {
        this.edge_panel_strokeColor = edge_panel_strokeColor;
    }

    public JPanel getEdge_panel_highlightColor() {
        return edge_panel_highlightColor;
    }

    public void setEdge_panel_highlightColor(JPanel edge_panel_highlightColor) {
        this.edge_panel_highlightColor = edge_panel_highlightColor;
    }

    public JPanel getEdge_panel_arrowTipColor() {
        return edge_panel_arrowTipColor;
    }

    public void setEdge_panel_arrowTipColor(JPanel edge_panel_arrowTipColor) {
        this.edge_panel_arrowTipColor = edge_panel_arrowTipColor;
    }

    public JPanel getEdge_panel_weightColor() {
        return edge_panel_weightColor;
    }

    public void setEdge_panel_weightColor(JPanel edge_panel_weightColor) {
        this.edge_panel_weightColor = edge_panel_weightColor;
    }

    public JPanel getEdge_panel_weightBorderColor() {
        return edge_panel_weightBorderColor;
    }

    public void setEdge_panel_weightBorderColor(JPanel edge_panel_weightBorderColor) {
        this.edge_panel_weightBorderColor = edge_panel_weightBorderColor;
    }

    public JButton getEdge_button_strokeColor() {
        return edge_button_strokeColor;
    }

    public void setEdge_button_strokeColor(JButton edge_button_strokeColor) {
        this.edge_button_strokeColor = edge_button_strokeColor;
    }

    public JButton getEdge_button_highlightColor() {
        return edge_button_highlightColor;
    }

    public void setEdge_button_highlightColor(JButton edge_button_highlightColor) {
        this.edge_button_highlightColor = edge_button_highlightColor;
    }

    public JButton getEdge_button_arrowTipColor() {
        return edge_button_arrowTipColor;
    }

    public void setEdge_button_arrowTipColor(JButton edge_button_arrowTipColor) {
        this.edge_button_arrowTipColor = edge_button_arrowTipColor;
    }

    public JButton getEdge_button_weightColor() {
        return edge_button_weightColor;
    }

    public void setEdge_button_weightColor(JButton edge_button_weightColor) {
        this.edge_button_weightColor = edge_button_weightColor;
    }

    public JButton getEdge_button_weightBorderColor() {
        return edge_button_weightBorderColor;
    }

    public void setEdge_button_weightBorderColor(JButton edge_button_weightBorderColor) {
        this.edge_button_weightBorderColor = edge_button_weightBorderColor;
    }

    public JTextField getEdge_textField_strokeWidth() {
        return edge_textField_strokeWidth;
    }

    public void setEdge_textField_strokeWidth(JTextField edge_textField_strokeWidth) {
        this.edge_textField_strokeWidth = edge_textField_strokeWidth;
    }

    public JTextField getEdge_textField_arrowLenght() {
        return edge_textField_arrowLenght;
    }

    public void setEdge_textField_arrowLenght(JTextField edge_textField_arrowLenght) {
        this.edge_textField_arrowLenght = edge_textField_arrowLenght;
    }

    public JPanel getShortcutPanel() {
        return shortcutPanel;
    }

    public void setShortcutPanel(JPanel shortcutPanel) {
        this.shortcutPanel = shortcutPanel;
    }

    public JLabel getNew_label_shortcut() {
        return new_label_shortcut;
    }

    public void setNew_label_shortcut(JLabel new_label_shortcut) {
        this.new_label_shortcut = new_label_shortcut;
    }

    public JLabel getOpen_label_shortcut() {
        return open_label_shortcut;
    }

    public void setOpen_label_shortcut(JLabel open_label_shortcut) {
        this.open_label_shortcut = open_label_shortcut;
    }

    public JLabel getSave_label_shortcut() {
        return save_label_shortcut;
    }

    public void setSave_label_shortcut(JLabel save_label_shortcut) {
        this.save_label_shortcut = save_label_shortcut;
    }

    public JLabel getExit_label_shortcut() {
        return exit_label_shortcut;
    }

    public void setExit_label_shortcut(JLabel exit_label_shortcut) {
        this.exit_label_shortcut = exit_label_shortcut;
    }

    public JLabel getAlgorithms_label_shortcut() {
        return algorithms_label_shortcut;
    }

    public void setAlgorithms_label_shortcut(JLabel algorithms_label_shortcut) {
        this.algorithms_label_shortcut = algorithms_label_shortcut;
    }

    public JLabel getDocumentation_label_shortcut() {
        return documentation_label_shortcut;
    }

    public void setDocumentation_label_shortcut(JLabel documentation_label_shortcut) {
        this.documentation_label_shortcut = documentation_label_shortcut;
    }

    public JLabel getLicence_label_shortcut() {
        return licence_label_shortcut;
    }

    public void setLicence_label_shortcut(JLabel licence_label_shortcut) {
        this.licence_label_shortcut = licence_label_shortcut;
    }

    public JLabel getContact_label_shortcut() {
        return contact_label_shortcut;
    }

    public void setContact_label_shortcut(JLabel contact_label_shortcut) {
        this.contact_label_shortcut = contact_label_shortcut;
    }

	public JPanel getVertex_panel_settings() {
		return vertex_panel_settings;
	}

	public void setVertex_panel_settings(JPanel vertex_panel_settings) {
		this.vertex_panel_settings = vertex_panel_settings;
	}

	public static Graph getExample_graph() {
		return example_graph;
	}

	public static void setExample_graph(Graph example_graph) {
		SettingsDialog.example_graph = example_graph;
	}

	public JLabel getCloseTab_label_shortcut() {
		return closeTab_label_shortcut;
	}

	public void setCloseTab_label_shortcut(JLabel closeTab_label_shortcut) {
		this.closeTab_label_shortcut = closeTab_label_shortcut;
	}

    @Override
    public void stateChanged(ChangeEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stateChanged'");
    }
}
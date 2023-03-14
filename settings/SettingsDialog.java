package settings;

import java.awt.Dimension;
import java.awt.event.WindowListener;
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
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import gui.Gui;

public class SettingsDialog extends JDialog
{
     // ===== VARIABLES USED TO CREATE THE SETTINGS WINDOW =====
        int nbrParameters = 5;
        JTabbedPane tabbedPane;
        // === vertexs ===
            JPanel vertexPanel;
            // == LABELS ==    
            private JLabel vertex_label_insideColor,
                           vertex_label_outsideColor,
                           vertex_label_nameColor,
                           vertex_label_diameter,
                           vertex_label_StrokeWidth;

            // == DISPLAY CURRENT COLOR ==
            private JPanel vertex_panel_insideColor,
                           vertex_panel_outsideColor,
                           vertex_panel_nameColor;

            // == BUTTONS ==
            private JButton vertex_button_insideColor,
                            vertex_button_outsideColor,
                            vertex_button_nameColor,
                            vertex_button_diameter,
                            vertex_button_strokeWidth;

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
                           edge_button_weightBorderColor,
                           edge_button_strokeWidth,
                           edge_button_arrowLenght;

            // == EDIT CURRENT VALUES ==
            private JTextField edge_textField_strokeWidth,
                               edge_textField_arrowLenght;


    // ===== END VARIABLES USED TO CREATE THE SETTINGS WINDOW =====

    public SettingsDialog(Gui gui)
    {
        //We create the settings page
        super(gui, "Settings", true);
         
        //this.setLayout(new GridLayout(0, 3,0,10));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setMinimumSize(new Dimension(400,40*nbrParameters));
        this.setMaximumSize(new Dimension(400,40*nbrParameters));
        this.setLocationRelativeTo(null); //Centering the frame
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

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



        // ===== VERTEXS SETTINGS =====
        //VertexPanel
        vertexPanel = new JPanel();
        vertexPanel.setLayout(new GridLayout(0, 3,0,10));
        //Vertexs settings area
        //INSIDE COLOR
        vertex_label_insideColor = new JLabel("Inside color : ");
        vertexPanel.add(vertex_label_insideColor);

        vertex_panel_insideColor = new JPanel();
        vertex_panel_insideColor.setMaximumSize(new Dimension(16,16));
        vertex_panel_insideColor.setBackground(Gui.getSettings().getVertexInsideColor());
        vertex_panel_insideColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        vertexPanel.add(vertex_panel_insideColor);

        vertex_button_insideColor = new JButton("Change...");
        vertex_button_insideColor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color tempColor;
                if((tempColor = JColorChooser.showDialog(gui, "Select a new color", Gui.getSettings().vertexInsideColor)) != null)
                {
                    Gui.getSettings().setVertexInsideColor(tempColor);
                    vertex_panel_insideColor.setBackground(Gui.getSettings().getVertexInsideColor());

                }
            }   
        });
        vertexPanel.add(vertex_button_insideColor);

        //OUTSIDE COLOR
        vertex_label_outsideColor = new JLabel("Outside color : ");
        vertexPanel.add(vertex_label_outsideColor);

        vertex_panel_outsideColor = new JPanel();
        vertex_panel_outsideColor.setMaximumSize(new Dimension(16,16));
        vertex_panel_outsideColor.setBackground(Gui.getSettings().getVertexOutsideColor());
        vertex_panel_outsideColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        vertexPanel.add(vertex_panel_outsideColor);

        vertex_button_outsideColor = new JButton("Change...");
        vertex_button_outsideColor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color tempColor;
                if((tempColor = JColorChooser.showDialog(gui, "Select a new color", Gui.getSettings().getVertexOutsideColor())) != null)
                {
                    Gui.getSettings().setVertexOutsideColor(tempColor);
                    vertex_panel_outsideColor.setBackground(Gui.getSettings().getVertexOutsideColor());

                }
            }   
        });
        vertexPanel.add(vertex_button_outsideColor);

        //NAME COLOR
        vertex_label_nameColor = new JLabel("Name color : ");
        vertexPanel.add(vertex_label_nameColor);

        vertex_panel_nameColor = new JPanel();
        vertex_panel_nameColor.setMaximumSize(new Dimension(16,16));
        vertex_panel_nameColor.setBackground(Gui.getSettings().getVertexNameColor());
        vertex_panel_nameColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        vertexPanel.add(vertex_panel_nameColor);

        vertex_button_nameColor = new JButton("Change...");
        vertex_button_nameColor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color tempColor;
                if((tempColor = JColorChooser.showDialog(gui, "Select a new color", Gui.getSettings().getVertexNameColor())) != null)
                {
                    Gui.getSettings().setVertexNameColor(tempColor);
                    vertex_panel_nameColor.setBackground(Gui.getSettings().getVertexNameColor());

                }
            }   
        });
        vertexPanel.add(vertex_button_nameColor);

        //Vertex Diameter
        vertex_label_diameter = new JLabel("Diameter : ["+Gui.getSettings().getVertexDiameter()+"]");
        vertexPanel.add(vertex_label_diameter);
        vertex_textField_diameter = new JTextField();
        vertexPanel.add(vertex_textField_diameter);
        vertex_button_diameter = new JButton("Change");
        vertex_button_diameter.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Gui.getSettings().setVertexDiameter(Integer.parseInt(vertex_textField_diameter.getText()));
                    vertex_label_diameter.setText("Diameter : ["+Gui.getSettings().getVertexDiameter()+"]");
                }

                catch(Exception error)
                {
                    System.out.println(error.getMessage());
                }
            }   
        });
        vertexPanel.add(vertex_button_diameter);
        
        //Vertex StrokeWidth
        vertex_label_StrokeWidth = new JLabel("Stroke : ["+Gui.getSettings().getVertexStrokeWidth()+"]");
        vertexPanel.add(vertex_label_StrokeWidth);
        vertex_textField_strokeWidth = new JTextField();
        vertexPanel.add(vertex_textField_strokeWidth);
        vertex_button_strokeWidth = new JButton("Change");
        vertex_button_strokeWidth.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Gui.getSettings().setVertexStrokeWidth(Integer.parseInt(vertex_textField_strokeWidth.getText()));
                    vertex_label_StrokeWidth.setText("Stroke : ["+Gui.getSettings().getVertexStrokeWidth()+"]");
                }

                catch(Exception error)
                {
                    System.out.println(error.getMessage());
                }
            }   
        });
        vertexPanel.add(vertex_button_strokeWidth);
        
        //We add vertexPanel as a tabulation 
        tabbedPane.addTab("Vertex", vertexPanel);
        // ===== END VERTEXS SETTINGS =====



        // ===== EDGES SETTINGS =====
        //EdgePanel
        edgePanel = new JPanel();
        edgePanel.setLayout(new GridLayout(0, 3,0,2));
        //Edges settings area
        //INSIDE COLOR
        edge_label_strokeColor = new JLabel("Stroke color : ");
        edgePanel.add(edge_label_strokeColor);

        edge_panel_strokeColor = new JPanel();
        edge_panel_strokeColor.setMaximumSize(new Dimension(16,16));
        edge_panel_strokeColor.setBackground(Gui.getSettings().getEdgeStrokeColor());
        edge_panel_strokeColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        edgePanel.add(edge_panel_strokeColor);

        edge_button_strokeColor = new JButton("Change...");
        edge_button_strokeColor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color tempColor;
                if((tempColor = JColorChooser.showDialog(gui, "Select a new color", Gui.getSettings().getEdgeStrokeColor())) != null)
                {
                    Gui.getSettings().setEdgeStrokeColor(tempColor);
                    edge_panel_strokeColor.setBackground(Gui.getSettings().getEdgeStrokeColor());
                }
            }   
        });
        edgePanel.add(edge_button_strokeColor);

        //HIGHTLIGHT COLOR
        edge_label_highlightColor = new JLabel("Highlight color : ");
        edgePanel.add(edge_label_highlightColor);

        edge_panel_highlightColor = new JPanel();
        edge_panel_highlightColor.setMaximumSize(new Dimension(16,16));
        edge_panel_highlightColor.setBackground(Gui.getSettings().getEdgeHighlightColor());
        edge_panel_highlightColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        edgePanel.add(edge_panel_highlightColor);

        edge_button_highlightColor = new JButton("Change...");
        edge_button_highlightColor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color tempColor;
                if((tempColor = JColorChooser.showDialog(gui, "Select a new color", Gui.getSettings().getEdgeHighlightColor())) != null)
                {
                    Gui.getSettings().setEdgeHighlightColor(tempColor);
                    edge_panel_highlightColor.setBackground(Gui.getSettings().getEdgeHighlightColor());

                }
            }   
        });
        edgePanel.add(edge_button_highlightColor);

        //ARROW TIP COLOR
        edge_label_arrowTipColor = new JLabel("Arrow Tip color : ");
        edgePanel.add(edge_label_arrowTipColor);

        edge_panel_arrowTipColor = new JPanel();
        edge_panel_arrowTipColor.setMaximumSize(new Dimension(16,16));
        edge_panel_arrowTipColor.setBackground(Gui.getSettings().getEdgeArrowTipColor());
        edge_panel_arrowTipColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        edgePanel.add(edge_panel_arrowTipColor);

        edge_button_arrowTipColor = new JButton("Change...");
        edge_button_arrowTipColor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color tempColor;
                if((tempColor = JColorChooser.showDialog(gui, "Select a new color", Gui.getSettings().getEdgeArrowTipColor())) != null)
                {
                    Gui.getSettings().setEdgeArrowTipColor(tempColor);
                    edge_panel_arrowTipColor.setBackground(Gui.getSettings().getEdgeArrowTipColor());

                }
            }   
        });
        edgePanel.add(edge_button_arrowTipColor);

        //WEIGHT COLOR
        edge_label_weightColor = new JLabel("Weight color : ");
        edgePanel.add(edge_label_weightColor);

        edge_panel_weightColor = new JPanel();
        edge_panel_weightColor.setMaximumSize(new Dimension(16,16));
        edge_panel_weightColor.setBackground(Gui.getSettings().getEdgeWeightColor());
        edge_panel_weightColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        edgePanel.add(edge_panel_weightColor);

        edge_button_weightColor = new JButton("Change...");
        edge_button_weightColor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color tempColor;
                if((tempColor = JColorChooser.showDialog(gui, "Select a new color", Gui.getSettings().getEdgeWeightColor())) != null)
                {
                    Gui.getSettings().setEdgeWeightColor(tempColor);
                    edge_panel_weightColor.setBackground(Gui.getSettings().getEdgeWeightColor());

                }
            }   
        });
        edgePanel.add(edge_button_weightColor);

        //WEIGHT BORDER COLOR
        edge_label_weightBorderColor = new JLabel("Weight border color : ");
        edgePanel.add(edge_label_weightBorderColor);

        edge_panel_weightBorderColor = new JPanel();
        edge_panel_weightBorderColor.setMaximumSize(new Dimension(16,16));
        edge_panel_weightBorderColor.setBackground(Gui.getSettings().getEdgeWeightBorderColor());
        edge_panel_weightBorderColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        edgePanel.add(edge_panel_weightBorderColor);

        edge_button_weightBorderColor = new JButton("Change...");
        edge_button_weightBorderColor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color tempColor;
                if((tempColor = JColorChooser.showDialog(gui, "Select a new color", Gui.getSettings().getEdgeWeightBorderColor())) != null)
                {
                    Gui.getSettings().setEdgeWeightBorderColor(tempColor);
                    edge_panel_weightBorderColor.setBackground(Gui.getSettings().getEdgeWeightBorderColor());

                }
            }   
        });
        edgePanel.add(edge_button_weightBorderColor);

        //Stroke width
        edge_label_strokeWidth = new JLabel("Stroke width : ["+Gui.getSettings().getEdgeStrokeWidth()+"]");
        edgePanel.add(edge_label_strokeWidth);
        edge_textField_strokeWidth = new JTextField();
        edgePanel.add(edge_textField_strokeWidth);
        edge_button_strokeWidth = new JButton("Change");
        edge_button_strokeWidth.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Gui.getSettings().setEdgeStrokeWidth(Integer.parseInt(edge_textField_strokeWidth.getText()));
                    edge_label_strokeWidth.setText("Stroke width : ["+Gui.getSettings().getEdgeStrokeWidth()+"]");
                }

                catch(Exception error)
                {
                    System.out.println(error.getMessage());
                }
            }   
        });
        edgePanel.add(edge_button_strokeWidth);

        //Arrow Lenght
        edge_label_arrowLenght = new JLabel("Arrow lenght : ["+Gui.getSettings().getArrowLength()+"]");
        edgePanel.add(edge_label_arrowLenght);
        edge_textField_arrowLenght = new JTextField();
        edgePanel.add(edge_textField_arrowLenght);
        edge_button_arrowLenght = new JButton("Change");
        edge_button_arrowLenght.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Gui.getSettings().setArrowLength(Integer.parseInt(edge_textField_arrowLenght.getText()));
                    edge_label_arrowLenght.setText("Arrow lenght : ["+Gui.getSettings().getArrowLength()+"]");
                }

                catch(Exception error)
                {
                    System.out.println(error.getMessage());
                }
            }   
        });
        edgePanel.add(edge_button_arrowLenght);
                
        //We add edgePanel as a tabulation 
        tabbedPane.addTab("Edges", edgePanel);
        // ===== END EDGES SETTINGS =====
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

    public JLabel getVertex_label_StrokeWidth() {
        return vertex_label_StrokeWidth;
    }

    public void setVertex_label_StrokeWidth(JLabel vertex_label_StrokeWidth) {
        this.vertex_label_StrokeWidth = vertex_label_StrokeWidth;
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

    public int getNbrParameters() {
        return nbrParameters;
    }

    public void setNbrParameters(int nbrParameters) {
        this.nbrParameters = nbrParameters;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public JPanel getVertexPanel() {
        return vertexPanel;
    }

    public void setVertexPanel(JPanel vertexPanel) {
        this.vertexPanel = vertexPanel;
    }

    public JButton getVertex_button_diameter() {
        return vertex_button_diameter;
    }

    public void setVertex_button_diameter(JButton vertex_button_diameter) {
        this.vertex_button_diameter = vertex_button_diameter;
    }

    public JButton getVertex_button_strokeWidth() {
        return vertex_button_strokeWidth;
    }

    public void setVertex_button_strokeWidth(JButton vertex_button_strokeWidth) {
        this.vertex_button_strokeWidth = vertex_button_strokeWidth;
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

    public JButton getEdge_button_strokeWidth() {
        return edge_button_strokeWidth;
    }

    public void setEdge_button_strokeWidth(JButton edge_button_strokeWidth) {
        this.edge_button_strokeWidth = edge_button_strokeWidth;
    }

    public JButton getEdge_button_arrowLenght() {
        return edge_button_arrowLenght;
    }

    public void setEdge_button_arrowLenght(JButton edge_button_arrowLenght) {
        this.edge_button_arrowLenght = edge_button_arrowLenght;
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
}

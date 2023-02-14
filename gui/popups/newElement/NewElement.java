package gui.popups.newElement;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import gui.Gui;
import gui.draw.Draw;
import util.Graph;

public class NewElement extends JDialog
{
    //JPanel that have every other JPanel (required since we can only have 1 JPanel per JFrame)
    private JPanel global;
        private JPanel radioButtonArea;
            //Radio button group
            private ButtonGroup elementsButton;
                //The radio buttons and the items inside
                private JRadioButton graphButton;
                private JRadioButton automatonButton;

        private JPanel checkBoxArea;
            //One checkbox
            private JCheckBox isOriented;
            private JCheckBox isWeighted;

        private JPanel weightTypeArea;
            //Type of weight
            private JLabel weightLabel;
            private String[] weightType = {"Integer", "Boolean", "String", "Float"};
            private JComboBox weightList;

        private JPanel setNameArea;
            private JLabel nameLabel;
            private JTextArea nameTextArea;
            
        private JPanel validButtonArea;
            private JButton validation;

    public NewElement(Gui gui, Draw drawArea)
    {
        //General settings
        super(gui, "New element", true);
        this.setSize(200,220);
        this.setLocationRelativeTo(null); //Centering the frame
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* From this window, we can :
         * - Choose between graph and automaton
         * - For a graph, choose if its weighted and oriented
         * - If the graph is weighted, the type of weight
         * - Set a name
         */

        //Global JPanel
        global = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //Area of the radio buttons
        radioButtonArea = new JPanel();       
        //Creating the radio buttons
        //Graph button
        graphButton = new JRadioButton("Graph  ");
        graphButton.setSelected(true); //Graph is selected by default
        graphButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                //When Graph is selected, we enable the checkbox to create a weigthed/oriented graph
                isOriented.setEnabled(true);
                isWeighted.setEnabled(true);
                weightList.setEnabled(true);
            }
        });
        radioButtonArea.add(graphButton);
        //Automaton button
        automatonButton = new JRadioButton("Automaton");
        automatonButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                isOriented.setEnabled(false);
                isWeighted.setEnabled(false);
                weightList.setEnabled(false);
            }
        });
        radioButtonArea.add(automatonButton);
        //Adding the radio buttons to the same group
        elementsButton = new ButtonGroup();
        elementsButton.add(graphButton);
        elementsButton.add(automatonButton);
        //Adding the radio area to the JDialog
        global.add(radioButtonArea);



        //To choose if a graph is oriented and weighted (an automaton is always oritend and weighted)
        checkBoxArea = new JPanel();
        //Creating the checkboxes
        //Oriented
        isOriented = new JCheckBox("Oriented");
        checkBoxArea.add(isOriented);
        //Weighted
        isWeighted = new JCheckBox("Weighted");
        checkBoxArea.add(isWeighted);
        //Adding the checkbox area to the JDialog
        global.add(checkBoxArea);



        //Type of the weight
        //Label
        weightTypeArea = new JPanel();
        weightLabel = new JLabel("Weight type :");
        weightTypeArea.add(weightLabel);
        //JComboBox
        weightList = new JComboBox(weightType);
        weightTypeArea.add(weightList);
        global.add(weightTypeArea);



        //Name
        setNameArea = new JPanel();
        //Label
        nameLabel = new JLabel("Name :");
        setNameArea.add(nameLabel);
        //JTextArea
        nameTextArea = new JTextArea();
        nameTextArea.setPreferredSize(new Dimension(116, 14));
        setNameArea.add(nameTextArea);
        global.add(setNameArea);



        //Create button
        validButtonArea = new JPanel();
        validation = new JButton("Create");
        validation.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                createElement(gui, drawArea);
            }
        });
        validButtonArea.add(validation);
        validation.setPreferredSize(new Dimension(180,30));
        global.add(validButtonArea);


        //TEMPORARY
        automatonButton.setEnabled(false);
        //END TEMPORARY


        this.add(global);
        this.setVisible(true);
    }

    //Once everything is selected, we read user inputs to create the graph/automaton
    private void createElement(Gui gui, Draw drawArea)
    {
        //We first read if its an automaton or a graph
        if(graphButton.isSelected())
        {
            //Creating a new Graph
            //Each graph is associated with a tabulation
            Graph graph = new Graph(nameTextArea.getText(), isOriented.isSelected(), isWeighted.isSelected());
            //Creating the tabulation
            PanelPaint panelPaint = new PanelPaint(gui, drawArea);
            drawArea.addTab(nameTextArea.getText(), null, panelPaint, nameTextArea.getText()); //We create the new tab and retrieve it
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
        this.dispose();
    }
}
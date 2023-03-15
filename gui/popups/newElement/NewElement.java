package gui.popups.newElement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import gui.Gui;
import gui.draw.Draw;
import util.Graph;
import gui.draw.PanelPaint;

public class NewElement extends JDialog
{
    
    //JPanel that have every other JPanel (required since we can only have 1 JPanel per JFrame)
    private JPanel global;
            //Radio button group
            private ButtonGroup elementsButton;
                //The radio buttons and the items inside
                private JRadioButton graphButton;
                private JRadioButton automatonButton;

            //One checkbox
            private JCheckBox isOriented;
            private JCheckBox isWeighted;

            private JLabel nameLabel;
            private JTextField nameTextField;
            
            private JButton validation;
            private JButton cancel;

        private static int nbrElementCreated = 0;

    public NewElement(Gui gui, Draw drawArea)
    {
        //General settings
        super(gui, "New element", true);
        this.setSize(200,120);
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
        global = new JPanel();
        global.setLayout(new GridLayout(0,2));
      
        //Creating the radio buttons
        //Graph button
        graphButton = new JRadioButton("Graph  ");
        graphButton.setSelected(true); //Graph is selected by default
        graphButton.setHorizontalAlignment(JRadioButton.CENTER);
        graphButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                //When Graph is selected, we enable the checkbox to create a weigthed/oriented graph
                isOriented.setEnabled(true);
                isWeighted.setEnabled(true);
            }
        });
        global.add(graphButton);
        //Automaton button
        automatonButton = new JRadioButton("Automaton");
        automatonButton.setHorizontalAlignment(JRadioButton.CENTER);
        automatonButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                isOriented.setEnabled(false);
                isWeighted.setEnabled(false);
            }
        });
        global.add(automatonButton);
        //Adding the radio buttons to the same group
        elementsButton = new ButtonGroup();
        elementsButton.add(graphButton);
        elementsButton.add(automatonButton);



        //Creating the checkboxes
        //Oriented
        isOriented = new JCheckBox("Oriented");
        isOriented.setHorizontalAlignment(JCheckBox.CENTER);
        global.add(isOriented);
        //Weighted
        isWeighted = new JCheckBox("Weighted");
        isWeighted.setHorizontalAlignment(JCheckBox.CENTER);
        global.add(isWeighted);

        //Label
        nameLabel = new JLabel("Name :",SwingConstants.CENTER);        
        global.add(nameLabel);
        //JTextField
        nameTextField = new JTextField("Graph "+(nbrElementCreated++));
        nameTextField.setHorizontalAlignment(JTextField.CENTER);
        nameTextField.setPreferredSize(new Dimension(116, 20));
        global.add(nameTextField);



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

        global.add(cancel);
        cancel.setPreferredSize(new Dimension(180,30));

        validation = new JButton("Create");
        validation.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                createElement(gui, drawArea);
            }
        });
        global.add(validation);
        validation.setPreferredSize(new Dimension(180,30));

        

        this.add(global);

        //TEMPORARY
        automatonButton.setEnabled(false);
        //END TEMPORARY
        
        this.setVisible(true);
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
        this.dispose();
    }
}
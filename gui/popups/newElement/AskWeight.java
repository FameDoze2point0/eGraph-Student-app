package gui.popups.newElement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import gui.Gui;
import gui.draw.Draw;
import util.Graph;
import gui.draw.PanelPaint;

public class AskWeight extends JDialog
{
    
    private JPanel global;
        private JTextField jtf;
        private JButton submit;
        private JLabel errorLabel;
        private Integer weight;

    private GridBagConstraints constraints; //Constraints of the element that is gonna be added to the panel

    

    public AskWeight(Gui gui, Draw drawArea){
        super(gui, "Weight selection", true);
        this.setSize(550,100);
        this.setLocationRelativeTo(null); //Centering the frame
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        //Global JPanel
        global = new JPanel();
        global.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();



        //Label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;

        global.add(new JLabel("Enter the weight of the edge as an integer : "), constraints);
        


        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;

        jtf = new JTextField();
        jtf.setPreferredSize(new Dimension(90, 20));
        global.add(jtf, constraints);



        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;

        submit = new JButton("Valid your choice");
        submit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = jtf.getText();

                try {
                    weight = Integer.parseInt(text);
                    System.out.println("" + weight);
                    dispose();
                } catch (NumberFormatException exce) {
                    errorLabel.setVisible(true);
                    // System.out.println(exce);
                }
            }
        });
        global.add(submit, constraints);



        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;

        errorLabel = new JLabel("The weight has to be not null and an integer !");
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setVerticalAlignment(SwingConstants.CENTER);
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(new Font("Arial",Font.BOLD,20));
        errorLabel.setVisible(false);
        global.add(errorLabel, constraints);

        this.add(global);
        this.setVisible(true);
    }



    public Integer getWeight() {
        return weight;
    }

    
}

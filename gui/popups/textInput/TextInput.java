package gui.popups.textInput;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import gui.Gui;
import util.Edge;
import util.Vertex;

import java.awt.*;
import javax.swing.*;

public class TextInput extends JDialog
{
    JPanel general;
        JTextField input;
        JButton validationButton;

    public TextInput(Gui gui, Object obj)
    {
        super(gui, "Enter text", true);
        this.setMinimumSize(new Dimension(300,60));
        this.setLocationRelativeTo(null); //Centering the frame
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        general = new JPanel(new FlowLayout(FlowLayout.LEFT));
        general.setLayout(new GridLayout(0,2));

            input = new JTextField();
            input.setPreferredSize(new Dimension(90, 20));
            general.add(input);

            validationButton = new JButton("Confirmation");
            validationButton.setPreferredSize(new Dimension(190, 20));
            validationButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if (input.getText().length() > 5)
                    {
                        input.setText(input.getText().substring(0,5));   
                    }

                    if(input.getText().length() > 0)
                    {
                        sendInput(obj);
                    }
                }
            });
            general.add(validationButton);

        this.add(general);
        this.setVisible(true);
    }

    //Method that retrieve the user input and close the window
    public void sendInput(Object obj)
    {
        //Obj is the element we want to change the text of
        if(obj instanceof Vertex)
        {
            ((Vertex)obj).setName(input.getText());
            this.dispose();
        }

        else if(obj instanceof Edge)
        {
            try
            {
                ((Edge)obj).setWeight(Integer.parseInt(input.getText()));
                this.dispose();
            }
            catch (Exception e)
            {
                System.out.println("invalid input");
            }
        }
    }
}

package gui.popups.textInput;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import gui.Gui;
import util.Edge;
import util.Vertex;

public class TextInput extends JDialog
{
    JPanel general;
        JTextArea input;
        JButton validationButton;

    public TextInput(Gui gui, Object obj)
    {
        super(gui, "Enter text", true);
        this.setMinimumSize(new Dimension(300,57));
        this.setMaximumSize(new Dimension(300,250));
        this.setLocationRelativeTo(null); //Centering the frame
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        general = new JPanel(new FlowLayout(FlowLayout.LEFT));

            input = new JTextArea();
            input.setPreferredSize(new Dimension(90, 15));
            general.add(input);

            validationButton = new JButton("Confirmation");
            validationButton.setPreferredSize(new Dimension(190, 20));
            validationButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    sendInput(obj);
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
        }

        else if(obj instanceof Edge)
        {
            ((Edge)obj).setWeight(input.getText());
        }
        
        this.dispose();
    }
}

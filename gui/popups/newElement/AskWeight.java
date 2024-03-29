package gui.popups.newElement;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import gui.Gui;
import gui.draw.Draw;

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

        //To quit the jdialog when pressing echap
        Action quitAction = new AbstractAction("Cancel") {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();                
            }
        };
        quitAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        global.getActionMap().put("quitAskWeight", quitAction);
        global.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke)quitAction.getValue(Action.ACCELERATOR_KEY), "quitAskWeight");

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


        Action validationAskWeight = new AbstractAction("Valid your choice") {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (jtf.getText().length() > 5)
                {
                    jtf.setText(jtf.getText().substring(0,5));   
                }

                String text = jtf.getText();

                try {
                    weight = Integer.parseInt(text);
                    dispose();
                } catch (NumberFormatException exce) {
                    errorLabel.setVisible(true);
                    // System.out.println(exce);
                }
            }
        };
        validationAskWeight.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        submit = new JButton(validationAskWeight);
        submit.getActionMap().put("askWeight", validationAskWeight);
        submit.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke)validationAskWeight.getValue(Action.ACCELERATOR_KEY), "askWeight");
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

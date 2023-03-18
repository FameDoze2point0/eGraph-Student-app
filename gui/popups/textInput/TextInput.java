package gui.popups.textInput;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import gui.Gui;
import gui.draw.PanelPaint;
import util.Edge;
import util.Graph;
import util.Vertex;

public class TextInput extends JDialog
{
    private JPanel general;
    private JTextField input;
    private JButton validationButton;

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


            Action textInputAction = new AbstractAction("Create") {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(input.getText().length() > 0)
                    {
                        sendInput(obj, gui);
                    }
                }
            };
            textInputAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
            validationButton = new JButton(textInputAction);
            validationButton.getActionMap().put("textInputAction", textInputAction);
            validationButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke)textInputAction.getValue(Action.ACCELERATOR_KEY), "textInputAction");
            general.add(validationButton);

        this.add(general);
        this.setVisible(true);
    }

    //Method that retrieve the user input and close the window
    public void sendInput(Object obj, Gui gui)
    {
        Graph graph = gui.getTabulations().get(gui.getDraw().getSelectedComponent());
        //Obj is the element we want to change the text of
        if(obj instanceof Vertex)
        {
            if (input.getText().length() > 5)
            {
                input.setText(input.getText().substring(0,5));   
            }
            ((Vertex)obj).setName(input.getText());
            graph.getPanelPaint().repaint();
            this.dispose();
        }

        else if(obj instanceof Edge)
        {
            try
            {
                if (input.getText().length() > 5)
                {
                    input.setText(input.getText().substring(0,5));   
                }
                Edge myEdge = ((Edge)obj);
                myEdge.setWeight(Integer.parseInt(input.getText()));

                if(!graph.getOriented())
                {
                    for(Edge edge : graph.getEdges()) {
                        if (myEdge.getStart() == edge.getEnd() && edge.getStart() == myEdge.getEnd()) {
                            edge.setWeight(Integer.parseInt(input.getText()));
                            break;
                        }
                    }
                }
                graph.getPanelPaint().repaint();
                this.dispose();
            }
            catch (Exception e)
            {
                System.out.println("invalid input");
            }
        }

        else if(obj instanceof Graph)
        {
            try
            {
                graph.setName(input.getText()); //Graph name
                gui.getDraw().setTitleAt(gui.getDraw().getSelectedIndex(), input.getText()); //Tab name
                ((PanelPaint)gui.getDraw().getSelectedComponent()).getGraphNameLabel().setText("Name : "+input.getText());;
                dispose();
            }
            catch (Exception e)
            {
                System.out.println("invalid input");
            }
        }
    }
}

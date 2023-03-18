package gui.popups.other;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import gui.Gui;

public class About extends JDialog
{
    public About(Gui gui)
    {
        super(gui, "About",true);
        this.setMinimumSize(new Dimension(500,500));
        this.setSize(new Dimension(500,500));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel about = new JLabel("<html><body><p>This project was realized within the framework of our studies, with pedagogical ends.<br><br>This software allows to create and manipulate graphs and automata, as well as to run some associated algorithms.</p><h1>Specials thanks</h1><p>We want to thank some of the teachers who helped us during the realization of this project:<ul><li>Mr. Cohen : Mr. Cohen followed us thoroughly every week and gave us advice to guide us in the right direction. Moreover, his enthusiasm for our project was an additional source of motivation. </li><br><li>Mr. Fürst : Expert in Java, he gave us advices on how to develop in object-oriented language as well as on the implementation of some features.</li><br><li>Ms. Levé : Even before the start of the development, Ms. Levé gave us advices on implementations choices as well as on legal aspects.</li><br><li>Mr. Devismes : Mr. Devismes gave us advice before the beginning of the project and advised us on our choices in order to have the best possible project in the time allotted to us. He also lent us a book to help us develop in Java.</li></ul><br><u>Testers :</u> Mr. Devismes, Mr. Fürst, Ms. Levé, Mr. Bonvarlet, Mr. Giakoumakis<br><u>Project Supervisors :</u> Mr. Fürst & Mr. Cohen</p></body></html>");

        //To quit the jdialog when pressing echap
        AbstractAction quitAction = new AbstractAction("Cancel") {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();                
            }
        };
        quitAction.putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        about.getActionMap().put("quitAbout", quitAction);
        about.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke)quitAction.getValue(AbstractAction.ACCELERATOR_KEY), "quitAbout");
        
        about.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"About"));
        add(about);
        this.setVisible(true);
    }

   
}

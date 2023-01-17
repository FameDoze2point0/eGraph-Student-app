package gui;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Gui extends JFrame{

    JMenuBar menuBar;
    public Gui(){
        super("eGraph Student");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setSize(960,540);
        this.setVisible(true); // we display the JFrame
        this.setLocationRelativeTo(null); // we center the JFrame

        createMenuBar();
        this.setJMenuBar(menuBar);
    }

    private void createMenuBar(){
        // the menu bar
        menuBar = new JMenuBar();

        //File section
        JMenu menuFile = new JMenu("File");
            menuFile.setMnemonic('F');
        JMenuItem itemFile = new JMenuItem("New graph");
            //mnemonic
            //icon
            //accelerator
            //actionlistener
            menuFile.add(itemFile);
        itemFile = new JMenuItem("New automaton");
            //mnemonic
            //icon
            //accelerator
            //actionlistener
            menuFile.addSeparator();
            menuFile.add(itemFile);
            
        itemFile = new JMenuItem("Save");
            itemFile.setMnemonic('S');
            itemFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
            //actionlistener
            menuFile.addSeparator();
            menuFile.add(itemFile);

        menuBar.add(menuFile);






        //Edit section
        JMenu menuEdit = new JMenu("Edit");
        menuFile.setMnemonic('E');

        menuBar.add(menuEdit);






        //Algorithm section
        JMenu menuAlgorithm = new JMenu("Algorithm");
        menuFile.setMnemonic('A');

        menuBar.add(menuAlgorithm);






        //Settings section
        JMenu menuSettings = new JMenu("Settings");
        menuFile.setMnemonic('S');

        menuBar.add(menuSettings);






        //Help section
        JMenu menuHelp = new JMenu("Help");
        menuFile.setMnemonic('H');

        menuBar.add(menuHelp);




    }
}

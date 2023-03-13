package gui.draw;
import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileSystemView;

import gui.Gui;
import util.Graph;

public class Draw extends JTabbedPane implements ChangeListener
{
    public Draw()
    {
        super();
        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT); //Allow to force tab to stay on the same line
        this.addChangeListener(this); //We redraw when we change of tabulation
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.getSelectedComponent().repaint();
    }

    //TO LOAD AND SAVE GRAPH
    public void saveTabulation(Gui gui)
    {
        try
        {
            //To select create a save file
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Create the save file...");
            int userSelection = jfc.showSaveDialog(null);

            if(userSelection == JFileChooser.APPROVE_OPTION)
            {
                FileOutputStream fos = new FileOutputStream(jfc.getSelectedFile().getAbsolutePath());
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                //Retreiving the graph to save
                Graph toSave = gui.getTabulations().get(getSelectedComponent());

                //Writing the object
                oos.writeObject(toSave);

                oos.close();
                fos.close();
            }
        }

        catch(Exception e)
        {
            System.out.println("An error occured during the save of the tabulation.");
        }
    }

    public void loadTabulation(Gui gui)
    {
        try
        {
            //To select a save file
            // JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            // jfc.setCurrentDirectory(new File(System.getProperty("user.home")));
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Open a file...");

            if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                FileInputStream fis = new FileInputStream(jfc.getSelectedFile().getAbsolutePath());
                ObjectInputStream ois = new ObjectInputStream(fis);

                //Reading the object
                Graph toLoad = (Graph)ois.readObject();
                toLoad.getPanelPaint().setGui(gui);
                toLoad.getPanelPaint().setDrawArea(this);

                //Creating the tabulation
                PanelPaint panelPaint = toLoad.getPanelPaint();
                addTab(toLoad.getName(), null, panelPaint, toLoad.getName()); //We create the new tab and retrieve it
                //We then add these two components to the list of opened tabulations
                gui.getTabulations().put(panelPaint,toLoad);

                //We go to the new tabulation and draw the panel
                setSelectedComponent(panelPaint);
                panelPaint.repaint();

                ois.close();
                fis.close();
            }
        }

        catch(Exception e)
        {
            System.out.println("An error occured during the load of the tabulation : "+e.getLocalizedMessage());
        }
    }
}
package gui.menu.components;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.Event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.Gui;
import gui.draw.Draw;
import gui.popups.newElement.NewElement;

public class File extends JMenu
{
    private JMenuItem newFile;
    private JMenuItem openFile;
    private JMenuItem saveAs;
    
    private JMenu exportFile;
        //exportFile have 2 sub components
        private JMenuItem exportPDF;
        private JMenuItem exportSVG;

    private JMenuItem closeTabulation;
    private JMenuItem exit;

    public File(Gui gui, Draw draw)
    {
        super("File");
        // - File (New, Open, Save, Export (PDF, SVG), Exit)

        //New file
        newFile = new JMenuItem("New...", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_bezierinsert.png"));
        newFile.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                new NewElement(gui, draw);
            }
        });
        newFile.setMnemonic(KeyEvent.VK_N);
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK)); 
        this.add(newFile);

        //Open file
        openFile = new JMenuItem("Open...", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_insertmasterpage.png"));
        openFile.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                draw.loadTabulation(gui);
            }
        });
        openFile.setMnemonic(KeyEvent.VK_O);
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
        this.add(openFile);

        //Save file
        saveAs = new JMenuItem("Save as...", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_recsave.png"));
        saveAs.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                draw.saveTabulation(gui);
            }
        });
        saveAs.setMnemonic(KeyEvent.VK_S);
        saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
        this.add(saveAs);

        //Export menu
        exportFile = new JMenu("Export");
            //As PDF
            exportPDF = new JMenuItem("PDF...");
            exportPDF.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.out.println("export pdf popup");
                }
            });
            exportFile.add(exportPDF);

            //As SVG
            exportSVG = new JMenuItem("SVG...");
            exportSVG.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.out.println("export SVG popup");
                }
            });
            exportFile.add(exportSVG);
            exportFile.setEnabled(false);
        this.add(exportFile);


        closeTabulation = new JMenuItem("Close Tabulation", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_dbformdelete.png"));
        closeTabulation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                if (draw.getTabCount() > 1) {
                    
                    
                    gui.getTabulations().remove(draw.getSelectedComponent());
                    draw.remove(draw.getSelectedIndex());
                    draw.setSelectedIndex(0); 
                }
                
            }
        });
        closeTabulation.setMnemonic(KeyEvent.VK_W);
        closeTabulation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, Event.CTRL_MASK));
        this.add(closeTabulation);



        exit = new JMenuItem("Exit", new ImageIcon(System.getProperty("user.dir")+"/ressources/sc_quit.png"));
        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        exit.setMnemonic(KeyEvent.VK_ESCAPE);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        this.add(exit);
    }

    public JMenuItem getNewFile() {
        return newFile;
    }

    public void setNewFile(JMenuItem newFile) {
        this.newFile = newFile;
    }

    public JMenuItem getOpenFile() {
        return openFile;
    }

    public void setOpenFile(JMenuItem openFile) {
        this.openFile = openFile;
    }

    public JMenuItem getSaveAs() {
        return saveAs;
    }

    public void setSaveAs(JMenuItem saveAs) {
        this.saveAs = saveAs;
    }

    public JMenu getExportFile() {
        return exportFile;
    }

    public void setExportFile(JMenu exportFile) {
        this.exportFile = exportFile;
    }

    public JMenuItem getExportPDF() {
        return exportPDF;
    }

    public void setExportPDF(JMenuItem exportPDF) {
        this.exportPDF = exportPDF;
    }

    public JMenuItem getExportSVG() {
        return exportSVG;
    }

    public void setExportSVG(JMenuItem exportSVG) {
        this.exportSVG = exportSVG;
    }

    public JMenuItem getExit() {
        return exit;
    }

    public void setExit(JMenuItem exit) {
        this.exit = exit;
    }
}

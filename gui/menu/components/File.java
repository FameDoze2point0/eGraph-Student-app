package gui.menu.components;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import gui.Gui;
import gui.draw.Draw;
import gui.popups.newElement.NewElement;

import java.awt.event.*;

public class File extends JMenu
{
    private JMenuItem newFile;
    private JMenuItem openFile;
    private JMenuItem saveAs;
    
    private JMenu exportFile;
        //exportFile have 2 sub components
        private JMenuItem exportPDF;
        private JMenuItem exportSVG;

    private JMenuItem exit;

    public File(Gui gui, Draw drawArea)
    {
        super("File");
        // - File (New, Open, Save, Export (PDF, SVG), Exit)

        //New file
        newFile = new JMenuItem("New...");
        newFile.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                new NewElement(gui, drawArea);
            }
        });
        this.add(newFile);

        //Open file
        openFile = new JMenuItem("Open...");
        openFile.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("open file popup");
            }
        });
        this.add(openFile);

        //Save file
        saveAs = new JMenuItem("Save as...");
        saveAs.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("save as popup");
            }
        });
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
        this.add(exportFile);

        exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("exit popup");
            }
        });
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

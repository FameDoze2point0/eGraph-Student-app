package gui.menu;
import javax.swing.JMenuBar;
import gui.Gui;
import gui.draw.Draw;
import gui.menu.components.Algorithms;
import gui.menu.components.Edit;
import gui.menu.components.File;
import gui.menu.components.Other;
import gui.menu.components.SettingsMenu;

public class Menu extends JMenuBar
{
    private File file;
    private Edit edit;
    private Algorithms algorithms;
    private SettingsMenu settingsMenu;
    private Other other;

    public Menu(Gui gui, Draw drawArea)
    {
        super(); //Init of the JMenuBar
        /* The menu will have :
         * - File (New, Open, Save, Export (PDF, SVG), Exit)
         * - Edit (Undo, Redo, Cut, Copy, Paste)
         * - Algorithms (Launch, Add)
         * - Settings (Shortcuts, Appareance, Default automaton, Default graph)
         * - Other (Documentation, Licence, Contacts, About)
         */

        file = new File(gui, drawArea);
        this.add(file);
        
        edit = new Edit(gui, drawArea);
        this.add(edit);
        
        algorithms = new Algorithms(gui, drawArea);
        this.add(algorithms);
        
        settingsMenu = new SettingsMenu(gui);
        this.add(settingsMenu);

        other = new Other(gui);
        this.add(other);
    }
    
    public Edit getEdit() {
        return edit;
    }

    public void setEdit(Edit edit) {
        this.edit = edit;
    }   

    public Algorithms getAlgorithms() {
        return algorithms;
    }

    public void setAlgorithms(Algorithms algorithms) {
        this.algorithms = algorithms;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}

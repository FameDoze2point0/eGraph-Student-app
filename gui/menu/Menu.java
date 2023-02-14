package gui.menu;
import javax.swing.JMenuBar;

import gui.Gui;
import gui.draw.Draw;
import gui.menu.components.Algorithms;
import gui.menu.components.Edit;
import gui.menu.components.File;
import gui.menu.components.Other;
import gui.menu.components.Settings;

public class Menu extends JMenuBar
{
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

        this.add(new File(gui, drawArea));
        this.add(new Edit());
        this.add(new Algorithms());
        this.add(new Settings());
        this.add(new Other());
    }
}

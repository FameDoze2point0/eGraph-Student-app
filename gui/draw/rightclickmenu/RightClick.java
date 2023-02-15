package gui.draw.rightclickmenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class RightClick extends JPopupMenu
{
    public RightClick()
    {
        super("edit");   
        JMenuItem cut = new JMenuItem("Cut");  
        JMenuItem copy = new JMenuItem("Copy");  
        JMenuItem paste = new JMenuItem("Paste");  
        this.add(cut);
        this.add(copy);
        this.add(paste);
    }   
}

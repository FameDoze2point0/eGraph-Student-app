package gui.draw;
import javax.swing.JTabbedPane;

public class Draw extends JTabbedPane
{
    public Draw()
    {
        super();
        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT); //Allow to force tab to stay on the same line
    }
}

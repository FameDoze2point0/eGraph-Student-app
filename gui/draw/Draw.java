package gui.draw;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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

    public static void showRightClickMenu() {
    }
}

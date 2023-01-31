package gui;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.*;

public class PanelPaint extends JPanel
{
    PanelPaint()
    {
        super();
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 1000000, 100000);
    }
}

package gui.popups.other;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import gui.Gui;

public class License extends JDialog
{
    public License(Gui gui)
    {
        super(gui, "License",true);
        this.setMinimumSize(new Dimension(500,350));
        this.setSize(new Dimension(500,350));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel license= new JLabel("<html>Copyright (c) 2022 Antonin C. & François P.<br> Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the \"Software\"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:<br><br>The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.<br><br>THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.</p></html>");
        license.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"MIT License"));
        
        AbstractAction quitAction = new AbstractAction("Cancel") {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();                
            }
        };
        quitAction.putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        license.getActionMap().put("quitDocumentation", quitAction);
        license.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke)quitAction.getValue(AbstractAction.ACCELERATOR_KEY), "quitDocumentation");

        
        this.add(license);
        this.setVisible(true);
    }
}

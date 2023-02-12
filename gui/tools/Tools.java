package gui.tools;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class Tools extends JToolBar
{
    //New graph/automaton
    JButton newGraph;
    JButton newAutomaton;

    //Add elements to a graph/automaton
    JButton newVertex;
    JButton newEdge;
    JButton addWeight;


    public Tools()
    {
        super("Tools", JToolBar.HORIZONTAL);

        //Create Graph
        newGraph = new JButton("Graph +");
        newGraph.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("new graph !");
                
            }
        });
        this.add(newGraph);

        //Create automaton
        newAutomaton = new JButton("Automaton +");
        newAutomaton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("new automaton !");
                
            }
        });
        this.add(newAutomaton);

        //Adding a separator
        this.add(new JToolBar.Separator());

        //New Vertex/State
        newVertex = new JButton("Vertex +");
        newVertex.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("new vertex !");
                
            }
        });
        this.add(newVertex);

        //New edge
        newEdge = new JButton("Edge +");
        newEdge.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("new edge !");
                
            }
        });
        this.add(newEdge);
    }


    public JButton getNewGraph() {
        return newGraph;
    }


    public void setNewGraph(JButton newGraph) {
        this.newGraph = newGraph;
    }


    public JButton getNewAutomaton() {
        return newAutomaton;
    }


    public void setNewAutomaton(JButton newAutomaton) {
        this.newAutomaton = newAutomaton;
    }


    public JButton getNewVertex() {
        return newVertex;
    }


    public void setNewVertex(JButton newVertex) {
        this.newVertex = newVertex;
    }


    public JButton getNewEdge() {
        return newEdge;
    }


    public void setNewEdge(JButton newEdge) {
        this.newEdge = newEdge;
    }


    public JButton getAddWeight() {
        return addWeight;
    }


    public void setAddWeight(JButton addWeight) {
        this.addWeight = addWeight;
    }
}

import javax.swing.*;

public class GraphicsResult{

    JLabel Input, second, third;

    public JPanel createContentPane (String input, String line2, String line3){
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);

        Input = new JLabel();
        Input.setText("Input: "+input);
        Input.setLocation(10,10);
        Input.setSize(200, 20);
        totalGUI.add(Input);
        
        second = new JLabel();
        second.setText(line2);
        second.setLocation(10,30);
        second.setSize(200, 20);
        totalGUI.add(second);
        
        third = new JLabel();
        third.setText(line3);
        third.setLocation(10,50);
        third.setSize(500, 20);
        totalGUI.add(third);
        
        totalGUI.setOpaque(true);    
        return totalGUI;
    }

    public static void createAndShowGUI(String input, boolean isGraph, String line2, String line3) {
        JFrame.setDefaultLookAndFeelDecorated(false);
        JFrame frame = new JFrame("Results!");
        GraphicsResult demo = new GraphicsResult();
        frame.setContentPane(demo.createContentPane(input, line2, line3));
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 300);
        if(isGraph)
         Graph.GraphicCreate(input);
        frame.setLocation(950,200);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI("x+1", true, "Word form: "+ NumToString.stringify(5), "Quantity of 5");
            }
        });
    }
}

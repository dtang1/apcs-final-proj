//heavy source: http://www.macs.hw.ac.uk/guidebook/?name=Introduction&page=1

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GraphicsStart implements  ActionListener{

    JPanel textPanel, panelForTextFields, pPanel;
    JLabel titleLabel, InputLabel, userLabel;
    JTextField InputField, loginField;
    JButton GoButton, HelpButton;

    public JPanel createContentPane (){

        // We create a bottom JPanel to place everything on.
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);

        titleLabel = new JLabel("Program Running...");
        titleLabel.setLocation(0,0);
        titleLabel.setSize(290, 30);
        titleLabel.setHorizontalAlignment(0);
        totalGUI.add(titleLabel);

        // Creation of a Panel to contain the JLabels
        textPanel = new JPanel();
        textPanel.setLayout(null);
        textPanel.setLocation(10, 35);
        textPanel.setSize(70, 80);
        totalGUI.add(textPanel);

        // Input Label
        InputLabel = new JLabel("Input");
        InputLabel.setLocation(0, 0);
        InputLabel.setSize(70, 40);
        InputLabel.setHorizontalAlignment(4);
        textPanel.add(InputLabel);

        // TextFields Panel Container
        panelForTextFields = new JPanel();
        panelForTextFields.setLayout(null);
        panelForTextFields.setLocation(100, 40);
        panelForTextFields.setSize(100, 50);
        totalGUI.add(panelForTextFields);

        // Input Textfield
        InputField = new JTextField(8);
        InputField.setLocation(0, 0);
        InputField.setSize(100, 30);
        panelForTextFields.add(InputField);

        // Button for Go
        GoButton = new JButton("Enter!");
        GoButton.setLocation(115, 90);
        GoButton.setSize(80, 30);
        GoButton.addActionListener(this);
        totalGUI.add(GoButton);

        HelpButton = new JButton("Help!");
        HelpButton.setLocation(210, 40);
        HelpButton.setSize(80, 30);
        HelpButton.addActionListener(this);
        InputLabel.setHorizontalAlignment(4);
        totalGUI.add(HelpButton);

        totalGUI.setOpaque(true);    
        return totalGUI;
    }
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == GoButton)
        {
      int i = Main.reader(InputField.getText());
      System.out.println(i);
     boolean graphnes = true;
     String result = "";
     String secondary = "";
     if(i == 1){
       Polynomials ply = new Polynomials(InputField.getText());
       result = "Root 1: " + ply.root1();
       secondary = "Root 2: " + ply.root2();
     }
     else if (i == 2){
       String st = Operate.evaluate(InputField.getText());
       result = "Result: " + st;
       secondary = "Number Name: " + NumToString.stringify(Double.parseDouble(st));
       graphnes = false;
     }
     else if (i == 3){
       String st = Equation.evaluate(InputField.getText());
       result = "Result: x = " + st;
       secondary = "Number Name: x is equal to " + NumToString.stringify(Double.parseDouble(st));
       graphnes = false;
     }
     else
       result = "Invalid input.";
     final String uno = result;
     final String dos = secondary;
     final boolean graphable = graphnes;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GraphicsResult.createAndShowGUI(InputField.getText(), graphable, uno, dos);                           
            }
        });
        }
        if(e.getSource() == HelpButton)
         HelpWindow();
    }


    private static void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(false);
        JFrame frame = new JFrame("Brought to you by AlphaWolves");

        GraphicsStart demo = new GraphicsStart();
        frame.setContentPane(demo.createContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(310, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public static void HelpWindow(){
     SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HelpFrame();
            }
        });
    }
    public static void HelpFrame(){
     JFrame Helpframe = new JFrame("Help!");
     Helpframe.setSize(200,200);
     Helpframe.setLocationRelativeTo(null);
     Helpframe.setVisible(true);
     
        JPanel HelpP = new JPanel();
        HelpP.setLayout(new BorderLayout());

        JTextArea Help = new JTextArea("This program is quick and simple to use. Simply enter a mathematical expression or simple equation such as 42 or x^2 and press the Enter! button for results!");
        Help.setLocation(115,90);
        Help.setSize(100, 100);
        Help.setVisible(true);
        Help.setLineWrap(true);
        Help.setWrapStyleWord(true);
        HelpP.add(Help);
     Helpframe.setContentPane(HelpP);
    }
    public static void main() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
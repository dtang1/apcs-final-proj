//Amazing base source: http://stackoverflow.com/questions/8693342/drawing-a-simple-line-graph-in-java
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Graph extends JPanel {
   private static final int MAX_COR = 20;
   private static final int PREF_W = 310;
   private static final int PREF_H = 310;
   private static final int BORDER_GAP = 10;
   private static final Color GRAPH_COLOR = Color.red;
   private static final Color GRAPH_POINT_COLOR = Color.cyan;
   private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
   private static final int GRAPH_POINT_WIDTH = 4;
   private static final int Y_HATCH_COUNT = 20;
   public List<Integer> points;

   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      double xScale = (((double) getWidth() - 2 * BORDER_GAP) / (points.size() - 1) /2);
      double yScale = (((double) getHeight() - 2 * BORDER_GAP) / (MAX_COR - 1));
      
      List<Point> graphPoints = new ArrayList<Point>();
      for (int i = 0; i < 21; i++) {
         int x1 = (int) ((i * xScale)*2 + BORDER_GAP);
         int y1 = (int) (((MAX_COR - points.get(i)) * yScale) - getHeight()/2 + BORDER_GAP);
         graphPoints.add(new Point(x1, y1));
      }

      // create x and y axes 
      g2.drawLine(getWidth() /2, getHeight() - BORDER_GAP, getWidth() / 2, BORDER_GAP);
      g2.drawLine(BORDER_GAP, getHeight()/2, getWidth() - BORDER_GAP, getHeight() / 2);

      // create hatch marks for y axis. 
      for (int i = 0; i < Y_HATCH_COUNT; i++) {
         int x0 = (getWidth() - GRAPH_POINT_WIDTH) / 2;
         int x1 = (getWidth() + GRAPH_POINT_WIDTH) / 2;
         int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_COUNT + BORDER_GAP);
         int y1 = y0;
         g2.drawLine(x0, y0, x1, y1);
      }

      // and for x axis
      for (int i = -1; i < points.size() - 1; i++) {
         int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (points.size() - 1) + BORDER_GAP;
         int x1 = x0;
         int y0 = (getHeight() - GRAPH_POINT_WIDTH) / 2;
         int y1 = (getHeight() + GRAPH_POINT_WIDTH) / 2;
         g2.drawLine(x0, y0, x1, y1);
      }

      Stroke oldStroke = g2.getStroke();
      g2.setColor(GRAPH_COLOR);
      g2.setStroke(GRAPH_STROKE);//graphPoints.size() - 1
      for (int i = 0; i < graphPoints.size() - 1; i++) {
         int x1 = graphPoints.get(i).x;
         int y1 = graphPoints.get(i).y;
         int x2 = graphPoints.get(i + 1).x;
         int y2 = graphPoints.get(i + 1).y;
         g2.drawLine(x1, y1, x2, y2);         
      }

      g2.setStroke(oldStroke);      
      g2.setColor(GRAPH_POINT_COLOR);
      for (int i = 0; i < graphPoints.size(); i++) {
         int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
         int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
         int ovalW = GRAPH_POINT_WIDTH;
         int ovalH = GRAPH_POINT_WIDTH;
         g2.fillOval(x, y, ovalW, ovalH);
      }
   }

   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }

   private static void createAndShowGui() {
      Graph mainPanel = new Graph();
      mainPanel.points =new Polynomials("x").getPoints();

      JFrame frame = new JFrame("Graph");
      frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocation(950, 400);
      frame.setVisible(true);
   }
   private static void createAndShowGui(String poly) {
	      Graph mainPanel = new Graph();
	      mainPanel.points =new Polynomials(poly).getPoints();

	      JFrame frame = new JFrame("Graph");
	      frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	      frame.getContentPane().add(mainPanel);
	      frame.pack();
	      frame.setLocation(950, 400);
	      frame.setVisible(true);
	   }
   public static void GraphicCreate(final String input){
	      SwingUtilities.invokeLater(new Runnable() {
	          public void run() {
	             createAndShowGui(input);
	          }
	       });
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}
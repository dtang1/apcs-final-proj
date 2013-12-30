import cs1.Keyboard;
import java.util.ArrayList;

public class Main {
  
  
  public static int reader(String s){
    if (s.indexOf("=") >= 0 && s.indexOf("y") < 0) {
      String st = Equation.evaluate(s);
      if (!st.equals("Invalid input")) 
        return 3;
        else return 0;
    }
    else if (s.indexOf("x") >= 0) {
      try {
          Polynomials test = new Polynomials(s);
          test.quadroots();
          return 1;
        }
      catch(Exception e){
        return 0;
      }
    }
    else {
        String st = Operate.evaluate(s);
        if (!st.equals("Invalid input"))
          return 2;
        else return 0;
            
        }
  }
      
    
  public static void main(String[] args) { 
    /*System.out.println("Welcome! Type something below.\n");
    while (true) {
      String s = Keyboard.readString();
      if (s.indexOf("=") >= 0) {
        String st = Equation.evaluate(s);
        System.out.println(st);
        if (!st.equals("Invalid input")) {
          try {System.out.println(NumToString.stringify(Double.parseDouble(st)));}
          catch(Exception e){};
        }
      }
      else if (s.indexOf("x") >= 0) {
        try {
          Polynomials test = new Polynomials(s);
          test.quadroots();
        }
        catch(Exception e){
          System.out.println("Invalid input");
        }
      }
      else {
        String st = Operate.evaluate(s);
        System.out.println(st);
        if (!st.equals("Invalid input")) {
          try {System.out.println(NumToString.stringify(Double.parseDouble(st)));}
          catch(Exception e){};
        }
      }
      System.out.println();
    }
    */
    GraphicsStart start = new GraphicsStart();
    start.main();
  }
  
}
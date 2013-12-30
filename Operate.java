import cs1.Keyboard;
import java.util.ArrayList;
import java.math.BigDecimal;

class Operate {

    private static final String DIGITS = "0123456789";
    private static final String OPERATORS = "()^*/+-";

    static String evaluate(String s) {
 ArrayList<String> a = putInArr(s);
 if (!checkParens(a)) return "Invalid input";
 readNegative(a);
 if (!operate(a,0,a.size()-1)) return "Invalid input";
 String retVal = a.get(0);
 if (retVal.length() > 2 && retVal.substring(retVal.length()-2).equals(".0"))
   retVal = retVal.substring(0,retVal.length()-2);
 if (retVal.equals("-0")) retVal = "0";
 return retVal;
    }

    static ArrayList<String> putInArr(String s) {
 ArrayList<String> a = new ArrayList<String>();
 boolean dec = false;
 String temp = "";
 for (int i = 0; i <= s.length(); i++) {
   if (i == s.length()) {
     if (temp.length() > 0) a.add(temp);
     break;
   }
     String c = s.substring(i,i+1);
     if (DIGITS.indexOf(c) > -1) temp += c;
     else if (OPERATORS.indexOf(c) > -1) {
  if (temp.length() > 0) {
      if (temp.equals(".")) return null;
      a.add(temp);
      temp = "";
      dec = false;
  }
  a.add(c);
     }
     else if (".".equals(c)) {
  if (dec) return null;
  temp += c;
  dec = true;
     }
     else if (!" ".equals(c) && !",".equals(c)) return null;
 }
 return a;
    }

    static boolean checkParens(ArrayList<String> a) {
      if (a == null) return false;
      int paren = 0;
      for (int i = 0; i < a.size(); i++) {
        if (a.get(i).equals("(")) paren++;
        else if (a.get(i).equals(")")) paren--;
        if (paren < 0) return false;
      }
      if (paren != 0) return false;
      return true;
    }
    
    static void readNegative(ArrayList<String> a) {
      for (int i = a.size()-2; i > 0; i--) {
        String s0 = a.get(i-1);
        String s1 = a.get(i);
        String s2 = a.get(i+1);
        if ("+".equals(s1) && OPERATORS.substring(2).indexOf(s0) >= 0) a.remove(i);
        else if ("-".equals(s1) && OPERATORS.substring(2).indexOf(s0) >= 0) {
          if ("(".equals(s2)) {
            a.add(i,"(");
            int j = i+3;
            int paren = 0;
            while (paren >= 0) {
              if ("(".equals(a.get(j))) paren++;
              else if (")".equals(a.get(j))) paren--;
              j++;
            }
            a.add(j,")");
          }
          else if (OPERATORS.indexOf(s2) == -1) {
            a.remove(i);
            a.set(i,-1*Double.parseDouble(a.get(i)) + "");
          }
        }
      }
    }
    
    static boolean operate(ArrayList<String> a, int f, int l) {
      if (f > l) return false;
      int n = checkOp(a,f,l);
      if (n == -1) return false;
      else l += n;
      int f0 = -1;
      int l0 = -1;
      boolean b = false;
      int paren = 0;
      for (int i = f; i <= l; i++) {
        if (!b) {
          if (a.get(i).equals("(")) {
            f0 = i+1;
            b = true;
          }
        }
        else {
          if (a.get(i).equals("(")) paren++;
          else if (a.get(i).equals(")")) paren--;
          if (paren == -1) {
            l0 = i-1;
            break;
          }
        }
      }
      if (f0 > -1) {
        if (!operate(a,f0,l0)) return false;
        l -= (l0 - f0);
        if (f0-2 >= 0 && OPERATORS.indexOf(a.get(f0-2)) == -1) a.set(f0-1,"*");
        else {
          a.remove(f0-1);
          f0--;
          l--;
        }
        if (f0+2 < a.size() && OPERATORS.indexOf(a.get(f0+2)) == -1) a.set(f0+1,"*");
        else {
          a.remove(f0+1);
          l--;
        }
        return operate(a,f,l);
      }
      n = exponent(a,f,l);
      if (n == -1) return false;
      else l -= n;
      n = multiply(a,f,l);
      if (n == -1) return false;
      else l -= n;
      n = add(a,f,l);
      if (n == -1) return false;
      else l -= n;
      return true;
    }
    
    static int checkOp(ArrayList<String> a, int f, int l) {
      int n = 0;
      String first = a.get(f);
      if (OPERATORS.substring(2,5).indexOf(first) >= 0) return -1;
      if (OPERATORS.substring(5).indexOf(first) >= 0) {
        a.add(f,"0");
        l++;
        n++;
      }
      String last = a.get(l);
      if (OPERATORS.substring(2).indexOf(last) >= 0) return -1;
      return n;
    }
    
    static int exponent(ArrayList<String> a, int f, int l) {
      int n = 0;
      for (int i = f+1; i < l; i++) {
        if (a.get(i).equals("^")) {
          if (OPERATORS.indexOf(a.get(i-1)) >= 0 || OPERATORS.indexOf(a.get(i+1)) >= 0) return -1;
          double x = Double.parseDouble(a.get(i-1));
          double y = Double.parseDouble(a.get(i+1));
          String z = Math.pow(x,y) + "";
          a.set(i-1,z);
          a.remove(i);
          a.remove(i);
          i--;
          l -= 2;
          n += 2;
        }
      }
      return n;
    }
    
    static int multiply(ArrayList<String> a, int f, int l) {
      int n = 0;
      for (int i = f+1; i < l; i++) {
        if (a.get(i).equals("*")) {
          if (OPERATORS.indexOf(a.get(i-1)) > -1 || OPERATORS.indexOf(a.get(i+1)) > -1) return -1;
          double x = Double.parseDouble(a.get(i-1));
          double y = Double.parseDouble(a.get(i+1));
          String z = x * y + "";
          a.set(i-1,z);
          a.remove(i);
          a.remove(i);
          i--;
          l -= 2;
          n += 2;
        }
        else if (a.get(i).equals("/")) {
          if (OPERATORS.indexOf(a.get(i-1)) > -1 || OPERATORS.indexOf(a.get(i+1)) > -1) return -1;
          double x = Double.parseDouble(a.get(i-1));
          double y = Double.parseDouble(a.get(i+1));
          if (y == 0) return -1;
          String z = x / y + "";
          a.set(i-1,z);
          a.remove(i);
          a.remove(i);
          i--;
          l -= 2;
          n += 2;
        }
      }
      return n;
    }
    
    static int add(ArrayList<String> a, int f, int l) {
      int n = 0;
      for (int i = f+1; i < l; i++) {
        if (a.get(i).equals("+")) {
          if (OPERATORS.indexOf(a.get(i-1)) > -1 || OPERATORS.indexOf(a.get(i+1)) > -1) return -1;
          double x = Double.parseDouble(a.get(i-1));
          double y = Double.parseDouble(a.get(i+1));
          String z = x + y + "";
          a.set(i-1,z);
          a.remove(i);
          a.remove(i);
          i--;
          l -= 2;
          n += 2;
        }
        else if (a.get(i).equals("-")) {
          if (OPERATORS.indexOf(a.get(i-1)) > -1 || OPERATORS.indexOf(a.get(i+1)) > -1) return -1;
          BigDecimal x = new BigDecimal(a.get(i-1));
          BigDecimal y = new BigDecimal(a.get(i+1));
          String z = x.subtract(y).doubleValue() + "";
          a.set(i-1,z);
          a.remove(i);
          a.remove(i);
          i--;
          l -= 2;
          n += 2;
        }
      }
      return n;
    }
    
    public static void main(String[] args) {
      while (true) {
        String s = evaluate(Keyboard.readString());
        System.out.println(s);
      }
    }
    
}
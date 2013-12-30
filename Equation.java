import cs1.Keyboard;
import java.math.BigDecimal;

class Equation {
  
  static String evaluate(String s) {
    s = s.replaceAll(" ","");
    s = s.replaceAll(",","");
    while (s.indexOf("y") >= 0)
      s = s.substring(0,s.indexOf("y")) + s.substring(s.indexOf("y")+1);
  if(s.substring(0,1).equals("="))
    s = s.substring(1);
    
    if (s.indexOf("(") >= 0 || s.indexOf(")") >= 0) return "Invalid input";
    
    String left = "";
    String right = "";
    int split = s.indexOf("=");
    if (split == -1) return "Invalid input";
    else {
      left = s.substring(0,split) + "+";
      right = s.substring(split+1) + "+";
    }
    if (left.equals("+") || right.equals("+")) return "Invalid input";
    
    String leftNum = "";
    String leftVar = "";
    String temp = "";
    for (int i = 0; i < left.length(); i++) {
      String c = left.substring(i,i+1);
      if ("+-".indexOf(c) >= 0) {
        int pos = temp.indexOf("x");
        if (pos == -1) leftNum += temp;
        else if (temp.length() > pos+1 && "*/".indexOf(temp.substring(pos+1,pos+2)) == -1) return "Invalid input";
        else {
          String leftVarTemp = temp.substring(0,pos);
          if (leftVarTemp.length() > 0) {
            if ("^/".indexOf(leftVarTemp.substring(leftVarTemp.length()-1)) >= 0) return "Invalid input";
            if ("+-".indexOf(leftVarTemp.substring(leftVarTemp.length()-1)) >= 0) leftVarTemp += "1";
            if ("*".equals(leftVarTemp.substring(leftVarTemp.length()-1)))
              leftVarTemp = leftVarTemp.substring(0,leftVarTemp.length()-1);
          }
          else leftVarTemp = "1";
          leftVar += leftVarTemp + temp.substring(pos+1);
        }
        temp = c;
      }
      else temp += c;
    }
    
    String rightNum = "";
    String rightVar = "";
    temp = "";
    for (int i = 0; i < right.length(); i++) {
      String c = right.substring(i,i+1);
      if ("+-".indexOf(c) >= 0) {
        int pos = temp.indexOf("x");
        if (pos == -1) rightNum += temp;
        else if (temp.length() > pos+1 && "*/".indexOf(temp.substring(pos+1,pos+2)) == -1) return "Invalid input";
        else {
          String rightVarTemp = temp.substring(0,pos);
          if (rightVarTemp.length() > 0) {
            if ("^/".indexOf(rightVarTemp.substring(rightVarTemp.length()-1)) >= 0) return "Invalid input";
            if ("+-".indexOf(rightVarTemp.substring(rightVarTemp.length()-1)) >= 0) rightVarTemp += "1";
            if ("*".equals(rightVarTemp.substring(rightVarTemp.length()-1)))
              rightVarTemp = rightVarTemp.substring(0,rightVarTemp.length()-1);
          }
          else rightVarTemp = "1";
          rightVar += rightVarTemp + temp.substring(pos+1);
        }
        temp = c;
      }
      else temp += c;
    }
    
    if (leftNum.equals("")) leftNum = "0";
    if (leftVar.equals("")) leftVar = "0";
    if (rightNum.equals("")) rightNum = "0";
    if (rightVar.equals("")) rightVar = "0";
    BigDecimal leftNumVal = null;
    String val = Operate.evaluate(leftNum);
    if (val.equals("Invalid input")) return val;
    else leftNumVal = new BigDecimal(val);
    BigDecimal leftVarVal = null;
    val = Operate.evaluate(leftVar);
    if (val.equals("Invalid input")) return val;
    else leftVarVal = new BigDecimal(val);
    BigDecimal rightNumVal = null;
    val = Operate.evaluate(rightNum);
    if (val.equals("Invalid input")) return val;
    else rightNumVal = new BigDecimal(val);
    BigDecimal rightVarVal = null;
    val = Operate.evaluate(rightVar);
    if (val.equals("Invalid input")) return val;
    else rightVarVal = new BigDecimal(val);
    
    double num = leftNumVal.subtract(rightNumVal).doubleValue();
    double var = rightVarVal.subtract(leftVarVal).doubleValue();
    if (var == 0) return "Invalid input";
    return Operate.evaluate(num / var + "");
  }
  
  public static void main(String[] args) {
    while (true) {
      String s = evaluate(Keyboard.readString());
      System.out.println(s);
    }
  }
  
}
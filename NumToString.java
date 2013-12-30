import cs1.Keyboard;
import java.util.ArrayList;

public class NumToString {
  private static final String[] stringys = {"one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fifteen","teen","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety","hundred","thousand","million","billion","tenths","hundredths","thousandths","ten-thousandths","hundred-thousandths","millionths","billionths"};
 
  public static String stringify(double s){
    boolean neg = false;
    if(s < 0){
      neg = true;
      s *= -1;
    }
    String input = s + "";
double dec = Double.parseDouble(input.substring(input.indexOf(".") + 1));
    int esy = (int) s;
    String num = "";
if (esy == 0) num = "zero ";
    ArrayList<Integer> poskeeper = new ArrayList<Integer>();
    while(esy > 0){
      poskeeper.add(esy % 10);
      esy /= 10;
    }
    for(int i = 0; i < poskeeper.size(); i++){
      if(i % 3 == 2 && poskeeper.get(i) != 0)
        num = stringys[23] + " " + num;
      if(i == 3){
        if(poskeeper.get(i) != 0)
          num = stringys[24] + " " + num;
        else if(poskeeper.get(i + 1) != 0)
          num = stringys[24] + " " + num;
        else if(poskeeper.get(i + 2) != 0)
          num = stringys[24] + " " + num;
      }
      if(i == 6){
        if(poskeeper.get(i) != 0)
          num = stringys[25] + " " + num;
        else if(poskeeper.get(i + 1) != 0)
          num = stringys[25] + " " + num;
        else if(poskeeper.get(i + 2) != 0)
          num = stringys[25] + " " + num;
      }
      if(i == 9){
        if(poskeeper.get(i) != 0)
          num = stringys[26] + " " + num;
        else if(poskeeper.get(i + 1) != 0)
          num = stringys[26] + " " + num;
        else if(poskeeper.get(i + 2) != 0)
          num = stringys[26] + " " + num;
      }
      
      if(i % 3 == 0 && poskeeper.get(i) == 0 && poskeeper.get(i + 1) == 1){
        num = stringys[9] + " " + num;
        i++;
      }
      else if (poskeeper.get(i) == 0)
        num = num;
      else if((i % 3 == 0 && (i + 1 == poskeeper.size() || poskeeper.get(i+1) != 1)) || i % 3 == 2)
        num = stringys[poskeeper.get(i) - 1] + " " + num;
      else if(i % 3 == 0 && poskeeper.get(i+1) == 1 && poskeeper.get(i) < 4){
        num = stringys[poskeeper.get(i) + 9] + " " + num;
        i++;
      }
      else if(i % 3 == 0 && poskeeper.get(i+1) == 1 && poskeeper.get(i) == 5){
        num = stringys[13] + " " + num;
        i++;
      }
      else if(i % 3 == 0 && poskeeper.get(i+1) == 1){
        num = stringys[poskeeper.get(i) - 1] + stringys[14] + " " + num;
        i++;
      }
      else if(i % 3 == 1){
        if(poskeeper.get(i - 1) != 0)
          num = "-" + num;
        else
          num = " " + num;
        num = stringys[poskeeper.get(i) + 13] + num;
        
      }
    }
    
    if(dec > 0){
      if(dec > 1000000000){
        String tmp = dec + "";
        dec = Double.parseDouble(tmp.substring(0,8));
      }
      num += "and " + stringify(dec);
      int places = 1;
      while(dec / Math.pow(10,places) > 1){
        places++;
      }
      if(dec != 0)
     num += stringys[places + 26];
    }
    if (neg == true) num = "negative " + num;
    return num;
  }
  
  
  
  
  public static void main(String[] args) {
    NumToString crabs = new NumToString();
    double i = Double.parseDouble(Keyboard.readString());
    System.out.println(crabs.stringify(i));
  }
}
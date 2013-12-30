import java.util.ArrayList;
public class Polynomials{
 //public char variable; (currently assume x)
 //public double first, second, third, fourth;
 public int[] temp;
 public Polynomials(String s){
   s = s.replaceAll("\\s","");
   if(s.indexOf("y=") > -1)
     s = s.substring(0,s.indexOf("y=")) + s.substring(s.indexOf("y=") + 2);
   temp = splitter(s); 
 }
 public int[] splitter(String s){
  int pos = 0;
  int last = 0;
  boolean neg = false;
  int[] rtn = new int[4];
  while(pos < s.length()){
   String value = s.substring(pos, pos+1);
   if((pos == s.length() - 1)){
    int temp =explvl(s.substring(last, pos+1));
    int set = setter(s.substring(last,pos+1));
    rtn[temp] = set;
    if(neg){
     rtn[temp] = -1 * set;
     neg = false;
    }
   }
   if((pos > 0) &&(value.equals("+")||value.equals("-"))){
   //System.out.println("string:"+s.substring(last, pos));
   int temp =explvl(s.substring(last, pos));
   //System.out.println("temp: "+temp);
   int set = setter(s.substring(last,pos));
   //System.out.println("set: "+temp);
   rtn[temp] = set;
   if(neg){
    rtn[temp] = -1 * set;
    neg = false;
   }
    //rtn[explvl(s.substring(last, pos))] = setter(s.substring(last, pos));
   last = pos + 1; 
   //System.out.println("pos:"+pos+" last:"+last);
   if(value.equals("-"))
    neg = true;
   }
   pos++;
  }
  return rtn;
 }
 public int explvl(String s){
  //System.out.println("explvl:"+ s);
  int exppos = s.indexOf("^");
  int varpos = s.indexOf("x");
  //System.out.println("exppos:"+ exppos +" varpos: "+ varpos);
  if(exppos == -1 && varpos == -1)
   return 3;
  else if(exppos == -1){
   return 2;
  }
  else{
   //System.out.println(exppos);
   return 3 - Integer.parseInt(s.substring(exppos + 1, exppos+2));
  }
 }
 public int setter(String s){
  int varpos = s.indexOf("x");
  if(varpos == -1)
   return Integer.parseInt(s);
  else if(varpos == 0)
   return 1;
  else{
   return Integer.parseInt(s.substring(0,varpos));
  }
 }
 public String toString(){
  String rtn = "";
  for(int a: temp){
   rtn+= a+" ";
  }
  return rtn;
 }
 public void quadroots(){
    System.out.println("root 1:"+(((-1 * temp[2])) + Math.sqrt(( temp[2] * temp[2]) - (4 * temp[1] * temp[3]))) / (2 * temp[1]));
 System.out.println("root 2:"+(((-1 * temp[2])) - Math.sqrt(( temp[2] * temp[2]) - (4 * temp[1] * temp[3]))) / ( 2 * temp[1]));
 }
 public double root1(){
   if(temp[1] == 0)
     return ((double)temp[3]) / ((double)temp[2]) * -1;
   else
  return (((-1 * temp[2])) + Math.sqrt(( temp[2] * temp[2]) - (4 * temp[1] * temp[3]))) / (2 * temp[1]);
 }
 public double root2(){
   if(temp[1] == 0)
     return ((double)temp[3]) / ((double)temp[2]) * -1;
   else
  return (((-1 * temp[2])) - Math.sqrt(( temp[2] * temp[2]) - (4 * temp[1] * temp[3]))) / (2 * temp[1]);
 }
 public ArrayList<Integer> getPoints(){
  ArrayList<Integer> points = new ArrayList<Integer>();
  for(int xcor = -10; xcor < 11; xcor++){
   points.add(temp[0]*xcor*xcor*xcor+temp[1]*xcor*xcor+ temp[2]*xcor + temp[3]);
  }
  return points;
 }
 public static void main(String[] args){
  Polynomials test = new Polynomials("x^2 +5x +5");
  System.out.println(test);
  test.quadroots();
 } 
}

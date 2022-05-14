package springboot.spring_firstapp;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.lang.Math;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/print")
public class welcme {
@GetMapping("/Welcome")
  public String calculate(@RequestParam String expression ) {
    expression= expression.replace(' ','+');
    //square root symbol and multiplication sign
    char root='\u221A';
    char multiply='\u00D7';
    expression= expression.replace(root,'r');
    expression= expression.replace(multiply,'*');
    double answer=0;
    String expression1=expression;
    //check if square root or negative is the first character
    // to avoid empty cells in  beginning of array
    if(expression.charAt(0)=='r'||expression.charAt(0)=='-'){
     expression1=expression.substring(1);}
    if((expression.charAt(0)=='(')&&(expression.charAt(1)=='-')){
        expression1=expression.substring(2);
    }
    else if(expression.contains("(-")){
        for(int j=0;j<expression.length();j++){if(expression.charAt(j)=='('){
            expression1=expression1.substring(0,j-1)+expression1.substring(j+1);
            System.out.println(expression1);
            break;
        }}
    }
    //array of operands without operations
    String[] s = expression1.split("[+/*r%^(-]");;
    //to check if there is empty places due to (- in the array and remove them
    for(int i=0;i<s.length;i++){if(s[i].isEmpty()){System.out.println(s[i+1]+ "n"+ s.length); s[i]=s[i+1];}}


    double[] arr = new double[s.length];
    if (s.length == 1 && s[0].isEmpty())
        arr = new double[]{};
    else {
        for(int i = 0; i < s.length; ++i)
            arr[i] = Double.parseDouble(s[i]);
    }
    int n=arr.length;

    String answrr=null;
    //determining operations of the expression
    if(expression.charAt(0)=='-'||(expression.charAt(0)=='('&&expression.charAt(1)=='-'))
                      {  arr[0]=-arr[0];}

    if(expression.contains("-(-")){ answer=(arr[0]+arr[1]); answrr=Double.toString(answer);}
    else if(expression.contains("+(-")){ answer=arr[0]-arr[1]; answrr=Double.toString(answer);}
    else if(expression.contains("*(-")){answer=arr[0]*(-arr[1]); answrr=Double.toString(answer);}
    else if(expression.contains("/(-")){
        if (arr[1]==0){    answrr="E"; }else
        {arr[1]=-arr[1]; answer=arr[0]/arr[1]; answrr=Double.toString(answer); }}
    else  if(n>1||(n==1&&expression.contains("%"))||(n==1&&expression.contains("r"))){

         for(char w: expression.toCharArray()){


             if(w=='+'){ answer=arr[0]+arr[1]; }
             else if(w=='%'){ answer=arr[0]/100.000; }
             else if(w=='^'){ answer=Math.pow(arr[0],2); }
             else if(w== 'r'){ answer=Math.sqrt(arr[0]); }
             else if(w=='-'){ answer=arr[0]-arr[1]; }
             else if(w=='*'){ answer=arr[0]*arr[1];}
             else if(w=='/'){ if (arr[1]==0){    answrr="E";
                 System.out.println("Error"); break;}
                     answer=arr[0]/arr[1];}

             answrr=Double.toString(answer);
    }}else if(n==1&&expression.contains("(-")){answer=-arr[0]; answrr=Double.toString(answer);}
    else{answrr=expression;}


	  return answrr;
  }
}

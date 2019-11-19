import java.util.Stack;

/*
栈里先放数字再放符号，弹出先弹符号再弹数字
(1+(4+5+2)-3)+(6+8)
'('--->|    | res=0,sign=1
       |  1 |
       |__0_|
  1--->res=1
'+'---> sign=1
'('--->|   |
       | 1 |
       | 1 |
       | 1 |
       | 0 | res=0,sign=1
4--->res=4
'+'--->sign=1
5--->res=4+5=9
'+'--->sign=1
2---->res=9+2=11
')'--->res=11*1+1=12
'-'--->sign=-1
3--->res=12+(-1)*3=9
')'--->res=9*1+0=9
'+'--->sign=1
'('--->|   |
       | 1 |
       | 9 | res=0,sign=1
6--->res=0+1*6=6
'+'--->sign=1
8--->res=6+1*8=14
')'--->res=14*1+9=23
*/

/*
public class Solution {
public int calculate(String s) {
    int len;
    if(s==null || (len = s.length())==0) return 0;
    Stack<Integer> stack = new Stack<Integer>();
    int num = 0;
    char sign = '+';
    for(int i=0;i<len;i++){
        if(Character.isDigit(s.charAt(i))){
            num = num*10+s.charAt(i)-'0';
        }
        if((!Character.isDigit(s.charAt(i)) &&' '!=s.charAt(i)) || i==len-1){
            if(sign=='-'){
                stack.push(-num);
            }
            if(sign=='+'){
                stack.push(num);
            }
            if(sign=='*'){
                stack.push(stack.pop()*num);
            }
            if(sign=='/'){
                stack.push(stack.pop()/num);
            }
            sign = s.charAt(i);
            num = 0;
        }
    }

    int re = 0;
    for(int i:stack){
        re += i;
    }
    return re;
}
}
 */


public class Calculator {
    public int calculate(String s) {
        Stack<Integer> stack=new Stack<>();
        int sign=1;
        int res=0;
        for(int i=0;i<s.length();i++){
            if(Character.isDigit(s.charAt(i))){
                int num=s.charAt(i)-'0';
                while(i+1<s.length()&&Character.isDigit(s.charAt(i+1))){
                    num=num*10+s.charAt(i+1)-'0';
                    i++;
                }
                res+=num*sign;
            }else if(s.charAt(i)=='+'){
                sign=1;
            }else if(s.charAt(i)=='-'){
                sign=-1;
            }else if(s.charAt(i)=='('){
                stack.push(res);
                stack.push(sign);
                res=0;
                sign=1;
            }else if(s.charAt(i)==')'){
                res=res*stack.pop()+stack.pop();
            }
        }
        return res;
    }
}

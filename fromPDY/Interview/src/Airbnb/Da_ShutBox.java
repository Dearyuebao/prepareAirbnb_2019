package Airbnb;
import java.util.*;
public class Da_ShutBox {
    //boolean[] memo = new boolean[10];//save 1 - 9 condition
    HashSet<Integer> candidate;
    //boolean[] winState = new boolean[10];
    public Da_ShutBox(){
        candidate = new HashSet<>();
        for(int i = 1; i <= 9;i ++){
            //memo[i] = true;
            candidate.add(i);
        }
    }
    private int rand(){
        return (int)(Math.random() * 6) + 1;
    }

    public boolean solution2(){
        while(candidate.size() > 0){
            int rand1 = rand();
            int rand2 = rand();
            int sum = rand1 + rand2;
            int res1= -1, res2 = -1;
            if(candidate.contains(sum)){
                candidate.remove(sum);
                continue;
            }
            for(int key:candidate){
                if(candidate.contains(sum - key)){
                    res1 = key;
                    res2 = sum - key;
                    break;
                }
            }
            if(res1 < 0 || res2 < 0) return false;
            candidate.remove(res1);
            candidate.remove(res2);
        }
        return true;
    }
    public boolean solution1(){
        while(candidate.size() > 0){
            int rand1 = rand();
            int rand2 = rand();
            int sum = rand1 + rand2;
            int res1= -1, res2 = -1;
            for(int key:candidate){
                if(candidate.contains(sum - key)){
                    res1 = key;
                    res2 = sum - key;
                    break;
                }
            }
            if(res1 < 0 || res2 < 0) return false;
            candidate.remove(res1);
            candidate.remove(res2);
        }
        return true;

    }


}

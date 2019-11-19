/*
1.我没见过，如果大佬知道原题可以说出来。给一个 数字string，和一个lowerbound，
给出string重新排列形成的数字中不小于lowerbound的最小的数。
不能用brute force 或优化的brute force。
显然用递归，不过lowerbound的范围不定，一开始考虑到位数问题慌了，递归写的尤为复杂，没有写完。
关上coderpad后冷静想了一下
实际上应该先判断lowerbound的位数，大于string就无解，不足就前补0.
之后string sort一下，一位一位比，如果string相应位更大则直接return，如果更小就bisect出剩余子串中大于等于lowerbound对应位的数
1.如果是大于可以直接把该数移过来return
2. 如果不存在则reuturn 无解
3.等于则继续递归，看相等是否有解，无解就根据步骤1来
整体是nlogn。
 */
package Airbnb;
import java.util.*;
public class Df_lowerbound {
    public int solve(String input, int bound){
        if(String.valueOf(bound).length() > input.length()) return  -1;
        String model = String.valueOf(bound);
        String copy = new String(model);
        for(int i = 0; i < input.length() - model.length();i++ ){
            copy = "0" + copy;
        }
        model = copy;
        int[] elem = new int[10];
        for(int i = 0; i < input.length();i++){
            elem[input.charAt(i) - '0'] ++;
        }
        int[] test = Arrays.copyOf(elem,elem.length);
        if(Integer.valueOf(findMax(test)) < bound) return -1;
        int diff = 0;
        List<Character> res = new ArrayList<>();

        for(int i = 0;i < input.length();i++){
            char c = ' ';
            for(int j = model.charAt(i) - '0' + diff ;j < 10;j++){
                if(elem[j] > 0) {
                    elem[j] --;
                    c = (char)(j + '0');
                    break;
                }
            }
            if(c != ' '){//前进
                res.add(c);
                if(c > model.charAt(i)){
                    break;
                }
            }
            else{//回退
                diff = 1;i -= 2;
                char del = res.get(res.size() - 1);
                elem[del - '0'] ++;
                res.remove(res.size() - 1);
            }
        }
        StringBuilder part1 = new StringBuilder();
        String part2 = findMin(elem);
        for(char c:res) part1.append(c);
        return Integer.valueOf(part1.append(part2).toString());
    }

    private String findMin(int[] elem){
        String res = "";
        for(int i = 0; i < elem.length;i++){
            while(elem[i] > 0){
                elem[i] --;
                res += i;
            }
        }
        return res;
    }
    private String findMax(int[] elem){
        String res = "";
        for(int i = elem.length - 1; i >= 0;i--){
            while(elem[i] > 0){
                elem[i] --;
                res += i;
            }
        }
        return res;
    }


}

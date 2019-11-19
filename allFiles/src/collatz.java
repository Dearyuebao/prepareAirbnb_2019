//public class collatz {
//}
import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

//for this question, I think I can use recursive to solve it
//for each number no greater than input, we have to find the length of longest path that come to 1
//I will use a map to store the length we have already calculated to save time(like cache)
//and use recursive to count the steps come to zero
class collatz{
    static Map<Integer, Integer> map = new HashMap<>();

    public static int findLongest(int num) {
        int res = 1;
        map.put(1, 1);
        if(num <= 1) return 1;
        for(int i = 2; i <= num; i++) {
            int curr = steps(i, i);
            map.put(i, curr);
            if(curr > res) {
                res = curr;
            }
        }
        return res;
    }

    public static int steps(int origin, int n) {
        if(map.containsKey(n)) return map.get(n);
        //if it is even num
        if(n % 2 == 0) return 1 + steps(origin, n / 2);
        //if it is odd number
        else return 1 + steps(origin, n * 3 + 1);
    }


    //follow up: print path
    //use a list to store the result path
    static List<Integer> path = new ArrayList<>();
    //use a map to store path of each number
    static Map<Integer, List<Integer>> eachPath = new HashMap<>();
    public static int findLongestFollow(int num) {
        int res = 1;
        map.put(1, 1);
        eachPath.put(1, new ArrayList<>());
        eachPath.get(1).add(1);
        if(num <= 1) {
            path.add(1);
            return 1;
        }
        for(int i = 2; i <= num; i++) {
            eachPath.put(i, new ArrayList<>());
            int curr = stepsFollow(i, i);
            map.put(i, curr);
            if(curr > res) {
                res = curr;
                //if we encounter a longer one, we update the result path
                path.clear();
                path.addAll(eachPath.get(i));
            }
        }
        return res;
    }

    public static int stepsFollow(int origin, int n) {
        if(map.containsKey(n)){
            //if we have this number in our map(cache), we add its path to the origin one
            eachPath.get(origin).addAll(eachPath.get(n));
            return map.get(n);
        } else {
            //if we meet a new number, we add this number to the origin's path
            eachPath.get(origin).add(n);
        }
        if(n % 2 == 0) return 1 + stepsFollow(origin, n / 2);
        else return 1 + stepsFollow(origin, n * 3 + 1);
    }

    public static void main(String[] args) {
        System.out.println(findLongestFollow(100));
        System.out.println(path);
    }
}



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class collatz {
    static Map<Integer, Integer> map = new HashMap<>();
    static List<Integer> path = new ArrayList<Integer>();
    static Map<Integer, List<Integer>> eachPath = new HashMap<>();
    public static int findLongest(int num) {
        int res = 1;
        map.put(1, 1);
        eachPath.put(1, new ArrayList<>());
        eachPath.get(1).add(1);
        if(num <= 1) {
            path.add(num);
            return 1;
        }
        for(int i = 2; i <= num; i++) {
            eachPath.put(i, new ArrayList<>());
            int curr = steps(i, i);
            map.put(i, curr);
            if(curr > res) {
                res = curr;
                //take care clear
                path.clear();
                path.addAll(eachPath.get(i));
            }
        }
        return res;
    }

    public static int steps(int origin, int n) {
        if(map.containsKey(n)) {
            eachPath.get(origin).addAll(eachPath.get(n));
            return map.get(n);
        } else {
            eachPath.get(origin).add(n);
        }
        if(n % 2 == 0) return 1 + steps(origin, n / 2);
        else return 1 + steps(origin, n * 3 + 1);
    }

    public static void main(String[] args) {
        System.out.println(findLongest(7));
       // System.out.println(findLongest(1000));
        System.out.println(path);
    }
}

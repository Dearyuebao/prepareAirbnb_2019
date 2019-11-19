package Airbnb;

import java.util.HashMap;

public class Cf_CollatzConjecture {
    public static int findLongestSteps (int limit) {
        if (limit < 1) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 1; i <= limit; i++) {
            max = Math.max(max, helper(i, map));
        }
        return max;
    }
    public static int helper (int num, HashMap<Integer, Integer> map) {
        if (num <= 1) return 1;
        if (map.containsKey(num)) return map.get(num);
        int res = 0;
        if (num % 2 == 0) res = helper(num / 2, map) + 1;
        else res = helper(num * 3 + 1, map) + 1;
        map.put(num, res);
        return res;
    }
}

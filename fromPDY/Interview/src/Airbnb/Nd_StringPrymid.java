package Airbnb;
import java.util.*;
public class Nd_StringPrymid {
    Map<String, Set<Character>> map;
    private Map<String, Boolean> cache;
    final String SEP = "###";
    Nd_StringPrymid(String[] line) {
        cache = new HashMap<>();
        map = new HashMap<>();
        for (String s : line) {
        String[] splitted = s.split(",");
        String key = splitted[0] + SEP + splitted[1];
        Set<Character> set = new HashSet<>();
        for (char c : splitted[2].toCharArray())
            set.add(c); map.put(key, set);
    }
    }
    private void getNextLevel(List<String> res, String curr, int start, StringBuilder sb) {
        if (start == curr.length()-1) { res.add(new String(sb));
        return;
        }
        for (int i=start; i<curr.length()-1; i++) {
            String key = curr.charAt(i) + SEP + curr.charAt(i+1);
            for (char c : map.get(key)) {
                sb.append(c);
                getNextLevel(res, curr, start+1, sb);
                sb.setLength(sb.length() - 1);
            }
        }
    }
    private boolean search(String input, String current) {
        if (cache.containsKey(input))
            return cache.get(input);
        if (current.length() == 1) {
            cache.put(current, input.contains(current));
            return cache.get(current); }
        List<String> cands = new ArrayList<>();
        getNextLevel(cands, current, 0, new StringBuilder()); for (String cand : cands) {
// System.out.println(cand);
            if (cache.containsKey(cand)) return cache.get(cand);
            boolean res = search(input, cand);
            if (res) {
                cache.put(cand, true);
                return true;
            }
        }
        return false;
    }
    public boolean check(String input) {
        cache.clear();
        return search(input, input);
    }
}

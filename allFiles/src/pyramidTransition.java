import java.util.*;

public class pyramidTransition {
    static class Pyramid{
        Set<Character> rule = new HashSet<>();
        Map<Character, Map<Character, Set<Character>>> graph = new HashMap<>();
        Map<String, Boolean> cache;

        public Pyramid(String[] lines, String dst) {
            for(char each : dst.toCharArray()) {
                rule.add(each);
            }
            for(String eachString: lines) {
                String[] part = eachString.split(",");
                char left = part[0].charAt(0);
                char right = part[1].charAt(0);
                if(!graph.containsKey(left)) {
                    graph.put(left, new HashMap<>());
                }
                graph.get(left).put(right, new HashSet<>());
                for(int i = 0; i < part[2].length(); i++) {
                    graph.get(left).get(right).add(part[2].charAt(i));
                }
            }
            cache = new HashMap<>();
        }

        public boolean check(String input) {
            if(cache.containsKey(input)) {
                return cache.get(input);
            }
            if(input.length() == 1) {
                cache.put(input, rule.contains(input.charAt(0)));
                return cache.get(input);
            }
            List<String> nextLevel = new ArrayList<>();
            helper(input, nextLevel, 0, new StringBuilder());
            for(String each : nextLevel) {
                if(check(each)) {
                    cache.put(input, true);
                    return true;
                }
            }
            cache.put(input, false);
            return false;
        }

        public void helper(String input, List<String> nextLevel, int start, StringBuilder sb) {
            if(start == input.length() - 1) {
                nextLevel.add(sb.toString());
                return;
            }
            char left = input.charAt(start);
            char right = input.charAt(start + 1);
            for(char c : graph.get(left).get(right)) {
                sb.append(c);
                helper(input, nextLevel, start + 1, sb);
                sb.setLength(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        String[] lines = {"A,A,AC", "A,B,CD", "A,C,D","A,D,B","B,A,B","B,B,C","B,C,A","B,D,CD","C,A,A","C,B,C","C,C,D","C,D,B","D,A,BC","D,B,D","D,C,A","D,D,C"};
        Pyramid py = new Pyramid(lines, "D");
        Pyramid py2 = new Pyramid(lines, "ABCD");

        System.out.println(py.check("ABC"));
        System.out.println(py.check("AACC"));
        System.out.println(py.check("AAAA"));

        System.out.println(py2.check("ABC"));
        System.out.println(py2.check("AACC"));
        System.out.println(py2.check("AAAA"));

    }
}

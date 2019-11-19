import java.util.*;

public class alienDictionary {
    public static String alienOrder(String[] words) {
        if(words == null || words.length == 0) return "";
        Map<Character, HashSet<Character>> map = new HashMap<>();
        int[] indegree = new int[26];
        buildGraph(words, map, indegree);
        return bfs(map, indegree);
    }

    public static void buildGraph(String[] words, Map<Character, HashSet<Character>> map, int[] indegree) {
        for(String eachString: words) {
            for(char eachChar: eachString.toCharArray()) {
                map.putIfAbsent(eachChar, new HashSet<Character>());
            }
        }
        for(int i = 1; i < words.length; i++) {
            String first = words[i - 1];
            String second = words[i];
            int len = Math.min(first.length(), second.length());
            for(int j = 0; j < len; j++ ) {
                char a = first.charAt(j);
                char b = second.charAt(j);
                if(a != b) {
                    //take care
                    if(!map.get(a).contains(b)) {
                        map.get(a).add(b);
                        indegree[b - 'a']++;
                    }
                    break;
                }
            }
        }
    }
    

    public static String bfs(Map<Character, HashSet<Character>> map, int[] indegree) {
        int total = map.size();
        StringBuilder res = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        for(char each: map.keySet()) {
            if(indegree[each - 'a'] == 0) {
                res.append(each);
                q.offer(each);
            }
        }

        while(!q.isEmpty()) {
            char curr = q.poll();
            //take care
            if(map.get(curr) == null || map.get(curr).size() == 0) continue;
            for(char each : map.get(curr)) {
                indegree[each - 'a']--;
                if(indegree[each - 'a'] == 0) {
                    res.append(each);
                    q.offer(each);
                }
            }
        }
        return res.length() == total? res.toString():"";
    }

    public static void main(String[] args) {
        String[] dic = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(alienOrder(dic));
    }
}


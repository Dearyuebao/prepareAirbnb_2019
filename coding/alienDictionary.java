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


// package Airbnb;
// import java.util.*;
// /*
// //time: O(v + E) k平均长度
// Use topological sort to slove the question,
// Use map to save the input,The Key is the character,
// the value is HashSet contains characters smaller
// than the key, use a array to save the indegree
// of every node Traverse the indegree array, when
// the indegree of the node is 0, offer the character
// to a queue, every time, pop a character from
// the queue, and the indegree of it should minus 1,
// when the indegree of it is 0, offer the node to
// the queue, every time append the character
// pop from the queue with res, finally, I can get the res.
// */

// //For this question, we have to sort the letters, and if it is directed acyclic graph we could successful sort it, if it has a circle
// //we will return an empty string. so I will use topological sort.
// //we have to record the indegree of each nodes, the indegree represents how many edges come to this node
// //each time we add the nodes whose indegree is zero

// //if we have to print path, I will use dfs.
// //
// public class Af_AlienDictionary {
//     public static String alienOrder(String[] words) {
//         // Write your code here
//         if (words== null || words.length == 0) return "";
//         HashMap<Character, HashSet<Character>> map = new HashMap<>();
//         int[] indegree = new int[26];
//         int count = 0;
//         for (String word : words) {
//             char[] ch = word.toCharArray();
//             for (char c : ch) {
//                 if (indegree[c - 'a'] == 0) {
//                     indegree[c - 'a'] = 1;
//                     count++;
//                 }
//             }
//         }
//         for (int i = 0; i < words.length; i++) {
//                 for (int j = 0; j < words[i].length(); j++) {
//                     map.putIfAbsent(words[i].charAt(j), new HashSet<>());
//                     if (j == 0) {
//                         if (i > 0 && words[i].charAt(j) != words[i - 1].charAt(j)) {
//                             map.get(words[i].charAt(j)).add(words[i - 1].charAt(j));
//                         }
//                     } else {
//                         if (i > 0 &&
//                                 words[i - 1].length() > j &&
//                                 words[i].charAt(j - 1) == words[i - 1].charAt(j - 1) &&
//                                 words[i].charAt(j) != words[i - 1].charAt(j)) {
//                             if (map.get(words[i].charAt(j)).add(words[i - 1].charAt(j))){
//                             indegree[words[i - 1].charAt(j) - 'a'] ++;
//                             }
//                         }
//                     }
//                 }
//             }
//         Queue<Character> queue = new LinkedList<>();
//         for (int i = 0; i < 26; i++) {
//             if (indegree[i] == 1) {
//                 queue.offer((char)(i + 'a'));
//             }
//         }
//         StringBuilder res = new StringBuilder();
//         while (!queue.isEmpty()) {
//             char cur = queue.poll();
//             res.append(cur);
//             if (map.containsKey(cur)) {
//                 for (char c : map.get(cur)) {
//                     indegree[c - 'a']--;
//                     if (indegree[c - 'a'] == 1) {
//                         queue.offer(c);
//                     }
//                 }
//             }
//         }
//         if (res.length() != count) return "";
//         return res.toString();
//     }
// }

/*
print path: When there are some character have no
relationship with other character, there are several
answer, so it's like a conditional permutation, the
condition is the order we already know from the
dictionary, so I can use backtracking to do it,

public class F_AlienDictionary {
        public String alienOrder(String[] words) {

            HashMap<Character, HashSet<Character>> map = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                for (int j = 0; j < words[i].length(); j++) {
                    map.putIfAbsent(words[i].charAt(j), new HashSet<>());
                    if (j == 0) {
                        if (i > 0 && words[i].charAt(j) != words[i - 1].charAt(j)) {
                            map.get(words[i].charAt(j)).add(words[i - 1].charAt(j));
                        }
                    } else {
                        if (i > 0 &&
                                words[i - 1].length() > j &&
                                words[i].charAt(j - 1) == words[i - 1].charAt(j - 1) &&
                                words[i].charAt(j) != words[i - 1].charAt(j)) {
                            map.get(words[i].charAt(j)).add(words[i - 1].charAt(j));
                        }
                    }
                }
            }
            List<String> res = new ArrayList<>();
            helper(map, new StringBuilder(), res);
            for (String s : res) System.out.println(s);
            return res.get(0);
        }
        private void helper(HashMap<Character,HashSet<Character>> map, StringBuilder elem, List<String> res){
            if(map.keySet().size() == 0){//map清空就走完了
                res.add(new String(elem.toString()));return;
            }
            for(char key:map.keySet()){
                if(map.get(key).size() == 0){//找入度为0的
                    HashMap<Character,HashSet<Character>> copy = new HashMap<>();//copy map，实现回溯
                    for(char k:map.keySet()) copy.put(k, new HashSet<>(map.get(k)));
                    copy.remove(key);//把入度为0的用掉
                    for(char k:copy.keySet()){//移除含有入度为零的set的key
                        if(copy.get(k).contains(key)){
                            copy.get(k).remove(key);
                        }
                    }
                    elem.append(key);//返回的路径
                    helper(copy,elem,res);
                    elem.delete(elem.length() - 1,elem.length());//移除路径
                }
            }
        }
}
*/



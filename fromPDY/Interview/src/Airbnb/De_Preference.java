package Airbnb;
import java.util.*;
/*
Follow up: 用一个 map来存储优先级，把 queue改成PriorityQueue 根据优先级出来
 */
public class De_Preference {
    public List<Integer> getPreference(List<List<Integer>> preferences) {
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Set<Integer>> nodes = new HashMap<>();
//        Map<Integer, Integer> prefer = new HashMap<>();
//         for (int i = 0; i < preferences.size(); i++) {
//           for (int j = 0; j < preferences.get(i).size(); j++) {
//             if (!prefer.containsKey(preferences.get(i).get(j))) {
//               prefer.put(preferences.get(i).get(j), i);
//             }
//           }

//         }
        for (List<Integer> l : preferences) {
            for (int i = 0; i < l.size() - 1; i++) {
                int from = l.get(i);
                int to = l.get(i + 1);
                if (!nodes.containsKey(from)) {
                    inDegree.put(from, 0);
                    nodes.put(from, new HashSet<>());
                }
                if (!nodes.containsKey(to)) {
                    inDegree.put(to, 0);
                    nodes.put(to, new HashSet<>());
                }
                if (!nodes.get(from).contains(to)) {
                    Set<Integer> s = nodes.get(from); s.add(to);
                    nodes.put(from, s);
                }
                inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
            }
        }
        Queue<Integer> q = new LinkedList<>();
        //PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> prefer.get(a) - prefer.get(b));
        for (int k : inDegree.keySet()) {
            if (inDegree.get(k) == 0) {
                q.offer(k);
            }
        }
        List<Integer> res = new ArrayList<>(); while (!q.isEmpty()) {
            int id = q.poll();
            res.add(id);
            Set<Integer> neighbors = nodes.get(id);
            for (int next : neighbors) {
                int degree = inDegree.get(next) - 1;
                inDegree.put(next, degree);
                if (degree == 0) q.offer(next);
            }
        }
        return res;
    }
}

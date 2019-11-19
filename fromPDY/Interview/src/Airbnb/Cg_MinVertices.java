package Airbnb;
import java.util.*;
public class Cg_MinVertices {
    public static List<Integer> getMin(int[][] edges, int n) {
        Map<Integer, List<Integer>>  map = new HashMap<>();
        int[] indegree = new int[n];
        boolean[] visited = new boolean[n];
        List<Integer> res = new ArrayList<>();
        for (int[] e : edges) {
            if (!map.containsKey(e[0])) {
                map.put(e[0], new ArrayList<>());
            }
            map.get(e[0]).add(e[1]);
            indegree[e[1]]++;
        }

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                res.add(i);
                dfs(map, visited, i);
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                res.add(i);
                dfs(map, visited, i);
            }
        }
        return res;
    }
    public static void dfs (Map<Integer, List<Integer>> map, boolean[] visited, int cur) {
        visited[cur] = true;
        if (map.containsKey(cur)) {
            for (int next : map.get(cur)) {
                if (visited[next]) continue;
                dfs(map, visited, next);
            }
        }
    }
    public static void main (String args[]) {
        int[][] edges = {{2,9},{3,3},{3,5},{3,7},{4,8},{5,8},{6,6},{7,4},{8,7},{9,3},{9,6}};
        List<Integer> res = getMin(edges, 10);
        for (int num : res) {
            System.out.println(num);
        }
    }

}



import java.util.*;

public class minVerticesTraverseGraph {

    public static List<Integer> minVertices(int[][] edges, int n) {
        Set<Integer> res = new HashSet<>();
        Map<Integer, HashSet<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            map.put(i, new HashSet<>());
        }
        for(int i = 0; i < edges.length; i++) {
            map.get(edges[i][0]).add(edges[i][1]);
        }
        Set<Integer> visited = new HashSet<>();

        for(int i = 0; i < n; i++) {
            if(visited.contains(i)) {
                continue;
            }
            res.add(i);
            visited.add(i);
            Set<Integer> thisTimeVisited = new HashSet<>();
            dfs(res, map, visited, thisTimeVisited, i, i);
        }
        return new ArrayList<Integer>(res);
    }

    public static void dfs(Set<Integer> res, Map<Integer, HashSet<Integer>> map, Set<Integer> visited, Set<Integer> thisTimeVisited, int curr, int start) {
        for(int next : map.get(curr)) {
            if(res.contains(next) && next != start) {
                res.remove(next);
            }
            if(!thisTimeVisited.contains(next)) {
                visited.add(next);
                thisTimeVisited.add(next);
                dfs(res, map, visited, thisTimeVisited, next, start);
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{2,3},{3,1},{1,2},{2,0},{4,5}};
        minVerticesTraverseGraph graph = new minVerticesTraverseGraph();
        System.out.println(graph.minVertices(edges, 6));
        System.out.println(minVertices(edges, 6));
    }
}

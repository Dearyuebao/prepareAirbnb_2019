import java.util.*;
//time: O(l^n)
public class minVertices {

    //this question is a directed graph, and we need to find minimum start points set to traverse the whole
    //graph, so I will use dfs to  check that is I set a node as a start, which points we could reach throught
    //this start point
    public static List<Integer> minVertice(int[][] edges, int n) {
        //use the hashset to store the result
        Set<Integer> res = new HashSet<>();
        //use this map to store the neighbour of each points
        Map<Integer, HashSet<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            map.put(i, new HashSet<>());
        }
        //then I will build the directed graph
        for(int i = 0; i < edges.length; i++) {
            map.get(edges[i][0]).add(edges[i][1]);
        }
        //use this set to store the visited points
        Set<Integer> visited = new HashSet<>();

        //then we begin to set not visited point as start
        for(int i = 0; i < n; i++) {
            if(visited.contains(i)) continue;
            //if the point is not visited, I add it to result, which means that I temporarily set it as start
            res.add(i);
            //and add it to visited
            visited.add(i);
            //I use a new set thisTimeVisited to store the visited point this time
            Set<Integer> thisTimeVisited = new HashSet<>();
            //then I use dfs to find all the connected nodes
            dfs(res, map, visited, thisTimeVisited, i, i);
        }
        return new ArrayList<Integer>(res);
    }
    public static void dfs(Set<Integer> res, Map<Integer, HashSet<Integer>> map, Set<Integer> visited, Set<Integer> thisTimeVisited, int curr, int start) {
        //find the neighbours of the current point
        for(int next : map.get(curr)) {
            //if the result contains the next point and the next point is not the start, it means we have used it as a start point before
            //but if we could reach it from the current start, we could remove it from the result set
            //to make sure we get the minimum amount of points as result
            if(res.contains(next) && next != start) {
                res.remove(next);
            }
            //if I never meet the next point from this start, I will do dfs from it
            if(!thisTimeVisited.contains(next)) {
                visited.add(next);
                thisTimeVisited.add(next);
                dfs(res, map, visited, thisTimeVisited, next, start);
            }
        }
    }

    public static void main(String[] args) {
//        int[][] edges = {{2,3},{3,1},{1,2},{2,0},{4,5}};
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {7, 9}, {9, 1}};
        System.out.println(minVertice(edges, 10));
    }

}

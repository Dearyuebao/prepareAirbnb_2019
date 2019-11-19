package Airbnb;

import java.util.*;
/*
time: O(V+E), V : number of vertex, E: number of edges
Use topological sort to solve this question, very stop
is represent by class Node, the variable is name, point
nexts,score, use HashMap to build the graph, the key
is the name of current stop, the value is the Node for
current stop, use HashMap to represent the indegree,
the Key is current stop name, the value is the indegree
of current stop, use queue to implement topological sort,
first offer the src node to the queue, every time pop
the Node from the queue, update score value when it has
a larger one, use HashMap to store the path, the key in
the path map is current, the value in the path map is the
previous stop, so each stop is mapped to a stop which make
it has the max score, use variable ans to record the max score,
when reach a stop has no next stop, and it's score is
larger than the ans, update the ans value, finally we
can know the final stop name, and we can transverse the
path map from the end stop to start stop, so get the path.

 */

//it is a directed acyclic graph, we need to sort it and get the maximum score, so I will use topological sort
//To implement topological sort, we need to record the indegree of each node, indegree is the number of edges that going
//to the node. We also need a queue to implement it, each time we add the zero indegree node to the queue.
public class Ad_SnowSkiing {
    static class Node {
        String name;
        int point;
        HashMap<String, Integer> nexts;
        int score;
        Node(String name, int point) {
            this.name = name;
            this.point = point;
            this.nexts = new HashMap<>();
            this.score = Integer.MIN_VALUE;
        }
    }
    private static int getScore(int point, int dis) {
        return point*2 + dis;
    }
    public static int sk(String[] nodes, String[] edges, String src, List<String> path) {
        HashMap<String, Node> graph = new HashMap<>();
        HashMap<String, Integer> indegree = new HashMap<>();
        for (String s : nodes) {
            String[] t = s.split(",");
            String name = t[0];
            int point = Integer.parseInt(t[1]);
            graph.put(name, new Node(name, point));
            graph.get(name).point = point;
            indegree.put(name, 0);
            if (name.equals(src)) graph.get(name).score = 0;
        }
        for (String s : edges) {
            String[] t = s.split(",");
            int dis = Integer.parseInt(t[1]);
            String[] fromto = t[0].split("->");
            String from = fromto[0];
            String to = fromto[1];
            if (!graph.get(from).nexts.containsKey(to)) {
                indegree.put(to, indegree.getOrDefault(to, 0) + 1);
            }
            graph.get(from).nexts.put(to, dis);
        }
        Queue<Node> queue = new LinkedList<>();
        HashMap<String, String> link = new HashMap<>();
        queue.offer(graph.get(src));
        String finalName = "";
        int ans = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.nexts.isEmpty() && cur.score != Integer.MIN_VALUE && ans < cur.score) {
                ans = cur.score;
                finalName = cur.name;
            }
            int point = cur.point;
            for (String next : cur.nexts.keySet()) {
                if (cur.score != Integer.MIN_VALUE) {//对于score小的还是Integer.MIN就不访问另一条了
                    int dis = cur.nexts.get(next);
                    int newScore = getScore(point, dis);
                    if (newScore + cur.score > graph.get(next).score) {//只更新了score大的
                        graph.get(next).score = newScore + cur.score;
                        link.put(next, cur.name);
                    }
                }
                indegree.put(next, indegree.get(next)-1);
                if (indegree.get(next) == 0) {
                    queue.offer(graph.get(next));
                }
            }

        }
        path.clear();
        while (!finalName.isEmpty()) {
            path.add(finalName);
            finalName = link.containsKey(finalName) ? link.get(finalName) : "";
        }
        Collections.reverse(path);
        return ans;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] nodes = {"A,5", "B,7", "C,6", "D,2", "E,4", "F,7", "H,7", "I,3", "J,2"} ;
        String[] edges = {
                "A->B,2",
                "A->C,3",
                "B->D,5",
                "B->E,6",
                "C->E,4",
                "C->F,4",
                "D->H,7",
                "E->H,6",
                "H->I,1",
                "H->J,2",
                "F->J,3"
        } ;
        List<String> path = new ArrayList<>() ;
        int ans = sk(nodes, edges, "A", path) ;
        System.out.println(ans);
        System.out.println(path);
    }
}

import java.util.*;

public class skiScore {
    static List<String> path = new ArrayList<>();
    static int res;
    static class Node{
        String name;
        int point;
        int score;
        Map<String, Integer> nexts = new HashMap<>();
        public Node(String name, int point) {
            this.name = name;
            this.point = point;
            this.score = Integer.MIN_VALUE;
            this.nexts = new HashMap<>();
        }
    }

    public static void maxScore(String[] nodes, String[] edges, String src) {
        Map<String, Node> graph = new HashMap<>();
        Map<String, Integer> indegrees = new HashMap<>();
        for(String each : nodes) {
            String[] num = each.split(",");
            String name = num[0];
            int point = Integer.parseInt(num[1]);
            if(!graph.containsKey(name)) {
                graph.put(name, new Node(name, point));
            }
            //graph.get(name).point = point;//can be removed?
            indegrees.put(name, 0);
            if(name.equals(src)) {
                graph.get(name).score = 0;
            }
        }

        for(String each : edges) {
            String[] num = each.split(",");
            String line = num[0];
            int dist = Integer.parseInt(num[1]);
            String[] fromTo = line.split("->");
            String from = fromTo[0];
            String to = fromTo[1];
            //if(!graph.get(from).nexts.containsKey(to)) {//can be removed?
                indegrees.put(to, indegrees.getOrDefault(to, 0) + 1);
            //}
            graph.get(from).nexts.put(to, dist);
        }

        Map<String, String> links = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        for(String name: indegrees.keySet()) {
            if(indegrees.get(name) == 0) {
                q.offer(graph.get(name));
            }
        }
        String finalName = "";
        res = Integer.MIN_VALUE;
        while(!q.isEmpty()) {
            Node curr = q.poll();
            if(curr.nexts.isEmpty() && curr.score != Integer.MIN_VALUE && res < curr.score) {
                res = curr.score;
                finalName = curr.name;
            }
            for(String next: curr.nexts.keySet()) {
                if(curr.score != Integer.MIN_VALUE) {
                    int score = 2 * curr.point + curr.nexts.get(next);
                    if(curr.score + score > graph.get(next).score) {
                        graph.get(next).score = curr.score + score;
                        links.put(next, curr.name);
                    }
                }
                indegrees.put(next, indegrees.get(next) - 1);
                if(indegrees.get(next) == 0) {
                    q.offer(graph.get(next));
                }
            }
        }

        while(!finalName.isEmpty()) {
            path.add(finalName);
            finalName = links.containsKey(finalName)?links.get(finalName):"";
        }
        Collections.reverse(path);
    }

    public static void main(String[] args) {
        String[] nodes = {"A,5", "B,7","C,6", "D,2","E,4","F,7","H,7","I,3","J,2"};
        String[] edges = {"A->B,2","A->C,3","B->D,5","B->E,6","C->E,4","C->F,4","D->H,7","E->H,6","H->I,1","H->J,2","F->J,3"};
        maxScore(nodes, edges, "A");
        System.out.println(res);
        System.out.println(path);
    }
}


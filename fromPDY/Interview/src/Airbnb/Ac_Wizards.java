package Airbnb;
import java.util.*;
/*time : O(E+ VlogV)
This question is equals to find the minimum distance in a directed graph, so I can use dijkstra algorithm.
1. Use a class Node to represent each wizard, there are current distance, current wizard and previous wiard in the Node class.
2. Use small root heap to implement dijkstra algorithm,first offer the src wizard, every time pop the min dis wizard node from the heap,
and offer the children of pre wizard to the queue, until we pop a node, which name is equal to the destination name, we get the result.
 */
//dijkstra
public class Ac_Wizards {
    static class Node {
        int dis;
        int val;
        int pre;
        Node(int dis, int val, int pre) {
            this.dis = dis;
            this.val = val;
            this.pre = pre;
        }
    }
    static int min = Integer.MAX_VALUE;
    public static List<Integer> wizards(List<List<Integer>> wizards, int src, int dst) {
        List<Integer> res = new ArrayList<>();
        if(wizards == null || wizards.size() == 0) return res;
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> (a.dis - b.dis));
        int[] parent = new int[wizards.size()];
        Arrays.fill(parent, -1);
        for (int i : wizards.get(src)) {
            heap.offer(new Node((i - src) * (i - src), i, src));
        }
        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            if(parent[cur.val] != -1) continue;
            parent[cur.val] = cur.pre;
            if(cur.val == dst) {
                min = cur.dis;
                int p = dst;
                while (p != src) {
                    res.add(p);
                    p = parent[p];
                }
                res.add(src);
                Collections.reverse(res);
                return res;
            }
            for (int i : wizards.get(cur.val)) {
                heap.offer(new Node((i - cur.val) * (i - cur.val) + cur.dis, i, cur.val));
            }
        }
        return new ArrayList<>();
    }
    public static void main(String args[]) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Integer> l = new ArrayList<>();
            if (i == 0) {
                l.add(1);
                l.add(2);
            }
            if (i == 1) {
                l.add(3);
            }
            if (i == 2) {
                l.add(4);
            }
            if (i == 3) {
                l.add(4);
            }
            list.add(l);
        }
        List<Integer> res = wizards(list, 0, 4);
        System.out.println(min);
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i) + " ");
        }
    }

}


/*
有向图找最短路径，BFS
public class wizards {
    public static class Node {
        int index;
        int dis;
        Node(int index, int dis) {
            this.index = index;
            this.dis = dis;
        }
    }

    public static int minDis(List<List<Integer>> wizard) {
        int n = wizard.size();
        Queue<Node> queue = new LinkedList<>();
        Node w[] = new Node[n];
        for (int i = 0; i < n; i++) {
            w[i] = new Node(i, i == 0 ? 0 : Integer.MAX_VALUE);
        }
        queue.offer(w[0]);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int i : wizard.get(cur.index)) {
                int newDis = (i - cur.index) * (i - cur.index) + cur.dis;
                if (newDis < w[i].dis) {
                    w[i].dis = newDis;
                    queue.offer(w[i]);
                }
            }
        }
        return w[n - 1].dis;
    }
}
*/
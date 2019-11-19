import java.util.*;

/*time : O(E+ VlogV)
This question is equals to find the minimum distance in a directed graph, so I can use dijkstra algorithm.
1. Use a class Node to represent each wizard, there are current distance, current wizard and previous wiard in the Node class.
2. Use small root heap to implement dijkstra algorithm,first offer the src wizard, every time pop the min dis wizard node from the heap,
and offer the children of pre wizard to the queue, until we pop a node, which name is equal to the destination name, we get the result.
 */
//dijkstra
public class Wizards {
    static class Num{
        int dis;//means the distance to source wizard
        int val;//means the current wizard
        //int last;
        public Num(int d, int v) {
            this.dis = d;
            this.val = v;
           // int last = l;
        }
    }

    public static int getMinCost(List<List<Integer>> wizards, int src, int dst) {
        int min = Integer.MAX_VALUE;
        if(wizards == null || wizards.size() == 0) return min;
        PriorityQueue<Num> pq = new PriorityQueue<Num>((a, b) -> (a.dis - b.dis));
        //sort by diatance to the first wizard, from low to high
        for(int each : wizards.get(src)) {
            pq.offer(new Num((each - src) * (each - src), each));
        }
        HashSet<Integer> visited = new HashSet<>();
        visited.add(src);
        while(!pq.isEmpty()) {
            Num cur = pq.poll();
            visited.add(cur.val);
            if(cur.val == dst) {
                min = cur.dis;
                return min;
            }
            for(int i : wizards.get(cur.val)) {
                if(!visited.contains(i)) {
                    pq.offer(new Num((i - cur.val) * (i - cur.val) + cur.dis, i));
                }
            }
        }
        return min;
    }

    //follow up: print path

    static class Node{
        int dis;//means the distance to source wizard
        int val;//means the current wizard
        int last;
        public Node(int d, int v, int l) {
            this.dis = d;
            this.val = v;
            this.last = l;
        }
    }

    static int ans = Integer.MAX_VALUE;
    public static List<Integer> wizardsFollow(List<List<Integer>> wizards, int src, int dst) {
        List<Integer> res = new ArrayList<>();
        if(wizards == null || wizards.size() == 0) return res;
        if(dst == src) {
            res.add(src);
            return res;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b)->(a.dis - b.dis));
        int[] parent = new int[wizards.size()];
        Arrays.fill(parent, -1);
        for(int i : wizards.get(src)) {
            pq.offer(new Node((i - src) * (i - src), i, src));
        }
        HashSet<Integer> visited = new HashSet<>();
        visited.add(src);
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(parent[cur.val] == -1) parent[cur.val] = cur.last;
            if(cur.val == dst) {
                ans = cur.dis;
                int p = dst;
                while(p != src) {
                    res.add(p);
                    p = parent[p];
                }
                res.add(src);
                Collections.reverse(res);
                return res;
            }
            for(int i : wizards.get(cur.val)) {
                if(!visited.contains(i)) {
                    pq.offer(new Node((i - cur.val) * (i - cur.val) + cur.dis, i, cur.val));
                }
            }
        }
        return new ArrayList<>();
    }

    //follow up : bfs

    public static int bfs(List<List<Integer>> wizards, int src, int dst) {
        int min = Integer.MAX_VALUE;
        if(wizards == null || wizards.size() == 0) return min;
        int n = wizards.size();
        Num[] num = new Num[n];
        for(int i = 0; i < n; i++) {
            num[i] = new Num(i == 0?0:Integer.MAX_VALUE, i);
        }
        Queue<Num> q = new LinkedList<>();
        q.offer(num[src]);
        while(!q.isEmpty()) {
            Num curr = q.poll();
            for(int i : wizards.get(curr.val)) {
                int newDis = curr.dis + (i - curr.val) * (i - curr.val);
                if(num[i].dis > newDis) {
                    num[i].dis = newDis;
                    q.offer(num[i]);
                }
            }
        }
        //each Num.dis store the min cost to Num.val
        return num[dst].dis;
    }



    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1,2,3));
        list.add(Arrays.asList(8,6,4));
        list.add(Arrays.asList(7,8,3));
        list.add(Arrays.asList(8,1));
        list.add(Arrays.asList(6));
        list.add(Arrays.asList(8,7));
        list.add(Arrays.asList(9,4));
        list.add(Arrays.asList(4,6));
        list.add(Arrays.asList(1));
        list.add(Arrays.asList(1,4));
        System.out.println(getMinCost(list, 0, 9));
        System.out.println();
        List<Integer> path = wizardsFollow(list, 0, 9);
        System.out.println();
        System.out.println(ans);
        for(int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) + " ");
        }
    }

}

//pdy
//package Airbnb;
//        import java.util.*;
///*time : O(E+ VlogV)
//This question is equals to find the minimum distance in a directed graph, so I can use dijkstra algorithm.
//1. Use a class Node to represent each wizard, there are current distance, current wizard and previous wiard in the Node class.
//2. Use small root heap to implement dijkstra algorithm,first offer the src wizard, every time pop the min dis wizard node from the heap,
//and offer the children of pre wizard to the queue, until we pop a node, which name is equal to the destination name, we get the result.
// */
////dijkstra
//public class Ac_Wizards {
//    static class Node {
//        int dis;
//        int val;
//        int pre;
//        Node(int dis, int val, int pre) {
//            this.dis = dis;
//            this.val = val;
//            this.pre = pre;
//        }
//    }
//    static int min = Integer.MAX_VALUE;
//    public static List<Integer> wizards(List<List<Integer>> wizards, int src, int dst) {
//        List<Integer> res = new ArrayList<>();
//        if(wizards == null || wizards.size() == 0) return res;
//        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> (a.dis - b.dis));
//        int[] parent = new int[wizards.size()];
//        Arrays.fill(parent, -1);
//        for (int i : wizards.get(src)) {
//            heap.offer(new Node((i - src) * (i - src), i, src));
//        }
//        while (!heap.isEmpty()) {
//            Node cur = heap.poll();
//            if(parent[cur.val] != -1) continue;
//            parent[cur.val] = cur.pre;
//            if(cur.val == dst) {
//                min = cur.dis;
//                int p = dst;
//                while (p != src) {
//                    res.add(p);
//                    p = parent[p];
//                }
//                res.add(src);
//                Collections.reverse(res);
//                return res;
//            }
//            for (int i : wizards.get(cur.val)) {
//                heap.offer(new Node((i - cur.val) * (i - cur.val) + cur.dis, i, cur.val));
//            }
//        }
//        return new ArrayList<>();
//    }
//    public static void main(String args[]) {
//        List<List<Integer>> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            List<Integer> l = new ArrayList<>();
//            if (i == 0) {
//                l.add(1);
//                l.add(2);
//            }
//            if (i == 1) {
//                l.add(3);
//            }
//            if (i == 2) {
//                l.add(4);
//            }
//            if (i == 3) {
//                l.add(4);
//            }
//            list.add(l);
//        }
//        List<Integer> res = wizards(list, 0, 4);
//        System.out.println(min);
//        for (int i = 0; i < res.size(); i++) {
//            System.out.print(res.get(i) + " ");
//        }
//    }

//}


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

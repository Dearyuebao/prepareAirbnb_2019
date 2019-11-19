package Airbnb;
import java.util.*;
//

class Ce_DisplayPage {
    static class Node {
        String hostId;
        int j;
        String s;
        public Node (String hostId, int j, String s) {
            this.hostId = hostId;
            this.j = j;
            this.s = s;
        }
    }

    public static List<String> findPage (List<String> path, int pageSize) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : path) {
            String hostId = str.split(",")[0];
            if (!map.containsKey(hostId)) {
                map.put(hostId, new ArrayList<>());
            }
            map.get(hostId).add(str);
        }

        Queue<Node> queue = new LinkedList<>();
        for (String key : map.keySet()) {
            queue.offer(new Node(key, 0, map.get(key).get(0)));
        }

        List<Node> temp = new ArrayList<>();
        int count = 0;
        List<String> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            temp.add(cur);
            count++;
            if (cur.j + 1!= map.get(cur.hostId).size()) {
                queue.offer(new Node(cur.hostId, cur.j + 1, map.get(cur.hostId).get(cur.j + 1)));
            }
            if (count == pageSize) {
                copy(temp, res);
                count = 0;
            }
        }
        if (temp.size() != 0) {
            copy(temp, res);
        }
        return res;
    }

    public static void copy (List<Node> temp, List<String> res) {
        Collections.sort(temp, new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                return Double.compare(Double.parseDouble(n2.s.split(",")[2]),Double.parseDouble(n1.s.split(",")[2]));
            }
        });
        for (Node node : temp) {
            res.add(node.s);
        }
        res.add("");
        temp.clear();
    }

    public static void main(String args[])
    {
        //hostId, ListId, points, city
        List<String> input = new ArrayList<>();
        input.add("1,28,310.6,SF");
        input.add("1,5,204.1,SF");
        input.add("20,7,203.2,Oakland");
        input.add("6,8,202.2,SF");
        input.add("6,10,199.1,SF");
        input.add("1,16,190.4,SF");
        input.add("6,29,185.2,SF");
        input.add("7,20,180.1,SF");
        input.add("6,21,162.1,SF");
        input.add("2,18,161.2,SF");
        input.add("2,30,149.1,SF");
        input.add("3,76,146.2,SF");
        input.add("2,14,141.1,San Jose");

        List<String> result = findPage(input, 5);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }

    }
}
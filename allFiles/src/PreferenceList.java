import java.util.*;

public class PreferenceList {
    public static List<Integer> sortPreference(List<List<Integer>> preference) {
        Map<Integer, HashSet<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for(int i = 0 ; i < preference.size(); i++) {
            for(int j = 0; j < preference.get(i).size(); j++) {
                int curr = preference.get(i).get(j);
                if(!graph.containsKey(curr)) {
                    graph.put(curr, new HashSet<Integer>());
                }
                if(!indegree.containsKey(curr)) {
                    indegree.put(curr, 0);
                }

                if(j > 0) {
                    int pre = preference.get(i).get(j - 1);
                    graph.get(pre).add(curr);
                    indegree.put(curr, indegree.get(curr) + 1);
                }
            }
        }

        int total = indegree.size();
        Queue<Integer> q = new LinkedList<>();
        for(int each : indegree.keySet()) {
            if(indegree.get(each) == 0) {
                q.offer(each);
            }
        }
        while(!q.isEmpty()) {
            int n = q.size();
            for(int i = 0; i < n; i++) {
                int cur = q.poll();
                res.add(cur);
                for(int each : graph.get(cur)) {
                    indegree.put(each, indegree.get(each) - 1);
                    if(indegree.get(each) == 0) {
                        q.offer(each);
                    }
                }
            }
        }
        return res.size() == total? res: new ArrayList<>();
    }

    public static List<Integer> sortPreferenceFollow(List<List<Integer>> preference, int tieBreaker) {
        Map<Integer, HashSet<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        Set<Integer> tieBreakList = new HashSet<>(preference.get(tieBreaker));

        for(int i = 0 ; i < preference.size(); i++) {
            for(int j = 0; j < preference.get(i).size(); j++) {
                int curr = preference.get(i).get(j);
                if(!graph.containsKey(curr)) {
                    graph.put(curr, new HashSet<Integer>());
                }
                if(!indegree.containsKey(curr)) {
                    indegree.put(curr, 0);
                }

                if(j > 0) {
                    int pre = preference.get(i).get(j - 1);
                    graph.get(pre).add(curr);
                    indegree.put(curr, indegree.get(curr) + 1);
                }
            }
        }

        int total = indegree.size();
        Queue<Integer> q = new LinkedList<>();
        for(int each : indegree.keySet()) {
            List<Integer> tiePref = new ArrayList<>();
            List<Integer> otherPref = new ArrayList<>();
            if(indegree.get(each) == 0) {
                if(tieBreakList.contains(each)) {
                    tiePref.add(each);
                } else {
                    otherPref.add(each);
                }
            }

            for(int w : tiePref) {
                q.offer(w);
            }
            for(int w : otherPref) {
                q.offer(w);
            }
        }
        while(!q.isEmpty()) {
            int n = q.size();
            List<Integer> tiePref = new ArrayList<>();
            List<Integer> otherPref = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                int cur = q.poll();
                res.add(cur);
                for(int each : graph.get(cur)) {
                    indegree.put(each, indegree.get(each) - 1);

                    if(indegree.get(each) == 0) {
                        if(tieBreakList.contains(each)) {
                            tiePref.add(each);
                        } else {
                            otherPref.add(each);
                        }
                    }
                }
            }
            for(int w : tiePref) {
                q.offer(w);
            }
            for(int w : otherPref) {
                q.offer(w);
            }
        }
        return res.size() == total? res: new ArrayList<>();
    }

    public static List<Integer> sortPreferenceNew(List<List<Integer>> preferences, int tieBreaker) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Set<Integer>> edges = new HashMap<>();
        Map<Integer, Integer> indegrees = new HashMap<>();

        for (int i = 0; i < preferences.size(); i++) {
            for (int j = 0; j < preferences.get(i).size(); j++) {
                int thisNode = preferences.get(i).get(j);
                if (!edges.containsKey(thisNode)) {
                    edges.put(thisNode, new HashSet<>());
                    indegrees.put(thisNode, 0);
                }

                if (j > 0) {
                    int lastNode = preferences.get(i).get(j - 1);
                    edges.get(lastNode).add(thisNode);
                    indegrees.put(thisNode, indegrees.get(thisNode) + 1);
                }
            }
        }

        Set<Integer> tieBreakList = new HashSet<>(preferences.get(tieBreaker));
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> tieBreakerPrefs1 = new ArrayList<>();
        List<Integer> othersPrefs1 = new ArrayList<>();
        for (int node : indegrees.keySet()) {

            if (indegrees.get(node) == 0) {
                if (tieBreakList.contains(node)) {
                    tieBreakerPrefs1.add(node);
                } else {
                    othersPrefs1.add(node);
                }
            }
        }
            for (int pref : tieBreakerPrefs1) {
                queue.offer(pref);
            }
            for (int pref : othersPrefs1) {
                queue.offer(pref);
            }

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tieBreakerPrefs = new ArrayList<>();
            List<Integer> othersPrefs = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                res.add(node);
                for (int succ : edges.get(node)) {
                    indegrees.put(succ, indegrees.get(succ) - 1);
                    if (indegrees.get(succ) == 0) {
                        if (tieBreakList.contains(succ)) {
                            tieBreakerPrefs.add(succ);
                        } else {
                            othersPrefs.add(succ);
                        }
                    }
                }
            }

            for (int pref : tieBreakerPrefs) {
                queue.offer(pref);
            }
            for (int pref : othersPrefs) {
                queue.offer(pref);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> preference = new ArrayList<>();
        preference.add(Arrays.asList(3, 5, 7, 9));//K = 1
        preference.add(Arrays.asList(2, 5, 8));//
        preference.add(Arrays.asList(1, 7, 10));
        //3 5 7 9
        //2
//        List<Integer> a = new ArrayList<>();
//        a.add(3);
//        a.add(5);
//        a.add(7);
//        a.add(9);
//        preference.add(a);
//        List<Integer> b = new ArrayList<>();
//        b.add(2);
//        b.add(3);
//        b.add(8);
//        preference.add(b);
//        List<Integer> c = new ArrayList<>();
//        c.add(5);
//        c.add(8);
//        preference.add(c);
        System.out.println(sortPreferenceNew(preference, 0));
        System.out.println(sortPreferenceNew(preference, 1));
        System.out.println(sortPreferenceNew(preference, 2));
    }
}

//
//    public static List<Integer> getPreference(List<List<Integer>> preferences) {
//        Map<Integer, Integer> inDegree = new HashMap<>();
//        Map<Integer, Set<Integer>> nodes = new HashMap<>();
//        Map<Integer, Integer> prefer = new HashMap<>();
//        for (int i = 0; i < preferences.size(); i++) {
//            for (int j = 0; j < preferences.get(i).size(); j++) {
//                if (!prefer.containsKey(preferences.get(i).get(j))) {
//                    prefer.put(preferences.get(i).get(j), i);
//                }
//            }
//        }
//   for (List<Integer> l : preferences) {
//        for (int i = 0; i < l.size() - 1; i++) {
//        int from = l.get(i);
//        int to = l.get(i + 1);
//        if (!nodes.containsKey(from)) {
//        inDegree.put(from, 0);
//        nodes.put(from, new HashSet<>());
//        }
//        if (!nodes.containsKey(to)) {
//        inDegree.put(to, 0);
//        nodes.put(to, new HashSet<>());
//        }
//        if (!nodes.get(from).contains(to)) {
//        Set<Integer> s = nodes.get(from); s.add(to);
//        nodes.put(from, s);
//        }
//        inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
//        }
//        }
//        Queue<Integer> q = new LinkedList<>();
//        for (int k : inDegree.keySet()) {
//        if (inDegree.get(k) == 0) {
//        q.offer(k);
//        }
//        }
//        List<Integer> res = new ArrayList<>();
//        while (!q.isEmpty()) {
//        int size = q.size();
//        PriorityQueue<Integer> pq = new PriorityQueue<>((a , b) ->prefer.get(a) - prefer.get(b));
//        for (int i = 0; i < size; i++) {
//        int id = q.poll();
//        pq.offer(id);
//        Set<Integer> neighbors = nodes.get(id);
//        for (int next : neighbors) {
//        int degree = inDegree.get(next) - 1;
//        inDegree.put(next, degree);
//        if (degree == 0) q.offer(next);
//        }
//        }
//        while(pq.size() > 0) {
//        res.add(pq.poll());
//        }
//
//        }
//        return res;
//        }
//public static void main(String[] args) {
//        List<List<Integer>> list = new ArrayList<>();
//        list.add(new ArrayList<>(Arrays.asList(3,5,7,9)));
//        list.add(new ArrayList<>(Arrays.asList(2,5,8)));
//        list.add(new ArrayList<>(Arrays.asList(1,7,10)));
//        List<Integer> res = getPreference(list);
//        for (int i : res) {
//        System.out.print(i + " ");
//        }
//        }
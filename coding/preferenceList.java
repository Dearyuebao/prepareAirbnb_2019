import java.util.*;

public class preferenceList {
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

    public static void main(String[] args) {
        List<List<Integer>> preference = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        a.add(3);
        a.add(5);
        a.add(7);
        a.add(9);
        preference.add(a);
        List<Integer> b = new ArrayList<>();
        b.add(2);
        b.add(3);
        b.add(8);
        preference.add(b);
        List<Integer> c = new ArrayList<>();
        c.add(5);
        c.add(8);
        preference.add(c);
        System.out.println(sortPreferenceFollow(preference, 1));
    }

}

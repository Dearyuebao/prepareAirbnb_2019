package Airbnb;
import java.util.*;
class Db_LCA {
    Map<String, String> parent;

    public Db_LCA(List<List<String>> lists) {
        this.parent = new HashMap<String, String>();
        if (lists == null || lists.size() == 0) {
            return;
        }
        for (List<String> list : lists) {
            String par = list.get(0);
            String s1 = list.get(1);
            String s2 = list.get(2);
            parent.put(s1, par);
            parent.put(s2, par);
        }
    }

    public String getLCA(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return null;
        }

        Set<String> visited = new HashSet<String>();
        while (parent.get(s1) != null) {
            visited.add(s1);
            s1 = parent.get(s1);
        }
        visited.add(s1);

        while (!visited.contains(s2)) {
            s2 = parent.get(s2);
        }

        return s2;
    }
}

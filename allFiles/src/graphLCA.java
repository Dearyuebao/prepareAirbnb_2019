import java.util.*;

public class graphLCA {
    public static String findAncester(String city1, String city2, List<List<String>> list) {
        if(city1 == null || city2 == null) {
            return "";
        }
        Map<String, String> map = new HashMap<>();
        for(List<String> each : list) {
            String par = each.get(0);
            String son1 = each.get(1);
            String son2 = each.get(2);
            map.put(son1, par);
            map.put(son2, par);
        }

        Set<String> visited = new HashSet<>();
        while(map.get(city1) != null) {
            visited.add(city1);
            city1 = map.get(city1);
        }
        visited.add(city1);

        while(!visited.contains(city2)) {
            city2 = map.get(city2);
        }
        return city2;
    }

    public static void main(String[] args) {
        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList(new String[]{"earth", "north america", "south america"}));
        list.add(Arrays.asList(new String[]{"south america", "brazil", "arginta"}));
        list.add(Arrays.asList(new String[]{"north america", "united states", "canada"}));
        list.add(Arrays.asList(new String[]{"united states", "california", "new york"}));
        list.add(Arrays.asList(new String[]{"california", "san fracisco", "oakland"}));

        String res = findAncester("new york", "oakland", list);
        System.out.println(res);
    }
}

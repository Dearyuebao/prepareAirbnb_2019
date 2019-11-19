package Airbnb;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
//use a class buddy to store information of each people
//traverse each buddies's wishlist, and calculate similarities between each buddy and me, store the similarity in the buddy class
//if the similarity is greater than 1 over 2, we add the buddy in a list
//sort them by similarity from high to low, and output


class Buddy {
    String name;
    int sim;
    Set<String> list;
    public Buddy(String name, int sim, Set<String> list) {
        this.name = name;
        this.sim = sim;
        this.list = list;
    }
}

class TravelBuddy {

    private List<Buddy> buddies;
    private Set<String> myList;
    private Map<String, Set<String>> friendLists;

    public TravelBuddy(Set<String> myList, Map<String, Set<String>> friendLists) {
        this.myList = myList;
        this.friendLists = friendLists;
    }

    public List<Buddy> findBuddies() {
        List<Buddy> buddies = new ArrayList<>();
        for (String name : friendLists.keySet()) {
            Set<String> wishList = friendLists.get(name);
            Set<String> common = new HashSet<>(wishList);
            common.retainAll(myList);
            if (common.size() >= myList.size() / 2) {
                buddies.add(new Buddy(name, common.size(), wishList));
            }
        }
        Collections.sort(buddies, new Comparator<Buddy>(){
            public int compare(Buddy b1, Buddy b2) {
                return b2.sim - b1.sim;
            }
        });
        this.buddies = buddies;
        return buddies;
    }

    public Set<String> recommend(int k){
        int count = 0;
        Set<String> result = new LinkedHashSet<>();
        for (Buddy buddy : buddies) {
            //注意是从buddy的list中remove我的list，而不是从我的list中remove他们的
            Set<String> diff = new HashSet<>(buddy.list);
            diff.removeAll(myList);
            for (String city : diff) {
                if (count < k) {
                    if (result.add(city)) {
                        count++;
                    }
                } else {
                    return result;
                }
            }
        }
        return result;
    }
}


//class Solution
//{
//    public static void main(String args[])
//    {
//        Set<String> myList = new HashSet<>(Arrays.asList("A", "B", "C", "D"));
//
//        Set<String> peter = new HashSet<>(Arrays.asList("A", "B", "E", "F"));
//        Set<String> john = new HashSet<>(Arrays.asList("A", "B", "D", "G"));
//        Set<String> casy = new HashSet<>(Arrays.asList("X", "B", "A", "D", "Q"));
//        Set<String> jason = new HashSet<>(Arrays.asList("A", "B", "C", "D", "P", "Q"));
//        Set<String> ken = new HashSet<>(Arrays.asList("A", "X", "Y", "Z"));
//
//        Map<String, Set<String>> friendLists = new HashMap<>();
//        friendLists.put("peter", peter);
//        friendLists.put("john", john);
//        friendLists.put("casy", casy);
//        friendLists.put("jason", jason);
//        friendLists.put("ken", ken);
//
//
//        TravelBuddy t = new TravelBuddy(myList, friendLists);
//        List<Buddy> buddies = t.findBuddies();
//        for (int i = 0; i < buddies.size(); i++) {
//            Buddy b = buddies.get(i);
//            System.out.println("Name: " + b.name + " sim: " + b.sim);
//        }
//
//        Set<String> cities = t.recommend(5);
//        for (String city : cities) {
//            System.out.println(city);
//        }
//    }
//}

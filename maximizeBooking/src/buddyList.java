import java.util.*;

public class buddyList {
    static class Buddy implements Comparable<Buddy>{
        String name;
        int sim;
        Set<String> wish;
        public Buddy(String name, int sim, Set<String> wish) {
            this.name = name;
            this.sim = sim;
            this.wish = wish;
        }

        @Override
        public int compareTo(Buddy that) {
            return that.sim - this.sim;
        }
    }

    static class BuddyList{
        List<Buddy> buddies;
        Set<String> myWishList;

        public BuddyList(Set<String> myWishList, Map<String, Set<String>> friendWishList) {
            this.myWishList = myWishList;
            //take care : don't forget
            this.buddies = new ArrayList<>();
            for(String name: friendWishList.keySet()) {
                Set<String> curr = friendWishList.get(name);
                Set<String> copy = new HashSet<>(curr);
                copy.retainAll(myWishList);
                int sim = copy.size();
                if(sim >= curr.size() / 2)
                    buddies.add(new Buddy(name, sim, curr));
            }
        }

        public List<Buddy> getBuddies(){
            Collections.sort(buddies);
            List<Buddy> res = new ArrayList<>(buddies);

            for(Buddy each : res) {
                System.out.print(each.name + ":");
                for(String city: each.wish) {
                    System.out.print(city + " ");
                }
                System.out.println(" ");
            }
            return res;
        }

        public List<String> recommend(int k) {
            List<String> res = new ArrayList<>();
            List<Buddy> buddies = getBuddies();

            int i = 0;
            while(i < buddies.size() && k > 0) {
                Buddy curr = buddies.get(i);
                Set<String> currWish = curr.wish;
                Set<String> copy = new HashSet<>(currWish);
                copy.removeAll(myWishList);
                if(copy.size() <= k) {
                    res.addAll(copy);
                    k -= copy.size();
                    i++;
                } else {
                    Iterator<String> it = copy.iterator();
                    while(k > 0) {
                        res.add(it.next());
                        k--;
                    }
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Set<String> myWishList = new HashSet(Arrays.asList(new String[]{"a","b","c","d"}));
        Map<String, Set<String>> friendWishList = new HashMap<>();
        Set<String> friend1 = new HashSet(Arrays.asList(new String[]{"a","b","e","f"}));
        Set<String> friend2 = new HashSet(Arrays.asList(new String[]{"a","c","d","g"}));
        Set<String> friend3 = new HashSet(Arrays.asList(new String[]{"c","f","e","g"}));
        friendWishList.put("friend1", friend1);
        friendWishList.put("friend2", friend2);
        friendWishList.put("friend3", friend3);
        BuddyList by = new BuddyList(myWishList, friendWishList);
        //by.getBuddies();
        System.out.println(by.recommend(2));
        System.out.println(by.recommend(3));
        System.out.println(by.recommend(10));

    }
}

import java.util.*;

public class Page {
//    The input is sorted by the score, because it is already sorted, we add all input to the iterator,
//    then we traverse the iterator from the start, and we use a list of Integer to store the
//    visited hostId, when we meet a number not in the visited
//    we add the current hostId string to the result list and the current hostId to the visited list,
//    and the remove corresponding string form input until we fill the current page, if we have
//    traverse all the string but not fill the current page, we will update input iterator and
//    find from the begining iterator.

//    //T:O(l^n) l is input.size()/pagesize, n is input.size()
    //we firstly consider putting different hostId in one page, if we don't have enough different hostId for one page,
    //we will add the hostId in order
    //I'd like to use iterator to solve this question, because iterator could conveniently remove and check next element
    //each time we traverse the whole iterator, which means we reach the end, I will update the iterator

    public static List<String> displayPages(List<String> input, int pageSize) {
        List<String> res = new ArrayList<>();
        if (input == null || input.size() == 0) {
            return res;
        }
        List<String> visited = new ArrayList<>();
        Iterator<String> iter = input.iterator();
        boolean reachEnd = false;
        while (iter.hasNext()) {
            String curr = iter.next();
            String hostId = curr.split(",")[0];
            if (!visited.contains(hostId) || reachEnd) {
                res.add(curr);
                visited.add(hostId);
                iter.remove();
            }
            if (visited.size() == pageSize) {
                visited.clear();
                reachEnd = false;
                if (!input.isEmpty()) {
                    res.add(" ");
                }
                iter = input.iterator();
            }
            if (!iter.hasNext()) {
                iter = input.iterator();
                reachEnd = true;
            }
        }
        return res;
    }


    public static void main(String[] args) {
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
//        input.add("1, 100");//
//        input.add("1, 98");//
//        input.add("1, 97");//
//        input.add("1, 99");//
//        input.add("2, 96");//
//        input.add("1, 95");//
//        input.add("2, 94");//
//        input.add("3, 10");//
//        input.add("2, 9");//
//        input.add("2, 8");//
//        input.add("3, 7");//
//        input.add("3, 6");//
//        input.add("4, 5");//
//        input.add("3, 4");
//        input.add("4, 3");//
//        input.add("3, 2");
//        input.add("5, 1");//
    List<String> res = displayPages(input, 5);
    for(int i = 0; i < res.size(); i++)
    {
        System.out.println(res.get(i));
    }
}
}

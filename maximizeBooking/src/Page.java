//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class Page {
//    public static void main(String[] args) {
//        List<String> input = new ArrayList<>();
//        input.add("1, 100");
//        input.add("1, 98");
//        input.add("1, 97");
//        input.add("1, 99");
//        input.add("2, 96");
//        input.add("1, 95");
//        input.add("2, 94");
//        input.add("3, 10");
//        input.add("2, 9");
//        input.add("2, 8");
//        input.add("3, 7");
//        input.add("3, 6");
//        input.add("4, 5");
//        input.add("3, 4");
//        input.add("4, 3");
//        input.add("3, 2");
//        input.add("5, 1");
//        System.out.println(pageSort(input, 5));
//    }
//
//    public static List<String> pageSort(List<String> input, int pageSize) {
//        List<String> res = new ArrayList<>();
//        if(input == null || input.size() == 0) return res;
//        Iterator<String> iter = input.iterator();
//        List<String> visited = new ArrayList<>();
//        boolean reachEnd = false;
//        while(iter.hasNext()) {
//            String curr = iter.next();
//            int hostId =curr.split(",");
//            if(!visited.contains(curr) || reachEnd) {
//                res.add(curr);
//                visited.add(curr);
//
//            }
//        }
//    }
//}

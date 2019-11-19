import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class maximizeBooking {
    public static void main(String[] args) {
        int[][] booking = {{1, 2}, {3, 5}, {4, 5}, {5, 9}};
        maximizeBooking(booking);
        System.out.println(num);
        System.out.println(res);
    }

    static List<List<Integer>> res;
    static int num;
    public static void maximizeBooking(int[][] booking) {
        res = new ArrayList<>();
        num = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b)->(a[0] - b[0]));
        for(int[] each : booking) {
            pq.offer(each);
        }
        int[] first = pq.poll();
        int start = first[0];
        int end = first[1];

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currStart = curr[0];
            int currEnd = curr[1];
            if(currStart >= end) {
                List<Integer> find = new ArrayList<>();
                find.add(start);
                find.add(end);
                res.add(find);
                num += (end - start);
                start = currStart;
                end = currEnd;
                continue;
            } else if(currEnd > end) {
                end = currEnd;
            }
        }
        List<Integer> last = new ArrayList<>();
        last.add(start);
        last.add(end);
        res.add(last);
        num+=(end - start);
    }
}

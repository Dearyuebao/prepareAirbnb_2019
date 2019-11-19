import java.util.*;

public class EmployeeFreeTime {
    static class Interval{
        int start;
        int end;
        public Interval(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }
    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> (a.start - b.start));
        //schedule.forEach(e -> pq.addAll(e));
        schedule.forEach(e -> pq.addAll(e));

        Interval pre = pq.poll();
        int preStart = pre.start;
        int preEnd = pre.end;
        int currStart;
        int currEnd;
        while(!pq.isEmpty()) {
            Interval curr = pq.poll();
            currStart = curr.start;
            currEnd = curr.end;
            if(currStart > preEnd) {
                res.add(new Interval(preEnd, currStart));
                preStart = currStart;
                preEnd = currEnd;
            } else if(currEnd > preEnd) {
                preEnd = currEnd;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Interval>> intervals = new ArrayList<>();
        List<Interval> inter1 = new ArrayList<>();
        inter1.add(new Interval(1, 3));
        inter1.add(new Interval(6, 7));
        List<Interval> inter2 = new ArrayList<>();
        inter1.add(new Interval(2, 4));
        List<Interval> inter3 = new ArrayList<>();
        inter1.add(new Interval(2, 5));
        inter1.add(new Interval(9, 12));
        intervals.add(inter1);
        intervals.add(inter2);
        intervals.add(inter3);
        List<Interval> res = employeeFreeTime(intervals);
        for(Interval each : res) {
            System.out.println("[" + each.start + "," + each.end + "]");
        }
    }
}

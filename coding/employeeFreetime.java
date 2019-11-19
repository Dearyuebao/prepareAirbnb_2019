/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start,int _end) {
        start = _start;
        end = _end;
    }
};
*/

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
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

}
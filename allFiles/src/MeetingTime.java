import java.util.*;

public class MeetingTime {
    static class Interval{
        int start;
        int end;
        public Interval(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    static class Point implements Comparable<Point>{
        int time;
        boolean isStart;
        public Point(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(Point that) {
            if(this.time != that.time || this.isStart == that.isStart) {
                return this.time - that.time;
            } else {
                return this.isStart? -1:1;
            }
        }

    }


        public static List<Interval> getAvailableIntervals(List<List<Interval>> intervals) {
            List<Point> points = new ArrayList<>();
            for(List<Interval> eachPerson: intervals) {
                for(Interval eachInterval:eachPerson) {
                    points.add(new Point(eachInterval.start, true));
                    points.add(new Point(eachInterval.end, false));
                }
            }
            List<Interval> res = new ArrayList<>();

            Collections.sort(points);
            int countOfBusy = 0;
            Integer avalaibleStart = null;
            for(int i = 0; i < points.size(); i++) {
                Point curr = points.get(i);
                if(curr.isStart) {
                    countOfBusy++;
                    if(avalaibleStart != null && countOfBusy == 1) {
                        res.add(new Interval(avalaibleStart, curr.time));
                        avalaibleStart = null;
                    }
                } else {
                    countOfBusy--;
                    if(countOfBusy == 0 && i < points.size() - 1) {
                        avalaibleStart = curr.time;
                    }
                }
            }
            return res;
        }

        public static List<Interval> getAvailableFollow(List<List<Interval>> intervals, int k) {
            List<Point> points = new ArrayList<>();
            for(List<Interval> eachPerson: intervals) {
                for(Interval eachInterval:eachPerson) {
                    points.add(new Point(eachInterval.start, true));
                    points.add(new Point(eachInterval.end, false));
                }
            }
            List<Interval> res = new ArrayList<>();

            Collections.sort(points);
            int countOfBusy = 0;
            Integer avalaibleStart = null;
            for(int i = 0; i < points.size(); i++) {
                Point curr = points.get(i);
                if(curr.isStart) {
                    countOfBusy++;
                    if(avalaibleStart == null && i == 0 && countOfBusy <= intervals.size() - k) {
                        avalaibleStart = curr.time;
                    }
                    else if(avalaibleStart != null && countOfBusy == intervals.size() - k + 1) {
                        res.add(new Interval(avalaibleStart, curr.time));
                        avalaibleStart = null;
                    }
                } else {
                    countOfBusy--;
                    if(i < points.size() - 1 && countOfBusy == intervals.size() - k) {
                        avalaibleStart = curr.time;
                    }
                    else if(avalaibleStart != null && i == points.size() - 1 && countOfBusy <= intervals.size() - k) {
                        res.add(new Interval(avalaibleStart, curr.time));
                        avalaibleStart = null;
                    }
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
        inter1.add(new Interval(1, 4));
        List<Interval> inter3 = new ArrayList<>();
        inter1.add(new Interval(2, 5));
        inter1.add(new Interval(9, 12));
        intervals.add(inter1);
        intervals.add(inter2);
        intervals.add(inter3);


        List<Interval> res = getAvailableIntervals(intervals);
        for(Interval each : res) {
            System.out.println("[" + each.start + "," + each.end + "]");
        }

        System.out.println(" ");
        List<Interval> res2 = getAvailableFollow(intervals, 2);
        for(Interval each : res2) {
            System.out.println("[" + each.start + "," + each.end + "]");
        }
    }
}

import java.util.*;

public class maximizeBooking {
    static class Interval implements Comparable<Interval>{
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Interval that) {
            int flag = this.end - that.end;
            if(flag == 0) {
                flag = this.start - that.start;
            }
            return flag;
        }
}

    static List<Interval> list = new ArrayList<>();
    public static void main(String[] args) {
        List<Interval> interval = new ArrayList<>();
//        interval.add(new Interval(4, 8));
//        interval.add(new Interval(1, 3));
//        interval.add(new Interval(2, 5));
//        interval.add(new Interval(4, 5));
//        interval.add(new Interval(6, 8));
//        interval.add(new Interval(7, 8));

        interval.add(new Interval(1, 2));
        interval.add(new Interval(3, 5));
        interval.add(new Interval(4, 5));
        interval.add(new Interval(5, 9));
        int res = booking(interval);
        System.out.println(res);
        for(Interval each : list) {
            System.out.println("[" + each.start + "," + each.end + "]");
        }
    }

    public static int booking(List<Interval> interval) {
        Collections.sort(interval);
        int n = interval.get(interval.size() - 1).end;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        dp[1] = 0;
        Map<Integer, List<Interval>> map = new HashMap<>();
        map.put(1,new ArrayList<Interval>());
        int index = 0;
        int i = 2;
        while(i <= n) {
            int last = interval.get(index).end;
            while(i < last) {
                dp[i] = dp[i - 1];
                map.put(i, new ArrayList<>());
                map.get(i).addAll(map.get(i - 1));
                i++;
            }
            dp[i] = dp[i - 1];
            map.put(i, new ArrayList<>());
            map.get(i).addAll(map.get(i - 1));
            while(index < interval.size() && interval.get(index).end == last) {
                if(dp[interval.get(index).start] + interval.get(index).end - interval.get(index).start > dp[i]){
//                dp[i] = Math.max(dp[i], dp[interval.get(index).start] + interval.get(index).end - interval.get(index).start);
                    dp[i] = dp[interval.get(index).start] + interval.get(index).end - interval.get(index).start;
                    map.get(i).clear();
                    map.get(i).addAll(map.get(interval.get(index).start));
                    map.get(i).add(interval.get(index));
                }
                index++;
            }
            i++;
        }
        list = map.get(n);
        return dp[n];
    }
}

package Airbnb;
import java.util.*;
/*
Using dynamic programing to solve.
First sort the input according to the end time,
Traverse the input array, dp start from 2, when n
is not the end time, dp[n] is equal to dp[n - 1]
otherwise dp[end] = Max(dp[start] + end - start, dp[end])

For the path, use HashMap to record the path, each
time is mapped to a path from start time to curent
end time, update the path when dp update.

 */
public class Dd_Booking {
    public static int booking(int[][] input) {
        Arrays.sort(input, (a, b) -> (a[1] - b[1]));
        int max = input[input.length - 1][1];
        int[] dp = new int[max + 1];
        int pre = 1;
        //HashMap<Integer, List<int[]>> pathMap = new HashMap<>();
        //pathMap.put(1, new ArrayList<>());
        for (int[] duration : input) {
            for (int i = pre + 1; i < duration[1]; i++) {
                dp[i] = dp[pre];
                //pathMap.put(i, new ArrayList<>(pathMap.get(pre)));
            }
            if (duration[1] - duration[0] + dp[duration[0]] > dp[duration[1]]) {
                dp[duration[1]] = duration[1] - duration[0] + dp[duration[0]];
                //List<int[]> list = new ArrayList<>(pathMap.get(duration[0]));
                //list.add(duration);
                //pathMap.put(duration[1], list);
            }
            pre = duration[1];
        }
        //for (int[] p: pathMap.get(max)) {
        //    for(int i : p) {
        //        System.out.print(i + " ");
        //    }
        //    System.out.println();
        //}
        return dp[max];
    }

    public static void main(String args[]) {
        int[][] input = {{1, 2}, {2, 4}, {3, 6}, {5, 8}, {6, 8}, {7, 12}, {8, 10}};
        System.out.println(booking(input));
    }
}

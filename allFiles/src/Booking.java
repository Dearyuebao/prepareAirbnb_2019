import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Booking {
    //sort the input array by end from low to high, use dp[n] to record the maximum booking days until the node n
    //we traverse the experience array, use a variable named "last" to record the former end point
    //each time we encounter a next end point, we update the value of "last"
    //this variable is used to fill the dp slot between the two ends
    //we update the dp[current end] by compare current dp[end] and dp[start] + end - start days
    public static int booking(int[][] input) {
        Arrays.sort(input, (a, b) -> (a[1] - b[1]));
        int max = input[input.length - 1][1];
        int[] dp = new int[max + 1];
        int pre = 1;
        HashMap<Integer, List<int[]>> pathMap = new HashMap<>();
        pathMap.put(1, new ArrayList<>());
        for (int[] duration : input) {
            int start = duration[0];
            int end = duration[1];
            for (int i = pre + 1; i <= end; i++) {
                dp[i] = dp[pre];
                pathMap.put(i, new ArrayList<>(pathMap.get(pre)));
            }
            if (end - start + dp[start] > dp[end]) {
                dp[end] = end - start + dp[start];
                List<int[]> list = new ArrayList<>(pathMap.get(duration[0]));
                list.add(duration);
                pathMap.put(end, list);
            }
            pre = end;
        }
        for (int[] p: pathMap.get(max)) {
            for(int i : p) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        return dp[max];
    }

    public static void main(String[] args) {
        int[][] input = {{1, 2}, {2, 4}, {3, 6}, {5, 8}, {6, 8}, {7, 12}, {8, 10}};
        System.out.println(booking(input));
    }
}

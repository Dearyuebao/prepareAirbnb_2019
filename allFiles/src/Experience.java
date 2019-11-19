import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Experience {
    //sort the input array by end from low to high, use dp[n] to record the maximum experience until the node n
    //we traverse the experience array, use a variable named "last" to record the former end point
    //each time we encounter a next end point, we update the value of "last"
    //this variable is used to fill the dp slot between the two ends
    //we update the dp[current end] by compare current dp[end] and dp[start] + experience
    public static int maxInterest(int[][] experience) {
        Arrays.sort(experience, (a, b) -> (a[1] - b[1]));
        int max = experience[experience.length - 1][1];
        int[] dp = new int[max + 1];
        int pre = 0;
        for (int[] e : experience) {
            int start = e[0];
            int end = e[1];
            int interest = e[2];
            for (int i = pre + 1; i <= end; i++) {
                dp[i] = dp[pre];
            }
            if (dp[start] + interest > dp[end]) {
                dp[end] = dp[start] + interest;
            }
            pre = end;
        }
        return dp[max];
    }

    //follow up : print path
    public static int maxInterestFollow(int[][] experience) {
        Arrays.sort(experience, (a, b) -> (a[1] - b[1]));
        int max = experience[experience.length - 1][1];
        int[] dp = new int[max + 1];
        int pre = 0;
        HashMap<Integer, List<int[]>> pathMap = new HashMap<>();
        pathMap.put(0, new ArrayList<>());
        for (int[] e : experience) {
            int start = e[0];
            int end = e[1];
            int interest = e[2];
            for (int i = pre + 1; i <= end; i++) {
                dp[i] = dp[pre];
                pathMap.put(i, new ArrayList<>(pathMap.get(pre)));
            }
            if (dp[start] + interest > dp[end]) {
                dp[end] = dp[start] + interest;
                List<int[]> list = new ArrayList<>(pathMap.get(start));
                list.add(e);
                pathMap.put(end, list);
            }
            pre = end;
        }
        for (int[] p : pathMap.get(max)) {
            for (int i : p) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        return dp[max];
    }

    public static void main(String[] args) {
        int[][] experience = {{2, 5, 8}, {3, 6, 10}, {6, 9, 4},{7, 10, 2}};
        int[][] e = {{0, 5, 8}, {2, 5, 10}, {3, 7, 9}, {7, 10, 2},{13, 18, 6},{17, 23, 9}};
        int res = maxInterest(e);
        System.out.println(res);
    }
}



package Airbnb;
/*
My assumption is: Handle the water drop by drop The water will first go left, if it cannot go left, it will go right. starting from position K,
initialize index equals to K,first go left,if the value of height in current position is lower than the heights of previous position,
update the index value,if the value of height in current position is higher than the heights of previous position, break the loop
 */
//time: O(V * K) space: O(V)
public class Aa_PourWater {
    public static void pourWater(int[] heights, int v, int k) {
        if (heights == null || heights.length == 0) return;
        int[] water = new int[heights.length];
        while(v > 0) {
            int index = k;
            int flag = 0;
            for (int i = k - 1; i >= -1; i--) {
                if (i == -1) {
                    index = -1;
                    break;
                }
                if (heights[i] + water[i] < heights[index] + water[index]) {
                    index = i;
                } else if (heights[i] + water[i] > heights[index] + water[index]) {
                    flag = 1;
                    break;
                }
            }
            if (index >= k) {
                for (int i = k + 1; i < heights.length; i++) {
                    if (heights[i] + water[i] < heights[index] + water[index]) {
                        index = i;
                    } else if (heights[i] + water[i] > heights[index] + water[index]) {
                        flag = 2;
                        break;
                    }
                }
            }

            if(flag == 1 && index < k || flag == 2 && index >= k) {
                water[index]++;
            }
            v--;
        }
        print(heights, water);
    }
    private static void print (int[] heights, int[] water) {
        int n = heights.length;
        int maxHeight = 0;
        for (int i = 0; i < n; i++) {
            maxHeight = Math.max(maxHeight, heights[i] + water[i]);
        }
        for (int height = maxHeight; height >= 0; height--) {
            for(int i = 0; i < n; i++) {
                if (height <= heights[i]) {
                    System.out.print("+");
                } else if (height > heights[i] && height <= heights[i] + water[i]) {
                    System.out.print("w");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String args[]) {

        int[] heights = new int[]{3,1,0,2,2,1};
        int v = 100;
        int k = 3;
        pourWater(heights, v,k);

    }
}   
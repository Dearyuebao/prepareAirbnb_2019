import java.util.Arrays;
//I assume that the water drop by drop, it will go left first, if it cannot go left,
// it will go to the right side.

public class PourWater {
    public static void main(String[] args) {
        int[] heights = {1, 3, 2, 1, 2, 4, 3};
        int V = 100;
        int K = 3;
        int[] res = pourWater(heights, V, K);
        for(int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }

        //follow up 1
        System.out.println();
        int[] original = {1, 3, 2, 1, 2, 4, 3};//I let the original same as the origin height
        //because heights will be changed in the pourWater function
        print(original, res);

        //follow up 2 : no wall
//        int[] res2 = pourWaterNoWall(heights, V, K);
//        for(int i = 0; i < res2.length; i++) {
//            System.out.print(res2[i] + " ");
//        }
//        System.out.println();
//        int[] original = {1, 3, 2, 1, 2, 4, 3};//I let the original same as the origin height
//        //because heights will be changed in the pourWater function
//        print(original, res2);
    }

    public static int[] pourWater(int[] heights, int V, int K) {
        if(heights == null || heights.length == 0 || V == 0) return heights;
        int l = K, r = K, n = heights.length;
        //n is used to check the right boundary
        while(V > 0) {
            while(l > 0 && heights[l] >= heights[l - 1]) l--;
            //we firstly move this drop to the possible left most
            while(l < K && heights[l] == heights[l + 1]) l++;
            //if the height left one is equal to its right neighbour,move right
            //the right side is the same way
            while(r < n - 1 && heights[r] >= heights[r + 1]) r++;
            while(r > K && heights[r] == heights[r - 1]) r--;

            if(l != K) {
                //which means that it can move left
                heights[l]++;
            } else {
                heights[r]++;
            }
            V--;
        }
        return heights;
    }
    //for this method, the time complexity is O(V * K), and the space complexity is O(1), because I do it in place

    //follow up 1 : print water
    public static void print(int[] heights, int[] waters) {
        //the output should be a rectangular
        int n = heights.length;//n represent the width of output
        int maxHeight = 0;//and maxHeight represents the heights of the output
        for(int i = 0; i < n ; i++) {
            maxHeight = Math.max(maxHeight, waters[i]);
        } //after traverse the waters array, I find the maxHeight
        //then I will draw the rectangular from top to down, and from left to right
        for(int height = maxHeight; height > 0;height-- ) {
            for(int i = 0; i < n; i++) {
                if(heights[i] >= height) {
                    System.out.print("+");//take care, not println
                    //if the original heights is greater than height, I add a plus sign here
                } else if(heights[i] < height && waters[i] >= height) {
                    System.out.print("W");
                } else {
                    System.out.print(" ");
                }
            }
            //after we finished one line, we change to a new line
            System.out.println();
        }
    }

    //follow up 2: still assume water will drop to left first, if it cannot go left, it will go right
    public static int[] pourWaterNoWall(int[] heights, int V, int K) {
        if(heights == null || heights.length == 0 || V == 0) return heights;
        int newK = K + 1;
        int l = newK, r = newK, n = heights.length + 2;
        int[] newHeights = new int[n];
        //because there is no wall on both side, so I let the left most and right most to be set as Integer.MIN_VALUE
        newHeights[0] = Integer.MIN_VALUE;
        newHeights[n - 1] = Integer.MIN_VALUE;
        //except the left most and right most, the newHeight array is the same as heights array
        for(int i = 1; i <= n - 2; i ++) {
            newHeights[i] = heights[i - 1];
        }
        //the following program is similar to the pourwater function
        while(V > 0) {
            while(l > 0 && newHeights[l] >= newHeights[l - 1]) l--;
            while(l > newK && newHeights[l] == newHeights[l + 1]) l++;
            while(r < n - 1 && newHeights[r] >= newHeights[r + 1]) r++;
            while(r > newK && newHeights[r] == newHeights[r - 1]) r--;

            if(l != newK) newHeights[l]++;
            else newHeights[r]++;
            V--;
        }
        for(int i = 1; i < n - 1; i++) {
            heights[i - 1] = newHeights[i];
        }
        return heights;
    }

    public static int[] pourWaterBothSide(int[] heights, int V, int K) {
        if(heights == null || heights.length == 0 || V == 0) {
            return heights;
        }

        int newK = K + 1;
        int l = newK, r = newK, n = heights.length + 2;
        int[] newHeights = new int[n];
        newHeights[0] = Integer.MAX_VALUE;
        newHeights[n - 1] = Integer.MAX_VALUE;
        for(int i = 1; i <= n - 2; i++) {
            newHeights[i] = heights[i - 1];
        }

        int flag = 1;
        int flagr = 1;
        while(V > 0) {
            if(flag == 1) {
                while(l > 0 && newHeights[l] >= newHeights[l - 1]) l--;
            }
            if(l == 1) flag = -1;
            if(flag == 1) {
                while(l < newK && newHeights[l] == newHeights[l + 1]) l++;
            }
            if(flagr == 1) {
                while(r < n - 1 && newHeights[r] >= newHeights[r + 1]) r++;
            }
            if(r == n - 2) flagr = -1;
            if(flagr == 1) {
                while(r > newK && newHeights[r] == newHeights[r - 1]) r--;
            }

            if(l != 1 && l != newK) newHeights[l]++;
            else if(r != n - 2 && r != newK) newHeights[r]++;
            else if(l == 1 && r == n - 2) break;
            else {
                if(flag == 1) newHeights[newK]++;
            }
            V--;
        }
        for(int i = 1; i < n - 1;i++) {
            heights[i - 1] = newHeights[i];
        }
        return heights;
    }

}


//pdy's solution
//package Airbnb;
///*
//My assumption is: Handle the water drop by drop The water will first go left, if it cannot go left, it will go right. starting from position K,
//initialize index equals to K,first go left,if the value of height in current position is lower than the heights of previous position,
//update the index value,if the value of height in current position is higher than the heights of previous position, break the loop
// */
////time: O(V * K) space: O(V)
//public class Aa_PourWater {
//    public static void pourWater(int[] heights, int v, int k) {
//        if (heights == null || heights.length == 0) return;
//        int[] water = new int[heights.length];
//        while(v > 0) {
//            int index = k;
//            int flag = 0;
//            for (int i = k - 1; i >= -1; i--) {
//                if (i == -1) {
//                    index = -1;
//                    break;
//                }
//                if (heights[i] + water[i] < heights[index] + water[index]) {
//                    index = i;
//                } else if (heights[i] + water[i] > heights[index] + water[index]) {
//                    flag = 1;
//                    break;
//                }
//            }
//            if (index >= k) {
//                for (int i = k + 1; i < heights.length; i++) {
//                    if (heights[i] + water[i] < heights[index] + water[index]) {
//                        index = i;
//                    } else if (heights[i] + water[i] > heights[index] + water[index]) {
//                        flag = 2;
//                        break;
//                    }
//                }
//            }
//
//            if(flag == 1 && index < k || flag == 2 && index >= k) {
//                water[index]++;
//            }
//            v--;
//        }
//        print(heights, water);
//    }
//    private static void print (int[] heights, int[] water) {
//        int n = heights.length;
//        int maxHeight = 0;
//        for (int i = 0; i < n; i++) {
//            maxHeight = Math.max(maxHeight, heights[i] + water[i]);
//        }
//        for (int height = maxHeight; height >= 0; height--) {
//            for(int i = 0; i < n; i++) {
//                if (height <= heights[i]) {
//                    System.out.print("+");
//                } else if (height > heights[i] && height <= heights[i] + water[i]) {
//                    System.out.print("w");
//                } else {
//                    System.out.print(" ");
//                }
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }
//    public static void main(String args[]) {
//
//        int[] heights = new int[]{3,1,0,2,2,1};
//        int v = 100;
//        int k = 3;
//        pourWater(heights, v,k);
//
//    }
//}
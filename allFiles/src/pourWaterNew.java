public class pourWaterNew {
    public static int[] pourWater(int[] heights, int V, int K) {
        if(heights == null || heights.length == 0 || V == 0) return heights;
        int newK = K + 1;
        int l = newK, r = newK, n = heights.length + 2;
        int[] newHeights = new int[n];
        newHeights[0] = Integer.MIN_VALUE;
        newHeights[n - 1] = Integer.MIN_VALUE;
        for(int i = 1; i <= n - 2; i++) {
            newHeights[i] = heights[i - 1];
        }
        while(V > 0) {
            while(l > 0 && newHeights[l] >= newHeights[l - 1]) l--;
            while(l < newK && newHeights[l] == newHeights[l + 1]) l++;
            while(r < n - 1 && newHeights[r] >= newHeights[r + 1]) r++;
            while(r > newK && newHeights[r] == newHeights[r - 1]) r--;

            if(l != 0 && r != n - 1 && newHeights[l] <= newHeights[r]) newHeights[l]++;
            else if(l != 0 && r != n - 1 && newHeights[l] > newHeights[r]) newHeights[r]++;
            else if(l == 0 && r != n - 1) newHeights[r]++;
            else if(r == n - 1 && l != 0) newHeights[l]++;
            V--;
        }

        for(int i = 1; i < n - 1;i++) {
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

            if(l != 1 && l != newK && r != n - 2 && r != newK && newHeights[l] <= newHeights[r]) newHeights[l]++;
            else if(l != 1 && l != newK && r != n - 2 && r != newK && newHeights[l] > newHeights[r]) newHeights[r]++;
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

    public static void main(String[] args) {
        int[] heights = {1, 2, 2, 7, 2, 1, 6};
        int[] origin = {1, 2, 2, 7, 2, 1, 6};
        int V = 100;
        int K = 3;
        int[] res = pourWater(heights, V, K);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println();
        print(origin, res);
    }
}
//    /** Return the array of height after drop all the droplets based on the assumption:
//     * The both side of array are cliffs and the droplet would fall off the cliff and if
//     * can stay on the platform, it would stay at the lowest position of the two sides */
//    public int[] cliffWithLowest(int[] ground, int index, int num) {
//        int[] afterPour = Arrays.copyOf(ground, ground.length);
//        while (num-- > 0) {
//
//        while (num-- > 0) {
//                // try go left
//                int lowLeft = index;
//                boolean discardLeft = false;
//                for (int i = index - 1; i >= 0; --i) {
//                if (afterPour[i] > afterPour[i + 1]) {
//                break;
//                }
//                // if i == 0 and the height of 0 is not higher that the right slot, the droplet would be discarded
//                if (i == 0) {
//                discardLeft = true;
//                break;
//                }
//                if (afterPour[i] < afterPour[lowLeft]) {
//        lowLeft = i;
//        }
//        }
//        // try go right
//        int lowRight = index;
//        boolean discardRight = false;
//        for (int i = index + 1; i < afterPour.length; ++i) {
//        if (afterPour[i] > afterPour[i - 1]) {
//        break;
//
//                }
//                        if (i == afterPour.length - 1) {
//                        discardRight = true;
//                        break;
//                        }
//                        if (afterPour[lowRight] > afterPour[i]) {
//                        lowRight = i;
//                        }
//                        }
//                        // if the side has the lowest position won't let the droplet fall off
//                        if ((!discardLeft && (afterPour[lowLeft] <= afterPour[lowRight]))) {
//                        ++afterPour[lowLeft];
//                        } else if (!discardRight && (afterPour[lowRight] < afterPour[lowLeft])) {
//        ++afterPour[lowRight];
//        }
//        }
//        print(ground, afterPour);
//        return afterPour;
//        }
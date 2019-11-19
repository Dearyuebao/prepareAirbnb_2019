public class findMedian {
    /*
Using binary search, given a lower bound and upper
bound, initialize the median we guess, compare the
number int the array, count the how many number are
smaller than the guess, according to this, do binary
search.
time : O(nlogn)
 */

//binary search,
//each time assume the median and count how many number is not greater than the median, if it equals to the len/2

    static class findMedi{
        public double findMedi(int[] nums) {
            int len = nums.length;
            if(len % 2 == 1) return (double)search(nums, len /2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
            else return (double)(search(nums, len / 2, Integer.MIN_VALUE, Integer.MAX_VALUE) + search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE))/2;
        }

        public long search(int[] nums, int k, long small, long large) {
            if(small == large) return small;
            long guess = (small + large) / 2;
            int count = 0;
            long res = small;
            for(int num: nums) {
                if(num <= guess) {
                    count++;
                    res = Math.max(res, num);
                }
            }

            if(count == k) return res;
            else if(count < k) return search(nums, k, Math.max(res + 1, guess), large);
            else return search(nums, k, small, res);
        }
    }

    public static void main(String[] args) {
        findMedi fm = new findMedi();
        System.out.println(fm.findMedi(new int[]{-6, 18, 9}));
        System.out.println(fm.findMedi(new int[]{-20, 0, 7, 5}));
        System.out.println(fm.findMedi(new int[]{1, 2, 2, 2, 3, 3 }));
    }
}

//    public static double findMedian(int[] nums) {
//        int len = 0;
//        for (int i = 0; i < nums.length; i++) {
//            len++;
//        }
//        if (len % 2 == 0) return (double) (findKth(nums, len / 2, Integer.MIN_VALUE, Integer.MAX_VALUE) + findKth(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE)) / 2;
//        return (double) (findKth(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE));
//    }
//    public static long findKth(int[] nums, int k, long left, long right) {
//        if (left >= right) return left;
//        long guess = (right - left) / 2 + left;
//        long largestLess = left;
//        int count = 0;
//        for (int num : nums) {
//            if (num <= guess) {
//                count++;
//                largestLess = Math.max(num, largestLess);
//            }
//        }
//        if (count == k) return largestLess;
//        if (count < k) {
//            return findKth(nums, k,guess + 1, right);
//        }
//        return findKth(nums, k, left, largestLess);
//    }

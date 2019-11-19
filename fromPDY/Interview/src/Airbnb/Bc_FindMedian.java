package Airbnb;
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

public class Bc_FindMedian {
    public static double findMedian(int[] nums) {
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            len++;
        }
        if (len % 2 == 0) return (double) (findKth(nums, len / 2, Integer.MIN_VALUE, Integer.MAX_VALUE) + findKth(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE)) / 2;
        return (double) (findKth(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
    public static long findKth(int[] nums, int k, long left, long right) {
        if (left >= right) return left;
        long guess = (right - left) / 2 + left;
        long largestLess = left;
        int count = 0;
        for (int num : nums) {
            if (num <= guess) {
                count++;
                largestLess = Math.max(num, largestLess);
            }
        }
        if (count == k) return largestLess;
        if (count < k) {
            return findKth(nums, k,guess + 1, right);
        }
        return findKth(nums, k, left, largestLess);
    }
}

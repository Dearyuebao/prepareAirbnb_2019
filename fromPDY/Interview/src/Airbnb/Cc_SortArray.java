package Airbnb;
/*
Suppose nums[0...i-1], is wiggled, for position i
if it is odd, we already have nums[i - 1] <= nums[i - 2]
if nums[i - 1] >= nums[i], then we does not need to do anything
if nums[i - 1] < nums[i], then we swap element at i - 1 and i
Due to previous wiggled elements (nums[i - 2] <= nums[i - 1]),
we know after swap the sequence is ensured to be
nums[i - 2] < nums[i - 1] > nums[i], which is wiggled.
similarly,
if i is even, we already have, nums[i - 2] >= nums[i - 1],
if nums[i - 1] <= nums[i], pass
if nums[i - 1] > nums[i], after swap, we are sure to have
wiggled nums[i - 2] > nums[i - 1] > nums[i].
The same recursive solution applies to all the elements in the sequence, ensuring the algo success.
time : O(n) space: O(1)
The number on even index is larger than in odd index
Traverse the input array, fo the number on odd index
if it is large then the number before it, swap them
for the number in even index, if it is smaller than
the number before, swap them.
 */
public class Cc_SortArray {
    public static void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if(i % 2 == 1) {
                if (nums[i] > nums[i - 1]) {
                    swap(nums, i);
                }
            } else if (i != 0 && nums[i] < nums[i - 1]) {
                swap(nums, i);
            }
        }
    }
    public static void swap (int[] nums, int i) {
        int temp = nums[i];
        nums[i] = nums[i - 1];
        nums[i - 1] = temp;
    }
    public static void main(String args[]) {
        int[] num = {10, 5, 4, 7, 1, 3, 6, 2, 4, 5};
        sort(num);
        for (int i : num) {
            System.out.print(i + " ");
        }
    }
}

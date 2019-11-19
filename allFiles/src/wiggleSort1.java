public class wiggleSort1 {
    //even larger
    public static void wiggleSortEven(int[] nums) {
        //even >= odd <= even >= odd
        //because we want the even number to be no less than odd number
        //so we traverse the array, when we meet even number, it larger than
        //its former odd number, if it is not, we swap nums[i] and nums[i - 1]
        //for example, when we are at even number nums[i], it should be larger or equal to nums[i - 1],
        //if nums[i] < nums[i - 1], originally we have nums[i - 1] <= nums[i - 2], when we swap them, nums[i]
        // become smaller, so it must be still no larger than nums[i - 2]

        //the same way for odd number,
        //when we meet odd number, it should be no larger than it's former even number, if it is not,
        //we swap nums[i] and nums[i - 1]
        // nums[i] should be no larger than nums[i - 1], if nums[i] > nums[i - 1]
        //we have to swap nums[i] and nums[i - 1], originally we have nums[i - 1] >= nums[i - 2]
        //after we swap them, nums[i - 1] becomes larger, so it must still larger than nums[i - 2]

        //each time we compare it and swap current number with the former number, in which way we could
        //ensure that the whole array is wiggle sorted


        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 1) {
                if (nums[i - 1] < nums[i]) {
                    int tmp = nums[i - 1];
                    nums[i - 1] = nums[i];
                    nums[i] = tmp;
                }
            } else {
                if (i > 0 && nums[i - 1] > nums[i]) {
                    int tmp = nums[i - 1];
                    nums[i - 1] = nums[i];
                    nums[i] = tmp;
                }
            }
        }
    }

    //even <= odd >= even <= odd, odd is larger
    public static void wiggleSort(int[] nums) {
        //because we want the odd number to be no less than even number
        //so we traverse the array, when we meet even number, it should be smaller or equal to
        //its former odd number, if it is not, we swap nums[i] and nums[i - 1]
        //when we meet odd number, it should be no less than it's former even number, if it is not,
        //we swap nums[i] and nums[i - 1]
        //each time we compare it and swap current number with the former number, in which way we could
        //ensure that the whole array is wiggle sorted
        //for example, when we are at even number nums[i], it should be smaller or equal to nums[i - 1],
        //if nums[i] > nums[i - 1], originally we have nums[i - 1] > nums[i - 2], when we swap them, nums[i] //become larger, so it must be still larger than nums[i - 2]
        //the same way for odd number, nums[i] should be larger than nums[i - 1], if it is not,
        //we have to swap nums[i] and nums[i - 1], originally we have nums[i - 1] <= nums[i - 2]
        //after we swap them, nums[i - 1] becomes smaller, so it must still less than nums[i - 2]
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 1) {
                if (nums[i - 1] > nums[i]) {
                    int tmp = nums[i - 1];
                    nums[i - 1] = nums[i];
                    nums[i] = tmp;
                }
            } else {
                if (i > 0 && nums[i - 1] < nums[i]) {
                    int tmp = nums[i - 1];
                    nums[i - 1] = nums[i];
                    nums[i] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        wiggleSortEven(nums);
        for(int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ",");
        }
        wiggleSort(nums);
        System.out.println();
        for(int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ",");
        }
    }
}

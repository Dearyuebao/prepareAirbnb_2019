import java.util.Arrays;

public class wiggleSort {
//    Sort it to find mediAN, traverse the input, if it is larger than medium, insert it to even index from right to left
//    if it is smaller than mediAN, insert it to odd index from left to right
//    After this, put the mediAN to the remain position
    public static void wiggleSortEvenBig(int[] nums) {
        //奇数大的
        //大于中位数的从右往左放偶数位
        //小于中位数的从左往右放奇数位
        //而奇数大的：
        //大于中位数的从左往右放奇数位
        //小于中位数的从右往左放偶数位
        //T: O(nlogn) S:O(n)
        //even > odd
        Arrays.sort(nums);
        int midIndex = (nums.length - 1) / 2;
        int mid = nums[midIndex];
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, Integer.MAX_VALUE);
        int evenIndex = (len - 1) % 2 == 0 ? len - 1: len - 2;
        int oddIndex = 1;
        for(int i = 0; i < midIndex; i++) {
            res[oddIndex] = nums[i];
            oddIndex +=2;
        }
        for(int i = len - 1; i > midIndex; i--) {
            res[evenIndex] = nums[i];
            evenIndex -= 2;
        }
        for(int i = 0; i < len; i++) {
            if(res[i] == Integer.MAX_VALUE) res[i] = mid;
        }
        System.arraycopy(res, 0, nums, 0, len);
    }

    //time: O(n) space: O(1)
//        nums[0] < nums[1] > nums[2] < nums[3]...
//        (1) elements larger than the 'median' are put into the last even slots
//        (2) elements smaller than the 'median' are put into the first odd slots
//        (3) the medians are put into the remaining slots.
//        Using quick select to find the median, then using virtual index mapping to get the result.
//        quick select: use quick sort achieve quick select, larger than medium num put on the left, small than medium num put on the right
//        index mapping: (1 + 2*index) % (n | 1) => get mapped index => original index: 0,1,2,3,4,5 mapped index: 1,3,5,2,4,6
//        odd index is on the left, even index is on the right
//        After you get median element, the 'nums' is partially sorted such that the first half is smaller or equal
//        to the median, the second half is larger or equal to the median, i.e
//        if nums[mapped_index] > median: put it into odd index from left to right
//        if nums[mapped_index] < median: put it into even index form right to left

    public static void wiggleSortEvenBigFollow(int[] nums) {
        //i will use quick selection by implementing the findKthSmallest function
        int median = findKthSmallest(nums, (nums.length + 1) / 2);
        int n = nums.length;
        //I initialize left and right for virtual index mapping
        int left = 0, i = 0, right = n - 1;
        //we traverse the virtual mapping index from left to right
        while (i <= right) {
            //at this point, for our nums array, the first part is smaller than median
            //the second part is larger than median, and the median is in the middle
            //For our virtual index mapping, the first part index is odd number, and the second
            //part is even number
            //we want to put the smaller than median numbers in odd number, from left to right
            //and put larger than median numbers in even number, from right to left
            //In this way, the virtual mapping index store the right number, we just have to
            //make it right for the original index
            //Mapped_idx[Left] denotes the position where the next smaller-than median element  will be inserted.
            //Mapped_idx[Right] denotes the position where the next larger-than median element  will be inserted.
            if (nums[newIndex(i,n)] < median) {
                //so if the mapped_idx(i) is smaller than median, we could put this number at nums[mapped_idx(left)]
                swap(nums, newIndex(left++,n), newIndex(i++,n));
            }
            else if (nums[newIndex(i,n)] > median) {
                //so if the mapped_idx(i) is larger than median, we could put this number at nums[mapped_idx(right)]
                swap(nums, newIndex(right--,n), newIndex(i,n));
            }
            else {
                i++;
            }
        }
    }
    private static int newIndex(int index, int n) {
        //this is used to generate virtual index
        return (1 + 2*index) % (n | 1);
    }//0 1 2 3 4 5=>1 3 5 2 4 6

    public static int findKthSmallest(int[] nums, int k) {
        if(nums==null||nums.length==0) return 0;
        int left=0;
        int right=nums.length - 1;
        while(true){
            //the partion function will return the position of nums[left]
            int pos=partition(nums,left,right);
            if(pos+1==k){
                //if the position of nums[left] equals to the mid position, we find the median
                return nums[pos];
            }else if(pos+1>k){
                //if the position of nums[left] is larger than mid position, it means median is on the left side
                right=pos-1;
            }else{
                //if the position of nums[left] is smaller than mid position, it means median is on the right side
                //so we update the left
                left=pos+1;
            }
        }
    }
    public static int partition(int[] nums,int left,int right){
        //to find the position of nums[left]
        //I will put all the numbers less than nums[left] to the left , and all the numbers larger than nums[left] to the right
        int pivot=nums[left];
        int l=left+1;
        int r=right;
        while(l<=r){
            if(nums[l]>pivot&&nums[r]<pivot){
                swap(nums,l++,r--);
            }
            if(nums[l]<=pivot) l++;
            if(nums[r]>=pivot) r--;
        }
        swap(nums,left,r);
        return r;
    }
    public static void swap(int[] nums,int i,int j ){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

//example:
//    Original idx: 0    1    2    3    4    5
//    Mapped idx:   1    3    5    0    2    4
//    Array:        13   6    5    5    4    2
    public static void wiggleSortOddBig(int[] nums) {
        //T: O(nlogn) S:O(n)
        //even < odd
        Arrays.sort(nums);
        int midIndex = (nums.length - 1) / 2;
        int mid = nums[midIndex];
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, Integer.MAX_VALUE);
        int evenIndex = (len - 1) % 2 == 0 ? len - 1: len - 2;
        int oddIndex = 1;
        for(int i = 0; i < midIndex; i++) {
            res[evenIndex] = nums[i];
            evenIndex -=2;
        }
        for(int i = len - 1; i > midIndex; i--) {
            res[oddIndex] = nums[i];
            oddIndex += 2;
        }
        for(int i = 0; i < len; i++) {
            if(res[i] == Integer.MAX_VALUE) res[i] = mid;
        }
        System.arraycopy(res, 0, nums, 0, len);
    }





//    public void wiggleSortOddBigFollow(int[] nums) {
//        int median = findKthLargest(nums, (nums.length + 1) / 2);
//        int n = nums.length;
//
//        int left = 0, i = 0, right = n - 1;
//
//        while (i <= right) {
//
//            if (nums[newIndex(i,n)] > median) {
//                swap(nums, newIndex(left++,n), newIndex(i++,n));
//            }
//            else if (nums[newIndex(i,n)] < median) {
//                swap(nums, newIndex(right--,n), newIndex(i,n));
//            }
//            else {
//                i++;
//            }
//        }
//    }
//
//    private int newIndex(int index, int n) {
//        return (1 + 2*index) % (n | 1);
//    }
//
//    public int findKthLargest(int[] nums, int k) {
//        if(nums==null||nums.length==0) return 0;
//        int left=0;
//        int right=nums.length-1;
//        while(true){
//            int pos=partition(nums,left,right);
//            if(pos+1==k){
//                return nums[pos];
//            }else if(pos+1>k){
//                right=pos-1;
//            }else{
//                left=pos+1;
//            }
//
//
//        }
//
//    }
//    public int partition(int[] nums,int left,int right){
//        int privot=nums[left];
//        int l=left+1;
//        int r=right;
//        while(l<=r){
//            if(nums[l]<privot&&nums[r]>privot){
//                swap(nums,l++,r--);
//            }
//            if(nums[l]>=privot) l++;
//            if(nums[r]<=privot) r--;
//        }
//        swap(nums,left,r);
//        return r;
//    }
//    public void swap(int[] nums,int i,int j ){
//        int temp=nums[i];
//        nums[i]=nums[j];
//        nums[j]=temp;
//    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 9, 8, 7, 4, 1, 6};
        wiggleSortEvenBigFollow(nums);
        for(int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}

package Airbnb;

public class Cb_WiggleSort {

        public void wiggleSort(int[] nums) {
            int median = findKthLargest(nums, (nums.length + 1) / 2);
            int n = nums.length;

            int left = 0, i = 0, right = n - 1;

            while (i <= right) {

                if (nums[newIndex(i,n)] > median) {
                    swap(nums, newIndex(left++,n), newIndex(i++,n));
                }
                else if (nums[newIndex(i,n)] < median) {
                    swap(nums, newIndex(right--,n), newIndex(i,n));
                }
                else {
                    i++;
                }
            }
        }

        private int newIndex(int index, int n) {
            return (1 + 2*index) % (n | 1);
        }

        public int findKthLargest(int[] nums, int k) {
            if(nums==null||nums.length==0) return 0;
            int left=0;
            int right=nums.length-1;
            while(true){
                int pos=partition(nums,left,right);
                if(pos+1==k){
                    return nums[pos];
                }else if(pos+1>k){
                    right=pos-1;
                }else{
                    left=pos+1;
                }


            }

        }
        public int partition(int[] nums,int left,int right){
            int privot=nums[left];
            int l=left+1;
            int r=right;
            while(l<=r){
                if(nums[l]<privot&&nums[r]>privot){
                    swap(nums,l++,r--);
                }
                if(nums[l]>=privot) l++;
                if(nums[r]<=privot) r--;
            }
            swap(nums,left,r);
            return r;
        }
        public void swap(int[] nums,int i,int j ){
            int temp=nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
        }


}
//time: O(n) space: O(1)
//        nums[0] < nums[1] > nums[2] < nums[3]...
//        (1) elements smaller than the 'median' are put into the last even slots
//        (2) elements larger than the 'median' are put into the first odd slots
//        (3) the medians are put into the remaining slots.
//        Using quick select to find the median, then using virtual index mapping to get the result.
//        quick select: use quick sort achieve quick select, larger than medium num put on the left, small than medium num put on the right
//        index mapping: (1 + 2*index) % (n | 1) => get mapped index => original index: 0,1,2,3,4,5 mapped index: 1,3,5,2,4,6
//        odd index is on the left, even index is on the right
//        After you get median element, the 'nums' is partially sorted such that the first half is larger or equal
//        to the median, the second half is smaller or equal to the median, i.e
//        if nums[mapped_index] > median: put it into odd index from left to right
//        if nums[mapped_index] < median: put it into even index form right to left

//    public static void wiggleSort(int[] nums) {
//        int median = findKthSmallest(nums, (nums.length + 1) / 2);
//        int n = nums.length;
//        int left = 0, i = 0, right = n - 1;
//        while (i <= right) {
//            if (nums[newIndex(i,n)] < median) {
//                swap(nums, newIndex(left++,n), newIndex(i++,n));
//            }
//            else if (nums[newIndex(i,n)] > median) {
//                swap(nums, newIndex(right--,n), newIndex(i,n));
//            }
//            else {
//                i++;
//            }
//        }
//    }
//    private static int newIndex(int index, int n) {
//        return (1 + 2*index) % (n | 1);
//    }
//
//    public static int findKthSmallest(int[] nums, int k) {
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
//        }
//    }
//    public static int partition(int[] nums,int left,int right){
//        int privot=nums[left];
//        int l=left+1;
//        int r=right;
//        while(l<=r){
//            if(nums[l]>privot&&nums[r]<privot){
//                swap(nums,l++,r--);
//            }
//            if(nums[l]<=privot) l++;
//            if(nums[r]>=privot) r--;
//        }
//        swap(nums,left,r);
//        return r;
//    }
//    public static void swap(int[] nums,int i,int j ){
//        int temp=nums[i];
//        nums[i]=nums[j];
//        nums[j]=temp;
//    }
//    public static void main(String args[]) {
//        int[] nums = {1, 2, 3, 9, 8, 7, 4, 1, 6};
//        wiggleSort(nums);
//        for (int i = 0; i < nums.length; i++) {
//            System.out.print(nums[i] + " ");
//        }
//    }

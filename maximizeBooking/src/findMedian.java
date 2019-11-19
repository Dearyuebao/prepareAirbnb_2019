public class findMedian {

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

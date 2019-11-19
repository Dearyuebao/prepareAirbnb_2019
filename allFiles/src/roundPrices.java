import java.util.*;

public class roundPrices {
    static class Num{
        double val;
        double frac;
        int index;
        public Num(double price, int index) {
            this.val = price;
            this.frac = price - Math.floor(price);
            this.index = index;
        }
    }
        public static int[] roundPrice(double[] prices) {
            if(prices == null || prices.length == 0) {
                return new int[0];
            }
            double sum = 0;
            int roundSum = 0;
            int n = prices.length;
            int[] res = new int[n];
            Num[] num = new Num[n];
            for(int i = 0; i < n; i++) {
                num[i] = new Num(prices[i], i);
                sum += prices[i];
                roundSum += (int)Math.round(prices[i]);
                res[i] = (int)Math.round(prices[i]);
            }
            int sumRound = (int)Math.round(sum);
            if(sumRound == roundSum) return res;
            else if(roundSum < sumRound) {
                int count = sumRound - roundSum;
                Arrays.sort(num, (a, b)->(Double.compare(b.frac, a.frac)));
                for(int i = 0; i < n; i++) {
                    Num curr = num[i];
                    if(curr.frac < 0.5 && count > 0) {
                        res[curr.index] = (int)Math.ceil(curr.val);
                        count--;
                    }
                }
            } else {
                int count = roundSum - sumRound;
                Arrays.sort(num, (a, b)->(Double.compare(a.frac, b.frac)));
                for(int i = 0; i < n; i++) {
                    Num curr = num[i];
                    if(curr.frac >= 0.5 && count > 0) {
                        res[curr.index] = (int)Math.floor(curr.val);
                        count--;
                    }
                }
            }
            return res;
        }

    public static void main(String[] args) {
        double[] prices = {1.2, 2.3, 3.4};
        int[] res = roundPrice(prices);
        for(int r : res) {
            System.out.print(r + " ");
        }
        System.out.println();

        double[] prices2 = {2.5, 2.3, 3.1, 6.5};
        int[] res2 = roundPrice(prices2);
        for(int r : res2) {
            System.out.print(r + " ");
        }
        System.out.println();

        double[] prices3 = {2.9, 2.3, 1.4, 3, 6};


        int[] res3 = roundPrice(prices3);
        for (int r : res3) {
            System.out.print(r + " ");
        }
        System.out.println();

        double[] prices4 = {-0.4,1.3,1.3,1.3,1.3,1.3,1.3,1.3,1.3,1.3,1.3};
        int[] res4 = roundPrice(prices4);
        for (int r : res4) {
            System.out.print(r + " ");
        }
        System.out.println();

    }
}

//
//public class Pair{
//    double dif;
//    int index;
//    public Pair(double d,int i){
//        dif = d;
//        index = i;
//    }
//}
//
//    public int[] Solution(double[] input){
//        List<Pair> memo =  new ArrayList<>();
//        int[] res = new int[input.length];
//        double remain = 0.0;
//
//        for(int i = 0;i < input.length; i++){
//            double dif = input[i] - Math.floor(input[i]);
//            remain += dif;
//            Pair elem = new Pair(dif,i);
//            memo.add(elem);
//        }
//
//        int len = (int)Math.round(remain);
//        Collections.sort(memo, (a,b) -> (a.dif - b.dif > 0.0 ? -1 : 1));
//        for(int i = 0; i < len;i++){
//            //System.out.println(memo.get(i).dif);
//            res[memo.get(i).index] = (int)Math.ceil(input[memo.get(i).index]);
//        }
//        for(int i = len; i < input.length;i++){
//            //System.out.println(memo.get(i).dif);
//            res[memo.get(i).index] = (int)Math.floor(input[memo.get(i).index]);
//        }
//        return res;
//    }


//1058
//class Solution {
//    public String minimizeError(String[] prices, int target) {
//        if(prices == null || prices.length == 0) return "-1";
//        int n = prices.length;
//        Num[] num = new Num[n];
//        int[] res = new int[n];
//        int roundSum = 0;
//        double ans = 0.000;
//        for(int i = 0; i < n; i++) {
//            num[i] = new Num(Double.parseDouble(prices[i]), i);
//            roundSum += (int)Math.floor(Double.parseDouble(prices[i]));
//            res[i] = (int)Math.floor(Double.parseDouble(prices[i]));
//        }
//        if(roundSum == target) {
//            for(int i = 0 ; i < n; i++) {
//                Num cur = num[i];
//                ans += Math.abs(res[cur.index] - cur.val);
//            }
//            return String.format("%.3f",ans);
//        } else if(roundSum > target) {
//            return "-1";
//        } else {
//            Arrays.sort(num, (a, b)->Double.compare(b.frac, a.frac));
//            for(int i = 0; i < n;i++) {
//                Num curr = num[i];
//                if(curr.frac != 0){
//                    res[curr.index] = (int)Math.ceil(curr.val);
//                    roundSum++;
//                }
//                if(roundSum == target) {
//                    for(int j = 0 ; j < n; j++) {
//                        Num cur = num[j];
//                        ans += Math.abs(res[cur.index] - cur.val);
//                    }
//                    return String.format("%.3f",ans);
//                }
//            }
//        }
//        return "-1";
//
//    }
//    class Num{
//        double val;
//        double frac;
//        int index;
//        public Num(double price, int index) {
//            this.val = price;
//            this.frac = price - Math.floor(price);
//            this.index = index;
//        }
//    }
//}


//    public String minimizeError(String[] prices, int target) {
//        int n = prices.length;
//        Map<Integer, Double>[] dp = new HashMap[n + 1];
//        dp[0] = new HashMap<>();
//        dp[0].put(0, 0.0);
//        for (int i = 1; i <= n; i++) {
//            double num = Double.parseDouble(prices[i - 1]);
//            double upperCost = Math.ceil(num) - num;
//            int upper = (int)Math.ceil(num);
//            double lowerCost = num - Math.floor(num);
//            int lower = (int)Math.floor(num);
//            dp[i] = new HashMap<>();
//            for (Map.Entry<Integer, Double> entry : dp[i - 1].entrySet()) {
//                int upperKey = entry.getKey() + upper;
//                dp[i].put(upperKey, Math.min(dp[i].getOrDefault(upperKey, Double.MAX_VALUE), entry.getValue() + upperCost));
//                int lowerKey = entry.getKey() + lower;
//                dp[i].put(lowerKey, Math.min(dp[i].getOrDefault(lowerKey, Double.MAX_VALUE), entry.getValue() + lowerCost));
//            }
//        }
//        return dp[n].containsKey(target) ? String.format ("%.3f", dp[n].get(target)) : "-1";
//    }
//}
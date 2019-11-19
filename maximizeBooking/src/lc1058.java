import java.util.Arrays;

public class lc1058 {

        public String minimizeError(String[] prices, int target) {
            if(prices == null || prices.length == 0) return "-1";
            int n = prices.length;
            Num[] num = new Num[n];
            int[] res = new int[n];
            int roundSum = 0;
            double ans = 0.000;
            for(int i = 0; i < n; i++) {
                num[i] = new Num(Double.parseDouble(prices[i]), i);
                roundSum += (int)Math.floor(Double.parseDouble(prices[i]));
                res[i] = (int)Math.floor(Double.parseDouble(prices[i]));
            }
            if(roundSum == target) {
                for(int i = 0 ; i < n; i++) {
                    Num cur = num[i];
                    ans += Math.abs(res[cur.index] - cur.val);
                }
                return String.format("%.3f",ans);
            } else if(roundSum > target) {
                return "-1";
            } else {
                Arrays.sort(num, (a, b)->Double.compare(b.frac, a.frac));
                for(int i = 0; i < n;i++) {
                    Num curr = num[i];
                    if(curr.frac != 0){
                        res[curr.index] = (int)Math.ceil(curr.val);
                        roundSum++;
                    }
                    if(roundSum == target) {
                        for(int j = 0 ; j < n; j++) {
                            Num cur = num[j];
                            ans += Math.abs(res[cur.index] - cur.val);
                        }
                        return String.format("%.3f",ans);
                    }
                }
            }
            return "-1";

        }
        class Num{
            double val;
            double frac;
            int index;
            public Num(double price, int index) {
                this.val = price;
                this.frac = price - Math.floor(price);
                this.index = index;
            }
        }


}

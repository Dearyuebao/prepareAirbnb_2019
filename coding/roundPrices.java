import java.util.Arrays;

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

    static class roundPrice{
        public int[] round(double[] prices) {
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
                Arrays.sort(num, (a,b)->(Double.compare(b.frac, a.frac)));
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
    }

    public static void main(String[] args) {
        double[] prices = {1.2, 2.3, 3.4};
        roundPrice rp = new roundPrice();
        int[] res = rp.round(prices);
        for(int r : res) {
            System.out.print(r + " ");
        }
        System.out.println();

        double[] prices2 = {2.5, 2.3, 3.1, 6.5};
        roundPrice rp2 = new roundPrice();
        int[] res2 = rp2.round(prices2);
        for(int r : res2) {
            System.out.print(r + " ");
        }
        System.out.println();

        double[] prices3 = {2.9, 2.3, 1.4, 3, 6};

        roundPrice rp3 = new roundPrice();
    int[] res3 = rp3.round(prices3);
    for (int r : res3) {
      System.out.print(r + " ");
    }
    System.out.println();

    double[] prices4 = {-0.4,1.3,1.3,1.3,1.3,1.3,1.3,1.3,1.3,1.3,1.3};
        roundPrice rp4 = new roundPrice();
    int[] res4 = rp4.round(prices4);
    for (int r : res4) {
      System.out.print(r + " ");
    }
    System.out.println();

    }
}

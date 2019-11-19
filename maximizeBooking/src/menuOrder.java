import java.util.ArrayList;
import java.util.List;

public class menuOrder {

    public static void main(String[] args) {
        double[] prices = {10.32, 4.67, 9.76, 8.88, 7.82, 8.5, 2.38, 15};
        System.out.println(menuSelect(prices, 17.38));
    }

    public static List<List<Double>> menuSelect(double[] prices, double target) {
        List<List<Double>> res = new ArrayList<>();
        if(prices == null || prices.length == 0 || target < 0) {
            return res;
        }
        int n = prices.length;
        int[] changePrices = new int[n];
        int Target = (int) Math.round(target * 100);
        for(int i = 0; i < n; i++) {
            changePrices[i] = (int) Math.round(prices[i] * 100);
        }
        helper(res, changePrices, prices, Target, new ArrayList<Double>(), 0);
        return res;
    }

    public static void helper(List<List<Double>> res, int[] changePrices, double[] prices, int Target, ArrayList<Double> curr, int start) {
        if(Target == 0) {
            res.add(new ArrayList<Double>(curr));
            return;
        }
        for(int i = start; i < prices.length; i++) {
            if(i > start && changePrices[i] == changePrices[i - 1]) {
                continue;
            }
            if(changePrices[i] > Target) {
                break;
            }
            curr.add(prices[i]);
            helper(res, changePrices, prices, Target - changePrices[i], curr, i + 1);
            curr.remove(curr.size() - 1);
        }
    }

}

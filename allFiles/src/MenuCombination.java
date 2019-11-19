import java.util.*;

public class MenuCombination {


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

    //time : O(M * N) M : target value N: number of distinct coins
//space: O(M)
/*
When double and float plus or minus, we cannot guarantee the
precision, so what precision do you want?
1. Is the menu price unique? Cab I choose one menu multiple
times?
I use backtracking to solve it.
 */
//The price is not uniqule,only choose onece
//the price is unique, can choose multiple times
//search(res, centsPrices, i, centsTarget - centsPrices[i], curCombo, prices)
//    public static void main(String[] args) {
//        double[] prices = {2.40, 0.01, 6.00, 2.58};
//        List<List<Double>> result = menuComb(prices, 8.58);
//        for (int i = 0; i < result.size(); i++) {
//            System.out.println(i + "th result:");
//            for (int j = 0; j < result.get(i).size(); j++) {
//                System.out.println(result.get(i).get(j));
//            }
//        }
//
//    }
//    public static void search(List<List<Double>> res,
//                              int[] centsPrices, int start,
//                              int centsTarget,
//                              List<Double> curCombo,
//                              double[] prices) {
//        if (centsTarget == 0) {
//            res.add(new ArrayList<>(curCombo));
//            return;
//        }
//        for (int i = start; i < centsPrices.length; i++) {
//            if (i > start &&
//                    centsPrices[i] == centsPrices[i - 1]) {
//                continue;
//            }
//            if (centsPrices[i] > centsTarget) {
//                break;
//            }
//            curCombo.add(prices[i]);
//            search(res, centsPrices, i + 1,
//                    centsTarget - centsPrices[i],
//                    curCombo, prices);
//            curCombo.remove(curCombo.size() - 1);
//        }
//    }
//
//    public static List<List<Double>> menuComb(double[] prices,
//                                              double target) {
//        List<List<Double>> res = new ArrayList<>();
//        if (prices == null || prices.length == 0 || target <= 0) {
//            return res;
//        }
//        int centsTarget = (int) Math.round(target * 100);
//        Arrays.sort(prices);
//        int[] centsPrices = new int[prices.length];
//        for (int i = 0; i < prices.length; i++) {
//            centsPrices[i] = (int) Math.round(prices[i] * 100);
//        }
//        search(res, centsPrices, 0, centsTarget,
//                new ArrayList<>(), prices);
//        return res;
//
//    }
}

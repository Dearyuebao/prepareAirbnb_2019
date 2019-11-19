import java.util.*;

//T:O(2^n) because it is backtracking S:O(n)
//for this problem, if we can use all the card, we will win.
//my strategy is that each time, we choose as less card as possible to sum to the target
//and I will use backtracking to implement it
public class ShutTheBox {
            public static boolean solution(){
                Set<Integer> candidates = new HashSet<>();
                candidates.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9));
            while(candidates.size() > 0) {
                int dice1 = (int)(Math.random() * 6) + 1;;
                int dice2 = (int)(Math.random() * 6) + 1;;
                //the combination is the sum of two dices
                int target = dice1 + dice2;
                //the result is the best combination for the current target
                List<Integer> res = new ArrayList<>();
                int[] candidate = new int[candidates.size()];
                //this candidate array is one of the input of my combination sum function
                for(int i = 0; i < candidate.length; i++) {
                    candidate[i] = (int)candidates.toArray()[i];
                }
                //int[] candidate = candidates.toArray();
                helper(candidate, 0, target,res, new ArrayList<>());
                if(res.size() == 0) {
                    return false;
                }
                candidates.removeAll(res);
            }
            return true;
        }

            public static void helper(int[] candidate, int start, int target, List<Integer> res, List<Integer> curr) {
            if(target == 0) {
                //this is my strategy
                //if we never update the result, we put the first combination to the result
                if(res.size() == 0) res.addAll(curr);
                //if the current combination use less card than former combination, we update the result
                if(curr.size() < res.size()) {
                    res.clear();
                    res.addAll(curr);
                    return;
                }
            } else if(target > 0) {
                for(int i = start; i < candidate.length; i++) {
                    if(candidate[i] > target) {
                        break;
                    }
                    curr.add(candidate[i]);
                    helper(candidate, i + 1, target - candidate[i], res, curr);
                    curr.remove(curr.size() - 1);
                }
            }
        }
            public static boolean solution2(){
                Set<Integer> candidates = new HashSet<>();
                candidates.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9));
            while(candidates.size() > 0){
                int rand1 = (int)(Math.random() * 6) + 1;
                int rand2 = (int)(Math.random() * 6) + 1;
                int sum = rand1 + rand2;
                int res1= -1, res2 = -1;
                int flag = -1;
                //每次只选一张牌
                if(candidates.contains(sum)){
                    //加这个

                    candidates.remove(sum);
                    flag = 1;
                    continue;
                }
                //每次选两张牌
                for(int key:candidates){
                    if(candidates.contains(sum - key)){
                        //加这个
                        res1 = key;
                        res2 = sum - key;
                        flag = 1;
                        break;
                    }
                }
                if(flag == -1) return false;
                if(res1 < 0 || res2 < 0) return false;
                candidates.remove(res1);
                candidates.remove(res2);
            }
            return true;
        }
    public static boolean shutTheBox() {
        Set<Integer> tiles = new HashSet<>();
        tiles.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9));
        boolean oneDice = false;
        while (tiles.size() != 0) {
            oneDice = isOneDice(tiles);
            int dice1 = (int)(Math.random() * 6) + 1;
            int dice2 = oneDice ? 0 : (int)(Math.random() * 6) + 1;
            int sum = dice1 + dice2;
            // try the big number first
            if (tiles.contains(sum)) {
                tiles.remove(sum);
                continue;
            }
            // then try combination of two numbers
            boolean succeed = false;
            for (int num1 : tiles) {
                int num2 = sum - num1;
                if (tiles.contains(num2)) {
                    tiles.remove(num2);
                    tiles.remove(num1);
                    succeed = true;
                    break;
                }
            }
            if (succeed) {
                continue;
            } else {
                return false;
            }
        }
        return tiles.size() == 0;
    }

    /** Return the if the sum of remaining tiles is six or smaller */
    private static boolean isOneDice(Set<Integer> tiles) {
        int sum = 0;
        for (int i : tiles) {
            sum += i;
        }
        return sum <= 6;
    }

    public static void main(String[] args) {
        int count = 0;
        int num = 0;
        int game = 1000;
        for(int i = 0; i < game; i++) {
            if(solution2())
            {
                count++;
            }
            if(solution()) num++;
        }
        System.out.println((double)count/game * 100 + "%");
        System.out.println((double)num/game * 100 + "%");

    }


//    static public class shutBox {
//        //candidates is a set that represent all the cards, it contains 1-9 at first
//        //in the game process, I will remove used card from this set
//        Set<Integer> candidates = new HashSet<>();
//        public shutBox() {
//            for(int i = 1; i <= 9; i++) {
//                candidates.add(i);
//            }
//        }
//
//        public int rand() {
//            //this function is used to generate a random number between 1-6 to simulate the dice
//            return (int)(Math.random() * 6) + 1;
//        }
//            public boolean solution1() {
//        while (candidates.size() > 0) {
//            int rand1 = rand();
//            int rand2 = rand();
//            int sum = rand1 + rand2;
//            int res1 = -1, res2 = -1;
//            for (int key : candidates) {
//                if (candidates.contains(sum - key)) {
//                    res1 = key;
//                    res2 = sum - key;
//                    break;
//                }
//            }
//            if (res1 < 0 || res2 < 0) return false;
//            candidates.remove(res1);
//            candidates.remove(res2);
//        }
//        return true;
//    }
//        public boolean solution(){
//            while(candidates.size() > 0) {
//                int dice1 = rand();
//                int dice2 = rand();
//                //the combination is the sum of two dices
//                int target = dice1 + dice2;
//                //the result is the best combination for the current target
//                List<Integer> res = new ArrayList<>();
//                int[] candidate = new int[candidates.size()];
//                //this candidate array is one of the input of my combination sum function
//                for(int i = 0; i < candidate.length; i++) {
//                    candidate[i] = (int)candidates.toArray()[i];
//                }
//                //int[] candidate = candidates.toArray();
//                helper(candidate, 0, target,res, new ArrayList<>());
//                if(res.size() == 0) {
//                    return false;
//                }
//                candidates.removeAll(res);
//            }
//            return true;
//        }
//
//        //不RECURSION 每次只能选1 或 2 张
//        public boolean solution2(){
//
//            while(candidates.size() > 0){
//                int rand1 = rand();
//                int rand2 = rand();
//                int sum = rand1 + rand2;
//                int res1= -1, res2 = -1;
//                int flag = -1;
//                //每次只选一张牌
//                if(candidates.contains(sum)){
//                    //加这个
//
//                    candidates.remove(sum);
//                    flag = 1;
//                    continue;
//                }
//                //每次选两张牌
//                for(int key:candidates){
//                    if(candidates.contains(sum - key)){
//                        //加这个
//                        res1 = key;
//                        res2 = sum - key;
//                        flag = 1;
//                        break;
//                    }
//                }
//                if(flag == -1) return false;
//                if(res1 < 0 || res2 < 0) return false;
//                candidates.remove(res1);
//                candidates.remove(res2);
//            }
//            return true;
//        }
//
//
//        //this is my combiation sum function, I use backtracking
//        public void helper(int[] candidate, int start, int target, List<Integer> res, List<Integer> curr) {
//            if(target == 0) {
//                //this is my strategy
//                //if we never update the result, we put the first combination to the result
//                if(res.size() == 0) res.addAll(curr);
//                //if the current combination use less card than former combination, we update the result
//                if(curr.size() < res.size()) {
//                    res.clear();
//                    res.addAll(curr);
//                    return;
//                }
//            } else if(target > 0) {
//                for(int i = start; i < candidate.length; i++) {
//                    if(candidate[i] > target) {
//                        break;
//                    }
//                    curr.add(candidate[i]);
//                    helper(candidate, i + 1, target - candidate[i], res, curr);
//                    curr.remove(curr.size() - 1);
//                }
//            }
//        }
//    }


}

//nature's solution
//    public boolean solution2(){
//        while(candidate.size() > 0){
//            int rand1 = rand();
//            int rand2 = rand();
//            int sum = rand1 + rand2;
//            int res1= -1, res2 = -1;
//            if(candidate.contains(sum)){
//                candidate.remove(sum);
//                continue;
//            }
//            for(int key:candidate){
//                if(candidate.contains(sum - key)){
//                    res1 = key;
//                    res2 = sum - key;
//                    break;
//                }
//            }
//            if(res1 < 0 || res2 < 0) return false;
//            candidate.remove(res1);
//            candidate.remove(res2);
//        }
//        return true;
//    }


    //不RECURSION 每次只能选1 或 2 张
//    public boolean solution2(){
//    for(int i = 0; i < )
//        while(candidate.size() > 0){
//            int rand1 = rand();
//            int rand2 = rand();
//            int sum = rand1 + rand2;
//            int res1= -1, res2 = -1;
//            //每次只选一张牌
//            if(candidate.contains(sum)){
//                //加这个
//                System.out.println(sum);
//
//                candidate.remove(sum);
//                continue;
//            }
//            //每次选两张牌
//            for(int key:candidate){
//                if(candidate.contains(sum - key)){
//                    //加这个
//                    System.out.println(key, sum - key);
//                    res1 = key;
//                    res2 = sum - key;
//                    break;
//                }
//            }
//            if(res1 < 0 || res2 < 0) return false;
//            candidate.remove(res1);
//            candidate.remove(res2);
//        }
//        return true;
//    }
//
//
//    public boolean solution1() {
//        while (candidate.size() > 0) {
//            int rand1 = rand();
//            int rand2 = rand();
//            int sum = rand1 + rand2;
//            int res1 = -1, res2 = -1;
//            for (int key : candidate) {
//                if (candidate.contains(sum - key)) {
//                    res1 = key;
//                    res2 = sum - key;
//                    break;
//                }
//            }
//            if (res1 < 0 || res2 < 0) return false;
//            candidate.remove(res1);
//            candidate.remove(res2);
//        }
//        return true;
//    }

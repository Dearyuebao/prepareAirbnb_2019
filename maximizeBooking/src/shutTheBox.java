import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class shutTheBox {
    static public class shutBox {
        Set<Integer> candidates = new HashSet<>();
        public shutBox() {
            for(int i = 1; i <= 9; i++) {
                candidates.add(i);
            }
        }

        public int rand() {
            return (int)(Math.random() * 6) + 1;
        }

        public boolean solution(){
            while(candidates.size() > 0) {
                int dice1 = rand();
                int dice2 = rand();
                int target = dice1 + dice2;
                List<Integer> res = new ArrayList<>();
                int[] candidate = new int[candidates.size()];
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

        public void helper(int[] candidate, int start, int target, List<Integer> res, List<Integer> curr) {
            if(target == 0) {
                if(res.size() == 0) res.addAll(curr);
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
    }


        public static void main(String[] args) {
            shutBox box = new shutBox();
            int count = 0;
            for(int i = 0; i < 10; i++) {
                if(box.solution())
                {
                    count++;
                }
            }
            System.out.println(count);
        }


}

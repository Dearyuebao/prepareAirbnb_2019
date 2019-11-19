package Airbnb;
import java.util.*;
public class Dc_RoundRice {
    public class Pair{
        double dif;
        int index;
        public Pair(double d,int i){
            dif = d;
            index = i;
        }
    }

    public int[] Solution(double[] input){
        List<Pair> memo =  new ArrayList<>();
        int[] res = new int[input.length];
        double remain = 0.0;

        for(int i = 0;i < input.length; i++){
            double dif = input[i] - Math.floor(input[i]);
            remain += dif;
            Pair elem = new Pair(dif,i);
            memo.add(elem);
        }

        int len = (int)Math.round(remain);
        Collections.sort(memo, (a,b) -> (a.dif - b.dif > 0.0 ? -1 : 1));
        for(int i = 0; i < len;i++){
            //System.out.println(memo.get(i).dif);
            res[memo.get(i).index] = (int)Math.ceil(input[memo.get(i).index]);
        }
        for(int i = len; i < input.length;i++){
            //System.out.println(memo.get(i).dif);
            res[memo.get(i).index] = (int)Math.floor(input[memo.get(i).index]);
        }
        return res;
    }


}

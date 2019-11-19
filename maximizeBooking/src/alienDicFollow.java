import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class alienDicFollow {
    public static String alienOrder(String[] words) {

        HashMap<Character, HashSet<Character>> map = new HashMap<>();
        for(int i = 0;i < words.length; i++){
            for(int j = 0; j < words[i].length();j++){
                map.putIfAbsent(words[i].charAt(j),new HashSet<>());
                if(j == 0){
                    if(i > 0 && words[i].charAt(j) != words[i - 1].charAt(j)){
                        map.get(words[i].charAt(j)).add(words[i - 1].charAt(j));
                    }
                }else{
                    if(i > 0 &&
                            words[i - 1].length() > j &&
                            words[i].charAt(j - 1) == words[i - 1].charAt(j - 1) &&
                            words[i].charAt(j) != words[i - 1].charAt(j)){
                        map.get(words[i].charAt(j)).add(words[i - 1].charAt(j));
                    }
                }
            }
        }
        List<String> res = new ArrayList<>();
        helper(map,new StringBuilder(),res);
        for(String s:res) System.out.println(s);
        return res.get(0);
    }
    private static void helper(HashMap<Character,HashSet<Character>> map, StringBuilder elem, List<String> res){
        if(map.keySet().size() == 0){
            res.add(new String(elem.toString()));return;
        }
        for(char key:map.keySet()){
            if(map.get(key).size() == 0){
                HashMap<Character,HashSet<Character>> copy = new HashMap<>();
                for(char k:map.keySet()) copy.put(k, new HashSet<>(map.get(k)));
                copy.remove(key);
                for(char k:copy.keySet()){
                    if(copy.get(k).contains(key)){
                        copy.get(k).remove(key);
                    }
                }
                elem.append(key);
                helper(copy,elem,res);
                elem.delete(elem.length() - 1,elem.length());
            }
        }
    }

    public static void main(String[] args) {
        String[] dic = {"wrt", "wrf", "ett", "rftt"};
        System.out.println(alienOrder(dic));
    }
}


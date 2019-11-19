import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class palindromePairs {

    public static void main(String[] args) {
        String[] words = {"abcd","dcba","lls","s","sssll"};
        System.out.println(palindromePairs(words));
    }

    static class TrieNode {
        TrieNode[] next;
        int index;
        List<Integer> list;
        public TrieNode() {
            next = new TrieNode[26];
            index = -1;
            list = new ArrayList<>();
        }
    }

    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if(words == null || words.length == 0) return res;
        TrieNode root = new TrieNode();

        for(int i = 0; i < words.length; i++) {
            addWord(root, words[i], i);
        }

        for(int i = 0; i < words.length; i++) {
            search(root, words[i], i, res);
        }

        return res;
    }

    public static void addWord(TrieNode root, String word, int index) {
        for(int i = word.length() - 1; i >= 0; i--) {
            int place = word.charAt(i) - 'a';
            if(root.next[place] == null) {
                root.next[place] = new TrieNode();
            }

            if(isPalindrome(word, 0, i)) {
                root.list.add(index);
            }

            root = root.next[place];
        }
        root.index = index;
        root.list.add(index);
    }

    public static void search(TrieNode root, String word, int index, List<List<Integer>> res) {
        for(int i = 0; i < word.length(); i++) {
            if(root.index >=0 && index != root.index && isPalindrome(word, i, word.length() - 1)) {
                res.add(Arrays.asList(index, root.index));
            }

            int j = word.charAt(i) - 'a';
            if(root.next[j] != null) {
                root = root.next[j];
            } else {
                return;
            }
        }

        for(int each : root.list) {
            if(each != index) {
                res.add(Arrays.asList(index, each));
            }
        }
    }

    public static boolean isPalindrome(String word, int i , int j) {
        if(i > j) return false;
        while(i <= j) {
            if(word.charAt(i) != word.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}

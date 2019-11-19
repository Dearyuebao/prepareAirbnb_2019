package Airbnb;
import java.util.*;
/*
Build Trie, represent the words int dictionary using trie
For the board, start from every point, find all combination
from this point
Using recursion 1: start from(i, j) find all word combination
recursion 2: from current point, find all possible word index
 */

//For convenience of tracking words in board, I will build a trie to store words in dictionary.
//Recursion 1: traverse each coordinate on the board to check whether there exist a word in the dictionary which start
//from this coordinate, if there has such a path, we record the path in the paths list

//Recursion2 : for each stored path, find other words on the board and return the maximum

//还是用一个 Trie 来加速 Word 的查找
//第一个循环，遍历 board 上每一个点，然后从这里找第一个单词（因为第一个单词的选择会影响最终单词数量），开始第一个递归。
//第一个递归的作用是，从当前点开始，通过第二个递归拿到当前点可行的每一个单词。挨个放入，每放入一个更新当前 board 的使用情况，然后开始下一层搜索。
//第二个递归的作用是，从当前点开始，找所有可行的单词 indexes，为第一个递归提供选择



public class Bg_BoggleGame {
    class TrieNode {
        char val;
        boolean isWord;
        TrieNode[] children;
        TrieNode (char val) {
            this.val = val;
            this.isWord = false;
            this.children = new TrieNode[26];
        }
    }

    class Trie {
        TrieNode root;
        Trie() {
            this.root = new TrieNode('0');
        }
        public void insert(String word) {
            if (word == null || word.length() == 0) return;
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new TrieNode(ch);
                }
                node = node.children[ch - 'a'];
            }
            node.isWord = true;
        }
    }

    public void findAllWords (char[][] board, String[] dic) {
        List<String> res = new ArrayList<>();
        Trie trie = new Trie();
        for (String word : dic) {
            trie.insert(word);
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<String> path = new ArrayList<>();
                boolean[][] visited = new boolean[m][n];
                findWords(res, board, path, visited, i, j, trie.root);
            }
        }
        System.out.println(res);
    }

    public void findWords(List<String> res, char[][] board, List<String> words, boolean[][] visited, int x, int y, TrieNode root) {
        int m = board.length;
        int n = board[0].length;
        for (int i = x; i < m; i++) {
            for (int j = y; j < n; j++) {
                List<List<Integer>> nextWordIndex = new ArrayList<>();
                List<Integer> path = new ArrayList<>();
                getNextWord(nextWordIndex, path, board, visited, i, j, root);
                for (List<Integer> indexs: nextWordIndex) {
                    String word = "";
                    for (int index : indexs) {
                        int row = index / n;
                        int col = index % n;
                        visited[row][col] = true;
                        word += board[row][col];
                    }
                    words.add(word);
                    if (words.size() > res.size()) {
                        res.clear();
                        res.addAll(words);
                    }
                    findWords(res, board, words, visited, i, j, root);
                    for (int index : indexs) {//把这条线的都找完了， 就设为没visited
                        int row = index / n;
                        int col = index % n;
                        visited[row][col] = false;
                    }
                    words.remove(words.size() - 1);
                }
            }
            y = 0;
        }

    }

    public void getNextWord(List<List<Integer>> nextWordIndex, List<Integer> path, char[][] board, boolean[][] visited, int i, int j, TrieNode root) {
        int m = board.length;
        int n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] == true || root.children[board[i][j] - 'a'] == null) return;
        root = root.children[board[i][j] - 'a'];
        if (root.isWord) {
            List<Integer> newList = new ArrayList<>(path);
            newList.add(i * n + j);
            nextWordIndex.add(newList);
            return;
        }
        visited[i][j] = true;
        path.add(i * n + j);
        getNextWord(nextWordIndex, path, board, visited, i + 1, j, root);
        getNextWord(nextWordIndex, path, board, visited, i - 1, j, root);
        getNextWord(nextWordIndex, path, board, visited, i, j + 1, root);
        getNextWord(nextWordIndex, path, board, visited, i, j - 1, root);
        path.remove(path.size() - 1);
        visited[i][j] = true;
    }
//    public static void main(String[] args) {
//        char[][] board = new char[][]{
//                {'a', 'b', 'c'},
//                {'d', 'e', 'f'},
//                {'g', 'h', 'i'}
//        };
//        String[] words = new String[] {
//                "abc", "cfi", "beh", "defi", "gh"
//        };
//
//        Solution s = new Solution();
//        s.findAllWords(board, words);
//    }

}

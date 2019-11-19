import java.util.*;

public class BoggleGame {
    static class ReturnType{
        boolean hasPrefix;
        boolean hasWord;
        public ReturnType(boolean hasPrefix, boolean hasWord) {
            this.hasPrefix = hasPrefix;
            this.hasWord = hasWord;
        }
    }

    static class TrieNode{
        char c;
        boolean isEnd;
        Map<Character, TrieNode> children;
        public TrieNode(char c, boolean isEnd) {
            this.c = c;
            this.isEnd = isEnd;
            children = new HashMap<>();
        }
    }

    static class Trie{
        TrieNode root;
        public Trie(){
            this.root = new TrieNode(' ', false);
        }

        public void insert(String word) {
            TrieNode cur = root;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(!cur.children.containsKey(c)) {
                    cur.children.put(c, new TrieNode(c, false));
                }
                cur = cur.children.get(c);
            }
            cur.isEnd = true;
        }

        public ReturnType search(String word) {
            TrieNode cur = root;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(!cur.children.containsKey(c)) {
                    return new ReturnType(false, false);
                }
                cur = cur.children.get(c);
            }
            return new ReturnType(true, cur.isEnd);
        }
    }

    //为什么要建TRIE来存储DIC： 方便存储和查找
    static class boggle{
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        public List<String> findMostStr(char[][] board, Set<String> dict) {
            Trie trie = new Trie();
            for(String word: dict) {
                trie.insert(word);
            }

            List<List<int[]>> paths = new ArrayList<>();
            int m = board.length;
            int n = board[0].length;
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    boolean[][] visited = new boolean[m][n];
                    visited[i][j] = true;
                    List<int[]> curPath = new ArrayList<>();
                    curPath.add(new int[]{i, j});
                    dfs(paths, board, trie, visited, i,j, curPath);
                }
            }

            List<String> res = new ArrayList<>();
            searchWord(paths, board, res, 0, new ArrayList<String>(), new boolean[m][n]);
            return res;
        }

        public void searchWord(List<List<int[]>> paths, char[][] board, List<String> res, int start, List<String> curWord, boolean[][] visited) {
            if(start == paths.size()) {
                if(curWord.size() > res.size()) {
                    res.clear();
                    res.addAll(curWord);
                }
                return;
            }
            for(int i = start; i < paths.size(); i++) {
                boolean canUse = true;
                for(int[] coord: paths.get(i)) {
                    if(visited[coord[0]][coord[1]]) {
                        canUse = false;
                        break;
                    }
                }

                if(canUse) {
                    curWord.add(path2word(board, paths.get(i)));
                    for(int[] coord: paths.get(i)) {
                        visited[coord[0]][coord[1]] = true;
                    }
                    searchWord(paths, board, res, i + 1, curWord, visited);
                    curWord.remove(curWord.size() - 1);
                    for(int[] coord:paths.get(i)) {
                        visited[coord[0]][coord[1]] = false;
                    }
                }
            }
        }

        public void dfs(List<List<int[]>> paths, char[][] board, Trie trie, boolean[][] visited, int x, int y, List<int[]> curPath ) {
            String curWord = path2word(board, curPath);
            ReturnType flag = trie.search(curWord);
            if(!flag.hasPrefix){
                return ;
            }
            if(flag.hasWord) {
                paths.add(new ArrayList<>(curPath));
            }

            int m = board.length;
            int n = board[0].length;
            for(int[] dir:dirs) {
                int xx = x + dir[0];
                int yy = y + dir[1];

                if(xx < 0 || xx >= m || yy < 0 || yy >= n) continue;

                visited[xx][yy] = true;
                curPath.add(new int[]{xx,yy});
                dfs(paths, board, trie, visited, xx, yy, curPath);
                curPath.remove(curPath.size() - 1);
                visited[xx][yy] = false;
            }
        }

        public String path2word(char[][] board, List<int[]> path) {
            StringBuilder sb = new StringBuilder();
            for(int[] each:path) {
                sb.append(board[each[0]][each[1]]);
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'o','a','t','h'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        Set<String> dict = new HashSet<>();
        dict.add("oath");
        dict.add("pea");
        dict.add("eat");
        dict.add("rain");
        boggle game = new boggle();
        System.out.println(game.findMostStr(board, dict));
    }
}

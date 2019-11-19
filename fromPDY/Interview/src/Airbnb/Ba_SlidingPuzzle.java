package Airbnb;
import java.util.*;
/*
solve: represent the board as a string, Queue<String>
record the change of the string, HashSet<String>
record the string being visited, Queue<List<String>
record all possible path, then use BFS
 */
class Ba_SlidingPuzzle {
    int m;
    int n;
    String start;
    String target;
    int[][] pair = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public Ba_SlidingPuzzle(int m, int n) {
        this.m = m;
        this.n = n;
        start = "";
        target = "";
        for (int i = 0; i < m * n - 1; i++) {
            target += i + 1;
        }
        target += 0;
        start = target;
    }

    public void printBoard() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(start.charAt(i * n + j) + " ");
            }
            System.out.println();
        }
    }

    public void shuffle (){
        StringBuilder sb = new StringBuilder(start);
        for(int i = 0; i < m * n; i++) {
            int rand = (int)(Math.random() * (m * n - 1));
            sb.setCharAt(i, start.charAt(rand));
            sb.setCharAt(rand, start.charAt(i));
            start = sb.toString();
        }
    }

    public void move(String str) {
        int zeroIndex = start.indexOf('0');
        int x = zeroIndex / n;
        int y = zeroIndex % n;
        if (str.equals("up") && x - 1 >= 0) {
            start = swap(start, zeroIndex, (x - 1) * n + y);
        }else if (str.equals("down") && x + 1 < m) {
            start = swap(start, zeroIndex, (x + 1) * n + y);
        }else if (str.equals("left") && y - 1 >= 0) {
            start = swap(start, zeroIndex, x * n + y - 1);
        }else if (str.equals("right") && y + 1 < n) {
            start = swap(start, zeroIndex, x * n + y + 1);
        } else {
            System.out.println("ERROR MOVING");
        }
    }

    public String swap(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(i, str.charAt(j));
        sb.setCharAt(j, str.charAt(i));
        return sb.toString();
    }

    public int solve() {
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        Queue<List<String>> path = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        List<String> in = new ArrayList<>();
        in.add(start);
        path.offer(in);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                List<String> curPath = path.poll();
                if (cur.equals(target)) {
                    for (int j = 0; j < curPath.size(); j++) {
                        for (int r = 0; r < m; r++) {
                            for (int c = 0; c < n; c++) {
                                System.out.print(curPath.get(j).charAt(r * n + c) + " ");
                            }
                            System.out.println();
                        }
                        System.out.println();
                    }
                    return res;
                }

                int zeroIndex = cur.indexOf('0');
                int x = zeroIndex / n;
                int y = zeroIndex % n;
                for (int[] p : pair) {
                    int a = p[0];
                    int b = p[1];
                    if ( x + a < 0 || x + a >= m || y + b < 0 || y + b >= n) continue;
                    String next = swap(cur, zeroIndex, (x + a) * n + y + b);
                    if (visited.contains(next)) continue;
                    queue.offer(next);
                    visited.add(next);
                    List<String> newPath = new ArrayList<>(curPath);
                    newPath.add(next);
                    path.offer(newPath);
                }
            }
            res++;
        }
        return -1;
    }
}

//class Solution {
//    public static void main(String[] args) {
//        slidingBoard game = new slidingBoard(3, 3);
//        game.shuffle();
//        game.printBoard();
//        System.out.println();
//        game.move("up");
//        game.printBoard();
//        System.out.println();
//        game.move("left");
//        game.printBoard();
//        System.out.println();
//        game.move("down");
//        game.printBoard();
//        System.out.println();
//        game.move("right");
//        game.printBoard();
//        System.out.println();
//        System.out.println("Solving Path")
//        System.out.println(game.solve());
//    }
//}

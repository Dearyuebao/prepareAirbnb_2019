import java.util.*;

public class slidingPuzzle {
    //I will add function to print the board, shuffle the board, move
    static class SlidingPuzzle {
        int m;
        int n;
        String target;
        String start;
        int[][] pair = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        public SlidingPuzzle(int m, int n, String input) {
            this.m = m;
            this.n = n;
            start = "";
            target = "";
            for(int i = 0; i < m * n - 1; i++) {
                target += i + 1;
                target += ",";
            }
            target += 0;
            if(input.length() == 0) start = target;
            else start = input;
        }

        public void printBoard() {
            int[][] board = stringToMatrix(start);
            for(int i = 0; i < m; i ++) {
                for(int j = 0; j < n; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }
        public String matrixToString(int[][] matrix) {
            StringBuilder res = new StringBuilder();
            for(int i = 0; i < matrix.length; i++) {
                for(int j = 0; j < matrix[0].length; j++) {
                    res.append(matrix[i][j]).append(",");
                }
            }
            return res.substring(0, res.length() - 1).toString();
        }

        public int[][] stringToMatrix(String str) {
            int[][] res = new int[m][n];
            String[] parts = str.split(",");
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    res[i][j] = Integer.parseInt(parts[i * n + j]);
                }
            }
            return res;
        }
        public void shuffle() {
            int[][] startMatrix = stringToMatrix(start);
            int[][] build = startMatrix.clone();
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    int r = (int)(Math.random() * (m - 1));
                    int c = (int)(Math.random() * (n - 1));
                    int tmp = build[i][j];
                    build[i][j] = startMatrix[r][c];
                    build[r][c] = tmp;
                }
            }
            start = matrixToString(build);
        }

        public void move(String str) {
            int[][] startMatrix = stringToMatrix(start);
            int x = 0;
            int y = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (startMatrix[i][j] == 0) {
                        x = i;
                        y = j;
                        break;
                    }
                }
            }
            int tmp = startMatrix[x][y];
            if(str == "up" && x - 1 >= 0) {
                startMatrix[x][y] = startMatrix[x - 1][y];
                startMatrix[x - 1][y] = tmp;
            } else if(str == "down" && x + 1 < m) {
                startMatrix[x][y] = startMatrix[x + 1][y];
                startMatrix[x + 1][y] = tmp;
            } else if( str == "left" && y - 1 >= 0) {
                startMatrix[x][y] = startMatrix[x][y - 1];
                startMatrix[x][y - 1] = tmp;
            } else if( str == "right" && y + 1 < n) {
                startMatrix[x][y] = startMatrix[x][y + 1];
                startMatrix[x][y + 1] = tmp;
            } else System.out.println("ERROR MOVING");
            start = matrixToString(startMatrix);
        }

        public List<String> printPath() {
            int[][] startMatrix = stringToMatrix(start);
            int originX = 0;
            int originY = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (startMatrix[i][j] == 0) {
                        originX = i;
                        originY = j;
                        break;
                    }
                }
            }
            Queue<int[]> coord = new LinkedList<>();
            Queue<String> puzzle = new LinkedList<>();
            Queue<List<String>> path = new LinkedList<>();
            Set<String> visited = new HashSet<>();

            coord.offer(new int[]{originX, originY});
            String first = start;
            puzzle.add(first);
            visited.add(first);
            path.offer(new ArrayList<>());
            String[] turn = {"Up", "Down", "Right", "Left"};

            while (!puzzle.isEmpty()) {
                int size = puzzle.size();
                for (int i = 0; i < size; i++) {
                    String curr = puzzle.poll();
                    List<String> curPath = path.poll();
                    int[] curCoord = coord.poll();
                    int x = curCoord[0];
                    int y = curCoord[1];
                    if (curr.equals(target)) {
                        return curPath;
                    }

                    for (int j = 0; j < pair.length; j++) {
                        int xx = x + pair[j][0];
                        int yy = y + pair[j][1];

                        if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
                            continue;
                        }
                        int[][] newMatrix = stringToMatrix(curr);
                        int tmp = newMatrix[x][y];
                        newMatrix[x][y] = newMatrix[xx][yy];
                        newMatrix[xx][yy] = tmp;

                        String newString = matrixToString(newMatrix);
                        if (visited.contains(newString)) {
                            continue;
                        }
                        coord.offer(new int[]{xx, yy});
                        puzzle.offer(newString);
                        List<String> newPath = new ArrayList<String>(curPath);
                        newPath.add(turn[j]);
                        path.offer(newPath);
                        visited.add(newString);
                    }
                }
            }
            List<String> result= new ArrayList<>();
            return result;
        }
    }


    public static void main(String[] args) {
//        SlidingPuzzle puz = new SlidingPuzzle(4, 5, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,0,19");
        SlidingPuzzle puz = new SlidingPuzzle(3, 4, "1,2,3,4,5,6,7,8,9,10,0,11");
        puz.printBoard();
        //  puz.shuffle();
        //  System.out.println();
        //puz.printBoard();
        puz.move("up");
        System.out.println();
        puz.printBoard();
        puz.move("down");
        System.out.println();
        puz.printBoard();
        puz.move("left");
        System.out.println();
        puz.printBoard();
        puz.move("right");
        System.out.println();
        puz.printBoard();
        puz.move("some");
        System.out.println();
        List<String> res = puz.printPath();
        if(res.size() != 0) {
            System.out.println(res);
        } else System.out.println("cannot solve");
    }
}



// * https://en.wikipedia.org/wiki/15_puzzle
// * 就是wikipeida里的问题换成九宫格，有8个版
// * 这里我们假设空格为0，所以0周围的数字可以与其交换
// *
// * 最好的应该是A*算法，这里用BFS也是可以做的。最好不要DFS，可能会爆栈。
// * 面经里应该只需要判断是否能solve，其实打印出最短路径也是差不多的
// */


//import java.util.*;
//
//
//public class slidingPuzzle {
//    public static class EightPuzzle {
//        private static final int[][] DIRS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
//        private int[][] matrix;
//        private int m;
//        private int n;
//        private int originX;
//        private int originY;
//        public String recovered;
//
//        public EightPuzzle(int[][] matrix) {
//            this.matrix = matrix;
//            this.m = matrix.length;
//            this.n = matrix[0].length;
//            int[][] recoveredMatrix = new int[m][n];
//            for (int i = 0; i < m; i++) {
//                for (int j = 0; j < n; j++) {
//                    if (matrix[i][j] == 0) {
//                        this.originX = i;
//                        this.originY = j;
//                    }
//
//                    recoveredMatrix[i][j] = i * n + j + 1;
//                }
//            }
//            recoveredMatrix[m - 1][n - 1] = 0;
//            this.recovered = getMatrixString(recoveredMatrix);
//        }
//
//        // Check if it can be solved
//        public boolean canSolve() {
//            Queue<int[]> elementQ = new LinkedList<>();
//            Queue<String> matrixQ = new LinkedList<>();
//            Set<String> visited = new HashSet<>();
//
//            String stringMatrix = getMatrixString(matrix.clone());
//            elementQ.offer(new int[] { originX, originY });
//            matrixQ.offer(stringMatrix);
//            visited.add(stringMatrix);
//
//            while (!elementQ.isEmpty()) {
//                int size = elementQ.size();
//                for (int i = 0; i < size; i++) {
//                    int[] curElement = elementQ.poll();
//                    String curMatrixString = matrixQ.poll();
//                    int x = curElement[0];
//                    int y = curElement[1];
//
//                    if (curMatrixString.equals(recovered)) {
//                        return true;
//                    }
//
//                    for (int k = 0; k < DIRS.length; k++) {
//                        int xx = x + DIRS[k][0];
//                        int yy = y + DIRS[k][1];
//
//                        if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
//                            continue;
//                        }
//
//                        int[][] newMatrix = recoverMatrixString(curMatrixString);
//                        int temp = newMatrix[x][y];
//                        newMatrix[x][y] = newMatrix[xx][yy];
//                        newMatrix[xx][yy] = temp;
//                        String newMatrixString = getMatrixString(newMatrix);
//                        if (visited.contains(newMatrixString)) {
//                            continue;
//                        }
//
//                        elementQ.offer(new int[] { xx, yy });
//                        matrixQ.offer(newMatrixString);
//                        visited.add(newMatrixString);
//                    }
//                }
//            }
//
//            return false;
//        }
//
//        // Print shortest path
//        public List<String> getSolution() throws NoSuchMethodException {
//            String[] pathWord = { "Down", "Right", "Up", "Left" };
//
//            Queue<int[]> elementQ = new LinkedList<>();
//            Queue<String> matrixQ = new LinkedList<>();
//            Queue<List<String>> pathQ = new LinkedList<>();
//            Set<String> visited = new HashSet<>();
//
//            String stringMatrix = getMatrixString(matrix.clone());
//            elementQ.offer(new int[] { originX, originY });
//            matrixQ.offer(stringMatrix);
//            pathQ.offer(new ArrayList<>());
//            visited.add(stringMatrix);
//
//            while (!elementQ.isEmpty()) {
//                int size = elementQ.size();
//                for (int i = 0; i < size; i++) {
//                    int[] curElement = elementQ.poll();
//                    String curMatrixString = matrixQ.poll();
//                    List<String> curPath = pathQ.poll();
//                    int x = curElement[0];
//                    int y = curElement[1];
//
//                    if (curMatrixString.equals(recovered)) {
//                        return curPath;
//                    }
//
//                    for (int k = 0; k < DIRS.length; k++) {
//                        int xx = x + DIRS[k][0];
//                        int yy = y + DIRS[k][1];
//
//                        if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
//                            continue;
//                        }
//
//                        int[][] newMatrix = recoverMatrixString(curMatrixString);
//                        int temp = newMatrix[x][y];
//                        newMatrix[x][y] = newMatrix[xx][yy];
//                        newMatrix[xx][yy] = temp;
//                        String newMatrixString = getMatrixString(newMatrix);
//                        if (visited.contains(newMatrixString)) {
//                            continue;
//                        }
//
//                        List<String> newPath = new ArrayList<>(curPath);
//                        newPath.add(pathWord[k]);
//
//                        elementQ.offer(new int[] { xx, yy });
//                        matrixQ.offer(newMatrixString);
//                        pathQ.offer(newPath);
//                        visited.add(newMatrixString);
//                    }
//                }
//            }
//
////                return new ArrayList<>();
//            throw new NoSuchMethodException("no such path");
//        }
//
//        private String getMatrixString(int[][] matrix) {
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < m; i++) {
//                for (int j = 0; j < n; j++) {
//                    sb.append(matrix[i][j]).append(",");
//                }
//            }
//            return sb.substring(0, sb.length()).toString();
//        }
//
//        private int[][] recoverMatrixString(String str) {
//            String[] parts = str.split(",");
//            int[][] res = new int[m][n];
//            for (int i = 0; i < m; i++) {
//                for (int j = 0; j < n; j++) {
//                    res[i][j] = Integer.parseInt(parts[i * n + j]);
//                }
//            }
//            return res;
//        }
//    }
//    public static void main(String[] args) {
//    int[][] matrix = {
//            {3, 1, 4, 9},
//            {6, 2, 0, 10},
//            {7, 5, 8, 11}
//    };
////        int[][] matrix = {
////                {1,2,3},{5,4,0}
////        };
//        EightPuzzle ep = new EightPuzzle(matrix);
//        System.out.println(ep.canSolve());
//        try {
//            System.out.println(ep.getSolution());
//        } catch(NoSuchMethodException e) {
//            System.out.println(e);
//        }
//        System.out.println(ep.recovered);
//    }
//}


//pdy's
//package Airbnb;
//        import java.util.*;
///*
//solve: represent the board as a string, Queue<String>
//record the change of the string, HashSet<String>
//record the string being visited, Queue<List<String>
//record all possible path, then use BFS
// */
//class Ba_SlidingPuzzle {
//    int m;
//    int n;
//    String start;
//    String target;
//    int[][] pair = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
//    public Ba_SlidingPuzzle(int m, int n) {
//        this.m = m;
//        this.n = n;
//        start = "";
//        target = "";
//        for (int i = 0; i < m * n - 1; i++) {
//            target += i + 1;
//        }
//        target += 0;
//        start = target;
//    }
//
//    public void printBoard() {
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(start.charAt(i * n + j) + " ");
//            }
//            System.out.println();
//        }
//    }
//
//    public void shuffle (){
//        StringBuilder sb = new StringBuilder(start);
//        for(int i = 0; i < m * n; i++) {
//            int rand = (int)(Math.random() * (m * n - 1));
//            sb.setCharAt(i, start.charAt(rand));
//            sb.setCharAt(rand, start.charAt(i));
//            start = sb.toString();
//        }
//    }
//
//    public void move(String str) {
//        int zeroIndex = start.indexOf('0');
//        int x = zeroIndex / n;
//        int y = zeroIndex % n;
//        if (str.equals("up") && x - 1 >= 0) {
//            start = swap(start, zeroIndex, (x - 1) * n + y);
//        }else if (str.equals("down") && x + 1 < m) {
//            start = swap(start, zeroIndex, (x + 1) * n + y);
//        }else if (str.equals("left") && y - 1 >= 0) {
//            start = swap(start, zeroIndex, x * n + y - 1);
//        }else if (str.equals("right") && y + 1 < n) {
//            start = swap(start, zeroIndex, x * n + y + 1);
//        } else {
//            System.out.println("ERROR MOVING");
//        }
//    }
//
//    public String swap(String str, int i, int j) {
//        StringBuilder sb = new StringBuilder(str);
//        sb.setCharAt(i, str.charAt(j));
//        sb.setCharAt(j, str.charAt(i));
//        return sb.toString();
//    }
//
//    public int solve() {
//        Queue<String> queue = new LinkedList<>();
//        HashSet<String> visited = new HashSet<>();
//        Queue<List<String>> path = new LinkedList<>();
//        queue.offer(start);
//        visited.add(start);
//        List<String> in = new ArrayList<>();
//        in.add(start);
//        path.offer(in);
//        int res = 0;
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            for (int i = 0; i < size; i++) {
//                String cur = queue.poll();
//                List<String> curPath = path.poll();
//                if (cur.equals(target)) {
//                    for (int j = 0; j < curPath.size(); j++) {
//                        for (int r = 0; r < m; r++) {
//                            for (int c = 0; c < n; c++) {
//                                System.out.print(curPath.get(j).charAt(r * n + c) + " ");
//                            }
//                            System.out.println();
//                        }
//                        System.out.println();
//                    }
//                    return res;
//                }
//
//                int zeroIndex = cur.indexOf('0');
//                int x = zeroIndex / n;
//                int y = zeroIndex % n;
//                for (int[] p : pair) {
//                    int a = p[0];
//                    int b = p[1];
//                    if ( x + a < 0 || x + a >= m || y + b < 0 || y + b >= n) continue;
//                    String next = swap(cur, zeroIndex, (x + a) * n + y + b);
//                    if (visited.contains(next)) continue;
//                    queue.offer(next);
//                    visited.add(next);
//                    List<String> newPath = new ArrayList<>(curPath);
//                    newPath.add(next);
//                    path.offer(newPath);
//                }
//            }
//            res++;
//        }
//        return -1;
//    }
//}

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





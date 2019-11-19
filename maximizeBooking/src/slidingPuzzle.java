
// * https://en.wikipedia.org/wiki/15_puzzle
// * 就是wikipeida里的问题换成九宫格，有8个版
// * 这里我们假设空格为0，所以0周围的数字可以与其交换
// *
// * 最好的应该是A*算法，这里用BFS也是可以做的。最好不要DFS，可能会爆栈。
// * 面经里应该只需要判断是否能solve，其实打印出最短路径也是差不多的
// */


import java.util.*;

public class slidingPuzzle {
    static class Puzzle{
        int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int m;
        int n;
        int[][] matrix;
        int originX;
        int originY;
        String recover;

        public Puzzle(int[][] matrix) {
            this.matrix = matrix;
            this.m = matrix.length;
            this.n = matrix[0].length;
            int[][] recoverMatrix = new int[m][n];
            for(int i = 0; i < m;i++) {
                for(int j = 0; j < n; j++) {
                    if(matrix[i][j] == 0) {
                        this.originX = i;
                        this.originY = j;
                    }
                    recoverMatrix[i][j] = i * n + j + 1;
                }
            }
            recoverMatrix[m - 1][n - 1] = 0;
            this.recover = matrixToString(recoverMatrix);
        }

        public String matrixToString(int[][] matrix) {
            StringBuilder res = new StringBuilder();
            for(int i = 0; i < matrix.length; i++) {
                for(int j = 0; j < matrix[0].length; j++) {
                    res.append(matrix[i][j]).append(",");
                }
            }
            return res.toString();
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

        public boolean canSolve() {
            Queue<int[]> coord = new LinkedList<>();
            Queue<String> puzzle = new LinkedList<>();
            Set<String> visited = new HashSet<>();

            coord.offer(new int[]{originX, originY});
            String first = matrixToString(matrix.clone());
            puzzle.offer(first);
            visited.add(first);

            while(!coord.isEmpty()) {
                int size = coord.size();
                for(int i = 0; i < size; i++) {
                    String curr = puzzle.poll();
                    int[] curCoord = coord.poll();
                    int x = curCoord[0];
                    int y = curCoord[1];

                    if(curr.equals(recover)){
                        return true;
                    }

                    for(int k = 0; k < move.length; k++) {
                        int xx = x + move[k][0];
                        int yy = y + move[k][1];

                        if(xx < 0 || xx >= m || yy < 0 || yy >= n) {
                            continue;
                        }

                        int[][] newMatrix = stringToMatrix(curr);
                        int tmp = newMatrix[x][y];
                        newMatrix[x][y] = newMatrix[xx][yy];
                        newMatrix[xx][yy] = tmp;

                        String newString = matrixToString(newMatrix);
                        if(visited.contains(newString)) {
                            continue;
                        }
                        coord.offer(new int[]{xx, yy});
                        puzzle.offer(newString);
                        visited.add(newString);
                    }
                }
            }
            return false;
//            Queue<int[]> elementQ = new LinkedList<>();
//            Queue<String> matrixQ = new LinkedList<>();
//            Set<String> visited = new HashSet<>();
//
//            String stringMatrix = matrixToString(matrix.clone());
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
//                    if (curMatrixString.equals(recover)) {
//                        return true;
//                    }
//
//                    for (int k = 0; k < move.length; k++) {
//                        int xx = x + move[k][0];
//                        int yy = y + move[k][1];
//
//                        if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
//                            continue;
//                        }
//
//                        int[][] newMatrix = stringToMatrix(curMatrixString);
//                        int temp = newMatrix[x][y];
//                        newMatrix[x][y] = newMatrix[xx][yy];
//                        newMatrix[xx][yy] = temp;
//                        String newMatrixString = matrixToString(newMatrix);
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
        }

        public List<String> printPath() throws NoSuchMethodException {
            Queue<int[]> coord = new LinkedList<>();
            Queue<String> puzzle = new LinkedList<>();
            Queue<List<String>> path = new LinkedList<>();
            Set<String> visited = new HashSet<>();

            coord.offer(new int[]{originX, originY});
            String first = matrixToString(matrix.clone());
            puzzle.add(first);
            visited.add(first);
            path.offer(new ArrayList<>());
            String[] turn = {"Down", "Right", "Up", "Left"};

            while (!puzzle.isEmpty()) {
                int size = puzzle.size();
                for (int i = 0; i < size; i++) {
                    String curr = puzzle.poll();
                    List<String> curPath = path.poll();
                    int[] curCoord = coord.poll();
                    int x = curCoord[0];
                    int y = curCoord[1];
                    if (curr.equals(recover)) {
                        return curPath;
                    }

                    for (int j = 0; j < move.length; j++) {
                        int xx = x + move[j][0];
                        int yy = y + move[j][1];

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
            throw new NoSuchMethodException("No Such Path");
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {5, 4, 0}};
        int[][] matrixTwo = {{3,1,4}, {6,2,0},{7,5,8}};
        Puzzle p = new Puzzle(matrix);
        Puzzle p2 = new Puzzle(matrixTwo);

        //System.out.println(p.canSolve());
        System.out.println(p2.canSolve());
        try {
            System.out.println(p.printPath());
        } catch (NoSuchMethodException e){
            System.out.println(e);
        }
        System.out.println(p.recover);

        try {
            System.out.println(p2.printPath());
        } catch (NoSuchMethodException e){
            System.out.println(e);
        }
        System.out.println(p2.recover);
    }
}


import java.util.Arrays;
import java.util.PriorityQueue;

public class flights {
//    public static int networkDelayTime(int[][] times, int N, int K) {
//        K--;
//        int[] Distance = new int[N];
//        Arrays.fill(Distance, Integer.MAX_VALUE);
//        Distance[K] = 0;
//        int[][] graph = new int[N][N];
//        for(int[] each : times) {
//            graph[each[0] - 1][each[1] - 1] = each[2];
//            if(each[0] - 1 == K) {
//                Distance[each[1] - 1] = each[2];
//            }
//        }
//        //PriorityQueue<int[]> q = new PriorityQueue<>((a - b) -> a[0] - b[0]);
//        PriorityQueue<Integer> q = new PriorityQueue<>((a,b) -> a - b);
//        q.offer(K);
//        int res = 0;
//        boolean[] marked = new boolean[N];
//
//        while(!q.isEmpty()) {
//            int curr = q.poll();
//            marked[curr] = true;
//            res = Distance[curr];
//
//            for(int i = 0; i < N; i++) {
//                if( graph[curr][i] > 0) {
//                    if(!marked[i]) q.offer(i);
//                    Distance[i] = Math.min(Distance[i], Distance[curr] + graph[curr][i]);
//                }
//            }
//        }
//        for(int i = 0; i < N; i++) {
//            if(!marked[i]) return -1;
//        }
//        return res;
//    }

    public static int networkDelayTime(int[][] times, int N, int K) {
        K--;
        int[] Distance = new int[N];
        Arrays.fill(Distance, Integer.MAX_VALUE);
        Distance[K] = 0;
        int[][] graph = new int[N][N];
        for(int[] each : times) {
            graph[each[0] - 1][each[1] - 1] = each[2];
            if(each[0] - 1 == K) {
                Distance[each[1] - 1] = each[2];
            }
        }
        //PriorityQueue<int[]> q = new PriorityQueue<>((a - b) -> a[0] - b[0]);
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        q.offer(new int[]{0,K});
        int res = 0;
        boolean[] marked = new boolean[N];

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            if(marked[curr[1]]) continue;
            marked[curr[1]] = true;
            res = curr[0];

            for(int i = 0; i < N; i++) {
                if( graph[curr[1]][i] >= 0) {
                    Distance[i] = Math.min(Distance[i], curr[0] + graph[curr[1]][i]);
                    if(!marked[i]) q.offer(new int[]{Distance[i], i});
                }
            }
        }
        for(int i = 0; i < N; i++) {
            if(!marked[i]) return -1;
        }
        return res;
    }

    public static void main(String[] args) {
//        int[][] flights = {{1,5,66},{3,5,55},{4,3,29},{1,2,9},{3,4,10},{3,1,3},{2,3,78},{1,4,98},{4,5,21},{5,2,19},{5,1,76},{4,1,65},{3,2,27},{5,3,23},{5,4,12},{2,1,36},{4,2,75},{2,4,11},{1,3,30},{2,5,8}};
        int[][] flights = {{3,5,78},{2,1,1},{1,3,0},{4,3,59},{5,3,85},{5,2,22},{2,4,23},{1,4,43},{4,5,75},{5,1,15},{1,5,91},{4,1,16},{3,2,98},{3,4,22},{5,4,31},{1,2,0},{2,5,4},{4,2,51},{3,1,36},{2,3,59}};
        int n =  5;
        int k = 5;
        int res = networkDelayTime(flights, n, k);
        System.out.println(res);
    }
}

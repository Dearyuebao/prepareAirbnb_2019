package Airbnb;
import java.util.*;
/*time: O(E + VlogV), E: The number of edges, V the number of vertices,We need to find the route,with cheapest ticket,so it is equal to find the
shortest distance in a directed graph,so I will use dijkstra algorithm.
1. Use two dimensional array to build the graph,the first index is source city, the second index is the destination city,the value is the cost.
2. Use the PriorityQueue to implement small root heap,the element in the heap is an array, the length of the array is 3, the first one in the
array is the price,the second is the city name, the third is the remain stops
3. offer the src city to the queue, every time pop the lowest price one from the heap, if the city is equals to the dst, got the result.
 */

//this is a directed graph, we have weights on each edge, we have to find the least cost.  the first algorithm I come up with is dfs,
// I will use a variable named result to
// record the smallest cost. I will traverse the tree from source, when I meet the destination, if it is less than the result, we update
// the result to current cost. after we traverse the tree, we will get the least cost.
//the time complexity of dfs is :O(l ^ n) n is the number of nodes, and l is the average of each level.
//But I think this is not the best solution, the dijkstra algorithm is suiltable for finding least cost in graph.
//and the time T: O(E + VlogV) E is the number of edges, and V is the number of nodes, it's an improvement compared to dfs.

public class Ab_CheapestFlight {
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        for (int[] f : flights) {
            graph[f[0]][f[1]] = f[2];
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> (a[0] - b[0]));
        heap.offer(new int[]{0, src, K + 1});
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int price = cur[0], city = cur[1], remainStops = cur[2];
            if (city == dst) return price;
            if (remainStops > 0) {
                for (int i = 0; i < n; i++) {
                    if (graph[city][i] > 0) {
                        heap.offer(new int[] {price + graph[city][i], i, remainStops - 1});
                    }
                }
            }

        }
        return -1;
    }
    public static void main(String[] args) {
        int n = 4;
        int[][] flights = {{0,1,100},{0,2,1000},{0,3,1000},{1,2,200},{1,3,4000},{2,3,400}};
        int src = 0;
        int dst = 3;
        int k = 1;
        System.out.println(findCheapestPrice(n, flights, src, dst, k));

    }
}
/*
follow up: print the root
Pre city should be saved in the heap, use an array to
represent the path,the index of the array is current city,
the value of the array is the previous city, update the
path when there is a min cost previous city, so finally,
I can traverse the array from the last one to first one,
so I can get the path

public class B_CheapestFlight {
    //static int res = -1;
    //static List<Integer> list = new ArrayList<>();
    public static void findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        //int[] path = new int[n];
        //Arrays.fill(path, -1);
        //int[] min = new int[n];
        //Arrays.fill(min, Integer.MAX_VALUE);
        for (int[] f : flights) {
            graph[f[0]][f[1]] = f[2];
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b)->(a[0] - b[0]));
        //heap.offer(new int[] {0, src, K + 1,-1});
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int price = cur[0];
            int city = cur[1];
            int remainStops = cur[2];
            //int lastCity = cur[3];
            //if (min[city] > price) {
            //    path[city] = lastCity;
            //    min[city] = price;
            //}
            if (city == dst) {
                //list.add(dst);
                //int p = dst;
                //while (p > 0) {
                //    list.add(path[p]);
                //    p = path[p];
                //}
                //res = price;
                //break;
            }
            if (remainStop > 0) {
                for (int i = 0; i < n; i++) {
                    if (graph[city][i] > 0) {
                        //heap.offer(new int[]{cost + graph[city][i], i, remainStop - 1, city});
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        int n = 4;
        int[][] flights = {{0,1,100},{0,2,1000},{0,3,1000},{1,2,200},{1,3,4000},{2,3,400}};
        int src = 0;
        int dst = 3;
        int k = 1;
        findCheapestPrice(n, flights, src, dst, k);
        //System.out.println("minCost" + res);
        //System.out.println("path");
        //for (int i = list.size() - 1; i >= 0; i--) {
        //    System.out.print(list.get(i) + " ");

        //}
    }

}
 */

/*dfs
Using HashMap to record the graph, the city is mapped
to an array, which contains the next stop and the cost
using dfs to search the graph, when it arrives the
dst, update dst when the cost is smaller, after
searching the entire graph, got the result.
time: O(l^n) space: O(n)
static int res = Integer.MAX_VALUE;
    public static int DFS(int n, int[][] flights, int src, int dst, int K) {
        if(flights == null || flights.length == 0) return -1;
        HashMap<Integer,List<int[]>> map = new HashMap<>();
        for(int[] elem:flights){
            List<int[]> list = new ArrayList<>();
            if(map.containsKey(elem[0])) {
                list = map.get(elem[0]);
            }
            list.add(new int[]{elem[1],elem[2]});
            map.put(elem[0],list);
        }
        //HashMap<Integer, List<Integer>> path = new HashMap<>();
        //List<Integer> link = new ArrayList<>();
        //link.add(src);
        helper(K,src,dst,map,0) //path, //link);
        if(res == Integer.MAX_VALUE) return -1;
        //List<Integer> l = path.get(res);
        //for (int i = 0; i < l.size(); i++) {
        //  System.out.println(l.get(i));
        //}
        return res;
    }
    private static void helper(int K,int src, int dst, HashMap<Integer,List<int[]>> map,int sumNow, HashMap<Integer, //List<Integer>> path, //List<Integer> link){
        if(K < 0 || sumNow > res) return ;
        if(!map.containsKey(src)) return ;//dfs city after it
        List<int[]> list = map.get(src);
        for(int[] elem:list){
          //link.add(elem[0]);
            if(elem[0] == dst){
                if (sumNow + elem[1] < res) {
                  res = sumNow + elem[1];
                  //path.put(res, new ArrayList<>(link));
                }
            }else{
                helper(K - 1,elem[0],dst,map,sumNow + elem[1], //path, //link);
            }
          //link.remove(link.size() - 1);
        }
    }
   public static void main(String[] args) {
        int n = 4;
        int[][] flights = {{0,1,1000},{0,2,1000},{0,3,100},{1,2,200},{1,3,4000},{2,3,400}};
        int src = 0;
        int dst = 3;
        int k = 2;
        System.out.println(DFS(n, flights, src, dst, k));

    }
 */



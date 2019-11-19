package com.company;

import java.util.*;

public class Main {

    static class Num {
        int dis;
        int val;
        int last;

        public Num(int d, int v, int l) {
            this.dis = d;
            this.val = v;
            this.last = l;
        }
    }

//    public static int getMinCost(List<List<Integer>> wizards, int src, int dst) {
//        PriorityQueue<Num> pq = new PriorityQueue<Num>((a, b) -> a.dis - b.dis);
//        for(int each : wizards.get(src)) {
//            pq.offer(new Num((each - src)* (each - src), each));
//        }
//        HashSet<Integer> visited = new HashSet<>();
//        visited.add(src);
//
//        while(!pq.isEmpty()) {
//            Num curr = pq.poll();
//            visited.add(curr.val);
//            if(curr.val == dst) {
//                return curr.dis;
//            }
//            for(int each : wizards.get(curr.val)) {
//                if(!visited.contains(each)) {
//                    pq.offer(new Num(curr.dis + (each - curr.val) * (each - curr.val), each));
//                }
//            }
//        }
//        return Integer.MAX_VALUE;
//    }

    static int cost;
    static List<Integer> path = new ArrayList<>();
    public static void printPath(List<List<Integer>> wizards, int src, int dst) {
        PriorityQueue<Num> pq = new PriorityQueue<Num>((a, b) -> a.dis - b.dis);
        for(int each : wizards.get(src)) {
            pq.offer(new Num((each - src)* (each - src), each, src));
        }
        HashSet<Integer> visited = new HashSet<>();
        visited.add(src);
        int[] p = new int[wizards.size() + 1];
        Arrays.fill(p, -1);
        p[src] = src;

        while(!pq.isEmpty()) {
            Num curr = pq.poll();
            visited.add(curr.val);
            if(p[curr.val] == -1) {
                p[curr.val] = curr.last;
            }
            if(curr.val == dst) {
                cost = curr.dis;
                path.add(curr.val);
                int place = curr.val;
                while(p[place] != src) {
                    place = p[place];
                    path.add(place);
                }
                path.add(src);
                return;
            }
            for(int each : wizards.get(curr.val)) {
                if(!visited.contains(each)) {
                    pq.offer(new Num(curr.dis + (each - curr.val) * (each - curr.val), each, curr.val));
                }
            }
        }
        cost = Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
	// write your code here
        List<List<Integer>> list = new ArrayList<>();
//        list.add(Arrays.asList(1, 4, 9));
//        list.add(Arrays.asList(2));
//        list.add(Arrays.asList(3));
//        list.add(Arrays.asList(2));
//        list.add(Arrays.asList(5));
//        list.add(Arrays.asList(6));
//        list.add(Arrays.asList(7));
//        list.add(Arrays.asList(8));
//        list.add(Arrays.asList(9));

//        list.add(Arrays.asList(1,2));
//        list.add(Arrays.asList(3));
//        list.add(Arrays.asList(3,4));
//        list.add(Arrays.asList(4));

        list.add(Arrays.asList(1,2,3));
        list.add(Arrays.asList(8,6,4));
        list.add(Arrays.asList(7,8,3));
        list.add(Arrays.asList(8,1));
        list.add(Arrays.asList(6));
        list.add(Arrays.asList(8,7));
        list.add(Arrays.asList(9,4));
        list.add(Arrays.asList(4,6));
        list.add(Arrays.asList(1));
        list.add(Arrays.asList(1,4));
        printPath(list, 0, 9);
        System.out.println(cost);
        for(int i = path.size() - 1; i >= 0 ; i--) {
            System.out.print(path.get(i));
            System.out.print(" ");
        }
    }
}

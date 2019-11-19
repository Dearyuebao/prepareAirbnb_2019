package Airbnb;

import java.util.LinkedList;
import java.util.Queue;

public class S_CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        int num = numCourses;
        for (int[] p : prerequisites) {
            indegree[p[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int[] p : prerequisites) {
                if (p[1] == cur) {
                    indegree[p[0]]--;
                    if(indegree[p[0]] == 0) {
                        queue.offer(p[0]);
                    }
                }
            }
            num--;
        }
        return num == 0;
    }

}

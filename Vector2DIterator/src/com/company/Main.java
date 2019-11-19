package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static class Vector2DIterator implements Iterator<Integer> {
        private Integer cur;
        private int outerIndex;
        private int innerIndex;
        private int lastOuterIndex;
        private int lastInnerIndex;
        private List<List<Integer>> vector;

        public Vector2DIterator(List<List<Integer>> vec2d) {
            this.vector = vec2d;
            this.outerIndex = 0;
            this.innerIndex = 0;
            this.lastOuterIndex = -1;
            this.lastInnerIndex = -1;
            searchNext();
        }

        @Override
        public Integer next() {
            if(!hasNext()) {
                return null;
            }
            int temp = cur;
            lastOuterIndex = outerIndex;
            lastInnerIndex = innerIndex;
            innerIndex++;
            searchNext();
            return temp;
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public void remove() {
            if(lastOuterIndex == -1 && lastInnerIndex == -1) {
                throw new IllegalStateException("remove() can be called only once per call to next()");
            }

            vector.get(lastOuterIndex).remove(lastInnerIndex);
            if(lastOuterIndex == outerIndex) {
                innerIndex--;
            }
            lastOuterIndex = -1;
            lastInnerIndex = -1;
        }

        private void searchNext() {
            if(outerIndex < vector.size()) {
                if(innerIndex < vector.get(outerIndex).size()) {
                    cur = vector.get(outerIndex).get(innerIndex);
                } else {
                    outerIndex++;
                    innerIndex = 0;
                    searchNext();
                }
            } else {
                cur = null;
            }
        }

    }

    public static void main(String[] args) {
	// write your code here
        List<List<Integer>> matrix = new ArrayList<>();
        List<Integer> row1 = new ArrayList<>();
        row1.add(1);
        row1.add(2);
        matrix.add(row1);
        List<Integer> row2 = new ArrayList<>();
        row2.add(3);
        matrix.add(row2);
        List<Integer> row3 = new ArrayList<>();
        matrix.add(row3);
        List<Integer> row4 = new ArrayList<>();
        row4.add(4);
        row4.add(5);
        row4.add(6);
        matrix.add(row4);

        Vector2DIterator iter = new Vector2DIterator(matrix);
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        iter.remove();
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        iter.remove();
        System.out.println(iter.next());
        System.out.println(iter.next());
        iter.remove();
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        iter.remove();
       // iter.remove();
        System.out.println(iter.next());
        System.out.println(iter.hasNext());
        iter.remove();
    }
}

import java.util.*;
///**
// * Given an array of arrays, implement an iterator class to allow the client to traverse
// * and remove elements in the array list.
// * This iterator should provide three public class member functions:
// *
// * boolean hasNext()
// * return true if there is another element in the set
// *
// * int next()
// * return the value of the next element in the array
// *
// * void remove()
// * remove the last element returned by the iterator.
// * That is, remove the element that the previous next() returned
// * This method can be called only once per call to next(), otherwise an exception will be thrown.
// */

public class TwoDIterator {
    //two index: innerIndex, outerIndex
    /*
    Use two index. the first index represent the index
    int of the element in the List<Index>, the second
    index represent the index of each list.
     */
    public static class Vector2DIterator {
        int eleIndex;//this is the innerIndex
        int listIndex;//this is the outterIndex
        List<List<Integer>> vec;
        public Vector2DIterator (List<List<Integer>> vec2d) {
            eleIndex = 0;
            listIndex = 0;
            vec = vec2d;
        }

        public boolean hasNext() {
            while (listIndex < vec.size()){
                if (eleIndex < vec.get(listIndex).size()) {
                    return true;
                } else {
                    listIndex++;
                    eleIndex = 0;
                }
            }
            return false;
        }

        public int next() {
            if (hasNext()) return vec.get(listIndex).get(eleIndex++);
            return -1;
        }

        public void remove() {
            if (hasNext()) {
                if (eleIndex + 1 < vec.get(listIndex).size()) {
                    eleIndex++;
                } else {
                    listIndex++;
                    eleIndex = 0;
                }
            }
        }
    }
//    public void remove2(){
//        if(hasNext()){
//            if(indexEle < vec.get(indexList).size() - 1 ){
//                vec.get(indexList).remove(indexEle);
//            }else if(indexEle == vec.get(indexList).size() - 1){
//                vec.get(indexList).remove(indexEle);
//                if(vec.get(indexList).size() == 0) vec.remove(indexList);
//                else indexList++;
//                indexEle = 0;
//            }
//        }
//    }



/*
class Solution {
  public static void main(String[] args) {
    List<List<Integer>> input = new ArrayList<>();
    input.add(Arrays.asList(1,2));
    input.add(Arrays.asList(3));
    input.add(Arrays.asList(4,5,6));
    Bd_TwoDIterator t = new Bd_TwoDIterator(input);
    System.out.println(t.hasNext());
    System.out.println(t.next());
    System.out.println(t.hasNext());
    t.remove();
    System.out.println(t.next());

  }
}
 */


/*
public class Solution implements Iterator<Integer> {
    private Iterator<List<Integer>> rowIter;
    private Iterator<Integer> colIter;
    public Solution(List<List<Integer>> vec2d) {
        rowIter = vec2d.iterator();
        colIter = Collections.emptyIterator();
    }
    @Override
    public Integer next() {
        return colIter.next();
    }
    @Override
    public boolean hasNext() {
        while ((colIter == null || !colIter.hasNext()) && rowIter.hasNext()){
            colIter = rowIter.next().iterator();
        }
        return colIter != null && colIter.hasNext();
}
    @Override
    public void remove() {
        while (colIter == null && rowIter.hasNext())
            colIter = rowIter.next().iterator();
        if (colIter != null){
        colIter.remove();
    }
}


 */





    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(1,2));
        input.add(Arrays.asList(3));
        input.add(Arrays.asList(4,5,6));
        Vector2DIterator t = new Vector2DIterator(input);

        System.out.println(t.hasNext());
        System.out.println(t.next());
        t.remove();
        System.out.println(t.next());
        System.out.println(t.next());
        System.out.println(t.next());
        System.out.println(t.next());
        System.out.println(t.next());
        System.out.println(t.next());
        System.out.println(t.hasNext());
        System.out.println(t.next());
    }
}

//    List<List<Integer>> list = new ArrayList<>();
//    list.add(Arrays.asList(1,2));
//            list.add(Arrays.asList(3));
//            list.add(Arrays.asList(4,5,6));


//public static class Vector2DIterator implements Iterator<Integer> {
//    private Integer cur;
//    private int outerIndex;
//    private int innerIndex;
//    private int lastOuterIndex;
//    private int lastInnerIndex;
//    private List<List<Integer>> vector;
//
//    public Vector2DIterator(List<List<Integer>> vec2d) {
//        this.vector = vec2d;
//        this.outerIndex = 0;
//        this.innerIndex = 0;
//        this.lastOuterIndex = -1;
//        this.lastInnerIndex = -1;
//        searchNext();
//    }
//
//        @Override
//        public Integer next() {
//            if (!hasNext()) {
//                return null;
//            }
//            int temp = cur;
//            lastOuterIndex = outerIndex;
//            lastInnerIndex = innerIndex;
//            innerIndex++;
//            searchNext();
//            return temp;
//        }
//
//        @Override
//        public boolean hasNext() {
//            return cur != null;
//        }
//
//        @Override
//        public void remove() {
//            if (lastOuterIndex == -1 && lastInnerIndex == -1) {
//                throw new IllegalStateException("remove() can be called only once per call to next()");
//            }
//
//            vector.get(lastOuterIndex).remove(lastInnerIndex);
//            if (lastOuterIndex == outerIndex) {
//                innerIndex--;
//            }
//            lastOuterIndex = -1;
//            lastInnerIndex = -1;
//        }
//
//
//
//
//
//        private void searchNext() {
//            if (outerIndex < vector.size()) {
//                if (innerIndex < vector.get(outerIndex).size()) {
//                    cur = vector.get(outerIndex).get(innerIndex);
//                } else {
//                    outerIndex++;
//                    innerIndex = 0;
//                    searchNext();
//                }
//            } else {
//                cur = null;
//            }
//        }
//    }





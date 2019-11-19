package Airbnb;
import java.util.*;
/*
Use two index. the first index represent the index
int of the element in the List<Index>, the second
index represent the index of each list.
 */
public class Bd_TwoDIterator {
    int eleIndex;
    int listIndex;
    List<List<Integer>> vec;
    public Bd_TwoDIterator (List<List<Integer>> vec2d) {
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





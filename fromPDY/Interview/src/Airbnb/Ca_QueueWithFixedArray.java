package Airbnb;
/*
加入：到了数组最后一个的时候，新建一个数组，把他加到后面数组第一个
取出：到了前面数组最后一个，当前数组第一个的时候，把前面一个数组删除
 */
import java.util.*;
public class Ca_QueueWithFixedArray {
    private int fixedSize;
    private int count;
    private int head;
    private int tail;
    private List<Object> headList;
    private List<Object> tailList;
    public Ca_QueueWithFixedArray(int fixedSize) {
        this.fixedSize = fixedSize;
        this.count = 0;
        this.head = -1;//0
        this.tail = -1;//0
        this.headList = new ArrayList<>();
        // this.headList.add(null);
        this.tailList = this.headList;
    }

    public void offer(int num) {
        count++;
        tail++;
        if (tail == fixedSize - 1) {
            List<Object> newList =  new ArrayList<>();
            // newList.add(tailList);
            newList.add(num);
            tailList.add(newList);
            tailList = newList;
            tail = 0;//1;
        }
        else {
            tailList.add(num);
        }

    }

    public Integer pop(){
        if(count == 0) return null;
        Integer num = (Integer) tailList.get(tail);
        tail--;
        count--;
        // System.out.println(tail);
        if(tail == 0 && tailList.get(tail) != null){
            List<Object> newList = (List<Object>)tailList.get(tail);
            tailList.clear();
            tailList = newList;
            tail = fixedSize - 2;
            // System.out.println(tail);
        }
        else if(count == 0){
            tailList.clear();
            //tailList.add(null);
            headList = tailList;
            head = -1; tail = -1;//0
        }

        return num;
    }

    public Integer poll() {
        if (count == 0) {
            return null;
        }
        head++;
        count--;

        if (head == fixedSize - 1) {
            List<Object> newList = (List<Object>)headList.get(head);
            headList.clear();
            headList = newList;
            head = 0;//1;
        }
        Integer num = (Integer) headList.get(head);
        if(count == 0){
            headList.clear();
            //headList.add(null);
            headList = tailList;
            head = -1;tail = -1;//0
        }

        return num;
    }

    public int size() {
        return count;
    }


}

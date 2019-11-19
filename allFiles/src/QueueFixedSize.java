import java.util.*;

public class QueueFixedSize {
    //offer: tail record the number in tailList, head record the number in headList
// offer : from tail   poll: from head   pop: from tail
// count: the total amount in the queue
// When we want to offer number in the queue, we operate the tailList, and the tailList is a List of Object, we use variable tail
// to record the number in tailList, if the number is greater than size - 1, we add the number to a newList, and the newList is
// added to the tailList, then we update the tailList to the newList, and reset the tail to zero.
// poll: is the similar way as on tailList, just change it to headList
// pop: first we check the size of the tailList, if the size is greater than 0, we remove the last number in tailList, but if it
// is null, we remove the last number in headList
// offer: tail record the number in tailList, head record the number in headList
    static class QueueWithFixedArray {
        private int fixedSize;

        private int count;
        private int head;
        private int tail;
        private List<Object> headList;
        private List<Object> tailList;

        public QueueWithFixedArray(int fixedSize) {
            this.fixedSize = fixedSize;
            this.count = 0;
            this.head = 0;
            this.tail = 0;
            this.headList = new ArrayList<>();
            this.tailList = this.headList;
        }

        public void offer(int num) {
            if (tail == fixedSize - 1) {
                List<Object> newList = new ArrayList<>();
                newList.add(num);
                tailList.add(newList);
                tailList = (List<Object>) tailList.get(tail);
                tail = 0;
            } else {
                tailList.add(num);
            }
            count++;//count is the total amount in the queue
            tail++;
        }

        public Integer poll() {
            if (count == 0) {
                return null;
            }
            if(head == fixedSize - 1) {
                //if the head arrive the tail of the array
                headList = (List<Object>) headList.get(head);
                head = 0;
            }
            count--;
            return (int)headList.get(head++);
        }

        public Integer pop() {
            //if the tailList is not null, we will pop element from the tailList first
            if(tailList.size() > 0) {
                int res = (int) tailList.get(tailList.size() - 1);
                tailList.remove(tailList.size() - 1);
                if(tailList.size() == 0) {
                    headList.remove(headList.size() - 1);
                }
                count = count - 1;
                return res;
            } else {
                if(headList.size() == 0) {
                    return null;
                } else {
                    int res = (int) headList.get(headList.size() - 1);
                    headList.remove(headList.size() - 1);
                    count = count - 1;
                    return res;
                }
            }

        }
    }

    public static void main(String[] args) {
        QueueWithFixedArray queue = new QueueWithFixedArray(4);
        System.out.println(queue.poll());
        queue.offer(1);
        queue.offer(2);
        // System.out.println(queue.poll());//head=1
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.poll());//head=0
        System.out.println(queue.poll());//head=1
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
//        try {
//            System.out.println(queue.pop());
//            System.out.println(queue.pop());
//            System.out.println(queue.pop());
//            System.out.println(queue.pop());
//            System.out.println(queue.pop());
//
//            System.out.println(queue.poll());
//            System.out.println(queue.poll());
//
//        } catch(NoSuchElementException e) {
//            System.out.println(e.toString());
//        }
//        try {
//            System.out.println(queue.poll());
//            System.out.println(queue.poll());
//            System.out.println(queue.poll());
//        } catch(NoSuchElementException e) {
//            System.out.println(e.toString());
//        }
    }


//class Main {
//  public static void main(String[] args) {
//    QueueWithFixedArray queue = new QueueWithFixedArray(5);
//    System.out.println(queue.poll());
//    queue.offer(1);
//    queue.offer(2);
//    System.out.println(queue.poll());
//    queue.offer(3);
//    queue.offer(4);
//    queue.offer(5);
//    queue.offer(6);
//    System.out.println(queue.poll());
//    System.out.println(queue.poll());
//    System.out.println(queue.poll());
//    System.out.println(queue.poll());
//    System.out.println(queue.poll());
//  }
//}
    }


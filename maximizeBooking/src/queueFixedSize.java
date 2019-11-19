import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class queueFixedSize {
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
            count++;
            tail++;
        }

        public Integer poll() {
            if (count == 0) {
                return null;
            }
            int num = Integer.MAX_VALUE;
            if(headList.size() >=  head + 1) {
                num = (int) headList.get(head);
            }
            head++;
            count--;

            if (head == fixedSize - 1) {
                if(headList.size() >= head + 1) {
                    List<Object> newList = (List<Object>) headList.get(head);
                    headList.clear();
                    headList = newList;
                }
                else {
                    List<Object> newList = new ArrayList<>();
                    headList.clear();
                    headList = newList;
                }


//                headList.clear();
//                headList = newList;
                head = 0;
            }

            if(num == Integer.MAX_VALUE) {
                throw new NoSuchElementException("no such element");
            } else {
                return num;
            }
        }

        public Integer pop() {
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
                    throw new NoSuchElementException("no such element");
                } else {
                    int res = (int) headList.get(headList.size() - 1);
                    headList.remove(headList.size() - 1);
                    count = count - 1;
                    return res;
                }
            }

        }

        public int size() {
            return count;
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
   // System.out.println(queue.poll());//head=2
//    System.out.println(queue.poll());//head=0
//    System.out.println(queue.poll());//head=1
//    System.out.println(queue.poll());
//    System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        try {
            System.out.println(queue.pop());
            System.out.println(queue.pop());
            System.out.println(queue.pop());
            System.out.println(queue.pop());
            System.out.println(queue.pop());

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        } catch(NoSuchElementException e) {
            System.out.println(e.toString());
        }
    }
}

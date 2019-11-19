public class HilbertCurve {

    static class HilbertPoint{

        public int HilbertFind(int x, int y, int iter) {
            if(iter == 0) {
                return 1;
            }
            int len = (int)Math.pow(2, iter - 1);
            int area = len * len;

            if(x < len && y < len) {
                return HilbertFind(y, x, iter - 1);
            } else if(x < len && y >= len) {
                return area + HilbertFind(x, y - len, iter - 1);
            } else if(x >= len && y >= len) {
                return 2 * area + HilbertFind(x - len, y - len, iter - 1);
            } else {
                return 3 * area + HilbertFind(len - 1- y, 2 * len - 1 - x, iter - 1);
            }
        }
    }
    public static void main(String[] args) {
        HilbertPoint hb = new HilbertPoint();
        System.out.println(hb.HilbertFind(1, 1, 2));
        System.out.println(hb.HilbertFind(0, 1, 1));
        System.out.println(hb.HilbertFind(2, 2, 2));
        System.out.println(hb.HilbertFind(100, 100, 9));
    }
}

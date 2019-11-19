package Airbnb;
import java.util.*;
/*
Using 2's complement, x AND -x means how many ips we can
represent int CIDR.
1. split ip ",", we can regard ip as 256 bit, and transfer
it to decimal number.
2. Through x AND -x, calculate the number of CIDR represent
by current ip, if the number is out of the range, divide 2
3. Calculate the number in binary is how much bit
Transfer x to ip
 */
public class Bf_ipToCIDR {
    public static List<String> ipToCIDR(String ip, int n) {
        List<String> res = new ArrayList<>();
//        if (ip=="0.0.0.0") {
//            ip = "0.0.0.1";
//            n = n - 1;
//            res.add("0.0.0.0/32");
//        }
        long x = 0;
        String[] parts = ip.split("\\."); // we need \\ here because '.' is a keyword in regex.
        for(int i = 0; i < 4; i++) {
            x = x * 256 + Long.parseLong(parts[i]);
        }
        while(n > 0) {
            long count = x & -x;
            while(count > n) {
                count /= 2;
            }
            res.add(oneCIDR(x, count));
            n = n - (int)count;
            x = x + (int)count;
        }
        return res;
    }
    private static String oneCIDR(long x, long count) {
        int d, c, b, a;
        d = (int) x & 255; // Compute right-most part of ip
        x >>= 8; // throw away the right-most part of ip
        c = (int) x & 255;
        x >>= 8;
        b = (int) x & 255;
        x >>= 8;
        a = (int) x & 255;
        int len = 0;
        // this while loop to know how many digits of count is in binary
        // for example, 00001000 here the len will be 4.
        while(count > 0) {
            count /= 2;
            len++;
        }
        int mask = 32 - (len - 1);
        // Think about 255.0.0.7 -> 11111111 00000000 00000000 00000111
        // x & -x of it is 00000001, the mask is 32. (which is 32 - (1 - 1))
        return new StringBuilder()
                .append(a)
                .append(".")
                .append(b)
                .append(".")
                .append(c)
                .append(".")
                .append(d)
                .append("/")
                .append(mask)
                .toString();
    }
}

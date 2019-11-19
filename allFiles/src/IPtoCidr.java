import java.util.*;

public class IPtoCidr {
//    Using 2's complement, x AND -x means how many ips we can
//    represent int CIDR.
//            1. split ip ",", we can regard ip as 256 bit, and transfer
//    it to decimal number.
//            2. Through x AND -x, calculate the number of CIDR represent
//    by current ip, if the number is out of the range, divide 2
//            3. Calculate the number in binary is how much bit
//    Transfer x to ip
    static class ipToCidr{
        //0000
        public List<String> ip2Cidr(String ip, int range) {
            long start = ip2Long(ip);
            long end = start + range -1;
            List<String> res = new ArrayList<>();

            while(start <= end) {
                long lastOne = start & (-start);//意味着它最多能代表几位
                //follow up: 0000
                int couldRepresent = (int) (Math.log(lastOne) / Math.log(2));
                int cidrNum = 32 - couldRepresent;

                double constriantRange = Math.log(end - start + 1) / Math.log(2);//意味着表示这个range最多需要几位
                int constriant = 32 - (int)Math.floor(constriantRange);

                int curr = Math.max(cidrNum, constriant);
                String currIp = long2Ip(start);
                res.add(currIp + "/" + curr);
                start += Math.pow(2, (32 - curr));
            }
            return res;
        }

        public String long2Ip(long start) {
            StringBuilder sb = new StringBuilder("");
            sb.append(String.valueOf(start >>> 24));
            sb.append(".");
            sb.append(String.valueOf((start & 0X00FFFFFF) >>>16 ));
            sb.append(".");
            sb.append(String.valueOf((start & 0x0000FFFF) >>> 8));
            sb.append(".");
            sb.append(String.valueOf(start & 0x000000FF));
            return sb.toString();
        }

        public long ip2Long(String ip) {
            String[] ipString = ip.split("\\.");
            long[] changedIp = new long[4];
            for(int i = 0; i < 4; i++) {
                changedIp[i] = Long.valueOf(ipString[i]);
            }
            return (changedIp[0] << 24) + (changedIp[1] << 16) + (changedIp[2] << 8) + changedIp[3];
        }
    }

    public static void main(String[] args) {
        ipToCidr ip = new ipToCidr();
        System.out.println(ip.ip2Cidr("255.0.0.7", 10));
        System.out.println(ip.ip2Cidr("255.0.0.0", 256));
        System.out.println(ip.ip2Cidr("255.0.1.0", 1024));
        System.out.println(ip.ip2Cidr("255.0.3.0", 1024));
        System.out.println(ip.ip2Cidr("255.0.10.25", 231));
    }
}

//Input: ip = "255.0.0.7", n = 10
//        Output: ["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
//        Explanation:
//        The initial ip address, when converted to binary, looks like this (spaces added for clarity):
//        255.0.0.7 -> 11111111 00000000 00000000 00000111
//        The address "255.0.0.7/32" specifies all addresses with a common prefix of 32 bits to the given address,
//        ie. just this one address.
//
//        The address "255.0.0.8/29" specifies all addresses with a common prefix of 29 bits to the given address:
//        255.0.0.8 -> 11111111 00000000 00000000 00001000
//        Addresses with common prefix of 29 bits are:
//        11111111 00000000 00000000 00001000
//        11111111 00000000 00000000 00001001
//        11111111 00000000 00000000 00001010
//        11111111 00000000 00000000 00001011
//        11111111 00000000 00000000 00001100
//        11111111 00000000 00000000 00001101
//        11111111 00000000 00000000 00001110
//        11111111 00000000 00000000 00001111
//
//        The address "255.0.0.16/32" specifies all addresses with a common prefix of 32 bits to the given address,
//        ie. just 11111111 00000000 00000000 00010000.
//
//        In total, the answer specifies the range of 10 ips starting with the address 255.0.0.7 .
//
//        There were other representations, such as:
//        ["255.0.0.7/32","255.0.0.8/30", "255.0.0.12/30", "255.0.0.16/32"],
//        but our answer was the shortest possible.
//
//        Also note that a representation beginning with say, "255.0.0.7/30" would be incorrect,
//        because it includes addresses like 255.0.0.4 = 11111111 00000000 00000000 00000100
//        that are outside the specified range.

/*

 */
//public class Bf_ipToCIDR {
//    public static List<String> ipToCIDR(String ip, int n) {
//        List<String> res = new ArrayList<>();
////        if (ip=="0.0.0.0") {
////            ip = "0.0.0.1";
////            n = n - 1;
////            res.add("0.0.0.0/32");
////        }
//        long x = 0;
//        String[] parts = ip.split("\\."); // we need \\ here because '.' is a keyword in regex.
//        for(int i = 0; i < 4; i++) {
//            x = x * 256 + Long.parseLong(parts[i]);
//        }
//        while(n > 0) {
//            long count = x & -x;
//            while(count > n) {
//                count /= 2;
//            }
//            res.add(oneCIDR(x, count));
//            n = n - (int)count;
//            x = x + (int)count;
//        }
//        return res;
//    }
//    private static String oneCIDR(long x, long count) {
//        int d, c, b, a;
//        d = (int) x & 255; // Compute right-most part of ip
//        x >>= 8; // throw away the right-most part of ip
//        c = (int) x & 255;
//        x >>= 8;
//        b = (int) x & 255;
//        x >>= 8;
//        a = (int) x & 255;
//        int len = 0;
//        // this while loop to know how many digits of count is in binary
//        // for example, 00001000 here the len will be 4.
//        while(count > 0) {
//            count /= 2;
//            len++;
//        }
//        int mask = 32 - (len - 1);
//        // Think about 255.0.0.7 -> 11111111 00000000 00000000 00000111
//        // x & -x of it is 00000001, the mask is 32. (which is 32 - (1 - 1))
//        return new StringBuilder()
//                .append(a)
//                .append(".")
//                .append(b)
//                .append(".")
//                .append(c)
//                .append(".")
//                .append(d)
//                .append("/")
//                .append(mask)
//                .toString();
//    }
//}

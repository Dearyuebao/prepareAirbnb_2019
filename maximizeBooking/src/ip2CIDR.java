import java.util.ArrayList;
import java.util.List;

public class ip2CIDR {
    static class ipToCidr{
        public List<String> ip2Cidr(String ip, int range) {
            long start = ip2Long(ip);
            long end = start + range -1;
            List<String> res = new ArrayList<>();

            while(start <= end) {
                long lastOne = start & (-start);
                int couldRepresent = (int) (Math.log(lastOne) / Math.log(2));
                int cidrNum = 32 - couldRepresent;

                double constriantRange = Math.log(end - start + 1) / Math.log(2);
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
//255.0.0.7/32, 255.0.0.8/29, 255.0.0.16/32
//7， 8， 16是当前的其实数字， 32代表这个32位的前32位都一样 因此从7开始 只有7一个数字
//29代表这个32位IP的前29位都一样 后面三位是可以改变的 因此能数8个数字
//因此只需要计算其实数字START 和 位
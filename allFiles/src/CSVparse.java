import java.util.*;

public class CSVparse {
    static class CSVParse{
        public String CSVparse(String str) {
            List<String> items = new ArrayList<>();
            boolean inQuote = false;
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < str.length(); i++) {
                if(inQuote) {
                    if(str.charAt(i) == '\"') {
                        if(i < str.length() - 1 && str.charAt(i + 1) == '\"') {
                            sb.append('\"');
                            i++;
                        } else {
                            inQuote = false;
                        }
                    } else {
                        sb.append(str.charAt(i));
                    }
                } else {
                    if(str.charAt(i) == '\"') {
                        inQuote = true;
                    } else if(str.charAt(i) == ',') {
                        items.add(sb.toString());
                        sb.setLength(0);
                    } else {
                        sb.append(str.charAt(i));
                    }
                }
            }

            if(sb.length() > 0) {
                items.add(sb.toString());
            }
            return String.join("|", items);
        }
    }

    public static void main(String[] args) {
//        String str = "\"\"\"Alexandra Alex\"\"\"";
//        String str2 = "\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1";
//        String str3 = "Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0";
//        CSVParse csv = new CSVParse();
//        System.out.println(csv.CSVparse(str));
//        System.out.println(csv.CSVparse(str2));
//        System.out.println(csv.CSVparse(str3));
        CSVParse p = new CSVParse();
        System.out.println(p.CSVparse("\"\"\"Alexandra Alex\"\"\""));
        System.out.println(p.CSVparse("\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1"));
        System.out.println(p.CSVparse("Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0"));
        System.out.println(p.CSVparse("\"\"\"Alexandra\"\"\""));
    }
}

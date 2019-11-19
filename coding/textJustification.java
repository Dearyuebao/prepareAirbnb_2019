import java.util.ArrayList;
import java.util.List;

public class textJustification {

    public static List<String> originJust(String[] text, int width) {
        int curLen = 0;
        List<String> res = new ArrayList<>();
        int lastI = 0;
        int count = text.length;
        for(int i = 0; i <= count; i++) {
            if(i == count || (curLen + text[i].length() + i - lastI > width)) {
                StringBuilder curr = new StringBuilder();
                int spaceCount = width - curLen;
                int spaceSlot = i - lastI - 1;
                if(i == count || spaceSlot == 0) {
                    for(int j = lastI; j < i; j++) {
                        curr.append(text[j]);
                        if(j != i - 1) {
                            appendSpace(curr, 1);
                        }
                    }
                } else {
                    int num = spaceCount / spaceSlot;
                    int remain = spaceCount % spaceSlot;
                    for(int j = lastI; j < i; j++) {
                        curr.append(text[j]);
                        if(j != i - 1)
                        appendSpace(curr, num + (j - lastI < remain ? 1:0));
                    }
                }
                res.add(curr.toString());
                curLen = 0;
                lastI = i;
            }
            if(i < count) {
                curLen += text[i].length();
            }
        }
        return res;
    }

    public static void appendSpace(StringBuilder curr, int num) {
        for(int i = 0; i < num; i++) {
            curr.append(" ");
        }
    }

    public static List<String> followJust(String[] text, int width) {
        int count = text.length;
        int lastI = 0;
        int curLen = 0;
        List<String> res = new ArrayList<>();
        for(int i = 0; i <= count; i++) {
            if(i == count || curLen + text[i].length() + i - lastI > width) {
                StringBuilder curr = new StringBuilder();
                for(int j = lastI; j < i; j++) {
                    curr.append(text[j]);
                    if(j != i - 1)
                    curr.append(" ");
                }
                if(i != count) {
                    if (curr.length() < width) curr.append(" ");
                    if (curr.length() < width) {
                        int remain = width - curr.length();
                        String sub = text[i].substring(0, remain);
                        curr.append(sub);
                        curr.append("-");
                        text[i] = text[i].substring(remain);
                    }
                }
                lastI = i;
                curLen = 0;
                res.add(curr.toString());
            }
            if(i < count) {
                curLen += text[i].length();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] text = {"This", "is", "an", "example", "of", "test", "justification."};
        List<String> ans = originJust(text, 16);
        for(int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }
        List<String> ans2 = followJust(text, 16);
        for(int i = 0; i < ans2.size(); i++) {
            System.out.println(ans2.get(i));
        }
    }
}

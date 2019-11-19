

import java.net.*;
import java.io.*;
import java.util.*;

public class guessNumber {

    public static String guess() {
        String base = "1111";
        int firstResp = connectServer(base).charAt(0) - '0';
        if (firstResp == 4) {
            return base;
        }

        char[] res = new char[4];
        Arrays.fill(res, '0');
        for (int i = 0; i < 4; i++) {
            int lastResp = firstResp;
            char[] charBase = base.toCharArray();
            for (int j = 2; j < 6; j++) {
                charBase[i] = (char)('0' + j);
                int resp = connectServer(new String(charBase)).charAt(0) - '0';
                if (resp == 4) {
                    return new String(charBase);
                }
                if (resp != lastResp) {
                    res[i] = lastResp > resp ? '1' : (char)('0' + j);
                    break;
                }
            }
            if (res[i] == '0') {
                res[i] = '6';
            }
        }

        return new String(res);
    }

    private static String connectServer(String input) {
        String serverName = "0.0.0.0";//ip
        int port = 44;//端
        String res = "";
        try {

            Socket client = new Socket(serverName, port);
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF(input);
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            res = in.readUTF();
            System.out.println("Server says " + res);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(guess());
    }
}




package Airbnb;
import java.net.*;
import java.io.*;
import java.util.*;
public class Bh_GuessNumber {
    int g = 0;
    public void guess(){
        List<Character> candidate = Arrays.asList(new Character[]{'1','2','3','4','5','6'});
        String res = solve(candidate,"1111",0);
        System.out.println(res);
        System.out.println(g);
        return ;
    }
    private String solve(List<Character> candidate,  String yet, int index){
        if(index >= 4) return yet;
        StringBuilder test = new StringBuilder(yet);

        int[] ini = new int[2];
        List<Character> remove = new ArrayList<>();
        for(int i = 0; i < candidate.size();i++){
            String feedback = new String();
            int[] now = new int[2];
            test.setCharAt(index,candidate.get(i));
            feedback = connectServer(test.toString());
            g++;
            if(i == 0){
                ini[0] = feedback.charAt(0) - '0';
                ini[1] = feedback.charAt(2) - '0';
                if(ini[0] + ini[1] == 0) remove.add(candidate.get(i));
            }else{
                now[0] = feedback.charAt(0) - '0';
                now[1] = feedback.charAt(2) - '0';
                if(now[0] + now[1] == 0) remove.add(candidate.get(i));
                if(now[0] > ini[0]) break;
                else if(now[0] < ini[0]){
                    test.setCharAt(index,candidate.get(0));
                    break;
                }
            }
        }
        candidate.remove(remove);
        return solve(candidate,test.toString(),index + 1);
    }
    private String connectServer(String input){
        String serverName = "0.0.0.0";//ip
        int port = 44;//端口
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
}
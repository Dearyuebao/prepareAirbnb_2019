package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Main extends Thread {
    private ServerSocket serverSocket;

    public Main(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(1000000);
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Waiting for client on port " +
                        serverSocket.getLocalPort() + "..." + serverSocket.getLocalSocketAddress());
                Socket server = serverSocket.accept();
                System.out.println("Just connected to " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());

                String back = guessServer(in.readUTF(), target);
                System.out.println(back);

                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF(back);
                server.close();

            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

        String target = "6543";

    private String guessServer(String guess, String secret) {
        int[] smemo = new int[10];
        int[] gmemo = new int[10];
        int cow = 0;
        int bull = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) bull++;
            else {
                smemo[(int) (secret.charAt(i) - '0')]++;
                gmemo[(int) (guess.charAt(i) - '0')]++;
            }
        }
        for (int i = 0; i < smemo.length; i++) {
            cow += Math.min(smemo[i], gmemo[i]);
        }
        return bull + "A" + cow + "B";
    }

    public static void main(String[] args) {
        int port = 44;
        try {
            Thread t = new Main(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


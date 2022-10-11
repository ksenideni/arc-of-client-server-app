package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static String HOST_NAME = "127.0.0.1";
    public static int PORT = 6666;
    private Socket clientSocket;
    private PrintWriter printWriter;
    private BufferedReader in;
    private Thread texting;
    private Thread chat;

    Client() {
        texting = new Thread(() -> sendingMessage());
        startConnection();
        chat = new Thread(() -> readMessages());
        texting.start();
        chat.start();

    }

    private void startConnection() {
        try {
            clientSocket = new Socket(HOST_NAME, PORT);
            printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readMessages() {
        String line;
        try {
            line = in.readLine();
            while (line != null) {
                System.out.println(line);
                line = in.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendingMessage() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(">> ");
            printWriter.println(scanner.next());
        }
    }

    public void stopConnection() throws IOException {
        in.close();
        printWriter.close();
        clientSocket.close();
    }
}

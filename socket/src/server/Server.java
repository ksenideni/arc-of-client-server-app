package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    public static int SERVER_PORT = 6666;
    public static int TIME_OUT_IN_MILLS = 20_000;
    private static List<String> messages = Collections.synchronizedList(new ArrayList<>());
    private static List<Socket> sockets = Collections.synchronizedList(new ArrayList<>());

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private Thread mail;
    private PrintWriter writer;

    private Server() {
        mail = new Thread(() -> {
            doMailing();
        });
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(SERVER_PORT);
        mail.start();

        while (true) {
            //server waits, listening to the socket
            clientSocket = serverSocket.accept();
            sockets.add(clientSocket);
            new ClientHandler(clientSocket).start();
        }
    }

    public static void writeInChat(String message) {
        messages.add(message);
    }

    public static void deleteSocket(Socket clientSocket) {
        sockets.remove(clientSocket);
    }

    private void doMailing() {
        while (true) {
            System.out.println("[INFO]: " + "start - mail");
            try {
                Thread.sleep(TIME_OUT_IN_MILLS);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            for (Socket socket : sockets) {
                if (messages.isEmpty()) break;
                try {
                    writer = new PrintWriter(socket.getOutputStream(), true);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                writer.println("----start--chat-------");
                System.out.println("[INFO]: " + "--start-debug-mess");
                for (String message : messages) {
                    System.out.println("[INFO]: " + message);
                    writer.println(message);
                }
                writer.println("------end--chat-------");
                System.out.println("[INFO]: " + "end-debug-message");
            }
            messages.clear();
        }

    }

    private void stop() throws IOException {
        serverSocket.close();
    }
}

package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler extends Thread {
    public static String STOP_PHRASE = "stop-connect";
    private Socket clientSocket;
    private BufferedReader in;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String line;
                line = in.readLine();

                while (!line.equals(STOP_PHRASE)) {
                    if (line!=null) {
                        Server.writeInChat(line);
                        line = in.readLine();
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    System.out.println("finally-block");
                    in.close();
                    clientSocket.close();
                    Server.deleteSocket(clientSocket);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
}

package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

    // The port that the server listens on.
    private static final int PORT = 9001;

    /**
     * The application main method, which just listens on a port and
     * spawns handler threads.
     */
    private static int count = 1;

    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
                count++;
            }
        } finally {
            listener.close();
        }
    }

    /**
     * A handler thread class. Handlers are spawned from the listening
     * loop and are responsible for a dealing with a single client
     * and broadcasting its messages.
     */
    private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        // Constructs a handler thread.
        public Handler(Socket socket) {
            this.socket = socket;
            System.out.println("Client: " + count + "\nRemote Socket Address; " + socket.getRemoteSocketAddress());
            // Setup streams, send id to client
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println(count);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Thread run function
        public void run() {
            try {
                while (true) {
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
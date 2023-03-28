package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;

import Client.UI.MainMenu;

public class GameClient {

    BufferedReader in;
    PrintWriter out;

    MainMenu mainMenu;

    // constructor
    public GameClient() {
        mainMenu = new MainMenu();
    }

    /**
     * Prompt for and return the address of the server.
     */
    private String getServerAddress() {
        return null;
    }

    /**
     * Prompt for and return the desired screen name.
     */
    private String getName() {
        return null;
    }

    /**
     * Connects to the server then enters the processing loop.
     */
    private void run() throws IOException {

        // Make connection and initialize streams
        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        String line;
    }

    /**
     * Runs the client as an application with a closeable frame.
     */
    public static void main(String[] args) throws Exception {
        GameClient client = new GameClient();
        client.mainMenu.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.mainMenu.getFrame().setVisible(true);
        client.run();
    }
}

package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;

import Client.UI.FindServer;
import Client.UI.MainMenu;

public class GameClient {

    // Variables
    // Server
    private String serverAddress;
    private int serverPort;
    private BufferedReader in;
    private PrintWriter out;

    // UI
    private int FRAMEWIDTH = 600;
    private int FRAMEHEIGHT = 600;
    private MainMenu mainMenu;
    private FindServer FindServer;

    // constructor
    public GameClient() {
        openMainMenu();
    }

    public void openMainMenu() {
        mainMenu = new MainMenu(FRAMEWIDTH, FRAMEHEIGHT, this);
    }

    public void openFindServer() {
        FindServer = new FindServer(FRAMEWIDTH, FRAMEHEIGHT, this);
    }

    public void openGame() {

    }

    /**
     * Gets the server address.
     */
    private String getServerAddress() {
        return serverAddress;
    }

    /**
     * Sets the server address.
     */
    private void setServerAddress(String address) {
        serverAddress = address;
    }

    /**
     * Gets the server port.
     */
    private String getServerPort() {
        return serverAddress;
    }

    /**
     * Sets the server port.
     */
    private void setServerPort(int port) {
        serverPort = port;
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

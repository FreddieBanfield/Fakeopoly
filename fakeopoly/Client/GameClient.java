package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;

import Client.UI.*;

public class GameClient {

    // Variables
    // Server
    private Socket socket;
    private String serverAddress;
    private int serverPort;
    private BufferedReader in;
    private PrintWriter out;

    // UI
    private int FRAMEWIDTH = 600;
    private int FRAMEHEIGHT = 600;
    private MainMenu mainMenu;
    private FindServer findServer;
    private GameView gameView;
    private GameLobby gameLobby;
    private int id;

    // constructor
    public GameClient() {
        openMainMenu();
    }

    public void openMainMenu() {
        mainMenu = new MainMenu(FRAMEWIDTH, FRAMEHEIGHT, this);
    }

    public void openFindServer() {
        findServer = new FindServer(FRAMEWIDTH, FRAMEHEIGHT, this);
    }

    public void openGameView() {
        gameView = new GameView(FRAMEWIDTH, FRAMEHEIGHT, this);
    }

    public void openGameLobby() {
        gameLobby = new GameLobby(FRAMEWIDTH, FRAMEHEIGHT, this);
    }

    public void connectToServer() throws IOException {
        socket = new Socket(serverAddress, serverPort);
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            setClientId(Integer.parseInt(in.readLine()));
            System.out.println("Client to server connection created\nClient Id: " + getClientId());
        } catch (Exception e) {
            System.out.println("Failed to setup input output streams with server.");
        }
    }

    /**
     * Gets the server address.
     */
    public String getServerAddress() {
        return serverAddress;
    }

    /**
     * Sets the server address.
     */
    public void setServerAddress(String address) {
        serverAddress = address;
    }

    /**
     * Gets the server port.
     */
    public int getServerPort() {
        return serverPort;
    }

    /**
     * Sets the server port.
     */
    public void setServerPort(int port) {
        serverPort = port;
    }

    /**
     * Gets the client Id.
     */
    public int getClientId() {
        return id;
    }

    /**
     * Sets the client Id.
     */
    public void setClientId(int id) {
        this.id = id;
    }

    /**
     * Runs the client as an application with a closeable frame.
     */
    public static void main(String[] args) throws Exception {
        GameClient client = new GameClient();
        client.mainMenu.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.mainMenu.getFrame().setVisible(true);
    }
}

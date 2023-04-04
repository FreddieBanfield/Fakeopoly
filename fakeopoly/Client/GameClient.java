package Client;

import java.awt.Color;
import java.io.Serializable;
import java.rmi.Naming;

import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;

import Client.UI.*;
import Shared.Interfaces.ChatClientIF;
import Shared.Interfaces.ChatServerIF;
import Shared.Interfaces.PlayerIF;
import Shared.Objects.Player;

public class GameClient implements Serializable{

    // Variables
    // Server
    private String serverAddress;
    private int serverPort;

    // UI
    private int FRAMEWIDTH = 600;
    private int FRAMEHEIGHT = 600;
    private MainMenu mainMenu;
    private FindServer findServer;
    private GameView gameView;
    public GameLobby gameLobby;
    private ChatClientService _chatService;
    private int id;
    public Player player;

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

    public Boolean connectToServer(String playerName, Color playerColor) {
        try {
            // lookup method to find reference of remote object
            PlayerIF access = (PlayerIF) Naming
                    .lookup("rmi://" + getServerAddress() + ":" + serverPort + "/PlayerService");
            ChatServerIF chat = (ChatServerIF) Naming.lookup("rmi://" + getServerAddress() + ":" + serverPort + "/ChatService");
            _chatService = new ChatClientService();
            Naming.rebind("rmi://localhost:" + serverPort+ "/ChatClientService", _chatService);

            player = access.createPlayer(playerName, playerColor);

            chat.registerListener(this);
            //System.out.println("Player Name: " + player.getName() + ", Player Color: " + player.getColor());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
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

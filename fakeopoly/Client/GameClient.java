package Client;

import java.awt.Color;
import java.rmi.Naming;

import javax.swing.JFrame;

import Client.UI.*;
import Shared.Interfaces.PlayerServiceIF;
import Shared.Objects.Player;

public class GameClient {

    // Variables
    // Server
    private String serverAddress;
    private int serverPort;

    // API
    PlayerServiceIF _playerService;

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

    public Boolean connectToServer(String playerName, Color playerColor) {
        try {
            // lookup method to find reference of remote object
            _playerService = (PlayerServiceIF) Naming
                    .lookup("rmi://" + getServerAddress() + ":" + serverPort + "/PlayerService");

            setClientId(_playerService.createPlayer(playerName, playerColor));
            System.out.println("Player Name: " + _playerService.getNameById(id) + ", Player Color: "
                    + _playerService.getColorById(id));
            return true;
        } catch (Exception e) {
            System.out.println(e);
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
     * Gets the _playerService.
     */
    public PlayerServiceIF getPlayerService() {
        return _playerService;
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

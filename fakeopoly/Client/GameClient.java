package Client;

import java.awt.Color;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import javax.swing.JFrame;

import Client.Services.ClientService;
import Client.UI.*;
import Shared.Interfaces.PlayerServiceIF;

public class GameClient {

    // Variables
    // Server
    private String serverAddress;
    private int serverPort;

    // Client
    private String clientAddress;
    private int clientPort;

    // API
    private PlayerServiceIF _playerService;
    private ClientService _clientService;

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
            // Setup connections with Server APIs
            _playerService = (PlayerServiceIF) Naming
                    .lookup("rmi://" + getServerAddress() + ":" + getServerPort() + "/PlayerService");

            // Create Player object on server
            setClientId(_playerService.createPlayer(playerName, playerColor));

            // Setup Local API connection for Server
            // Create an object of the interface
            setClientAddress("localhost");
            setClientPort(getServerPort() + getClientId() + 1);
            _clientService = new ClientService();
            // Create rmi registry within the server JVM
            LocateRegistry.createRegistry(getClientPort());
            // Binds the remote object by the name
            Naming.rebind("rmi://" + getClientAddress() + ":" + getClientPort() + "/" + playerName + getClientId(),
                    _clientService);

            // Connects client to Server
            boolean connectClientToServerWasSuccessful = _playerService.connectClient(getClientAddress(),
                    getClientPort(), getClientId());

            if (!connectClientToServerWasSuccessful)
                throw new Exception("Could not connect client to server");

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
     * Gets the server address.
     */
    public String getClientAddress() {
        return clientAddress;
    }

    /**
     * Sets the server address.
     */
    public void setClientAddress(String address) {
        clientAddress = address;
    }

    /**
     * Gets the server port.
     */
    public int getClientPort() {
        return clientPort;
    }

    /**
     * Sets the server port.
     */
    public void setClientPort(int port) {
        clientPort = port;
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

package Server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

import Server.Services.PlayerService;
import Shared.Objects.Message;
import Shared.Objects.Player;

public class GameServer {

    // constants
    private static final int MAXPLAYERS = 4;
    private static final int PORT = 9001;
    // Services
    private static PlayerService _playerService;
    // Objects
    private static ArrayList<Player> players;
    private static ArrayList<Message> messages;

    public static void main(String args[]) {
        // Declare variables
        players = new ArrayList<Player>(MAXPLAYERS);
        messages = new ArrayList<Message>();

        try {
            // Create an object of the interface
            _playerService = new PlayerService(players, messages);
            // Create rmi registry within the server JVM
            LocateRegistry.createRegistry(PORT);
            // Binds the remote object by the name
            Naming.rebind("rmi://localhost:" + PORT + "/PlayerService", _playerService);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
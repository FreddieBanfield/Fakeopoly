package Server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Client.GameClient;
import Client.UpdateService;
import Server.Services.GameService;
import Server.Services.PlayerService;
import Shared.Objects.Player;

public class GameServer {

 

    // constants
    private static final int MAXPLAYERS = 4;
    private static final int PORT = 9001;
    // Services
    private static PlayerService _playerService;
    private static GameService _gameService;
    // Objects
    private static ArrayList<Player> players;
    private static ArrayList<UpdateService> clients;

    public static void main(String args[]) throws RemoteException {
        java.rmi.registry.LocateRegistry.createRegistry(9001);
        // Declare variables
        players = new ArrayList<Player>(MAXPLAYERS);
        clients = new ArrayList<UpdateService>(MAXPLAYERS);
        try {
            // Create an object of the interface
            _playerService = new PlayerService(players);
            _gameService = new GameService();
            // Create rmi registry within the server JVM
            // Binds the remote object by the name

            Naming.rebind("rmi://localhost:" + PORT + "/PlayerService", _playerService);
            Naming.rebind("rmi://localhost:" + PORT + "/GameService", _gameService);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
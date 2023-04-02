package Server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import Server.Services.PlayerService;

public class GameServer {

    // The port that the server listens on.
    private static final int PORT = 9001;
    private static PlayerService _playerService;

    public static void main(String args[]) {
        try {
            // Create an object of the interface
            // implementation class
            _playerService = new PlayerService();
            // rmi registry within the server JVM
            LocateRegistry.createRegistry(PORT);
            // Binds the remote object by the name
            Naming.rebind("rmi://localhost:" + PORT + "/PlayerService", _playerService);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
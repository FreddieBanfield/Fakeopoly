package Server;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Server.Services.PlayerService;
import Shared.Objects.Message;
import Shared.Objects.Player;
import Shared.Objects.Property;

public class GameServer {

    // constants
    private static final int MAXPLAYERS = 4;
    private static final int PORT = 9001;
    // Services
    private static PlayerService _playerService;
    // Objects
    private static ArrayList<Player> players;
    private static ArrayList<Property> properties;
    private static ArrayList<Message> messages;

    public static void main(String args[]) {
        // Declare variables
        players = new ArrayList<Player>(MAXPLAYERS);
        messages = new ArrayList<Message>();
        createAllProperties();

        try {
            // Create an object of the interface
            _playerService = new PlayerService(players, properties, messages);
            // Create rmi registry within the server JVM
            LocateRegistry.createRegistry(PORT);
            // Binds the remote object by the name
            Naming.rebind("rmi://localhost:" + PORT + "/PlayerService", _playerService);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void createAllProperties() {
        properties = new ArrayList<Property>();

        ObjectMapper mapper = new ObjectMapper();
        try {
            properties = mapper.readValue(Paths.get("./fakeopoly/Server/SeededData/Properties.json").toFile(),
                    new TypeReference<ArrayList<Property>>() {
                    });
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
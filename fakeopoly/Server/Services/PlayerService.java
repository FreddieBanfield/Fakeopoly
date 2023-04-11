package Server.Services;

import java.awt.Color;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Shared.Interfaces.*;
import Shared.Objects.Message;
import Shared.Objects.Player;
import Shared.Objects.Property;

public class PlayerService extends UnicastRemoteObject implements PlayerServiceIF {
    private ArrayList<Player> players;
    private ArrayList<Property> properties;
    private ArrayList<Message> messages;

    public PlayerService(ArrayList<Player> players, ArrayList<Property> properties, ArrayList<Message> messages)
            throws RemoteException {
        super();
        this.players = players;
        this.properties = properties;
        this.messages = messages;
    }

    @Override
    public int getNumberOfPlayers() throws RemoteException {
        return players.size();
    }

    /**
     * Saves new player object on server
     * 
     * @param name
     * @param color
     * @return Returns the ID for that player
     * @throws RemoteException
     */
    @Override
    public int createPlayer(String name, Color color) throws RemoteException {
        Player player = new Player(name, color);
        players.add(player);
        int id = players.indexOf(player);
        return id;
    }

    /**
     * Connects the clients Interface to the server via username + id
     * Example: Virality1
     * 
     * @param clientAddress
     * @param clientPort
     * @param id
     * @return Returns true for success, false for fail
     * @throws RemoteException
     */
    @Override
    public boolean connectClient(String clientAddress, int clientPort, int id) throws RemoteException {
        return players.get(id).connectClient(clientAddress, clientPort, id);
    }

    @Override
    public void updatePlayerList() throws RemoteException {
        // Get list of player names
        String[] playerNames = new String[players.size()];
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) != null)
                playerNames[i] = players.get(i).getName();
        }

        // Send update to each client
        for (Player player : players) {
            player.getClient().updatePlayerList(playerNames);
        }
    }

    @Override
    public String getNameById(int id) throws RemoteException {
        return players.get(id).getName();
    }

    @Override
    public Color getColorById(int id) throws RemoteException {
        return players.get(id).getColor();
    }

    @Override
    public void deletePlayer(int id) throws RemoteException {
        players.set(id, null);
    }

    @Override
    public void addMessage(Message message) throws RemoteException {
        messages.add(message);
        UpdateMessageBoard();
    }

    @Override
    public void UpdateMessageBoard() throws RemoteException {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) != null)
                players.get(i).getClient().updateMessageBoard(messages);
        }
    }

    @Override
    public Boolean getIsReadyById(int id) throws RemoteException {
        return players.get(id).getIsReady();
    }

    @Override
    public void setIsReadyById(Boolean isReady, int id) throws RemoteException {
        players.get(id).setIsReady(isReady);
        checkIfAllPlayersAreReady();
    }

    private void checkIfAllPlayersAreReady() throws RemoteException {
        int readyPlayers = 0, totalPlayers = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) != null) {
                totalPlayers++;
                if (getIsReadyById(i) == true) {
                    readyPlayers++;
                }
            }
        }

        if (totalPlayers == readyPlayers && totalPlayers > 1) {
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i) != null)
                    players.get(i).getClient().startGame();
            }
        }
    }

    @Override
    public Property getPropertyById(int id) throws RemoteException {
        return properties.get(id);
    }
}

package Server.Services;

import java.awt.Color;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import Shared.Interfaces.*;
import Shared.Objects.Message;
import Shared.Objects.Player;
import Shared.Objects.Property;

public class PlayerService extends UnicastRemoteObject implements PlayerServiceIF {
    private ArrayList<Player> players;
    private ArrayList<Property> properties;
    private ArrayList<Message> messages;
    private int turn;
    private int totalPlayers;

    public PlayerService(ArrayList<Player> players, ArrayList<Property> properties, ArrayList<Message> messages)
            throws RemoteException {
        super();
        this.players = players;
        this.properties = properties;
        this.messages = messages;
        turn = 0;
    }

    @Override
    public Player getPlayerById(int id) throws RemoteException {
        return players.get(id);
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
    public void addGameMessage(Message message) {
        messages.add(message);
        UpdateGameMessageBoard();
    }

    public void UpdateGameMessageBoard() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) != null)
                try {
                    players.get(i).getClient().updateGameMessageBoard(messages);
                } catch (RemoteException e) {
                    System.out.println(e);
                }
        }
    }

    @Override
    public int createPlayer(String name, Color color) throws RemoteException {
        Player player = new Player(name, color);
        players.add(player);
        int id = players.indexOf(player);
        return id;
    }

    @Override
    public int getTurn() {
        return turn;
    }

    @Override
    public void endTurn() {
        // update other pieces
        if (turn == totalPlayers - 1) {
            turn = 0;
        } else {
            turn++;
        }

        try {
            for (int i = 0; i < totalPlayers; i++) {
                players.get(i).getClient().nextTurn(turn);
                wipe(i);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

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
    public void displayDiceRoll(int id) {
        Thread thread = new Thread(new Runnable() {
            int delay = 50;
            int sum = 0;

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(delay);
                    } catch (Exception e) {
                        System.out.print(e);
                    }
                    int dice1 = (int) (Math.random() * 6 + 1);
                    int dice2 = (int) (Math.random() * 6 + 1);
                    for (int x = 0; x < totalPlayers; x++) {
                        try {
                            players.get(x).getClient().displayDiceRoll(dice1, dice2);
                        } catch (Exception e) {
                            System.out.print(e);
                        }
                    }
                    delay *= 1.3;
                    sum = dice1 + dice2;
                }
                updatePlayerLocation(sum, id);
            }
        });
        thread.start();
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
    public int getMoneyById(int id) throws RemoteException {
        return players.get(id).getMoney();
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
        int readyPlayers = 0;
        totalPlayers = 0;
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

    @Override
    public int getTotalPlayers() {
        return totalPlayers;
    }

    private void wipe(int id) {
        try {
            players.get(id).getClient().wipe();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void updatePlayerLocation(int diceSum, int id) {
        // Update Server Player object
        int currentLocation = players.get(id).getLocation();
        int newLocation = currentLocation + diceSum;
        if (newLocation > 39)
            newLocation -= 40;
        players.get(id).setLocation(newLocation);

        // Update Clients UI
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) != null)
                try {
                    players.get(i).getClient().updatePlayerLocation(newLocation, id);
                } catch (RemoteException e) {
                    System.out.println(e);
                }
        }
    }
}
